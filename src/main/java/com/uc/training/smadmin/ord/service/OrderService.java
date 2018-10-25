package com.uc.training.smadmin.ord.service;

import com.uc.training.smadmin.ord.model.CartGoods;
import com.uc.training.smadmin.ord.re.orderRe;
import com.uc.training.smadmin.ord.vo.OrdCartGoodsVo;
import com.uc.training.smadmin.ord.vo.OrdOrderGoodsVo;
import com.uc.training.smadmin.ord.vo.OrdOrderVo;

import java.util.List;

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
  void updateOrderInfo(List<OrdOrderGoodsVo> orderInfoListNow);


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
  List<orderRe> getOrderPage (OrdOrderVo orderVo);

  /**
   * 获取总记录数
   * @return
   */
  Integer getOrderTotal();
}
