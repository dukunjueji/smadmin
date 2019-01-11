package com.ucar.smadmin.common.upload.re;

import java.io.Serializable;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/29
 */
public class UploadRE implements Serializable{
    private static final long serialVersionUID = 59283449140196354L;
    /**
     * 图片完整路径
     */
    private String name;
    /**
     * 图片原始路径
     */
    private String originalName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }
}
