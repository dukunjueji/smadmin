package com.uc.training.smadmin.ord.dao.impl;
import com.uc.training.smadmin.ord.model.Order;
import com.uc.training.smadmin.ord.dao.OrderDao;
import com.uc.training.smadmin.ord.re.OrderRe;
import com.uc.training.smadmin.ord.vo.OrdOrderVo;
import com.zuche.framework.dao.CarIsIbatisDaoImpl;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * @author kun.du01@ucarinc.com
 * @date 2018-10-17 星期三 16:28
 * @description:OrderDao数据库操作接口类
 */
@Repository
public class OrderDaoImpl extends CarIsIbatisDaoImpl implements OrderDao {

	 @Override
	 public List<Order> getOrderById(Long memberId){
		  return this.queryForList("com.uc.training.smadmin.ord.dao.OrderDao.getOrderById", memberId);
	 }

	 @Override
	 public List<Order>  queryOrderList(){
		  return this.queryForList("com.uc.training.smadmin.ord.dao.OrderDao.queryOrderList");
	 }

	 @Override
	public Integer queryOrderCount(Long memberId){
		  return (Integer) this.queryForObject("com.uc.training.smadmin.ord.dao.OrderDao.queryOrderCount", memberId);
	 }

	 @Override
	public Long insertOrder( Order record ){
		  return (Long) this.insert("com.uc.training.smadmin.ord.dao.OrderDao.insertOrder", record);
	 }

	 @Override
	public int updateOrderById( Order record ){
		  return this.update("com.uc.training.smadmin.ord.dao.OrderDao.updateOrderById", record);
	 }

	@Override
	public  List<OrderRe> getOrderPage(OrdOrderVo orderVo) {
		return this.queryForList("com.uc.training.smadmin.ord.dao.OrderDao.getOrderPage",orderVo,orderVo.getStartIndex(),orderVo.getEndIndex());
	}

  @Override
  public Integer getOrderTotal(OrdOrderVo orderVo) {
    return (Integer) this.queryForObject("com.uc.training.smadmin.ord.dao.OrderDao.OrderCount",orderVo);
  }

  @Override
  public int logicDelOrder(List<OrderRe> list) {

    return this.update("com.uc.training.smadmin.ord.dao.OrderDao.logicDelOrder",list);
  }

	@Override
	public int updateOrder(OrdOrderVo ordOrderVo) {
		return this.update("com.uc.training.smadmin.ord.dao.OrderDao.updateOrder",ordOrderVo);
	}
}