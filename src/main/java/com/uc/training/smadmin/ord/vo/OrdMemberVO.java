package com.uc.training.smadmin.ord.vo;

import com.uc.training.common.bean.PageQuery;
import com.ycc.base.common.BaseDomain;

import java.io.Serializable;

/**
 * 会员订单VO
 *
 * @author DK
 */
public class OrdMemberVO extends PageQuery implements Serializable {
    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 订单状态
     *
     * @return
     */
    private Long status;

    /**
     * 订单编号
     *
     * @return
     */
    private String orderNum;

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "OrdMemberVO{" +
                "memberId=" + memberId +
                ", status=" + status +
                ", orderNum='" + orderNum + '\'' +
                '}';
    }
}
