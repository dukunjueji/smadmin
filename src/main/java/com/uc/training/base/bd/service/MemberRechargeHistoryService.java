package com.uc.training.base.bd.service;

import com.uc.training.smadmin.bd.model.MemberRechargeHistory;
import com.uc.training.smadmin.bd.re.MemberRechargeHistoryListRE;
import com.uc.training.smadmin.bd.vo.MemberRechargeHistoryVO;

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
     * @param memberRechargeHistory
     * @return
     */
    Long insertMemberRechargeHistory(MemberRechargeHistory memberRechargeHistory);

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
