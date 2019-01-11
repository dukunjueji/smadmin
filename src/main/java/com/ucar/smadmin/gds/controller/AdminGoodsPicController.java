package com.ucar.smadmin.gds.controller;

import com.ucar.smadmin.common.base.controller.BaseController;

import com.ucar.smadmin.common.vo.Result;
import com.ucar.smadmin.gds.dto.GoodsPicDTO;
import com.ucar.smadmin.gds.re.AdminGoodsPicRE;
import com.ucar.smadmin.gds.service.GoodsPicService;
import com.ucar.smadmin.gds.service.PropertyService;
import com.ucar.smadmin.ord.service.OrderGoodsService;
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
@RequestMapping("admin/gds/goodsPic")
public class AdminGoodsPicController extends BaseController {

    @Autowired
    private GoodsPicService goodsPicService;

    @Autowired
    private OrderGoodsService orderGoodsService;

    @Autowired
    private GoodsPicService goodsService;

    @Autowired
    private PropertyService propertyService;
    /**
     * 后台通过商品属性id获取图片
     * @param propertyId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getGoodsPicListByPropertyId.do_", method = RequestMethod.POST)
    public Result<List<AdminGoodsPicRE>> getGoodsPicListByPropertyId(Long propertyId) {
        return Result.getSuccessResult(goodsPicService.getGoodsPicListByPropertyId(propertyId));
    }
    /**
     * 后台新增商品属性图片
     * @param goodsPic
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "insertGoodsPic.do_", method = RequestMethod.POST)
    public Result insertGoodsPic(@Validated GoodsPicDTO goodsPic) {

        goodsPic.setCreateEmp(getUid());
        goodsPic.setModifyEmp(getUid());

        return Result.getSuccessResult(goodsPicService.insertGoodsPic(goodsPic));
    }
    /**
     * 后台更新商品属性图片
     * @param goodsPic
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateGoodsPic.do_", method = RequestMethod.POST)
    public Result updateGoodsPic(@Validated GoodsPicDTO goodsPic) {

        goodsPic.setModifyEmp(getUid());

        return Result.getSuccessResult(goodsPicService.updateGoodsPic(goodsPic));
    }
    /**
     * 后台通过主键id删除商品属性图片
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deleteGoodsPicById.do_", method = RequestMethod.POST)
    public Result deleteGoodsPicById(Long id) {

        //判断该商品属性的图片数量
        if (goodsPicService.getPropertyIdCountById(id) == 1) {
            //判断商品是否上架
            if (goodsService.getGoodsById(propertyService.getPropertyById
            (goodsPicService.getPropertyIdById(id)).getGoodsId()).getStatus() == 1) {
                return Result.getBusinessException("商品处于上架状态，商品属性中至少有一张图片！", null);
            }
            //判断商品属性是否存在订单
            if (orderGoodsService.getUnPayGoodsPropertyCountByPropertyId(goodsPicService.getPropertyIdById(id)) >= 1L) {
                return Result.getBusinessException("该商品属性存在待付款的订单，至少有一张图片！", null);
            }
        }

        return Result.getSuccessResult(goodsPicService.deleteGoodsPicById(id));
    }
}
