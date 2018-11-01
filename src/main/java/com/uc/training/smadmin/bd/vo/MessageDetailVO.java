package com.uc.training.smadmin.bd.vo;

import java.io.Serializable;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/11/1
 * 说明：响应消息详情
 */
public class MessageDetailVO implements Serializable {
    private static final long serialVersionUID = -4638924453475197169L;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 消息内容
     */
    private String content;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
