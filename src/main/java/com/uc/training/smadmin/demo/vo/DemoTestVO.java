package com.uc.training.smadmin.demo.vo;

import com.uc.training.common.bean.PageQuery;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class DemoTestVO extends PageQuery {

    private static final long serialVersionUID = -384549370196291123L;
    @NotNull(message = "姓名不能为空")
    private String name;
    private Integer sex;
    @NotNull(message = "手机号不能为空")
    @Pattern(regexp = "^((13[0-9])|(15[^4,\\D])|(17[0,5-9])|(18[0,5-9])|(19[0,5-9]))\\d{8}$", message = "手机号格式不正确")
    private String cellphone;

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

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }
}
