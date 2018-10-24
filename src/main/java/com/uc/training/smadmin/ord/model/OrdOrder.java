package com.uc.training.smadmin.ord.model;

import java.io.Serializable;
import java.util.Date;

/**
 *用户订单信息
 * @author DK
 * @date 2018/10/15
 */

public class OrdOrder implements Serializable{

  private Long id;
  private Long memberId;
  private String orderNum;
  private double orderPrice;
  private double payPrice;
  private Long isDelete;
  private Long status;
  private String receiptName;
  private String receiptTel;
  private String receiptAddress;
  private Long createEmp;
  private java.sql.Timestamp createTime;
  private Long modifyEmp;
  private java.sql.Timestamp modifyTime;


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


  public double getOrderPrice() {
    return orderPrice;
  }

  public void setOrderPrice(double orderPrice) {
    this.orderPrice = orderPrice;
  }


  public double getPayPrice() {
    return payPrice;
  }

  public void setPayPrice(double payPrice) {
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


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public Long getModifyEmp() {
    return modifyEmp;
  }

  public void setModifyEmp(Long modifyEmp) {
    this.modifyEmp = modifyEmp;
  }


  public java.sql.Timestamp getModifyTime() {
    return modifyTime;
  }

  public void setModifyTime(java.sql.Timestamp modifyTime) {
    this.modifyTime = modifyTime;
  }

}
