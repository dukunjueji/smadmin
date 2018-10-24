package com.uc.training.smadmin.bd.dao.impl;
import com.uc.training.smadmin.bd.dao.MessageDao;
import com.uc.training.smadmin.bd.model.Message;
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
	 * 通过主键来查找查找
	 */
	 @Override
	 public List<Message> getMessageById(Long id){
		  return (List<Message>) this.queryForObject("com.uc.training.smadmin.bd.dao.MessageDao.getMessageById", id);
	 }

	/**
	 * 查询列表
	 */
	 @Override
	 public List<Message>  queryMessageList(){
		  return this.queryForList("com.uc.training.smadmin.bd.dao.MessageDao.queryMessageList");
	 }

	/**
	 * 查找数据总记录数
	 */
	 @Override
	public Integer queryMessageCount(Long memberId){
		  return (Integer) this.queryForObject("com.uc.training.smadmin.bd.dao.MessageDao.queryMessageCount", memberId);
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

}