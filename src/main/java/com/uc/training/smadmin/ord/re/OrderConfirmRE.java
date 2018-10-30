package com.uc.training.smadmin.ord.re;

import java.io.Serializable;

/**
 * 订单提交RE
 *
 * @author DK
 */
public class OrderConfirmRE implements Serializable {

    /**
     * 状态说明
     **/
    private String showStatus;

    /**
     * 商品状态码0表示删除/下架/库存不足 1上架
     */
    private Integer goodsStatus;

    /**
     * 订单编码
     */
    private String orderNum;


    /**
     * 订单状态
     *0-未生成1-待付款 2-取消订单 3-待发货 4-待收货 5-已完成
     * @return
     */

    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(String showStatus) {
        this.showStatus = showStatus;
    }

    public Integer getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(Integer goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
}
