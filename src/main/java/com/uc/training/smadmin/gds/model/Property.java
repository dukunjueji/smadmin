package com.uc.training.smadmin.gds.model;

import com.uc.training.common.base.model.BaseModel;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 商品属性
 *
 * @author zhongling(ling.zhong @ ucarinc.com)
 * @since 2018年10月15日 15:04
 */
public class Property extends BaseModel implements Serializable {

    private static final long serialVersionUID = 4467616407682429649L;

    private Long id;
    /**
     * 商品id
     */
    private Long goodsId;
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
    private Integer isDiscount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getStock() {
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

    public Integer getIsDiscount() {
        return isDiscount;
    }

    public void setIsDiscount(Integer isDiscount) {
        this.isDiscount = isDiscount;
    }

    @Override
    public String toString() {
        return "Property{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", stock=" + stock +
                ", discountPrice=" + discountPrice +
                ", salePrice=" + salePrice +
                ", property='" + property + '\'' +
                ", isDiscount=" + isDiscount +
                '}';
    }
}
