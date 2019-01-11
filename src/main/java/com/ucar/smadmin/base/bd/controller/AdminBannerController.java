package com.ucar.smadmin.base.bd.controller;

import com.ucar.smadmin.base.bd.re.BannerRE;
import com.ucar.smadmin.base.bd.service.BannerService;
import com.ucar.smadmin.base.bd.vo.BannerListVO;
import com.ucar.smadmin.base.bd.vo.BannerVO;
import com.ucar.smadmin.common.base.controller.BaseController;
import com.ucar.smadmin.common.vo.PageVO;
import com.ucar.smadmin.common.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
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
    public Result<PageVO<BannerRE>> getAllBannerList(BannerListVO bannerListVO) {

        PageVO<BannerRE> pageVO = new PageVO<>();
        pageVO.setPageIndex(bannerListVO.getPageIndex());
        pageVO.setPageSize(bannerListVO.getPageSize());
        pageVO.setTotal(bannerService.getAdminBannerCount(bannerListVO));
        pageVO.setDataList(bannerService.getAllBannerList(bannerListVO));

        return Result.getSuccessResult(pageVO);
    }

    /**
     * 更新图片
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateBanner.do_", method = RequestMethod.POST)
    public Result updateBanner(@Validated BannerVO bannerVO) {
        if (bannerVO.getId() == null) {
            return Result.getBusinessException("请重新修改图片！", null);
        }
        bannerVO.setModifyEmp(getUid());
        bannerVO.setClicks(null);
        return Result.getSuccessResult(bannerService.updateBanner(bannerVO));
    }

    /**
     * 根据主键id删除图片
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteBannerById.do_", method = RequestMethod.POST)
    public Result deleteBannerById(Long id) {
        if (id == null) {
            return Result.getBusinessException("请重新选择图片！", null);
        }
        return Result.getSuccessResult(bannerService.deleteBannerById(id));
    }

    /**
     * 新增轮播图
     * @param bannerVO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insertBanner.do_", method = RequestMethod.POST)
    public Result insertBanner(@Validated BannerVO bannerVO) {
        bannerVO.setCreateEmp(getUid());
        bannerVO.setModifyEmp(getUid());
        return Result.getSuccessResult(bannerService.insertBanner(bannerVO));
    }

}
