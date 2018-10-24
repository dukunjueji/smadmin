package com.uc.training.smadmin.sys.re;

import java.io.Serializable;

/**
 * 用户登录返回结果
 * @author 吴佰川（baichuan.wu@ucarinc.com）
 * @version 1.0 2018/10/18 15:15 by 吴佰川（baichuan.wu@ucarinc.com）创建
 * @copyright All Rights Reserved.
 */
public class UserLoginRE implements Serializable {
    private static final long serialVersionUID = -2167388848846793016L;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
