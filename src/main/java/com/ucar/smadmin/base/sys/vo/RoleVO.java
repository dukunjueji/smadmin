package com.ucar.smadmin.base.sys.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 余旭东
 * @Date: 2018/11/30 14:08
 * @Description:
 */
public class RoleVO implements Serializable {

    private static final long serialVersionUID = -4494123606326332620L;
    /**
     *自增主键id
     */
    private Long id;

    /**
     *角色名字
     */
    @NotBlank(message = "角色名不能为空")
    @Length(max = 32, message = "角色名不能超过32位")
    private String name;

    private Long createEmp;

    public Long getCreateEmp() {
        return createEmp;
    }

    public void setCreateEmp(Long createEmp) {
        this.createEmp = createEmp;
    }

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
        return "RoleVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
