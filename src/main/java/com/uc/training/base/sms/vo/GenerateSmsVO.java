package com.uc.training.base.sms.vo;

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
     * 邮箱标题
     */
    private String emailTitle;
    /**
     * 邮箱
     */
    private String emil;

    /**
     * 信息
     */
    private String message;

    /**
     * 模板编号
     */
    private String code;
    /**
     * 消息类型
     */
    private Integer type;

    /**
     * 充值状态
     */
    private Integer rechargeStatus;

    @Override
    public String toString() {
        return "GenerateSmsVO{" +
                "telephone='" + telephone + '\'' +
                ", emailTitle='" + emailTitle + '\'' +
                ", emil='" + emil + '\'' +
                ", message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", type=" + type +
                ", rechargeStatus=" + rechargeStatus +
                '}';
    }

    public String getEmailTitle() {
        return emailTitle;
    }

    public void setEmailTitle(String emailTitle) {
        this.emailTitle = emailTitle;
    }

    public String getEmil() {
        return emil;
    }

    public void setEmil(String emil) {
        this.emil = emil;
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

    public Integer getType() {
        return type;
    }

    public Integer getRechargeStatus() {
        return rechargeStatus;
    }

    public void setRechargeStatus(Integer rechargeStatus) {
        this.rechargeStatus = rechargeStatus;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
