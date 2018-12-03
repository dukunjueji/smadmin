package com.uc.training.gds.service;

import com.uc.training.common.vo.PageVO;
import com.uc.training.gds.dto.GoodsAndPropertyDTO;
import com.uc.training.gds.dto.GoodsDTO;
import com.uc.training.gds.dto.GoodsListDTO;
import com.uc.training.gds.re.AdminGoodsRE;
import com.uc.training.gds.re.GoodsDetailRE;
import com.uc.training.gds.re.GoodsRE;
import com.uc.training.gds.re.GoodsStokeRE;
import com.uc.training.gds.re.HotTagRE;
import com.uc.training.gds.vo.GoodsStokeVO;

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
     * 通过属性id商品详情
     * @param propertyIds
     * @return
     */
    List<GoodsDetailRE> getGoodsDetailByPropertyIds(List<Long> propertyIds);

    /**
     * 通过分类来获取商品
     * @param goodsListDTO
     * @return
     */
    PageVO<GoodsRE> getGoodsPageByCategory(GoodsListDTO goodsListDTO);

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
     * 模糊查询商品列表
     * @param goodsListDTO
     * @return
     */
    PageVO<GoodsRE> searchByGoodsName(GoodsListDTO goodsListDTO);

    /**
     * 获取热门标签
     * @return
     */
    List<HotTagRE> selectHotTag();

    /**
     * 获取会员的折扣点
     * @param uid
     * @return
     */
    Double getMemberDiscountPoint(Long uid);

    /**
     * 获取商品上架删除库存信息
     * @param goodsAndPropertyDTO
     * @return
     */
    public  GoodsStokeRE selectGoodsStatus(GoodsStokeVO goodsAndPropertyDTO);

    /**
     * 测试高并发下的减库存安全
     * @param goodsAndPropertyDTO
     * @return
     */
    Integer updateAndDeductStoke(GoodsStokeVO goodsAndPropertyDTO);


    /**
     * 后台获取所有商品
     * @param goodsListDTO
     * @return
     */
    List<AdminGoodsRE> getAdminGoodsList(GoodsListDTO goodsListDTO);

    /**
     * 后台更新商品
     * @param goodsDTO
     * @return
     */
    Integer adminUpdateGoods(GoodsDTO goodsDTO);

    /**
     * 后台新增商品
     * @param goods
     * @return
     */
    Long adminInsertGoods(GoodsDTO goods);

    /**
     * 逻辑删除商品
     * @param id
     * @return
     */
    Integer logicDeleteGoods(Long id);

    /**
     * 更新销量
     * @param goodsAndPropertyDTO
     * @return
     */
    int updateSales(GoodsAndPropertyDTO goodsAndPropertyDTO);

    /**
     * 后台获取商品数量
     * @param goodsListDTO
     * @return
     */
    Long getAdminGoodsListCount(GoodsListDTO goodsListDTO);

    /**
     * 商品上架
     * @param goodsDTO
     * @return
     */
    Integer pullOnGoods(GoodsDTO goodsDTO);

    /**
     * 商品下架
     * @param goodsDTO
     * @return
     */
    Integer pullOffGoods(GoodsDTO goodsDTO);

    /**
     * 根据id获取商品信息
     * @param id
     * @return
     */
    GoodsRE getGoodsById(Long id);
}
