package com.uc.training.smadmin.gds.re;

import java.io.Serializable;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/24
 */
public class HotTagRE implements Serializable{

    private static final long serialVersionUID = -6333049884502048708L;

    /**
     * 标签id
     */
    private String id;
    /**
     * 热搜标签
     */
    private String tag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
