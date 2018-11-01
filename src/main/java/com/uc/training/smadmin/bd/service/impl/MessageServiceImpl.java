package com.uc.training.smadmin.bd.service.impl;

import com.uc.training.smadmin.bd.dao.MessageDao;
import com.uc.training.smadmin.bd.model.Message;
import com.uc.training.smadmin.bd.re.MessageRE;
import com.uc.training.smadmin.bd.service.MessageService;
import com.uc.training.smadmin.bd.vo.MessageListVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private MessageDao messageDao;

    @Override
    public Integer queryMessageCount(Long memberId) {
        return this.messageDao.queryMessageCount(memberId);
    }

    @Override
    public Integer queryAllMessageCount(Long memberId) {
        return this.messageDao.queryAllMessageCount(memberId);
    }

    @Override
    public List<MessageRE> queryMessageList(MessageListVO messageListVO) {
        List<Message> messageList = this.messageDao.queryMessageList(messageListVO);
        List<MessageRE> messageListRE = new ArrayList<>();
        for (Message message: messageList){
            MessageRE messageRE = new MessageRE();
            messageRE.setId(message.getId());
            messageRE.setContent(message.getContent());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String createdate = sdf.format(message.getCreateTime());
            messageRE.setCreateTime(createdate);
            messageRE.setIsRead(message.getIsRead());
            messageRE.setTitle(StringUtils.substring(message.getContent(), 0, 5) + "....");
            messageListRE.add(messageRE);
        }
        return messageListRE;
    }

    @Override
    public int updateMessageStatus(Message message) {
        return this.messageDao.updateMessageStatus(message);
    }

    @Override
    public Long insertMessage(Message record) {
        return this.messageDao.insertMessage(record);
    }
}
