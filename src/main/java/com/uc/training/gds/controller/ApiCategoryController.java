package com.uc.training.gds.controller;

import com.uc.training.common.annotation.AccessLogin;
import com.uc.training.common.base.controller.BaseController;
import com.uc.training.smadmin.gds.re.CategoryRE;
import com.uc.training.smadmin.gds.service.CategoryService;
import com.ycc.base.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/23
 */
@Controller
@RequestMapping("api/gds/category")
public class ApiCategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 获取所有有效分类
     * @return
     */
    @ResponseBody
    @AccessLogin(required = false)
    @RequestMapping(value = "getCategoryList.do_", method = RequestMethod.GET)
    public Result<List<CategoryRE>> getCategoryList() {
        //获取所有类型
        List<CategoryRE> categoryOriginList = categoryService.getCategoryList();

        //判空
        if (CollectionUtils.isEmpty(categoryOriginList)) {
            return Result.getSuccessResult(categoryOriginList);
        }

        //最终的分类
        List<CategoryRE> categoryList = new ArrayList<>();

        //cateList中增加顶级分类
        for (CategoryRE categoryRE : categoryOriginList) {
            //不存在父级id的分类为顶级分类
            if (categoryRE.getParentId() == 0) {
                categoryList.add(categoryRE);
            }
        }

        // 判空
        if (CollectionUtils.isEmpty(categoryList)) {
            return Result.getSuccessResult(categoryList);
        }

        //父类分类中增加子类分类
        for (CategoryRE category : categoryList) {
            category.setChildren(getChildren(category.getId(), categoryOriginList));

            //在原始的集合中去除第二层的分类
            if (!CollectionUtils.isEmpty(category.getChildren())) {
                categoryOriginList.removeAll(category.getChildren());
            }
            // 判空
            if (CollectionUtils.isEmpty(categoryOriginList)) {
                return Result.getSuccessResult(categoryList);
            }
        }
        return Result.getSuccessResult(categoryList);
    }

    /**
     * 获取子类分类，参数：父级id，所有分类
     * @param id
     * @param category
     * @return
     */
    public List<CategoryRE> getChildren(Long id, List<CategoryRE> category) {

        List<CategoryRE> child = new ArrayList<>();

        //子分类添加到父类分类的集合中
        for (CategoryRE categoryRE : category) {
            if (categoryRE.getParentId() != null && categoryRE.getParentId().equals(id)) {
                child.add(categoryRE);
            }
        }

        return child;
    }
}
