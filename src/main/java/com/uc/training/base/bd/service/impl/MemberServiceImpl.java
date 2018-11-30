package com.uc.training.base.bd.service.impl;

import com.uc.training.base.bd.dto.LoginLogDTO;
import com.uc.training.base.bd.dto.MemberDTO;
import com.uc.training.base.bd.re.MemberDetailRE;
import com.uc.training.base.bd.re.MemberRE;
import com.uc.training.base.bd.service.MemberService;
import com.uc.training.common.enums.GrowthEnum;
import com.uc.training.common.enums.IntegralEnum;
import com.uc.training.common.enums.OrderEnum;
import com.uc.training.common.mq.MqProducer;
import com.uc.training.common.mq.vo.MqVO;
import com.uc.training.common.utils.EncryptUtil;
import com.uc.training.remote.client.BaseClient;
import com.uc.training.smadmin.bd.dao.MemberDao;
import com.uc.training.smadmin.bd.model.Member;
import com.uc.training.smadmin.bd.re.MemberInfoRE;
import com.uc.training.smadmin.bd.service.LoginLogService;
import com.uc.training.smadmin.bd.vo.MemberBalanceVO;
import com.uc.training.smadmin.bd.vo.MemberInfoVO;
import com.uc.training.smadmin.bd.vo.MemberListVO;
import com.uc.training.smadmin.bd.vo.MemberLoginVO;
import com.uc.training.smadmin.gds.service.GoodsService;
import com.uc.training.smadmin.gds.vo.GoodsStokeVO;
import com.uc.training.smadmin.ord.model.Order;
import com.uc.training.smadmin.ord.re.OrderConfirmRE;
import com.uc.training.smadmin.ord.service.OrderService;
import com.uc.training.smadmin.ord.vo.OrdMemberVO;
import com.uc.training.smadmin.ord.vo.OrdOrderVo;
import com.uc.training.smadmin.sms.service.SmsTemplateService;
import com.ycc.tools.middleware.metaq.MetaQUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/16
 * 说明：用户逻辑判断实现类
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    private LoginLogService loginLogService;

    @Autowired
    private SmsTemplateService smsTemplateService;

    @Override
    public Long insertMember(MemberDTO memberDTO) {
        return BaseClient.insertMember(memberDTO);
    }

    @Override
    public MemberRE queryOneMember(MemberDTO member) {
        if (member.getPassword() != null) {
            //密码加密
            member.setPassword(EncryptUtil.md5(member.getPassword()));
        }
        return BaseClient.queryOneMember(member);
    }

    @Override
    public Integer updateMember(MemberDTO member) {
        member.setPassword(EncryptUtil.md5(member.getPassword()));
        return BaseClient.updateMember(member);
    }

    @Override
    public Integer updateMemberBalance(Member member) {
        return memberDao.updateMemberBalance(member);
    }

    /**
     * 查询余额/确认支付
     *
     * @param orderPayInfoNow
     */
    @Override
    public List<OrderConfirmRE> queryBalances(List<MemberInfoVO> orderPayInfoNow, MqVO mqVO) {
        List<OrderConfirmRE> list = new ArrayList<>();
        OrderConfirmRE orderConfirmRE = new OrderConfirmRE();
        MemberInfoVO memberInfoVO = new MemberInfoVO();
        memberInfoVO.setOrderId(orderPayInfoNow.get(0).getOrderId());
        memberInfoVO.setMemberId(orderPayInfoNow.get(0).getMemberId());
        OrdMemberVO ordMemberVO = new OrdMemberVO();
        ordMemberVO.setOrderId(orderPayInfoNow.get(0).getOrderId());
        ordMemberVO.setMemberId(orderPayInfoNow.get(0).getMemberId());
        List<Order> orderList = orderService.getOrderByMemberVO(ordMemberVO);
        if (orderList.size() <= 0) {
            return list;
        }
        BigDecimal accountBalances = memberDao.queryBalances(memberInfoVO.getMemberId());
        if (accountBalances.compareTo(orderList.get(0).getPayPrice()) > 0) {
            // 加上对应的商品销量
            for (int i = 1, j = orderPayInfoNow.size(); i < j; i++) {
                GoodsStokeVO goodsStokeVO = new GoodsStokeVO();
                goodsStokeVO.setStoke(orderPayInfoNow.get(i).getGoodsNum());
                goodsStokeVO.setGoodsId(orderPayInfoNow.get(i).getGoodsId());
                goodsService.updateSales(goodsStokeVO);
            }
            //减去用户余额
            MemberBalanceVO memberBalanceVO = new MemberBalanceVO();
            memberBalanceVO.setMemberId(memberInfoVO.getMemberId());
            memberBalanceVO.setTotalMoney(orderList.get(0).getPayPrice());
            memberDao.updateBalance(memberBalanceVO);
            //加成长值，积分
            MqVO mqVO1 = new MqVO();
            mqVO1.setMemberId(memberInfoVO.getMemberId());
            memberInfoVO.setTotalPrice(orderList.get(0).getPayPrice());
            mqVO1.setGrowthType(GrowthEnum.PURCHASE.getGrowthType());
            mqVO1.setIntegralType(IntegralEnum.PURCHASE.getIntegralType());
            mqVO1.setPurchaseValue(memberInfoVO.getTotalPrice());

            //订单短信
            mqVO1.setGenerateSmsVO(mqVO.getGenerateSmsVO());

            MetaQUtils.sendMsgNoException(new MqProducer(mqVO1));
            //更新订单状态
            orderConfirmRE.setStatus(OrderEnum.WAITSHIP.getKey());
            OrdOrderVo ordOrderVo = new OrdOrderVo();
            ordOrderVo.setOrderNum(orderList.get(0).getOrderNum());
            ordOrderVo.setStatus(OrderEnum.WAITSHIP.getKey().longValue());
            ordOrderVo.setMemberId(memberInfoVO.getMemberId());
            orderConfirmRE.setShowStatus("成功购买商品");
            if (orderService.updateOrder(ordOrderVo) > 0) {
                list.add(orderConfirmRE);
            }

            return list;
        } else {
            orderConfirmRE.setShowStatus("余额不足，请充值或者返回购物车重新选取商品");
            list.add(orderConfirmRE);
            orderConfirmRE.setStatus(OrderEnum.WAITPAY.getKey());
            return list;
        }
    }

    @Override
    public MemberDetailRE getMemberDetailById(Long memberId) {
        return BaseClient.getMemberDetailById(memberId);
    }

    @Override
    public Member getMemberLogin(MemberLoginVO memberLoginVO) {
        return memberDao.getMemberLogin(memberLoginVO);
    }

    @Override
    public Integer updateMemberInfo(MemberDTO member) {
        return BaseClient.updateMember(member);
    }

    @Override
    public MemberInfoRE queryOneMemberById(Long uid) {
        return memberDao.queryOneMemberById(uid);
    }

    @Override
    public List<Member> getMemberList(MemberListVO memberListVO) {
        return memberDao.getMemberList(memberListVO);
    }

    @Override
    public Long queryMemberCount(MemberListVO memberListVO) {
        return memberDao.queryMemberCount(memberListVO);
    }

    @Override
    public void updateBalance(MemberBalanceVO memberBalanceVO) {
        this.memberDao.updateBalance(memberBalanceVO);
    }

    @Override
    public void memberLogin(LoginLogDTO loginLog, MqVO mqVO) {
        BaseClient.insertLoginLog(loginLog);
        //判断是否第一次登陆
        Integer loginNum = BaseClient.queryLoginCount(loginLog);
        if (loginNum != null && loginNum == 1) {
            MetaQUtils.sendMsgNoException(new MqProducer(mqVO));
        }
    }

    @Override
    public Member queryMemberTel(Long memberId) {
        return memberDao.queryMemberTel(memberId);
    }

    @Override
    public Integer memberRecharge(MemberDTO member, MqVO mqVO) {
        Integer status = BaseClient.updateMember(member);
        mqVO.setRechargeStatus(status);
        mqVO.getMemberRechargeHistory().setStatus(status);
        mqVO.getGenerateSmsVO().setRechargeStatus(status);
        MetaQUtils.sendMsgNoException(new MqProducer(mqVO));
        return status;
    }

    @Override
    public Member queryMemberByTel(String telephone) {
        return this.memberDao.queryMemberByTel(telephone);
    }

}
