package com.uc.training.smadmin.bd.dao;

import com.uc.training.smadmin.bd.model.Member;
import com.uc.training.smadmin.bd.re.MemberDetailRE;
import com.uc.training.smadmin.bd.re.MemberInfoRE;
import com.uc.training.smadmin.bd.vo.*;

import java.util.List;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/16
 * 说明：用户接口
 */
public interface MemberDao {

    /**
     * 插入会员信息
     *
     * @param member 会员对象
     */
    void insertMember(Member member);

    /**
     * 说明：根据手机号或者密码查找一个会员
     *
     * @param member 会员类
     * @return：com.uc.training.smadmin.bd.model.Member
     * @throws：
     */
    Member queryOneMember(Member member);

    /**
     * 说明：重置会员密码
     *
     * @param member 会员类
     * @return：void
     * @throws：
     */
    void updateMember(Member member);

    /**
     * 说明：更新会员余额
     *
     * @param member
     * @return：void
     * @throws：
     */
    void updateMemberBalance(Member member);

    /**
     * 查询余额
     * @param memberId
     * @return
     */
    Double queryBalances(Long memberId);

    /**
     * 说明：通过会员id得到会员的详细信息(包括会员等级)
     *
     * @param memberId 会员id
     * @return：com.uc.training.smadmin.bd.re.MemberDetailRE
     * @throws：
     */
    MemberDetailRE getMemberDetailById(Long memberId);

    /**
     * 获取登录会员信息
     *
     * @param memberLoginVO 登录请求参数
     * @return com.uc.training.smadmin.bd.model.Member
     * @version 1.0 2018/10/23 14:12 by 吴佰川（baichuan.wu@ucarinc.com）创建
     */
    Member getMemberLogin(MemberLoginVO memberLoginVO);

    /**
    *说明：更新会员的信息
    *@param member
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

    /**
    *说明：更新会员积分
    *@param memberIntegralVO
    *@return：void
    *@throws：
    */
    void updateIntegralById(MemberIntegralVO memberIntegralVO);

    /**
    *说明：更新会员成长值
    *@param memberGrowthVO
    *@return：void
    *@throws：
    */
    void updateGrowthById(MemberGrowthVO memberGrowthVO);

    /**
     * 获取用户信息分页页面
     * @param memberListVO
     * @return
     */
    List<Member> getMemberList(MemberListVO memberListVO);

    /**
     * 查询会员数量
     * @param memberListVO
     * @return
     */
    Long queryMemberCount(MemberListVO memberListVO);

    /**
    *说明：支付商品更新余额
    *@param memberBalanceVO
    *@return：void
    *@throws：
    */
    void updateBalance(MemberBalanceVO memberBalanceVO);
    /**
    *说明：通过会员id查询会员的手机号
    *@param memberId
    *@return：void
    *@throws：
    */
    Member queryMemberTel(Long memberId);

    /**
    *说明：通过手机号查询会员信息
    *@param telephone
    *@return：com.uc.training.smadmin.bd.model.Member
    *@throws：
    */
    Member queryMemberByTel(String telephone);
}
