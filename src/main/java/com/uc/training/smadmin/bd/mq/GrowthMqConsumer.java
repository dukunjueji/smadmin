package com.uc.training.smadmin.bd.mq;

import com.uc.training.smadmin.bd.service.GrowthDetailService;
import com.uc.training.smadmin.bd.service.impl.GrowthDetailServiceImpl;
import com.uc.training.smadmin.bd.vo.GrowthVO;
import com.uc.training.smadmin.bd.vo.LoginMqVO;
import com.zuche.framework.metaq.handler.DefaultExecutorMessageListener;
import com.zuche.framework.metaq.vo.MessageVO;
import com.zuche.framework.remote.nio.codec.HessianSerializerUtils;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/31
 * 说明：
 */
public class GrowthMqConsumer extends DefaultExecutorMessageListener {
    @Override
    public void handlerMessage(MessageVO message) {
        LoginMqVO loginMqVO = (LoginMqVO)HessianSerializerUtils.deserialize(message.getData());
        GrowthDetailService growthDetailService = new GrowthDetailServiceImpl();
        GrowthVO growthVO = new GrowthVO();
        growthVO.setMemberId(loginMqVO.getMemberId());
        growthVO.setGrowthType(loginMqVO.getGrowthType());
        growthDetailService.saveGrowthDetail(growthVO);
    }
}
