package com.ucar.smadmin.base.bd.vo;

import com.ucar.smapi.common.base.model.BaseModel;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/15
 */
public class MemberRechargeHistoryModelVO extends BaseModel implements Serializable {

    private static final long serialVersionUID = 3054235948030307661L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 会员id
     */
    private Long memberId;
    /**
     * 充值金额
     */
    private BigDecimal balance;
    /**
     * 充值状态 1成功 0失败
     */
    private Integer status;

    @Override
    public String toString() {
        return "MemberRechargeHistory{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", balance=" + balance +
                ", status=" + status +
                '}';
    }

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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
