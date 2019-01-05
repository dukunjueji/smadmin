package com.uc.training.base.bd.service.impl;

import com.uc.training.base.bd.dto.BannerDTO;
import com.uc.training.base.bd.re.BannerRE;
import com.uc.training.base.bd.service.BannerService;
import com.uc.training.base.bd.vo.BannerListVO;
import com.uc.training.base.bd.vo.BannerVO;
import com.uc.training.remote.client.BaseClient;
import org.springframework.beans.BeanUtils;
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
private BaseClient baseClient;
    /**
     * 获取轮播图信息(不包含不显示的图片)
     * @return
     */
    @Override
    public List<BannerRE> getBannerList(BannerVO bannerVO) {
        BannerDTO bannerDTO = new BannerDTO();
        BeanUtils.copyProperties(bannerVO, bannerDTO);
        return baseClient.getBannerList(bannerDTO).getRe();
    }

    /**
     * 查询所有图片
     *
     * @return
     */
    @Override
    public List<BannerRE> getAllBannerList(BannerListVO adminBannerListVO) {
        BannerDTO bannerDTO = new BannerDTO();
        BeanUtils.copyProperties(adminBannerListVO, bannerDTO);
        return baseClient.getAllBannerList(bannerDTO).getRe();
    }

    /**
     * 更新图片
     *
     * @param banner
     * @return
     */
    @Override
    public Integer updateBanner(BannerVO banner) {
        BannerDTO bannerDTO = new BannerDTO();
        BeanUtils.copyProperties(banner, bannerDTO);
        return baseClient.updateBanner(bannerDTO).getRe();
    }

    /**
     * 删除图片
     *
     * @param id
     * @return
     */
    @Override
    public Integer deleteBannerById(Long id) {
        return baseClient.deleteBannerById(id).getRe();
    }

    /**
     * 新增轮播图
     *
     * @param banner
     * @return
     */
    @Override
    public Long insertBanner(BannerVO banner) {
        BannerDTO bannerDTO = new BannerDTO();
        BeanUtils.copyProperties(banner, bannerDTO);
        return baseClient.insertBanner(bannerDTO).getRe();
    }

    /**
     * 获取图片数量
     *
     * @param bannerListVO
     * @return
     */
    @Override
    public Long getAdminBannerCount(BannerListVO bannerListVO) {
        BannerDTO bannerDTO = new BannerDTO();
        BeanUtils.copyProperties(bannerListVO, bannerDTO);
        return baseClient.getBannerCount(bannerDTO).getRe();
    }
}
