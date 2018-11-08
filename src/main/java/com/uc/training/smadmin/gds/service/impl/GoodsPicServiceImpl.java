package com.uc.training.smadmin.gds.service.impl;

import com.uc.training.smadmin.gds.dao.GoodsPicDao;
import com.uc.training.smadmin.gds.model.GoodsPic;
import com.uc.training.smadmin.gds.re.AdminGoodsPicRE;
import com.uc.training.smadmin.gds.service.GoodsPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/30
 */
@Service
public class GoodsPicServiceImpl implements GoodsPicService{

    @Autowired
    private GoodsPicDao goodsPicDao;

    /**
     * 新增商品图片
     *
     * @param goodsPic
     * @return
     */
    @Override
    public Long insertGoodsPic(GoodsPic goodsPic) {
        return goodsPicDao.insertGoodsPic(goodsPic);
    }

    /**
     * 更新商品图片
     *
     * @param goodsPic
     * @return
     */
    @Override
    public Integer updateGoodsPic(GoodsPic goodsPic) {
        return goodsPicDao.updateGoodsPic(goodsPic);
    }

    /**
     * 根据商品属性id获取图片信息
     *
     * @param propertyId
     * @return
     */
    @Override
    public List<AdminGoodsPicRE> getGoodsPicListByPropertyId(Long propertyId) {
        return goodsPicDao.getGoodsPicListByPropertyId(propertyId);
    }

    /**
     * 通过主键id删除商品属性图片
     *
     * @param id
     * @return
     */
    @Override
    public Integer deleteGoodsPicById(Long id) {
        return goodsPicDao.deleteGoodsPicById(id);
    }

    /**
     * 通过商品属性id删除图片
     *
     * @param propertyId
     * @return
     */
    @Override
    public Integer deleteGoodsPicByPropertyId(Long propertyId) {
        return goodsPicDao.deleteGoodsPicByPropertyId(propertyId);
    }

    /**
     * 后台通过图片id获取表中对应商品属性的的数量
     *
     * @param id
     * @return
     */
    @Override
    public Integer getPropertyIdCountById(Long id) {
        return goodsPicDao.getPropertyIdCountById(id);
    }

    /**
     * 通过主键id查找商品状态（1：上架，0：下架）
     *
     * @param id
     * @return
     */
    @Override
    public Integer getGoodsStatusById(Long id) {
        return goodsPicDao.getGoodsStatusById(id);
    }

    /**
     * 通过商品属性id查找商品图片的数量
     *
     * @param propertyId
     * @return
     */
    @Override
    public Integer getGoodsPicCountByPropertyId(Long propertyId) {
        return goodsPicDao.getGoodsPicCountByPropertyId(propertyId);
    }

    /**
     * 通过主键id获取商品属性id
     *
     * @param id
     * @return
     */
    @Override
    public Long getPropertyIdById(Long id) {
        return goodsPicDao.getPropertyIdById(id);
    }
}
