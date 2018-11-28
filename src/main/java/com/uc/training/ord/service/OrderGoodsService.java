package com.uc.training.ord.service;

import com.uc.training.ord.dto.OrdOrderGoodsDTO;
import com.uc.training.ord.re.OrderRE;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/8
 */
public interface OrderGoodsService {
    /**
     * 通过商品属性id获取待支付的商品属性数量
     * @param propertyId
     * @return
     */
    Integer getUnPayGoodsPropertyCountByPropertyId(Long propertyId);

    /**
     * 根据id 更改订单商品评论状态
     * @param ordOrderGoodsDTO
     * @return
     */
    Integer upOrdGoodsCommentStatus(OrdOrderGoodsDTO ordOrderGoodsDTO);

    /**
     * 根据订单商品表Id获取订单表信息
     * @param ordGoodsId
     * @return
     */
    OrderRE getOrderByOrdGoodsId(Long ordGoodsId);
}
