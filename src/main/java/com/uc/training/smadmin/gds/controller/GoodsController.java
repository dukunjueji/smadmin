package com.uc.training.smadmin.gds.controller;

import com.ycc.base.common.Result;
import com.uc.training.common.base.controller.BaseController;
import com.uc.training.common.vo.PageVO;
import com.uc.training.smadmin.gds.re.GoodsRE;
import com.uc.training.smadmin.gds.re.GoodsDetailRE;
import com.uc.training.smadmin.gds.model.HotTag;
import com.uc.training.smadmin.gds.service.GoodsService;
import com.uc.training.smadmin.gds.vo.GoodsListVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 请填写类注释
 *
 * @author zhongling(ling.zhong @ ucarinc.com)
 * @since 2018年10月16日 11:17
 */
@Controller
@RequestMapping("api/gds")
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
    @ResponseBody
    @RequestMapping(value = "getHotRecommend.do_",method = RequestMethod.POST)
    public Result<List<GoodsRE>> getHotRecommend(int listSize) {
        Result<List<GoodsRE>> res;
        try {
            res = Result.getSuccessResult(goodsService.getHotRecommend(listSize));
        } catch (Exception e) {
            logger.error("查询符合条件错误！", e);
            res = Result.getBusinessException("获取热门推荐失败", null);
        }
        return res;
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
    @ResponseBody
    @RequestMapping(value = "getGoodsPageByCategory.do_",method = RequestMethod.POST)
    public Result<PageVO<GoodsRE>> getGoodsPageByCategory(GoodsListVO goodsListVO) {
        Result<PageVO<GoodsRE>> res;
        try {
            PageVO<GoodsRE> pageVO = new PageVO<GoodsRE>();
            pageVO.setPageIndex(goodsListVO.getPageIndex());
            pageVO.setPageSize(goodsListVO.getPageSize());
            pageVO.setTotal(goodsService.getGoodsListCount(goodsListVO));
            pageVO.setDataList(goodsService.getGoodsList(goodsListVO));
            res = Result.getSuccessResult(pageVO);
        } catch (Exception e) {
            logger.error("查询符合条件错误！", e);
            res = Result.getBusinessException("获取商品分页失败", null);
        }
        return res;
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
    @ResponseBody
    @RequestMapping(value = "getGoodsDetailByPropertyId.do_/{propertyId}",method = RequestMethod.GET)
    public Result<GoodsDetailRE> getGoodsDetailByPropertyId(@PathVariable(value = "propertyId") Long propertyId) {
        Result<GoodsDetailRE> res;
        try {
            res = Result.getSuccessResult(goodsService.getGoodsDetailByPropertyId(propertyId));
        } catch (Exception e) {
            logger.error("获取商品详情错误！", e);
            res = Result.getBusinessException("获取商品详情失败", null);
        }
        return res;
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
    @ResponseBody
    @RequestMapping(value = "searchByGoodsName.do_",method = RequestMethod.POST)
    public Result<List<GoodsRE>> searchByGoodsName(@RequestParam(value = "goodsName")String goodsName) {
        if(StringUtils.isEmpty(goodsName)){
            return null;
        }
        Result<List<GoodsRE>> res;
        try {
            res = Result.getSuccessResult(goodsService.searchByGoodsName(goodsName));
        } catch (Exception e) {
            logger.error("模糊查询商品错误！", e);
            res = Result.getBusinessException("模糊查询商品失败", null);
        }
        return res;
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
    @ResponseBody
    @RequestMapping(value = "selectHotTag.do_",method = RequestMethod.GET)
    public Result<List<HotTag>> selectHotTag() {

        Result<List<HotTag>> res;
        try {
            res = Result.getSuccessResult(goodsService.selectHotTag());
        } catch (Exception e) {
            logger.error("获取热门标签错误！", e);
            res = Result.getBusinessException("获取热门标签失败", null);
        }
        return res;
    }
}
