package com.uc.training.base.bd.vo;

import java.io.Serializable;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/31
 * 说明：
 */
public class LoginVO implements Serializable {
    private static final long serialVersionUID = -8801416573765709572L;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 成长值类型
     */
    private Integer growthType;

    /**
     * 地址
     */
    private String ip;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Integer getGrowthType() {
        return growthType;
    }

    public void setGrowthType(Integer growthType) {
        this.growthType = growthType;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "LoginVO{" +
                "memberId=" + memberId +
                ", growthType=" + growthType +
                ", ip='" + ip + '\'' +
                '}';
    }
}
