package com.uc.training.gds.vo;

import java.io.Serializable;

/**
 * 商品库存
 *
 * @author zhongling(ling.zhong @ ucarinc.com)
 * @since 2018年10月19日 14:21
 */
public class GoodsStokeVO implements Serializable {

    private static final long serialVersionUID = -6524638416249583239L;
    private Long propertyId;
    /**
     * 需要扣除的库存
     */
    private Long stock;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
    /**
     * 商品属性id
     * @return
     */
    private Long goodsId;

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "GoodsStokeVO{" + "propertyId=" + propertyId + ", stock=" + stock + ", goodsId=" + goodsId + '}';
    }
}
