package com.uc.training.base.bd.vo;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
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
public class CreateCodeVO implements Serializable {
    private static final long serialVersionUID = 5104922049937081790L;
    /**
     * 电话号码
     */
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])[0-9]{8}$", message = "手机号格式不正确")
    private String telephone;
    /**
     * 电子邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    @Length(max = 30, message = "邮箱长度过长")
    @Email(message = "请输入正确格式的邮箱")
    private String email;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "CreateCodeVO{" +
                "telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
