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
	 * 通过主键来查找查找
	 */
	 public List<Message>  getMessageById(Long id);

	/**
	 * 查询列表
	 */
	 public List<Message>  queryMessageList();

	/**
	 * 查找指定会员消息总记录数
	 */
	public Integer queryMessageCount(Long memberId);

	/**
	 * 保存
	 */
	public Long insertMessage(Message record);

	/**
	 * 更新
	 */
	public int updateMessageById(Message record);

}