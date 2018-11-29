package com.uc.training.base.bd.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/26
 * 说明：积分参数
 */
public class IntegralVO implements Serializable {
    private static final long serialVersionUID = -4591473224649071884L;
    /**
     *积分值类型
     */
    private Integer integralType;

    /**
     *购买商品的价格，如果积分值类型不是购买商品类型，那么这个字段不用赋值
     */
    private BigDecimal purchaseValue;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 积分值，如果积分值类型不是使用积分消费，那么这个字段不用赋值
     */
    private Long integral;

    public Integer getIntegralType() {
        return integralType;
    }

    public void setIntegralType(Integer integralType) {
        this.integralType = integralType;
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

    public Long getIntegral() {
        return integral;
    }

    public void setIntegral(Long integral) {
        this.integral = integral;
    }

    @Override
    public String toString() {
        return "IntegralVO{" +
                "integralType=" + integralType +
                ", purchaseValue=" + purchaseValue +
                ", memberId=" + memberId +
                ", integral=" + integral +
                '}';
    }
}
