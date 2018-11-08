package com.uc.training.smadmin.ord.service;

import com.uc.training.smadmin.ord.model.CartGoods;
import com.uc.training.smadmin.ord.model.Order;
import com.uc.training.smadmin.ord.re.OrderConfirmRE;
import com.uc.training.smadmin.ord.re.OrderGoodsDetailRe;
import com.uc.training.smadmin.ord.re.OrderInfoRE;
import com.uc.training.smadmin.ord.re.OrderRe;
import com.uc.training.smadmin.ord.re.OrderStatusRe;
import com.uc.training.smadmin.ord.vo.OrdCartGoodsVo;
import com.uc.training.smadmin.ord.vo.OrdGoodsVO;
import com.uc.training.smadmin.ord.vo.OrdMemberVO;
import com.uc.training.smadmin.ord.vo.OrdOrderGoodsVo;
import com.uc.training.smadmin.ord.vo.OrdOrderVo;

import java.util.List;

/**
 * 订单服务
 *
 * @author DK
 * @date 2018/10/19
 */
public interface OrderService {
    /**
     * 根据用户id查询购物车信息表
     *
     * @param memberId
     * @return
     */
    List<CartGoods> getCarGoodsById(Long memberId);

  /**
   * 根据用户ID和购物车商品表ID获取购物车商品信息
   *
   * @param ordGoodsVO
   * @return
   */
  List<CartGoods> getCarGoodsByIds(OrdGoodsVO ordGoodsVO);

  /**
   * 根据用户id获取订单商品信息
   *
   * @param orderGodsList
   * @return
   */

    List<OrdOrderGoodsVo> getOrderGoodsById(List<OrdOrderGoodsVo> orderGodsList);

    /**
     * 通过会员id来查找
     *
     * @param ordMemberVO （订单表id）
     * @return
     */
    List<Order> getOrderById(OrdMemberVO ordMemberVO);


    /**
     * 加入购物车
     *
     * @param ordCartGoodsVo
     */
    void addCarGoods(OrdCartGoodsVo ordCartGoodsVo);

    /**
     * 删除购物车商品
     *
     * @param ordCartGoodsVo
     * @return
     */
    int deleteCarGoods(OrdCartGoodsVo ordCartGoodsVo);

    /**
     * 提交订单信息
     *
     * @param orderInfoListNow
     * @return
     */
    List<OrderConfirmRE> confirmOrderInfo(List<OrdOrderGoodsVo> orderInfoListNow);


    /**
     * 更新购物车商品数量
     *
     * @param ordCartGoodsVo
     * @return 返回影响条数
     */
    int updataCarGoodsNum(OrdCartGoodsVo ordCartGoodsVo);

    /**
     * 查找指定会员订单总记录数
     *
     * @param memberId 会员id
     * @return 所有订单数目
     */
    Integer queryOrderCount(Long memberId);

    /**
     * 获取订单查询分页
     *
     * @param orderVo
     * @return
     */
    List<OrderRe> getOrderPage(OrdOrderVo orderVo);

    /**
     * 获取总记录数
     *
     * @param orderVo
     * @return
     */
    Integer getOrderTotal(OrdOrderVo orderVo);

    /**
     * 获取订单枚举选项
     *
     * @return
     */
    List<OrderStatusRe> getOrderEnum();

    /**
     * 批量逻辑删除订单
     *
     * @param list
     * @return
     */
    int logicDelOrder(List<Long> list);

    /**
     * 更新订单状态
     *
     * @param ordOrderVo
     * @return int
     */
    int updateOrder(OrdOrderVo ordOrderVo);

    /**
     * 根据订单ID获取订单商品详情
     *
     * @param id
     * @return
     */
    List<OrderGoodsDetailRe> getOrderGdsById(Integer id);

    /**
     * 根据订单号获取手机号
     *
     * @param orderNum
     * @return
     */
    String getTelephoneByOrderNum(String orderNum);

    /**
     * 获取订单信息列表
     *
     * @param ordMemberVO
     * @return List<OrderInfoRE>(订单信息列表)
     */

    List<OrderInfoRE> getOrderInfoListByMemberId(OrdMemberVO ordMemberVO);
}
