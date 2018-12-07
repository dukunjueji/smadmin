package com.uc.training.base.bd.controller;

import com.uc.training.base.bd.re.BannerRE;
import com.uc.training.base.bd.service.BannerService;
import com.uc.training.base.bd.vo.BannerVO;
import com.uc.training.common.annotation.AccessLogin;
import com.uc.training.common.base.controller.BaseController;
import com.ycc.base.common.Result;
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

    /**显示轮播图*/
    private static final Integer SHOW_BANNER = 1;
    /**图片类型，轮播图*/
    private static final Integer BANNER_TYPE = 1;
    /**点击次数*/
    private static final Integer CLICK = 1;


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
        BannerVO bannerVO = new BannerVO();
        bannerVO.setIsShow(SHOW_BANNER);
        bannerVO.setType(BANNER_TYPE);
        return Result.getSuccessResult(bannerService.getBannerList(bannerVO));
    }

    /**
     * 统计点击次数
     * @return
     */
    @ResponseBody
    @AccessLogin(required = false)
    @RequestMapping(value = "/insertClick.do_", method = RequestMethod.GET)
    public Result insertClick(Long id) {
        if (id == null) {
            return Result.getBusinessException("无法获取图片！", null);
        }
        BannerVO bannerVO = new BannerVO();
        bannerVO.setId(id);
        bannerVO.setClicks(CLICK);
        return Result.getSuccessResult(bannerService.updateBanner(bannerVO));
    }

}
