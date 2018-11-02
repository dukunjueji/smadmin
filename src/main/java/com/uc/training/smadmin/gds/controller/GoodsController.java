package com.uc.training.smadmin.gds.controller;

import com.alibaba.fastjson.JSON;
import com.google.inject.servlet.RequestParameters;
import com.uc.training.common.annotation.AccessLogin;
import com.uc.training.smadmin.gds.re.PageRE;
import com.uc.training.smadmin.gds.vo.GoodsStokeVO;
import com.uc.training.smadmin.redis.RedisConfigEnum;
import com.ycc.base.common.Result;
import com.uc.training.common.base.controller.BaseController;
import com.uc.training.smadmin.gds.vo.PageVO;
import com.uc.training.smadmin.gds.re.GoodsRE;
import com.uc.training.smadmin.gds.re.GoodsDetailRE;
import com.uc.training.smadmin.gds.model.HotTag;
import com.uc.training.smadmin.gds.service.GoodsService;
import com.uc.training.smadmin.gds.vo.GoodsListVO;
import com.ycc.tools.middleware.redis.RedisCacheUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 请填写类注释
 *
 * @author zhongling(ling.zhong @ ucarinc.com)
 * @since 2018年10月16日 11:17
 */
@Controller
@RequestMapping("api/gds/goods")
public class GoodsController extends BaseController {
    @Autowired
    GoodsService goodsService;

    /**
     *
     * 功能描述: 获取热门推荐
     *
     * @param: []
     * @return: com.ycc.base.common.Result<java.util.List<com.uc.training.smadmin.gds.re.GoodsRE>>
     * @auther: ling
     * @date: 2018/10/17 17:17
     */
    @AccessLogin(required = false)
    @ResponseBody
    @RequestMapping(value = "getHotRecommend.do_",method = RequestMethod.POST)
    public Result<PageRE<List<GoodsRE>>> getHotRecommend(PageVO pageVO) {
        try {
            List<GoodsRE> list = goodsService.getHotRecommend(pageVO);
            Integer totalNum = goodsService.getHotRecommendCount();
            PageRE<List<GoodsRE>> pageRE = new PageRE<>();
            pageRE.setData(list);
            pageRE.setTotalNum(totalNum);
            return Result.getSuccessResult(pageRE);
        } catch (Exception e) {
            logger.error("查询符合条件错误！", e);
            return Result.getBusinessException("获取热门推荐失败", null);
        }
    }

    /**
     *
     * 功能描述: 通过分类来获取商品
     *
     * @param: [demoListVO]
     * @return: com.ycc.base.common.Result<com.uc.training.common.vo.PageVO<com.uc.training.smadmin.gds.re.GoodsRE>>
     * @auther: ling
     * @date: 2018/10/19 9:02
     */
    @AccessLogin(required = false)
    @ResponseBody
    @RequestMapping(value = "getGoodsPageByCategory.do_",method = RequestMethod.POST)
    public Result<List<GoodsRE>> getGoodsPageByCategory(GoodsListVO goodsListVO) {
        try {
            /*PageVO<GoodsRE> pageVO = new PageVO<GoodsRE>();
            pageVO.setPageIndex(goodsListVO.getPageIndex());
            pageVO.setPageSize(goodsListVO.getPageSize());
            pageVO.setTotal(goodsService.getGoodsListCount(goodsListVO));
            pageVO.setDataList(goodsService.getGoodsList(goodsListVO));*/
            List<GoodsRE> goodsREList=new ArrayList<>();
            return Result.getSuccessResult(goodsREList);
        } catch (Exception e) {
            logger.error("查询符合条件错误！", e);
            return Result.getBusinessException("获取商品分页失败", null);
        }
    }

