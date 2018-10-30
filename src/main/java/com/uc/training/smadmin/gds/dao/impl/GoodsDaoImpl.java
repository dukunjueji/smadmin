package com.uc.training.smadmin.gds.dao.impl;

import com.uc.training.smadmin.gds.dao.GoodsDao;
import com.uc.training.smadmin.gds.model.Goods;
import com.uc.training.smadmin.gds.re.*;
import com.uc.training.smadmin.gds.model.HotTag;
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
    public List<GoodsRE> getHotRecommend(int listSize) {
        return this.queryForList("com.uc.training.smadmin.gds.dao.GoodsDao.getHotRecommend",listSize);
    }

    @Override
    public Long getGoodsListCount(GoodsListVO goodsListVO) {
        return (Long)this.queryForObject("com.uc.training.smadmin.gds.dao.GoodsDao.getGoodsListCount",goodsListVO);
    }

    @Override
    public List<GoodsRE> getGoodsList(GoodsListVO goodsListVO) {
        return this.queryForList("com.uc.training.smadmin.gds.dao.GoodsDao.getGoodsList",goodsListVO);
    }

    @Override
    public GoodsDetailRE getGoodsDetailByPropertyId(Long propertyId) {
        return (GoodsDetailRE)this.queryForObject("com.uc.training.smadmin.gds.dao.GoodsDao.getGoodsDetailByPropertyId",propertyId);
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
    public List<GoodsRE> searchByGoodsName(String goodsName) {
        return this.queryForList("com.uc.training.smadmin.gds.dao.GoodsDao.searchByGoodsName",goodsName);
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
     * @param goods
     * @return
     */
    @Override
    public Integer updateGoods(Goods goods) {
        return this.update("com.uc.training.smadmin.gds.dao.GoodsDao.updateGoods", goods);
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


}
