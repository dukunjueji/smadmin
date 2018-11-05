package com.uc.training.smadmin.bd.mq;

import com.uc.training.smadmin.bd.vo.LoginMqVO;
import com.uc.training.smadmin.bd.vo.MqVO;
import com.ycc.tools.middleware.metaq.MQHelperAdapter;
import com.ycc.tools.middleware.metaq.MetaQTopic;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/31
 * 说明：业务生成者
 */
public class MqProducer extends MQHelperAdapter {

    private MqVO mqVO;

    public MqProducer(MqVO mqVO) {
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

    @Override
    public MQHelperEncodeType getEncodeType() {
        return MQHelperEncodeType.JSON;
    }
}
