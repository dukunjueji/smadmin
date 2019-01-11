package com.uc.training.base.bd.vo;

import com.uc.training.common.base.model.BaseDomain;

import java.io.Serializable;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/15
 */
public class MemberRechargeHistoryVO extends BaseDomain implements Serializable {
    private static final long serialVersionUID = -6248171267506524782L;

    private Long memberId;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "MemberRechargeHistoryVO{" +
                "memberId=" + memberId +
                '}';
    }
}
