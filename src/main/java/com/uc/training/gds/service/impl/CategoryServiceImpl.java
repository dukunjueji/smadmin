package com.uc.training.gds.service.impl;

import com.uc.training.gds.dto.CategoryDTO;
import com.uc.training.gds.re.CategoryRE;
import com.uc.training.gds.service.CategoryService;
import com.uc.training.remote.client.GdsClient;
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
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    GdsClient gdsClient;

    /**
     * 获取有效的商品分类
     * @return
     */
    @Override
    public List<CategoryRE> getCategoryList() {
        return gdsClient.getCategoryList();
    }

    /**
     * 增加分类
     *
     * @param category
     * @return
     */
    @Override
    public Long addCategory(CategoryDTO category) {
        return gdsClient.addCategory(category);
    }

    /**
     * 删除分类
     *
     * @param id
     * @return
     */
    @Override
    public Integer logicDeleteCategory(Long id) {
        return gdsClient.logicDeleteCategory(id);
    }

    /**
     * 根据id获取分类
     *
     * @param id
     * @return
     */
    @Override
    public CategoryRE queryCategory(Long id) {
        return gdsClient.queryCategory(id);
    }

    /**
     * 编辑分类
     *
     * @param category
     * @return
     */
    @Override
    public Integer updateCategory(CategoryDTO category) {
        return gdsClient.updateCategory(category);
    }

    /**
     * 根据name和parentId查找数量
     *
     * @param categoryVO
     * @return
     */
    @Override
    public Integer getCountByNameAndParentId(CategoryDTO categoryVO) {
        return gdsClient.getCountByNameAndParentId(categoryVO);
    }
}
