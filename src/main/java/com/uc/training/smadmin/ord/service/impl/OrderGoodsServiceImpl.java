package com.uc.training.smadmin.ord.service.impl;

import com.uc.training.smadmin.ord.dao.OrderGoodsDao;
import com.uc.training.smadmin.ord.service.OrderGoodsService;
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
    private OrderGoodsDao orderGoodsDao;


    /**
     * 通过商品属性id获取待支付的商品属性数量
     *
     * @param propertyId
     * @return
     */
    @Override
    public Integer getUnPayGoodsPropertyCountByPropertyId(Long propertyId) {
        return orderGoodsDao.getUnPayGoodsPropertyCountByPropertyId(propertyId);
    }

}
