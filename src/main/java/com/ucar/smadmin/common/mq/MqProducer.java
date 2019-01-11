package com.ucar.smadmin.common.mq;

import com.ucar.smadmin.common.mq.vo.MqVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
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
    private final Logger logger = LoggerFactory.getLogger(MqProducer.class);
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public MqProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setMandatory(true);
        /**
         * 消息应答机制
         */
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if (!ack) {
                    logger.info("send message failed: " + cause);
                    throw new RuntimeException("send error " + cause);
                }
            }
        });
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
