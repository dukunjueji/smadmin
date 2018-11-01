package com.uc.training.smadmin.bd.re;

import com.uc.training.smadmin.bd.model.Banner;

import java.io.Serializable;
import java.util.Date;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/26
 */
public class AdminBannerListRE implements Serializable{

    private static final long serialVersionUID = 4569316691606285619L;

    /**自增主键**/
    private Long id;

    /**广告名称**/
    private String name;

    /**广告类型1首页**/
    private Integer type;

    /**是否前台显示，1：是，0:否**/
    private  Integer isShow;

    /**排序**/
    private Integer sortNum;

    /**广告图片**/
    private String imageUrl;

    /**广告地址**/
    private String url;

    /**点击次数**/
    private Integer clicks;

    /**创建时间**/
    private Date createTime;

    /**修改时间**/
    private Date modifyTime;

    /**描述**/
    private String description;

    @Override
    public String toString() {
        return "AdminBannerListRE{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", isShow=" + isShow +
                ", sortNum=" + sortNum +
                ", imageUrl='" + imageUrl + '\'' +
                ", url='" + url + '\'' +
                ", clicks=" + clicks +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", description='" + description + '\'' +
                '}';
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getClicks() {
        return clicks;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
