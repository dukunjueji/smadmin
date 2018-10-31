package com.uc.training.smadmin.demo.vo;

import com.uc.training.common.bean.PageQuery;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
/**
 * validator验证测试请求vo
 *
 * @author 吴佰川（baichuan.wu@ucarinc.com）创建
 * @version 1.0
 * @date 2018/10/25 17:52
 */
public class DemoTestVO extends PageQuery {

    private static final long serialVersionUID = -384549370196291123L;
    @NotBlank(message = "姓名不能为空")
    private String name;
    @NotNull(message = "性别不能为空")
    private Integer sex;
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])[0-9]{8}$", message = "手机号格式不正确")
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

    @Override
    public String toString() {
        return super.toString();
    }
}
