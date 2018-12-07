package com.uc.training.base.bd.service.impl;

import com.uc.training.base.bd.re.MemberRechargeHistoryListRE;
import com.uc.training.base.bd.service.MemberRechargeHistoryService;
import com.uc.training.base.bd.vo.MemberRechargeHistoryModelVO;
import com.uc.training.base.bd.vo.MemberRechargeHistoryVO;
import com.uc.training.remote.client.BaseClient;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/15
 */
@Service
public class MemberRechargeHistoryServiceImpl implements MemberRechargeHistoryService {

    /**
     * 新增充值记录
     *
     * @param memberRechargeHistoryModelVO
     * @return
     */
    @Override
    public Long insertMemberRechargeHistory(MemberRechargeHistoryModelVO memberRechargeHistoryModelVO) {
       return BaseClient.insertMemberRechargeHistory(memberRechargeHistoryModelVO);
    }

    /**
     * 根据会员id获取充值记录
     *
     * @param memberRechargeHistoryVO
     * @return
     */
    @Override
    public List<MemberRechargeHistoryListRE> getRechargeHistoryListByMemberId(MemberRechargeHistoryVO memberRechargeHistoryVO) {
        return BaseClient.getRechargeHistoryListByMemberId(memberRechargeHistoryVO);
    }

    /**
     * 根据会员id获取总记录数
     *
     * @param memberId
     * @return
     */
    @Override
    public Integer getCountByMemberId(Long memberId) {
        Long count = BaseClient.getCountByMemberId(memberId);
        if (count == null) {
            return null;
        }
        return count.intValue();
    }
}
