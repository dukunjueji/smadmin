package com.uc.training.smadmin.bd.vo;

import com.uc.training.smadmin.sms.vo.SmsVO;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/11/2
 * 说明：消息body
 */
public class MqVO implements Serializable {
    private static final long serialVersionUID = -1738502341066759491L;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 消息
     */
    private SmsVO smsVO;

    /**
     * 成长值类型
     */
    private Integer growthType;

    /**
     * 积分类型
     */
    private Integer integralType;

    /**
     * 购买商品的金额
     */
    private BigDecimal purchaseValue;

    /**
     * 充值金额
     */
    private BigDecimal rechargeValue;

    public SmsVO getSmsVO() {
        return smsVO;
    }

    public void setSmsVO(SmsVO smsVO) {
        this.smsVO = smsVO;
    }

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

    public BigDecimal getPurchaseValue() {
        return purchaseValue;
    }

    public void setPurchaseValue(BigDecimal purchaseValue) {
        this.purchaseValue = purchaseValue;
    }

    public BigDecimal getRechargeValue() {
        return rechargeValue;
    }

    public void setRechargeValue(BigDecimal rechargeValue) {
        this.rechargeValue = rechargeValue;
    }

    public Integer getIntegralType() {
        return integralType;
    }

    public void setIntegralType(Integer integralType) {
        this.integralType = integralType;
    }

    @Override
    public String toString() {
        return "MqVO{" +
                "memberId=" + memberId +
                ", growthType=" + growthType +
                ", integralType=" + integralType +
                ", purchaseValue=" + purchaseValue +
                ", rechargeValue=" + rechargeValue +
                '}';
    }
}
