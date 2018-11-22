package com.uc.training.remote.client;

import com.uc.training.ord.re.CartGoodsRE;
import com.uc.training.utils.RemoteUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/22
 */
@Service
public class OrderClient {

    private static final Logger logger = LoggerFactory.getLogger(OrderClient.class.getName());

    /** 根据用户id查询购物车信息表*/
    private static final String GET_CAR_GOODS_BY_ID = "smorder.api.getCarGoodsById";
    /** 更新购物车商品数量*/
    private static final String UPDATE_CAR_GOODS_NUM = "smorder.api.updateCarGoodsNum";
    /** 根据用户ID和购物车商品表ID获取购物车商品信息*/
    private static final String GET_CAR_GOODS_BY_IDS = "smorder.api.getCarGoodsByIds";
    /** 根据用户id 获取购物车商品表数据数量*/
    private static final String GET_ORDER_GOODS_BY_ORDER_ID = "smorder.api.getOrderGoodsByOrderId";
    /** 加入购物车*/
    private static final String ADD_CAR_GOODS = "smorder.api.addCarGoods";
    /** 删除购物车*/
    private static final String DELETE_CAR_GOODS = "smorder.api.deleteCarGoods";
    /** 插入订单信息并返回订单id*/
    private static final String INSERT_ORDER = "smorder.api.insertOrder";
    /** 插入订单商品信息*/
    private static final String INSERT_ORDER_GOODS = "smorder.api.insertOrderGoods";

    /**
     * 根据用户id查询购物车信息表
     * @return
     */
    public List<CartGoodsRE> getCarGoodsById(Long memberId) {
        List<CartGoodsRE> list = new ArrayList<>();
        try {
             list = (List<CartGoodsRE>) RemoteUtil.exec(GET_CAR_GOODS_BY_ID, memberId);
            return list;
        }catch (Exception e){
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return list;
    }
}
