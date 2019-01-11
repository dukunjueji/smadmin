package com.ucar.smadmin.gds.vo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/29
 */
public class AdminGoodsVO implements Serializable{

    private static final long serialVersionUID = -8084737002616351560L;

    /**
     * 商品名称
     */
    @NotBlank(message = "商品名称不能为空")
    private String name;

    /**
     * 商品属性
     */
    @NotNull(message = "请选择商品类型！")
    private Long categoryId;

    /**
     * 详情
     */
    private String detail;

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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "AdminGoodsVO{" +
                "name='" + name + '\'' +
                ", categoryId=" + categoryId +
                ", detail='" + detail + '\'' +
                '}';
    }
}
