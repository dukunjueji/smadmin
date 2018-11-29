package com.uc.training.base.bd.service;

import com.uc.training.smadmin.bd.model.Message;
import com.uc.training.smadmin.bd.re.MessageRE;
import com.uc.training.smadmin.bd.vo.MessageDetailVO;
import com.uc.training.smadmin.bd.vo.MessageListVO;

import java.util.List;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/24
 * 说明：
 */
public interface MessageService {
    /**
    *说明：查找指定会员未读消息总记录数
    *@param memberId 会员id
    *@return：java.lang.Integer 消息的数目
    *@throws：
    */
    Integer queryMessageCount(Long memberId);

    /**
     *说明：查找指定会员消息总记录数
     *@param memberId 会员id
     *@return：java.lang.Integer 消息的数目
     *@throws：
     */
    Integer queryAllMessageCount(Long memberId);

    /**
     * 查询指定会员的所有消息
     * @param messageListVO
     * @return 会员列表
     */
    List<MessageRE> queryMessageList(MessageListVO messageListVO);

    /**
    *说明：更新消息状态
    *@param message 消息
    *@return：int 更新返回状态
    *@throws：
    */
    int updateMessageStatus(Message message);

    /**
    *说明：保存消息
    *@param record 消息类
    *@return：java.lang.Long 影响数目
    *@throws：
    */
    Long insertMessage(Message record);

    /**
    *说明：通过消息id获取消息的详情
    *@param messageId
    *@return：com.uc.training.smadmin.bd.vo.MessageDetailVO
    *@throws：
    */
    MessageDetailVO queryOneMessageById(Long messageId);
}
