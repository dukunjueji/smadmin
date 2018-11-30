package com.uc.training.base.bd.service.impl;

import com.uc.training.base.bd.re.BannerRE;
import com.uc.training.base.bd.service.BannerService;
import com.uc.training.base.bd.vo.BannerListVO;
import com.uc.training.base.bd.vo.BannerVO;
import com.uc.training.remote.client.BaseClient;
import com.uc.training.smadmin.bd.dao.BannerDao;
import com.uc.training.smadmin.bd.re.AdminBannerListRE;
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

    /**
     * 获取轮播图信息(不包含不显示的图片)
     * @return
     */
    @Override
    public List<BannerRE> getBannerList(BannerVO bannerVO) {
        return BaseClient.getBannerList(bannerVO);
    }

    /**
     * 查询所有图片
     *
     * @return
     */
    @Override
    public List<BannerRE> getAllBannerList(BannerListVO adminBannerListVO) {
        return BaseClient.getAllBannerList(adminBannerListVO);
    }

    /**
     * 更新图片
     *
     * @param banner
     * @return
     */
    @Override
    public Integer updateBanner(BannerVO banner) {
        return BaseClient.updateBanner(banner);
    }

    /**
     * 删除图片
     *
     * @param id
     * @return
     */
    @Override
    public Integer deleteBannerById(Long id) {
        return BaseClient.deleteBannerById(id);
    }

    /**
     * 新增轮播图
     *
     * @param banner
     * @return
     */
    @Override
    public Long insertBanner(BannerVO banner) {
        return BaseClient.insertBanner(banner);
    }

    /**
     * 增加点击量
     */
    @Override
    public Integer insertClick(Long id) {
        return bannerDao.insertClick(id);
    }

    /**
     * 获取商品总数量
     *
     * @param adminBannerListVO
     * @return
     */
    @Override
    public Long getAdminBannerCount(BannerListVO adminBannerListVO) {
        return BaseClient.getBannerCount(BannerListVO);
    }
}
