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
    private String username;
    private String avatar;
    private List<String> roles;
    private List<String> perms;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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
