package com.uc.training.gds.service.impl;

import com.uc.training.gds.dto.PropertyDTO;
import com.uc.training.gds.re.GoodsDetailRE;
import com.uc.training.gds.service.GoodsPicService;
import com.uc.training.gds.service.PropertyService;
import com.uc.training.remote.client.GdsClient;
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
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private GoodsPicService goodsPicService;

    /**
     * 新增商品属性
     *
     * @param property
     * @return
     */
    @Override
    public Long insertProperty(PropertyDTO property) {
        return GdsClient.insertProperty(property);
    }

    /**
     * 更新商品
     *
     * @param property
     * @return
     */
    @Override
    public Integer updateProperty(PropertyDTO property) {
        return GdsClient.updateProperty(property);
    }

    /**
     * 通过商品id获取类型id
     *
     * @param goodsId
     * @return
     */
    @Override
    public List<Long> getPropertyIdListByGoodsId(Long goodsId) {
        return GdsClient.getPropertyIdListByGoodsId(goodsId);
    }

    /**
     * 通过商品id获取商品属性
     *
     * @param goodsId
     * @return
     */
    @Override
    public List<GoodsDetailRE> getPropertyListByGoodsId(Long goodsId) {
        return GdsClient.getPropertyListByGoodsId(goodsId);
    }

    /**
     * 通过主键id删除属性和图片
     *
     * @param id
     * @return
     */
    @Override
    public Integer deletePropertyAndGoodsPicById(Long id) {
        return GdsClient.deleteGoodsPicByPropertyId(id);
    }

    /**
     * 获取商品该名称规格的数量
     *
     * @param property
     * @return
     */
    @Override
    public Integer getCountByProperty(PropertyDTO property) {
        return GdsClient.getCountByProperty(property);
    }

    /**
     * 通过id获取属性
     *
     * @param id
     * @return
     */
    @Override
    public GoodsDetailRE getPropertyById(Long id) {
        return GdsClient.getPropertyById(id);
    }
}
