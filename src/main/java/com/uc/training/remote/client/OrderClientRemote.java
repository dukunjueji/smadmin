package com.uc.training.remote.client;

import com.uc.training.common.vo.RemoteResult;
import com.uc.training.ord.dto.OrdCartGoodsDTO;
import com.uc.training.ord.dto.OrdGoodsDTO;
import com.uc.training.ord.dto.OrdMemberDTO;
import com.uc.training.ord.dto.OrdOrderDTO;
import com.uc.training.ord.dto.OrdOrderGoodsDTO;
import com.uc.training.ord.dto.OrderDTO;
import com.uc.training.ord.dto.OrderGoodsDTO;
import com.uc.training.ord.re.CartGoodsRE;
import com.uc.training.ord.re.CommentRE;
import com.uc.training.ord.re.OrdOrderGoodsRE;
import com.uc.training.ord.re.OrderGoodsRE;
import com.uc.training.ord.re.OrderRE;
import com.uc.training.ord.re.OrderSaleRE;
import com.uc.training.remote.client.fallback.BaseClientFallback;
import com.uc.training.remote.client.fallback.OrderClientFallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * description: TODO
 *
 * @author 黄宏俊（hongjun.huang01@ucarinc.com）
 * @version 1.0
 * @date 2019/1/4  17:54
 */
@FeignClient(name = "provider_goods",fallback = OrderClientFallback.class)
public interface OrderClientRemote {

    /**
     * 根据用户ID和购物车商品表ID获取购物车商品信息
     *
     * @param ordGoodsDTO
     * @return
     */
    @PostMapping(value = "order/getCarGoodsByIds")
    RemoteResult<List<CartGoodsRE>> getCarGoodsByIds(OrdGoodsDTO ordGoodsDTO);
    /**
     * 根据用户ID获取购物车商品信息
     *
     * @param memberId
     * @return
     */
    @PostMapping(value = "order/getCarGoodsById")
    RemoteResult<List<CartGoodsRE>> getCarGoodsById(Long memberId);

    /**
     * 根据用户id获取订单商品信息
     *
     * @param orderGodsList
     * @return
     */
    @PostMapping(value = "order/getOrderGoodsById")
    RemoteResult<List<OrdOrderGoodsRE>> getOrderGoodsById(List<OrdOrderGoodsDTO> orderGodsList);

    /**
     * 通过会员信息或者订单信息来查找订单信息
     *
     * @param ordMemberDTO （订单表id）
     * @return
     */
    @PostMapping(value = "order/getOrderByMemberDTO")
    RemoteResult<List<OrderRE>> getOrderByMemberDTO(OrdMemberDTO ordMemberDTO);


    /**
     * 加入购物车
     *
     * @param ordCartGoodsDTO
     * @return
     */
    @PostMapping(value = "order/addCarGoods")
    RemoteResult<Integer> addCarGoods(OrdCartGoodsDTO ordCartGoodsDTO);

    /**
     * 删除购物车商品
     *
     * @param ordCartGoodsDTO
     * @return
     */
    @PostMapping(value = "order/deleteCarGoods")
    RemoteResult<Integer> deleteCarGoods(OrdCartGoodsDTO ordCartGoodsDTO);

    /**
     * 更新购物车商品数量
     *
     * @param ordCartGoodsDTO
     * @return 返回影响条数
     */
    @PostMapping(value = "order/updateCarGoodsNum")
    RemoteResult<Integer> updateCarGoodsNum(OrdCartGoodsDTO ordCartGoodsDTO);

    /**
     * 查找指定会员订单总记录数
     *
     * @param memberId 会员id
     * @return 所有订单数目
     */
    @PostMapping(value = "order/queryOrderCount")
    RemoteResult<Integer> queryOrderCount(Long memberId);


    /**
     * 获取订单查询分页
     *
     * @param orderDTO
     * @return
     */
    @PostMapping(value = "order/getOrderPage")
    RemoteResult<List<OrderRE>> getOrderPage(OrdOrderDTO orderDTO);

