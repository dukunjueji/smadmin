package com.uc.training.smadmin.ord.dao;
import com.uc.training.smadmin.ord.model.OrderHistory;import java.util.List;
/**
 * @author kun.du01@ucarinc.com
 * @date 2018-10-17 星期三 16:28
 * @description:OrderHistoryDao数据库操作接口类
 */
public interface OrderHistoryDao{

	/**
	 * 通过主键来查找查找
	 */
	 public List<OrderHistory>  getOrderHistoryById(Long id);

	/**
	 * 查询列表
	 */
	 public List<OrderHistory>  queryOrderHistoryList();

	/**
	 * 查找数据总记录数
	 */
	public Integer queryOrderHistoryCount();

	/**
	 * 保存
	 */
	public Long insertOrderHistory(OrderHistory record);

	/**
	 * 更新
	 */
	public int updateOrderHistoryById(OrderHistory record);

}