package com.uc.training.smadmin.gds.model;

import com.uc.training.common.base.model.BaseModel;

import java.io.Serializable;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/17
 */
public class Comment extends BaseModel implements Serializable {

    private static final long serialVersionUID = -6589465877208471433L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 商品id
     */
    private Long goodsId;
    /**
     * 会员id
     */
    private Long memberId;
    /**
     * 会员昵称
     */
    private String memberNickname;
    /**
     * 会员头像路径
     */
    private String memberImageUrl;
    /**
     * 商品属性id
     */
    private Long goodsPropertyId;
    /**
     * 订单商品id
     */
    private Long orderGoodsId;
    /**
     * 商品属性名称
     */
    private String goodsPropertyName;
    /**
     * 商品评分
     */
    private Float goodsSroce;
    /**
     * 商家回复
     */
    private Integer status;
    /**
     * 父级id(追加评论)
     */
    private Long parentId;
    /**
     * 评论内容
     */
    private String content;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", memberId=" + memberId +
                ", memberNickname='" + memberNickname + '\'' +
                ", memberImageUrl='" + memberImageUrl + '\'' +
                ", goodsPropertyId=" + goodsPropertyId +
                ", orderGoodsId=" + orderGoodsId +
                ", goodsPropertyName='" + goodsPropertyName + '\'' +
                ", goodsSroce=" + goodsSroce +
                ", status=" + status +
                ", parentId=" + parentId +
                ", content='" + content + '\'' +
                '}';
    }

    public Long getOrderGoodsId() {
        return orderGoodsId;
    }

    public void setOrderGoodsId(Long orderGoodsId) {
        this.orderGoodsId = orderGoodsId;
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

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberNickname() {
        return memberNickname;
    }

    public void setMemberNickname(String memberNickname) {
        this.memberNickname = memberNickname;
    }

    public String getMemberImageUrl() {
        return memberImageUrl;
    }

    public void setMemberImageUrl(String memberImageUrl) {
        this.memberImageUrl = memberImageUrl;
    }

    public Long getGoodsPropertyId() {
        return goodsPropertyId;
    }

    public void setGoodsPropertyId(Long goodsPropertyId) {
        this.goodsPropertyId = goodsPropertyId;
    }

    public String getGoodsPropertyName() {
        return goodsPropertyName;
    }

    public void setGoodsPropertyName(String goodsPropertyName) {
        this.goodsPropertyName = goodsPropertyName;
    }

    public Float getGoodsSroce() {
        return goodsSroce;
    }

    public void setGoodsSroce(Float goodsSroce) {
        this.goodsSroce = goodsSroce;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
