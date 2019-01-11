package com.ucar.smadmin.base.bd.service.impl;

import com.ucar.smadmin.base.bd.dto.MemberRechargeHistoryDTO;
import com.ucar.smadmin.base.bd.dto.MemberRechargeHistoryModelDTO;
import com.ucar.smadmin.base.bd.re.MemberRechargeHistoryListRE;
import com.ucar.smadmin.base.bd.service.MemberRechargeHistoryService;
import com.ucar.smadmin.base.bd.vo.MemberRechargeHistoryModelVO;
import com.ucar.smadmin.base.bd.vo.MemberRechargeHistoryVO;
import com.ucar.smadmin.remote.remoteclient.BaseClient;
import org.springframework.beans.BeanUtils;
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
    private BaseClient baseClient;

    /**
     * 新增充值记录
     *
     * @param memberRechargeHistoryModelVO
     * @return
     */
    @Override
    public Long insertMemberRechargeHistory(MemberRechargeHistoryModelVO memberRechargeHistoryModelVO) {
        MemberRechargeHistoryModelDTO memberRechargeHistoryModelDTO = new MemberRechargeHistoryModelDTO();
        BeanUtils.copyProperties(memberRechargeHistoryModelVO, memberRechargeHistoryModelDTO);
        return baseClient.insertMemberRechargeHistory(memberRechargeHistoryModelDTO).getRe();
    }

    /**
     * 根据会员id获取充值记录
     *
     * @param memberRechargeHistoryVO
     * @return
     */
    @Override
    public List<MemberRechargeHistoryListRE> getRechargeHistoryListByMemberId(MemberRechargeHistoryVO memberRechargeHistoryVO) {
        MemberRechargeHistoryDTO memberRechargeHistoryDTO = new MemberRechargeHistoryDTO();
        BeanUtils.copyProperties(memberRechargeHistoryVO, memberRechargeHistoryDTO);
        return baseClient.getRechargeHistoryListByMemberId(memberRechargeHistoryDTO).getRe();
    }

    /**
     * 根据会员id获取总记录数
     *
     * @param memberId
     * @return
     */
    @Override
    public Integer getCountByMemberId(Long memberId) {
        Long count = baseClient.getCountByMemberId(memberId).getRe();
        if (count == null) {
            return null;
        }
        return count.intValue();
    }
}
