package com.uc.training.smadmin.gds.dao;

import com.uc.training.smadmin.bd.dao.IntegralDetailDao;
import com.uc.training.smadmin.gds.model.Property;

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
}
