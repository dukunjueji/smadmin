package com.uc.training.smadmin.ord.vo;

import java.io.Serializable;


/**
 *订单商品信息VO
 * @author DK
 * @date 2018/10/15
 */
public class OrdOrderGoodsVo implements Serializable{
    /**
     * id
     */
    private Long id;

    /**
     * 订单商品信息
     */

    /**
     * 会员id
     */
    private Long memberId;
    /**
     * 购物车id
     */
    private Long cartId;
    /**
     * 商品id
     */
    private Long goodsId;


    /**
     * 商品图片
     */
    private String  gdsUrl;

    /**
     * 商品名称
     */
    private String gdsName;

    /**
     * 商品数量
     */
    private int num;

    /**
     * 商品规格
     */
    private String property;

    /**
     * 属性id
     */
    private Long propertyId;
    /**
     * 库存
     */
    private long stock;
    /**
     * 商品原价
     */
    private double salePrice;

    /**
     * 商品优惠价
     */
    private double discountPrice;

    /**
     * 是否打折
     */
    private long isDiscount;

    /**
     * 商品状态(是否下架)
     */
    private long status;

    /**
     * 收货人姓名
     */
    private String receiptName;

    /**
     * 收货人电话
     */
    private String receiptTel;

    /**
     * 收货人地址
     */
    private String receiptAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * 订单总额
     */
    private double totalPrice;
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGdsUrl() {
        return gdsUrl;
    }

    public void setGdsUrl(String gdsUrl) {
        this.gdsUrl = gdsUrl;
    }

    public String getGdsName() {
        return gdsName;
    }

    public void setGdsName(String gdsName) {
        this.gdsName = gdsName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }
    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public long getIsDiscount() {
        return isDiscount;
    }

    public void setIsDiscount(long isDiscount) {
        this.isDiscount = isDiscount;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public String getReceiptName() {
        return receiptName;
    }

    public void setReceiptName(String receiptName) {
        this.receiptName = receiptName;
    }

    public String getReceiptTel() {
        return receiptTel;
    }

    public void setReceiptTel(String receiptTel) {
        this.receiptTel = receiptTel;
    }

    public String getReceiptAddress() {
        return receiptAddress;
    }

    public void setReceiptAddress(String receiptAddress) {
        this.receiptAddress = receiptAddress;
    }



}
