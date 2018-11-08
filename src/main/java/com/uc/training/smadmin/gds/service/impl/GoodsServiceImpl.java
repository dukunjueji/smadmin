package com.uc.training.smadmin.gds.service.impl;

import com.uc.training.common.enums.GoodsStatusEnum;
import com.uc.training.common.enums.StokeStatusEnum;
import com.uc.training.smadmin.bd.service.MemberGradeService;
import com.uc.training.smadmin.gds.dao.GoodsDao;
import com.uc.training.smadmin.gds.model.Goods;
import com.uc.training.smadmin.gds.model.HotTag;
import com.uc.training.smadmin.gds.re.*;
import com.uc.training.smadmin.gds.service.CategoryService;
import com.uc.training.smadmin.gds.service.GoodsPicService;
import com.uc.training.smadmin.gds.service.GoodsService;
import com.uc.training.smadmin.gds.service.PropertyService;
import com.uc.training.smadmin.gds.vo.AdminPullGoodsVO;
import com.uc.training.smadmin.gds.vo.AdminUpdateGoodsVO;
import com.uc.training.smadmin.gds.vo.GoodsListVO;
import com.uc.training.smadmin.gds.vo.GoodsStokeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    /**
     * 锁标志
     */
    private static Object lock = new Object();

    @Override
    public List<GoodsRE> getHotRecommend() {
        List<Long> propertyIds = goodsDao.getHotRecommend();
        List<GoodsRE> list = new ArrayList<GoodsRE>();
        list = getPicUrlByPropertyId(propertyIds);
        return list;
    }

    @Override
    public Integer getHotRecommendCount() {
        return goodsDao.getHotRecommendCount();
    }

    /**
     * 通过属性id商品详情
     *
     * @param propertyIds
     * @return
     */
    @Override
    public List<GoodsDetailRE> getGoodsDetailByPropertyIds(List<Long> propertyIds) {
        List<GoodsDetailRE> goodsDetailRE =  goodsDao.getGoodsDetailByPropertyIds(propertyIds);
        return goodsDetailRE;
    }

    @Override
    public List<Long> getGoodsListCount(GoodsListVO goodsListVO) {
        return goodsDao.getGoodsListCount(goodsListVO);
    }

    @Override
    public List<GoodsRE> getGoodsList(List<Long> propertyIds) {
        return getPicUrlByPropertyId(propertyIds);
    }

    @Override
    public GoodsDetailRE getGoodsDetailByPropertyId(Long propertyId) {
        GoodsDetailRE goodsDetailRE=goodsDao.getGoodsDetailByPropertyId(propertyId);
        if(goodsDetailRE==null){
            return null;
        }
        List<PropertyUrlRE> list=goodsDao.getPicUrlByPropertyId(propertyId);
        if(list.size()>0){
            goodsDetailRE.setPicUrl(list);
        }
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
    public Integer searchCountByGoodsName(GoodsListVO goodsListVO) {
        return goodsDao.searchCountByGoodsName(goodsListVO);
    }

    @Override
    public List<GoodsRE> searchByGoodsName(GoodsListVO goodsListVO) {
        List<Long> propertyIds = goodsDao.searchByGoodsName(goodsListVO);
        List<GoodsRE> list = new ArrayList<GoodsRE>();
        list = getPicUrlByPropertyId(propertyIds);
        return list;
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
    public Integer updateAndDeductStoke(GoodsStokeVO goodsStokeVO) {

        synchronized(lock){
            GoodsStokeRE goodsStokeRE=goodsDao.selectGoodsStatus(goodsStokeVO);
            if(goodsStokeRE == null){
                return StokeStatusEnum.BLANK_STATUS.getStatus();
            }else if(goodsStokeRE.getStatus().equals(GoodsStatusEnum.GOODS_IS_SHELVES.getType())){
                return StokeStatusEnum.SHELVED_STATUS.getStatus();
            }else if(goodsStokeRE.getIsDelete().equals(GoodsStatusEnum.GOODS_DELETE.getType())){
                return StokeStatusEnum.DELETE_STATUS.getStatus();
            }else if(goodsStokeRE.getStoke()<goodsStokeVO.getStoke()){
                return StokeStatusEnum.NOT_ENOUGH_STATUS.getStatus();
            }else{
                goodsDao.updateAndDeductStoke(goodsStokeVO);
                return StokeStatusEnum.SUCCESS_STATUS.getStatus();
            }
        }
    }

    @Override
    public GoodsStokeRE selectGoodsStatus(GoodsStokeVO goodsStokeVO) {
        return goodsDao.selectGoodsStatus(goodsStokeVO);
    }

    /**
     * 后台新增商品
     * @param goods
     * @return
     */
    @Override
    public Long adminInsertGoods(Goods goods) {
        return goodsDao.insertGoods(goods);
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
     * @param adminUpdateGoodsVO
     * @return
     */
    @Override
    public Integer adminUpdateGoods(AdminUpdateGoodsVO adminUpdateGoodsVO) {
        return goodsDao.updateGoods(adminUpdateGoodsVO);
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

    /**
     * 更新销量
     *
     * @param goodsStokeVO
     * @return
     */
    @Override
    public int updateSales(GoodsStokeVO goodsStokeVO) {
        return goodsDao.updateSales(goodsStokeVO);
    }

    /**
     * 后台获取商品数量
     *
     * @param goodsListVO
     * @return
     */
    @Override
    public Long getAdminGoodsListCount(GoodsListVO goodsListVO) {
        return goodsDao.getAdminGoodsListCount(goodsListVO);
    }

    /**
     * 商品上架
     *
     * @param adminPullGoodsVO
     * @return
     */
    @Override
    public Integer pullOnGoods(AdminPullGoodsVO adminPullGoodsVO) {
        return goodsDao.pullOnGoods(adminPullGoodsVO);
    }

    /**
     * 商品下架
     *
     * @param adminPullGoodsVO
     * @return
     */
    @Override
    public Integer pullOffGoods(AdminPullGoodsVO adminPullGoodsVO) {
        return goodsDao.pullOffGoods(adminPullGoodsVO);
    }

    private List<GoodsRE> getPicUrlByPropertyId(List<Long> propertyIds){
        List<GoodsRE> list = new ArrayList<GoodsRE>();
        List<PropertyUrlRE> picList;
        if(propertyIds.size() > 0) {
            list = goodsDao.searchByPropertyId(propertyIds);
            for(int i = 0; i < list.size(); i++) {
                picList = new ArrayList<>();
                picList = goodsDao.getPicUrlByPropertyId(list.get(i).getPropertyId());
                if(picList.size() > 0) {
                    list.get(i).setPicUrl(picList.get(0).getPicUrl());
                }
            }
        }
        return list;
    }
}
