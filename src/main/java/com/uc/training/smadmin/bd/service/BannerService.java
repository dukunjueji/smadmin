package com.uc.training.smadmin.bd.service;

import com.uc.training.smadmin.bd.model.Banner;
import com.uc.training.smadmin.bd.re.AdminBannerListRE;
import com.uc.training.smadmin.bd.re.BannerRE;
import com.uc.training.smadmin.bd.vo.AdminBannerListVO;

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
    List<BannerRE> getBannerList();

    /**
     * 获取所有轮播图(后台)
     * @return
     */
    List<AdminBannerListRE> getAllBannerList(AdminBannerListVO adminBannerListVO);

    /**
     * 更新图片
     * @param banner
     * @return
     */
    Integer updateBanner(Banner banner);

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
    Long insertBanner(Banner banner);

    /**
     * 增加点击量
     * @param id
     */
    void insertClick(Long id);
}
