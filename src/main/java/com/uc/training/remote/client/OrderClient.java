package com.uc.training.remote.client;

import com.uc.training.ord.dto.OrdCartGoodsDTO;
import com.uc.training.ord.dto.OrdGoodsDTO;
import com.uc.training.ord.dto.OrdMemberDTO;
import com.uc.training.ord.dto.OrdOrderDTO;
import com.uc.training.ord.dto.OrdOrderGoodsDTO;
import com.uc.training.ord.dto.OrderDTO;
import com.uc.training.ord.dto.OrderGoodsDTO;
import com.uc.training.ord.re.CartGoodsRE;
import com.uc.training.ord.re.CommentRE;
import com.uc.training.ord.re.OrderGoodsRE;
import com.uc.training.ord.re.OrderRE;
import com.uc.training.ord.re.OrderSaleRE;
import com.uc.training.ord.vo.OrdCartGoodsVO;
import com.uc.training.ord.vo.OrdGoodsVO;
import com.uc.training.ord.vo.OrdMemberVO;
import com.uc.training.ord.vo.OrdOrderGoodsVO;
import com.uc.training.ord.vo.OrdOrderVO;
import com.uc.training.ord.vo.OrderGoodsVO;
import com.uc.training.ord.vo.OrderVO;
import com.uc.training.remote.remoteclient.OrderClientRemote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/22
 */
public final class OrderClient {

    private final Logger LOGGER = LoggerFactory.getLogger(OrderClient.class.getName());
    @Autowired
    OrderClientRemote orderClientRemote;


