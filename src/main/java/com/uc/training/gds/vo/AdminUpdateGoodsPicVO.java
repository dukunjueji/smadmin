package com.uc.training.gds.vo;

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
public class AdminUpdateGoodsPicVO implements Serializable {

    private static final long serialVersionUID = -4601705605148702575L;

    /**
     * 属性图片id
     */
    @NotNull(message = "请重新选择图片")
    private Long id;
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
        return "AdminUpdateGoodsPicVO{" +
                "id=" + id +
                ", picName='" + picName + '\'' +
                ", picUrl='" + picUrl + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
