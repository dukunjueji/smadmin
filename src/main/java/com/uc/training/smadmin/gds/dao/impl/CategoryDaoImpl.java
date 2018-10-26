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

    /**
     * 新增分类
     *
     * @param category
     * @return
     */
    @Override
    public Long addCategory(Category category) {
        return (Long) this.insert("com.uc.training.smadmin.gds.dao.CategoryDao.addCategory", category);
    }

    /**
     * 根据主键id查找分类
     *
     * @param id
     * @return
     */
    @Override
    public Category queryCategory(Long id) {
        return (Category) this.queryForObject("com.uc.training.smadmin.gds.dao.CategoryDao.queryCategory", id);
    }

    /**
     * 更新分类
     *
     * @param category
     * @return
     */
    @Override
    public Integer updateCategory(Category category) {
        return this.update("com.uc.training.smadmin.gds.dao.CategoryDao.updateCategory", category);
    }

    /**
     * 逻辑删除
     *
     * @param id
     * @return
     */
    @Override
    public Integer logicDeleteCategory(Long id) {
        return this.update("com.uc.training.smadmin.gds.dao.CategoryDao.logicDeleteCategory", id);
    }

    /**
     * 根据父类id查找所有有效分类
     *
     * @param id
     * @return
     */
    @Override
    public List<Long> queryListCategoryByParentId(Long id) {
        return this.queryForList("com.uc.training.smadmin.gds.dao.CategoryDao.queryListCategoryByParentId", id);
    }
}
