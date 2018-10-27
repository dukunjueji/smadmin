package com.uc.training.smadmin.gds.controller;

import com.uc.training.common.annotation.AccessLogin;
import com.uc.training.common.base.controller.BaseController;
import com.uc.training.smadmin.gds.model.Category;
import com.uc.training.smadmin.gds.vo.CategoryVO;
import com.ycc.base.common.Result;
import com.uc.training.smadmin.gds.re.CategoryRE;
import com.uc.training.smadmin.gds.service.CategoryService;
import com.zuche.base.common.web.taglib.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;
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
public class CategoryController extends BaseController {

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
        List<CategoryRE> list = categoryService.getCategoryList();

        //判空
        if (list == null && list.isEmpty()) {
            return Result.getSuccessResult(list);
        }

        //最终的分类
        List<CategoryRE> categoryList = new ArrayList<>();

        //cateList中增加顶级分类
        for (CategoryRE categoryRE : list) {
            //不存在父级id的分类为顶级分类
            if (categoryRE.getParentId() == 0) {
                categoryList.add(categoryRE);
            }
        }

        // 判空
        if (categoryList == null && categoryList.isEmpty()) {
            return Result.getSuccessResult(categoryList);
        }

        //父类分类中增加子类分类
        for (CategoryRE category : categoryList) {
            category.setChildren(getChildren(category.getId(), list));
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
        /*
        //child为空时，停止查询
        if (child.isEmpty()) {
            return null;
        }
        //递归查找子分类中的分类
        for (CategoryRE c : child) {
            c.setChildren(getChildren(c.getId(), category));
        }*/
        return child;
    }

    /**
     * 新增父级分类
     *
     * @param categoryVO
     * @return
     */
    @ResponseBody
    @AccessLogin
    @RequestMapping(value = "addParentCategory.do_", method = RequestMethod.POST)
    public Result addParentCategory(@Validated CategoryVO categoryVO) {

        categoryVO.setParentId(0L);

        //判断同一级别是否存在同名
        if (categoryService.getCountByNameAndParentId(categoryVO) > 0) {
            return Result.getBusinessException("该名称已存在", null);
        }

        Category category = new Category();
        //将categoryVO的值映射到category中
        BeanUtils.copyProperties(categoryVO, category);

        category.setCreateEmp(getUid());
        category.setModifyEmp(getUid());

        return Result.getSuccessResult(categoryService.addCategory(category));
    }

    /**
     * 新增子级分类
     *
     * @param categoryVO
     * @return
     */
    @ResponseBody
    @AccessLogin
    @RequestMapping(value = "addChildCategory.do_", method = RequestMethod.POST)
    public Result addChildCategory(@Validated CategoryVO categoryVO) {

        //判断同一级别是否存在同名
        if (categoryService.getCountByNameAndParentId(categoryVO) > 0) {
            return Result.getBusinessException("该名称已存在", null);
        }
        //判断图片地址
        if (StringUtil.isBlank(categoryVO.getImageUrl())) {
            return Result.getBusinessException("图片地址不能为空", null);
        }

        Category category = new Category();

        //将categoryVO的值映射到category中
        BeanUtils.copyProperties(categoryVO, category);

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
    @AccessLogin
    @RequestMapping(value = "logicDeleteCategory.do_", method = RequestMethod.POST)
    public Result deleteCategory(Long id) {
        return Result.getSuccessResult(categoryService.logicDeleteCategory(id));
    }

    /**
     * 更新子级分类
     *
     * @param categoryVO
     * @return
     */
    @ResponseBody
    @AccessLogin
    @RequestMapping(value = "editCategory.do_", method = RequestMethod.POST)
    public Result updateCategory(@Validated CategoryVO categoryVO) {

        //数据校验
        if (categoryVO.getParentId() != 0) {
            if (StringUtil.isBlank(categoryVO.getImageUrl())) {
                return Result.getBusinessException("请上传图片！", null);
            }
        } else {
            if (StringUtil.isNotBlank(categoryVO.getImageUrl())) {
                return Result.getBusinessException("不能上传图片！", null);
            }
        }

        Category category = new Category();

        //将categoryVO的值映射到category中
        BeanUtils.copyProperties(categoryVO, category);

        category.setModifyEmp(getUid());

        return Result.getSuccessResult(categoryService.updateCategory(category));
    }
}
