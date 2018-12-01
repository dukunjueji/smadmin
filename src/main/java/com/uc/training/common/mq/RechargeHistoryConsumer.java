package com.uc.training.common.mq;

import com.alibaba.fastjson.JSON;
import com.uc.training.base.bd.service.MemberRechargeHistoryService;
import com.uc.training.base.bd.vo.MemberRechargeHistoryModelVO;
import com.uc.training.common.mq.vo.MqVO;
import com.uc.training.common.utils.InjectionUtils;
import com.zuche.framework.metaq.handler.DefaultExecutorMessageListener;
import com.zuche.framework.metaq.vo.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/15
 */
@Controller
public class RechargeHistoryConsumer extends DefaultExecutorMessageListener {

    @Autowired
    private MemberRechargeHistoryService memberRechargeHistoryService;

    @Override
    public void handlerMessage(MessageVO messageVO) {
        this.memberRechargeHistoryService = InjectionUtils.getInjectionInstance(MemberRechargeHistoryService.class);
        MqVO mqVO = JSON.parseObject(messageVO.getData(), MqVO.class);
        MemberRechargeHistoryModelVO memberRechargeHistory = mqVO.getMemberRechargeHistory();
        //判断消息实体是否为空
        if (memberRechargeHistory != null){
            memberRechargeHistoryService.insertMemberRechargeHistory(memberRechargeHistory);
        }
    }
}
