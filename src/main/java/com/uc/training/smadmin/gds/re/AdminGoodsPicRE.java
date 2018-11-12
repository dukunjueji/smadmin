package com.uc.training.smadmin.gds.re;

import java.io.Serializable;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/31
 */
public class AdminGoodsPicRE implements Serializable{

    private static final long serialVersionUID = -1290973405434155932L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 图片名称
     */
    private String picName;
    /**
     * 图片地址
     */
    private String picUrl;

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

    @Override
    public String toString() {
        return "AdminGoodsPicRE{" +
                "id=" + id +
                ", picName='" + picName + '\'' +
                ", picUrl='" + picUrl + '\'' +
                '}';
    }
}
