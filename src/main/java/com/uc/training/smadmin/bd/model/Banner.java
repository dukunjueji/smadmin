package com.uc.training.smadmin.bd.model;

import com.uc.training.common.base.model.BaseModel;

import java.io.Serializable;

/**
 * @author qi.he@ucarinc.com
 * @date 2018-10-17 03:40
 * @description:广告表
 */
public class Banner extends BaseModel implements Serializable {

    private static final long serialVersionUID = 4444735858979083691L;

    /**
     * 自增主键
     **/
    private Long id;

    /**
     * 广告名称
     **/
    private String name;

    /**
     * 广告类型1首页
     **/
    private Integer type;

    /**
     * 是否前台显示，1：是，0:否
     **/
    private Integer isShow;
    /**
     * 排序
     **/
    private Integer sortNum;

    /**
     * 广告图片
     **/
    private String imageUrl;

    /**
     * 广告地址
     **/
    private String url;

    /**
     * 点击次数
     **/
    private Integer clicks;

    /**
     * 描述
     **/
    private String description;

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return this.type;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public Integer getSortNum() {
        return this.sortNum;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }

    public Integer getClicks() {
        return this.clicks;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

}
