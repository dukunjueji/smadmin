package com.uc.training.smadmin.demo.model;

import java.io.Serializable;
import java.util.Date;

/**
 * demo测试实体
 *
 * @author 吴佰川（baichuan.wu@ucarinc.com）创建
 * @version 1.0
 * @date 2018/10/25 17:48
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
        return createTime == null ? null : (Date) createTime.clone();
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime == null ? null : (Date) createTime.clone();
    }

    public Date getUpdateTime() {
        return updateTime == null ? null : (Date) updateTime.clone();
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime == null ? null : (Date) updateTime.clone();
    }
}
