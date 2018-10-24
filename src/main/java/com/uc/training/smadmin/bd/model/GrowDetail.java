package com.uc.training.smadmin.bd.model;

import com.uc.training.common.base.model.BaseModel;

import java.io.Serializable;
import java.util.Date;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/15
 * 说明：成长值明细类
 */
public class GrowDetail extends BaseModel implements Serializable {

    private static final long serialVersionUID = 7837521452890068574L;
    /**
     * 主键id
     */
    private Long id;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 类型 1:登录、2:签到、3:购买商品、4:评价、5:分享
     */
    private Integer type;

    /**
     * 成长值
     */
    private Long growth;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getGrowth() {
        return growth;
    }

    public void setGrowth(Long growth) {
        this.growth = growth;
    }


}
