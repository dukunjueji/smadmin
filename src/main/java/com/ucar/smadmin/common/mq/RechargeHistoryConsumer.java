package com.ucar.smadmin.common.mq;

import com.rabbitmq.client.Channel;
import com.ucar.smadmin.common.mq.vo.MqVO;
import com.ucar.smadmin.base.bd.service.MemberRechargeHistoryService;
import com.ucar.smadmin.base.bd.vo.MemberRechargeHistoryModelVO;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/15
 */
@Component
@RabbitListener(queues = RabbitConfig.RECHARGE_QUEUE)
public class RechargeHistoryConsumer {

    @Autowired
    private MemberRechargeHistoryService memberRechargeHistoryService;

    @RabbitHandler
    public void handlerMessage(MqVO mqVO, Channel channel, Message message) throws IOException {
        //this.memberRechargeHistoryService = InjectionUtils.getInjectionInstance(MemberRechargeHistoryService.class);
        MemberRechargeHistoryModelVO memberRechargeHistory = mqVO.getMemberRechargeHistory();
        //判断消息实体是否为空
        if (memberRechargeHistory != null) {
            if (memberRechargeHistoryService.insertMemberRechargeHistory(memberRechargeHistory) > 0) {
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
