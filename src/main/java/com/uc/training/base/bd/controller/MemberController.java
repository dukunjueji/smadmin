package com.uc.training.base.bd.controller;

import com.uc.training.base.bd.re.AllMessageRE;
import com.uc.training.base.bd.re.MemberDetailRE;
import com.uc.training.base.bd.re.MemberLoginRE;
import com.uc.training.base.bd.re.MemberRE;
import com.uc.training.base.bd.re.MessageRE;
import com.uc.training.base.bd.service.MemberService;
import com.uc.training.base.bd.service.MessageService;
import com.uc.training.base.bd.vo.ChargeBalanceVO;
import com.uc.training.base.bd.vo.CreateCodeVO;
import com.uc.training.base.bd.vo.LoginVO;
import com.uc.training.base.bd.vo.MemberInfoVO;
import com.uc.training.base.bd.vo.MemberLoginVO;
import com.uc.training.base.bd.vo.MemberRechargeHistoryModelVO;
import com.uc.training.base.bd.vo.MemberRegisterVO;
import com.uc.training.base.bd.vo.MemberVO;
import com.uc.training.base.bd.vo.MessageVO;
import com.uc.training.base.bd.vo.PasswordEditVO;
import com.uc.training.base.sms.vo.GenerateSmsVO;
import com.uc.training.common.annotation.AccessLogin;
import com.uc.training.common.base.controller.BaseController;
import com.uc.training.common.enums.GrowthEnum;
import com.uc.training.common.enums.SmsTypeEnum;
import com.uc.training.common.mq.MqProducer;
import com.uc.training.common.mq.vo.MqVO;
import com.uc.training.common.redis.RedisConfigEnum;
import com.uc.training.common.utils.TokenUtil;
import com.uc.training.ord.service.OrderService;
import com.ycc.base.common.Result;
import com.ycc.tools.middleware.metaq.MetaQUtils;
import com.ycc.tools.middleware.redis.RedisCacheUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/16
 * 说明：用户请求处理
 */
