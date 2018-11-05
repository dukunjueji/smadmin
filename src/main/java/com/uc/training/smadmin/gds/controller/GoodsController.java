package com.uc.training.smadmin.gds.controller;

import com.uc.training.common.annotation.AccessLogin;
import com.uc.training.common.base.controller.BaseController;
import com.uc.training.common.enums.StokeStatusEnum;
import com.uc.training.common.vo.PageVO;
import com.uc.training.smadmin.gds.model.HotTag;
import com.uc.training.smadmin.gds.re.GoodsDetailRE;
import com.uc.training.smadmin.gds.re.GoodsRE;
import com.uc.training.smadmin.gds.service.GoodsService;
import com.uc.training.smadmin.gds.vo.GoodsListVO;
import com.uc.training.smadmin.gds.vo.GoodsStokeVO;
import com.uc.training.smadmin.redis.RedisConfigEnum;
import com.ycc.base.common.Result;
import com.ycc.tools.middleware.redis.RedisCacheUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
     * 功能描述: 获取热门推荐
     *
     * @param: []
     * @return: com.ycc.base.common.Result<java.util.List   <   com.uc.training.smadmin.gds.re.GoodsRE>>
     * @auther: ling
     * @date: 2018/10/17 17:17
     */
    @AccessLogin(required = false)
    @ResponseBody
    @RequestMapping(value = "getHotRecommend.do_", method = RequestMethod.GET)
    public Result<List<GoodsRE>> getHotRecommend() {
        List<GoodsRE> list = goodsService.getHotRecommend();
            /*Integer totalNum = goodsService.getHotRecommendCount();
            PageRE<List<GoodsRE>> pageRE = new PageRE<>();
            pageRE.setData(list);
            pageRE.setTotalNum(totalNum);*/
        return Result.getSuccessResult(list);

    }

    /**
     * 功能描述: 通过分类来获取商品
     *
     * @param: [demoListVO]
     * @return: com.ycc.base.common.Result<com.uc.training.common.vo.PageVO   <   com.uc.training.smadmin.gds.re.GoodsRE>>
     * @auther: ling
     * @date: 2018/10/19 9:02
     */
    @AccessLogin(required = false)
    @ResponseBody
    @RequestMapping(value = "getGoodsPageByCategory.do_", method = RequestMethod.POST)
    public Result<PageVO<GoodsRE>> getGoodsPageByCategory(GoodsListVO goodsListVO) {
        PageVO<GoodsRE> pageVO = new PageVO<GoodsRE>();
        pageVO.setPageIndex(goodsListVO.getPageIndex());
        pageVO.setPageSize(goodsListVO.getPageSize());
        List<Long> listPropertyId = goodsService.getGoodsListCount(goodsListVO);
        if(listPropertyId.size() > 0){
            pageVO.setTotal(new Long(listPropertyId.size()));
            pageVO.setDataList(goodsService.getGoodsList(listPropertyId));
        }
        return Result.getSuccessResult(pageVO);
    }

    /**
     * 功能描述: 商品详情
     *
     * @param:
     * @return:
     * @auther: ling
     * @date: 2018/10/19 9:01
     */
    @AccessLogin(required = false)
    @ResponseBody
    @RequestMapping(value = "getGoodsDetailByGoodsId.do_", method = RequestMethod.GET)
    public Result<List<GoodsDetailRE>> getGoodsDetailByGoodsId(@RequestParam("goodsId") Long goodsId) {
        return Result.getSuccessResult(goodsService.getGoodsDetailByGoodsId(goodsId));
    }

    /**
     * 功能描述: 根据商品名称模糊查询商品列表
     *
     * @param:
     * @return:
     * @auther: ling
     * @date: 2018/10/22 9:49
     */
    @AccessLogin(required = false)
    @ResponseBody
    @RequestMapping(value = "searchByGoodsName.do_", method = RequestMethod.POST)
    public Result<PageVO<GoodsRE>> searchByGoodsName(GoodsListVO goodsListVO) {
        if(StringUtils.isEmpty(goodsListVO.getName())) {
            return null;
        }
        PageVO<GoodsRE> pageVO = new PageVO<GoodsRE>();
        pageVO.setPageIndex(goodsListVO.getPageIndex());
        pageVO.setPageSize(goodsListVO.getPageSize());
        pageVO.setTotal(new Long(goodsService.searchCountByGoodsName(goodsListVO)));
        pageVO.setDataList(goodsService.searchByGoodsName(goodsListVO));
        return Result.getSuccessResult(pageVO);
    }

    /**
     * 功能描述: 获取热门标签
     *
     * @param:
     * @return:
     * @auther: ling
     * @date: 2018/10/22 9:50
     */
    @AccessLogin(required = false)
    @ResponseBody
    @RequestMapping(value = "selectHotTag.do_", method = RequestMethod.GET)
    public Result<List<HotTag>> selectHotTag() {
        return Result.getSuccessResult(goodsService.selectHotTag());
    }

    /**
     * 功能描述: 获取会员的折扣点
     *
     * @param:
     * @return:
     * @auther: ling
     * @date: 2018/10/22 9:50
     */
    @AccessLogin(required = true)
    @ResponseBody
    @RequestMapping(value = "getMemberDiscountPoint.do_", method = RequestMethod.GET)
    public Result<Double> getMemberDiscountPoint() {
        Long uid = getUid();
        return Result.getSuccessResult(goodsService.getMemberDiscountPoint(uid));
    }

    /**
     * 测试高并发下的减库存安全
     *
     * @param
     */
    @AccessLogin(required = false)
    @ResponseBody
    @RequestMapping(value = "updateAndDeductStoke.do_")
    public Result<Integer> updateAndDeductStoke() {
        GoodsStokeVO goodsStokeVO = new GoodsStokeVO();
        goodsStokeVO.setPropertyId(26L);
        goodsStokeVO.setStoke(1L);
        Integer status = goodsService.updateAndDeductStoke(goodsStokeVO);
        System.out.println(status + "------------------------------------");
        if(status.equals(StokeStatusEnum.SUCCESS_STATUS.getStatus())) {
            return Result.getSuccessResult(status);
        } else {
            return Result.getBusinessException("减库存失败", null);
        }

    }

    /**
     * 测试redis
     */
    @AccessLogin(required = false)
    @ResponseBody
    @RequestMapping(value = "testRdis.do_")
    public Result<String> testRdis() {
        RedisCacheUtils redis = RedisCacheUtils.getInstance(RedisConfigEnum.GOODS_DETAIL);
        redis.set("helloTesst", "world");
        System.out.println(redis.get("helloTesst"));
        return Result.getSuccessResult("成功");
    }

}
