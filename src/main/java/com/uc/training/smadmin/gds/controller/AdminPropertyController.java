package com.uc.training.smadmin.gds.controller;

import com.uc.training.common.base.controller.BaseController;
import com.uc.training.smadmin.gds.model.Property;
import com.uc.training.smadmin.gds.re.AdminPropertyListRE;
import com.uc.training.smadmin.gds.service.PropertyService;
import com.uc.training.smadmin.gds.vo.AdminPropertyUpdateVO;
import com.uc.training.smadmin.gds.vo.AdminPropertyVO;
import com.ycc.base.common.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/31
 */
@Controller
@RequestMapping("admin/gds/property")
public class AdminPropertyController extends BaseController{

    @Autowired
    private PropertyService propertyService;

    /**
     * 后台通过商品id获取商品属性列表
     * @param goodsId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getPropertyListByGoodsId.do_", method = RequestMethod.POST)
    public Result<List<AdminPropertyListRE>> getPropertyListByGoodsId(Long goodsId) {
        return Result.getSuccessResult(propertyService.getPropertyListByGoodsId(goodsId));
    }
    /**
     * 后台新增商品属性
     * @param adminPropertyVO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "insertProperty.do_", method = RequestMethod.POST)
    public Result insertProperty(@Validated  AdminPropertyVO adminPropertyVO) {

        Property property = new Property();
        BeanUtils.copyProperties(adminPropertyVO, property);

        //获取同商品下同名称的数量
        if (propertyService.getCountByGoodsIdAndName(property) >= 1) {
            return Result.getBusinessException("该规格已存在，请不要重复添加！", null);
        }

        property.setCreateEmp(getUid());
        property.setModifyEmp(getUid());

        return Result.getSuccessResult(propertyService.insertProperty(property));
    }
    /**
     * 后台更新商品属性
     * @param adminPropertyUpdateVO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateProperty.do_", method = RequestMethod.POST)
    public Result updateProperty(@Validated AdminPropertyUpdateVO adminPropertyUpdateVO) {

        Property property = new Property();
        BeanUtils.copyProperties(adminPropertyUpdateVO, property);

        //获取同商品下同名称的数量
        if (propertyService.getCountByGoodsIdAndName(property) > 1) {
            return Result.getBusinessException("该规格已存在，请不要重复添加！", null);
        }

        property.setModifyEmp(getUid());

        return Result.getSuccessResult(propertyService.updateProperty(property));
    }
    /**
     * 后台通过主键id删除商品属性和图片
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deletePropertyAndGoodsPicById.do_", method = RequestMethod.POST)
    public Result deletePropertyById(Long id) {

        //判断商品是否上架和该商品的商品属性数量
        if (propertyService.getGoodsStatusById(id) == 1 && propertyService.getGoodsIdCountById(id) == 1) {
            return Result.getBusinessException("该商品处于上架状态，不能删除商品唯一的属性!", null);
        }

        return Result.getSuccessResult(propertyService.deletePropertyAndGoodsPicById(id));
    }
}
