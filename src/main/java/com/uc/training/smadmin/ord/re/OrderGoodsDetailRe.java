package com.uc.training.smadmin.ord.re;

import java.io.Serializable;

/**
 * description: TODO
 *
 * @author 黄宏俊
 * @version 1.0
 * @date 2018/10/26  18:28
 */
public class OrderGoodsDetailRe implements Serializable {
    private static final long serialVersionUID = -6912839796213052826L;
    /**
     * 图片
     */
    private String goodsUrl;
    /**
     * 商品名
     */
    private String goodsName;
    /**
     * 商品数量
     */
    private Integer goodsNum = 0;
    /**
     * 商品价格
     */
    private Double goodsPrice = 0.0;
    /**
     * 商品规格
     */
    private String goodsProperty;

    /**
     * 商品属性id
     *
     * @return
     */
    private Long goodsPropertyId;

    /**
     * 商品id
     *
     * @return
     */
    private Long goodsId;
    /**
     * 商品应付价格
     *
     * @return
     */

    private Double payPrice;
    /**
     * 是否打折
     */
    private Long isDiscount;
    /**
     * 打折价格
     */
    private Double discountPrice;

    public Long getIsDiscount() {
        return isDiscount;
    }

    public void setIsDiscount(Long isDiscount) {
        this.isDiscount = isDiscount;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Double getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(Double payPrice) {
        this.payPrice = payPrice;
    }

    public Long getGoodsPropertyId() {
        return goodsPropertyId;
    }

    public void setGoodsPropertyId(Long goodsPropertyId) {
        this.goodsPropertyId = goodsPropertyId;
    }

    public String getGoodsUrl() {
        return goodsUrl;
    }

    public void setGoodsUrl(String goodsUrl) {
        this.goodsUrl = goodsUrl;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsProperty() {
        return goodsProperty;
    }

    public void setGoodsProperty(String goodsProperty) {
        this.goodsProperty = goodsProperty;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
}
