package com.uc.training.smadmin.demo.vo;

import com.uc.training.common.bean.PageQuery;

import javax.validation.constraints.NotNull;

public class DemoListVO extends PageQuery {

    private static final long serialVersionUID = -384549370196291123L;
    private String name;
    private Integer sex;


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
}
