package com.ucar.smadmin.remote.remoteclient;

import com.ucar.smadmin.common.vo.PageVO;
import com.ucar.smadmin.common.vo.RemoteResult;
import com.ucar.smadmin.gds.dto.AdminHotTagListDTO;
import com.ucar.smadmin.gds.dto.CategoryDTO;
import com.ucar.smadmin.gds.dto.CommentDTO;
import com.ucar.smadmin.gds.dto.CommentListDTO;
import com.ucar.smadmin.gds.dto.CommentReplyDTO;
import com.ucar.smadmin.gds.dto.CommentReplyModelDTO;
import com.ucar.smadmin.gds.dto.GoodsAndPropertyDTO;
import com.ucar.smadmin.gds.dto.GoodsDTO;
import com.ucar.smadmin.gds.dto.GoodsListDTO;
import com.ucar.smadmin.gds.dto.GoodsPicDTO;
import com.ucar.smadmin.gds.dto.HotTagDTO;
import com.ucar.smadmin.gds.dto.PropertyDTO;
import com.ucar.smadmin.gds.re.AdminGoodsPicRE;
import com.ucar.smadmin.gds.re.AdminGoodsRE;
import com.ucar.smadmin.gds.re.CategoryRE;
import com.ucar.smadmin.gds.re.CommentAvgRE;
import com.ucar.smadmin.gds.re.CommentCountRE;
import com.ucar.smadmin.gds.re.CommentDetailRE;
import com.ucar.smadmin.gds.re.CommentPicRE;
import com.ucar.smadmin.gds.re.CommentRE;
import com.ucar.smadmin.gds.re.GoodsDetailRE;
import com.ucar.smadmin.gds.re.GoodsRE;
import com.ucar.smadmin.gds.re.GoodsStokeRE;
import com.ucar.smadmin.gds.re.HotTagRE;
import com.ucar.smadmin.remote.remoteclient.impl.GoodsRmoteFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * description: TODO
 *
 * @author 黄宏俊（hongjun.huang01@ucarinc.com）
 * @version 1.0
 * @date 2019/1/5  10:58
 */
@FeignClient(name = "provider-goods", fallback = GoodsRmoteFallback.class)
public interface GoodsRemote {
    /**
     * 获取商品详情
     */
    @PostMapping(value = "smgoods/api/getHotRecommend")
    RemoteResult<List<GoodsRE>> getHotRecommend();

    /**
     * 通过分类来获取商品
     *
     * @param goodsListDTO
     * @return
     **/
    @PostMapping(value = "smgoods/api/getGoodsPageByCategory")
    RemoteResult<PageVO<GoodsRE>> getGoodsPageByCategory(GoodsListDTO goodsListDTO);

    /**
     * 通过单个属性id商品详情
     *
     * @param propertyId
     * @return
     */
    @PostMapping(value = "smgoods/api/getGoodsDetailByPropertyId")
    RemoteResult<GoodsDetailRE> getGoodsDetailByPropertyId(Long propertyId);

    /**
     * 通过商品id获取所有规格的商品详情
     *
     * @param goodsId
     * @return
     */
    @PostMapping(value = "smgoods/api/getGoodsDetailByGoodsId")
    RemoteResult<List<GoodsDetailRE>> getGoodsDetailByGoodsId(Long goodsId);

    /**
     * 通过多个属性id获取商品详情
     *
     * @param
     * @return
     */
    @PostMapping(value = "smgoods/api/getGoodsDetailByPropertyIds")
    RemoteResult<List<GoodsDetailRE>> getGoodsDetailByPropertyIds(List<Long> propertyIds);

    /**
     * 模糊查询商品列表
     *
     * @param goodsListDTO
     * @return
     */
    @PostMapping(value = "smgoods/api/searchByGoodsName")
    RemoteResult<PageVO<GoodsRE>> searchByGoodsName(GoodsListDTO goodsListDTO);

    /**
     * 获取热门标签
     *
     * @param
     * @return
     */
    @PostMapping(value = "smgoods/api/selectHotTag")
    RemoteResult<List<HotTagRE>> selectHotTag();

