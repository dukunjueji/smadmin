package com.uc.training.smadmin.ord.dao;
import com.uc.training.smadmin.ord.model.Order;import java.util.List;
/**
 * @author kun.du01@ucarinc.com
 * @date 2018-10-17 星期三 16:28
 * @description:OrderDao数据库操作接口类
 */
public interface OrderDao{

	/**
	 * 通过主键来查找查找
	 */
	 public List<Order>  getOrderById(Long id);

	/**
	 * 查询列表
	 */
	 public List<Order>  queryOrderList();

	/**
	 * 查找指定会员订单总记录数
	 */
	public Integer queryOrderCount(Long memberId);

	/**
	 * 保存
	 */
	public Long insertOrder(Order record);

	/**
	 * 更新
	 */
	public int updateOrderById(Order record);

}