package com.uc.training.gds.service.impl;

import com.uc.training.common.vo.PageVO;
import com.uc.training.gds.dto.GoodsAndPropertyDTO;
import com.uc.training.gds.dto.GoodsDTO;
import com.uc.training.gds.dto.GoodsListDTO;
import com.uc.training.gds.re.AdminGoodsRE;
import com.uc.training.gds.re.GoodsDetailRE;
import com.uc.training.gds.re.GoodsRE;
import com.uc.training.gds.re.GoodsStokeRE;
import com.uc.training.gds.re.HotTagRE;
import com.uc.training.gds.service.GoodsService;
import com.uc.training.gds.vo.GoodsStokeVO;
import com.uc.training.remote.client.GdsClient;
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

    /**
     * 锁标志
     */
    private static Object lock = new Object();

    @Override
    public List<GoodsRE> getHotRecommend() {
        List<GoodsRE> list = GdsClient.getHotRecommend();
        return list;
    }

    /**
     * 通过分类来获取商品
     * @param goodsListDTO
     * @return
     */
    @Override
    public PageVO<GoodsRE> getGoodsPageByCategory(GoodsListDTO goodsListDTO){
        return GdsClient.getGoodsPageByCategory(goodsListDTO);
    }
    /**
     * 通过属性id商品详情
     *
     * @param propertyIds
     * @return
     */
    @Override
    public List<GoodsDetailRE> getGoodsDetailByPropertyIds(List<Long> propertyIds) {
        List<GoodsDetailRE> goodsDetailRE =  GdsClient.getGoodsDetailByPropertyIds(propertyIds);
        return goodsDetailRE;
    }


    @Override
    public GoodsDetailRE getGoodsDetailByPropertyId(Long propertyId) {
        GoodsDetailRE goodsDetailRE=GdsClient.getGoodsDetailByPropertyId(propertyId);
        return goodsDetailRE;
    }

    @Override
    public List<GoodsDetailRE> getGoodsDetailByGoodsId(Long goodsId) {
        List<GoodsDetailRE> goodsDetailREList=GdsClient.getGoodsDetailByGoodsId(goodsId);
        return goodsDetailREList;
    }


    @Override
    public PageVO<GoodsRE> searchByGoodsName(GoodsListDTO goodsListDTO) {

        PageVO<GoodsRE> pageVO = GdsClient.searchByGoodsName(goodsListDTO);
        return pageVO;
    }

    @Override
    public List<HotTagRE> selectHotTag() {
        return GdsClient.selectHotTag();
    }

    @Override
    public Double getMemberDiscountPoint(Long uid) {
//        return memberGradeService.getDiscountByUId(uid);
        return 0.8;
    }

    /**
     * 获取商品上架删除库存信息
     * @param goodsAndPropertyDTO
     * @return
     */
    @Override
    public  GoodsStokeRE selectGoodsStatus(GoodsStokeVO goodsAndPropertyDTO){
        return GdsClient.selectGoodsStatus(goodsAndPropertyDTO);
    }

    @Override
    public Integer updateAndDeductStoke(GoodsStokeVO goodsAndPropertyDTO) {
        return GdsClient.updateAndDeductStoke(goodsAndPropertyDTO);
    }


    /**
     * 后台新增商品
     * @param goods
     * @return
     */
    @Override
    public Long adminInsertGoods(GoodsDTO goods) {
        return GdsClient.adminInsertGoods(goods);
    }

    /**
     * 后台获取所有商品
     *
     * @param goodsListDTO
     * @return
     */
    @Override
    public List<AdminGoodsRE> getAdminGoodsList(GoodsListDTO goodsListDTO) {
        return GdsClient.getAdminGoodsList(goodsListDTO);
    }

    /**
     * 后台更新商品
     * @param goodsDTO
     * @return
     */
    @Override
    public Integer adminUpdateGoods(GoodsDTO goodsDTO) {
        return GdsClient.adminUpdateGoods(goodsDTO);
    }

    /**
     * 逻辑删除商品
     * @param id
     * @return
     */
    @Override
    public Integer logicDeleteGoods(Long id) {
        return GdsClient.logicDeleteGoods(id);
    }

    /**
     * 更新销量
     *
     * @param goodsAndPropertyDTO
     * @return
     */
    @Override
    public int updateSales(GoodsAndPropertyDTO goodsAndPropertyDTO) {
        return GdsClient.updateSales(goodsAndPropertyDTO);
    }

    /**
     * 后台获取商品数量
     *
     * @param goodsListVO
     * @return
     */
    @Override
    public Long getAdminGoodsListCount(GoodsListDTO goodsListVO) {
        return GdsClient.getAdminGoodsListCount(goodsListVO);
    }

    /**
     * 商品上架
     *
     * @param goodsDTO
     * @return
     */
    @Override
    public Integer pullOnGoods(GoodsDTO goodsDTO) {
        return GdsClient.pullOnGoods(goodsDTO);
    }

    /**
     * 商品下架
     *
     * @param goodsDTO
     * @return
     */
    @Override
    public Integer pullOffGoods(GoodsDTO goodsDTO) {
        return GdsClient.pullOffGoods(goodsDTO);
    }

    /**
     * 根据id获取商品信息
     *
     * @param id
     * @return
     */
    @Override
    public GoodsRE getGoodsById(Long id) {
        return GdsClient.getGoodsById(id);
    }

}