    /**
     * 根据用户id查询购物车信息表
     *
     * @return
     */
    public List<CartGoodsRE> getCarGoodsById(Long memberId) {
        try {
            List<CartGoodsRE> list = orderClientRemote.getCarGoodsById(memberId).getRe();
            return list;
        } catch (ClassCastException e) {
            LOGGER.error("类型转换异常");
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * 更新购物车商品数量
     */
    public Integer updateCarGoodsNum(OrdCartGoodsVO ordCartGoodsVO) {
        OrdCartGoodsDTO ordCartGoodsDTO = new OrdCartGoodsDTO();
        BeanUtils.copyProperties(ordCartGoodsVO, ordCartGoodsDTO);
        try {
            return orderClientRemote.updateCarGoodsNum(ordCartGoodsDTO).getRe();
        } catch (ClassCastException e) {
            LOGGER.error("类型转换异常");
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * 根据用户ID和购物车商品表ID获取购物车商品信息
     */
    public List<CartGoodsRE> getCarGoodsByIds(OrdGoodsVO ordGoodsVO) {
        OrdGoodsDTO ordGoodsDTO = new OrdGoodsDTO();
        BeanUtils.copyProperties(ordGoodsVO, ordGoodsDTO);
        try {
            return orderClientRemote.getCarGoodsByIds(ordGoodsDTO).getRe();
        } catch (ClassCastException e) {
            LOGGER.error("数据类型转换异常");
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * 根据订单id 获取购物车商品表数据
     */
    public List<OrderGoodsRE> getOrderGoodsByOrderId(Integer orderId) {
        try {
            return orderClientRemote.getOrderGoodsByOrderId(orderId).getRe();
        } catch (ClassCastException e) {
            LOGGER.error("类型转换异常");
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * 加入购物车
     */
    public Integer addCarGoods(OrdCartGoodsVO ordCartGoodsVO) {
        OrdCartGoodsDTO ordCartGoodsDTO = new OrdCartGoodsDTO();
        BeanUtils.copyProperties(ordCartGoodsVO, ordCartGoodsDTO);
        try {
            return orderClientRemote.addCarGoods(ordCartGoodsDTO).getRe();
        } catch (ClassCastException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 删除购物车
     */
    public Integer deleteCarGoods(OrdCartGoodsVO ordCartGoodsVO) {
        OrdCartGoodsDTO ordCartGoodsDTO = new OrdCartGoodsDTO();
        BeanUtils.copyProperties(ordCartGoodsVO, ordCartGoodsDTO);
        try {
            return orderClientRemote.deleteCarGoods(ordCartGoodsDTO).getRe();
        } catch (ClassCastException e) {
            LOGGER.error("数据类型转换异常");
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * 插入订单信息并返回订单id
     */
    public Long insertOrder(OrderVO orderVO) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderVO, orderDTO);
        try {
            return orderClientRemote.insertOrder(orderDTO).getRe();
        } catch (ClassCastException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 插入订单商品信息并返回订单id
     */
    public Long insertOrderGoods(OrderGoodsVO orderGoodsVO) {
        OrderGoodsDTO orderGoodsDTO = new OrderGoodsDTO();
        BeanUtils.copyProperties(orderGoodsVO, orderGoodsDTO);
        try {
            return orderClientRemote.insertOrderGoods(orderGoodsDTO).getRe();
        } catch (ClassCastException e) {
            LOGGER.error("数据类型转换异常");
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * 获取订单查询分页
     */
    public List<OrderRE> getOrderPage(OrdOrderVO ordOrderVO) {
        OrdOrderDTO ordOrderDTO = new OrdOrderDTO();
        BeanUtils.copyProperties(ordOrderVO, ordOrderDTO);
        try {
            return orderClientRemote.getOrderPage(ordOrderDTO).getRe();
        } catch (ClassCastException e) {
            LOGGER.error("数据类型转换异常");
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * 获取总记录数
     */
    public Integer getOrderTotal(OrdOrderVO ordOrderVO) {
        OrdOrderDTO ordOrderDTO = new OrdOrderDTO();
        BeanUtils.copyProperties(ordOrderVO, ordOrderDTO);
        try {
            return orderClientRemote.getOrderTotal(ordOrderDTO).getRe();
        } catch (ClassCastException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("数据类型转换异常");
        }
        return null;
    }

    /**
     * 批量逻辑删除订单
     */
    public Integer logicDelOrder(List<Long> list) {
        try {
            return orderClientRemote.logicDelOrder(list).getRe();
        } catch (ClassCastException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("结果转换异常");
        }
        return null;
    }

    /**
     * 通过会员信息或者订单信息来查找订单信息
     */
    public List<OrderRE> getOrderByMemberVO(OrdMemberVO ordMemberVO) {
        try {
            OrdMemberDTO ordMemberDTO = new OrdMemberDTO();
            BeanUtils.copyProperties(ordMemberVO, ordMemberDTO);
            return orderClientRemote.getOrderByMemberDTO(ordMemberDTO).getRe();
        } catch (ClassCastException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 更新订单状态
     */
    public Integer updateOrder(OrdOrderVO ordOrderVO) {
        try {
            OrdOrderDTO ordOrderDTO = new OrdOrderDTO();
            BeanUtils.copyProperties(ordOrderVO, ordOrderDTO);
            return orderClientRemote.updateOrder(ordOrderDTO).getRe();
        } catch (ClassCastException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 更新订单状态
     */
    public String getTelephoneById(Long id) {
        try {
            return orderClientRemote.getTelephoneById(id).getRe();
        } catch (ClassCastException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 当年每个月销售额
     */
    public OrderSaleRE getOrderSaleData() {
        try {
            return orderClientRemote.getOrderSaleData().getRe();
        } catch (ClassCastException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 根据用户id 获取购物车商品表数据数量
     */
    public Integer queryCartGoodsCount(Long memberId) {
        try {
            return orderClientRemote.queryCartGoodsCount(memberId).getRe();
        } catch (ClassCastException e) {
            LOGGER.error("Integer类型转换异常");
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * 逻辑删除订单
     */
    public Integer memberDelOrder(OrdOrderVO ordOrderVO) {
        OrdOrderDTO ordOrderDTO = new OrdOrderDTO();
        BeanUtils.copyProperties(ordOrderVO, ordOrderDTO);
        try {
            return orderClientRemote.memberDelOrder(ordOrderDTO).getRe();
        } catch (ClassCastException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("参数类型转换异常");
        }
        return null;
    }

    /**
     * 根据用户ID 和 订单状态获取待评价商品详情
     */
    public List<CommentRE> getPropertyIdListByUid(OrdGoodsVO ordGoodsVO) {
        OrdGoodsDTO ordGoodsDTO = new OrdGoodsDTO();
        BeanUtils.copyProperties(ordGoodsVO, ordGoodsDTO);
        try {
            return orderClientRemote.getPropertyIdListByUid(ordGoodsDTO).getRe();
        } catch (ClassCastException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("参数类型转换异常");
        }
        return null;
    }

    /**
     * 查找指定会员订单总记录数
     */
    public Integer queryOrderCount(Long memberId) {
        try {
            return orderClientRemote.queryOrderCount(memberId).getRe();
        } catch (ClassCastException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 通过商品属性id获取待支付的商品属性数量
     */
    public Integer getUnPayGoodsPropertyCountByPropertyId(Long propertyId) {
        try {
            return orderClientRemote.getUnPayGoodsPropertyCountByPropertyId(propertyId).getRe();
        } catch (ClassCastException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 根据id 更改订单商品评论状态
     */
    public Integer upOrdGoodsCommentStatus(OrdOrderGoodsVO ordOrderGoodsVO) {
        try {
            OrdOrderGoodsDTO ordOrderGoodsDTO = new OrdOrderGoodsDTO();
            BeanUtils.copyProperties(ordOrderGoodsVO, ordOrderGoodsDTO);
            return orderClientRemote.upOrdGoodsCommentStatus(ordOrderGoodsDTO).getRe();
        } catch (ClassCastException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 根据订单商品表Id获取订单表信息
     */
    public OrderRE getOrderByOrdGoodsId(Long ordGoodsId) {
        try {
            return orderClientRemote.getOrderByOrdGoodsId(ordGoodsId).getRe();
        } catch (ClassCastException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }
}
