package com.uc.training.ord.service.impl;


import com.uc.training.ord.re.OrderRE;
import com.uc.training.ord.service.OrderGoodsService;
import com.uc.training.ord.vo.OrdOrderGoodsVO;
import com.uc.training.remote.client.OrderClient;
import org.springframework.stereotype.Service;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/8
 */
@Service
public class OrderGoodsServiceImpl implements OrderGoodsService {

    /**
     * 通过商品属性id获取待支付的商品属性数量
     *
     * @param propertyId
     * @return
     */
    @Override
    public Integer getUnPayGoodsPropertyCountByPropertyId(Long propertyId) {
        return OrderClient.getUnPayGoodsPropertyCountByPropertyId(propertyId);
    }

    /**
     * 根据id 更改订单商品评论状态
     *
     * @param ordOrderGoodsVO
     * @return
     */
    @Override
    public Integer upOrdGoodsCommentStatus(OrdOrderGoodsVO ordOrderGoodsVO) {
        return OrderClient.upOrdGoodsCommentStatus(ordOrderGoodsVO);
    }

    /**
     * 根据订单商品表Id获取订单表信息
     *
     * @param ordGoodsId
     * @return
     */
    @Override
    public OrderRE getOrderByOrdGoodsId(Long ordGoodsId) {
        return OrderClient.getOrderByOrdGoodsId(ordGoodsId);
    }
}
