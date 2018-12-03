package com.uc.training.gds.vo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/31
 * 说明： 商品上架和下架
 */
public class AdminPullGoodsVO implements Serializable{

    private static final long serialVersionUID = 1092814357333489886L;
    /**
     * 商品id
     */
    @NotNull(message = "请选择商品!")
    private Long id;

    /**
     * 修改人
     */
    private Long modifyEmp;

    @Override
    public String toString() {
        return "AdminPullGoodsVO{" +
                "id=" + id +
                ", modifyEmp=" + modifyEmp +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getModifyEmp() {
        return modifyEmp;
    }

    public void setModifyEmp(Long modifyEmp) {
        this.modifyEmp = modifyEmp;
    }
}
