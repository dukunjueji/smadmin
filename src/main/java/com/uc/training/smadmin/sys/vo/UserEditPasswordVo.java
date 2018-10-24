package com.uc.training.smadmin.sys.vo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * 用户信息返回结果
 *
 * @author 吴佰川（baichuan.wu@ucarinc.com）
 * @version 1.0 2018/10/18 15:15 by 吴佰川（baichuan.wu@ucarinc.com）创建
 * @copyright All Rights Reserved.
 */
public class UserEditPasswordVo implements Serializable {

    private static final long serialVersionUID = -2956437397632428456L;
    @NotBlank(message = "原始密码不能为空")
    private String oldPwd;
    @NotBlank (message = "新的密码不能为空")
    @Size(min = 5, max = 16, message = "密码长度在5-16之间")
    private String newPwd;

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }
}
