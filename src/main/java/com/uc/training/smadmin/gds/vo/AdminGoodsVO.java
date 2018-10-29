package com.uc.training.smadmin.gds.vo;

import com.uc.training.smadmin.gds.re.AdminGoodsRE;

import java.io.Serializable;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/29
 */
public class AdminGoodsVO extends AdminGoodsRE implements Serializable{

    private static final long serialVersionUID = -8084737002616351560L;
    /**
     * 商品类型
     */
    private Integer categoryId;
    /**
     * 图片名称
     */
    private String picName;

    /**
     * 创建人
     */
    private Long createEmp;
    /**
     *修改人
     */
    private Long ModifyEmp;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public Long getCreateEmp() {
        return createEmp;
    }

    public void setCreateEmp(Long createEmp) {
        this.createEmp = createEmp;
    }

    public Long getModifyEmp() {
        return ModifyEmp;
    }

    public void setModifyEmp(Long modifyEmp) {
        ModifyEmp = modifyEmp;
    }
}
