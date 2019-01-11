package com.ucar.smadmin.base.bd.service;

import com.ucar.smadmin.base.bd.re.MessageRE;
import com.ucar.smadmin.base.bd.vo.MessageVO;

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
    *说明：查找消息数量
    *@param messageVO 会员id
    *@return：java.lang.Integer 消息的数目
    *@throws：
    */
    Integer queryMessageCount(MessageVO messageVO);

    /**
     * 查询指定会员的所有消息
     * @param messageVO
     * @return 会员列表
     */
    List<MessageRE> queryMessageList(MessageVO messageVO);

    /**
    *说明：更新消息状态
    *@param message 消息
    *@return：int 更新返回状态
    *@throws：
    */
    Integer updateMessage(MessageVO message);

    /**
    *说明：保存消息
    *@param record 消息类
    *@return：java.lang.Long 影响数目
    *@throws：
    */
    Long insertMessage(MessageVO record);

    /**
    *说明：通过消息id获取消息的详情
    *@param message
    *@return：com.uc.training.smadmin.bd.vo.MessageDetailVO
    *@throws：
    */
    MessageRE queryOneMessageById(MessageVO message);
}
