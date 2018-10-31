package com.uc.training.smadmin.sys.model;

import com.uc.training.common.base.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/15
 * 说明：系统菜单类
 */
public class SysMenu extends BaseModel implements Serializable {
    private static final long serialVersionUID = 5134718564496072018L;
    /**
     * 自增主键id
     */
    private Long id;

    /**
     * 菜单名字
     */
    private String name;

    /**
     * 菜单路径
     */
    private String url;

    /**
     * 标识
     */
    private String rel;

    /**
     * 排序
     */
    private Long sortNum;

    /**
     *级别
     */
    private Long level;

    /**
     * 父级
     */
    private Long parentId;

    /**
     * 菜单类型 1：目录   2：按钮
     */
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    private List<SysMenu> children;

    public List<SysMenu> getChildren() {
        return children;
    }

    public void setChildren(List<SysMenu> children) {
        this.children = children;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public Long getSortNum() {
        return sortNum;
    }

    public void setSortNum(Long sortNum) {
        this.sortNum = sortNum;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

}
