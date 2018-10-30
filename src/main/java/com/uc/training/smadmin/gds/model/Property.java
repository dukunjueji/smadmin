package com.uc.training.smadmin.gds.model;

import com.uc.training.common.base.model.BaseModel;

import java.math.BigDecimal;

/**
 *
 * 功能描述: 商品属性
 *
 * @param:
 * @return:
 * @auther: ling
 * @date: 2018/10/30 10:05
 */
public class Property extends BaseModel {

  private long id;
  /**
   * 商品id
   */
  private long goodsId;
  /**
   * 库存
   */
  private Integer stock;
  /**
   * 打折价格
   */
  private BigDecimal discountPrice;
  /**
   * 出售价格
   */
  private BigDecimal salePrice;
  /**
   * 规格
   */
  private String property;
  /**
   * 是否打折
   */
  private Long isDiscount;



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

  public void setStock(Integer stock) {
    this.stock = stock;
  }

  public BigDecimal getDiscountPrice() {
    return discountPrice;
  }

  public void setDiscountPrice(BigDecimal discountPrice) {
    this.discountPrice = discountPrice;
  }

  public BigDecimal getSalePrice() {
    return salePrice;
  }

  public void setSalePrice(BigDecimal salePrice) {
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

  public void setIsDiscount(Long isDiscount) {
    this.isDiscount = isDiscount;
  }

}
