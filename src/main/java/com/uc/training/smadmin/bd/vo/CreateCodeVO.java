package com.uc.training.smadmin.bd.vo;

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
    @Pattern(regexp = "^((13[0-9])|(15[^4,\\D])|(17[0,5-9])|(18[0,5-9])|(19[0,5-9]))\\d{8}$", message = "手机号格式不正确")
    private String telephone;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "CreateCodeVO{" +
                "telephone='" + telephone + '\'' +
                '}';
    }
}
