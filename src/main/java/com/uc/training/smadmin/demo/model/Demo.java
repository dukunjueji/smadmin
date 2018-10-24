package com.uc.training.smadmin.demo.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @Copyright All Rights Reserved.
 * @version 1.0 2018/9/25 14:07 by 吴佰川（baichuan.wu@ucarinc.com）创建
 */
public class Demo implements Serializable {

    private Integer id;
    private String name;
    private Integer sex;
    private String remark;
    private Date createTime;
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
