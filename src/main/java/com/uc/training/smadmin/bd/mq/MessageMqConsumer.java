package com.uc.training.smadmin.bd.mq;

import com.alibaba.fastjson.JSON;
import com.uc.training.common.enums.ConsumerTypeEnum;
import com.uc.training.smadmin.bd.model.Message;
import com.uc.training.smadmin.bd.service.MessageService;
import com.uc.training.smadmin.bd.vo.MqVO;
import com.uc.training.smadmin.utils.InjectionUtils;
import com.zuche.framework.common.SpringApplicationContext;
import com.zuche.framework.metaq.handler.DefaultExecutorMessageListener;
import com.zuche.framework.metaq.vo.MessageVO;
import com.zuche.framework.remote.nio.codec.HessianSerializerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.Map;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/11/3
 * 说明：消息消费者
 */
@Controller
public class MessageMqConsumer extends DefaultExecutorMessageListener {
    @Autowired
    private MessageService messageService;

    @Override
    public void handlerMessage(MessageVO message) {
        this.messageService = InjectionUtils.getInjectionInstance(MessageService.class);
        MqVO mqVO = JSON.parseObject(message.getData(), MqVO.class);
        //判断消费类型是否为支付消息
        if (mqVO.getConsumerType().equals(ConsumerTypeEnum.PURCHASEMESSAGETYPE.getConsumerType())){

        }
        //判断消费类型是否为充值消息
        if (mqVO.getConsumerType().equals(ConsumerTypeEnum.RECHARGEMESSAGETYPE.getConsumerType())){
            BigDecimal balance = mqVO.getRechargeValue();
            String content = balance + "元充值成功";
            Message mes = new Message();
            mes.setMemberId(mqVO.getMemberId());
            mes.setContent(content);
            messageService.insertMessage(mes);
        }
    }
}
