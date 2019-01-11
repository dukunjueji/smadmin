package com.uc.training.gds.controller;

import com.smgoods.common.base.controller.BaseController;
import com.smgoods.common.utils.StringUtil;
import com.smgoods.common.vo.Result;
import com.uc.training.gds.dto.CategoryDTO;
import com.uc.training.gds.re.CategoryRE;
import com.uc.training.gds.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
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
 * @date 2018/10/29
 */
@Controller
@RequestMapping("admin/gds/category")
public class AdminCategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 获取所有有效分类
     * @return
     */
    @ResponseBody
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

        // 去除原始集合中的顶级分类
        categoryOriginList.removeAll(categoryList);

        if (CollectionUtils.isEmpty(categoryOriginList)) {
            return Result.getSuccessResult(categoryList);
        }

        //父类分类中增加子类分类
        for (CategoryRE category : categoryList) {
            category.setChildren(getChildren(category.getId(), categoryOriginList));

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
     * @param categoryOriginList
     * @return
     */
    public List<CategoryRE> getChildren(Long id, List<CategoryRE> categoryOriginList) {

        List<CategoryRE> child = new ArrayList<>();

        //子分类添加到父类分类的集合中
        for (CategoryRE categoryRE : categoryOriginList) {
            if (categoryRE.getParentId() != null && categoryRE.getParentId().equals(id)) {
                child.add(categoryRE);
            }
        }

        if (!CollectionUtils.isEmpty(child)) {
            categoryOriginList.removeAll(child);
        }

        return child;
    }

    /**
     * 新增父级分类
     *
     * @param category
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addParentCategory.do_", method = RequestMethod.POST)
    public Result addParentCategory(@Validated CategoryDTO category) {
        category.setParentId(0L);
        //判断同一级别是否存在同名
        if (categoryService.getCountByNameAndParentId(category) > 0) {
            return Result.getBusinessException("该名称已存在", null);
        }
        category.setCreateEmp(getUid());
        category.setModifyEmp(getUid());
        return Result.getSuccessResult(categoryService.addCategory(category));
    }

    /**
     * 新增子级分类
     *
     * @param category
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addChildCategory.do_", method = RequestMethod.POST)
    public Result addChildCategory(@Validated CategoryDTO category) {

        //判断同一级别是否存在同名
        if (categoryService.getCountByNameAndParentId(category) > 0) {
            return Result.getBusinessException("该名称已存在", null);
        }
        //判断图片地址
        if (StringUtil.isBlank(category.getImageUrl())) {
            return Result.getBusinessException("图片地址不能为空", null);
        }

        category.setCreateEmp(getUid());
        category.setModifyEmp(getUid());

        return Result.getSuccessResult(categoryService.addCategory(category));
    }

    /**
     * 逻辑删除分类
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "logicDeleteCategory.do_", method = RequestMethod.POST)
    public Result deleteCategory(Long id) {
        return Result.getSuccessResult(categoryService.logicDeleteCategory(id));
    }

    /**
     * 更新子级分类
     *
     * @param category
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "editCategory.do_", method = RequestMethod.POST)
    public Result updateCategory(@Validated CategoryDTO category) {

        //数据校验
        if (category.getParentId() != 0) {
            if (StringUtil.isBlank(category.getImageUrl())) {
                return Result.getBusinessException("请上传图片！", null);
            }
        } else {
            if (StringUtil.isNotBlank(category.getImageUrl())) {
                return Result.getBusinessException("不能上传图片！", null);
            }
        }

        category.setModifyEmp(getUid());
        return Result.getSuccessResult(categoryService.updateCategory(category));
    }
}