    /**
     *
     * 功能描述: 商品详情
     *
     * @param:
     * @return:
     * @auther: ling
     * @date: 2018/10/19 9:01
     */
    @AccessLogin(required = false)
    @ResponseBody
    @RequestMapping(value = "getGoodsDetailByGoodsId.do_",method = RequestMethod.GET)
    public Result<List<GoodsDetailRE>> getGoodsDetailByGoodsId(@RequestParam("goodsId") Long goodsId) {
        try {
            return Result.getSuccessResult(goodsService.getGoodsDetailByGoodsId(goodsId));
        } catch (Exception e) {
            logger.error("获取商品详情错误！", e);
            return Result.getBusinessException("获取商品详情失败", null);
        }
    }

    /**
     *
     * 功能描述: 根据商品名称模糊查询商品列表
     *
     * @param:
     * @return:
     * @auther: ling
     * @date: 2018/10/22 9:49
     */
    @AccessLogin(required = false)
    @ResponseBody
    @RequestMapping(value = "searchByGoodsName.do_",method = RequestMethod.POST)
    public Result<List<GoodsRE>> searchByGoodsName(@RequestParam(value = "goodsName")String goodsName) {
        if(StringUtils.isEmpty(goodsName)){
            return null;
        }
        try {
            return Result.getSuccessResult(goodsService.searchByGoodsName(goodsName));
        } catch (Exception e) {
            logger.error("模糊查询商品错误！", e);
            return Result.getBusinessException("模糊查询商品失败", null);
        }
    }

    /**
     *
     * 功能描述: 获取热门标签
     *
     * @param:
     * @return:
     * @auther: ling
     * @date: 2018/10/22 9:50
     */
    @AccessLogin(required = false)
    @ResponseBody
    @RequestMapping(value = "selectHotTag.do_",method = RequestMethod.GET)
    public Result<List<HotTag>> selectHotTag() {

        try {
            return Result.getSuccessResult(goodsService.selectHotTag());
        } catch (Exception e) {
            logger.error("获取热门标签错误！", e);
            return Result.getBusinessException("获取热门标签失败", null);
        }
    }

    /**
     *
     * 功能描述: 获取会员的折扣点
     *
     * @param:
     * @return:
     * @auther: ling
     * @date: 2018/10/22 9:50
     */
    @AccessLogin(required = true)
    @ResponseBody
    @RequestMapping(value = "getMemberDiscountPoint.do_",method = RequestMethod.GET)
    public Result<Double> getMemberDiscountPoint() {
        Long uid = getUid();
        try {
            return Result.getSuccessResult(goodsService.getMemberDiscountPoint(uid));
        } catch (Exception e) {
            logger.error("获取会员的折扣点错误！", e);
            return Result.getBusinessException("获取会员的折扣点失败", null);
        }
    }

    /**
     * 测试高并发下的减库存安全
     * @param goodsStokeVO
     */
    @ResponseBody
    @RequestMapping(value = "updateAndDeductStoke.do_")
    public Result<String> updateAndDeductStoke(GoodsStokeVO goodsStokeVO){
        goodsStokeVO.setPropertyId(4L);
        goodsStokeVO.setStoke(1L);
        System.out.println(JSON.toJSON(goodsStokeVO));
        try {
            goodsService.updateAndDeductStoke(goodsStokeVO);
            return Result.getSuccessResult("减库存成功");
        } catch (Exception e) {
            logger.error("减库存错误！", e);
            return Result.getBusinessException("减库存失败", null);
        }
    }

    /**
     * 测试redis
     */
    @AccessLogin(required = false)
    @ResponseBody
    @RequestMapping(value = "testRdis.do_")
    public Result<String> testRdis(){
        RedisCacheUtils redis = RedisCacheUtils.getInstance(RedisConfigEnum.GOODS_DETAIL);
        redis.set("helloTesst","world");
        System.out.println(redis.get("hello"));
        try {
            return Result.getSuccessResult("成功");
        } catch (Exception e) {
            logger.error("错误！", e);
            return Result.getBusinessException("失败", null);
        }
    }
}
