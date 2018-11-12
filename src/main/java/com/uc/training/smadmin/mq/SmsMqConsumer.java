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
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/12
 */
@Controller
public class SmsMqConsumer  extends DefaultExecutorMessageListener {

    @Autowired
    private SmsTemplateService smsTemplateService;

    @Override
    public void handlerMessage(MessageVO messageVO) {
        this.smsTemplateService = InjectionUtils.getInjectionInstance(SmsTemplateService.class);
        MqVO mqVO = JSON.parseObject(messageVO.getData(), MqVO.class);
        GenerateSmsVO generateSmsVO = mqVO.getGenerateSmsVO();
        //判断消息实体是否为空
        if (generateSmsVO != null){
            System.out.println(234);
            this.smsTemplateService.generateSms(generateSmsVO);
        }
    }
}
