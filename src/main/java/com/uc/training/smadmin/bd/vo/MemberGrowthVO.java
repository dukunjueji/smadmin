package com.uc.training.smadmin.bd.vo;

import java.io.Serializable;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/26
 * 说明：会员成长值参数
 */
public class MemberGrowthVO implements Serializable {
    private static final long serialVersionUID = -5967808906011851140L;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 成长值
     */
    private Long growth;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getGrowth() {
        return growth;
    }

    public void setGrowth(Long growth) {
        this.growth = growth;
    }

    @Override
    public String toString() {
        return "MemberGrowthVO{" +
                "memberId=" + memberId +
                ", growth=" + growth +
                '}';
    }
}
