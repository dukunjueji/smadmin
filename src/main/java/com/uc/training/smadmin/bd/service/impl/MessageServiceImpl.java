package com.uc.training.smadmin.bd.service.impl;

import com.uc.training.smadmin.bd.dao.MessageDao;
import com.uc.training.smadmin.bd.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/24
 * 说明：
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Override
    public Integer queryMessageCount(Long memberId) {
        return this.messageDao.queryMessageCount(memberId);
    }
}
