package com.uc.training.base.bd.service.impl;

import com.uc.training.base.bd.service.IntegralDetailService;
import com.uc.training.base.bd.vo.IntegralVO;
import com.uc.training.base.bd.vo.MemberVO;
import com.uc.training.common.enums.IntegralEnum;
import com.uc.training.remote.client.BaseClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/26
 * 说明：积分详情逻辑实现
 */
@Service
public class IntegralDetailServiceImpl implements IntegralDetailService {

    private static final Integer  NUM = 100;
    @Override
    public Long saveIntegralDetail(IntegralVO integralVO){
        IntegralVO integralDetail = new IntegralVO();
        // 积分值
        Long integralValue = 0L;

        if (integralVO.getType().equals(IntegralEnum.SINGIN.getIntegralType())){
            integralValue = IntegralEnum.SINGIN.getValue();
        }else if (integralVO.getType().equals(IntegralEnum.ASSESS.getIntegralType())){
            integralValue = IntegralEnum.ASSESS.getValue();
        }else if (integralVO.getType().equals(IntegralEnum.PURCHASE.getIntegralType())){
            BigDecimal bigDecimal = new BigDecimal(IntegralEnum.PURCHASE.getValue());
            BigDecimal purchaseValue = bigDecimal.multiply(integralVO.getPurchaseValue()).divide(BigDecimal.valueOf(NUM));
            integralValue = purchaseValue.longValue();
        }else if (integralVO.getType().equals(IntegralEnum.INTEGRALCONSUME.getIntegralType())){
            integralValue = integralVO.getIntegral();
        }

        //封装积分详情
        integralDetail.setIntegral(integralValue);
        integralDetail.setMemberId(integralVO.getMemberId());
        integralDetail.setType(integralVO.getType());

        //插入会员详情
        BaseClient.addIntegralDetail(integralDetail);

        //封装修改会员积分的参数
        MemberVO memberVO = new MemberVO();
        memberVO.setIntegral(integralValue);
        memberVO.setId(integralVO.getMemberId());

        //更新会员积分
        BaseClient.updateMember(memberVO);
        return integralValue;
    }
}
