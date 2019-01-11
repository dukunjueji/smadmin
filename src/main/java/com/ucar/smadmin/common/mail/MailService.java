package com.ucar.smadmin.common.mail;

import com.ucar.smadmin.common.mail.dto.MailMessage;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2019/1/8
 */
public interface MailService {
    /**
     * 发送邮件
     * @param mailMessage
     */
    void sendSimpleMail(MailMessage mailMessage);
}
