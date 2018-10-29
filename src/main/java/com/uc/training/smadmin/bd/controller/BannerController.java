package com.uc.training.smadmin.bd.controller;

import com.uc.training.common.base.controller.BaseController;
import com.uc.training.smadmin.bd.re.BannerAllRE;
import com.uc.training.smadmin.bd.re.BannerRE;
import com.ycc.base.common.Result;
import com.uc.training.common.annotation.AccessLogin;
import com.uc.training.smadmin.bd.model.Banner;
import com.uc.training.smadmin.bd.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/22
 */
@Controller
@RequestMapping("/api/banner")
public class BannerController extends BaseController{

    @Autowired
    private BannerService bannerService;

    /**
     * 前台获取广告信息（不包括不显示的广告）
     * @return
     */
    @ResponseBody
    @AccessLogin(required = false)
    @RequestMapping(value = "/getBannerList.do_", method = RequestMethod.GET)
    public Result<List<BannerRE>> getBannerList() {
        return Result.getSuccessResult(bannerService.getBannerList());
    }

    /**
     * 统计点击次数
     * @return
     */
    @ResponseBody
    @AccessLogin(required = false)
    @RequestMapping(value = "/insertClick.do_", method = RequestMethod.GET)
    public void insertClick(Long id) {
        bannerService.insertClick(id);
    }

    /**
     * 后台获取广告信息（包括不显示的广告）
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAllBannerList.do_", method = RequestMethod.POST)
    public Result<List<BannerAllRE>> getAllBannerList() {
        return Result.getSuccessResult(bannerService.getAllBannerList());
    }

    /**
     * 更新图片
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateBanner.do_", method = RequestMethod.POST)
    public Result updateBanner(Banner banner) {
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
     * @param banner
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insertBannerById.do_", method = RequestMethod.POST)
    public Result insertBannerById(Banner banner) {

        banner.setCreateEmp(getUid());
        banner.setModifyEmp(getUid());

        return Result.getSuccessResult(bannerService.insertBannerById(banner));
    }
}
