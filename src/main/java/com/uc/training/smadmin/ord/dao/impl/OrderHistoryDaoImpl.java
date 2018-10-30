package com.uc.training.smadmin.ord.dao.impl;
import com.uc.training.smadmin.ord.model.OrderHistory;
import com.uc.training.smadmin.ord.dao.OrderHistoryDao;
import com.zuche.framework.dao.CarIsIbatisDaoImpl;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * @author kun.du01@ucarinc.com
 * @date 2018-10-17 星期三 16:28
 * @description:OrderHistoryDao数据库操作接口类
 */
@Repository
public class OrderHistoryDaoImpl extends CarIsIbatisDaoImpl implements OrderHistoryDao {

	 @Override
	 public List<OrderHistory> getOrderHistoryById(Long id){
		  return (List<OrderHistory>) this.queryForObject("com.uc.training.smadmin.ord.dao.OrderHistoryDao.getOrderHistoryById", id);
	 }

	 @Override
	 public List<OrderHistory>  queryOrderHistoryList(){
		  return this.queryForList("com.uc.training.smadmin.ord.dao.OrderHistoryDao.queryOrderHistoryList");
	 }

	 @Override
	public Integer queryOrderHistoryCount(){
		  return (Integer) this.queryForObject("com.uc.training.smadmin.ord.dao.OrderHistoryDao.queryOrderHistoryCount");
	 }

	 @Override
	public Long insertOrderHistory( OrderHistory record ){
		  return (Long) this.insert("com.uc.training.smadmin.ord.dao.OrderHistoryDao.insertOrderHistory", record);
	 }

	 @Override
	public int updateOrderHistoryById( OrderHistory record ){
		  return this.update("com.uc.training.smadmin.ord.dao.OrderHistoryDao.updateOrderHistoryById", record);
	 }

}