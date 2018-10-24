package com.uc.training.smadmin.sys.vo;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 用户登录请求vo
 * @author 吴佰川（baichuan.wu@ucarinc.com）
 * @version 1.0 2018/10/18 15:15 by 吴佰川（baichuan.wu@ucarinc.com）创建
 * @copyright All Rights Reserved.
 */
public class UserLoginVO implements Serializable {
    private static final long serialVersionUID = -2167388848846793016L;
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
