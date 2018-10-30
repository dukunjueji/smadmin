package com.uc.training.smadmin.bd.dao;
import com.uc.training.smadmin.bd.model.Message;

import java.util.List;
/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/24
 * 说明：消息接口
 */
public interface MessageDao{

	 /**
	 *说明：通过主键来查找查找
	 *@param id 消息id
	 *@return：java.util.List<com.uc.training.smadmin.bd.model.Message>
	 *@throws：
	 */
	 public List<Message>  getMessageById(Long id);

	 /**
	 *说明：查询消息列表
	 *@return：java.util.List<com.uc.training.smadmin.bd.model.Message>
	 *@throws：
	 */
	 public List<Message>  queryMessageList();

	/**
	*说明：查找指定会员消息总记录数
	*@param memberId 会员id
	*@return：java.lang.Integer
	*@throws：
	*/
	public Integer queryMessageCount(Long memberId);

	/**
	*说明：保存消息
	*@param record 消息类
	*@return：java.lang.Long
	*@throws：
	*/
	public Long insertMessage(Message record);

	/**
	*说明：更新消息
	*@param record 消息类
	*@return：int
	*@throws：
	*/
	public int updateMessageById(Message record);

	/**
	 * 查询所有消息
	 * @param uid 会员
	 * @return
	 */
	public List<Message> queryMessageList(Long uid);

	/**
	*说明：更新消息状态
	*@param message 消息
	*@return：int
	*@throws：
	*/
    int updateMessageStatus(Message message);


}