package com.uc.training.smadmin.bd.controller;

import com.ycc.base.common.Result;
import com.uc.training.common.annotation.AccessLogin;
import com.uc.training.smadmin.bd.model.Banner;
import com.uc.training.smadmin.bd.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @ResponseBody
    @RequestMapping("/getBannerList.do_")
    public Result<List<Banner>> getBannerList() {
        Result result =  Result.getSuccessResult(bannerService.getBannerList());
        return result;
    }
}
