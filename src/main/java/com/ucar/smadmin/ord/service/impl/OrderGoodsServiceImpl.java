package com.ucar.smadmin.ord.service.impl;


import com.ucar.smadmin.ord.re.OrderRE;
import com.ucar.smadmin.ord.service.OrderGoodsService;
import com.ucar.smadmin.ord.vo.OrdOrderGoodsVO;
import com.ucar.smadmin.remote.client.OrderClient;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    OrderClient orderClient;

    /**
     * 通过商品属性id获取待支付的商品属性数量
     *
     * @param propertyId
     * @return
     */
    @Override
    public Integer getUnPayGoodsPropertyCountByPropertyId(Long propertyId) {
        return orderClient.getUnPayGoodsPropertyCountByPropertyId(propertyId);
    }

    /**
     * 根据id 更改订单商品评论状态
     *
     * @param ordOrderGoodsVO
     * @return
     */
    @Override
    public Integer upOrdGoodsCommentStatus(OrdOrderGoodsVO ordOrderGoodsVO) {
        return orderClient.upOrdGoodsCommentStatus(ordOrderGoodsVO);
    }

    /**
     * 根据订单商品表Id获取订单表信息
     *
     * @param ordGoodsId
     * @return
     */
    @Override
    public OrderRE getOrderByOrdGoodsId(Long ordGoodsId) {
        return orderClient.getOrderByOrdGoodsId(ordGoodsId);
    }
}
