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
import com.uc.training.ord.vo.OrderVO;
import com.uc.training.remote.utils.RemoteUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/22
 */
public final class OrderClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderClient.class.getName());

    /**
     * 根据用户id查询购物车信息表
     */
    private static final String GET_CAR_GOODS_BY_ID = "smorder.api.getCarGoodsById";
    /**
     * 更新购物车商品数量
     */
    private static final String UPDATE_CAR_GOODS_NUM = "smorder.api.updateCarGoodsNum";
    /**
     * 根据用户ID和购物车商品表ID获取购物车商品信息
     */
    private static final String GET_CAR_GOODS_BY_IDS = "smorder.api.getCarGoodsByIds";
    /**
     * 根据订单id 获取购物车商品表数据
     */
    private static final String GET_ORDER_GOODS_BY_ORDER_ID = "smorder.api.getOrderGoodsByOrderId";
    /**
     * 加入购物车
     */
    private static final String ADD_CAR_GOODS = "smorder.api.addCarGoods";
    /**
     * 删除购物车
     */
    private static final String DELETE_CAR_GOODS = "smorder.api.deleteCarGoods";
    /**
     * 插入订单信息并返回订单id
     */
    private static final String INSERT_ORDER = "smorder.api.insertOrder";
    /**
     * 插入订单商品信息
     */
    private static final String INSERT_ORDER_GOODS = "smorder.api.insertOrderGoods";
    /**
     * 获取订单查询分页
     */
    private static final String GET_ORDER_PAGE = "smorder.api.getOrderPage";
    /**
     * 获取总记录数
     */
    private static final String GET_ORDER_TOTAL = "smorder.api.getOrderTotal";
    /**
     * 批量逻辑删除订单
     */
    private static final String LOGIC_DEL_ORDER = "smorder.api.logicDelOrder";
    /**
     * 通过会员信息或者订单信息来查找订单信息
     */
    private static final String GET_ORDER_BY_MEMBERDTO = "smorder.api.getOrderByMemberDTO";
    /**
     * 更新订单状态
     */
    private static final String UPDATE_ORDER = "smorder.api.updateOrder";
    /**
     * 根据主键id获取手机号
     */
    private static final String GET_TELEPHONE_BY_ID = "smorder.api.getTelephoneById";
    /**
     * 当年每个月销售额
     */
    private static final String GET_ORDER_SALEDATA = "smorder.api.getOrderSaleData";
    /**
     * 根据用户id 获取购物车商品表数据数量
     */
    private static final String QUERY_CART_GOODS_COUNT = "smorder.api.queryCartGoodsCount";
    /**
     * 逻辑删除订单
     */
    private static final String MEMBER_DEL_ORDER = "smorder.api.memberDelOrder";
    /**
     * 根据用户ID 和 订单状态获取待评价商品详情
     */
    private static final String GET_PROPERTY_ID_LIST_BY_UID = "smorder.api.getPropertyIdListByUid";
    /**
     * 查找指定会员订单总记录数
     */
    private static final String QUERY_ORDER_COUNT = "smorder.api.queryOrderCount";
    /**
     * 通过商品属性id获取待支付的商品属性数量
     */
    private static final String GET_UN_PAY_GOODS_PROPERTY_COUNT_BY_PROPERTY_ID = "smorder.api.getUnPayGoodsPropertyCountByPropertyId";
    /**
     * 根据id 更改订单商品评论状态
     */
    private static final String UP_ORD_GOODS_COMMENT_STATUS = "smorder.api.upOrdGoodsCommentStatus";
    /**
     * 根据订单商品表Id获取订单表信息
     */
    private static final String GET_ORDER_BY_ORD_GOODS_ID = "smorder.api.getOrderByOrdGoodsId";



    /**
     * 根据用户id查询购物车信息表
     *
     * @return
     */
    public static List<CartGoodsRE> getCarGoodsById(Long memberId) {

        Object object = RemoteUtil.exec(GET_CAR_GOODS_BY_ID, memberId);
        if (object != null) {
            try {
                List<CartGoodsRE> list = (List<CartGoodsRE>) RemoteUtil.exec(GET_CAR_GOODS_BY_ID, memberId);
                return list;
            } catch (ClassCastException e) {
                LOGGER.error("类型转换异常");
                LOGGER.error(e.getMessage());
            }
        }
        return null;
    }

    /**
     * 更新购物车商品数量
     */
    public static Integer updateCarGoodsNum(OrdCartGoodsVO ordCartGoodsVO) {
        OrdCartGoodsDTO ordCartGoodsDTO = new OrdCartGoodsDTO();
        BeanUtils.copyProperties(ordCartGoodsVO, ordCartGoodsDTO);
        try {
            return (Integer) RemoteUtil.exec(UPDATE_CAR_GOODS_NUM, ordCartGoodsDTO);
        } catch (Exception e) {
            LOGGER.error("类型转换异常");
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * 根据用户ID和购物车商品表ID获取购物车商品信息
     */
    public static List<CartGoodsRE> getCarGoodsByIds(OrdGoodsVO ordGoodsVO) {
        OrdGoodsDTO ordGoodsDTO = new OrdGoodsDTO();
        BeanUtils.copyProperties(ordGoodsVO, ordGoodsDTO);
        try {
            return (List<CartGoodsRE>) RemoteUtil.exec(GET_CAR_GOODS_BY_IDS, ordGoodsDTO);
        } catch (Exception e) {
            LOGGER.error("数据类型转换异常");
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * 根据订单id 获取购物车商品表数据
     */
    public static List<OrderGoodsRE> getOrderGoodsByOrderId(Integer orderId) {
        try {
            return (List<OrderGoodsRE>) RemoteUtil.exec(GET_ORDER_GOODS_BY_ORDER_ID, orderId);
        } catch (Exception e) {
            LOGGER.error("类型转换异常");
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * 加入购物车
     */
    public static Integer addCarGoods(OrdCartGoodsVO ordCartGoodsVO) {
        OrdCartGoodsDTO ordCartGoodsDTO = new OrdCartGoodsDTO();
        BeanUtils.copyProperties(ordCartGoodsVO, ordCartGoodsDTO);
        try {
            return (Integer) RemoteUtil.exec(ADD_CAR_GOODS, ordCartGoodsDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 删除购物车
     */
    public static Integer deleteCarGoods(OrdCartGoodsVO ordCartGoodsVO) {
        OrdCartGoodsDTO ordCartGoodsDTO = new OrdCartGoodsDTO();
        BeanUtils.copyProperties(ordCartGoodsVO, ordCartGoodsDTO);
        try {
            return (Integer) RemoteUtil.exec(DELETE_CAR_GOODS, ordCartGoodsDTO);
        } catch (Exception e) {
            LOGGER.error("数据类型转换异常");
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * 插入订单信息并返回订单id
     */
    public static Long insertOrder(OrderVO orderVO) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderVO, orderDTO);
        try {
            return (Long) RemoteUtil.exec(INSERT_ORDER, orderDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 插入订单商品信息并返回订单id
     */
    public static Long insertOrderGoods(OrdGoodsVO orderGoodsVO) {
        OrderGoodsDTO orderGoodsDTO = new OrderGoodsDTO();
        BeanUtils.copyProperties(orderGoodsVO, orderGoodsDTO);
        try {
            return (Long) RemoteUtil.exec(INSERT_ORDER_GOODS, orderGoodsDTO);
        } catch (Exception e) {
            LOGGER.error("数据类型转换异常");
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * 获取订单查询分页
     */
    public static List<OrderRE> getOrderPage(OrdOrderVO ordOrderVO) {
        OrdOrderDTO ordOrderDTO = new OrdOrderDTO();
        BeanUtils.copyProperties(ordOrderVO, ordOrderDTO);
        try {
            return (List<OrderRE>) RemoteUtil.exec(GET_ORDER_PAGE, ordOrderDTO);
        } catch (Exception e) {
            LOGGER.error("数据类型转换异常");
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * 获取总记录数
     */
    public static Integer getOrderTotal(OrdOrderVO ordOrderVO) {
        OrdOrderDTO ordOrderDTO = new OrdOrderDTO();
        BeanUtils.copyProperties(ordOrderVO, ordOrderDTO);
        try {
            return (Integer) RemoteUtil.exec(GET_ORDER_TOTAL, ordOrderDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("数据类型转换异常");
        }
        return null;
    }

    /**
     * 批量逻辑删除订单
     */
    public static Integer logicDelOrder(List<Long> list) {
        try {
            return (Integer) RemoteUtil.exec(LOGIC_DEL_ORDER, list);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("结果转换异常");
        }
        return null;
    }

    /**
     * 通过会员信息或者订单信息来查找订单信息
     */
    public static List<OrderRE> getOrderByMemberVO(OrdMemberVO ordMemberVO) {
        try {
            OrdMemberDTO ordMemberDTO = new OrdMemberDTO();
            BeanUtils.copyProperties(ordMemberVO, ordMemberDTO);
            return (List<OrderRE>) RemoteUtil.exec(GET_ORDER_BY_MEMBERDTO, ordMemberDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 更新订单状态
     */
    public static Integer updateOrder(OrdOrderVO ordOrderVO) {
        try {
            OrdOrderDTO ordOrderDTO = new OrdOrderDTO();
            BeanUtils.copyProperties(ordOrderVO, ordOrderDTO);
            return (Integer) RemoteUtil.exec(UPDATE_ORDER, ordOrderDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 更新订单状态
     */
    public static String getTelephoneById(Long id) {
        try {
            return (String) RemoteUtil.exec(GET_TELEPHONE_BY_ID, id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 当年每个月销售额
     */
    public static OrderSaleRE getOrderSaleData() {
        try {
            return (OrderSaleRE) RemoteUtil.exec(GET_ORDER_SALEDATA, null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }

    /**
     * 根据用户id 获取购物车商品表数据数量
     */
    public static Integer queryCartGoodsCount(Long memberId) {
        try {
            return (Integer) RemoteUtil.exec(QUERY_CART_GOODS_COUNT, memberId);
        } catch (Exception e) {
            LOGGER.error("Integer类型转换异常");
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * 逻辑删除订单
     */
    public static Integer memberDelOrder(OrdOrderVO ordOrderVO) {
        OrdOrderDTO ordOrderDTO = new OrdOrderDTO();
        BeanUtils.copyProperties(ordOrderVO, ordOrderDTO);
        try {
            return (Integer) RemoteUtil.exec(MEMBER_DEL_ORDER, ordOrderDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("参数类型转换异常");
        }
        return null;
    }

    /**
     * 根据用户ID 和 订单状态获取待评价商品详情
     */
    public static List<CommentRE> getPropertyIdListByUid(OrdGoodsVO ordGoodsVO) {
        OrdGoodsDTO ordGoodsDTO = new OrdGoodsDTO();
        BeanUtils.copyProperties(ordGoodsVO, ordGoodsDTO);
        try {
            return (List<CommentRE>) RemoteUtil.exec(GET_PROPERTY_ID_LIST_BY_UID, ordGoodsDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("参数类型转换异常");
        }
        return null;
    }

    /**
     * 查找指定会员订单总记录数
     */
    public static Integer queryOrderCount(Long memberId) {
        try {
            return (Integer) RemoteUtil.exec(QUERY_ORDER_COUNT, memberId);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }
    /**
     * 通过商品属性id获取待支付的商品属性数量
     */
    public static Integer getUnPayGoodsPropertyCountByPropertyId(Long propertyId) {
        try {
            return (Integer) RemoteUtil.exec(GET_UN_PAY_GOODS_PROPERTY_COUNT_BY_PROPERTY_ID, propertyId);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }
    /**
     * 根据id 更改订单商品评论状态
     */
    public static Integer upOrdGoodsCommentStatus(OrdOrderGoodsVO ordOrderGoodsVO) {
        try {
            OrdOrderGoodsDTO ordOrderGoodsDTO =new OrdOrderGoodsDTO();
            BeanUtils.copyProperties(ordOrderGoodsVO,ordOrderGoodsDTO);
            return (Integer) RemoteUtil.exec(UP_ORD_GOODS_COMMENT_STATUS, ordOrderGoodsDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }
    /**
     * 根据订单商品表Id获取订单表信息
     */
    public static OrderRE getOrderByOrdGoodsId(Long ordGoodsId) {
        try {
            return (OrderRE) RemoteUtil.exec(GET_ORDER_BY_ORD_GOODS_ID, ordGoodsId);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("类型转换异常");
        }
        return null;
    }
}
