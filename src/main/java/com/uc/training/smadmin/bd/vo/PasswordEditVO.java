package com.uc.training.smadmin.bd.vo;

import javax.validation.constraints.NotNull;
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
    @NotNull(message = "原来的密码不能为空")
    private String oldpasswor;

    /**
     * 新密码
     */
    @NotNull(message = "新密码不能为空")
    private String newpassword;

    /**
     *确认密码
     */
    @NotNull(message = "确认密码不能为空")
    private String confirmpassword;

    /**
     * 验证码
     */
    @NotNull(message = "验证码不能为空")
    private String code;

    public String getOldpasswor() {
        return oldpasswor;
    }

    public void setOldpasswor(String oldpasswor) {
        this.oldpasswor = oldpasswor;
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
}
