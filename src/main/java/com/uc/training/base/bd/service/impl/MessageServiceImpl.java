package com.uc.training.base.bd.service.impl;

import com.uc.training.base.bd.re.MessageRE;
import com.uc.training.base.bd.service.MessageService;
import com.uc.training.base.bd.vo.MessageVO;
import com.uc.training.remote.client.BaseClient;
import com.uc.training.smadmin.bd.dao.MessageDao;
import com.uc.training.smadmin.bd.model.Message;
import com.uc.training.smadmin.bd.re.MessageRE;
import com.uc.training.smadmin.bd.vo.MessageDetailVO;
import com.uc.training.smadmin.bd.vo.MessageListVO;
import com.uc.training.smadmin.utils.InjectionUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.displaytag.util.CollectionUtil;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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

    private static final Integer STARTNUM = 0;

    private static final Integer ENDNUM = 5;

    @Override
    public Integer queryMessageCount(MessageVO messageVO) {
        return BaseClient.queryMessageCount(messageVO);
    }

    @Override
    public Integer queryAllMessageCount(MessageVO messageVO) {
        return BaseClient.queryMemberCount(messageVO);
    }

    @Override
    public List<MessageRE> queryMessageList(MessageVO messageVO) {
        List<MessageRE> messageList = BaseClient.queryMessageList(messageVO);
        if (CollectionUtils.isEmpty(messageList)) {
            return null;
        }
        for (MessageRE message: messageList){
            message.setContent(StringUtils.substring(message.getContent(), STARTNUM, ENDNUM) + "....");
        }
        return messageList;
    }

    @Override
    public Integer updateMessage(MessageVO message) {
        return BaseClient.updateMessage(message);
    }

    @Override
    public Long insertMessage(MessageVO record) {
        this.messageDao = InjectionUtils.getInjectionInstance(MessageDao.class);
        return this.messageDao.insertMessage(record);
    }

    @Override
    public MessageRE queryOneMessageById(MessageVO messageVO) {
        return BaseClient.queryOneMessageById(messageVO);
    }
}
