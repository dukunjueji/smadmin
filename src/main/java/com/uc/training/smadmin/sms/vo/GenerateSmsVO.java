package com.uc.training.smadmin.sms.vo;

import java.io.Serializable;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/2
 */
public class GenerateSmsVO implements Serializable {

    private static final long serialVersionUID = 8413116707963300713L;
    /**
     * 手机号
     */
    private String telephone;

    /**
     * 信息
     */
    private String message;

    /**
     * 模板编号
     */
    private String code;

    @Override
    public String toString() {
        return "GenerateSmsVO{" +
                "telephone='" + telephone + '\'' +
                ", message='" + message + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
