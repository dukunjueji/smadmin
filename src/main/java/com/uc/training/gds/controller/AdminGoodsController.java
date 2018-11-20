package com.uc.training.gds.controller;

import com.uc.training.common.base.controller.BaseController;
import com.uc.training.common.enums.UUIDTypeEnum;
import com.uc.training.common.vo.PageVO;
import com.uc.training.smadmin.gds.model.Goods;
import com.uc.training.smadmin.gds.re.AdminGoodsRE;
import com.uc.training.smadmin.gds.service.GoodsPicService;
import com.uc.training.smadmin.gds.service.GoodsService;
import com.uc.training.smadmin.gds.service.PropertyService;
import com.uc.training.smadmin.gds.vo.AdminGoodsVO;
import com.uc.training.smadmin.gds.vo.AdminPullGoodsVO;
import com.uc.training.smadmin.gds.vo.AdminUpdateGoodsVO;
import com.uc.training.smadmin.gds.vo.GoodsListVO;
import com.uc.training.smadmin.utils.UUIDUtil;
import com.ycc.base.common.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;
import java.util.List;

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

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private GoodsPicService goodsPicService;

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
        pageVO.setTotal(goodsService.getAdminGoodsListCount(goodsListVO));
        pageVO.setDataList(goodsService.getAdminGoodsList(goodsListVO));

        return Result.getSuccessResult(pageVO);
    }

    /**
     * 新增商品
     * @param adminGoodsVO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "adminInsertGoods.do_", method = RequestMethod.POST)
    public Result adminInsertGoods(@Validated AdminGoodsVO adminGoodsVO) {

        Goods goods = new Goods();
        BeanUtils.copyProperties(adminGoodsVO, goods);

        //生成商品编号
        goods.setCode(UUIDUtil.getUuidByType(UUIDTypeEnum.GOODSID.getType()));
        goods.setCreateEmp(getUid());
        goods.setModifyEmp(getUid());

        return Result.getSuccessResult(goodsService.adminInsertGoods(goods));
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

    /**
     * 更新商品
     * @param adminUpdateGoodsVO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateGoods.do_", method = RequestMethod.POST)
    public Result updateGoods(@Validated AdminUpdateGoodsVO adminUpdateGoodsVO) {

        adminUpdateGoodsVO.setModifyEmp(getUid());

        return Result.getSuccessResult(goodsService.adminUpdateGoods(adminUpdateGoodsVO));
    }

    /**
     * 商品上架
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "pullOnGoods.do_", method = RequestMethod.POST)
    public Result pullOnGoods(@NotNull(message = "请选择商品!") Long id) {

        //判断商品商品属性数量
        List<Long> propertyIdList = propertyService.getPropertyIdListByGoodsId(id);
        if (CollectionUtils.isEmpty(propertyIdList)) {
            return Result.getBusinessException("请添加商品属性！", null);
        } else {
            for (Long propertyId : propertyIdList) {
                //判断商品属性的商品图片数量
                if (goodsPicService.getGoodsPicCountByPropertyId(propertyId) == 0) {
                    return Result.getBusinessException("商品属性中的图片信息不完整!", null);
                }
            }
        }

        AdminPullGoodsVO adminPullGoodsVO = new AdminPullGoodsVO();
        adminPullGoodsVO.setId(id);
        adminPullGoodsVO.setModifyEmp(getUid());

        return Result.getSuccessResult(goodsService.pullOnGoods(adminPullGoodsVO));
    }
    /**
     * 商品下架
     * @param adminPullGoodsVO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "pullOffGoods.do_", method = RequestMethod.POST)
    public Result pullOffGoods(@Validated AdminPullGoodsVO adminPullGoodsVO) {

        adminPullGoodsVO.setModifyEmp(getUid());

        return Result.getSuccessResult(goodsService.pullOffGoods(adminPullGoodsVO));
    }





}
