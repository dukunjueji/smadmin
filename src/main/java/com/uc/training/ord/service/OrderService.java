package com.uc.training.ord.service;



import com.uc.training.ord.re.CartGoodsRE;
import com.uc.training.ord.re.CommentRE;
import com.uc.training.ord.re.OrdOrderGoodsRE;
import com.uc.training.ord.re.OrderConfirmRE;
import com.uc.training.ord.re.OrderGoodsDetailRE;
import com.uc.training.ord.re.OrderInfoRE;
import com.uc.training.ord.re.OrderRE;
import com.uc.training.ord.re.OrderSaleRE;
import com.uc.training.ord.re.OrderStatusRE;
import com.uc.training.ord.vo.OrdCartGoodsVO;
import com.uc.training.ord.vo.OrdGoodsVO;
import com.uc.training.ord.vo.OrdMemberVO;
import com.uc.training.ord.vo.OrdOrderGoodsVO;
import com.uc.training.ord.vo.OrdOrderVO;

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
    List<CartGoodsRE> getCarGoodsById(Long memberId);

    /**
     * 根据用户ID和购物车商品表ID获取购物车商品信息
     *
     * @param ordGoodsVO
     * @return
     */
    List<CartGoodsRE> getCarGoodsByIds(OrdGoodsVO ordGoodsVO);

    /**
     * 根据用户id获取订单商品信息
     *
     * @param orderGodsList
     * @return
     */

    List<OrdOrderGoodsRE> getOrderGoodsById(List<OrdOrderGoodsVO> orderGodsList);

    /**
     * 通过会员id来查找
     *
     * @param ordMemberVO （订单表id）
     * @return
     */
    List<OrderRE> getOrderByMemberVO(OrdMemberVO ordMemberVO);


    /**
     * 加入购物车
     *
     * @param ordCartGoodsVO
     * @return
     */
    Integer addCarGoods(OrdCartGoodsVO ordCartGoodsVO);

    /**
     * 删除购物车商品
     *
     * @param ordCartGoodsVO
     * @return
     */
    Integer deleteCarGoods(OrdCartGoodsVO ordCartGoodsVO);

    /**
     * 提交订单信息
     *
     * @param orderInfoListNow
     * @return
     */
    List<OrderConfirmRE> confirmOrderInfo(List<OrdOrderGoodsVO> orderInfoListNow);


    /**
     * 更新购物车商品数量
     *
     * @param ordCartGoodsVO
     * @return返回影响条数
     */
    Integer updateCarGoodsNum(OrdCartGoodsVO ordCartGoodsVO);

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
     * @param ordOrderVO
     * @return
     */
    List<OrderRE> getOrderPage(OrdOrderVO ordOrderVO);

    /**
     * 获取总记录数
     *
     * @param ordOrderVO
     * @return
     */
    Integer getOrderTotal(OrdOrderVO ordOrderVO);

    /**
     * 获取订单枚举选项
     *
     * @return
     */
    List<OrderStatusRE> getOrderEnum();

    /**
     * 批量逻辑删除订单
     *
     * @param list
     * @return
     */
    Integer logicDelOrder(List<Long> list);

    /**
     * 更新订单状态
     *
     * @param ordOrderVO
     * @return int
     */
    int updateOrder(OrdOrderVO ordOrderVO);

    /**
     * 根据订单ID获取订单商品详情
     *
     * @param id
     * @return
     */
    List<OrderGoodsDetailRE> getOrderGdsById(Integer id);

    /**
     * 根据主键id获取手机号
     *
     * @param id
     * @return
     */
    String getTelephoneById(Long id);

    /**
     * 获取订单信息列表
     *
     * @param ordMemberVO
     * @return List<OrderInfoRE>(订单信息列表)
     */

    List<OrderInfoRE> getOrderInfoListByMemberId(OrdMemberVO ordMemberVO);

    /**
     * 获取订单商品列表(已生成订单)
     *
     * @param orderGodsList
     * @param orderId
     * @return
     */
    List<OrdOrderGoodsRE> getOrderGoods(List<OrdOrderGoodsVO> orderGodsList, Long orderId);

    /**
     * 当年每个月销售额
     *
     * @param
     * @return java.util.List<java.math.BigDecimal>
     * @version 1.0 2018/11/12 11:31 by 吴佰川（baichuan.wu@ucarinc.com）创建
     */
    OrderSaleRE getOrderSaleData();

    /**
     * hhj
     * 根据用户id 获取购物车商品表数据数量
     *
     * @param memberId
     * @return
     */
    Integer queryCartGoodsCount(Long memberId);

    /**
     * 根据用户ID 和 订单状态获取待评价商品详情
     *
     * @param ordGoodsVO
     * @return
     */
    List<CommentRE> getCommentOrderGoodsDetail(OrdGoodsVO ordGoodsVO);

    /**
     * 根据用户ID 获取待评价商品数量
     *
     * @param ordGoodsVO
     * @return
     */
    int getCommentGoodsCount(OrdGoodsVO ordGoodsVO);
}
