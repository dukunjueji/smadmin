package com.uc.training.smadmin.gds.vo;

import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/25
 */
public class CategoryVO implements Serializable{

    private static final long serialVersionUID = -842880782486785731L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 分类名称
     */
    @NotBlank(message = "名称不能为空")
    private String name;
    /**
     * 排序号
     */
    private Integer sortNum;
    /**
     * 图片地址
     */
    private String ImageUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}
