package com.ucar.smadmin.gds.vo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/1
 */
public class AdminInsertGoodsPicVO implements Serializable{

    private static final long serialVersionUID = 620057883239071872L;
    /**
     * 商品属性id
     */
    @NotNull(message = "请选择商品属性")
    private Long propertyId;
    /**
     * 图片名称
     */
    @NotBlank(message = "图片名称不能为空!")
    private String picName;
    /**
     * 图片地址
     */
    @NotBlank(message = "请上传图片！")
    private String picUrl;

    @Override
    public String toString() {
        return "AdminInsertGoodsPicVO{" +
                "propertyId=" + propertyId +
                ", picName='" + picName + '\'' +
                ", picUrl='" + picUrl + '\'' +
                '}';
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
