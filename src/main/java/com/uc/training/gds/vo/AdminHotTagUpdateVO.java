package com.uc.training.gds.vo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/6
 */
public class AdminHotTagUpdateVO extends AdminHotTagInsertVO implements Serializable {

    private static final long serialVersionUID = -4949963590990977030L;

    /**
     *标签id
     */
    @NotNull(message = "请重新修改!")
    private Long id;

    @Override
    public String toString() {
        return "AdminHotTagUpdateVO{" +
                "id=" + id +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
