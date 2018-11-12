package com.uc.training.smadmin.gds.vo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/31
 */
public class AdminPropertyVO implements Serializable{

    /**
     * 商品id
     */
    @NotNull(message = "请选择商品")
    private Long goodsId;
    /**
     * 库存
     */
    @NotNull(message = "库存不能为空")
    private Integer stock;
    /**
     * 打折价格
     */
    private BigDecimal discountPrice;
    /**
     * 出售价格
     */
    @NotNull(message = "出售价格不能为空")
    private BigDecimal salePrice;
    /**
     * 规格
     */
    @NotBlank(message = "商品规格不能为空")
    private String property;
    /**
     * 是否打折
     */
    @NotNull(message = "请选择是否打折")
    private Integer isDiscount;

    @Override
    public String toString() {
        return "AdminPropertyVO{" +
                "goodsId=" + goodsId +
                ", stock=" + stock +
                ", discountPrice=" + discountPrice +
                ", salePrice=" + salePrice +
                ", property='" + property + '\'' +
                ", isDiscount=" + isDiscount +
                '}';
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
