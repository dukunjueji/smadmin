package com.ucar.smadmin.gds.vo;

import com.ucar.smapi.common.bean.PageQuery;

import java.io.Serializable;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/6
 */
public class AdminHotTagListVO  extends PageQuery implements Serializable {

    private static final long serialVersionUID = -4782747724600979256L;

    /**
     * 标签
     */
    private String tag;

    @Override
    public String toString() {
        return "AdminHotTagListVO{" +
                "tag='" + tag + '\'' +
                '}';
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
