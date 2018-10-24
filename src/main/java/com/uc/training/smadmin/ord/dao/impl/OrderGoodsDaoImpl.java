package com.uc.training.smadmin.ord.dao.impl;
import com.uc.training.smadmin.ord.model.OrderGoods;
import com.uc.training.smadmin.ord.dao.OrderGoodsDao;
import com.zuche.framework.dao.CarIsIbatisDaoImpl;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * @author kun.du01@ucarinc.com
 * @date 2018-10-17 星期三 16:28
 * @description:OrderGoodsDao数据库操作接口类
 */
@Repository
public class OrderGoodsDaoImpl extends CarIsIbatisDaoImpl implements OrderGoodsDao {

	/**
	 * 通过主键来查找查找
	 */
	 @Override
	 public List<OrderGoods> getOrderGoodsById(Long id){
		  return (List<OrderGoods>) this.queryForObject("com.uc.training.smadmin.ord.dao.OrderGoodsDao.getOrderGoodsById", id);
	 }

	/**
	 * 查询列表
	 */
	 @Override
	 public List<OrderGoods>  queryOrderGoodsList(){
		  return this.queryForList("com.uc.training.smadmin.ord.dao.OrderGoodsDao.queryOrderGoodsList");
	 }

	/**
	 * 查找数据总记录数
	 */
	 @Override
	public Integer queryOrderGoodsCount(){
		  return (Integer) this.queryForObject("com.uc.training.smadmin.ord.dao.OrderGoodsDao.queryOrderGoodsCount");
	 }

	/**
	 * 保存
	 */
	 @Override
	public Long insertOrderGoods( OrderGoods record ){
		  return (Long) this.insert("com.uc.training.smadmin.ord.dao.OrderGoodsDao.insertOrderGoods", record);
	 }

	/**
	 * 更新
	 */
	 @Override
	public int updateOrderGoodsById( OrderGoods record ){
		  return this.update("com.uc.training.smadmin.ord.dao.OrderGoodsDao.updateOrderGoodsById", record);
	 }

}