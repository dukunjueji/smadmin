package com.uc.training.smadmin.upload.vo;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/29
 */
public class UploadVO implements Serializable{

    private static final long serialVersionUID = 59283449140196354L;
    /**
     * 文件类型
     */
    private MultipartFile file;
    /**
     * Base64类型
     */
    private String imageText;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getImageText() {
        return imageText;
    }

    public void setImageText(String imageText) {
        this.imageText = imageText;
    }
}
