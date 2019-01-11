package com.smgoods.enums;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/26
 * 说明：积分枚举类
 */
public enum IntegralEnum {
    //签到
    SINGIN("签到", 1, 1L),
    //评价
    ASSESS("评价", 2, 2L),
    //购买商品
    PURCHASE("购买商品", 3, 3L),
    //使用积分消费
    INTEGRALCONSUME("使用积分消费", 4, null);

    IntegralEnum(String describe, Integer integralType, Long value) {
        this.describe = describe;
        this.integralType = integralType;
        this.value = value;
    }

    /**
     * 描述
     */
    private String describe;

    /**
     * 积分类型
     */
    private Integer integralType;

    /**
     * 积分值
     */
    private Long value;

    public String getDescribe() {
        return describe;
    }

    public Integer getIntegralType() {
        return integralType;
    }


    public Long getValue() {
        return value;
    }

}
