package com.uc.training.smadmin.bd.service;

import com.uc.training.smadmin.bd.model.Banner;

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
     * 获取轮播图信息
     * @return
     */
    List<Banner> getBannerList();
}
