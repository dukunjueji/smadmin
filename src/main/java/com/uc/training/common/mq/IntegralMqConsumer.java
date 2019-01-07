package com.uc.training.common.mq;

import com.uc.training.base.bd.service.IntegralDetailService;
import com.uc.training.base.bd.vo.IntegralVO;
import com.uc.training.common.mq.vo.MqVO;
import com.uc.training.common.utils.InjectionUtils;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/11/3
 * 说明：积分消费者
 */
@Component
@RabbitListener(queues = RabbitConfig.INTEGRAL_QUEUE)
public class IntegralMqConsumer {
    @Autowired
    private IntegralDetailService integralDetailService;

    @RabbitHandler
    public void handlerMessage(MqVO mqVO) {
        this.integralDetailService = InjectionUtils.getInjectionInstance(IntegralDetailService.class);
        if (mqVO.getIntegralType() != null){
            IntegralVO integralVO = new IntegralVO();
            integralVO.setMemberId(mqVO.getMemberId());
            integralVO.setType(mqVO.getIntegralType());
            integralVO.setPurchaseValue(mqVO.getPurchaseValue());
            this.integralDetailService.saveIntegralDetail(integralVO);
        }
    }
}
