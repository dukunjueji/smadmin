package com.uc.training.smadmin.sys.vo;

import com.uc.training.common.bean.PageQuery;
import com.uc.training.common.vo.PageVO;

/**
 * @Author: 余旭东
 * @Date: 2018/10/30 17:48
 * @Description: 角色页面列表
 */
public class RoleListVO extends PageQuery {
    /**
     * 角色编号
     */
    private Long id;
    /**
     * 角色名称
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
