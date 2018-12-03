package com.uc.training.common.mq;

import com.alibaba.fastjson.JSON;
import com.uc.training.base.bd.service.MessageService;
import com.uc.training.common.enums.GrowthEnum;
import com.uc.training.common.enums.IntegralEnum;
import com.uc.training.common.mq.vo.MqVO;
import com.uc.training.common.utils.InjectionUtils;
import com.zuche.framework.metaq.handler.DefaultExecutorMessageListener;
import com.zuche.framework.metaq.vo.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;

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
    private static final Integer NUM = 100;
    private static final Integer NUMBER = 0;

    @Override
    public void handlerMessage(MessageVO message) {
        this.messageService = InjectionUtils.getInjectionInstance(MessageService.class);
        MqVO mqVO = JSON.parseObject(message.getData(), MqVO.class);
        //判断消费类型是否为支付消息
        if (mqVO.getPurchaseValue() != null){
            BigDecimal purchaseValue = mqVO.getPurchaseValue();
            Long growthValue = purchaseValue.multiply(BigDecimal
                    .valueOf(GrowthEnum.PURCHASE.getValue()))
                    .divide(BigDecimal.valueOf(NUM)).longValue();
            Long integralValue = purchaseValue.multiply(BigDecimal
                    .valueOf(IntegralEnum.PURCHASE.getValue()))
                    .divide(BigDecimal.valueOf(NUM)).longValue();
            String content = "您共支付了" + purchaseValue + "元,成长值增加了"+
                    growthValue + ",积分增加了" + integralValue;
            com.uc.training.base.bd.vo.MessageVO mes = new com.uc.training.base.bd.vo.MessageVO();
            mes.setMemberId(mqVO.getMemberId());
            mes.setContent(content);
            messageService.insertMessage(mes);
        }
        //判断消费类型是否为充值消息
        if (mqVO.getRechargeValue() != null){
            String content;
            if (mqVO.getRechargeStatus() > NUMBER){
                BigDecimal balance = mqVO.getRechargeValue();
                content = balance + "元充值成功";
            }else {
                content = "充值失败";
            }
            com.uc.training.base.bd.vo.MessageVO mes = new com.uc.training.base.bd.vo.MessageVO();
            mes.setMemberId(mqVO.getMemberId());
            mes.setContent(content);
            messageService.insertMessage(mes);
        }
    }
}
