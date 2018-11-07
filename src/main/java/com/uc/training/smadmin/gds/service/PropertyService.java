package com.uc.training.smadmin.gds.service;

import com.uc.training.smadmin.gds.model.Property;
import com.uc.training.smadmin.gds.re.AdminPropertyListRE;
import com.uc.training.smadmin.gds.vo.AdminPropertyVO;

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
    Long insertProperty(Property property);

    /**
     * 更新商品
     * @param property
     * @return
     */
    Integer updateProperty(Property property);

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
    List<AdminPropertyListRE> getPropertyListByGoodsId(Long goodsId);

    /**
     * 通过主键id删除属性和图片
     * @param id
     * @return
     */
    Integer deletePropertyAndGoodsPicById(Long id);

    /**
     * 通过商品属性id获取商品的状态（上架，下架）
     * @param id
     * @return
     */
    Integer getGoodsStatusById(Long id);

    /**
     * 通过商品属性id获取商品的商品属性数量
     * @param id
     * @return
     */
    Integer getGoodsIdCountById(Long id);

    /**
     * 获取商品该名称规格的数量
     * @param adminPropertyVO
     * @return
     */
    Integer getCountByGoodsIdAndName(AdminPropertyVO adminPropertyVO);
}
