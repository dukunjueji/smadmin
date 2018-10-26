package com.uc.training.common.enums;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/26
 * 说明：成长值枚举
 */
public enum GrowthEnum {
    LOGININ("登陆", 1, 5L),
    SINGIN("签到", 2, 5L),
    PURCHASE("购买商品", 3, 5L),
    COMMENT("评价", 4, 10L),
    SHARE("分享", 5, 10L);

    GrowthEnum(String describe, Integer growthType, Long value) {
        this.describe = describe;
        this.growthType = growthType;
        this.value = value;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Integer getGrowthType() {
        return growthType;
    }

    public void setGrowthType(Integer growthType) {
        this.growthType = growthType;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    private String describe;

    private Integer growthType;

    private Long value;
}
