package com.uc.training.smadmin.gds.service;

import com.uc.training.smadmin.gds.model.Property;
import com.uc.training.smadmin.gds.re.AdminPropertyListRE;

import java.util.List;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/30
 */
public interface PropertyService {
    /**
     * 新增商品属性
     * @param property
     * @return
     */
    Long insertProperty(Property property);

    /**
     * 更新商品
     * @param property
     * @return
     */
    Integer updateProperty(Property property);

    /**
     * 通过商品id获取类型数量
     * @param goodsId
     * @return
     */
    Integer getPropertyCountByGoodsId(Long goodsId);

    /**
     * 通过商品id获取商品属性
     * @param goodsId
     * @return
     */
    List<AdminPropertyListRE> getPropertyListByGoodsId(Long goodsId);
}
