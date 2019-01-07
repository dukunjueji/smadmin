package com.uc.training.common.mq;

import com.uc.training.base.bd.service.MemberRechargeHistoryService;
import com.uc.training.base.bd.vo.MemberRechargeHistoryModelVO;
import com.uc.training.common.mq.vo.MqVO;
import com.uc.training.common.utils.InjectionUtils;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public void handlerMessage(MqVO mqVO) {
        this.memberRechargeHistoryService = InjectionUtils.getInjectionInstance(MemberRechargeHistoryService.class);
        MemberRechargeHistoryModelVO memberRechargeHistory = mqVO.getMemberRechargeHistory();
        //判断消息实体是否为空
        if (memberRechargeHistory != null) {
            memberRechargeHistoryService.insertMemberRechargeHistory(memberRechargeHistory);
        }
    }
}
