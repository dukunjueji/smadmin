package com.uc.training.base.sys.vo;

import com.uc.training.common.base.model.BaseModel;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * @Author: 余旭东
 * @Date: 2018/11/30 14:08
 * @Description:
 */
public class UserVO extends BaseModel implements Serializable {
    private static final long serialVersionUID = -5513069233470724453L;
    /**
     * 自增主键id
     */
    private Long id;

    /**
     * 用户名
     */
    @Length(max = 32, message = "用户名长度不能超过32位")
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 是否是超级管理员(0:否1：是)
     *
     */
    private Integer isAdmin;

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}

