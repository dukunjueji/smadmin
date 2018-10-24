package com.uc.training.smadmin.gds.re;

import com.uc.training.smadmin.gds.model.Category;

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
    private String label;
    /**
     * 级别
     */
    private Integer level;

    private Integer sortNum;

    /**
     * 父级id
     */
    private Long parentId;
    /**
     * 子级类别
     */
    private List<CategoryRE> children;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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
