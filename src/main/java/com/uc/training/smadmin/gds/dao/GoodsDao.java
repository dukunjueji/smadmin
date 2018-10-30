package com.uc.training.smadmin.gds.dao;

import com.uc.training.smadmin.gds.re.GoodsRE;
import com.uc.training.smadmin.gds.re.GoodsDetailRE;
import com.uc.training.smadmin.gds.re.GoodsStokeRE;
import com.uc.training.smadmin.gds.model.HotTag;
import com.uc.training.smadmin.gds.re.PropertyUrlRE;
import com.uc.training.smadmin.gds.vo.GoodsListVO;
import com.uc.training.smadmin.gds.vo.GoodsStokeVO;

import java.util.HashMap;
import java.util.List;

/**
 * 请填写类注释
 *
 * @author zhongling(ling.zhong @ ucarinc.com)
 * @since 2018年10月16日 11:17
 */
public interface GoodsDao {
    /**
     * 获取热门推荐
     * @param listSize
     * @return
     */
    public List<GoodsRE> getHotRecommend(int listSize);

    /**
     * 通过分类来获取商品数量
     * @param goodsListVO
     * @return
     */
    public Long getGoodsListCount(GoodsListVO goodsListVO);

    /**
     * 通过分类来获取商品
     * @param goodsListVO
     * @return
     */
    public List<GoodsRE> getGoodsList(GoodsListVO goodsListVO);

    /**
     * 通过属性id商品详情
     * @param propertyId
     * @return
     */
    public GoodsDetailRE getGoodsDetailByPropertyId(Long propertyId);

    /**
     * 更新销量
     * @param goodsStokeVO key为goodsId、sales
     * @return
     */
    public int updateSales(GoodsStokeVO goodsStokeVO);

    /**
     * 通过商品id获取所有规格的商品详情
     * @param goodsId
     * @return
     */
    public List<GoodsDetailRE> getGoodsDetailByGoodsId(Long goodsId);

    /**
     * 减库存之前，查看商品是否下架、删除、检查库存是否足够
     * @param goodsStokeVO
     * @return
     */
    public GoodsStokeRE selectGoodsStatus(GoodsStokeVO goodsStokeVO);

    /**
     * 减库存
     * @param goodsStokeVO
     * @return
     */
    public int updateAndDeductStoke(GoodsStokeVO goodsStokeVO);

    /**
     * 模糊查询商品列表
     * @param goodsName
     * @return
     */
    public List<GoodsRE> searchByGoodsName(String goodsName);

    /**
     * 获取热门标签
     * @return
     */
    public List<HotTag> selectHotTag();

    /**
     * 通过属性id获取相应的图片
     * @param propertyId
     * @return
     */
    public List<PropertyUrlRE> getPicUrlByPropertyId(Long propertyId);
}
