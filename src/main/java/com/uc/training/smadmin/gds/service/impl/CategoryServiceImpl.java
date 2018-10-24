package com.uc.training.smadmin.gds.service.impl;

import com.ycc.base.common.Result;
import com.uc.training.smadmin.gds.dao.CategoryDao;
import com.uc.training.smadmin.gds.model.Category;
import com.uc.training.smadmin.gds.re.CategoryRE;
import com.uc.training.smadmin.gds.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/23
 */
@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryDao categoryDao;

    /**
     * 获取商品分类
     * @return
     */
    @Override
    public List<CategoryRE> getCategoryList() {
        return categoryDao.getCategoryList();
    }
}
