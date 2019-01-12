package com.ucar.smadmin.common.mq;

import com.rabbitmq.client.Channel;
import com.ucar.smadmin.base.bd.vo.MessageVO;
import com.ucar.smadmin.common.mq.vo.MqVO;
import com.ucar.smadmin.enums.GrowthEnum;
import com.ucar.smadmin.base.bd.service.MessageService;
import com.ucar.smadmin.enums.IntegralEnum;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/11/3
 * 说明：消息消费者
 */
@Component
@RabbitListener(queues = RabbitConfig.MESSAGE_QUEUE)
public class MessageMqConsumer {
    @Autowired
    private MessageService messageService;
    private static final Integer NUM = 100;
    private static final Integer NUMBER = 0;

    @RabbitHandler
    public void handlerMessage(MqVO mqVO, Channel channel, Message message) throws IOException {
        //this.messageService = InjectionUtils.getInjectionInstance(MessageService.class);
        //判断消费类型是否为支付消息
        if (mqVO.getPurchaseValue() != null) {
            BigDecimal purchaseValue = mqVO.getPurchaseValue();
            Long growthValue = purchaseValue.multiply(BigDecimal
                    .valueOf(GrowthEnum.PURCHASE.getValue()))
                    .divide(BigDecimal.valueOf(NUM)).longValue();
            Long integralValue = purchaseValue.multiply(BigDecimal
                    .valueOf(IntegralEnum.PURCHASE.getValue()))
                    .divide(BigDecimal.valueOf(NUM)).longValue();
            String content = "您共支付了" + purchaseValue + "元,成长值增加了" +
                    growthValue + ",积分增加了" + integralValue;
            MessageVO mes = new MessageVO();
            mes.setMemberId(mqVO.getMemberId());
            mes.setContent(content);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            messageService.insertMessage(mes);
            /*if (messageService.insertMessage(mes) > 0) {
                *//**
                 * 手动发送确认消息
                 *//*
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } else {
                *//**
                 * 失败重新投递消息
                 *//*
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }*/
        }
        //判断消费类型是否为充值消息
        if (mqVO.getRechargeValue() != null) {
            String content;
            if (mqVO.getRechargeStatus() > NUMBER) {
                BigDecimal balance = mqVO.getRechargeValue();
                content = balance + "元充值成功";
            } else {
                content = "充值失败";
            }
            MessageVO mes = new MessageVO();
            mes.setMemberId(mqVO.getMemberId());
            mes.setContent(content);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            messageService.insertMessage(mes);
            /*if (messageService.insertMessage(mes) > 0) {
                *//**
                 * 手动发送确认消息
                 *//*
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } else {
                *//**
                 * 失败重新投递消息
                 *//*
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }*/
        }
    }
}
