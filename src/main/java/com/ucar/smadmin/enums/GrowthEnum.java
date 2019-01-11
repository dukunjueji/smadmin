package com.ucar.smadmin.enums;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/26
 * 说明：成长值枚举
 */
public enum GrowthEnum {
    //登陆
    LOGININ("登陆", 1, 5L),
    //签到
    SINGIN("签到", 2, 5L),
    //购买商品
    PURCHASE("购买商品", 3, 5L),
    //评价
    COMMENT("评价", 4, 10L),
    //分享
    SHARE("分享", 5, 10L);

    GrowthEnum(String describe, Integer growthType, Long value) {
        this.describe = describe;
        this.growthType = growthType;
        this.value = value;
    }

    public String getDescribe() {
        return describe;
    }

    public Integer getGrowthType() {
        return growthType;
    }

    public Long getValue() {
        return value;
    }

    private String describe;

    private Integer growthType;

    private Long value;
}
