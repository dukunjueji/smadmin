package com.uc.training.smadmin.gds.re;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/29
 */
public class AdminGoodsRE implements Serializable{

    private static final long serialVersionUID = 8124458596259805857L;

    /**
     * 商品id
     */
    private Long id;

    /**
     * 商品编号
     */
    private String code;
    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品类型
     */
    private Long categoryId;

    /**
     * 出售价格
     */
    private BigDecimal sales;

    /**
     * 详情
     */
    private String detail;

    /**
     * 商品状态（上架，下架）
     */
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public BigDecimal getSales() {
        return sales;
    }

    public void setSales(BigDecimal sales) {
        this.sales = sales;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AdminGoodsRE{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", categoryId=" + categoryId +
                ", sales=" + sales +
                ", detail='" + detail + '\'' +
                ", status=" + status +
                '}';
    }
}
