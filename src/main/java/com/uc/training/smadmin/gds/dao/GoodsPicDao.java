package com.uc.training.smadmin.gds.dao;

import com.uc.training.smadmin.gds.model.GoodsPic;
import com.uc.training.smadmin.gds.re.AdminGoodsPicRE;

import java.util.List;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/29
 */
public interface GoodsPicDao {

    /**
     * 新增商品属性
     * @param goodsPic
     * @return
     */
    Long insertGoodsPic(GoodsPic goodsPic);

    /**
     * 更新商品属性
     * @param goodsPic
     * @return
     */
    Integer updateGoodsPic(GoodsPic goodsPic);

    /**
     * 根据商品属性id获取商品图片
     * @param propertyId
     * @return
     */
    List<AdminGoodsPicRE> getGoodsPicListByPropertyId(Long propertyId);

    /**
     * 通过商品属性id删除图片
     * @param propertyId
     * @return
     */
    Integer deleteGoodsPicByPropertyId(Long propertyId);

    /**
     * 通过主键id删除图片
     * @param id
     * @return
     */
    Integer deleteGoodsPicById(Long id);

    /**
     * 后台通过图片id获取表中对应商品属性的的数量
     * @param id
     * @return
     */
    Integer getPropertyIdCountById(Long id);

    /**
     * 通过主键id查找商品状态（1：上架，0：下架）
     * @param id
     * @return
     */
    Integer getGoodsStatusById(Long id);

    /**
     * 通过商品属性id查找商品图片的数量
     * @param propertyId
     * @return
     */
    Integer getGoodsPicCountByPropertyId(Long propertyId);
}
