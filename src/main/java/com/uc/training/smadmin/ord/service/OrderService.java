package com.uc.training.smadmin.ord.service;

import com.uc.training.smadmin.ord.re.OrderRe;
import com.uc.training.smadmin.ord.re.OrderStatusRe;
import com.uc.training.smadmin.ord.vo.OrdCartGoodsVo;
import com.uc.training.smadmin.ord.vo.OrdOrderGoodsVo;
import com.ycc.base.common.Result;
import com.uc.training.smadmin.ord.vo.OrdOrderVo;

import java.util.List;
import java.util.Map;

/**
 * 订单服务
 * @author DK
 * @date 2018/10/19
 */
public interface OrderService {
  /**
   * 根据用户id查询购物车信息表
   * @param memberId
   * @return
   */
  /**
   * 获取用户购物车商品信息
   * @param memberId
   * @return
   */
  List<OrdCartGoodsVo> getCarGoodsById(Long memberId);

  /**
   * 根据用户id获取订单商品信息
   * @param orderGodsList
   * @return
   */

  List<OrdOrderGoodsVo> getOrderGoodsById(List<OrdOrderGoodsVo> orderGodsList);


  /**
   * 加入购物车
   * @param ordCartGoodsVo
   */
  void addCarGoods(OrdCartGoodsVo ordCartGoodsVo);

  /**
   * 删除购物车商品
   */
  int deleteCarGoods(OrdCartGoodsVo ordCartGoodsVo);

  /**
   * 更新订单信息
   */
  List<OrderRe> updateOrderInfo(List<OrdOrderGoodsVo> orderInfoListNow);


  /**
   * 更新购物车商品数量
   * @param ordCartGoodsVo
   */
  int updataCarGoodsNum(OrdCartGoodsVo ordCartGoodsVo);

  /**
   * 查找指定会员订单总记录数
   * @param memberId 会员id
   * @return 所有订单数目
   */
  Integer queryOrderCount(Long memberId);

  /**
   * 获取订单查询分页
   * @param orderVo
   * @return
   */
  List<OrderRe> getOrderPage (OrdOrderVo orderVo);

  /**
   * 获取总记录数
   * @return
   */
  Integer getOrderTotal(OrdOrderVo orderVo);

  /**
   * 获取订单枚举选项
   */
  List<OrderStatusRe> getOrderEnum();

  /**
   * 逻辑删除订单
   * @param list
   * @return
   */
  int logicDelOrder (List<OrderRe> list);

  /**
   * 更新订单状态
   * @param ordOrderVo
   */
  void updateOrder(OrdOrderVo ordOrderVo);
}
