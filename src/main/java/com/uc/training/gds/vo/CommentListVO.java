package com.uc.training.gds.vo;

import com.uc.training.common.bean.PageQuery;

import java.io.Serializable;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/17
 */
public class CommentListVO extends PageQuery implements Serializable {

    private static final long serialVersionUID = 4121508245734898607L;
    /**
     * 评论主键id
     */
    private Long id;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 回复状态 0 ：未回复 1：已回复
     */
    private Integer status;

    /**
     * 商品属性id
     */
    private Long goodsPropertyId;

    /**
     * 评分区间上限
     */
    private Integer lowSroce = 0;

    /**
     * 评分区间上限
     */
    private Integer highSroce = 5;

    public Integer getLowSroce() {
        return lowSroce;
    }

    public void setLowSroce(Integer lowSroce) {
        this.lowSroce = lowSroce;
    }

    public Integer getHighSroce() {
        return highSroce;
    }

    public void setHighSroce(Integer highSroce) {
        this.highSroce = highSroce;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getGoodsPropertyId() {
        return goodsPropertyId;
    }

    public void setGoodsPropertyId(Long goodsPropertyId) {
        this.goodsPropertyId = goodsPropertyId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CommentListVO{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", goodsPropertyId=" + goodsPropertyId +
                '}';
    }
}
