package com.uc.training.smadmin.sms.vo;

import java.io.Serializable;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/2
 */
public class GenerateSysVO implements Serializable {

    private static final long serialVersionUID = 8413116707963300713L;
    /**
     * 手机号
     */
    private String telephone;
    /**
     * 类型（短信类型：1注册，2，登录，3充值，4订单信息，5会员等级），枚举SmsTypeEnum
     */
    private Integer type;
    /**
     * 信息
     */
    private String message;

    @Override
    public String toString() {
        return "GenerateSysVO{" +
                "telephone='" + telephone + '\'' +
                ", type=" + type +
                ", message='" + message + '\'' +
                '}';
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
