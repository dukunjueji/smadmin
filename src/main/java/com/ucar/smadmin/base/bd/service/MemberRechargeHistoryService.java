package com.ucar.smadmin.base.bd.service;

import com.ucar.smapi.base.bd.re.MemberRechargeHistoryListRE;
import com.ucar.smadmin.base.bd.vo.MemberRechargeHistoryModelVO;
import com.ucar.smadmin.base.bd.vo.MemberRechargeHistoryVO;

import java.util.List;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/15
 */
public interface MemberRechargeHistoryService {
    /**
     * 新增充值记录
     * @param memberRechargeHistoryModelVO
     * @return
     */
    Long insertMemberRechargeHistory(MemberRechargeHistoryModelVO memberRechargeHistoryModelVO);

    /**
     * 根据会员id获取充值记录
     * @param memberRechargeHistoryVO
     * @return
     */
    List<MemberRechargeHistoryListRE> getRechargeHistoryListByMemberId(MemberRechargeHistoryVO memberRechargeHistoryVO);

    /**
     * 根据会员id获取总记录数
     * @param memberId
     * @return
     */
    Integer getCountByMemberId(Long memberId);
}
