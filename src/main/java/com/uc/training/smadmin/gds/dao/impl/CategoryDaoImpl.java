package com.uc.training.smadmin.gds.dao.impl;

import com.ycc.base.common.Result;
import com.uc.training.smadmin.gds.dao.CategoryDao;
import com.uc.training.smadmin.gds.model.Category;
import com.uc.training.smadmin.gds.re.CategoryRE;
import com.zuche.framework.dao.CarIsIbatisDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/23
 */
@Repository
public class CategoryDaoImpl extends CarIsIbatisDaoImpl implements CategoryDao {
    /**
     * 获取商品分类
     * @return
     */
    @Override
    public List<CategoryRE> getCategoryList() {
        return this.queryForList("com.uc.training.smadmin.gds.dao.CategoryDao.getCategoryList");
    }
}
