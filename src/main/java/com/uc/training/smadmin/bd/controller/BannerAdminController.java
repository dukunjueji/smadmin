package com.uc.training.smadmin.bd.controller;

import com.uc.training.common.base.controller.BaseController;
import com.uc.training.smadmin.bd.re.BannerAdminRE;
import com.uc.training.smadmin.bd.service.BannerService;
import com.ycc.base.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class BannerAdminController extends BaseController {

    @Autowired
    private BannerService bannerService;


    /**
     * 后台获取广告信息（包括不显示的广告）
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAllBannerList.do_", method = RequestMethod.POST)
    public Result<List<BannerAdminRE>> getAllBannerList() {
        return Result.getSuccessResult(bannerService.getAllBannerList());
    }

    /**
     * 更新图片
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateBanner.do_", method = RequestMethod.POST)
    public Result updateBanner(BannerAdminRE bannerAdminRE) {
        return Result.getSuccessResult(bannerService.updateBanner(bannerAdminRE));
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
     * @param bannerAdminRE
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insertBannerById.do_", method = RequestMethod.POST)
    public Result insertBannerById(BannerAdminRE bannerAdminRE) {

        bannerAdminRE.setCreateEmp(getUid());
        bannerAdminRE.setModifyEmp(getUid());

        return Result.getSuccessResult(bannerService.insertBannerById(bannerAdminRE));
    }

}
