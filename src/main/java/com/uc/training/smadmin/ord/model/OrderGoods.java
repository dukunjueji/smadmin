package com.uc.training.smadmin.ord.model;

import com.uc.training.common.base.model.BaseModel;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author kun.du01@ucarinc.com
 * @date 2018-10-17 星期三 16:28
 * @description:订单商品信息表
 */
public class OrderGoods extends BaseModel implements Serializable {

    private static final long serialVersionUID = 2616817542365114797L;

    /**
     * 自增主键
     **/
    private Long id;

    /**
     * 订单id
     **/
    private Long orderId;

    /**
     * 商品id
     **/
    private Long goodsId;

    /**
     * 商品属性id
     **/
    private Long goodsPropertyId;

    /**
     * 商品数量
     **/
    private Integer goodsNum;

    /**
     * 商品的原价
     **/
    private BigDecimal salePrice;

    /**
     * 商品的优惠单价
     **/
    private BigDecimal discountPrice;

    /**
     * 商品的实际支付价格
     **/
    private BigDecimal payPrice;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return this.orderId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getGoodsId() {
        return this.goodsId;
    }

    public void setGoodsPropertyId(Long goodsPropertyId) {
        this.goodsPropertyId = goodsPropertyId;
    }

    public Long getGoodsPropertyId() {
        return this.goodsPropertyId;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Integer getGoodsNum() {
        return this.goodsNum;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getSalePrice() {
        return this.salePrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public BigDecimal getDiscountPrice() {
        return this.discountPrice;
    }

    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }

    public BigDecimal getPayPrice() {
        return this.payPrice;
    }

}
