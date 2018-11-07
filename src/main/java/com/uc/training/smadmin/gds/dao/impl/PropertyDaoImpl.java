package com.uc.training.smadmin.gds.dao.impl;

import com.uc.training.smadmin.gds.dao.PropertyDao;
import com.uc.training.smadmin.gds.model.Property;
import com.uc.training.smadmin.gds.re.AdminPropertyListRE;
import com.uc.training.smadmin.gds.vo.AdminPropertyVO;
import com.zuche.framework.dao.CarIsIbatisDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/10/29
 */
@Repository
public class PropertyDaoImpl extends CarIsIbatisDaoImpl implements PropertyDao{
    /**
     * 新增商品属性
     *
     * @param property
     * @return
     */
    @Override
    public Long insertProperty(Property property) {
        return (Long) this.insert("com.uc.training.smadmin.gds.dao.PropertyDao.insertProperty", property);
    }
    /**
     * 更新商品属性
     *
     * @param property
     * @return
     */
    @Override
    public Integer updateProperty(Property property) {
        return update("com.uc.training.smadmin.gds.dao.PropertyDao.updateProperty", property);
    }

    /**
     * 通过商品id获取商品属性
     *
     * @param goodsId
     * @return
     */
    @Override
    public List<Long> getPropertyIdListByGoodsId(Long goodsId) {
        return this.queryForList("com.uc.training.smadmin.gds.dao.PropertyDao.getPropertyIdListByGoodsId", goodsId);
    }

    /**
     * 通过商品id获取商品所有属性
     *
     * @param goodsId
     * @return
     */
    @Override
    public List<AdminPropertyListRE> getPropertyListByGoodsId(Long goodsId) {
        return this.queryForList("com.uc.training.smadmin.gds.dao.PropertyDao.getPropertyListByGoodsId", goodsId);
    }

    /**
     * 通过主键id删除商品属性
     * @param id
     * @return
     */
    @Override
    public Integer deletePropertyById(Long id) {
        return this.deleteObject("com.uc.training.smadmin.gds.dao.PropertyDao.deletePropertyById", id);
    }

    /**
     * 通过商品属性id获取商品的状态（上架，下架）
     *
     * @param id
     * @return
     */
    @Override
    public Integer getGoodsStatusById(Long id) {
        return (Integer) this.queryForObject("com.uc.training.smadmin.gds.dao.PropertyDao.getGoodsStatusById", id);
    }

    /**
     * 通过商品属性id获取商品的商品属性数量
     *
     * @param id
     * @return
     */
    @Override
    public Integer getGoodsIdCountById(Long id) {
        return (Integer) this.queryForObject("com.uc.training.smadmin.gds.dao.PropertyDao.getGoodsIdCountById", id);
    }

    /**
     * 获取商品该名称规格的数量
     *
     * @param adminPropertyVO
     * @return
     */
    @Override
    public Integer getCountByGoodsIdAndName(AdminPropertyVO adminPropertyVO) {
        return (Integer) this.queryForObject("com.uc.training.smadmin.gds.dao.PropertyDao.getCountByGoodsIdAndName", adminPropertyVO);
    }
}
