package com.uc.training.smadmin.gds.controller;

import com.uc.training.common.base.controller.BaseController;
import com.uc.training.common.enums.UUIDTypeEnum;
import com.uc.training.common.vo.PageVO;
import com.uc.training.smadmin.gds.model.Goods;
import com.uc.training.smadmin.gds.model.GoodsPic;
import com.uc.training.smadmin.gds.model.Property;
import com.uc.training.smadmin.gds.re.AdminGoodsRE;
import com.uc.training.smadmin.gds.service.GoodsService;
import com.uc.training.smadmin.gds.vo.AdminGoodsVO;
import com.uc.training.smadmin.gds.vo.GoodsListVO;
import com.uc.training.smadmin.utils.UUIDUtil;
import com.ycc.base.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/29
 */
@Controller
@RequestMapping("admin/gds/goods")
public class AdminGoodsController extends BaseController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 后台查看所有商品
     * @param goodsListVO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "adminGetGoodsPage.do_", method = RequestMethod.POST)
    public Result<PageVO<AdminGoodsRE>> getAdminGoodsPage(GoodsListVO goodsListVO) {

        PageVO<AdminGoodsRE> pageVO = new PageVO<>();
        pageVO.setPageIndex(goodsListVO.getPageIndex());
        pageVO.setPageSize(goodsListVO.getPageSize());
        pageVO.setTotal(goodsService.getGoodsListCount(goodsListVO));
        pageVO.setDataList(goodsService.getAdminGoodsList(goodsListVO));

        return Result.getSuccessResult(pageVO);
    }

    /**
     * 新赠商品
     * @param adminGoodsVO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "adminInsertGoods.do_", method = RequestMethod.POST)
    public Result adminInsertGoods(AdminGoodsVO adminGoodsVO) {

        //新增商品
        Goods goods = new Goods();

        //生成商品编号
        goods.setCode(UUIDUtil.getUuidByType(UUIDTypeEnum.GOODSID.getType()));
        goods.setName(adminGoodsVO.getName());
        goods.setCategoryId(adminGoodsVO.getCategoryId());
        goods.setSales(adminGoodsVO.getSales());
        goods.setDetail(adminGoodsVO.getDetail());
        goods.setStatus(adminGoodsVO.getStatus());
        goods.setCreateEmp(getUid());
        goods.setModifyEmp(getUid());

        // 新增商品属性
        Property property = new Property();


        property.setCreateEmp(getUid());
        property.setModifyEmp(getUid());

        // 新增图片
        GoodsPic goodsPic = new GoodsPic();

        goodsPic.setCreateEmp(getUid());
        goodsPic.setModifyEmp(getUid());


        return Result.getSuccessResult(goodsService.adminInsertGoods(goods, property, goodsPic));
    }

    /**
     * 逻辑删除商品
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "logicDeleteGoods.do_", method = RequestMethod.POST)
    public Result logicDeleteGoods(Long id) {
        return Result.getSuccessResult(goodsService.logicDeleteGoods(id));
    }

}
