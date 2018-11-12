package com.uc.training.common.enums;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/30
 */
public enum UploadEnum {

    // 地址为默认地址
    MAX_IMAGE_SIZE("图片大小", 4*1024*1024);
    /**
     * 描述
     */
    private String describe;
    /**
     * 类型
     */
    private Integer size;

    UploadEnum(String describe, Integer size) {
        this.describe = describe;
        this.size = size;
    }

    public String getDescribe() {
        return describe;
    }


    public Integer getSize() {
        return size;
    }

}
