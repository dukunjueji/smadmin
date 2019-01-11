package com.ucar.smadmin.remote.remoteclient.impl;

import com.ucar.smapi.common.vo.PageVO;
import com.ucar.smapi.common.vo.RemoteResult;
import com.ucar.smapi.gds.dto.AdminHotTagListDTO;
import com.ucar.smapi.gds.dto.CategoryDTO;
import com.ucar.smapi.gds.dto.CommentDTO;
import com.ucar.smapi.gds.dto.CommentListDTO;
import com.ucar.smapi.gds.dto.CommentReplyDTO;
import com.ucar.smapi.gds.dto.CommentReplyModelDTO;
import com.ucar.smapi.gds.dto.GoodsAndPropertyDTO;
import com.ucar.smapi.gds.dto.GoodsDTO;
import com.ucar.smapi.gds.dto.GoodsListDTO;
import com.ucar.smapi.gds.dto.GoodsPicDTO;
import com.ucar.smapi.gds.dto.HotTagDTO;
import com.ucar.smapi.gds.dto.PropertyDTO;
import com.ucar.smapi.gds.re.AdminGoodsPicRE;
import com.ucar.smapi.gds.re.AdminGoodsRE;
import com.ucar.smapi.gds.re.CategoryRE;
import com.ucar.smapi.gds.re.CommentAvgRE;
import com.ucar.smapi.gds.re.CommentCountRE;
import com.ucar.smapi.gds.re.CommentDetailRE;
import com.ucar.smapi.gds.re.CommentPicRE;
import com.ucar.smapi.gds.re.CommentRE;
import com.ucar.smapi.gds.re.GoodsDetailRE;
import com.ucar.smapi.gds.re.GoodsRE;
import com.ucar.smapi.gds.re.GoodsStokeRE;
import com.ucar.smapi.gds.re.HotTagRE;
import com.ucar.smadmin.remote.remoteclient.GoodsRemote;
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
