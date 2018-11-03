package com.uc.training.common.enums;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/11/3
 * 说明：消费者类型
 */
public enum ConsumerTypeEnum {
    //成长值类型
    GROWTHTYPE(1, "成长值类型"),
    //积分类型
    INTEGRALTYPE(2, "积分类型"),
    //支付消息类型
    PURCHASEMESSAGETYPE(3, "支付消息类型"),
    //充值消息类型
    RECHARGEMESSAGETYPE(4, "充值消息类型");
    ConsumerTypeEnum(Integer consumerType, String describe) {
        this.consumerType = consumerType;
        this.describe = describe;
    }
    /**
     * 消费类型
     */
    private Integer consumerType;

    /**
     * 描述
     */
    private String describe;

    public Integer getConsumerType() {
        return consumerType;
    }

    public void setConsumerType(Integer consumerType) {
        this.consumerType = consumerType;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
