package com.uc.training.smadmin.gds.dao.impl;

import com.uc.training.smadmin.gds.dao.PropertyDao;
import com.uc.training.smadmin.gds.model.Property;
import com.zuche.framework.dao.CarIsIbatisDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/29
 */
@Repository
public class PropertyDaoImpl  extends CarIsIbatisDaoImpl implements PropertyDao{
    /**
     * 新增商品属性
     *
     * @param property
     * @return
     */
    @Override
    public Long insertProperty(Property property) {
        return (Long) this.insert("com.uc.training.smadmin.gds.dao.PropertyDao.insertProperty", property);
    }
    /**
     * 更新商品属性
     *
     * @param property
     * @return
     */
    @Override
    public Integer updateProperty(Property property) {
        return update("com.uc.training.smadmin.gds.dao.PropertyDao.updateProperty", property);
    }
}
