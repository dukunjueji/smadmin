package com.ucar.smadmin.common.mq.impl;

import com.ucar.smadmin.common.mq.MqService;
import com.ucar.smapi.common.mq.MqVO;
import com.ucar.smapi.common.vo.RemoteResult;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2019/1/15
 */
public class MqServiceImplFallback implements MqService {
    /**
     * 发送短信
     *
     * @param mqVO
     */
    @Override
    public RemoteResult sendSms(MqVO mqVO) {
        return RemoteResult.getServiceError();
    }

    /**
     * 发送消息
     *
     * @param mqVO
     */
    @Override
    public RemoteResult sendMessage(MqVO mqVO) {
        return RemoteResult.getServiceError();
    }

    /**
     * 发送成长值
     *
     * @param mqVO
     */
    @Override
    public RemoteResult sendGrowth(MqVO mqVO) {
        return RemoteResult.getServiceError();
    }

    /**
     * 发送积分
     *
     * @param mqVO
     */
    @Override
    public RemoteResult sendIntegral(MqVO mqVO) {
        return RemoteResult.getServiceError();
    }

    /**
     * 发送充值记录
     *
     * @param mqVO
     * @return
     */
    @Override
    public RemoteResult sendRecharge(MqVO mqVO) {
        return RemoteResult.getServiceError();
    }
}
