package com.uc.training.smadmin.bd.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/26
 * 说明：
 */
public class ContinueSignDaysVO implements Serializable {
    private static final long serialVersionUID = -6285525694612006329L;

    public final static Integer MAXCONTINUEDAYS=7;
    /**
     * 会员id
     */
    private Long memberId;
    /**
     * 获取离今天最近的连续签到天数
     */
    private int continueSignDays;
    /**
     * 获取de连续签到天数的最后日期
     */
    private Date lastDate;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public int getContinueSignDays() {
        return continueSignDays;
    }

    public void setContinueSignDays(int continueSignDays) {
        this.continueSignDays = continueSignDays;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }
}
