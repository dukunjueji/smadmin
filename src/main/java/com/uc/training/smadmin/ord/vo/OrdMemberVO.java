package com.uc.training.smadmin.ord.vo;

import com.ycc.base.common.BaseDomain;

import java.io.Serializable;

/**
 * 会员订单VO
 *
 * @author DK
 */
public class OrdMemberVO extends BaseDomain implements Serializable {
    /**
     * 会员id
     */
    private Long memberId;

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
                '}';
    }
}
