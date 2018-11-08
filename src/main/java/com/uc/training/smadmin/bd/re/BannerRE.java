package com.uc.training.smadmin.bd.re;

import java.io.Serializable;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/26
 */
public class BannerRE implements Serializable {

    private static final long serialVersionUID = 7553625072522394694L;
    /**
     * 主键id
     */
    private Long id;

    /**
     * 广告图片地址
     */
    private String imageUrl;
    /**
     * 广告地址
     */
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
