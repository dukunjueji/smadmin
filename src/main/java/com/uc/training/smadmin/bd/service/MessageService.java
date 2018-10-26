package com.uc.training.smadmin.bd.service;

import com.uc.training.smadmin.bd.model.Message;
import com.uc.training.smadmin.bd.re.MessageRE;

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
     * 查找指定会员消息总记录数
     */
    public Integer queryMessageCount(Long memberId);

    /**
     * 查询指定会员的所有消息
     * @param uid 会员id
     * @return
     */
    public List<MessageRE> queryMessageList(Long uid);

    /**
    *说明：更新消息状态
    *@param message 消息
    *@return：int 更新返回状态
    *@throws：
    */
    int updateMessageStatus(Message message);
}
