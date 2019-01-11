package com.ucar.smadmin.ord.vo;

import com.ucar.smapi.common.base.model.BaseModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * description: TODO
 *
 * @author 黄宏俊
 * @version 1.0
 * @date 2018/10/25  10:36
 */
public class OrderVO extends BaseModel implements Serializable {
    private static final long serialVersionUID = -6912839796213052826L;

    /**
     * 主键id
     **/
    private Long id;

    /**
     * 会员id
     **/
    private Long memberId;

    /**
     * 订单号
     **/
    private String orderNum;

    /**
     * 订单总额
     **/
    private BigDecimal orderPrice;

    /**
     * 整个订单的实际支付金额
     **/
    private BigDecimal payPrice;

    /**
     * 是否删除:1表示删除，0表示未删除
     **/
    private Integer isDelete;

    /**
     * 状态:1待付款2取消订单3待发货4待收货5已完成
     **/
    private Integer status;

    /**
     * 收货人姓名
     **/
    private String receiptName;

    /**
     * 收货人电话
     **/
    private String receiptTel;

    /**
     * 收货人地址
     **/
    private String receiptAddress;

    /**
     * 下单时间
     **/
    private Date createTime;

    private String teag;

    /**
     * 商品状态码0表示删除/下架/库存不足 1上架
     */
    private Integer goodsStatus;

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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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

    @Override
    public Date getCreateTime() {
        return createTime == null ? null : (Date) createTime.clone();
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime == null ? null : (Date) createTime.clone();
    }

    public String getTeag() {
        return teag;
    }

    public void setTeag(String teag) {
        this.teag = teag;
    }

    public Integer getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(Integer goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    @Override
    public String toString() {
        return "OrderVO{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", orderNum='" + orderNum + '\'' +
                ", orderPrice=" + orderPrice +
                ", payPrice=" + payPrice +
                ", isDelete=" + isDelete +
                ", status=" + status +
                ", receiptName='" + receiptName + '\'' +
                ", receiptTel='" + receiptTel + '\'' +
                ", receiptAddress='" + receiptAddress + '\'' +
                ", createTime=" + createTime +
                ", teag='" + teag + '\'' +
                ", goodsStatus=" + goodsStatus +
                '}';
    }
}
