package com.uc.training.smadmin.upload.re;

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

    private String name;

    private String OriginalName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalName() {
        return OriginalName;
    }

    public void setOriginalName(String originalName) {
        OriginalName = originalName;
    }
}
