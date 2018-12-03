package com.uc.training.gds.vo;

import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * description: TODO
 *
 * @author 黄宏俊（hongjun.huang01@ucarinc.com）
 * @version 1.0
 * @date 2018/11/16  16:38
 */
public class AddCommentVO implements Serializable {

  private static final long serialVersionUID =  4289686880751788752L;
  /**
   * 商品id
   */
  private Long goodsId;

  /**
   *商品属性ID
   */
  private Long goodsPropertyId;

  /**
   *商品属性名称
   */
  private String goodsPropertyName;

  /**
   *评论状态
   */
  private Integer status;

  /**
   *父级Id
   */
  private Long parentId;

  /**
   *评论内容
   */
  @Length(max = 255,message = "内容不能超过255个字符之间")
  private String content;

  /**
   * 评论图片List
   */
  private String[] urlList;

  /**
   * 订单商品表id
   */
  private Long orderGoodsId;

  /**
   *评分
   */
  private Double goodsSroce;


  public Double getGoodsSroce() {
    return goodsSroce;
  }

  public void setGoodsSroce(Double goodsSroce) {
    this.goodsSroce = goodsSroce;
  }

  public Long getGoodsId() {
    return goodsId;
  }

  public void setGoodsId(Long goodsId) {
    this.goodsId = goodsId;
  }

  public Long getGoodsPropertyId() {
    return goodsPropertyId;
  }

  public void setGoodsPropertyId(Long goodsPropertyId) {
    this.goodsPropertyId = goodsPropertyId;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getGoodsPropertyName() {
    return goodsPropertyName;
  }

  public void setGoodsPropertyName(String goodsPropertyName) {
    this.goodsPropertyName = goodsPropertyName;
  }

  public String[] getUrlList() {
    return urlList;
  }

  public void setUrlList(String[] urlList) {
    this.urlList = urlList;
  }

  public Long getOrderGoodsId() {
    return orderGoodsId;
  }

  public void setOrderGoodsId(Long orderGoodsId) {
    this.orderGoodsId = orderGoodsId;
  }
}
