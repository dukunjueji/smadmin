package com.ucar.smadmin.enums;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/22
 */
public enum AddressTypeEnum {
    // 地址为默认地址
    ISDEFAULT("默认地址", 1);
    /**
     * 描述
     */
    private String describe;
    /**
     * 类型
     */
    private Integer type;

    AddressTypeEnum(String describe, Integer type) {
        this.describe = describe;
        this.type = type;
    }

    public String getDescribe() {
        return describe;
    }

    public Integer getType() {
        return type;
    }
}
