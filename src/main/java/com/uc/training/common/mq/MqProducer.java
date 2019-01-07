package com.uc.training.common.mq;

import com.uc.training.common.mq.vo.MqVO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/29
 */
@Component
public class MqProducer {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public MqProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        //rabbitTemplate如果为单例的话，那回调就是最后设置的内容
    }

    public void sendGrowth(MqVO mqVO) {
        this.rabbitTemplate.convertAndSend(RabbitConfig.MMC_TRAIN_SM_MSG, RabbitConfig.ROUTINGKEY_GROWTH, mqVO);
    }

    public void sendIntegral(MqVO mqVO) {
        this.rabbitTemplate.convertAndSend(RabbitConfig.MMC_TRAIN_SM_MSG, RabbitConfig.ROUTINGKEY_INTEGRAL, mqVO);
    }

    public void sendMessage(MqVO mqVO) {
        this.rabbitTemplate.convertAndSend(RabbitConfig.MMC_TRAIN_SM_MSG, RabbitConfig.ROUTINGKEY_MESSAGE, mqVO);
    }

    public void sendRecharge(MqVO mqVO) {
        this.rabbitTemplate.convertAndSend(RabbitConfig.MMC_TRAIN_SM_MSG, RabbitConfig.ROUTINGKEY_RECHARGE, mqVO);
    }

    public void sendSms(MqVO mqVO) {
        this.rabbitTemplate.convertAndSend(RabbitConfig.MMC_TRAIN_SM_MSG, RabbitConfig.ROUTINGKEY_SMS, mqVO);
    }
}
