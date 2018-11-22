package com.uc.training.ord.service;


import com.uc.training.ord.dto.OrdCartGoodsDTO;
import com.uc.training.ord.dto.OrdGoodsDTO;
import com.uc.training.ord.dto.OrdMemberDTO;
import com.uc.training.ord.dto.OrdOrderDTO;
import com.uc.training.ord.dto.OrdOrderGoodsDTO;
import com.uc.training.ord.re.CartGoodsRE;
import com.uc.training.ord.re.OrderConfirmRE;
import com.uc.training.ord.re.OrderGoodsDetailRE;
import com.uc.training.ord.re.OrderInfoRE;
import com.uc.training.ord.re.OrderRE;
import com.uc.training.ord.re.OrderSaleRE;
import com.uc.training.ord.re.OrderStatusRE;

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
     * @param ordGoodsDTO
     * @return
     */
    List<CartGoodsRE> getCarGoodsByIds(OrdGoodsDTO ordGoodsDTO);

    /**
     * 根据用户id获取订单商品信息
     *
     * @param orderGodsList
     * @return
     */

    List<OrdOrderGoodsDTO> getOrderGoodsById(List<OrdOrderGoodsDTO> orderGodsList);

    /**
     * 通过会员id来查找
     *
     * @param ordMemberDTO （订单表id）
     * @return
     */
    List<OrderRE> getOrderByMemberDTO(OrdMemberDTO ordMemberDTO);


    /**
     * 加入购物车
     *
     * @param ordCartGoodsDTO
     * @return
     */
    Integer addCarGoods(OrdCartGoodsDTO ordCartGoodsDTO);

    /**
     * 删除购物车商品
     *
     * @param ordCartGoodsDTO
     * @return
     */
    Integer deleteCarGoods(OrdCartGoodsDTO ordCartGoodsDTO);

    /**
     * 提交订单信息
     *
     * @param orderInfoListNow
     * @return
     */
    List<OrderConfirmRE> confirmOrderInfo(List<OrdOrderGoodsDTO> orderInfoListNow);


    /**
     * 更新购物车商品数量
     *
     * @param ordCartGoodsDTO
     * @return 返回影响条数
     */
    int updateCarGoodsNum(OrdCartGoodsDTO ordCartGoodsDTO);

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
     * @param orderDTO
     * @return
     */
    List<OrderRE> getOrderPage(OrdOrderDTO orderDTO);

    /**
     * 获取总记录数
     *
     * @param orderDTO
     * @return
     */
    Integer getOrderTotal(OrdOrderDTO orderDTO);

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
    int logicDelOrder(List<Long> list);

    /**
     * 更新订单状态
     *
     * @param ordOrderDTO
     * @return int
     */
    int updateOrder(OrdOrderDTO ordOrderDTO);

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
     * @param ordMemberDTO
     * @return List<OrderInfoRE>(订单信息列表)
     */

    List<OrderInfoRE> getOrderInfoListByMemberId(OrdMemberDTO ordMemberDTO);

    /**
     * 获取订单商品列表(已生成订单)
     *
     * @param orderGodsList
     * @param orderId
     * @return
     */
    List<OrdOrderGoodsDTO> getOrderGoods(List<OrdOrderGoodsDTO> orderGodsList, Long orderId);

    /**
     * 当年每个月销售额
     *
     * @version 1.0 2018/11/12 11:31 by 吴佰川（baichuan.wu@ucarinc.com）创建
     * @param
     * @return java.util.List<java.math.BigDecimal>
     */
    OrderSaleRE getOrderSaleData();

    /**
     * hhj
     * 根据用户id 获取购物车商品表数据数量
     * @param memberId
     * @return
     */
    Integer queryCartGoodsCount(Long memberId);
}
