package com.uc.training.smadmin.sys.model;

import com.uc.training.common.base.model.BaseModel;

import java.io.Serializable;
import java.util.Date;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/15
 * 说明：系统角色类
 */
public class SysRole extends BaseModel implements Serializable {
    private static final long serialVersionUID = -4494123606326332620L;
    /**
     *自增主键id
     */
    private Long id;

    /**
     *角色名字
     */
    private String name;

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

}
