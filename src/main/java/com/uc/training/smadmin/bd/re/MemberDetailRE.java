package com.uc.training.smadmin.bd.re;

import com.uc.training.smadmin.bd.model.Member;
import java.io.Serializable;
import java.util.Date;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/22
 * 说明：响应会员详情参数
 */
public class MemberDetailRE extends Member implements Serializable {
    private static final long serialVersionUID = -2658615585315875022L;

    /**主键id**/
    private Long memberGradeId;

    /**等级名称**/
    private String name;

    /**等级下限**/
    private Long minGrowth;

    /**等级上限**/
    private Long maxGrowth;

    /**折扣(以 x.xx 的形式使用，直接与原价相乘)**/
    private Float discount;

    /**
     * 订单数目
     */
    private Integer orderSum;

    private Integer messageSum;

    /**创建时间**/
    private Date memberGradeCreateTime;

    /**修改时间**/
    private Date memberGradeModifyTime;

    /**创建人**/
    private Long memberGradeCreateEmp;

    /**修改人**/
    private Long memberGradeModifyEmp;

    public Long getMemberGradeId() {
        return memberGradeId;
    }

    public void setMemberGradeId(Long memberGradeId) {
        this.memberGradeId = memberGradeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMinGrowth() {
        return minGrowth;
    }

    public void setMinGrowth(Long minGrowth) {
        this.minGrowth = minGrowth;
    }

    public Long getMaxGrowth() {
        return maxGrowth;
    }

    public void setMaxGrowth(Long maxGrowth) {
        this.maxGrowth = maxGrowth;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Integer getOrderSum() {
        return orderSum;
    }

    public void setOrderSum(Integer orderSum) {
        this.orderSum = orderSum;
    }

    public Integer getMessageSum() {
        return messageSum;
    }

    public void setMessageSum(Integer messageSum) {
        this.messageSum = messageSum;
    }

    public Date getMemberGradeCreateTime() {
        return memberGradeCreateTime;
    }

    public void setMemberGradeCreateTime(Date memberGradeCreateTime) {
        this.memberGradeCreateTime = memberGradeCreateTime;
    }

    public Date getMemberGradeModifyTime() {
        return memberGradeModifyTime;
    }

    public void setMemberGradeModifyTime(Date memberGradeModifyTime) {
        this.memberGradeModifyTime = memberGradeModifyTime;
    }

    public Long getMemberGradeCreateEmp() {
        return memberGradeCreateEmp;
    }

    public void setMemberGradeCreateEmp(Long memberGradeCreateEmp) {
        this.memberGradeCreateEmp = memberGradeCreateEmp;
    }

    public Long getMemberGradeModifyEmp() {
        return memberGradeModifyEmp;
    }

    public void setMemberGradeModifyEmp(Long memberGradeModifyEmp) {
        this.memberGradeModifyEmp = memberGradeModifyEmp;
    }
}
