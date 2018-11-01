package com.uc.training.smadmin.gds.service.impl;

import com.uc.training.smadmin.gds.dao.PropertyDao;
import com.uc.training.smadmin.gds.model.GoodsPic;
import com.uc.training.smadmin.gds.model.Property;
import com.uc.training.smadmin.gds.re.AdminPropertyListRE;
import com.uc.training.smadmin.gds.service.GoodsPicService;
import com.uc.training.smadmin.gds.service.PropertyService;
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
public class PropertyServiceImpl implements PropertyService{

    @Autowired
    private PropertyDao propertyDao;

    @Autowired
    private GoodsPicService goodsPicService;

    /**
     * 新增商品属性
     *
     * @param property
     * @return
     */
    @Override
    public Long insertProperty(Property property) {
        return propertyDao.insertProperty(property);
    }

    /**
     * 更新商品
     *
     * @param property
     * @return
     */
    @Override
    public Integer updateProperty(Property property) {
        return propertyDao.updateProperty(property);
    }

    /**
     * 通过商品id获取类型id
     *
     * @param goodsId
     * @return
     */
    @Override
    public List<Long> getPropertyIdListByGoodsId(Long goodsId) {
        return propertyDao.getPropertyIdListByGoodsId(goodsId);
    }

    /**
     * 通过商品id获取商品属性
     *
     * @param goodsId
     * @return
     */
    @Override
    public List<AdminPropertyListRE> getPropertyListByGoodsId(Long goodsId) {
        return propertyDao.getPropertyListByGoodsId(goodsId);
    }

    /**
     * 通过主键id删除属性和图片
     *
     * @param id
     * @return
     */
    @Override
    public Integer deletePropertyAndGoodsPicById(Long id) {
        goodsPicService.deleteGoodsPicByPropertyId(id);
        return propertyDao.deletePropertyById(id);
    }

    /**
     * 通过商品属性id获取商品的状态（上架，下架）
     *
     * @param id
     * @return
     */
    @Override
    public Integer getGoodsStatusById(Long id) {
        return propertyDao.getGoodsStatusById(id);
    }

    /**
     * 通过商品属性id获取商品的商品属性数量
     *
     * @param id
     * @return
     */
    @Override
    public Integer getGoodsIdCountById(Long id) {
        return propertyDao.getGoodsIdCountById(id);
    }
}
