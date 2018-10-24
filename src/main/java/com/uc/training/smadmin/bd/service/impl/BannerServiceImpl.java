package com.uc.training.smadmin.bd.service.impl;

import com.uc.training.smadmin.bd.dao.BannerDao;
import com.uc.training.smadmin.bd.model.Banner;
import com.uc.training.smadmin.bd.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/16 10:12
 * 说明：广告接口实现
 */
@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDao bannerDao;

    /**
     * 获取广告列表信息
     * @return
     */
    @Override
    public List<Banner> getBannerList() {
        return bannerDao.getBannerList();
    }
}
