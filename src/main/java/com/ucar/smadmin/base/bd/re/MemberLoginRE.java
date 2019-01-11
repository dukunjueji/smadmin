package com.ucar.smadmin.base.bd.re;

import java.io.Serializable;

/**
 * 会员登录返回结果
 *
 * @author 吴佰川（baichuan.wu@ucarinc.com）
 * @version 1.0 2018/10/18 15:15 by 吴佰川（baichuan.wu@ucarinc.com）创建
 * @copyright All Rights Reserved.
 */
public class MemberLoginRE implements Serializable {
    private static final long serialVersionUID = 1737365503817996617L;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
