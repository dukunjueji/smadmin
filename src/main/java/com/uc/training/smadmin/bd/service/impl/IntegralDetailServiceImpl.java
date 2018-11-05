package com.uc.training.smadmin.bd.service.impl;

import com.uc.training.common.enums.IntegralEnum;
import com.uc.training.smadmin.bd.dao.IntegralDetailDao;
import com.uc.training.smadmin.bd.dao.MemberDao;
import com.uc.training.smadmin.bd.model.IntegralDetaill;
import com.uc.training.smadmin.bd.service.IntegralDetailService;
import com.uc.training.smadmin.bd.vo.IntegralVO;
import com.uc.training.smadmin.bd.vo.MemberIntegralVO;
import com.uc.training.smadmin.utils.InjectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private IntegralDetailDao integralDetailDao;

    @Autowired
    private MemberDao memberDao;

    @Override
    public Long saveIntegralDetail(IntegralVO integralVO){
        this.integralDetailDao = InjectionUtils.getInjectionInstance(IntegralDetailDao.class);
        this.memberDao = InjectionUtils.getInjectionInstance(MemberDao.class);
        IntegralDetaill integralDetaill = new IntegralDetaill();
        // 积分值
        Long integralValue = 0L;

        if (integralVO.getIntegralType().equals(IntegralEnum.SINGIN.getIntegralType())){
            integralValue = IntegralEnum.SINGIN.getValue();
        }else if (integralVO.getIntegralType().equals(IntegralEnum.ASSESS.getIntegralType())){
            integralValue = IntegralEnum.ASSESS.getValue();
        }else if (integralVO.getIntegralType().equals(IntegralEnum.PURCHASE.getIntegralType())){
            BigDecimal bigDecimal = new BigDecimal(IntegralEnum.PURCHASE.getValue());
            BigDecimal purchaseValue = bigDecimal.multiply(integralVO.getPurchaseValue()).divide(BigDecimal.valueOf(100));
            integralValue = purchaseValue.longValue();
        }else if (integralVO.getIntegralType().equals(IntegralEnum.INTEGRALCONSUME.getIntegralType())){
            integralValue = integralVO.getIntegral();
        }

        //封装积分详情
        IntegralDetaill integralDetail = new IntegralDetaill();
        integralDetail.setIntegral(integralValue);
        integralDetail.setMemberId(integralVO.getMemberId());
        integralDetail.setType(integralVO.getIntegralType());

        //插入会员详情
        integralDetailDao.addIntegralDetail(integralDetail);

        //封装修改会员积分的参数
        MemberIntegralVO memberIntegralVO = new MemberIntegralVO();
        memberIntegralVO.setIntegral(integralValue);
        memberIntegralVO.setMemberId(integralVO.getMemberId());

        //更新会员积分
        memberDao.updateIntegralById(memberIntegralVO);
        return integralValue;
    }
}
