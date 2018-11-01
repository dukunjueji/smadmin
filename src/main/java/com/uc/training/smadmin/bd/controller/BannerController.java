package com.uc.training.smadmin.bd.controller;

import com.uc.training.common.base.controller.BaseController;
import com.uc.training.smadmin.bd.re.BannerRE;
import com.ycc.base.common.Result;
import com.uc.training.common.annotation.AccessLogin;
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
    public Result insertClick(Long id) {
        return Result.getSuccessResult(bannerService.insertClick(id));
    }

}
