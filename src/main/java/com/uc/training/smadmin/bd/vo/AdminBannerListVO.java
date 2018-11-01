package com.uc.training.smadmin.bd.vo;

import java.io.Serializable;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/1
 */
public class AdminBannerListVO implements Serializable{

    private static final long serialVersionUID = 510756824374107273L;
    /**
     * 广告名称
     */
    private String name;

    /**
     * 是否显示
     */
    private Integer isShow;

    @Override
    public String toString() {
        return "AdminBannerListVO{" +
                "name='" + name + '\'' +
                ", isShow=" + isShow +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }
}
