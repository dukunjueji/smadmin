package com.uc.training.smadmin.ord.dao;
import com.uc.training.smadmin.ord.model.OrderRE;
import com.uc.training.smadmin.ord.vo.OrdMemberVO;
import com.uc.training.smadmin.ord.vo.OrdOrderVO;
import com.uc.training.smadmin.sys.re.OrderSaleRE;

import java.util.List;

/**
 * @author kun.du01@ucarinc.com
 * @date 2018-10-17 星期三 16:28
 * @description:OrderDao数据库操作接口类
 */
public interface OrderDao {

	/**
	 * 通过会员id来查找
	 * @param ordMemberVO （订单表id）
	 * @return
	 */
  List<com.uc.training.smadmin.ord.re.OrderRE> getOrderByMemberVO(OrdMemberVO ordMemberVO);

	/**
	 * 查询列表
	 * @return
	 */
  List<OrderRE> queryOrderList();

  /**
   * 查找指定会员订单总记录数
   *
   * @param memberId
   * @return
   */
  Integer queryOrderCount(Long memberId);

  /**
   * 保存
   *
   * @param record
   * @return
   */
  Long insertOrder(com.uc.training.smadmin.ord.re.OrderRE record);

  /**
   * 更新
   *
   * @param record
   * @return
   */
  int updateOrderById(com.uc.training.smadmin.ord.re.OrderRE record);

	/**
	 * 获取查询分页
	 * @param orderVo
	 * @return
	 */
  List<com.uc.training.smadmin.ord.re.OrderRE> getOrderPage(OrdOrderVO orderVo);

	/**
	 * 返回订单表的总记录数
	 * @param orderVo
	 * @return
	 */
  Integer getOrderTotal(OrdOrderVO orderVo);

	/**
	 * 逻辑删除订单
	 * @param list
	 * @return
	 */
  int logicDelOrder(List<Long> list);

    /**
     * 更新订单状态
     *
     * @param ordOrderVO
     * @return
     */
    int updateOrder(OrdOrderVO ordOrderVO);

    /**
     * 用户删除订单
     * @param ordOrderVO
     * @return
     */
    int memberDelOrder(OrdOrderVO ordOrderVO);

    /**
     *  根据主键id获取订单手机号
     * @param id
     * @return
     */
    String getTelephoneById(Long id);

    /**
     * 当年每个月销售额统计
     *
     * @version 1.0 2018/11/12 11:32 by 吴佰川（baichuan.wu@ucarinc.com）创建
     * @param
     * @return java.util.List<java.math.BigDecimal>
     */
	OrderSaleRE getOrderSaleData();
}
