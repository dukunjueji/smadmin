package com.uc.training.base.bd.vo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/25
 * 说明：
 */
public class MessageVO implements Serializable {

    private static final long serialVersionUID = 4049801712145458173L;

    /**
     * 消息id
     */
    @NotNull(message = "消息id不能为空")
    private Long id;

    /**
     * 消息状态
     */
    @NotNull(message = "消息状态不能为空")
    private Integer isRead;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    @Override
    public String toString() {
        return "MessageVO{" +
                "id=" + id +
                ", isRead=" + isRead +
                '}';
    }
}
