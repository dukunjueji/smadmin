package com.uc.training.smadmin.bd.model;

import com.uc.training.common.base.model.BaseModel;


/**
 * @author 余旭东
 * @date Tue Oct 16 14:26:15 CST 2018
 * @description:
 */
public class Message extends BaseModel {


    private static final long serialVersionUID = 7752782242724170481L;


    /**
     * 自增ID
     */
    private Long id;

    /**
     * 接收会员ID
     */
    private Long memberId;

    /**
     * 内容
     */
    private String content;

    /**
     * 是否已读
     */
    private Integer isRead;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
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

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
