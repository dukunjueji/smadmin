package com.uc.training.remote.remoteclient;

import com.uc.training.common.vo.PageVO;
import com.uc.training.common.vo.RemoteResult;
import com.uc.training.gds.dto.AdminHotTagListDTO;
import com.uc.training.gds.dto.CategoryDTO;
import com.uc.training.gds.dto.CommentDTO;
import com.uc.training.gds.dto.CommentListDTO;
import com.uc.training.gds.dto.CommentReplyDTO;
import com.uc.training.gds.dto.CommentReplyModelDTO;
import com.uc.training.gds.dto.GoodsAndPropertyDTO;
import com.uc.training.gds.dto.GoodsDTO;
import com.uc.training.gds.dto.GoodsListDTO;
import com.uc.training.gds.dto.GoodsPicDTO;
import com.uc.training.gds.dto.HotTagDTO;
import com.uc.training.gds.dto.PropertyDTO;
import com.uc.training.gds.re.AdminGoodsPicRE;
import com.uc.training.gds.re.AdminGoodsRE;
import com.uc.training.gds.re.CategoryRE;
import com.uc.training.gds.re.CommentAvgRE;
import com.uc.training.gds.re.CommentCountRE;
import com.uc.training.gds.re.CommentDetailRE;
import com.uc.training.gds.re.CommentPicRE;
import com.uc.training.gds.re.CommentRE;
import com.uc.training.gds.re.GoodsDetailRE;
import com.uc.training.gds.re.GoodsRE;
import com.uc.training.gds.re.GoodsStokeRE;
import com.uc.training.gds.re.HotTagRE;
import com.uc.training.remote.remoteclient.impl.GoodsRmoteFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * description: TODO
 *
 * @author 黄宏俊（hongjun.huang01@ucarinc.com）
 * @version 1.0
 * @date 2019/1/5  10:58
 */
@FeignClient(name = "provider-smbase", fallback = GoodsRmoteFallback.class)
public interface GoodsRmote {
  /**
   * 获取商品详情
   *
   */
  @PostMapping(value = "smbase/api/queryOneMember")
  RemoteResult<List<GoodsRE>> getHotRecommend();

  /**
   * 通过分类来获取商品
   * @param  goodsListDTO
   * @return
   **/

  RemoteResult<PageVO<GoodsRE>> getGoodsPageByCategory(GoodsListDTO goodsListDTO);

  /**
   * 通过单个属性id商品详情
   *  @param propertyId
   *  @return
   */
  RemoteResult<GoodsDetailRE> getGoodsDetailByPropertyId(Long propertyId);

  /**
   * 通过商品id获取所有规格的商品详情
   *  @param goodsId
   *  @return
   */
  RemoteResult<List<GoodsDetailRE>> getGoodsDetailByGoodsId(Long goodsId);

  /**
   * 通过多个属性id获取商品详情
   *  @param
   *  @return
   */
  RemoteResult<List<GoodsDetailRE>> getGoodsDetailByPropertyIds(List<Long> propertyIds);

  /**
   * 模糊查询商品列表
   *  @param goodsListDTO
   *  @return
   */
  RemoteResult<PageVO<GoodsRE>> searchByGoodsName(GoodsListDTO goodsListDTO);

  /**
   * 获取热门标签
   *  @param
   *  @return
   */
  RemoteResult<List<HotTagRE>> selectHotTag();

  /**
   * 获取商品上架删除库存信息
   *  @param goodsAndPropertyDTO
   *  @return
   */
  RemoteResult<GoodsStokeRE> selectGoodsStatus(GoodsAndPropertyDTO goodsAndPropertyDTO);

  /**
   * 更新减库存
   *  @param goodsAndPropertyDTO
   *  @return
   */
  RemoteResult<Integer> updateAndDeductStoke(GoodsAndPropertyDTO goodsAndPropertyDTO);

  /**
   * 添加评论
   *  @param commentDTO
   *  @return
   */
  RemoteResult<Integer> addComment(CommentDTO commentDTO);

  /**
   * 根据订单商品表id获取评论List
   *  @param orderGoodsId
   *  @return
   */
  RemoteResult<List<CommentCountRE>> getCommentCountByOrderGoodsId(Long orderGoodsId);

