package com.uc.training.smadmin.mq;

import com.alibaba.fastjson.JSON;
import com.uc.training.smadmin.mq.vo.MqVO;
import com.uc.training.smadmin.sms.service.SmsTemplateService;
import com.uc.training.smadmin.sms.vo.GenerateSmsVO;
import com.uc.training.smadmin.utils.InjectionUtils;
import com.zuche.framework.metaq.handler.DefaultExecutorMessageListener;
import com.zuche.framework.metaq.vo.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/11/3
 * 说明：消息消费者
 */
@Controller
public class MsmMqConsumer extends DefaultExecutorMessageListener {
    @Autowired
    private SmsTemplateService smsTemplateService;

    @Override
    public void handlerMessage(MessageVO message) {
        this.smsTemplateService = InjectionUtils.getInjectionInstance(SmsTemplateService.class);
        MqVO mqVO = JSON.parseObject(message.getData(), MqVO.class);
        GenerateSmsVO generateSmsVO = mqVO.getGenerateSmsVO();
        //判断消息实体是否为空
        if (generateSmsVO != null){
            smsTemplateService.generateSms(generateSmsVO);
        }
    }
}
