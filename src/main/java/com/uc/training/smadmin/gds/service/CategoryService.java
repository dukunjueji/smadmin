package com.uc.training.smadmin.gds.service;

import com.ycc.base.common.Result;
import com.uc.training.smadmin.gds.model.Category;
import com.uc.training.smadmin.gds.re.CategoryRE;

import java.util.List;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/23
 */
public interface CategoryService {
    /**
     * 获取商品分类
     * @return
     */
    List<CategoryRE> getCategoryList();
}
