package com.ucar.smadmin.common.mail.impl;

import com.ucar.smadmin.common.mail.MailService;
import com.ucar.smadmin.common.mail.dto.MailMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2019/1/8
 */
@Service
public class MailServiceImpl implements MailService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.fromMail.addr}")
    private String from;

    @Override
    public void sendSimpleMail(MailMessage mailMessage) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(mailMessage.getMailAddress());
        message.setSubject(mailMessage.getTitle());
        message.setText(mailMessage.getContent());

        try {
            mailSender.send(message);
            logger.info("邮件已经发送。");
        } catch (Exception e) {
            logger.error("发送邮件时发生异常！", e);
        }

    }
}