    /**
     * 获取商品上架删除库存信息
     *
     * @param goodsAndPropertyDTO
     * @return
     */
    @PostMapping(value = "smgoods/api/selectGoodsStatus")
    RemoteResult<GoodsStokeRE> selectGoodsStatus(GoodsAndPropertyDTO goodsAndPropertyDTO);

    /**
     * 更新减库存
     *
     * @param goodsAndPropertyDTO
     * @return
     */
    @PostMapping(value = "smgoods/api/updateAndDeductStoke")
    RemoteResult<Integer> updateAndDeductStoke(@RequestBody GoodsAndPropertyDTO goodsAndPropertyDTO);

    /**
     * 添加评论
     *
     * @param commentDTO
     * @return
     */
    @PostMapping(value = "smgoods/api/addComment")
    RemoteResult<Integer> addComment(CommentDTO commentDTO);

    /**
     * 根据订单商品表id获取评论List
     *
     * @param orderGoodsId
     * @return
     */
    @PostMapping(value = "smgoods/api/getCommentCountByOrderGoodsId")
    RemoteResult<List<CommentCountRE>> getCommentCountByOrderGoodsId(Long orderGoodsId);

    /**
     * 获取评论列表
     *
     * @param commentListDTO
     * @return
     */
    @PostMapping(value = "smgoods/api/getCommentList")
    RemoteResult<List<CommentRE>> getCommentList(CommentListDTO commentListDTO);

    /**
     * 根据评分上下限获取计数
     *
     * @param commentListDTO
     * @return
     */
    @PostMapping(value = "smgoods/api/getCountBySroce")
    RemoteResult<CommentAvgRE> getCountBySroce(CommentListDTO commentListDTO);

    /**
     * 获取总评价数
     *
     * @param commentListDTO
     * @return
     */
    @PostMapping(value = "smgoods/api/getCommentCountAndAvg")
    RemoteResult<CommentAvgRE> getCommentCountAndAvg(CommentListDTO commentListDTO);

    /**
     * 根据ID修改评论
     *
     * @param commentDTO
     * @return
     */
    @PostMapping(value = "smgoods/api/editCommentById")
    RemoteResult<Integer> editCommentById(CommentDTO commentDTO);

    /**
     * 根据评论id获取评论图片
     *
     * @param commentId
     * @return
     */
    @PostMapping(value = "smgoods/api/getCommentPicByCommentId")
    RemoteResult<List<CommentPicRE>> getCommentPicByCommentId(Long commentId);

    /**
     * 根据评论Id获取评论的后台回复
     *
     * @param commentId
     * @return
     */
    @PostMapping(value = "smgoods/api/getAdminReplyContent")
    RemoteResult<String> getAdminReplyContent(Long commentId);

    /**
     * 根据评论id获取评论详情
     *
     * @param commentReplyDTO
     * @return
     */
    @PostMapping(value = "smgoods/api/getCommentDetailByCommentId")
    RemoteResult<CommentDetailRE> getCommentDetailByCommentId(CommentReplyDTO commentReplyDTO);

    /**
     * 新增商品详情回复
     *
     * @param commentReplyModelDTO
     * @return
     */
    @PostMapping(value = "smgoods/api/insertCommentReply")
    RemoteResult<Long> insertCommentReply(CommentReplyModelDTO commentReplyModelDTO);

    /**
     * 根据评论ID修改商家评论信息
     *
     * @param commentReplyModelDTO
     * @return
     */
    @PostMapping(value = "smgoods/api/editCommentReply")
    RemoteResult<Integer> editCommentReply(CommentReplyModelDTO commentReplyModelDTO);

    /**
     * 删除商品详情回复
     *
     * @param commentReplyDTO
     * @return
     */
    @PostMapping(value = "smgoods/api/deleteCommentReplyById")
    RemoteResult<Integer> deleteCommentReplyById(CommentReplyDTO commentReplyDTO);

    /**
     * 根据id获取商品信息
     *
     * @param id
     * @return
     */
    @PostMapping(value = "smgoods/api/getGoodsById")
    RemoteResult<GoodsRE> getGoodsById(Long id);

    /**
     * 后台获取所有商品
     *
     * @param goodsListDTO
     * @return
     */
    @PostMapping(value = "smgoods/api/getAdminGoodsList")
    RemoteResult<List<AdminGoodsRE>> getAdminGoodsList(GoodsListDTO goodsListDTO);

