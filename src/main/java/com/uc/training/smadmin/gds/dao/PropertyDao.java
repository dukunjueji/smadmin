package com.uc.training.smadmin.gds.dao;

import com.uc.training.smadmin.bd.dao.IntegralDetailDao;
import com.uc.training.smadmin.gds.model.Property;
import com.uc.training.smadmin.gds.re.AdminPropertyListRE;

import java.util.List;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/29
 */
public interface PropertyDao {

    /**
     * 新增商品属性
     * @param property
     * @return
     */
    Long insertProperty(Property property);

    /**
     * 更新商品属性
     * @param property
     * @return
     */
    Integer updateProperty(Property property);

    /**
     * 通过商品id获取商品类型数量
     * @param goodsId
     * @return
     */
    Integer getPropertyCountByGoodsId(Long goodsId);

    /**
     * 通过商品id获取商品所有属性
     * @param goodsId
     * @return
     */
    List<AdminPropertyListRE> getPropertyListByGoodsId(Long goodsId);
}
