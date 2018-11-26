package com.uc.training.remote.client;

import com.uc.training.ord.dto.OrdCartGoodsDTO;
import com.uc.training.ord.dto.OrdGoodsDTO;
import com.uc.training.ord.re.CartGoodsRE;
import com.uc.training.ord.re.OrderGoodsRE;
import com.uc.training.utils.RemoteUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/22
 */
public final class OrderClient {

    private static final Logger logger = LoggerFactory.getLogger(OrderClient.class.getName());

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
     * 根据用户id 获取购物车商品表数据数量
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
     * 根据用户id查询购物车信息表
     *
     * @return
     */
    public List<CartGoodsRE> getCarGoodsById(Long memberId) {

        Object object = RemoteUtil.exec(GET_CAR_GOODS_BY_ID, memberId);
        if (object != null) {
            try {
                List<CartGoodsRE> list = (List<CartGoodsRE>) RemoteUtil.exec(GET_CAR_GOODS_BY_ID, memberId);
                return list;
            } catch (ClassCastException e) {
                logger.error("类型转换异常");
                logger.error(e.getMessage());
            }
        }
        return null;
    }

    /**
     * 更新购物车商品数量
     */
    public Integer updateCarGoodsNum(OrdCartGoodsDTO ordCartGoodsDTO) {
        Integer num = null;
        try {
            num = (Integer) RemoteUtil.exec(UPDATE_CAR_GOODS_NUM, ordCartGoodsDTO);
            return num;
        } catch (Exception e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return num;
    }
    /**
     * 根据用户ID和购物车商品表ID获取购物车商品信息
     */
    public List<CartGoodsRE> getCarGoodsByIds(OrdGoodsDTO ordGoodsDTO) {
        List<CartGoodsRE> re = new ArrayList<>();
        try {
            re = (List<CartGoodsRE>) RemoteUtil.exec(GET_CAR_GOODS_BY_IDS, ordGoodsDTO);
            return re;
        } catch (Exception e) {
            logger.error("数据类型转换异常");
            logger.error(e.getMessage());
        }
        return re;
    }
    /**
     * 根据用户id 获取购物车商品表数据数量
     */
    public List<OrderGoodsRE> getOrderGoodsByOrderId(Integer orderId) {
        List<OrderGoodsRE> re = new ArrayList<>();
        try {
            re = (List<OrderGoodsRE>) RemoteUtil.exec(GET_ORDER_GOODS_BY_ORDER_ID, orderId);
            return re;
        } catch (Exception e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return re;
    }
}
