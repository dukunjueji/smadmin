package com.uc.training.gds.service.impl;

import com.uc.training.base.bd.service.MemberGradeService;
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
    GdsClient gdsClient;

    /**
     * 锁标志
     */
    private static Object lock = new Object();
    @Autowired
    private MemberGradeService memberGradeService;

    @Override
    public List<GoodsRE> getHotRecommend() {
        List<GoodsRE> list = gdsClient.getHotRecommend();
        return list;
    }

    /**
     * 通过分类来获取商品
     * @param goodsListDTO
     * @return
     */
    @Override
    public PageVO<GoodsRE> getGoodsPageByCategory(GoodsListDTO goodsListDTO){
        return gdsClient.getGoodsPageByCategory(goodsListDTO);
    }
    /**
     * 通过属性id商品详情
     *
     * @param propertyIds
     * @return
     */
    @Override
    public List<GoodsDetailRE> getGoodsDetailByPropertyIds(List<Long> propertyIds) {
        List<GoodsDetailRE> goodsDetailRE =  gdsClient.getGoodsDetailByPropertyIds(propertyIds);
        return goodsDetailRE;
    }


    @Override
    public GoodsDetailRE getGoodsDetailByPropertyId(Long propertyId) {
        GoodsDetailRE goodsDetailRE=gdsClient.getGoodsDetailByPropertyId(propertyId);
        return goodsDetailRE;
    }

    @Override
    public List<GoodsDetailRE> getGoodsDetailByGoodsId(Long goodsId) {
        List<GoodsDetailRE> goodsDetailREList=gdsClient.getGoodsDetailByGoodsId(goodsId);
        return goodsDetailREList;
    }


    @Override
    public PageVO<GoodsRE> searchByGoodsName(GoodsListDTO goodsListDTO) {

        PageVO<GoodsRE> pageVO = gdsClient.searchByGoodsName(goodsListDTO);
        return pageVO;
    }

    @Override
    public List<HotTagRE> selectHotTag() {
        return gdsClient.selectHotTag();
    }

    @Override
    public Double getMemberDiscountPoint(Long uid) {
        return memberGradeService.getDiscountByUId(uid);
    }

    /**
     * 获取商品上架删除库存信息
     * @param goodsAndPropertyDTO
     * @return
     */
    @Override
    public  GoodsStokeRE selectGoodsStatus(GoodsStokeVO goodsAndPropertyDTO){
        return gdsClient.selectGoodsStatus(goodsAndPropertyDTO);
    }

    @Override
    public Integer updateAndDeductStoke(GoodsStokeVO goodsAndPropertyDTO) {
        return gdsClient.updateAndDeductStoke(goodsAndPropertyDTO);
    }


    /**
     * 后台新增商品
     * @param goods
     * @return
     */
    @Override
    public Long adminInsertGoods(GoodsDTO goods) {
        return gdsClient.adminInsertGoods(goods);
    }

    /**
     * 后台获取所有商品
     *
     * @param goodsListDTO
     * @return
     */
    @Override
    public List<AdminGoodsRE> getAdminGoodsList(GoodsListDTO goodsListDTO) {
        return gdsClient.getAdminGoodsList(goodsListDTO);
    }

    /**
     * 后台更新商品
     * @param goodsDTO
     * @return
     */
    @Override
    public Integer adminUpdateGoods(GoodsDTO goodsDTO) {
        return gdsClient.adminUpdateGoods(goodsDTO);
    }

    /**
     * 逻辑删除商品
     * @param id
     * @return
     */
    @Override
    public Integer logicDeleteGoods(Long id) {
        return gdsClient.logicDeleteGoods(id);
    }

    /**
     * 更新销量
     *
     * @param goodsAndPropertyDTO
     * @return
     */
    @Override
    public int updateSales(GoodsAndPropertyDTO goodsAndPropertyDTO) {
        return gdsClient.updateSales(goodsAndPropertyDTO);
    }

    /**
     * 后台获取商品数量
     *
     * @param goodsListVO
     * @return
     */
    @Override
    public Long getAdminGoodsListCount(GoodsListDTO goodsListVO) {
        return gdsClient.getAdminGoodsListCount(goodsListVO);
    }

    /**
     * 商品上架
     *
     * @param goodsDTO
     * @return
     */
    @Override
    public Integer pullOnGoods(GoodsDTO goodsDTO) {
        return gdsClient.pullOnGoods(goodsDTO);
    }

    /**
     * 商品下架
     *
     * @param goodsDTO
     * @return
     */
    @Override
    public Integer pullOffGoods(GoodsDTO goodsDTO) {
        return gdsClient.pullOffGoods(goodsDTO);
    }

    /**
     * 根据id获取商品信息
     *
     * @param id
     * @return
     */
    @Override
    public GoodsRE getGoodsById(Long id) {
        return gdsClient.getGoodsById(id);
    }

}
