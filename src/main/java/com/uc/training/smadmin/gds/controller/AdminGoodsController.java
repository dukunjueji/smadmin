package com.uc.training.smadmin.gds.controller;

import com.uc.training.common.base.controller.BaseController;
import com.uc.training.common.vo.PageVO;
import com.uc.training.smadmin.gds.re.AdminGoodsRE;
import com.uc.training.smadmin.gds.service.GoodsService;
import com.uc.training.smadmin.gds.vo.AdminGoodsVO;
import com.uc.training.smadmin.gds.vo.GoodsListVO;
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

        adminGoodsVO.setCreateEmp(getUid());
        adminGoodsVO.setModifyEmp(getUid());

        return Result.getSuccessResult(goodsService.adminInsertGoods(adminGoodsVO));
    }

}
