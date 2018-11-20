package com.uc.training.gds.controller;

import com.uc.training.common.base.controller.BaseController;
import com.uc.training.smadmin.gds.model.GoodsPic;
import com.uc.training.smadmin.gds.re.AdminGoodsPicRE;
import com.uc.training.smadmin.gds.service.GoodsPicService;
import com.uc.training.smadmin.gds.vo.AdminInsertGoodsPicVO;
import com.uc.training.smadmin.gds.vo.AdminUpdateGoodsPicVO;
import com.uc.training.smadmin.ord.service.OrderGoodsService;
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
@RequestMapping("admin/gds/goodsPic")
public class AdminGoodsPicController extends BaseController{

    @Autowired
    private GoodsPicService goodsPicService;

    @Autowired
    private OrderGoodsService orderGoodsService;

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
     * @param adminInsertGoodsPicVO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "insertGoodsPic.do_", method = RequestMethod.POST)
    public Result insertGoodsPic(@Validated AdminInsertGoodsPicVO adminInsertGoodsPicVO) {

        GoodsPic goodsPic = new GoodsPic();
        BeanUtils.copyProperties(adminInsertGoodsPicVO, goodsPic);

        goodsPic.setCreateEmp(getUid());
        goodsPic.setModifyEmp(getUid());

        return Result.getSuccessResult(goodsPicService.insertGoodsPic(goodsPic));
    }
    /**
     * 后台更新商品属性图片
     * @param adminUpdateGoodsPicVO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateGoodsPic.do_", method = RequestMethod.POST)
    public Result updateGoodsPic(@Validated AdminUpdateGoodsPicVO adminUpdateGoodsPicVO) {

        GoodsPic goodsPic = new GoodsPic();
        BeanUtils.copyProperties(adminUpdateGoodsPicVO, goodsPic);

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
            if (goodsPicService.getGoodsStatusById(id) == 1) {
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
