package com.uc.training.smadmin.gds.re;

import com.uc.training.smadmin.gds.vo.AdminGoodsVO;

import java.io.Serializable;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/29
 */
public class AdminGoodsRE extends AdminGoodsVO implements Serializable{

    private static final long serialVersionUID = 8124458596259805857L;

    /**
     * 商品id
     */
    private Long id;

    /**
     * 商品编码
     */
    private String code;

    /**
     * 销量
     */
    private Integer sales;

    /**
     * 商品状态（上架，下架）
     */
    private Integer status;

    /**
     * 商品分类名称
     */
    private String categoryName;

    @Override
    public String toString() {
        return "AdminGoodsRE{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", sales=" + sales +
                ", status=" + status +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
