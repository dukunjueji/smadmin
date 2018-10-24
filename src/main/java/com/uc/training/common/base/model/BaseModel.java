package com.uc.training.common.base.model;


import java.io.Serializable;
import java.util.Date;

public abstract class BaseModel implements Serializable {
    /**
     * 创建人
     **/
    private Long createEmp;

    /**
     * 创建时间
     **/
    private Date createTime;

    /**
     * 修改人
     **/
    private Long modifyEmp;

    /**
     * 创建时间
     **/
    private Date modifyTime;

    public Long getCreateEmp() {
        return createEmp;
    }

    public void setCreateEmp(Long createEmp) {
        this.createEmp = createEmp;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getModifyEmp() {
        return modifyEmp;
    }

    public void setModifyEmp(Long modifyEmp) {
        this.modifyEmp = modifyEmp;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
