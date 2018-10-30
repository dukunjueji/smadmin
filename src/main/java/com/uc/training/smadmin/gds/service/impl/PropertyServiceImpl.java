package com.uc.training.smadmin.gds.service.impl;

import com.uc.training.smadmin.gds.dao.PropertyDao;
import com.uc.training.smadmin.gds.model.Property;
import com.uc.training.smadmin.gds.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/30
 */
@Service
public class PropertyServiceImpl implements PropertyService{

    @Autowired
    private PropertyDao propertyDao;

    /**
     * 新增商品属性
     *
     * @param property
     * @return
     */
    @Override
    public Long insertProperty(Property property) {
        return propertyDao.insertProperty(property);
    }

    /**
     * 更新商品
     *
     * @param property
     * @return
     */
    @Override
    public Integer updateProperty(Property property) {
        return propertyDao.updateProperty(property);
    }
}
