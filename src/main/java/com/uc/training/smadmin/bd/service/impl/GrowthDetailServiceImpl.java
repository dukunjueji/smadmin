package com.uc.training.smadmin.bd.service.impl;

import com.uc.training.common.enums.GrowthEnum;
import com.uc.training.smadmin.bd.dao.GrowthDetailDao;
import com.uc.training.smadmin.bd.dao.MemberDao;
import com.uc.training.smadmin.bd.vo.ContinueSignDaysVO;
import com.uc.training.smadmin.bd.model.GrowthDetail;
import com.uc.training.smadmin.bd.service.GrowthDetailService;
import com.uc.training.smadmin.bd.vo.GrowthVO;
import com.uc.training.smadmin.bd.vo.MemberGrowthVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

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
    @Autowired
    private GrowthDetailDao growthDetailDao;

    @Autowired
    private MemberDao memberDao;

    public Long saveGrowthDetail(GrowthVO growthVO){
        GrowthDetail growthDetail = new GrowthDetail();
        Long growthVaule = 0L;

        growthDetail.setMemberId(growthVO.getMemberId());
        growthDetail.setType(growthVO.getGrowthType());
        // 获取成长值
        growthVaule= getGrowthVauleByType(growthVO);
        growthDetail.setGrowth(growthVaule);

        // 保存积分详情
        growthDetailDao.saveGrowthDetail(growthDetail);

        MemberGrowthVO memberGrowthVO = new MemberGrowthVO();
        memberGrowthVO.setGrowth(growthVaule);
        memberGrowthVO.setMemberId(growthVO.getMemberId());

        // 更新会员成长值
        memberDao.updateGrowthById(memberGrowthVO);
        return growthVaule;
    }

    /**
     * 根据成长类型计算成长值
     * @param growthVO
     * @return
     */
    private Long getGrowthVauleByType(GrowthVO growthVO){
        Integer growthType=growthVO.getGrowthType();
        Long growthVaule = 0L;

        for(GrowthEnum growthEnum: GrowthEnum.values()){
            if(growthType.equals(GrowthEnum.PURCHASE.getGrowthType())){
                Long purchaseValue = GrowthEnum.PURCHASE.getValue();
                BigDecimal purchaseGrowth = growthVO.getPurchaseValue();
                growthVaule = BigDecimal.valueOf(purchaseValue)
                        .multiply(purchaseGrowth)
                        .divide(BigDecimal.valueOf(100)).longValue();
            }else {
                if(growthType.equals(growthEnum.getGrowthType())){
                    growthVaule = growthEnum.getValue();
                }
            }
        }
        return growthVaule;
    }
}
