package com.uc.training.smadmin.bd.dao.impl;
import com.uc.training.smadmin.bd.dao.MessageDao;
import com.uc.training.smadmin.bd.model.Message;
import com.uc.training.smadmin.bd.vo.MessageListVO;
import com.zuche.framework.dao.CarIsIbatisDaoImpl;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/24
 * 说明：用户接口
 */
@Repository
public class MessageDaoImpl extends CarIsIbatisDaoImpl implements MessageDao {

	/**
	 * 查找数据总记录数
	 */
	 @Override
	public Integer queryMessageCount(Long memberId){
		  return (Integer) this.queryForObject("com.uc.training.smadmin.bd.dao.MessageDao.queryMessageCount", memberId);
	 }

	@Override
	public Integer queryAllMessageCount(Long memberId) {
		return (Integer) this.queryForObject("com.uc.training.smadmin.bd.dao.MessageDao.queryAllMessageCount", memberId);
	}

	/**
	 * 保存
	 */
	 @Override
	public Long insertMessage( Message record ){
		  return (Long) this.insert("com.uc.training.smadmin.bd.dao.MessageDao.insertMessage", record);
	 }

	/**
	 * 更新
	 */
	 @Override
	public int updateMessageById( Message record ){
		  return this.update("com.uc.training.smadmin.bd.dao.MessageDao.updateMessageById", record);
	 }

	@Override
	public List<Message> queryMessageList(MessageListVO messageListVO) {
		return this.queryForList("com.uc.training.smadmin.bd.dao.MessageDao.queryMessageList", messageListVO,messageListVO.getStartIndex(),messageListVO.getEndIndex());
	}

	@Override
	public int updateMessageStatus(Message message) {
		return this.update("com.uc.training.smadmin.bd.dao.MessageDao.updateMessageStatus", message);
	}

	@Override
	public Message queryOneMessageById(Long messageId) {
		return (Message)this.queryForObject("com.uc.training.smadmin.bd.dao.MessageDao.queryOneMessageById", messageId);
	}

}