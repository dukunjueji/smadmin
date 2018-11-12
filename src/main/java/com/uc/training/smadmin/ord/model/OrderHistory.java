package com.uc.training.smadmin.ord.model;

import com.uc.training.common.base.model.BaseModel;

import java.io.Serializable;

/**
 * @author kun.du01@ucarinc.com
 * @date 2018-10-17 星期三 16:28
 * @description:
 */
public class OrderHistory extends BaseModel implements Serializable {

    private static final long serialVersionUID = 2554853947932732216L;

    /**
     * 主键id
     **/
    private Long id;

    /**
     * 订单id
     **/
    private Long orderId;

    /**
     * 状态:1待付款2取消订单3待发货4待收货5已完成
     **/
    private Integer status;

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

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return this.status;
    }

}
