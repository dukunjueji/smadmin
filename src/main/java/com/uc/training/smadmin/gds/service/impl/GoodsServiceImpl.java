package com.uc.training.smadmin.gds.service.impl;

import com.uc.training.common.enums.UUIDTypeEnum;
import com.uc.training.smadmin.bd.service.MemberGradeService;
import com.uc.training.smadmin.gds.dao.GoodsDao;
import com.uc.training.smadmin.gds.model.Goods;
import com.uc.training.smadmin.gds.model.GoodsPic;
import com.uc.training.smadmin.gds.model.Property;
import com.uc.training.smadmin.gds.re.*;
import com.uc.training.smadmin.gds.model.HotTag;
import com.uc.training.smadmin.gds.service.CategoryService;
import com.uc.training.smadmin.gds.service.GoodsPicService;
import com.uc.training.smadmin.gds.service.GoodsService;
import com.uc.training.smadmin.gds.service.PropertyService;
import com.uc.training.smadmin.gds.vo.AdminGoodsVO;
import com.uc.training.smadmin.gds.vo.GoodsListVO;
import com.uc.training.smadmin.gds.vo.GoodsStokeVO;
import com.uc.training.smadmin.utils.UUIDUtil;
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
    MemberGradeService memberGradeService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private GoodsPicService goodsPicService;

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

    @Override
    public double getMemberDiscountPoint(Long uid) {
        return memberGradeService.getDiscountByUId(uid);
    }

    @Override
    public void updateAndDeductStoke(GoodsStokeVO goodsStokeVO) {
        GoodsStokeRE goodsStokeRE=goodsDao.selectGoodsStatus(goodsStokeVO);
        if(goodsStokeRE != null && goodsStokeRE.getIsDelete()==0 && goodsStokeRE.getStatus()==1 && goodsStokeRE.getStoke()>=goodsStokeVO.getStoke()){
            goodsDao.updateAndDeductStoke(goodsStokeVO);
        }else{
            System.out.println("库存有问题！--------------------------------------");
        }
    }

    @Override
    public GoodsStokeRE selectGoodsStatus(GoodsStokeVO goodsStokeVO) {
        return goodsDao.selectGoodsStatus(goodsStokeVO);
    }

    /**
     * 后台新增商品
     * @param goods
     * @param property
     * @param goodsPic
     * @return
     */
    @Override
    public Long adminInsertGoods(Goods goods, Property property, GoodsPic goodsPic) {

        // 新增商品
        Long goodsId = goodsDao.insertGoods(goods);

        //新增商品类型
        property.setGoodsId(goodsId);
        Long propertyId = propertyService.insertProperty(property);

        // 新增图片
        goodsPic.setPropertyId(propertyId);
        return goodsPicService.insertGoodsPic(goodsPic);
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

    /**
     * 后台更新商品
     * @param adminGoodsVO
     * @return
     */
    @Override
    public Integer adminUpdateGoods(AdminGoodsVO adminGoodsVO) {

        // 修改商品属性
        Property property = new Property();

        property.setGoodsId(adminGoodsVO.getId());

        Integer propertyId = propertyService.updateProperty(property);

        // 新增图片
        GoodsPic goodsPic = new GoodsPic();

        goodsPic.setPropertyId(propertyId);

        goodsPicService.updateGoodsPic(goodsPic);

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
    @Override
    public Integer logicDeleteGoods(Long id) {
        return goodsDao.logicDeleteGoods(id);
    }


}
