package com.uc.training.smadmin.bd.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/30
 * 说明：支付商品扣余额参数
 */
public class MemberBalanceVO implements Serializable {
    private static final long serialVersionUID = -580231173327959134L;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 总支付金额
     */
    private BigDecimal totalMoney;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    @Override
    public String toString() {
        return "MemberBalanceVO{" +
                "memberId=" + memberId +
                ", totalMoney=" + totalMoney +
                '}';
    }
}
