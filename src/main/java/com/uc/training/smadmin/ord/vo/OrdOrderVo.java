package com.uc.training.smadmin.ord.vo;

import com.ycc.base.common.BaseDomain;

import java.io.Serializable;

/**
 * 用户订单信息VO
 *
 * @author DK
 * @date 2018/10/15
 */

public class OrdOrderVo extends BaseDomain implements Serializable {
    /**
     * 收货人姓名
     */
    private String receiptName;

    /**
     * 收货人电话
     */
    private String receiptTel;

    /**
     * 收货人地址
     */
    private String receiptAddress;

    /**
     * 订单状态
     */
    private Long status;

    /**
     * 订单编码
     */
    private String orderNum;

    /**
     * 删除的订单信息
     */
    private String orderListStr;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 会员id
     *
     */
    private Long memberId;


    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getReceiptName() {
        return receiptName;
    }

    public void setReceiptName(String receiptName) {
        this.receiptName = receiptName;
    }

    public String getReceiptTel() {
        return receiptTel;
    }

    public void setReceiptTel(String receiptTel) {
        this.receiptTel = receiptTel;
    }

    public String getReceiptAddress() {
        return receiptAddress;
    }

    public void setReceiptAddress(String receiptAddress) {
        this.receiptAddress = receiptAddress;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getOrderListStr() {
        return orderListStr;
    }

    public void setOrderListStr(String orderListStr) {
        this.orderListStr = orderListStr;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

}
