package com.uc.training.base.bd.service.impl;

import com.uc.training.base.bd.dto.MessageDTO;
import com.uc.training.base.bd.re.MessageRE;
import com.uc.training.base.bd.service.MessageService;
import com.uc.training.base.bd.vo.MessageVO;
import com.uc.training.remote.client.BaseClient;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private BaseClient baseClient;
    private static final Integer STARTNUM = 0;

    private static final Integer ENDNUM = 5;

    @Override
    public Integer queryMessageCount(MessageVO messageVO) {
        MessageDTO messageDTO = new MessageDTO();
        BeanUtils.copyProperties(messageVO, messageDTO);
        return baseClient.queryMessageCount(messageDTO).getRe();
    }

    @Override
    public List<MessageRE> queryMessageList(MessageVO messageVO) {
        MessageDTO messageDTO = new MessageDTO();
        BeanUtils.copyProperties(messageVO, messageDTO);
        List<MessageRE> messageList = baseClient.queryMessageList(messageDTO).getRe();
        if (CollectionUtils.isEmpty(messageList)) {
            return null;
        }
        for (MessageRE message : messageList) {
            message.setContent(StringUtils.substring(message.getContent(), STARTNUM, ENDNUM) + "....");
        }
        return messageList;
    }

    @Override
    public Integer updateMessage(MessageVO message) {
        MessageDTO messageDTO = new MessageDTO();
        BeanUtils.copyProperties(message, messageDTO);
        return baseClient.updateMessage(messageDTO).getRe();
    }

    @Override
    public Long insertMessage(MessageVO record) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setMemberId(record.getMemberId());
        messageDTO.setContent(record.getContent());
        return baseClient.insertMessage(messageDTO).getRe();
    }

    @Override
    public MessageRE queryOneMessageById(MessageVO messageVO) {
        MessageDTO messageDTO = new MessageDTO();
        BeanUtils.copyProperties(messageVO, messageDTO);
        return baseClient.queryOneMessageById(messageDTO).getRe();
    }
}
