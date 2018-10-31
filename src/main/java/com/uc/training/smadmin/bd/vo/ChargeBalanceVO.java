package com.uc.training.smadmin.bd.vo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/22
 * 说明：充值余额
 */
public class ChargeBalanceVO implements Serializable {

    private static final long serialVersionUID = -8568205543512463741L;

    /**
     * 充值余额
     */
    @NotNull(message = "充值金额不能为空")
    private BigDecimal balance;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "ChargeBalanceVO{" +
                "balance=" + balance +
                '}';
    }
}
