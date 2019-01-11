package com.uc.training.remote.remoteclient.impl;

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
import com.uc.training.remote.remoteclient.GoodsRemote;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * description: TODO
 *
 * @author 黄宏俊（hongjun.huang01@ucarinc.com）
 * @version 1.0
 * @date 2019/1/5  11:1
 */
@Component
public class GoodsRmoteFallback implements GoodsRemote {
  @Override
  public RemoteResult<List<GoodsRE>> getHotRecommend() {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<PageVO<GoodsRE>> getGoodsPageByCategory(GoodsListDTO goodsListDTO) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<GoodsDetailRE> getGoodsDetailByPropertyId(Long propertyId) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<List<GoodsDetailRE>> getGoodsDetailByGoodsId(Long goodsId) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<List<GoodsDetailRE>> getGoodsDetailByPropertyIds(List<Long> propertyIds) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<PageVO<GoodsRE>> searchByGoodsName(GoodsListDTO goodsListDTO) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<List<HotTagRE>> selectHotTag() {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<GoodsStokeRE> selectGoodsStatus(GoodsAndPropertyDTO goodsAndPropertyDTO) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<Integer> updateAndDeductStoke(GoodsAndPropertyDTO goodsAndPropertyDTO) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<Integer> addComment(CommentDTO commentDTO) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<List<CommentCountRE>> getCommentCountByOrderGoodsId(Long orderGoodsId) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<List<CommentRE>> getCommentList(CommentListDTO commentListDTO) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<CommentAvgRE> getCountBySroce(CommentListDTO commentListDTO) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<CommentAvgRE> getCommentCountAndAvg(CommentListDTO commentListDTO) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<Integer> editCommentById(CommentDTO commentDTO) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<List<CommentPicRE>> getCommentPicByCommentId(Long commentId) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<String> getAdminReplyContent(Long commentId) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<CommentDetailRE> getCommentDetailByCommentId(CommentReplyDTO commentReplyDTO) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<Long> insertCommentReply(CommentReplyModelDTO commentReplyModelDTO) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<Integer> editCommentReply(CommentReplyModelDTO commentReplyModelDTO) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<Integer> deleteCommentReplyById(CommentReplyDTO commentReplyDTO) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<GoodsRE> getGoodsById(Long id) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<List<AdminGoodsRE>> getAdminGoodsList(GoodsListDTO goodsListDTO) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<Integer> adminUpdateGoods(GoodsDTO goodsDTO) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<Long> adminInsertGoods(GoodsDTO goodsDTO) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<Integer> logicDeleteGoods(Long id) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<Integer> updateSales(GoodsAndPropertyDTO goodsAndPropertyDTO) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<Long> getAdminGoodsListCount(GoodsListDTO goodsListDTO) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<Integer> pullOnGoods(GoodsDTO goodsDTO) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<Integer> pullOffGoods(GoodsDTO goodsDTO) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<PageVO<HotTagRE>> getAllHotTagList(AdminHotTagListDTO adminHotTagListDTO) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<Integer> updateHotTag(HotTagDTO hotTagDTO) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<Integer> deleteHotTagById(Long id) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<Long> insertHotTag(HotTagDTO hotTagDTO) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<Long> insertGoodsPic(GoodsPicDTO goodsPicDTO) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<Integer> updateGoodsPic(GoodsPicDTO goodsPicDTO) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<List<AdminGoodsPicRE>> getGoodsPicListByPropertyId(Long propertyId) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<Integer> deleteGoodsPicById(Long id) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<Integer> deleteGoodsPicByPropertyId(Long propertyId) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<Integer> getPropertyIdCountById(Long id) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<Long> getPropertyIdById(Long id) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<Long> insertProperty(PropertyDTO propertyDTO) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<Integer> updateProperty(PropertyDTO propertyDTO) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<List<Long>> getPropertyIdListByGoodsId(Long goodsId) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<List<GoodsDetailRE>> getPropertyListByGoodsId(Long goodsId) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<Integer> deletePropertyAndGoodsPicById(Long id) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<Integer> getGoodsIdCountById(Long id) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<Integer> getCountByProperty(PropertyDTO propertyDTO) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<Integer> getCountByGoodsIdAndName(PropertyDTO var1) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<CategoryRE> queryCategory(Long id) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<Integer> getCountByGoodsPic(GoodsPicDTO goodsPicDTO) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<GoodsDetailRE> getPropertyById(Long id) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<List<CategoryRE>> getCategoryList() {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<Long> addCategory(CategoryDTO categoryDTO) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<Integer> logicDeleteCategory(Long id) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<Integer> updateCategory(CategoryDTO categoryDTO) {
    return RemoteResult.getServiceError();
  }

  @Override
  public RemoteResult<Integer> getCountByNameAndParentId(CategoryDTO category) {
    return RemoteResult.getServiceError();
  }
}
