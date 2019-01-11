package com.ucar.smadmin.common.mail.dto;

import java.io.Serializable;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2019/1/9
 */
public class MailMessage implements Serializable {

    private static final long serialVersionUID = -4883212339731104695L;
    /**
     * 邮件地址
     */
    private String mailAddress;
    /**
     * 邮件标题
     */
    private String title;
    /**
     * 邮件内容
     */
    private String content;

    @Override
    public String toString() {
        return "MailMessage{" +
                "mailAddress='" + mailAddress + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