  /**
   * 获取评论列表
   *  @param commentListDTO
   *  @return
   */
  RemoteResult<List<CommentRE>> getCommentList(CommentListDTO commentListDTO);

  /**
   * 根据评分上下限获取计数
   *  @param commentListDTO
   *  @return
   */
  RemoteResult<CommentAvgRE> getCountBySroce(CommentListDTO commentListDTO);

  /**
   * 获取总评价数
   *  @param commentListDTO
   *  @return
   */
  RemoteResult<CommentAvgRE> getCommentCountAndAvg(CommentListDTO commentListDTO);

  /**
   * 根据ID修改评论
   *  @param commentDTO
   *  @return
   */
  RemoteResult<Integer> editCommentById(CommentDTO commentDTO);

  /**
   * 根据评论id获取评论图片
   *  @param commentId
   *  @return
   */
  RemoteResult<List<CommentPicRE>> getCommentPicByCommentId(Long commentId);

  /**
   * 根据评论Id获取评论的后台回复
   *  @param commentId
   *  @return
   */
  RemoteResult<String> getAdminReplyContent(Long commentId);

  /**
   * 根据评论id获取评论详情
   *  @param commentReplyDTO
   *  @return
   */
  RemoteResult<CommentDetailRE> getCommentDetailByCommentId(CommentReplyDTO commentReplyDTO);

  /**
   * 新增商品详情回复
   *  @param commentReplyModelDTO
   *  @return
   */
  RemoteResult<Long> insertCommentReply(CommentReplyModelDTO commentReplyModelDTO);

  /**
   * 根据评论ID修改商家评论信息
   *  @param commentReplyModelDTO
   *  @return
   */
  RemoteResult<Integer> editCommentReply(CommentReplyModelDTO commentReplyModelDTO);

  /**
   * 删除商品详情回复
   *  @param commentReplyDTO
   *  @return
   */
  RemoteResult<Integer> deleteCommentReplyById(CommentReplyDTO commentReplyDTO);

  /**
   * 根据id获取商品信息
   *  @param id
   *  @return
   */
  RemoteResult<GoodsRE> getGoodsById(Long id);

  /**
   * 后台获取所有商品
   *
   * @param goodsListDTO
   * @return
   */
  RemoteResult<List<AdminGoodsRE>> getAdminGoodsList(GoodsListDTO goodsListDTO);

  /**
   * 后台更新商品
   * @param goodsDTO
   * @return
   */
  RemoteResult<Integer> adminUpdateGoods(GoodsDTO goodsDTO);

  /**
   * 后台新增商品
   * @param goodsDTO
   * @return
   */
  RemoteResult<Long> adminInsertGoods(GoodsDTO goodsDTO);

  /**
   * 逻辑删除商品
   * @param id
   * @return
   */
  RemoteResult<Integer> logicDeleteGoods(Long id);

  /**
   * 更新销量
   *
   * @param goodsAndPropertyDTO
   * @return
   */
  RemoteResult<Integer> updateSales(GoodsAndPropertyDTO goodsAndPropertyDTO);

  /**
   * 后台获取商品数量
   *
   * @param goodsListDTO
   * @return
   */
  RemoteResult<Long> getAdminGoodsListCount(GoodsListDTO goodsListDTO);

  /**
   * 商品上架
   *
   * @param goodsDTO
   * @return
   */
  RemoteResult<Integer> pullOnGoods(GoodsDTO goodsDTO);

  /**
   * 商品下架
   *
   * @param goodsDTO
   * @return
   */
  RemoteResult<Integer> pullOffGoods(GoodsDTO goodsDTO);

  /**
   * 后台获取商品标签
   *
   * @param adminHotTagListDTO
   * @return
   */
  RemoteResult<PageVO<HotTagRE>> getAllHotTagList(AdminHotTagListDTO adminHotTagListDTO);

  /**
   * 后台更新商品标签
   *
   * @param hotTagDTO
   * @return
   */
  RemoteResult<Integer> updateHotTag(HotTagDTO hotTagDTO);

  /**
   * 后台根据主键id删除商品标签
   *
   * @param id
   * @return
   */
  RemoteResult<Integer> deleteHotTagById(Long id);

