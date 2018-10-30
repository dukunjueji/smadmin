package com.uc.training.smadmin.gds.service;

import com.uc.training.smadmin.gds.re.GoodsRE;
import com.uc.training.smadmin.gds.re.GoodsDetailRE;
import com.uc.training.smadmin.gds.model.HotTag;
import com.uc.training.smadmin.gds.vo.GoodsListVO;

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
     * 通过商品id获取所有规格的商品详情
     * @param goodsId
     * @return
     */
    public List<GoodsDetailRE> getGoodsDetailByGoodsId(Long goodsId);

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
     * 获取会员的折扣点
     * @param uid
     * @return
     */
    public double getMemberDiscountPoint(Long uid);

    /**
     * 测试高并发下的减库存安全
     * @param goodsStokeVO
     */
    public void updateAndDeductStoke(GoodsStokeVO goodsStokeVO);

    /**
     * 后台获取所有商品
     * @param goodsListVO
     * @return
     */
    List<AdminGoodsRE> getAdminGoodsList(GoodsListVO goodsListVO);

    /**
     * 后台更新商品
     * @param adminGoodsVO
     * @return
     */
    Integer adminUpdateGoods(AdminGoodsVO adminGoodsVO);

    /**
     * 后台新增商品
     * @param goods
     * @param property
     * @param goodsPic
     * @return
     */
    Long adminInsertGoods(Goods goods, Property property, GoodsPic goodsPic);

    /**
     * 逻辑删除商品
     * @param id
     * @return
     */
    Integer logicDeleteGoods(Long id);
}