@Controller
@RequestMapping("api/member")
public class MemberController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MemberController.class);
    private static final Integer MESSAGE_STATUS = 1;
    @Autowired
    private MemberService memberService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MessageService messageService;

    /**
     *注册时，生成验证码及短信
     * @param createCodeVO 生成验证码的请求参数
     * @return 显示页面提示信息
     */
    @RequestMapping(value = "/createCode.do_", method = RequestMethod.POST)
    @ResponseBody
    @AccessLogin(required = false)
    public Result createCode(@Validated CreateCodeVO createCodeVO) {
        RedisCacheUtils redis = RedisCacheUtils.getInstance(RedisConfigEnum.SYS_CODE);
        if (redis.get(createCodeVO.getTelephone()) != null) {
            return Result.getBusinessException("请不要频繁操作！", null);
        }
        //根据手机号查询会员信息
        MemberVO memberVO = new MemberVO();
        memberVO.setTelephone(createCodeVO.getTelephone());
        MemberRE member = memberService.queryOneMember(memberVO);
        if(member != null){
            return Result.getBusinessException("手机号码已被注册", null);
        }
        //生成消息体
        MqVO mqVO = new MqVO();
        GenerateSmsVO generateSmsVO = new GenerateSmsVO();
        generateSmsVO.setTelephone(createCodeVO.getTelephone());
        generateSmsVO.setCode(SmsTypeEnum.REGISTER.getCode());
        generateSmsVO.setType(SmsTypeEnum.REGISTER.getType());
        generateSmsVO.setEmil(createCodeVO.getEmail());
        mqVO.setGenerateSmsVO(generateSmsVO);

        MetaQUtils.sendMsgNoException(new MqProducer(mqVO));
        return Result.getSuccessResult("验证码已发送");
    }

    /***
    *说明：会员注册
    *@param memberRegisterVO 会员注册的请求
    *@return：com.uc.training.smadmin.bd.re.MemberMegRE
    *@throws：
    */
    @RequestMapping(value = "/memberRegister.do_", method = RequestMethod.POST)
    @ResponseBody
    @AccessLogin(required = false)
    public Result memberRegister(@Validated MemberRegisterVO memberRegisterVO){
        MemberVO member = new MemberVO();
        member.setTelephone(memberRegisterVO.getTelephone());
        member.setPassword(memberRegisterVO.getPassword());
        member.setEmail(memberRegisterVO.getEmail());
        MemberRE mem = memberService.queryOneMember(member);
        if(mem != null){
            return Result.getBusinessException("手机号码已被注册",null);
        }
        //redis
        RedisCacheUtils redis = RedisCacheUtils.getInstance(RedisConfigEnum.SYS_CODE);
        String msg = redis.get(memberRegisterVO.getTelephone());
        if (msg == null) {
            return Result.getBusinessException("验证码已过期，请重新获取", null);
        }
        if(memberRegisterVO.getTelCode().equals(msg)){
            memberService.insertMember(member);
            return Result.getSuccessResult("注册成功");
        }else {
            return Result.getBusinessException("验证码不正确", null);
        }
    }

    /**
     * 会员登录
     * @param memberLoginVO 会员登录请求参数
     * @return com.ycc.base.common.Result<com.uc.training.smadmin.bd.re.MemberLoginRE>
     */
    @RequestMapping(value = "/memberLogin.do_", method = RequestMethod.POST)
    @ResponseBody
    @AccessLogin(required = false)
    public Result memberLogin(@Validated MemberLoginVO memberLoginVO){
        MemberVO member = new MemberVO();
        member.setTelephone(memberLoginVO.getTelephone());
        MemberRE mem = memberService.queryOneMember(member);
        if (mem == null) {
            return Result.getBusinessException("该账号不存在,请先注册", null);
        }
        member.setPassword(memberLoginVO.getPassword());
        MemberRE memberRE = memberService.queryOneMember(member);
        if (memberRE == null) {
            return Result.getBusinessException("您的密码错误", null);
        }else {
            String token = TokenUtil.sign(memberRE.getId());
            MemberLoginRE memberLoginRE = new MemberLoginRE();
            memberLoginRE.setToken(token);

            //生成登陆日志
            LoginVO loginLog = new LoginVO();
            loginLog.setMemberId(memberRE.getId());
            loginLog.setIp(getLocalhostIp());

            //生成消息体
            MqVO mqVO = new MqVO();
            mqVO.setMemberId(memberRE.getId());
            mqVO.setGrowthType(GrowthEnum.LOGININ.getGrowthType());

            memberService.memberLogin(loginLog, mqVO);
            return Result.getSuccessResult(memberLoginRE);
        }
    }

    /**
     * 重置密码时，生成验证码及短信
     * @param createCodeVO 发送验证码的请求参数
     * @return 显示页面提示信息
     */
    @RequestMapping(value = "/passwordCode.do_", method = RequestMethod.POST)
    @ResponseBody
    @AccessLogin(required = false)
    public Result passwordCode(@Validated CreateCodeVO createCodeVO) {
        MemberVO member = new MemberVO();
        member.setTelephone(createCodeVO.getTelephone());
        MemberRE memberRE = memberService.queryOneMember(member);
        if(memberRE == null){
            return Result.getBusinessException("手机号还没被注册", null);
        }
        RedisCacheUtils redis = RedisCacheUtils.getInstance(RedisConfigEnum.SYS_CODE);
        if (redis.get(createCodeVO.getTelephone()) != null) {
            return Result.getBusinessException("请不要频繁操作！", null);
        }
        //生成消息体
        MqVO mqVO = new MqVO();
        GenerateSmsVO generateSmsVO = new GenerateSmsVO();
        generateSmsVO.setTelephone(createCodeVO.getTelephone());
        generateSmsVO.setCode(SmsTypeEnum.FORGET_PASSWORD.getCode());
        generateSmsVO.setType(SmsTypeEnum.FORGET_PASSWORD.getType());
        generateSmsVO.setEmil(memberRE.getEmail());
        mqVO.setGenerateSmsVO(generateSmsVO);

        MetaQUtils.sendMsgNoException(new MqProducer(mqVO));
        return Result.getSuccessResult("验证码已发送");
    }

    /***
     *说明：会员重置密码
     *@param memberRegisterVO 重置密码请求参数
     *@return：com.uc.training.smadmin.bd.re.MemberMegRE
     *@throws：
     */
    @RequestMapping(value = "/memberPassword.do_", method = RequestMethod.POST)
    @ResponseBody
    @AccessLogin(required = false)
    public Result memberPassword(@Validated MemberRegisterVO memberRegisterVO){
        MemberVO member = new MemberVO();
        member.setTelephone(memberRegisterVO.getTelephone());
        MemberRE memberRE = memberService.queryOneMember(member);
        if(memberRE == null){
            return Result.getBusinessException("手机号还没被注册", null);
        }
        //redis
        RedisCacheUtils redis = RedisCacheUtils.getInstance(RedisConfigEnum.SYS_CODE);
        String msg = redis.get(memberRegisterVO.getTelephone());
        if (msg == null) {
            return Result.getBusinessException("验证码已过期，请重新获取", null);
        }
        if(memberRegisterVO.getTelCode().equals(msg)){
            member = new MemberVO();
            member.setPassword(memberRegisterVO.getPassword());
            member.setId(memberRE.getId());
            member.setModifyEmp(getUid());
            memberService.updateMember(member);
            return Result.getSuccessResult("重置密码成功");
        }else {
            return Result.getBusinessException("验证码不正确", null);
        }
    }

    /**
     *说明：会员充值金额
     * @param chargeBalanceVO 充值余额的请求参数
     * @return
     */
    @AccessLogin
    @RequestMapping(value = "/chargeBalance.do_", method = RequestMethod.POST)
    @ResponseBody
    public Result chargeBalance(@Validated ChargeBalanceVO chargeBalanceVO){
        String regExp = "^([1-9]\\d*|0)(\\.\\d{1,2})?$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(chargeBalanceVO.getBalance().toString());
        if(!m.matches()){
            return Result.getBusinessException("充值金额有误", null);
        }
        MemberVO memberVO = new MemberVO();
        memberVO.setId(getUid());
        MemberRE memberRE = memberService.queryOneMember(memberVO);
        memberVO.setBalance(chargeBalanceVO.getBalance());
        BigDecimal bigDecimal = new BigDecimal(0);
        int i = chargeBalanceVO.getBalance().compareTo(bigDecimal);
        if (chargeBalanceVO.getBalance() == null || i == 0 ) {
            return Result.getBusinessException("充值余额必须大于0", null);
        }else {
            //生成消息体
            MqVO mqVO = new MqVO();
            mqVO.setMemberId(getUid());
            mqVO.setRechargeValue(chargeBalanceVO.getBalance());

            //生成充值记录
            MemberRechargeHistoryModelVO memberRechargeHistory = new MemberRechargeHistoryModelVO();
            memberRechargeHistory.setMemberId(getUid());
            memberRechargeHistory.setCreateEmp(getUid());
            memberRechargeHistory.setModifyEmp(getUid());
            memberRechargeHistory.setBalance(chargeBalanceVO.getBalance());
            mqVO.setMemberRechargeHistory(memberRechargeHistory);

            //生成短信
            MemberVO md = new MemberVO();
            md.setId(getUid());
            MemberRE mem = memberService.queryOneMember(md);
            GenerateSmsVO generateSmsVO = new GenerateSmsVO();
            generateSmsVO.setTelephone(mem.getTelephone());
            generateSmsVO.setMessage(chargeBalanceVO.getBalance().toString());
            generateSmsVO.setCode(SmsTypeEnum.RECHARGE.getCode());
            generateSmsVO.setType(SmsTypeEnum.RECHARGE.getType());
            generateSmsVO.setEmil(memberRE.getEmail());
            mqVO.setGenerateSmsVO(generateSmsVO);

            return Result.getSuccessResult(memberService.memberRecharge(memberVO, mqVO));
        }
    }

    /**
    *说明：通过id获取会员的详细信息(包括会员等级)
    *@param
    *@return：com.ycc.base.common.Result<com.uc.training.smadmin.bd.re.MemberDetailRE>
    *@throws：
    */
    @RequestMapping(value = "/getMemberDetailById.do_", method = RequestMethod.GET)
    @ResponseBody
    @AccessLogin
    public Result<MemberDetailRE> getMemberDetailById(){
        MemberDetailRE memberDetailRE = memberService.getMemberDetailById(getUid());
        //会员的订单数量
        Integer orderSum = orderService.queryOrderCount(getUid());
        memberDetailRE.setOrderSum(orderSum);
        //会员的消息数量
        MessageVO messageVO = new MessageVO();
        messageVO.setMemberId(getUid());
        Integer messageSum = messageService.queryMessageCount(messageVO);
        memberDetailRE.setMessageSum(messageSum);

        return Result.getSuccessResult(memberDetailRE);
    }

    /**
     * 获取会员信息
     * @return
     */
    @RequestMapping(value = "/getMemberInfoById.do_", method = RequestMethod.GET)
    @ResponseBody
    @AccessLogin
    public Result<MemberRE> getMemberInfoById(){
        MemberVO memberVO = new MemberVO();
        memberVO.setId(getUid());
        return Result.getSuccessResult(memberService.queryOneMember(memberVO));
    }

    /**
    *说明：更新会员信息
    *@param memberInfoVO 更新会员信息请求参数
    *@return：com.ycc.base.common.Result
    *@throws：
    */
    @RequestMapping(value = "/editMemberInfo.do_", method = RequestMethod.POST)
    @ResponseBody
    @AccessLogin
    public Result<Integer> editMemberInfo(@Validated MemberInfoVO memberInfoVO){
        MemberVO member = new MemberVO();
        member.setNickname(memberInfoVO.getNickname());
        member.setEmail(memberInfoVO.getEmail());
        member.setSex(memberInfoVO.getSex());
        member.setId(getUid());
        member.setImageUrl(memberInfoVO.getImageUrl());
        member.setModifyEmp(getUid());

        //更新会员信息
        return Result.getSuccessResult(memberService.updateMemberInfo(member));
    }

    /**
    *说明：修改密码时发送验证码
    *@param sendCodeVO 发送验证码接受的参数
    *@return：com.ycc.base.common.Result
    *@throws：
    */
    @ResponseBody
    @RequestMapping(value = "/sendCode.do_", method = RequestMethod.GET)
    @AccessLogin
    public Result sendCode(@Validated PasswordEditVO sendCodeVO){
        // 判断新密码和确认密码是否一致
        if (!(sendCodeVO.getNewpassword()).equals(sendCodeVO.getConfirmpassword())){
            return Result.getBusinessException("两次输入的密码不一致", null);
        }

        MemberVO memberVO = new MemberVO();
        memberVO.setId(getUid());
        memberVO.setPassword(sendCodeVO.getOldpassword());
        MemberRE member = memberService.queryOneMember(memberVO);
        // 校验密码
        if (member == null) {
            return Result.getBusinessException("原来的密码输入有误", null);
        }
        RedisCacheUtils redis = RedisCacheUtils.getInstance(RedisConfigEnum.SYS_CODE);
        if (redis.get(member.getTelephone()) != null) {
            return Result.getBusinessException("请不要频繁操作！", null);
        }
        //生成消息体
        MqVO mqVO = new MqVO();
        GenerateSmsVO generateSmsVO = new GenerateSmsVO();
        generateSmsVO.setTelephone(member.getTelephone());
        generateSmsVO.setCode(SmsTypeEnum.CHANGE_PASSWORD.getCode());
        generateSmsVO.setType(SmsTypeEnum.CHANGE_PASSWORD.getType());
        generateSmsVO.setEmil(member.getEmail());
        mqVO.setGenerateSmsVO(generateSmsVO);
        MetaQUtils.sendMsgNoException(new MqProducer(mqVO));
        return Result.getSuccessResult("验证码已发送");
    }

    /**
    *说明：修改会员密码
    *@param passwordEditVO 修改会员密码接收的参数
    *@return：com.ycc.base.common.Result
    *@throws：
    */
    @ResponseBody
    @RequestMapping(value = "/editMemberPassword.do_", method = RequestMethod.POST)
    @AccessLogin
    public Result editMemberPassword(@Validated PasswordEditVO passwordEditVO){
        if (!passwordEditVO.getNewpassword().equals(passwordEditVO.getConfirmpassword())) {
            return Result.getBusinessException("新的密码和确认密码不一致", null);
        }
        if (StringUtils.isEmpty(passwordEditVO.getCode())) {
            return Result.getBusinessException("验证码不能为空", null);
        }
        MemberVO member = new MemberVO();
        member.setId(getUid());
        member.setPassword(passwordEditVO.getOldpassword());
        // 判断旧密码是否和库里的一致
        MemberRE mem = memberService.queryOneMember(member);
        if(mem == null){
            return Result.getBusinessException("原来的密码输入有误", null);
        }
        //redis
        RedisCacheUtils redis = RedisCacheUtils.getInstance(RedisConfigEnum.SYS_CODE);
        String msg = redis.get(mem.getTelephone());
        if (msg == null) {
            return Result.getBusinessException("验证码已过期，请重新获取", null);
        }
        if((passwordEditVO.getCode()).equals(msg)){
            member.setPassword(passwordEditVO.getNewpassword());
            member.setModifyEmp(getUid());
            memberService.updateMember(member);
            return Result.getSuccessResult("修改密码成功");
        }else {
            return Result.getBusinessException("输入的验证码有误", null);
        }
    }

    /**
    *说明：获取指定会员的消息列表
    *@param
    *@return：
    *@throws：
    */
    @ResponseBody
    @AccessLogin()
    @RequestMapping(value = "queryMessageList.do_", method = RequestMethod.GET)
    public Result<AllMessageRE> queryMessageList(MessageVO messageVO){
        messageVO.setMemberId(getUid());
        List<MessageRE> messageREList = messageService.queryMessageList(messageVO);
        Integer totalNum = messageService.queryMessageCount(messageVO);

        AllMessageRE allMessageRE = new AllMessageRE();
        allMessageRE.setMessageREList(messageREList);
        allMessageRE.setTotalNum(totalNum);
        return Result.getSuccessResult(allMessageRE);
    }

    /**
    *说明：更新消息的状态
    *@param messageVO
    *@return：com.ycc.base.common.Result
    *@throws：
    */
    @RequestMapping(value = "/updateMessageStatus.do_", method = RequestMethod.POST)
    @AccessLogin
    @ResponseBody
    public Result updateMessageStatus(@Validated MessageVO messageVO){
        MessageVO message = new MessageVO();
        message.setId(messageVO.getId());
        message.setIsRead(MESSAGE_STATUS);
        message.setMemberId(getUid());
        message.setModifyEmp(getUid());
        return Result.getSuccessResult( messageService.updateMessage(message));
    }

    /**
    *说明：查询一个消息详情
    *@param messageId
    *@return：com.ycc.base.common.Result<com.uc.training.smadmin.bd.vo.MessageDetailVO>
    *@throws：
    */
    @RequestMapping(value = "/queryOneMessageById.do_", method = RequestMethod.GET)
    @AccessLogin
    @ResponseBody
    public Result<MessageRE> queryOneMessageById(Long messageId){
        MessageVO messageVO = new MessageVO();
        messageVO.setId(messageId);
        messageVO.setMemberId(getUid());
        return Result.getSuccessResult(messageService.queryOneMessageById(messageVO));
    }
}
