package com.uc.training.smadmin.bd.vo;

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
