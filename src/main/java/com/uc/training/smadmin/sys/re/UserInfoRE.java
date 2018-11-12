package com.uc.training.smadmin.sys.re;

import java.io.Serializable;
import java.util.List;

/**
 * 用户信息返回结果
 *
 * @author 吴佰川（baichuan.wu@ucarinc.com）
 * @version 1.0 2018/10/18 15:15 by 吴佰川（baichuan.wu@ucarinc.com）创建
 * @copyright All Rights Reserved.
 */
public class UserInfoRE implements Serializable {

    private static final long serialVersionUID = 2609363435557801412L;
    /**
     * 用户名
     */
    private String username;
    /**
     * 是否超级管理员
     */
    private Boolean admin;
    /**
     * 角色列表
     */
    private List<String> roles;
    /**
     * 权限列表
     */
    private List<String> perms;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getPerms() {
        return perms;
    }

    public void setPerms(List<String> perms) {
        this.perms = perms;
    }
}
