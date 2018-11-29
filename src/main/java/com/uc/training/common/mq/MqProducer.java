package com.uc.training.common.mq;

import com.uc.training.common.mq.vo.MqVO;
import com.ycc.tools.middleware.metaq.MQHelperAdapter;
import com.ycc.tools.middleware.metaq.MetaQTopic;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/29
 */
public class MqProducer extends MQHelperAdapter {

    private MqVO mqVO;

    public MqProducer(MqVO mqVO) {
        this.mqVO = mqVO;
    }

    public MqVO getMqVO() {
        return mqVO;
    }

    public void setMqVO(MqVO mqVO) {
        this.mqVO = mqVO;
    }

    @Override
    public MetaQTopic getTopic() {
        return new MetaQTopic("mmc_train_sm_msg", "meta");
    }

    @Override
    public Object getData() {
        return this.mqVO;
    }
}
