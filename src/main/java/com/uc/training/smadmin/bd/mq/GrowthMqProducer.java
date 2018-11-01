package com.uc.training.smadmin.bd.mq;

import com.uc.training.smadmin.bd.vo.LoginMqVO;
import com.ycc.tools.middleware.metaq.MQHelperAdapter;
import com.ycc.tools.middleware.metaq.MetaQTopic;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/31
 * 说明：
 */
public class GrowthMqProducer extends MQHelperAdapter {

    private LoginMqVO loginMqVO;

    public GrowthMqProducer(LoginMqVO loginMqVO) {
        this.loginMqVO = loginMqVO;
    }

    @Override
    public MetaQTopic getTopic() {
        return new MetaQTopic("mmc_train_test", "meta");
    }

    @Override
    public Object getData() {
        return this.loginMqVO;
    }

    @Override
    public MQHelperEncodeType getEncodeType() {
        return MQHelperEncodeType.HESSIAN;
    }
}
