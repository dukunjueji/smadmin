package com.uc.training.smadmin.gds.re;

import java.io.Serializable;
import java.util.List;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/24
 */
public class CategoryRE implements Serializable{

    private static final long serialVersionUID = 3299031689826073976L;
    /**
     * 类别id
     */
    private Long id;

    /**
     * 类别名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer sortNum;

    /**
     * 父级id
     */
    private Long parentId;

    /**
     * 图片地址
     */
    private String imageUrl;
    /**
     * 子级
     */
    private List<CategoryRE> children;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<CategoryRE> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryRE> children) {
        this.children = children;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }
}
