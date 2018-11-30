package com.uc.training.gds.service;

import com.uc.training.gds.dto.PropertyDTO;
import com.uc.training.gds.re.GoodsDetailRE;

import java.util.List;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/30
 */
public interface PropertyService {
    /**
     * 新增商品属性
     * @param property
     * @return
     */
    Long insertProperty(PropertyDTO property);

    /**
     * 更新商品
     * @param property
     * @return
     */
    Integer updateProperty(PropertyDTO property);

    /**
     * 通过商品id获取类型id
     * @param goodsId
     * @return
     */
    List<Long> getPropertyIdListByGoodsId(Long goodsId);

    /**
     * 通过商品id获取商品属性
     * @param goodsId
     * @return
     */
    List<GoodsDetailRE> getPropertyListByGoodsId(Long goodsId);

    /**
     * 通过主键id删除属性和图片
     * @param id
     * @return
     */
    Integer deletePropertyAndGoodsPicById(Long id);

    /**
     * 获取商品该名称规格的数量
     * @param property
     * @return
     */
    Integer getCountByProperty(PropertyDTO property);

    /**
     * 通过id获取属性
     * @param id
     * @return
     */
    GoodsDetailRE getPropertyById(Long id);
}
