package com.uc.training.smadmin.gds.re;

import java.io.Serializable;
import java.util.Date;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/6
 */
public class AdminHotTagListRE implements Serializable {

    private static final long serialVersionUID = 1224774632242641821L;

    /**自增主键**/
    private Long id;

    /**标签名称**/
    private String tag;

    /**排序号**/
    private Integer sortNum;

    /**创建时间**/
    private Date createTime;

    /**修改时间**/
    private Date modifyTime;

    @Override
    public String toString() {
        return "AdminHotTagListRE{" +
                "id=" + id +
                ", tag='" + tag + '\'' +
                ", sortNum=" + sortNum +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
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
}
