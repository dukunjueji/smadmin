package com.ucar.smadmin.base.bd.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/24
 * 说明：
 */
public class PasswordEditVO implements Serializable {
    private static final long serialVersionUID = -3376593977134129948L;

       /**
     * 旧密码
     */
    @NotBlank(message = "原来的密码不能为空")
    @Length(min = 6, max = 10, message = "密码长度须在6-10位之间")
    private String oldpassword;

    /**
     * 新密码
     */
    @NotBlank(message = "新密码不能为空")
    @Length(min = 6, max = 10, message = "密码长度须在6-10位之间")
    private String newpassword;

    /**
     *确认密码
     */
    @NotBlank(message = "确认密码不能为空")
    @Length(min = 6, max = 10, message = "密码长度须在6-10位之间")
    private String confirmpassword;

    /**
     * 验证码
     */
    private String code;

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "PasswordEditVO{" +
                "oldpassword='" + oldpassword + '\'' +
                ", newpassword='" + newpassword + '\'' +
                ", confirmpassword='" + confirmpassword + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
