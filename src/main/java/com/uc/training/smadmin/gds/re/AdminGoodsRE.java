package com.uc.training.smadmin.gds.re;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/29
 */
public class AdminGoodsRE implements Serializable{

    private static final long serialVersionUID = 8124458596259805857L;

    /**
     * 商品id
     */
    private Long id;

    /**
     * 商品编号
     */
    private String code;
    /**
     * 商品名称
     */
    private String name;

    /**
     * 出售价格
     */
    private BigDecimal sales;

    /**
     * 详情
     */
    private String detail;
    /**
     * 商品状态
     */
    private Integer status;

    /**
     * 类型名称
     */
    private String categoryName;

    /**
     * 库存量
     */
    private Integer stock;

    /**
     * 打折价格
     */
    private BigDecimal discountPrice;

    private String property;

    /**
     * 定价
     */
    private BigDecimal salePrice;

    /**
     * 是否打折
     */
    private Integer isDiscount;

    /**
     * 图片地址
     */
    private String picUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSales() {
        return sales;
    }

    public void setSales(BigDecimal sales) {
        this.sales = sales;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public Integer getIsDiscount() {
        return isDiscount;
    }

    public void setIsDiscount(Integer isDiscount) {
        this.isDiscount = isDiscount;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
