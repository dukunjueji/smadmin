package com.uc.training.base.bd.service.impl;

import com.uc.training.smadmin.bd.dao.MemberRechargeHistoryDao;
import com.uc.training.smadmin.bd.model.MemberRechargeHistory;
import com.uc.training.smadmin.bd.re.MemberRechargeHistoryListRE;
import com.uc.training.smadmin.bd.service.MemberRechargeHistoryService;
import com.uc.training.smadmin.bd.vo.MemberRechargeHistoryVO;
import com.uc.training.smadmin.utils.InjectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private MemberRechargeHistoryDao memberRechargeHistoryDao;

    /**
     * 新增充值记录
     *
     * @param memberRechargeHistory
     * @return
     */
    @Override
    public Long insertMemberRechargeHistory(MemberRechargeHistory memberRechargeHistory) {
        this.memberRechargeHistoryDao = InjectionUtils.getInjectionInstance(MemberRechargeHistoryDao.class);
        return this.memberRechargeHistoryDao.insertMemberRechargeHistory(memberRechargeHistory);
    }

    /**
     * 根据会员id获取充值记录
     *
     * @param memberRechargeHistoryVO
     * @return
     */
    @Override
    public List<MemberRechargeHistoryListRE> getRechargeHistoryListByMemberId(MemberRechargeHistoryVO memberRechargeHistoryVO) {
        return memberRechargeHistoryDao.getRechargeHistoryListByMemberId(memberRechargeHistoryVO);
    }

    /**
     * 根据会员id获取总记录数
     *
     * @param memberId
     * @return
     */
    @Override
    public Integer getCountByMemberId(Long memberId) {
        return memberRechargeHistoryDao.getCountByMemberId(memberId);
    }
}
