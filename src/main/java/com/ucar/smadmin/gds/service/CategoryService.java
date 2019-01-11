package com.ucar.smadmin.gds.service;


import com.ucar.smapi.gds.dto.CategoryDTO;
import com.ucar.smapi.gds.re.CategoryRE;

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

    /**
     * 增加分类
     * @param category
     * @return
     */
    Long addCategory(CategoryDTO category);

    /**
     * 删除分类
     * @param id
     * @return
     */
    Integer logicDeleteCategory(Long id);

    /**
     * 根据id获取分类
     * @param id
     * @return
     */
    CategoryRE queryCategory(Long id);

    /**
     * 编辑分类
     * @param category
     * @return
     */
    Integer updateCategory(CategoryDTO category);

    /**
     * 根据name和parentId查找数量
     * @param category
     * @return
     */
    Integer getCountByNameAndParentId(CategoryDTO category);
}
