package com.uc.training.base.bd.service.impl;

import com.uc.training.base.bd.service.GrowthDetailService;
import com.uc.training.base.bd.vo.GrowthVO;
import com.uc.training.base.bd.vo.MemberVO;
import com.uc.training.common.enums.GrowthEnum;
import com.uc.training.remote.client.BaseClient;
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
    @Override
    public Long saveGrowthDetail(GrowthVO growthVO){
        GrowthVO growthDetail = new GrowthVO();

        growthDetail.setMemberId(growthVO.getMemberId());
        growthDetail.setType(growthVO.getType());
        // 获取成长值
        Long growthVaule= getGrowthVauleByType(growthVO);
        growthDetail.setGrowth(growthVaule);

        // 保存积分详情
        BaseClient.saveGrowthDetail(growthDetail);

        MemberVO memberVO = new MemberVO();
        memberVO.setId(growthVO.getMemberId());
        memberVO.setGrowth(growthVaule);
        // 更新会员成长值
        BaseClient.updateMember(memberVO);
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
