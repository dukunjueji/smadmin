package com.uc.training.smadmin.gds.re;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品详情类
 *
 * @author zhongling(ling.zhong @ ucarinc.com)
 * @since 2018年10月19日 10:51
 */
public class GoodsDetailRE {
    /**
     * 商品id
     */
    private Long goodsId;
    /**
     * 商品属性id
     */
    private Long propertyId;
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
    private Long sales;
    /**
     * 详情
     */
    private String detail;
    /**
     * 商品状态(是否下架)
     */
    private Long status;
    /**
     * 商品状态(是否删除)
     */
    private Long isDelete;
    /**
     * 库存
     */
    private Long stock;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<PropertyUrlRE> getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(List<PropertyUrlRE> picUrl) {
        this.picUrl = picUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSales() {
        return sales;
    }

    public void setSales(Long sales) {
        this.sales = sales;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Long isDelete) {
        this.isDelete = isDelete;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
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

    public Long getIsDiscount() {
        return isDiscount;
    }

    public void setIsDiscount(Long isDiscount) {
        this.isDiscount = isDiscount;
    }
}
