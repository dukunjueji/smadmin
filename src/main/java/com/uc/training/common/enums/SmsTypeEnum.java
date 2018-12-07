package com.uc.training.common.enums;

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
    REGISTER("注册", 1, "6671"),
    //忘记密码
    FORGET_PASSWORD("忘记密码", 2, "6672"),
    //修改密码
    CHANGE_PASSWORD("修改密码", 3, "6673"),
    //充值
    RECHARGE("充值", 4, "6674"),
    //充值失败
    RECHARGE_FAIL("充值失败", 4, "6678"),
    //订单信息
    ORDER_INFO("订单信息", 5, "6675"),
    //会员等级
    MEMBER_GRADE("会员等级", 6, "6676");
    /**
     * 描述
     */
    private String describe;

    /**
     * 模板类型
     */
    private Integer type;

    /**
     * 模板编号
     */
    private String code;

    SmsTypeEnum(String describe, Integer type, String code) {
        this.describe = describe;
        this.type = type;
        this.code = code;
    }

    public String getDescribe() {
        return describe;
    }

    public Integer getType() {
        return type;
    }

    public String getCode() {
        return code;
    }
}
