package com.uc.training.smadmin.ord.service;

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

}
