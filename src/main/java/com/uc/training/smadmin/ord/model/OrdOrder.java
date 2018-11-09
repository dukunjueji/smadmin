package com.uc.training.smadmin.ord.model;

import com.uc.training.common.base.model.BaseModel;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 用户订单信息
 *
 * @author DK
 * @date 2018/10/15
 */

public class OrdOrder extends BaseModel implements Serializable {

    /**
     * 自增主键id
     **/
    private Long id;

    /**
     * 会员id
     **/
    private Long memberId;

    /**
     * 商品数量
     **/
    private String orderNum;

    /**
     * 订单价格
     **/
    private BigDecimal orderPrice;

    /**
     * 支付价格
     **/
    private BigDecimal payPrice;

    /**
     * 删除状态
     **/
    private Long isDelete;

    /**
     * 状态:1待付款2取消订单3待发货4待收货5已完成
     **/
    private Long status;

    /**
     * 下单用户名
     **/
    private String receiptName;

    /**
     * 下单电话
     **/
    private String receiptTel;

    /**
     * 下单地址
     **/
    private String receiptAddress;

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

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }


    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }


    public BigDecimal getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }


    public Long getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Long isDelete) {
        this.isDelete = isDelete;
    }


    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
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

}
