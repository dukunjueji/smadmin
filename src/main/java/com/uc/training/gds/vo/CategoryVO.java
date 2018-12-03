package com.uc.training.gds.vo;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
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
    @NotNull(message = "排序号不能为空")
    private Integer sortNum;
    /**
     * 图片地址
     */
    private String imageUrl;

    /**
     * 父级id
     */
    private Long parentId;

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
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "CategoryVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sortNum=" + sortNum +
                ", imageUrl='" + imageUrl + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}
