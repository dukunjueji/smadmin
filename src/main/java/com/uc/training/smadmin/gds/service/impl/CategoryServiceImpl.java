package com.uc.training.smadmin.gds.service.impl;

import com.uc.training.smadmin.gds.dao.CategoryDao;
import com.uc.training.smadmin.gds.model.Category;
import com.uc.training.smadmin.gds.re.CategoryRE;
import com.uc.training.smadmin.gds.service.CategoryService;
import com.uc.training.smadmin.gds.vo.CategoryVO;
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
     * 获取有效的商品分类
     * @return
     */
    @Override
    public List<CategoryRE> getCategoryList() {
        return categoryDao.getCategoryList();
    }

    /**
     * 增加分类
     *
     * @param category
     * @return
     */
    @Override
    public Long addCategory(Category category) {
        return categoryDao.addCategory(category);
    }

    /**
     * 删除分类
     *
     * @param id
     * @return
     */
    @Override
    public Integer logicDeleteCategory(Long id) {
        //查找id对应的分类
        Category category = categoryDao.queryCategory(id);

        if (category.getParentId() == null) {
            //查找该分类的有效子分类
            List<Long> childList = categoryDao.queryIdByParentId(id);
            //逻辑删除有效子分类
            for (Long child : childList) {
                categoryDao.logicDeleteCategory(child);
            }
        }

        return categoryDao.logicDeleteCategory(id);
    }

    /**
     * 编辑分类
     *
     * @param category
     * @return
     */
    @Override
    public Integer updateCategory(Category category) {
        return categoryDao.updateCategory(category);
    }

    /**
     * 根据name和parentId查找数量
     *
     * @param categoryVO
     * @return
     */
    @Override
    public Integer getCountByNameAndParentId(CategoryVO categoryVO) {
        return categoryDao.getCountByNameAndParentId(categoryVO);
    }
}