    /**
     * 后台更新商品
     *
     * @param goodsDTO
     * @return
     */
    @PostMapping(value = "smgoods/api/adminUpdateGoods")
    RemoteResult<Integer> adminUpdateGoods(GoodsDTO goodsDTO);

    /**
     * 后台新增商品
     *
     * @param goodsDTO
     * @return
     */
    @PostMapping(value = "smgoods/api/adminInsertGoods")
    RemoteResult<Long> adminInsertGoods(GoodsDTO goodsDTO);

    /**
     * 逻辑删除商品
     *
     * @param id
     * @return
     */
    @PostMapping(value = "smgoods/api/logicDeleteGoods")
    RemoteResult<Integer> logicDeleteGoods(Long id);

    /**
     * 更新销量
     *
     * @param goodsAndPropertyDTO
     * @return
     */
    @PostMapping(value = "smgoods/api/updateSales")
    RemoteResult<Integer> updateSales(GoodsAndPropertyDTO goodsAndPropertyDTO);

    /**
     * 后台获取商品数量
     *
     * @param goodsListDTO
     * @return
     */
    @PostMapping(value = "smgoods/api/getAdminGoodsListCount")
    RemoteResult<Long> getAdminGoodsListCount(GoodsListDTO goodsListDTO);

    /**
     * 商品上架
     *
     * @param goodsDTO
     * @return
     */
    @PostMapping(value = "smgoods/api/pullOnGoods")
    RemoteResult<Integer> pullOnGoods(GoodsDTO goodsDTO);

    /**
     * 商品下架
     *
     * @param goodsDTO
     * @return
     */
    @PostMapping(value = "smgoods/api/pullOffGoods")
    RemoteResult<Integer> pullOffGoods(@RequestBody GoodsDTO goodsDTO);

    /**
     * 后台获取商品标签
     *
     * @param adminHotTagListDTO
     * @return
     */
    @PostMapping(value = "smgoods/api/getAllHotTagList")
    RemoteResult<PageVO<HotTagRE>> getAllHotTagList(AdminHotTagListDTO adminHotTagListDTO);

    /**
     * 后台更新商品标签
     *
     * @param hotTagDTO
     * @return
     */
    @PostMapping(value = "smgoods/api/updateHotTag")
    RemoteResult<Integer> updateHotTag(HotTagDTO hotTagDTO);

    /**
     * 后台根据主键id删除商品标签
     *
     * @param id
     * @return
     */
    @PostMapping(value = "smgoods/api/deleteHotTagById")
    RemoteResult<Integer> deleteHotTagById(Long id);

    /**
     * 后台新增商品标签
     *
     * @param hotTagDTO
     * @return
     */
    @PostMapping(value = "smgoods/api/insertHotTag")
    RemoteResult<Long> insertHotTag(HotTagDTO hotTagDTO);

    /**
     * 新增商品图片
     *
     * @param goodsPicDTO
     * @return
     */
    @PostMapping(value = "smgoods/api/insertGoodsPic")
    RemoteResult<Long> insertGoodsPic(GoodsPicDTO goodsPicDTO);

    /**
     * 更新商品图片
     *
     * @param goodsPicDTO
     * @return
     */
    @PostMapping(value = "smgoods/api/updateGoodsPic")
    RemoteResult<Integer> updateGoodsPic(GoodsPicDTO goodsPicDTO);

    /**
     * 根据商品属性id获取图片信息
     *
     * @param propertyId
     * @return
     */
    @PostMapping(value = "smgoods/api/getGoodsPicListByPropertyId")
    RemoteResult<List<AdminGoodsPicRE>> getGoodsPicListByPropertyId(Long propertyId);

    /**
     * 通过主键id删除商品属性图片
     *
     * @param id
     * @return
     */
    @PostMapping(value = "smgoods/api/deleteGoodsPicById")
    RemoteResult<Integer> deleteGoodsPicById(Long id);

    /**
     * 通过商品属性id删除图片
     *
     * @param propertyId
     * @return
     */
    @PostMapping(value = "smgoods/api/deleteGoodsPicByPropertyId")
    RemoteResult<Integer> deleteGoodsPicByPropertyId(Long propertyId);