    /**
     * 获取总记录数
     *
     * @param orderDTO
     * @return
     */
    @PostMapping(value = "order/getOrderTotal")
    RemoteResult<Integer> getOrderTotal(OrdOrderDTO orderDTO);

    /**
     * 批量逻辑删除订单
     *
     * @param list
     * @return
     */
    @PostMapping(value = "order/logicDelOrder")
    RemoteResult<Integer> logicDelOrder(List<Long> list);

    /**
     * 更新订单状态
     *
     * @param ordOrderDTO
     * @return int
     */
    @PostMapping(value = "order/updateOrder")
    RemoteResult<Integer> updateOrder(OrdOrderDTO ordOrderDTO);

    /**
     * 根据主键id获取手机号
     *
     * @param id
     * @return
     */
    @PostMapping(value = "order/getTelephoneById")
    RemoteResult<String> getTelephoneById(Long id);


    /**
     * 当年每个月销售额
     *
     * @param
     * @return java.util.List<java.math.BigDecimal>
     * @version 1.0 2018/11/12 11:31 by 吴佰川（baichuan.wu@ucarinc.com）创建
     */
    @PostMapping(value = "order/getOrderSaleData")
    RemoteResult<OrderSaleRE> getOrderSaleData();

    /**
     * hhj
     * 根据用户id 获取购物车商品表数据数量
     *
     * @param memberId
     * @return
     */
    @PostMapping(value = "order/queryCartGoodsCount")
    RemoteResult<Integer> queryCartGoodsCount(Long memberId);

    /**
     * hhj
     * 根据订单id订单商品信息
     *
     * @param orderId
     * @return
     */
    @PostMapping(value = "order/getOrderGoodsByOrderId")
    RemoteResult<List<OrderGoodsRE>> getOrderGoodsByOrderId(Integer orderId);

    /**
     * 插入订单并返回订单id
     *
     * @param orderDTO
     * @return
     */
    @PostMapping(value = "order/insertOrder")
    RemoteResult<Long> insertOrder(OrderDTO orderDTO);


    /**
     * 插入订单商品信息
     *
     * @param orderGoodsDTO
     * @return
     */
    @PostMapping(value = "order/insertOrderGoods")
    RemoteResult<Long> insertOrderGoods(OrderGoodsDTO orderGoodsDTO);

    /**
     * 逻辑删除订单
     *
     * @param ordOrderDTO
     * @return
     */
    @PostMapping(value = "order/memberDelOrder")
    RemoteResult<Integer> memberDelOrder(OrdOrderDTO ordOrderDTO);


    /**
     * 根据用户ID 和 订单状态获取待评价商品详情
     *
     * @param ordGoodsDTO
     * @return
     */
    @PostMapping(value = "order/getPropertyIdListByUid")
    RemoteResult<List<CommentRE>> getPropertyIdListByUid(OrdGoodsDTO ordGoodsDTO);

    /**
     * 通过商品属性id获取待支付的商品属性数量
     *
     * @param propertyId
     * @return
     */
    @PostMapping(value = "order/getUnPayGoodsPropertyCountByPropertyId")
    RemoteResult<Integer> getUnPayGoodsPropertyCountByPropertyId(Long propertyId);

    /**
     * 根据id 更改订单商品评论状态
     *
     * @param ordOrderGoodsDTO
     * @return
     */
    @PostMapping(value = "order/upOrdGoodsCommentStatus")
    RemoteResult<Integer> upOrdGoodsCommentStatus(OrdOrderGoodsDTO ordOrderGoodsDTO);

    /**
     * 根据订单商品表Id获取订单表信息
     *
     * @param ordGoodsId
     * @return
     */
    @PostMapping(value = "order/getOrderByOrdGoodsId")
    RemoteResult<OrderRE> getOrderByOrdGoodsId(Long ordGoodsId);
}


