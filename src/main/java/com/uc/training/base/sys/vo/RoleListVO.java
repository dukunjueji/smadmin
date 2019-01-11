package com.uc.training.base.sys.vo;

import com.smgoods.common.bean.PageQuery;
import org.hibernate.validator.constraints.Length;

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
    @Length(max = 32, message = "角色名长度不能超过32位")
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

    @Override
    public String toString() {
        return "RoleListVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
