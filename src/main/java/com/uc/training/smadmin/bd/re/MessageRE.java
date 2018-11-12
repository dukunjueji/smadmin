package com.uc.training.smadmin.bd.re;

import java.io.Serializable;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/25
 * 说明：响应的消息
 */
public class MessageRE implements Serializable {
    private static final long serialVersionUID = 1918964825196199569L;

    /**
     * 消息id
     */
    private Long id;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 是否已读
     */
    private Integer isRead;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 消息标题
     */
    private String title;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
