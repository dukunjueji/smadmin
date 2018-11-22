package com.uc.training.ord.vo;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


/**
 * 订单商品信息VO
 *
 * @author DK
 * @date 2018/10/15
 */
public class OrdOrderGoodsVO implements Serializable {
    /**
     * id
     */
    private Long id;

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
    private String gdsUrl;

    /**
     * 商品名称
     */
    private String gdsName;

    /**
     * 商品数量
     */
    private Integer num;

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
    private Long stock;
    /**
     * 商品原价
     */
    private BigDecimal salePrice;

    /**
     * 商品优惠价
     */
    private BigDecimal discountPrice;

    /**
     * 商品实际支付价格
     */
    private BigDecimal payPrice;
    /**
     * 是否打折
     */
    private Long isDiscount;

    /**
     * 商品状态(是否下架)
     */
    private Long status;

    /**
     * 地址id
     */
    private Long addressId;
    /**
     * 收货人姓名
     */
    private String receiptName;

    /**
     * 收货人电话
     */
    private String receiptTel;

    /**
     * 订单实际支付总额
     */
    private BigDecimal totalPrice;
    /**
     * 订单实际总额
     */
    private BigDecimal orderPrice;
    /**
     * 收货人地址
     */
    private String receiptAddress;
    /**
     * 订单信息串
     */
    private String orderInfoList;

    private List<OrdOrderGoodsVO> list;

    public BigDecimal getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }

    public List<OrdOrderGoodsVO> getList() {
        return (List<OrdOrderGoodsVO>) JSONArray.toList(JSONArray.fromObject(this.orderInfoList), new OrdOrderGoodsVO(), new JsonConfig());
    }

    public void setList(List<OrdOrderGoodsVO> list) {
        this.list = list;
    }

    public String getOrderInfoList() {
        return orderInfoList;
    }

    public void setOrderInfoList(String orderInfoList) {
        this.orderInfoList = orderInfoList;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
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

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Long getIsDiscount() {
        return isDiscount;
    }

    public void setIsDiscount(Long isDiscount) {
        this.isDiscount = isDiscount;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
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

    @Override
    public String toString() {
        return "OrdOrderGoodsVO{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", cartId=" + cartId +
                ", goodsId=" + goodsId +
                ", gdsUrl='" + gdsUrl + '\'' +
                ", gdsName='" + gdsName + '\'' +
                ", num=" + num +
                ", property='" + property + '\'' +
                ", propertyId=" + propertyId +
                ", stock=" + stock +
                ", salePrice=" + salePrice +
                ", discountPrice=" + discountPrice +
                ", payPrice=" + payPrice +
                ", isDiscount=" + isDiscount +
                ", status=" + status +
                ", addressId=" + addressId +
                ", receiptName='" + receiptName + '\'' +
                ", receiptTel='" + receiptTel + '\'' +
                ", totalPrice=" + totalPrice +
                ", orderPrice=" + orderPrice +
                ", receiptAddress='" + receiptAddress + '\'' +
                ", orderInfoList='" + orderInfoList + '\'' +
                ", list=" + list +
                '}';
    }
}
