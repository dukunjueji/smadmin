package com.uc.training.common.base.model;


import java.io.Serializable;
import java.util.Date;
/**
 * 基类model
 *
 * @author 吴佰川（baichuan.wu@ucarinc.com）创建
 * @date 2018/10/25 17:47
 * @version 1.0 2018/10/25 17:47
 */
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
        return createTime == null ? null : (Date) createTime.clone();
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime == null ? null : (Date) createTime.clone();
    }

    public Long getModifyEmp() {
        return modifyEmp;
    }

    public void setModifyEmp(Long modifyEmp) {
        this.modifyEmp = modifyEmp;
    }

    public Date getModifyTime() {
        return modifyTime == null ? null : (Date) modifyTime.clone();
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime == null ? null : (Date) modifyTime.clone();
    }
}
