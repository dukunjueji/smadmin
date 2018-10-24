package com.uc.training.smadmin.demo.re;

import java.io.Serializable;
import java.util.Date;

public class DemoRE implements Serializable {

    private static final long serialVersionUID = -6912839796213052826L;
    private Integer id;
    private String name;
    private Integer sex;
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
