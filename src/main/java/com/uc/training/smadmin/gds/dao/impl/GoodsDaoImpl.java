package com.uc.training.smadmin.gds.dao.impl;

import com.uc.training.smadmin.gds.dao.GoodsDao;
import com.uc.training.smadmin.gds.model.Goods;
import com.uc.training.smadmin.gds.model.HotTag;
import com.uc.training.smadmin.gds.re.*;
import com.uc.training.smadmin.gds.vo.AdminPullGoodsVO;
import com.uc.training.smadmin.gds.vo.AdminUpdateGoodsVO;
import com.uc.training.smadmin.gds.vo.GoodsListVO;
import com.uc.training.smadmin.gds.vo.GoodsStokeVO;
import com.zuche.framework.dao.CarIsIbatisDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 请填写类注释
 *
 * @author zhongling(ling.zhong @ ucarinc.com)
 * @since 2018年10月16日 11:17
 */
@Repository
public class GoodsDaoImpl  extends CarIsIbatisDaoImpl implements GoodsDao {
    @Override
    public List<Long> getHotRecommend() {
        return this.queryForList("com.uc.training.smadmin.gds.dao.GoodsDao.getHotRecommend");
    }

    @Override
    public Integer getHotRecommendCount() {
        return (Integer)this.queryForObject("com.uc.training.smadmin.gds.dao.GoodsDao.getHotRecommendCount");
    }

    @Override
    public List<Long> getGoodsListCount(GoodsListVO goodsListVO) {
        return this.queryForList("com.uc.training.smadmin.gds.dao.GoodsDao.getGoodsListCount",goodsListVO,goodsListVO.getOffset(),goodsListVO.getPageSize());
    }

    @Override
    public List<GoodsRE> getGoodsList(List<Long> propertyIds) {
        return this.queryForList("com.uc.training.smadmin.gds.dao.GoodsDao.getGoodsList",propertyIds);
    }

    @Override
    public GoodsDetailRE getGoodsDetailByPropertyId(Long propertyId) {
        return (GoodsDetailRE)this.queryForObject("com.uc.training.smadmin.gds.dao.GoodsDao.getGoodsDetailByPropertyId",propertyId);
    }

    /**
     * 通过属性id商品详情
     *
     * @param propertyIds
     * @return
     */
    @Override
    public List<GoodsDetailRE> getGoodsDetailByPropertyIds(List<Long> propertyIds) {
        return this.queryForList("com.uc.training.smadmin.gds.dao.GoodsDao.getGoodsDetailByPropertyIds",propertyIds);
    }

    @Override
    public int updateSales(GoodsStokeVO goodsStokeVO) {
        return this.update("com.uc.training.smadmin.gds.dao.GoodsDao.updateSales",goodsStokeVO);
    }

    @Override
    public List<GoodsDetailRE> getGoodsDetailByGoodsId(Long goodsId) {
        return this.queryForList("com.uc.training.smadmin.gds.dao.GoodsDao.getGoodsDetailByGoodsId",goodsId);
    }

    @Override
    public GoodsStokeRE selectGoodsStatus(GoodsStokeVO goodsStokeVO) {
        return (GoodsStokeRE)this.queryForObject("com.uc.training.smadmin.gds.dao.GoodsDao.selectGoodsStatus",goodsStokeVO);
    }

    @Override
    public int updateAndDeductStoke(GoodsStokeVO goodsStokeVO) {
        return this.update("com.uc.training.smadmin.gds.dao.GoodsDao.updateAndDeductStoke",goodsStokeVO);
    }

    @Override
    public Integer searchCountByGoodsName(GoodsListVO goodsListVO){
        return (Integer)this.queryForObject("com.uc.training.smadmin.gds.dao.GoodsDao.searchCountByGoodsName",goodsListVO);
    }

    @Override
    public List<Long> searchByGoodsName(GoodsListVO goodsListVO) {
        return this.queryForList("com.uc.training.smadmin.gds.dao.GoodsDao.searchByGoodsName",goodsListVO);
    }

    @Override
    public List<GoodsRE> searchByPropertyId(List<Long> propertyIds) {
        return this.queryForList("com.uc.training.smadmin.gds.dao.GoodsDao.searchByPropertyId",propertyIds);
    }

    @Override
    public List<HotTag> selectHotTag() {
        return this.queryForList("com.uc.training.smadmin.gds.dao.GoodsDao.selectHotTag");
    }

    @Override
    public List<PropertyUrlRE> getPicUrlByPropertyId(Long propertyId) {
        return this.queryForList("com.uc.training.smadmin.gds.dao.GoodsDao.getPicUrlByPropertyId",propertyId);
    }

    /**
     * 通过属性list列表id获取相应的图片
     *
     * @param propertyId
     * @return
     */
    @Override
    public List<List<PropertyUrlRE>> getPicUrlByPropertyIds(List<Long> propertyId) {
        return this.queryForList("com.uc.training.smadmin.gds.dao.GoodsDao.getPicUrlByPropertyIds",propertyId);
    }

    /**
     * 后台获取商品列表
     *
     * @param goodsListVO
     * @return
     */
    @Override
    public List<AdminGoodsRE> getAdminGoodsList(GoodsListVO goodsListVO) {
        return this.queryForList("com.uc.training.smadmin.gds.dao.GoodsDao.getAdminGoodsList", goodsListVO);
    }

    /**
     * 新增商品
     *
     * @param goods
     * @return
     */
    @Override
    public Long insertGoods(Goods goods) {
        return (Long) this.insert("com.uc.training.smadmin.gds.dao.GoodsDao.insertGoods", goods);
    }

    /**
     * 更新商品
     *
     * @param adminUpdateGoodsVO
     * @return
     */
    @Override
    public Integer updateGoods(AdminUpdateGoodsVO adminUpdateGoodsVO) {
        return this.update("com.uc.training.smadmin.gds.dao.GoodsDao.updateGoods", adminUpdateGoodsVO);
    }

    /**
     * 逻辑删除商品
     *
     * @param id
     * @return
     */
    @Override
    public Integer logicDeleteGoods(Long id) {
        return this.update("com.uc.training.smadmin.gds.dao.GoodsDao.logicDeleteGoods", id);
    }

    /**
     * 后台获取商品数量
     *
     * @param goodsListVO
     * @return
     */
    @Override
    public Long getAdminGoodsListCount(GoodsListVO goodsListVO) {
        return (Long) this.queryForObject("com.uc.training.smadmin.gds.dao.GoodsDao.getAdminGoodsListCount", goodsListVO);
    }

    /**
     * 商品上架
     *
     * @param adminPullGoodsVO
     * @return
     */
    @Override
    public Integer pullOnGoods(AdminPullGoodsVO adminPullGoodsVO) {
        return this.update("com.uc.training.smadmin.gds.dao.GoodsDao.pullOnGoods", adminPullGoodsVO);
    }

    /**
     * 商品下架
     *
     * @param adminPullGoodsVO
     * @return
     */
    @Override
    public Integer pullOffGoods(AdminPullGoodsVO adminPullGoodsVO) {
        return this.update("com.uc.training.smadmin.gds.dao.GoodsDao.pullOffGoods", adminPullGoodsVO);
    }


}
