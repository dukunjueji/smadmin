package com.uc.training.base.bd.service;

import com.uc.training.base.bd.re.BannerRE;
import com.uc.training.base.bd.vo.BannerListVO;
import com.uc.training.base.bd.vo.BannerVO;

import java.util.List;

/**
 * 版权声明： Copyright (c) 2008 Mochasoft co. All Rights Reserved.
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/16 10:11
 * 说明：广告接口
 */
public interface BannerService {
    /**
     * 获取轮播图信息(不包含不显示的图片)
     * @return
     */
    List<BannerRE> getBannerList(BannerVO bannerVO);

    /**
     * 获取所有轮播图(后台)
     * @param adminBannerListVO
     * @return
     */
    List<BannerRE> getAllBannerList(BannerListVO adminBannerListVO);

    /**
     * 更新图片
     * @param banner
     * @return
     */
    Integer updateBanner(BannerVO banner);

    /**
     * 删除图片
     * @param id
     * @return
     */
    Integer deleteBannerById(Long id);

    /**
     * 新增轮播图
     * @param banner
     * @return
     */
    Long insertBanner(BannerVO banner);

    /**
     * 增加点击量
     * @param id
     * @return
     */
    Integer insertClick(Long id);

    /**
     * 获取商品总数量
     * @param adminBannerListVO
     * @return
     */
    Long getAdminBannerCount(BannerListVO adminBannerListVO);
}
