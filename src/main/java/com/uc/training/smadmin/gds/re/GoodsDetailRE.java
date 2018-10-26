package com.uc.training.smadmin.gds.re;

import java.util.List;

/**
 * 商品详情类
 *
 * @author zhongling(ling.zhong @ ucarinc.com)
 * @since 2018年10月19日 10:51
 */
public class GoodsDetailRE {
    /**
     * 商品属性id
     */
    private long propertyId;
    /**
     * 商品编号
     */
    private String code;
    /**
     * 商品图片
     */
    private List<PropertyUrlRE> picUrl;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 销量
     */
    private long sales;
    /**
     * 详情
     */
    private String detail;
    /**
     * 商品状态(是否下架)
     */
    private long status;
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

    public long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(long propertyId) {
        this.propertyId = propertyId;
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

    public long getSales() {
        return sales;
    }

    public void setSales(long sales) {
        this.sales = sales;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
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

    public List<PropertyUrlRE> getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(List<PropertyUrlRE> picUrl) {
        this.picUrl = picUrl;
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
