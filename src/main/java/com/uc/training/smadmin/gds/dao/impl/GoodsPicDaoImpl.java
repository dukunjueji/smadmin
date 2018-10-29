package com.uc.training.smadmin.gds.dao.impl;

import com.uc.training.smadmin.gds.dao.GoodsPicDao;
import com.uc.training.smadmin.gds.model.GoodsPic;
import com.uc.training.smadmin.gds.model.Property;
import com.zuche.framework.dao.CarIsIbatisDaoImpl;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/29
 */
public class GoodsPicDaoImpl extends CarIsIbatisDaoImpl implements GoodsPicDao {

    /**
     * 新增商品属性
     *
     * @param goodsPic
     * @return
     */
    @Override
    public Long insertGoodsPic(GoodsPic goodsPic) {
        return (Long) this.insert("com.uc.training.smadmin.gds.dao.CategoryDao.insertGoodsPic", goodsPic);
    }

    /**
     * 更新商品属性
     *
     * @param goodsPic
     * @return
     */
    @Override
    public Integer updateGoodsPic(GoodsPic goodsPic) {
        return update("com.uc.training.smadmin.gds.dao.CategoryDao.updateGoodsPic", goodsPic);
    }
}
