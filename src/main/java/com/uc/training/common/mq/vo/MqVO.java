package com.uc.training.common.mq.vo;

import com.uc.training.base.bd.vo.MemberRechargeHistoryModelVO;
import com.uc.training.base.sms.vo.GenerateSmsVO;

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
     * 充值状态
     */
    private Integer rechargeStatus;

    /**
     * 生成短信
     */
    private GenerateSmsVO generateSmsVO;

    /**
     * 充值记录
     */
    private MemberRechargeHistoryModelVO memberRechargeHistory;

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

    public MemberRechargeHistoryModelVO getMemberRechargeHistory() {
        return memberRechargeHistory;
    }

    public void setMemberRechargeHistory(MemberRechargeHistoryModelVO memberRechargeHistory) {
        this.memberRechargeHistory = memberRechargeHistory;
    }
    public Integer getRechargeStatus() {
        return rechargeStatus;
    }

    public void setRechargeStatus(Integer rechargeStatus) {
        this.rechargeStatus = rechargeStatus;
    }

    public GenerateSmsVO getGenerateSmsVO() {
        return generateSmsVO;
    }

    public void setGenerateSmsVO(GenerateSmsVO generateSmsVO) {
        this.generateSmsVO = generateSmsVO;
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
                ", rechargeStatus=" + rechargeStatus +
                ", generateSmsVO=" + generateSmsVO +
                ", memberRechargeHistory=" + memberRechargeHistory +
                ", growthType=" + growthType +
                ", integralType=" + integralType +
                ", purchaseValue=" + purchaseValue +
                ", rechargeValue=" + rechargeValue +
                '}';
    }
}
