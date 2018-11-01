package com.uc.training.smadmin.gds.re;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/31
 */
public class AdminPropertyListRE implements Serializable{

    private static final long serialVersionUID = -153169186565165164L;

    /**
     * 商品属性id
     */
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

    private List<AdminGoodsPicRE> adminGoodsPicRE;

    @Override
    public String toString() {
        return "AdminPropertyListRE{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", stock=" + stock +
                ", discountPrice=" + discountPrice +
                ", salePrice=" + salePrice +
                ", property='" + property + '\'' +
                ", isDiscount=" + isDiscount +
                '}';
    }

    public List<AdminGoodsPicRE> getAdminGoodsPicRE() {
        return adminGoodsPicRE;
    }

    public void setAdminGoodsPicRE(List<AdminGoodsPicRE> adminGoodsPicRE) {
        this.adminGoodsPicRE = adminGoodsPicRE;
    }

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
}
