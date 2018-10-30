package com.uc.training.smadmin.ord.dao;
import com.uc.training.smadmin.ord.model.OrderGoods;import java.util.List;
/**
 * @author kun.du01@ucarinc.com
 * @date 2018-10-17 星期三 16:28
 * @description:OrderGoodsDao数据库操作接口类
 */
public interface OrderGoodsDao{

	/**
	 * 通过主键来查找
	 * @param id
	 * @return
	 */
	 public List<OrderGoods>  getOrderGoodsByOrderId(Integer id);

	/**
	 * 查询列表
	 * @return
	 */
	 public List<OrderGoods>  queryOrderGoodsList();

	/**
	 * 查找数据总记录数
	 * @return
	 */
	public Integer queryOrderGoodsCount();

	/**
	 * 保存
	 * @param record
	 * @return
	 */
	public Long insertOrderGoods(OrderGoods record);

	/**
	 * 更新
	 * @param record
	 * @return
	 */
	public int updateOrderGoodsById(OrderGoods record);


}