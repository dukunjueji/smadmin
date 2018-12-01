package com.uc.training.base.sys.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author: 余旭东
 * @Date: 2018/11/30 14:07
 * @Description:
 */
public class MenuVO implements Serializable {
    private static final long serialVersionUID = 5134718564496072018L;
    /**
     * 自增主键id
     */
    private Long id;

    /**
     * 菜单名字
     */
    @NotBlank(message = "菜单名不能位空")
    @Length(max = 32, message = "菜单名长度不能超过32位")
    private String name;

    /**
     * 菜单路径
     */
    @NotBlank(message = "url不能为空")
    @Length(max = 32, message = "路径长度不能超过32位")
    private String url;

    /**
     * 标识
     */
    @NotBlank(message = "权限标识不能为空")
    @Length(max = 32, message = "权限标识长度不能超过32位")
    private String rel;

    /**
     * 排序
     */
    @NotNull
    @Max(value = 1000, message = "序号不能大于1000")
    @Min(value = 0, message = "序号不能小于0")
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

    @NotNull
    public Long getSortNum() {
        return sortNum;
    }

    public void setSortNum(@NotNull Long sortNum) {
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MenuVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", rel='" + rel + '\'' +
                ", sortNum=" + sortNum +
                ", level=" + level +
                ", parentId=" + parentId +
                ", type=" + type +
                '}';
    }
}
