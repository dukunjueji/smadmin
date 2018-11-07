package com.uc.training.smadmin.gds.vo;

import javax.validation.constraints.NotNull;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/7
 */
public class AdminPropertyUpdateVO extends AdminPropertyVO {
    /**
     * 商品id
     */
    @NotNull(message = "请重新选择商品属性！")
    private Long id;

    @Override
    public String toString() {
        return "AdminPropertyUpdateVO{" +
                "id=" + id +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
