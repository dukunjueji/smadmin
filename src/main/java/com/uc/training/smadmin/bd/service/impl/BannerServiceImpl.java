package com.uc.training.smadmin.bd.service.impl;

import com.uc.training.smadmin.bd.dao.BannerDao;
import com.uc.training.smadmin.bd.model.Banner;
import com.uc.training.smadmin.bd.re.AdminBannerListRE;
import com.uc.training.smadmin.bd.re.BannerRE;
import com.uc.training.smadmin.bd.service.BannerService;
import com.uc.training.smadmin.bd.vo.AdminBannerListVO;
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
     * 获取轮播图信息(不包含不显示的图片)
     * @return
     */
    @Override
    public List<BannerRE> getBannerList() {
        return bannerDao.getBannerList();
    }

    /**
     * 查询所有图片
     *
     * @return
     */
    @Override
    public List<AdminBannerListRE> getAllBannerList(AdminBannerListVO adminBannerListVO) {
        return bannerDao.getAllBannerList(adminBannerListVO);
    }

    /**
     * 更新图片
     *
     * @param banner
     * @return
     */
    @Override
    public Integer updateBanner(Banner banner) {
        return bannerDao.updateBanner(banner);
    }

    /**
     * 删除图片
     *
     * @param id
     * @return
     */
    @Override
    public Integer deleteBannerById(Long id) {
        return bannerDao.deleteBannerById(id);
    }

    /**
     * 新增轮播图
     *
     * @param banner
     * @return
     */
    @Override
    public Long insertBanner(Banner banner) {
        return bannerDao.insertBanner(banner);
    }

    /**
     * 增加点击量
     */
    @Override
    public void insertClick(Long id) {
        bannerDao.insertClick(id);
    }
}
