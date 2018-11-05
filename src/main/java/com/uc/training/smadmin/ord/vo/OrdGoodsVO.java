package com.uc.training.smadmin.ord.vo;

import java.io.Serializable;

/**
 * description: TODO
 *
 * @author hhj
 * @version 1.0
 * @date 2018/11/2  12:28
 */
public class OrdGoodsVO implements Serializable {

  /**
   * 商品id
   */
  private Long goodsId;

  /**
   * 属性
   */
  private Long propertyId;

  /**
   * 商品数量
   */
  private Long goodsNum;

  public Long getGoodsId() {
    return goodsId;
  }

  public void setGoodsId(Long goodsId) {
    this.goodsId = goodsId;
  }

  public Long getPropertyId() {
    return propertyId;
  }

  public void setPropertyId(Long propertyId) {
    this.propertyId = propertyId;
  }

  public Long getGoodsNum() {
    return goodsNum;
  }

  public void setGoodsNum(Long goodsNum) {
    this.goodsNum = goodsNum;
  }
}
