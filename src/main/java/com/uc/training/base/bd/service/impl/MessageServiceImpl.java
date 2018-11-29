package com.uc.training.base.bd.service.impl;

import com.uc.training.base.bd.dto.MessageDTO;
import com.uc.training.base.bd.service.MessageService;
import com.uc.training.remote.client.BaseClient;
import com.uc.training.smadmin.bd.dao.MessageDao;
import com.uc.training.smadmin.bd.model.Message;
import com.uc.training.smadmin.bd.re.MessageRE;
import com.uc.training.smadmin.bd.vo.MessageDetailVO;
import com.uc.training.smadmin.bd.vo.MessageListVO;
import com.uc.training.smadmin.utils.InjectionUtils;
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

    private static final Integer STARTNUM = 0;

    private static final Integer ENDNUM = 5;

    @Override
    public Integer queryMessageCount(MessageDTO messageDTO) {
        return BaseClient.queryMessageCount(messageDTO);
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
            messageRE.setTitle(StringUtils.substring(message.getContent(), STARTNUM, ENDNUM) + "....");
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
        this.messageDao = InjectionUtils.getInjectionInstance(MessageDao.class);
        return this.messageDao.insertMessage(record);
    }

    @Override
    public MessageDetailVO queryOneMessageById(Long messageId) {
        Message message = this.messageDao.queryOneMessageById(messageId);
        MessageDetailVO messageDetailVO = new MessageDetailVO();
        messageDetailVO.setContent(message.getContent());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        messageDetailVO.setCreateTime(simpleDateFormat.format(message.getCreateTime()));
        return messageDetailVO;
    }
}
