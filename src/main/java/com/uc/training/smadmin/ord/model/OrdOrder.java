package com.uc.training.smadmin.ord.model;

import java.io.Serializable;
import java.util.Date;

/**
 *用户订单信息
 * @author DK
 * @date 2018/10/15
 */

public class OrdOrder implements Serializable{

  /**自增主键id **/
  private Long id;

  /**会员id**/
  private Long memberId;

  /**商品数量**/
  private String orderNum;

  /**订单价格**/
  private Double orderPrice;

  /**支付价格**/
  private Double payPrice;

  /**删除状态**/
  private Long isDelete;

  /**状态:1待付款2取消订单3待发货4待收货5已完成**/
  private Long status;

  /**下单用户名**/
  private String receiptName;

  /**下单电话**/
  private String receiptTel;

  /**下单地址**/
  private String receiptAddress;

  /**创建人**/
  private Long createEmp;

  /**创建时间**/
  private Date createTime;

  /**修改人**/
  private Long modifyEmp;

  /**修改时间**/
  private Date modifyTime;


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


  public Long getCreateEmp() {
    return createEmp;
  }

  public void setCreateEmp(Long createEmp) {
    this.createEmp = createEmp;
  }


  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }


  public Long getModifyEmp() {
    return modifyEmp;
  }

  public void setModifyEmp(Long modifyEmp) {
    this.modifyEmp = modifyEmp;
  }


  public Date getModifyTime() {
    return modifyTime;
  }

  public void setModifyTime(Date modifyTime) {
    this.modifyTime = modifyTime;
  }

}
