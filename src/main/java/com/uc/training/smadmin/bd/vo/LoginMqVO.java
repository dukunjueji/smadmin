package com.uc.training.smadmin.bd.vo;

import java.io.Serializable;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/31
 * 说明：
 */
public class LoginMqVO implements Serializable {
    private static final long serialVersionUID = -8801416573765709572L;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 成长值类型
     */
    private Integer growthType;

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

    @Override
    public String toString() {
        return "LoginMqVO{" +
                "memberId=" + memberId +
                ", growthType=" + growthType +
                '}';
    }
}
