package com.uc.training.smadmin.bd.model;

import com.uc.training.common.base.model.BaseModel;

import java.io.Serializable;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/11/1
 * 说明：
 */
public class LoginLog extends BaseModel implements Serializable {

    private static final long serialVersionUID = -3357853419291685511L;

    /**
     * 主鍵id
     */
    private Long id;

    /**
     * 会员Id
     */
    private Long memberId;

    /**
     * ip地址
     */
    private String ip;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
