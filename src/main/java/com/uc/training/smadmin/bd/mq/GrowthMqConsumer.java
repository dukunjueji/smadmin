package com.uc.training.smadmin.bd.mq;

import com.alibaba.fastjson.JSON;
import com.uc.training.common.enums.ConsumerTypeEnum;
import com.uc.training.smadmin.bd.service.GrowthDetailService;
import com.uc.training.smadmin.bd.service.impl.GrowthDetailServiceImpl;
import com.uc.training.smadmin.bd.vo.GrowthVO;
import com.uc.training.smadmin.bd.vo.LoginMqVO;
import com.uc.training.smadmin.bd.vo.MqVO;
import com.uc.training.smadmin.utils.InjectionUtils;
import com.zuche.framework.common.SpringApplicationContext;
import com.zuche.framework.metaq.handler.DefaultExecutorMessageListener;
import com.zuche.framework.metaq.vo.MessageVO;
import com.zuche.framework.remote.nio.codec.HessianSerializerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.Map;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/31
 * 说明：成长值消费者
 */
@Controller
public class GrowthMqConsumer extends DefaultExecutorMessageListener {
    @Autowired
    private GrowthDetailService growthDetailService;

    @Override
    public void handlerMessage(MessageVO message) {
        this.growthDetailService = InjectionUtils.getInjectionInstance(GrowthDetailService.class);
        MqVO mqVO = JSON.parseObject(message.getData(), MqVO.class);
        //判断消费类型是否为成长值
        if (mqVO.getConsumerType().equals(ConsumerTypeEnum.GROWTHTYPE.getConsumerType())){
            GrowthVO growthVO = new GrowthVO();
            growthVO.setMemberId(mqVO.getMemberId());
            growthVO.setGrowthType(mqVO.getGrowthType());
            growthDetailService.saveGrowthDetail(growthVO);
        }
    }
}
