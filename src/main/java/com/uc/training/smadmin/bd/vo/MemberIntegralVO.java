package com.uc.training.smadmin.bd.vo;

import java.io.Serializable;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/26
 * 说明：修改会员积分的参数
 */
public class MemberIntegralVO implements Serializable {
    private static final long serialVersionUID = -7628224671427494015L;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 积分
     */
    private Long integral;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getIntegral() {
        return integral;
    }

    public void setIntegral(Long integral) {
        this.integral = integral;
    }

    @Override
    public String toString() {
        return "MemberIntegralVO{" +
                "memberId=" + memberId +
                ", integral=" + integral +
                '}';
    }
}
