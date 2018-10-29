package com.uc.training.smadmin.bd.service;

import com.uc.training.smadmin.bd.model.Member;
import com.uc.training.smadmin.bd.re.MemberDetailRE;
import com.uc.training.smadmin.bd.re.MemberInfoRE;
import com.uc.training.smadmin.bd.vo.ChargeBalanceVO;
import com.uc.training.smadmin.bd.vo.MemberInfoVO;
import com.uc.training.smadmin.bd.vo.MemberLoginVO;
import com.uc.training.smadmin.ord.re.OrderConfirmRE;
import com.uc.training.smadmin.ord.re.OrderRe;

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
    *@param member 会员对象
    *@return：void
    *@throws：
    */
    void insertMember(Member member);

    /**
    *说明：根据手机号或者密码查找一个会员
    *@param member 会员对象
    *@return：com.uc.training.smadmin.bd.model.Member
    *@throws：
    */
    Member queryOneMember(Member member);

    /**
    *说明：重置会员密码
    *@param member 会员类
    *@return：void
    *@throws：
    */
    void updateMember(Member member);

    /**
    *说明：更新余额
    *@param member
    *@return：void
    *@throws：
    */
    void updateMemberBalance(Member member);

    /**
     * 查询用户订单金额
     * @param memberInfoVO
     * @return 返回订单状态码和提示信息
     */

    List<OrderConfirmRE> queryBalances(List<MemberInfoVO> memberInfoVO);

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
    * @param member 更新会员信息请求参数
    *@return：int
    *@throws：
    */
    int updateMemberInfo(Member member);

    /**
    *说明：查询会员信息
    *@param uid 会员id
    *@return：com.uc.training.smadmin.bd.re.MemberInfoRE
    *@throws：
    */
    MemberInfoRE queryOneMemberById(Long uid);
}
