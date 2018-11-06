package com.uc.training.smadmin.bd.controller;

import com.uc.training.common.base.controller.BaseController;
import com.uc.training.common.vo.PageVO;
import com.uc.training.smadmin.bd.model.Banner;
import com.uc.training.smadmin.bd.re.AdminBannerListRE;
import com.uc.training.smadmin.bd.service.BannerService;
import com.uc.training.smadmin.bd.vo.AdminBannerListVO;
import com.uc.training.smadmin.bd.vo.AdminInsertBannerVO;
import com.uc.training.smadmin.bd.vo.AdminUpdateBannerVO;
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
 * @date 2018/10/29
 */
@Controller
@RequestMapping("/admin/banner")
public class AdminBannerController extends BaseController {

    @Autowired
    private BannerService bannerService;


    /**
     * 后台获取广告信息（包括不显示的广告）
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAllBannerList.do_", method = RequestMethod.POST)
    public Result<PageVO<AdminBannerListRE>> getAllBannerList(AdminBannerListVO adminBannerListVO) {

        PageVO<AdminBannerListRE> pageVO = new PageVO<>();
        pageVO.setPageIndex(adminBannerListVO.getPageIndex());
        pageVO.setPageSize(adminBannerListVO.getPageSize());
        pageVO.setTotal(bannerService.getAdminBannerCount(adminBannerListVO));
        pageVO.setDataList(bannerService.getAllBannerList(adminBannerListVO));

        return Result.getSuccessResult(pageVO);
    }

    /**
     * 更新图片
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateBanner.do_", method = RequestMethod.POST)
    public Result updateBanner(@Validated AdminUpdateBannerVO adminUpdateBannerVO) {

        Banner banner = new Banner();
        BeanUtils.copyProperties(adminUpdateBannerVO, banner);

        banner.setModifyEmp(getUid());

        return Result.getSuccessResult(bannerService.updateBanner(banner));
    }

    /**
     * 根据主键id删除图片
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteBannerById.do_", method = RequestMethod.POST)
    public Result updateBanner(Long id) {
        return Result.getSuccessResult(bannerService.deleteBannerById(id));
    }

    /**
     * 新增轮播图
     * @param adminInsertBannerVO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insertBanner.do_", method = RequestMethod.POST)
    public Result insertBannerById(@Validated AdminInsertBannerVO adminInsertBannerVO) {

        Banner banner = new Banner();
        BeanUtils.copyProperties(adminInsertBannerVO, banner);

        banner.setCreateEmp(getUid());
        banner.setModifyEmp(getUid());

        return Result.getSuccessResult(bannerService.insertBanner(banner));
    }

}
