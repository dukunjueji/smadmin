package com.uc.training.smadmin.bd.vo;

import java.io.Serializable;

/**
 * @author 吴佰川（baichuan.wu@ucarinc.com）
 * @version 1.0 2018/10/23 13:58 by 吴佰川（baichuan.wu@ucarinc.com）创建
 * @copyright All Rights Reserved.
 */
public class MemberLoginVO implements Serializable {
    private static final long serialVersionUID = 5208446196575065858L;
    /**
     * 会员电话
     */
    private String telephone;
    /**
     * 密码
     */
    private String password;

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
}
