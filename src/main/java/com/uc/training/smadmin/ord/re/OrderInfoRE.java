package com.uc.training.smadmin.ord.re;

import java.io.Serializable;
import java.util.List;

/**
 * description://TODO
 *
 * @param
 * @author DK
 * @date 2018/10/30  11:04
 * @return
 **/


public class OrderInfoRE implements Serializable {

    /**
     * 订单id
     */
    private Integer orderId;
    /**
     * 订单编码
     */
    private String orderNum;
    /**
     * 订单状态
     */
    private Long status;

    /**
     * 订单状态说明
     */
    private String statusDes;
    /**
     * 订单金额
     */

    private Double orderPrice;

    /**
     * 订单实际支付金额
     */

    private Double payPrice;

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
     * 订单商品信息列表
     */
    private List<OrderGoodsDetailRe> orderGoodsDetailRe;

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

    /**
     * 订单总数
     *
     * @return
     */


    private Long totalOrderNum;

    public String getStatusDes() {
        return statusDes;
    }

    public void setStatusDes(String statusDes) {
        this.statusDes = statusDes;
    }

    public Long getTotalOrderNum() {
        return totalOrderNum;
    }

    public void setTotalOrderNum(Long totalOrderNum) {
        this.totalOrderNum = totalOrderNum;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Double getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(Double payPrice) {
        this.payPrice = payPrice;
    }

    public List<OrderGoodsDetailRe> getOrderGoodsDetailRe() {
        return orderGoodsDetailRe;
    }

    public void setOrderGoodsDetailRe(List<OrderGoodsDetailRe> orderGoodsDetailRe) {
        this.orderGoodsDetailRe = orderGoodsDetailRe;
    }

}
