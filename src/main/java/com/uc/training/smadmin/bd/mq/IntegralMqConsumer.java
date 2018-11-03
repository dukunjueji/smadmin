package com.uc.training.smadmin.bd.mq;

import com.zuche.framework.metaq.handler.DefaultExecutorMessageListener;
import com.zuche.framework.metaq.vo.MessageVO;
import org.springframework.stereotype.Controller;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/11/3
 * 说明：积分消费者
 */
@Controller
public class IntegralMqConsumer extends DefaultExecutorMessageListener {
    @Override
    public void handlerMessage(MessageVO message) {

    }
}
