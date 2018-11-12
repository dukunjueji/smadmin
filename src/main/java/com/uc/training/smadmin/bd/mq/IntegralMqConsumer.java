package com.uc.training.smadmin.bd.mq;

import com.alibaba.fastjson.JSON;
import com.uc.training.smadmin.bd.service.IntegralDetailService;
import com.uc.training.smadmin.bd.vo.IntegralVO;
import com.uc.training.smadmin.bd.vo.MqVO;
import com.uc.training.smadmin.utils.InjectionUtils;
import com.zuche.framework.metaq.handler.DefaultExecutorMessageListener;
import com.zuche.framework.metaq.vo.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/11/3
 * 说明：积分消费者
 */
@Controller
public class IntegralMqConsumer extends DefaultExecutorMessageListener {
    @Autowired
    private IntegralDetailService integralDetailService;

    @Override
    public void handlerMessage(MessageVO message) {
        this.integralDetailService = InjectionUtils.getInjectionInstance(IntegralDetailService.class);
        MqVO mqVO = JSON.parseObject(message.getData(), MqVO.class);
        if (mqVO.getIntegralType() != null){
            IntegralVO integralVO = new IntegralVO();
            integralVO.setMemberId(mqVO.getMemberId());
            integralVO.setIntegralType(mqVO.getIntegralType());
            integralVO.setPurchaseValue(mqVO.getPurchaseValue());
            this.integralDetailService.saveIntegralDetail(integralVO);
        }
    }
}
