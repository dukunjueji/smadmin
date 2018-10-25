package com.uc.training.smadmin.ord.re;

import java.io.Serializable;
import java.util.Date;

/**
 * description: TODO
 *
 * @author 黄宏俊
 * @version 1.0
 * @date 2018/10/25  10:36
 */
public class OrderRe implements Serializable {
  private static final long serialVersionUID = -6912839796213052826L;

  /**主键id**/
  private Long id;

  /**会员id**/
  private Long memberId;

  /**订单号**/
  private String orderNum;

  /**订单总额**/
  private Double orderPrice;

  /**整个订单的实际支付金额**/
  private Double payPrice;

  /**是否删除:1表示删除，0表示未删除**/
  private Integer isDelete;

  /**状态:1待付款2取消订单3待发货4待收货5已完成**/
  private Integer status;

  /**收货人姓名**/
  private String receiptName;

  /**收货人电话**/
  private String receiptTel;

  /**收货人地址**/
  private String receiptAddress;

  /**下单时间**/
  private Date createTime;

  /**状态说明**/
  private String showStatus;

  private String teag;

  public String getTeag() {
    return teag;
  }

  public void setTeag(String teag) {
    this.teag = teag;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

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

  public String getShowStatus() {
    return showStatus;
  }

  public void setShowStatus(String showStatus) {
    this.showStatus = showStatus;
  }
}
