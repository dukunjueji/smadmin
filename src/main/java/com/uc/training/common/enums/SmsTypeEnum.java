package com.uc.training.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/16
 * 说明：
 */
public enum SmsTypeEnum {
    //注册
    REGISTER("注册", 1),
    //登录
    LOGIN("登陆", 2),
    //充值
    RECHARGE("充值", 3),
    //订单信息
    ORDERINFO("订单信息", 4),
    //会员等级
    MEMBERGRADE("会员等级", 5);
    /**
     * 描述
     */
    private String describe;

    /**
     * 模板类型
     */
    private Integer type;

    SmsTypeEnum(String describe, Integer type) {
        this.describe = describe;
        this.type = type;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public static Map<Integer, String> getMap() {
        Map<Integer, String> map = new HashMap<>();
        for (SmsTypeEnum s : SmsTypeEnum.values()) {
            map.put(s.type, s.describe);
        }
        return map;
    }
}
