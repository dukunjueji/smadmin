package com.uc.training.smadmin.gds.service;

import com.uc.training.smadmin.gds.model.Goods;
import com.uc.training.smadmin.gds.model.HotTag;
import com.uc.training.smadmin.gds.re.AdminGoodsRE;
import com.uc.training.smadmin.gds.re.GoodsDetailRE;
import com.uc.training.smadmin.gds.re.GoodsRE;
import com.uc.training.smadmin.gds.re.GoodsStokeRE;
import com.uc.training.smadmin.gds.vo.AdminPullGoodsVO;
import com.uc.training.smadmin.gds.vo.AdminUpdateGoodsVO;
import com.uc.training.smadmin.gds.vo.GoodsListVO;
import com.uc.training.smadmin.gds.vo.GoodsStokeVO;

import java.util.List;

/**
 * 请填写类注释
 *
 * @author zhongling(ling.zhong @ ucarinc.com)
 * @since 2018年10月16日 11:17
 */
public interface GoodsService {
    /**
     * 获取热门推荐
     * @return
     */
    List<GoodsRE> getHotRecommend();

    /**
     * 获取热门推荐总数量
     * @return
     */
    Integer getHotRecommendCount();

    /**
     * 通过属性id商品详情
     * @param propertyIds
     * @return
     */
    List<GoodsDetailRE> getGoodsDetailByPropertyIds(List<Long> propertyIds);

    /**
     * 通过分类来获取商品数量
     * @param goodsListVO
     * @return
     */
    List<Long> getGoodsListCount(GoodsListVO goodsListVO);

    /**
     * 通过分类来获取商品
     * @param propertyIds
     * @return
     */
    List<GoodsRE> getGoodsList(List<Long> propertyIds);

    /**
     * 通过属性id商品详情
     * @param propertyId
     * @return
     */
    GoodsDetailRE getGoodsDetailByPropertyId(Long propertyId);

    /**
     * 通过商品id获取所有规格的商品详情
     * @param goodsId
     * @return
     */
    List<GoodsDetailRE> getGoodsDetailByGoodsId(Long goodsId);

    /**
     * 模糊查询商品列表总数量
     * @param goodsListVO
     * @return
     */
    Integer searchCountByGoodsName(GoodsListVO goodsListVO);

    /**
     * 模糊查询商品列表
     * @param goodsListVO
     * @return
     */
    List<GoodsRE> searchByGoodsName(GoodsListVO goodsListVO);

    /**
     * 获取热门标签
     * @return
     */
    List<HotTag> selectHotTag();

    /**
     * 获取会员的折扣点
     * @param uid
     * @return
     */
    double getMemberDiscountPoint(Long uid);

    /**
     * 测试高并发下的减库存安全
     * @param goodsStokeVO
     * @return
     */
    Integer updateAndDeductStoke(GoodsStokeVO goodsStokeVO);

    /**
     * 减库存之前，查看商品是否下架、删除、检查库存是否足够
     * @param goodsStokeVO
     * @return
     */
    GoodsStokeRE selectGoodsStatus(GoodsStokeVO goodsStokeVO);

    /**
     * 后台获取所有商品
     * @param goodsListVO
     * @return
     */
    List<AdminGoodsRE> getAdminGoodsList(GoodsListVO goodsListVO);

    /**
     * 后台更新商品
     * @param adminUpdateGoodsVO
     * @return
     */
    Integer adminUpdateGoods(AdminUpdateGoodsVO adminUpdateGoodsVO);

    /**
     * 后台新增商品
     * @param goods
     * @return
     */
    Long adminInsertGoods(Goods goods);

    /**
     * 逻辑删除商品
     * @param id
     * @return
     */
    Integer logicDeleteGoods(Long id);

    /**
     * 更新销量
     * @param goodsStokeVO
     * @return
     */
    int updateSales(GoodsStokeVO goodsStokeVO);

    /**
     * 后台获取商品数量
     * @param goodsListVO
     * @return
     */
    Long getAdminGoodsListCount(GoodsListVO goodsListVO);

    /**
     * 商品上架
     * @param adminPullGoodsVO
     * @return
     */
    Integer pullOnGoods(AdminPullGoodsVO adminPullGoodsVO);

    /**
     * 商品下架
     * @param adminPullGoodsVO
     * @return
     */
    Integer pullOffGoods(AdminPullGoodsVO adminPullGoodsVO);
}
