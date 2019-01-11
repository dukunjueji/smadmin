package com.ucar.smadmin.base.bd.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/26
 * 说明：成长值参数
 */
public class GrowthVO implements Serializable {
    private static final long serialVersionUID = -4457901071488014842L;

    /**
     * 成长值
     */
    private Long growth;
    /**
     * 购买商品的价格，如果成长值类型不是购买商品类型，那么这个字段不用赋值
     */
    private BigDecimal purchaseValue;
    /**
     * 成长值类型
     */
    private Integer type;

    /**
     * 会员ID
     */
    private Long memberId;

    @Override
    public String toString() {
        return "GrowthVO{" +
                "growth=" + growth +
                ", purchaseValue=" + purchaseValue +
                ", type=" + type +
                ", memberId=" + memberId +
                '}';
    }

    public Long getGrowth() {
        return growth;
    }

    public void setGrowth(Long growth) {
        this.growth = growth;
    }

    public BigDecimal getPurchaseValue() {
        return purchaseValue;
    }

    public void setPurchaseValue(BigDecimal purchaseValue) {
        this.purchaseValue = purchaseValue;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
