package com.ucar.smadmin.base.bd.service.impl;

import com.ucar.smadmin.base.bd.dto.GrowthDetailDTO;
import com.ucar.smadmin.base.bd.dto.MemberDTO;
import com.ucar.smadmin.base.bd.service.GrowthDetailService;
import com.ucar.smadmin.base.bd.vo.GrowthVO;
import com.ucar.smadmin.base.bd.vo.MemberVO;
import com.ucar.smadmin.enums.GrowthEnum;
import com.ucar.smadmin.remote.remoteclient.BaseClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/26
 * 说明：成长值详情逻辑实现
 */
@Service
public class GrowthDetailServiceImpl implements GrowthDetailService {

    private static final Integer NUM = 100;
    @Autowired
    private BaseClient baseClient;
    @Override
    public Long saveGrowthDetail(GrowthVO growthVO){
        GrowthVO growthDetail = new GrowthVO();

        growthDetail.setMemberId(growthVO.getMemberId());
        growthDetail.setType(growthVO.getType());
        // 获取成长值
        Long growthVaule= getGrowthVauleByType(growthVO);
        growthDetail.setGrowth(growthVaule);

        // 保存积分详情
        GrowthDetailDTO growthDetailDTO = new GrowthDetailDTO();
        growthDetailDTO.setMemberId(growthVO.getMemberId());
        growthDetailDTO.setGrowth(growthVO.getGrowth());
        growthDetailDTO.setType(growthVO.getType());
        baseClient.saveGrowthDetail(growthDetailDTO);

        MemberVO memberVO = new MemberVO();
        memberVO.setId(growthVO.getMemberId());
        memberVO.setGrowth(growthVaule);
        // 更新会员成长值
        MemberDTO memberDTO = new MemberDTO();
        BeanUtils.copyProperties(memberVO, memberDTO);
        baseClient.updateMember(memberDTO).getRe();
        return growthVaule;
    }

    /**
     * 根据成长类型计算成长值
     * @param growthVO
     * @return
     */
    private Long getGrowthVauleByType(GrowthVO growthVO){
        Integer growthType=growthVO.getType();
        Long growthVaule = 0L;

        for(GrowthEnum growthEnum: GrowthEnum.values()){
            if(growthType.equals(GrowthEnum.PURCHASE.getGrowthType())){
                Long purchaseValue = GrowthEnum.PURCHASE.getValue();
                BigDecimal purchaseGrowth = growthVO.getPurchaseValue();
                growthVaule = BigDecimal.valueOf(purchaseValue)
                        .multiply(purchaseGrowth)
                        .divide(BigDecimal.valueOf(NUM)).longValue();
            }else {
                if(growthType.equals(growthEnum.getGrowthType())){
                    growthVaule = growthEnum.getValue();
                }
            }
        }
        return growthVaule;
    }
}
