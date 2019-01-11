package com.ucar.smadmin.gds.service.impl;


import com.ucar.smadmin.gds.dto.GoodsPicDTO;
import com.ucar.smadmin.gds.re.AdminGoodsPicRE;
import com.ucar.smadmin.gds.re.GoodsRE;
import com.ucar.smadmin.gds.service.GoodsPicService;
import com.ucar.smadmin.remote.client.GdsClient;
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
public class GoodsPicServiceImpl implements GoodsPicService {
    @Autowired
    GdsClient gdsClient;

    /**
     * 新增商品图片
     *
     * @param goodsPic
     * @return
     */
    @Override
    public Long insertGoodsPic(GoodsPicDTO goodsPic) {
        return gdsClient.insertGoodsPic(goodsPic);
    }

    /**
     * 更新商品图片
     *
     * @param goodsPic
     * @return
     */
    @Override
    public Integer updateGoodsPic(GoodsPicDTO goodsPic) {
        return gdsClient.updateGoodsPic(goodsPic);
    }

    /**
     * 根据商品属性id获取图片信息
     *
     * @param propertyId
     * @return
     */
    @Override
    public List<AdminGoodsPicRE> getGoodsPicListByPropertyId(Long propertyId) {
        return gdsClient.getGoodsPicListByPropertyId(propertyId);
    }

    /**
     * 通过主键id删除商品属性图片
     *
     * @param id
     * @return
     */
    @Override
    public Integer deleteGoodsPicById(Long id) {
        return gdsClient.deleteGoodsPicById(id);
    }

    /**
     * 通过商品属性id删除图片
     *
     * @param propertyId
     * @return
     */
    @Override
    public Integer deleteGoodsPicByPropertyId(Long propertyId) {
        return gdsClient.deleteGoodsPicByPropertyId(propertyId);
    }

    /**
     * 后台通过图片id获取表中对应商品属性的的数量
     *
     * @param id
     * @return
     */
    @Override
    public Integer getPropertyIdCountById(Long id) {
        return gdsClient.getPropertyIdCountById(id);
    }

    /**
     * 通过商品属性id查找商品图片的数量
     *
     * @param goodsPic
     * @return
     */
    @Override
    public Integer getCountByGoodsPic(GoodsPicDTO goodsPic) {
        return gdsClient.getCountByGoodsPic(goodsPic);
    }

    /**
     * 通过主键id获取商品属性id
     *
     * @param id
     * @return
     */
    @Override
    public Long getPropertyIdById(Long id) {
        return gdsClient.getPropertyIdById(id);
    }

    /**
     * 根据id获取商品信息
     *
     * @param id
     * @return
     */
    @Override
    public GoodsRE getGoodsById(Long id) {
        return gdsClient.getGoodsById(id);
    }
}
