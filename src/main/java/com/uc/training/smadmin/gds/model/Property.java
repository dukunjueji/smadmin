package com.uc.training.smadmin.gds.model;

import com.uc.training.common.base.model.BaseModel;


public class Property extends BaseModel {

  private long id;
  /**
   * 商品id
   */
  private long goodsId;
  /**
   * 库存
   */
  private long stock;
  /**
   * 打折价格
   */
  private double discountPrice;
  /**
   * 出售价格
   */
  private double salePrice;
  /**
   * 规格
   */
  private String property;
  /**
   * 是否打折
   */
  private long isDiscount;



  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getGoodsId() {
    return goodsId;
  }

  public void setGoodsId(long goodsId) {
    this.goodsId = goodsId;
  }


  public long getStock() {
    return stock;
  }

  public void setStock(long stock) {
    this.stock = stock;
  }


  public double getDiscountPrice() {
    return discountPrice;
  }

  public void setDiscountPrice(double discountPrice) {
    this.discountPrice = discountPrice;
  }


  public double getSalePrice() {
    return salePrice;
  }

  public void setSalePrice(double salePrice) {
    this.salePrice = salePrice;
  }


  public String getProperty() {
    return property;
  }

  public void setProperty(String property) {
    this.property = property;
  }


  public long getIsDiscount() {
    return isDiscount;
  }

  public void setIsDiscount(long isDiscount) {
    this.isDiscount = isDiscount;
  }

}
