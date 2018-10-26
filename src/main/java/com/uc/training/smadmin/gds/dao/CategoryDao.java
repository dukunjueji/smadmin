package com.uc.training.smadmin.gds.dao;

import com.uc.training.smadmin.gds.vo.CategoryVO;
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
public interface CategoryDao {
    /**
     * 获取商品分类
     * @return
     */
    List<CategoryRE> getCategoryList();

    /**
     * 新增分类
     * @param category
     * @return
     */
    Long addCategory(Category category);

    /**
     * 根据主键id查找分类
     * @param id
     * @return
     */
    Category queryCategory(Long id);

    /**
     * 更新分类
     * @param category
     * @return
     */
    Integer updateCategory(Category category);

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    Integer logicDeleteCategory(Long id);

    /**
     * 根据父类id查找所有有效分类
     * @param id
     * @return
     */
    List<Long> queryIdByParentId(Long id);

    /**
     * 根据name和parentId查找数量
     * @param categoryVO
     * @return
     */
    Integer getCountByNameAndParentId(CategoryVO categoryVO);
}
