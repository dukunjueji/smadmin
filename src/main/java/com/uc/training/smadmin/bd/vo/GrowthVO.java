package com.uc.training.smadmin.bd.vo;

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
     * 成长值类型
     */
    private Integer growthType;
    /**
     * 购买商品的价格，如果成长值类型不是购买商品类型，那么这个字段不用赋值
     */
    private BigDecimal purchaseValue;

    /**
     * 会员ID
     */
    private Long memberId;

    public Integer getGrowthType() {
        return growthType;
    }

    public void setGrowthType(Integer growthType) {
        this.growthType = growthType;
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
}
