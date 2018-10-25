package com.uc.training.smadmin.gds.controller;

import com.uc.training.common.annotation.AccessLogin;
import com.uc.training.common.base.controller.BaseController;
import com.uc.training.smadmin.gds.model.Category;
import com.uc.training.smadmin.gds.vo.CategoryVO;
import com.ycc.base.common.Result;
import com.uc.training.smadmin.gds.re.CategoryRE;
import com.uc.training.smadmin.gds.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/23
 */
@Controller
@RequestMapping("api/gds/category")
public class CategoryController extends BaseController{

    @Autowired
    private CategoryService categoryService;

    //获取所有没有被删除分类
    @ResponseBody
    @AccessLogin
    @RequestMapping(value = "getCategoryList.do_",method = RequestMethod.POST)
    public Result<List<CategoryRE>> getCategoryList() {
        //获取所有类型
        List<CategoryRE> list = categoryService.getCategoryList();

        //最终的分类
        List<CategoryRE> categoryList = new ArrayList<>();

        //cateList中增加顶级分类
        for (CategoryRE categoryRE : list) {
            //不存在父级id的分类为顶级分类
            if (categoryRE.getParentId() == null) {
                categoryList.add(categoryRE);
            }
        }
        //父类分类中增加子类分类
        for (CategoryRE category : categoryList) {
            category.setChildren(getChildren(category.getId(), list));
        }
        return Result.getSuccessResult(categoryList);
    }

    //获取子类分类，参数：父级id，所有分类
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
        if (child.size() == 0) {
            return null;
        }
        //递归查找子分类中的分类
        for (CategoryRE c : child) {
            c.setChildren(getChildren(c.getId(), category));
        }*/
        return child;
    }

    @ResponseBody
    @AccessLogin
    @RequestMapping(value = "addParentCategory.do_", method = RequestMethod.POST)
    public Result addParentCategory(@Validated CategoryVO categoryVO) {

        Category category = new Category();
        //将categoryVO的值映射到category中
        BeanUtils.copyProperties(categoryVO, category);

        category.setCreateEmp(getUid());
        category.setModifyEmp(getUid());

        return Result.getSuccessResult(categoryService.addCategory(category));
    }
}
