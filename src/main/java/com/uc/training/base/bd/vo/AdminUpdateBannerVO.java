package com.uc.training.base.bd.vo;

import javax.validation.constraints.NotNull;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/1
 */
public class AdminUpdateBannerVO extends AdminInsertBannerVO {
    /**
     * 主键id
     */
    @NotNull(message = "请重新修改")
    private Long id;

    @Override
    public String toString() {
        return "AdminUpdateBannerVO{" +
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
