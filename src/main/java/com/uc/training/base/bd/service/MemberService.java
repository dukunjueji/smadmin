package com.uc.training.base.bd.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.uc.training.base.bd.dto.LoginLogDTO;
import com.uc.training.base.bd.dto.MemberDTO;
import com.uc.training.base.bd.re.MemberDetailRE;
import com.uc.training.base.bd.re.MemberRE;
import com.uc.training.base.bd.vo.MemberListVO;
import com.uc.training.base.bd.vo.MemberVO;
import com.uc.training.common.mq.vo.MqVO;
import com.uc.training.smadmin.bd.model.Member;
import com.uc.training.smadmin.bd.re.MemberInfoRE;
import com.uc.training.smadmin.bd.vo.MemberBalanceVO;
import com.uc.training.smadmin.bd.vo.MemberInfoVO;
import com.uc.training.smadmin.bd.vo.MemberLoginVO;
import com.uc.training.smadmin.ord.re.OrderConfirmRE;

import java.util.List;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/16
 * 说明：用户功能逻辑判断接口
 */
public interface MemberService {

    /**
    *说明：插入会员信息逻辑
    *@param memberVO 会员对象
    *@return：void
    *@throws：
    */
    Long insertMember(MemberVO memberVO);

    /**
    *说明：根据手机号或者密码查找一个会员
    *@param member 会员对象
    *@return：com.uc.training.smadmin.bd.model.Member
    *@throws：
    */
    MemberRE queryOneMember(MemberVO member);

    /**
    *说明：重置会员密码
    *@param member 会员类
    *@return：void
    *@throws：
    */
    Integer updateMember(MemberVO member);

    /**
    *说明：更新余额(用于充值)
    *@param member
    *@return：void
    *@throws：
    */
    Integer updateMemberBalance(Member member);

    /**
     * 查询用户订单金额
     * @param memberInfoVO
     * @param mqVO
     * @return 返回订单状态码和提示信息
     */

    List<OrderConfirmRE> queryBalances(List<MemberInfoVO> memberInfoVO, MqVO mqVO);

    /**
    *说明：通过会员id得到会员的详细信息(包括会员等级)
    *@param memberId 会员id
    *@return：com.uc.training.smadmin.bd.re.MemberDetailRE
    *@throws：
    */
    MemberDetailRE getMemberDetailById(Long memberId);

    /**
     * 获取登录会员信息
     *
     * @version 1.0 2018/10/23 14:11 by 吴佰川（baichuan.wu@ucarinc.com）创建
     * @param memberLoginVO 登录请求参数
     * @return com.uc.training.smadmin.bd.model.Member
     */
    Member getMemberLogin(MemberLoginVO memberLoginVO);

    /**
    *说明：更新会员信息
    * @param memberVO 更新会员信息请求参数
    *@return：int
    *@throws：
    */
    Integer updateMemberInfo(MemberVO memberVO);

    /**
    *说明：查询会员信息
    *@param uid 会员id
    *@return：com.uc.training.smadmin.bd.re.MemberInfoRE
    *@throws：
    */
    MemberInfoRE queryOneMemberById(Long uid);

    /**
     * 获取会员信息分页页面
     * @param memberListVO
     * @return
     */
    List<MemberRE> getMemberList(MemberListVO memberListVO);

    /**
     * 获取会员数量
     * @param memberListVO
     * @return
     */
    Long queryMemberCount(MemberListVO memberListVO);

    /**
    *说明：支付商品更新余额
    *@param memberBalanceVO 支付商品扣余额参数
    *@return：void
    *@throws：
    */
    void updateBalance(MemberBalanceVO memberBalanceVO);

    /**
    *说明：会员登陆
    *@param loginLog
    *@param mqVO
    *@return：void
    *@throws：
    */
    void memberLogin(LoginLogDTO loginLog, MqVO mqVO);

    /**
     *说明：通过会员id查询会员的手机号
     *@param memberId
     *@return：void
     *@throws：
     */
    Member queryMemberTel(Long memberId);

    /**
    *说明：会员充值业务(包括更新余额和发送消息)
    *@param member
    *@param mqVO
    *@return：void
    *@throws：
    */
    Integer memberRecharge(MemberVO member, MqVO mqVO);

    /**
     *说明：通过手机号查询会员信息
     *@param telephone
     *@return：com.uc.training.smadmin.bd.model.Member
     *@throws：
     */
    Member queryMemberByTel(String telephone);
}
