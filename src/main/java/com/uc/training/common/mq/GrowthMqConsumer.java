package com.uc.training.common.mq;

import com.rabbitmq.client.Channel;
import com.uc.training.base.bd.service.GrowthDetailService;
import com.uc.training.base.bd.vo.GrowthVO;
import com.uc.training.common.mq.vo.MqVO;
import com.uc.training.common.utils.InjectionUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：Dk
 * @version：v1.0
 * @date: 2019/1/7
 * 说明：成长值消费者
 */
@Component
@RabbitListener(queues = RabbitConfig.GROWTH_QUEUE)
public class GrowthMqConsumer {
    @Autowired
    private GrowthDetailService growthDetailService;

    @RabbitHandler
    public void handlerMessage(MqVO mqVO, Channel channel, Message message) throws IOException {
        this.growthDetailService = InjectionUtils.getInjectionInstance(GrowthDetailService.class);
        //判断消费类型是否为成长值
        if (mqVO.getGrowthType() != null) {
            GrowthVO growthVO = new GrowthVO();
            growthVO.setMemberId(mqVO.getMemberId());
            growthVO.setType(mqVO.getGrowthType());
            growthVO.setPurchaseValue(mqVO.getPurchaseValue());
            if (growthDetailService.saveGrowthDetail(growthVO) > 0) {
                /**
                 * 手动发送确认消息
                 */
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } else {
                /**
                 * 失败重新投递消息
                 */
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        }
    }
}
