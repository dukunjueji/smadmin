package com.uc.training.smadmin.bd.vo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/24
 * 说明：
 */
public class MemberInfoVO implements Serializable {
    private static final long serialVersionUID = 3856880345470428651L;

    /**
     * 会员昵称
     */
    private String nickname;

    /**
     * 会员id
     */
     private Long memberId;
    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    @Pattern(regexp = "^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\\\.)+[a-z]{2,}$", message = "邮箱格式不正确")
    private String email;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 头像路径
     */
    private String imageUrl;

    /**
     *用户余额
     */
    private Double balances;

    /**
     * 用户订单号
     * @return
     */

    private String orderName;

    /**
     * 用户订单商品id
     * @return
     */
    private Long goodsId;

    /**
     * 用户订单总价
     */
    private Double totalPrice;

    /**
     * 用户订单商品对应的商品数目
     * @return
     */
    private Long goodsNum;

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Long goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Double getBalances() {
        return balances;
    }

    public void setBalances(Double balances) {
        this.balances = balances;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "MemberInfoVO{" +
                "nickname='" + nickname + '\'' +
                ", memberId=" + memberId +
                ", email='" + email + '\'' +
                ", sex=" + sex +
                ", imageUrl='" + imageUrl + '\'' +
                ", balances=" + balances +
                ", orderName='" + orderName + '\'' +
                ", goodsId=" + goodsId +
                ", totalPrice=" + totalPrice +
                ", goodsNum=" + goodsNum +
                '}';
    }
}
