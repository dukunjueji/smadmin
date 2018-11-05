package com.uc.training.smadmin.bd.vo;

import com.kenai.jaffl.annotations.SaveError;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/23
 * 说明：
 */
public class MemberRegisterVO implements Serializable {
    private static final long serialVersionUID = -1962544340179487309L;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    private String telephone;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
    private String telCode;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelCode() {
        return telCode;
    }

    public void setTelCode(String telCode) {
        this.telCode = telCode;
    }

    @Override
    public String toString() {
        return "MemberRegisterVO{" +
                "telephone='" + telephone + '\'' +
                ", password='" + password + '\'' +
                ", telCode='" + telCode + '\'' +
                '}';
    }
}