    /**
     * 后台通过图片id获取表中对应商品属性的的数量
     *
     * @param id
     * @return
     */
    @PostMapping(value = "smgoods/api/getPropertyIdCountById")
    RemoteResult<Integer> getPropertyIdCountById(Long id);

    /**
     * 通过主键id获取商品属性id
     *
     * @param id
     * @return
     */
    @PostMapping(value = "smgoods/api/getPropertyIdById")
    RemoteResult<Long> getPropertyIdById(Long id);

    /**
     * 新增商品属性
     *
     * @param propertyDTO
     * @return
     */
    @PostMapping(value = "smgoods/api/insertProperty")
    RemoteResult<Long> insertProperty(PropertyDTO propertyDTO);

    /**
     * 更新商品
     *
     * @param propertyDTO
     * @return
     */
    @PostMapping(value = "smgoods/api/updateProperty")
    RemoteResult<Integer> updateProperty(PropertyDTO propertyDTO);

    /**
     * 通过商品id获取类型id
     *
     * @param goodsId
     * @return
     */
    @PostMapping(value = "smgoods/api/getPropertyIdListByGoodsId")
    RemoteResult<List<Long>> getPropertyIdListByGoodsId(Long goodsId);

    /**
     * 通过商品id获取商品属性
     *
     * @param goodsId
     * @return
     */
    @PostMapping(value = "smgoods/api/getPropertyListByGoodsId")
    RemoteResult<List<GoodsDetailRE>> getPropertyListByGoodsId(Long goodsId);

    /**
     * 通过主键id删除属性和图片
     *
     * @param id
     * @return
     */
    @PostMapping(value = "smgoods/api/deletePropertyAndGoodsPicById")
    RemoteResult<Integer> deletePropertyAndGoodsPicById(Long id);

    /**
     * 通过商品属性id获取商品的商品属性数量
     *
     * @param id
     * @return
     */
    @PostMapping(value = "smgoods/api/getGoodsIdCountById")
    RemoteResult<Integer> getGoodsIdCountById(Long id);

    /**
     * 获取商品该名称规格的数量
     *
     * @param propertyDTO
     * @return
     */
    @PostMapping(value = "smgoods/api/getCountByProperty")
    RemoteResult<Integer> getCountByProperty(PropertyDTO propertyDTO);

    @PostMapping(value = "smgoods/api/getCountByGoodsIdAndName")
    RemoteResult<Integer> getCountByGoodsIdAndName(PropertyDTO var1);

    /**
     * 根据id获取分类
     *
     * @param id
     * @return
     */
    @PostMapping(value = "smgoods/api/queryCategory")
    RemoteResult<CategoryRE> queryCategory(Long id);

    /**
     * 通过商品属性id查找商品图片的数量
     *
     * @param goodsPicDTO
     * @return
     */
    @PostMapping(value = "smgoods/api/getCountByGoodsPic")
    RemoteResult<Integer> getCountByGoodsPic(GoodsPicDTO goodsPicDTO);

    /**
     * 通过id获取属性
     *
     * @param id
     * @return
     */
    @PostMapping(value = "smgoods/api/getPropertyById")
    RemoteResult<GoodsDetailRE> getPropertyById(Long id);

    /**
     * 获取商品分类
     *
     * @return
     */
    @PostMapping(value = "smgoods/api/getCategoryList")
    RemoteResult<List<CategoryRE>> getCategoryList();

    /**
     * 增加分类
     *
     * @param categoryDTO
     * @return
     */
    @PostMapping(value = "smgoods/api/addCategory")
    RemoteResult<Long> addCategory(CategoryDTO categoryDTO);

    /**
     * 删除分类
     *
     * @param id
     * @return
     */
    @PostMapping(value = "smgoods/api/logicDeleteCategory")
    RemoteResult<Integer> logicDeleteCategory(Long id);

    /**
     * 编辑分类
     *
     * @param categoryDTO
     * @return
     */
    @PostMapping(value = "smgoods/api/updateCategory")
    RemoteResult<Integer> updateCategory(CategoryDTO categoryDTO);

    /**
     * 根据name和parentId查找数量
     *
     * @param category
     * @return
     */
    @PostMapping(value = "smgoods/api/getCountByNameAndParentId")
    RemoteResult<Integer> getCountByNameAndParentId(CategoryDTO category);
}
