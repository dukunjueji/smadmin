package com.ucar.smadmin.common.mq;

import com.ucar.smadmin.common.mq.impl.MqServiceImplFallback;
import com.ucar.smapi.common.mq.MqVO;
import com.ucar.smapi.common.vo.RemoteResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2019/1/15
 */
@FeignClient(name = "provider-smbase", fallback = MqServiceImplFallback.class)
public interface MqService {
    /**
     * 发送短信
     * @param mqVO
     * @return
     */
    @PostMapping(value = "smbase/api/sendSms")
    RemoteResult sendSms(MqVO mqVO);

    /**
     * 发送消息
     * @param mqVO
     * @return
     */
    @PostMapping(value = "smbase/api/sendMessage")
    RemoteResult sendMessage(MqVO mqVO);

    /**
     * 发送成长值
     * @param mqVO
     * @return
     */
    @PostMapping(value = "smbase/api/sendGrowth")
    RemoteResult sendGrowth(MqVO mqVO);

    /**
     * 发送积分
     * @param mqVO
     * @return
     */
    @PostMapping(value = "smbase/api/sendIntegral")
    RemoteResult sendIntegral(MqVO mqVO);

    /**
     * 发送充值记录
     * @param mqVO
     * @return
     */
    @PostMapping(value = "smbase/api/sendRecharge")
    RemoteResult sendRecharge(MqVO mqVO);
}
