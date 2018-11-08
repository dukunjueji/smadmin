package com.uc.training.smadmin.gds.dao.impl;

import com.uc.training.smadmin.gds.dao.GoodsPicDao;
import com.uc.training.smadmin.gds.model.GoodsPic;
import com.uc.training.smadmin.gds.re.AdminGoodsPicRE;
import com.zuche.framework.dao.CarIsIbatisDaoImpl;
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
public class GoodsPicDaoImpl extends CarIsIbatisDaoImpl implements GoodsPicDao {

    /**
     * 新增商品属性
     *
     * @param goodsPic
     * @return
     */
    @Override
    public Long insertGoodsPic(GoodsPic goodsPic) {
        return (Long) this.insert("com.uc.training.smadmin.gds.dao.GoodsPicDao.insertGoodsPic", goodsPic);
    }

    /**
     * 更新商品属性
     *
     * @param goodsPic
     * @return
     */
    @Override
    public Integer updateGoodsPic(GoodsPic goodsPic) {
        return this.update("com.uc.training.smadmin.gds.dao.GoodsPicDao.updateGoodsPic", goodsPic);
    }

    /**
     * 根据商品属性id获取商品图片
     *
     * @param propertyId
     * @return
     */
    @Override
    public List<AdminGoodsPicRE> getGoodsPicListByPropertyId(Long propertyId) {
        return this.queryForList("com.uc.training.smadmin.gds.dao.GoodsPicDao.getGoodsPicListByPropertyId", propertyId);
    }

    /**
     * 通过商品属性id删除图片
     *
     * @param propertyId
     * @return
     */
    @Override
    public Integer deleteGoodsPicByPropertyId(Long propertyId) {
        return this.deleteObject("com.uc.training.smadmin.gds.dao.GoodsPicDao.deleteGoodsPicByPropertyId", propertyId);
    }

    /**
     * 通过主键id删除图片
     *
     * @param id
     * @return
     */
    @Override
    public Integer deleteGoodsPicById(Long id) {
        return this.deleteObject("com.uc.training.smadmin.gds.dao.GoodsPicDao.deleteGoodsPicById", id);
    }

    /**
     * 后台通过图片id获取表中对应商品属性的的数量
     *
     * @param id
     * @return
     */
    @Override
    public Integer getPropertyIdCountById(Long id) {
        return (Integer) this.queryForObject("com.uc.training.smadmin.gds.dao.GoodsPicDao.getPropertyIdCountById", id);
    }

    /**
     * 通过主键id查找商品状态（1：上架，0：下架）
     *
     * @param id
     * @return
     */
    @Override
    public Integer getGoodsStatusById(Long id) {
        return (Integer) this.queryForObject("com.uc.training.smadmin.gds.dao.GoodsPicDao.getGoodsStatusById", id);
    }

    /**
     * 通过商品属性id查找商品图片的数量
     *
     * @param propertyId
     * @return
     */
    @Override
    public Integer getGoodsPicCountByPropertyId(Long propertyId) {
        return (Integer) this.queryForObject("com.uc.training.smadmin.gds.dao.GoodsPicDao.getGoodsPicCountByPropertyId", propertyId);
    }

    /**
     * 通过主键id获取商品属性id
     *
     * @param id
     * @return
     */
    @Override
    public Long getPropertyIdById(Long id) {
        return (Long) this.queryForObject("com.uc.training.smadmin.gds.dao.GoodsPicDao.getPropertyIdById", id);
    }
}
