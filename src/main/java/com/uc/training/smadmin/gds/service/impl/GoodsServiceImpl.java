package com.uc.training.smadmin.gds.service.impl;

import com.uc.training.common.enums.UUIDTypeEnum;
import com.uc.training.common.vo.PageVO;
import com.uc.training.smadmin.gds.dao.CategoryDao;
import com.uc.training.smadmin.gds.dao.GoodsDao;
import com.uc.training.smadmin.gds.dao.GoodsPicDao;
import com.uc.training.smadmin.gds.dao.PropertyDao;
import com.uc.training.smadmin.gds.model.*;
import com.uc.training.smadmin.gds.re.AdminGoodsRE;
import com.uc.training.smadmin.gds.re.GoodsRE;
import com.uc.training.smadmin.gds.re.GoodsDetailRE;
import com.uc.training.smadmin.gds.re.PropertyUrlRE;
import com.uc.training.smadmin.gds.service.GoodsService;
import com.uc.training.smadmin.gds.vo.AdminGoodsVO;
import com.uc.training.smadmin.gds.vo.GoodsListVO;
import com.uc.training.smadmin.utils.UUIDUtil;
import com.ycc.base.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 请填写类注释
 *
 * @author zhongling(ling.zhong @ ucarinc.com)
 * @since 2018年10月16日 11:17
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsDao goodsDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private PropertyDao propertyDao;

    private GoodsPicDao goodsPicDao;

    @Override
    public List<GoodsRE> getHotRecommend(int listSize) {
        return goodsDao.getHotRecommend(listSize);
    }

    @Override
    public Long getGoodsListCount(GoodsListVO goodsListVO) {
        return goodsDao.getGoodsListCount(goodsListVO);
    }

    @Override
    public List<GoodsRE> getGoodsList(GoodsListVO goodsListVO) {
        return goodsDao.getGoodsList(goodsListVO);
    }

    /**
     * 后台获取所有商品
     *
     * @param goodsListVO
     * @return
     */
    @Override
    public List<AdminGoodsRE> getAdminGoodsList(GoodsListVO goodsListVO) {
        return goodsDao.getAdminGoodsList(goodsListVO);
    }

    @Override
    public GoodsDetailRE getGoodsDetailByPropertyId(Long propertyId) {
        GoodsDetailRE goodsDetailRE=goodsDao.getGoodsDetailByPropertyId(propertyId);
        List<PropertyUrlRE> list=goodsDao.getPicUrlByPropertyId(propertyId);
        goodsDetailRE.setPicUrl(list);
        return goodsDetailRE;
    }

    @Override
    public List<GoodsDetailRE> getGoodsDetailByGoodsId(Long goodsId) {
        List<GoodsDetailRE> goodsDetailREList=goodsDao.getGoodsDetailByGoodsId(goodsId);
        List<PropertyUrlRE> urlPics=null;
        for(int i = 0; i < goodsDetailREList.size(); i++) {
            urlPics=goodsDao.getPicUrlByPropertyId(goodsDetailREList.get(i).getPropertyId());
            goodsDetailREList.get(i).setPicUrl(urlPics);
        }
        return goodsDetailREList;
    }

    @Override
    public List<GoodsRE> searchByGoodsName(String goodsName) {
        return goodsDao.searchByGoodsName(goodsName);
    }

    @Override
    public List<HotTag> selectHotTag() {
        return goodsDao.selectHotTag();
    }

    /**
     * 后台新增商品
     *
     * @param adminGoodsVO
     * @return
     */
    @Override
    public Long adminInsertGoods(AdminGoodsVO adminGoodsVO) {

        // 新增商品属性
        Property property = new Property();

        property.setGoodsId(adminGoodsVO.getId());
        property.setStock(adminGoodsVO.getStock());
        property.setDiscountPrice(adminGoodsVO.getDiscountPrice());
        property.setSalePrice(adminGoodsVO.getSalePrice());
        property.setProperty(adminGoodsVO.getProperty());
        property.setIsDiscount(adminGoodsVO.getIsDiscount());
        property.setCreateEmp(adminGoodsVO.getCreateEmp());
        property.setModifyEmp(adminGoodsVO.getModifyEmp());

        Long PropertyId = propertyDao.insertProperty(property);

        // 新增图片
        GoodsPic goodsPic = new GoodsPic();

        goodsPic.setPropertyId(PropertyId);
        goodsPic.setPicName(adminGoodsVO.getPicName());
        goodsPic.setPicUrl(adminGoodsVO.getPicUrl());
        goodsPic.setCreateEmp(adminGoodsVO.getCreateEmp());
        goodsPic.setModifyEmp(adminGoodsVO.getModifyEmp());

        goodsPicDao.insertGoodsPic(goodsPic);

        //新增商品
        Goods goods = new Goods();
        //生成商品编号
        goods.setCode(UUIDUtil.getUuidByType(UUIDTypeEnum.GOODSID.getType()));
        goods.setCategoryId(adminGoodsVO.getCategoryId());
        goods.setSales(adminGoodsVO.getSales());
        goods.setDetail(adminGoodsVO.getDetail());
        goods.setStatus(adminGoodsVO.getStatus());

        return goodsDao.insertGoods(goods);
    }

    /**
     * 更新商品
     * @param adminGoodsVO
     * @return
     */
    public Integer adminUpdateGoods(AdminGoodsVO adminGoodsVO) {

        // 修改商品属性
        Property property = new Property();

        property.setGoodsId(adminGoodsVO.getId());
        property.setStock(adminGoodsVO.getStock());
        property.setDiscountPrice(adminGoodsVO.getDiscountPrice());
        property.setSalePrice(adminGoodsVO.getSalePrice());
        property.setProperty(adminGoodsVO.getProperty());
        property.setIsDiscount(adminGoodsVO.getIsDiscount());
        property.setCreateEmp(adminGoodsVO.getCreateEmp());
        property.setModifyEmp(adminGoodsVO.getModifyEmp());

        Integer PropertyId = propertyDao.updateProperty(property);

        // 新增图片
        GoodsPic goodsPic = new GoodsPic();

        goodsPic.setPropertyId(PropertyId);
        goodsPic.setPicName(adminGoodsVO.getPicName());
        goodsPic.setPicUrl(adminGoodsVO.getPicUrl());
        goodsPic.setCreateEmp(adminGoodsVO.getCreateEmp());
        goodsPic.setModifyEmp(adminGoodsVO.getModifyEmp());

        goodsPicDao.updateGoodsPic(goodsPic);

        //新增商品
        Goods goods = new Goods();
        //生成商品编号
        goods.setCode(UUIDUtil.getUuidByType(UUIDTypeEnum.GOODSID.getType()));
        goods.setCategoryId(adminGoodsVO.getCategoryId());
        goods.setSales(adminGoodsVO.getSales());
        goods.setDetail(adminGoodsVO.getDetail());
        goods.setStatus(adminGoodsVO.getStatus());

        return goodsDao.updateGoods(goods);
    }

    /**
     * 逻辑删除商品
     * @param id
     * @return
     */
    public Integer logicDeleteGoods(Long id) {
        return goodsDao.logicDeleteGoods(id);
    }


}
