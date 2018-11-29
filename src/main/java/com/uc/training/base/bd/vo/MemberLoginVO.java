package com.uc.training.base.bd.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author 吴佰川（baichuan.wu@ucarinc.com）
 * @version 1.0 2018/10/23 13:58 by 吴佰川（baichuan.wu@ucarinc.com）创建
 * @copyright All Rights Reserved.
 */
public class MemberLoginVO implements Serializable {
    private static final long serialVersionUID = 5208446196575065858L;
    /**
     * 会员电话
     */
    @NotBlank(message = "手机号码不能为空")
    @Pattern(regexp = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])[0-9]{8}$", message = "手机号格式不正确")
    private String telephone;
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 10, message = "密码长度须在6-10位之间")
    private String password;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "MemberLoginVO{" +
                "telephone='" + telephone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
