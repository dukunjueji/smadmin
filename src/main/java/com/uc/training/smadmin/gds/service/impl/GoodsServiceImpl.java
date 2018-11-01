package com.uc.training.smadmin.gds.service.impl;

import com.uc.training.smadmin.bd.service.MemberGradeService;
import com.uc.training.smadmin.gds.dao.GoodsDao;
import com.uc.training.smadmin.gds.model.Goods;
import com.uc.training.smadmin.gds.re.*;
import com.uc.training.smadmin.gds.model.HotTag;
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
import java.util.LinkedList;
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

    /**
     * 通过属性id商品详情
     *
     * @param propertyIds
     * @return
     */
    @Override
    public List<GoodsDetailRE> getGoodsDetailByPropertyIds(List<Long> propertyIds) {
        List<GoodsDetailRE> goodsDetailRE =  goodsDao.getGoodsDetailByPropertyIds(propertyIds);
        List<List<PropertyUrlRE>> list=(List<List<PropertyUrlRE>>)goodsDao.getPicUrlByPropertyIds(propertyIds);
        for (int i = 0;i < list.size();i++){
            System.out.println("------------");
            System.out.println(list.get(i).toString());
            //goodsDetailRE.get(i).setPicUrl((List<PropertyUrlRE>)list.get(i));
        }
        return goodsDetailRE;
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
        List<Long> propertyIds = goodsDao.searchByGoodsName(goodsName);
        List<GoodsRE> list = new ArrayList<GoodsRE>();
        if(propertyIds.size()>0){
            list = goodsDao.searchByPropertyId(propertyIds);
        }
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


}