  /**
   * 后台新增商品标签
   *
   * @param hotTagDTO
   * @return
   */
  RemoteResult<Long> insertHotTag(HotTagDTO hotTagDTO);

  /**
   * 新增商品图片
   *
   * @param goodsPicDTO
   * @return
   */
  RemoteResult<Long> insertGoodsPic(GoodsPicDTO goodsPicDTO);

  /**
   * 更新商品图片
   *
   * @param goodsPicDTO
   * @return
   */
  RemoteResult<Integer> updateGoodsPic(GoodsPicDTO goodsPicDTO);

  /**
   * 根据商品属性id获取图片信息
   *
   * @param propertyId
   * @return
   */
  RemoteResult<List<AdminGoodsPicRE>> getGoodsPicListByPropertyId(Long propertyId);

  /**
   * 通过主键id删除商品属性图片
   *
   * @param id
   * @return
   */
  RemoteResult<Integer> deleteGoodsPicById(Long id);

  /**
   * 通过商品属性id删除图片
   *
   * @param propertyId
   * @return
   */
  RemoteResult<Integer> deleteGoodsPicByPropertyId(Long propertyId);

  /**
   * 后台通过图片id获取表中对应商品属性的的数量
   *
   * @param id
   * @return
   */
  RemoteResult<Integer> getPropertyIdCountById(Long id);

  /**
   * 通过主键id获取商品属性id
   *
   * @param id
   * @return
   */
  RemoteResult<Long> getPropertyIdById(Long id);

  /**
   * 新增商品属性
   *
   * @param propertyDTO
   * @return
   */
  RemoteResult<Long> insertProperty(PropertyDTO propertyDTO);

  /**
   * 更新商品
   *
   * @param propertyDTO
   * @return
   */
  RemoteResult<Integer> updateProperty(PropertyDTO propertyDTO);

  /**
   * 通过商品id获取类型id
   *
   * @param goodsId
   * @return
   */
  RemoteResult<List<Long>> getPropertyIdListByGoodsId(Long goodsId);

  /**
   * 通过商品id获取商品属性
   *
   * @param goodsId
   * @return
   */
  RemoteResult<List<GoodsDetailRE>> getPropertyListByGoodsId(Long goodsId);

  /**
   * 通过主键id删除属性和图片
   *
   * @param id
   * @return
   */
  RemoteResult<Integer> deletePropertyAndGoodsPicById(Long id);

  /**
   * 通过商品属性id获取商品的商品属性数量
   *
   * @param id
   * @return
   */
  RemoteResult<Integer> getGoodsIdCountById(Long id);

  /**
   * 获取商品该名称规格的数量
   * @param propertyDTO
   * @return
   */
  RemoteResult<Integer> getCountByProperty(PropertyDTO propertyDTO);

  RemoteResult<Integer> getCountByGoodsIdAndName(PropertyDTO var1);

  /**
   * 根据id获取分类
   *
   * @param id
   * @return
   */
  RemoteResult<CategoryRE> queryCategory(Long id);

  /**
   * 通过商品属性id查找商品图片的数量
   * @param goodsPicDTO
   * @return
   */
  RemoteResult<Integer> getCountByGoodsPic(GoodsPicDTO goodsPicDTO);

  /**
   * 通过id获取属性
   * @param id
   * @return
   */
  RemoteResult<GoodsDetailRE> getPropertyById(Long id);

  /**
   * 获取商品分类
   * @return
   */
  RemoteResult<List<CategoryRE>> getCategoryList();

  /**
   * 增加分类
   * @param categoryDTO
   * @return
   */
  RemoteResult<Long> addCategory(CategoryDTO categoryDTO);

  /**
   * 删除分类
   * @param id
   * @return
   */
  RemoteResult<Integer> logicDeleteCategory(Long id);

  /**
   * 编辑分类
   * @param categoryDTO
   * @return
   */
  RemoteResult<Integer> updateCategory(CategoryDTO categoryDTO);

  /**
   * 根据name和parentId查找数量
   * @param category
   * @return
   */
  RemoteResult<Integer> getCountByNameAndParentId(CategoryDTO category);
}
