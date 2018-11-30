package com.uc.training.remote.client;

import com.uc.training.common.vo.PageVO;
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
import com.uc.training.gds.re.HotTagRE;
import com.uc.training.remote.utils.RemoteUtil;
import com.uc.training.smadmin.gds.model.CommentReply;
import com.uc.training.smadmin.gds.vo.CommentListVO;
import com.uc.training.smadmin.gds.vo.CommentReplyVO;
import com.uc.training.smadmin.gds.vo.CommentVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/22
 */
public  final class GdsClient {

    private static final Logger logger = LoggerFactory.getLogger(GdsClient.class.getName());

    /** 根据id查询商品信息*/
    private static final String GET_GOODS_INFO_BY_ID = "smgds.api.getGoodsInfoById";

    /** 添加评论*/
    private static final String ADD_COMMENT = "smgoods.api.addComment";

    /** 根据订单商品表id获取评论List*/
    private static final String GET_COMMENTCOUNT_BY_ORDERGOODSID = "smgoods.api.getCommentCountByOrderGoodsId";

    /** 获取评论列表*/
    private static final String GET_COMMENTLIST = "smgoods.api.getCommentList";

    /** 根据评分上下限获取计数*/
    private static final String GET_COUNT_BY_SROCE = "smgoods.api.getCountBySroce";

    /** 获取总评价数*/
    private static final String GET_COMMENTCOUNT_AND_AVG = "smgoods.api.getCommentCountAndAvg";

    /** 根据ID修改评论*/
    private static final String EDIT_COMMENT_BY_ID = "smgoods.api.editCommentById";

    /** 根据评论id获取评论图片*/
    private static final String GET_COMMENTPIC_BY_COMMENTID = "smgoods.api.getCommentPicByCommentId";

    /** 根据评论Id获取评论的后台回复*/
    private static final String GET_ADMINREPLYCONTENT = "smgoods.api.getAdminReplyContent";

    /** 根据评论id获取评论详情*/
    private static final String GET_COMMENTDETAIL_BY_COMMENTID = "smgoods.api.getCommentDetailByCommentId";

    /** 新增商品详情回复*/
    private static final String INSERT_COMMENTREPLY = "smgoods.api.insertCommentReply";

    /** 根据评论ID修改商家评论信息*/
    private static final String EDIT_COMMENTREPLY = "smgoods.api.editCommentReply";

    /** 删除商品详情回复*/
    private static final String DELETE_COMMENTREPLY_BY_ID = "smgoods.api.deleteCommentReplyById";

    /**
     * 根据商品id查询商品信息
     * @return
     */
    public static GoodsRE getCarGoodsById(GoodsDTO goodsDTO) {
        try {
            return (GoodsRE) RemoteUtil.exec(GET_GOODS_INFO_BY_ID, goodsDTO);
        }catch (ClassCastException e){
            logger.error("类型转换异常");
            logger.error(e.getMessage());
            return null;
        }
    }
    /**
     * 添加评论
     *hhj
     * @param commentVO
     * @return
     */
    public static Integer addComment(CommentVO commentVO) {
        CommentDTO commentDTO = new CommentDTO();
        BeanUtils.copyProperties(commentVO, commentDTO);
        try {
            return (Integer) RemoteUtil.exec(ADD_COMMENT, commentDTO);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * hhj
     * 根据订单商品表id获取评论List
     *
     * @param orderGoodsId
     * @return
     */
    public static List<CommentCountRE> getCommentCountByOrderGoodsId(Long orderGoodsId){
        try {
            return (List<CommentCountRE>) RemoteUtil.exec(GET_COMMENTCOUNT_BY_ORDERGOODSID, orderGoodsId);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * hhj
     * 获取评论列表
     * @param commentListVO
     * @return
     */
    public static List<CommentRE> getCommentList(CommentListVO commentListVO){
        CommentListDTO commentListDTO = new CommentListDTO();
        BeanUtils.copyProperties(commentListVO, commentListDTO);
        try {
            return (List<CommentRE>) RemoteUtil.exec(GET_COMMENTLIST, commentListDTO);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * hhj
     * 根据评分上下限获取计数
     * @param commentListVO
     * @return
     */
    public static CommentAvgRE getCountBySroce(CommentListVO commentListVO){
        CommentListDTO commentListDTO = new CommentListDTO();
        BeanUtils.copyProperties(commentListVO, commentListDTO);
        try {
            return (CommentAvgRE) RemoteUtil.exec(GET_COUNT_BY_SROCE, commentListDTO);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * hhj
     * 获取总评价数
     * @param commentListVO
     * @return
     */
    public static CommentAvgRE getCommentCountAndAvg(CommentListVO commentListVO){
        CommentListDTO commentListDTO = new CommentListDTO();
        BeanUtils.copyProperties(commentListVO, commentListDTO);
        try {
            return (CommentAvgRE) RemoteUtil.exec(GET_COMMENTCOUNT_AND_AVG, commentListDTO);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
            return null;
        }
    }
    /**
     * hhj
     * 根据ID修改评论
     * @param commentVO
     * @return
     */
    public static Integer editCommentById(CommentVO commentVO){
        CommentDTO commentDTO = new CommentDTO();
        BeanUtils.copyProperties(commentVO, commentDTO);
        try {
            return (Integer) RemoteUtil.exec(EDIT_COMMENT_BY_ID, commentDTO);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * hhj
     * 根据评论id获取评论图片
     * @param commentId
     * @return
     */
    public static  List<CommentPicRE> getCommentPicByCommentId(Long commentId){
        try {
            return (List<CommentPicRE>) RemoteUtil.exec(GET_COMMENTPIC_BY_COMMENTID, commentId);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * hhj
     * 根据评论Id获取评论的后台回复
     * @param commentId
     * @return
     */
    public static String getAdminReplyContent(Long commentId){
        try {
            return (String) RemoteUtil.exec(GET_ADMINREPLYCONTENT, commentId);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * hhj
     * 根据评论id获取评论详情
     * @param commentReplyVO
     * @return
     */
    public static CommentDetailRE getCommentDetailByCommentId(CommentReplyVO commentReplyVO){
        CommentReplyDTO commentReplyDTO = new CommentReplyDTO();
        BeanUtils.copyProperties(commentReplyVO, commentReplyDTO);
        try {
            return (CommentDetailRE) RemoteUtil.exec(GET_COMMENTDETAIL_BY_COMMENTID, commentReplyDTO);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * hhj
     * 新增商品详情回复
     * @param commentReply
     * @return
     */
    public static Long insertCommentReply(CommentReply commentReply){
        CommentReplyModelDTO commentReplyModelDTO = new CommentReplyModelDTO();
        BeanUtils.copyProperties(commentReply, commentReplyModelDTO);
        try {
            return (Long) RemoteUtil.exec(INSERT_COMMENTREPLY, commentReplyModelDTO);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * hhj
     * 根据评论ID修改商家评论信息
     * @param commentReply
     * @return
     */
    public static Integer editCommentReply(CommentReply commentReply){
        CommentReplyModelDTO commentReplyModelDTO = new CommentReplyModelDTO();
        BeanUtils.copyProperties(commentReply, commentReplyModelDTO);
        try {
            return (Integer) RemoteUtil.exec(EDIT_COMMENTREPLY, commentReplyModelDTO);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * hhj
     * 删除商品详情回复
     * @param commentReplyVO
     * @return
     */
    public static Integer deleteCommentReplyById(CommentReplyVO commentReplyVO){
        CommentReplyDTO commentReplyDTO = new CommentReplyDTO();
        BeanUtils.copyProperties(commentReplyVO, commentReplyDTO);
        try {
            return (Integer) RemoteUtil.exec(DELETE_COMMENTREPLY_BY_ID, commentReplyDTO);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * 获取商品详情
     */
    private static final String GET_HOT_RECOMMEND = "smgoods.api.getHotRecommend";
    /**
     * 通过分类来获取商品
     */
    private static final String GET_GOODS_PAGE_BY_CATEGORY = "smgoods.api.getGoodsPageByCategory";
    /**
     * 通过单个属性id商品详情
     */
    private static final String GET_GOODS_DETAIL_BY_PROPERTY_ID = "smgoods.api.getGoodsDetailByPropertyId";
    /**
     * 通过商品id获取所有规格的商品详情
     */
    private static final String GET_GOODS_DETAIL_BY_GOODS_ID = "smgoods.api.getGoodsDetailByGoodsId";
    /**
     * 通过多个属性id获取商品详情
     */
    private static final String GET_GOODS_DETAIL_BY_PROPERTY_IDS = "smgoods.api.getGoodsDetailByPropertyIds";
    /**
     * 模糊查询商品列表
     */
    private static final String SEARCH_BY_GOODS_NAME = "smgoods.api.searchByGoodsName";
    /**
     * 获取热门标签
     */
    private static final String SELECT_HOT_TAG = "smgoods.api.selectHotTag";
    /**
     * 获取会员的折扣点
     */
    private static final String GET_MEMBER_DISCOUNT_POINT = "smgoods.api.getMemberDiscountPoint";
    /**
     * 更新减库存
     */
    private static final String UPDATE_AND_DEDUCT_STOKE = "smgoods.api.updateAndDeductStoke";
    /**
     * 减库存之前，查看商品是否下架、删除、检查库存是否足够
     */
    private static final String SELECT_GOODS_STATUS = "smgoods.api.selectGoodsStatus";    /**
     * 获取商品详情
     */
    public static List<GoodsRE> getHotRecommend(){
        try {
            return (List<GoodsRE>) RemoteUtil.exec(GET_HOT_RECOMMEND,null);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }
    /**
     * 通过分类来获取商品
     */
    public static PageVO<GoodsRE> getGoodsPageByCategory(GoodsListDTO goodsListDTO){
        try {
            return (PageVO<GoodsRE>) RemoteUtil.exec(GET_GOODS_PAGE_BY_CATEGORY,  goodsListDTO);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }
    /**
     * 通过单个属性id商品详情
     */
    public static GoodsDetailRE getGoodsDetailByPropertyId(Long propertyId){
        try {
            return (GoodsDetailRE) RemoteUtil.exec(GET_GOODS_DETAIL_BY_PROPERTY_ID,  propertyId);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }
    /**
     * 通过商品id获取所有规格的商品详情
     */
    public static List<GoodsDetailRE> getGoodsDetailByGoodsId(Long goodsId){
        try {
            return (List<GoodsDetailRE>) RemoteUtil.exec(GET_GOODS_DETAIL_BY_GOODS_ID,  goodsId);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }
    /**
     * 通过多个属性id获取商品详情
     */
    public static List<GoodsDetailRE> getGoodsDetailByPropertyIds(List<Long> propertyIds){
        try {
            return (List<GoodsDetailRE>) RemoteUtil.exec(GET_GOODS_DETAIL_BY_PROPERTY_IDS,  propertyIds);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }
    /**
     * 模糊查询商品列表
     */
    public static PageVO<GoodsRE> searchByGoodsName(GoodsListDTO goodsListDTO){
        try {
            return (PageVO<GoodsRE>) RemoteUtil.exec(SEARCH_BY_GOODS_NAME,  goodsListDTO);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }
    /**
     * 获取热门标签
     */
    public static List<HotTagRE> selectHotTag(){
        try {
            return (List<HotTagRE>) RemoteUtil.exec(SELECT_HOT_TAG, null);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 更新减库存
     */
    public static Integer updateAndDeductStoke(GoodsAndPropertyDTO goodsAndPropertyDTO){
        try {
            return (Integer) RemoteUtil.exec(UPDATE_AND_DEDUCT_STOKE,  goodsAndPropertyDTO);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 获取商品详情
     */
    private static final String GET_ADMIN_GOODS_LIST = "smgoods.admin.getAdminGoodsList";
    /**
     * 获取商品详情
     */
    private static final String ADMIN_UPDATE_GOODS = "smgoods.admin.adminUpdateGoods";
    /**
     * 获取商品详情
     */
    private static final String ADMIN_INSERT_GOODS = "smgoods.admin.adminInsertGoods";
    /**
     * 获取商品详情
     */
    private static final String LOGIC_DELETE_GOODS = "smgoods.admin.logicDeleteGoods";
    /**
     * 获取商品详情
     */
    private static final String UPDATE_SALES = "smgoods.admin.updateSales";
    /**
     * 获取商品详情
     */
    private static final String GET_ADMIN_GOODS_LIST_COUNT = "smgoods.admin.getAdminGoodsListCount";
    /**
     * 获取商品详情
     */
    private static final String PULL_ON_GOODS = "smgoods.admin.pullOnGoods";
    /**
     * 获取商品详情
     */
    private static final String PULL_OFF_GOODS = "smgoods.admin.pullOffGoods";
    /**
     * 后台获取标签列表数量
     */
    private static final String GET_ADMIN_HOT_TAG_COUNT = "smgoods.admin.getAdminHotTagCount";
    /**
     * 后台获取商品标签
     */
    private static final String GET_ALL_HOT_TAG_LIST = "smgoods.admin.getAllHotTagList";
    /**
     * 后台更新商品标签
     */
    private static final String UPDATE_HOT_TAG = "smgoods.admin.updateHotTag";
    /**
     * 后台根据主键id删除商品标签
     */
    private static final String DELETE_HOT_TAG_BY_ID = "smgoods.admin.deleteHotTagById";
    /**
     * 后台新增商品标签
     */
    private static final String INSERT_HOT_TAG = "smgoods.admin.insertHotTag";
    /**
     * 新增商品图片
     */
    private static final String INSERT_GOODS_PIC = "smgoods.admin.insertGoodsPic";
    /**
     * 更新商品图片
     */
    private static final String UPDATE_GOODS_PIC = "smgoods.admin.updateGoodsPic";
    /**
     * 根据商品属性id获取图片信息
     */
    private static final String GET_GOODS_PIC_LIST_BY_PROPERTY_ID = "smgoods.admin.getGoodsPicListByPropertyId";
    /**
     * 通过主键id删除商品属性图片
     */
    private static final String DELETE_GOODS_PIC_BY_ID = "smgoods.admin.deleteGoodsPicById";
    /**
     * 通过商品属性id删除图片
     */
    private static final String DELETE_GOODS_PIC_BY_PROPERTY_ID = "smgoods.admin.deleteGoodsPicByPropertyId";
    /**
     * 后台通过图片id获取表中对应商品属性的的数量
     */
    private static final String GET_PROPERTY_ID_COUNT_BY_ID = "smgoods.admin.getPropertyIdCountById";
    /**
     * 通过主键id获取商品属性id
     */
    private static final String GET_PROPERTY_ID_BY_ID = "smgoods.admin.getPropertyIdById";
    /**
     * 新增商品属性
     */
    private static final String INSERT_PROPERTY = "smgoods.admin.insertProperty";
    /**
     * 更新商品
     */
    private static final String UPDATE_PROPERTY = "smgoods.admin.updateProperty";
    /**
     * 通过商品id获取类型id
     */
    private static final String GET_PROPERTY_ID_LIST_BY_GOODS_ID = "smgoods.admin.getPropertyIdListByGoodsId";
    /**
     * 通过商品id获取商品属性
     */
    private static final String GET_PROPERTY_LIST_BY_GOODS_ID = "smgoods.admin.getPropertyListByGoodsId";
    /**
     * 通过主键id删除属性和图片
     */
    private static final String DELETE_PROPERTY_AND_GOODS_PIC_BY_ID = "smgoods.admin.deletePropertyAndGoodsPicById";
    /**
     * 通过商品属性id获取商品的商品属性数量
     */
    private static final String GET_GOODS_ID_COUNT_BY_ID = "smgoods.admin.getGoodsIdCountById";
    /**
     * 获取商品该名称规格的数量
     */
    private static final String GET_COUNT_BY_GOODS_ID_AND_NAME = "smgoods.admin.getCountByGoodsIdAndName";
    /**
     * 根据id获取分类
     */
    private static final String QUERY_CATEGORY = "smgoods.admin.queryCategory";
    /**
     * 通过商品属性id查找商品图片的数量
     */
    private static final String GET_COUNT_BY_GOODS_PIC = "smgoods.admin.getCountByGoodsPic";
    /**
     * 根据id获取商品信息
     */
    private static final String GET_GOODS_BY_ID = "smgoods.admin.getGoodsById";
    /**
     * 通过id获取属性
     */
    private static final String GET_PROPERTY_BY_ID = "smgoods.admin.getPropertyById";
    /**
     * 获取商品详情
     */
    public static List<AdminGoodsRE> getAdminGoodsList(GoodsListDTO goodsListDTO){
        try {
            return (List<AdminGoodsRE>) RemoteUtil.exec(GET_ADMIN_GOODS_LIST,  goodsListDTO);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 获取商品详情
     */
    public static Integer adminUpdateGoods(GoodsDTO adminUpdateGoodsDTO){
        try {
            return (Integer) RemoteUtil.exec(ADMIN_UPDATE_GOODS,  adminUpdateGoodsDTO);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 获取商品详情
     */
    public static Long adminInsertGoods(GoodsDTO goods){
        try {
            return (Long) RemoteUtil.exec(ADMIN_INSERT_GOODS,  goods);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 获取商品详情
     */
    public static Integer logicDeleteGoods(Long id){
        try {
            return (Integer) RemoteUtil.exec(LOGIC_DELETE_GOODS,  id);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 获取商品详情
     */
    public static Integer updateSales(GoodsAndPropertyDTO goodsAndPropertyDTO){
        try {
            return (Integer) RemoteUtil.exec(UPDATE_SALES,  goodsAndPropertyDTO);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 获取商品详情
     */
    public static Long getAdminGoodsListCount(GoodsListDTO GoodsListDTO){
        try {
            return (Long) RemoteUtil.exec(GET_ADMIN_GOODS_LIST_COUNT,  GoodsListDTO);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 获取商品详情
     */
    public static Integer pullOnGoods(GoodsDTO goodsDTO){
        try {
            return (Integer) RemoteUtil.exec(PULL_ON_GOODS,  goodsDTO);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 获取商品详情
     */
    public static Integer pullOffGoods(GoodsDTO goodsDTO){
        try {
            return (Integer) RemoteUtil.exec(PULL_OFF_GOODS,  goodsDTO);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 后台获取标签列表数量
     */
    public static Long getAdminHotTagCount(HotTagDTO hotTagDTO){
        try {
            return (Long) RemoteUtil.exec(GET_ADMIN_HOT_TAG_COUNT,  hotTagDTO);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 后台获取商品标签
     */
    public static List<HotTagRE> getAllHotTagList(HotTagDTO hotTagDTO){
        try {
            return (List<HotTagRE>) RemoteUtil.exec(GET_ALL_HOT_TAG_LIST,  hotTagDTO);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 后台更新商品标签
     */
    public static Integer updateHotTag(HotTagDTO hotTagDTO){
        try {
            return (Integer) RemoteUtil.exec(UPDATE_HOT_TAG,  hotTagDTO);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 后台根据主键id删除商品标签
     */
    public static Integer deleteHotTagById(Long id){
        try {
            return (Integer) RemoteUtil.exec(DELETE_HOT_TAG_BY_ID,  id);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 后台新增商品标签
     */
    public static Long insertHotTag(HotTagDTO hotTagDTO){
        try {
            return (Long) RemoteUtil.exec(INSERT_HOT_TAG,  hotTagDTO);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 新增商品图片
     */
    public static Long insertGoodsPic(GoodsPicDTO goodsPic){
        try {
            return (Long) RemoteUtil.exec(INSERT_GOODS_PIC,  goodsPic);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 更新商品图片
     */
    public static Integer updateGoodsPic(GoodsPicDTO goodsPic){
        try {
            return (Integer) RemoteUtil.exec(UPDATE_GOODS_PIC,  goodsPic);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 根据商品属性id获取图片信息
     */
    public static List<AdminGoodsPicRE> getGoodsPicListByPropertyId(Long propertyId){
        try {
            return (List<AdminGoodsPicRE>) RemoteUtil.exec(GET_GOODS_PIC_LIST_BY_PROPERTY_ID,  propertyId);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 通过主键id删除商品属性图片
     */
    public static Integer deleteGoodsPicById(Long id){
        try {
            return (Integer) RemoteUtil.exec(DELETE_GOODS_PIC_BY_ID,  id);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 通过商品属性id删除图片
     */
    public static Integer deleteGoodsPicByPropertyId(Long propertyId){
        try {
            return (Integer) RemoteUtil.exec(DELETE_GOODS_PIC_BY_PROPERTY_ID,  propertyId);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 后台通过图片id获取表中对应商品属性的的数量
     */
    public static Integer getPropertyIdCountById(Long id){
        try {
            return (Integer) RemoteUtil.exec(GET_PROPERTY_ID_COUNT_BY_ID,  id);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 通过主键id获取商品属性id
     */
    public static Long getPropertyIdById(Long id){
        try {
            return (Long) RemoteUtil.exec(GET_PROPERTY_ID_BY_ID,  id);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 新增商品属性
     */
    public static Long insertProperty(PropertyDTO property){
        try {
            return (Long) RemoteUtil.exec(INSERT_PROPERTY,  property);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 更新商品
     */
    public static Integer updateProperty(PropertyDTO property){
        try {
            return (Integer) RemoteUtil.exec(UPDATE_PROPERTY,  property);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 通过商品id获取类型id
     */
    public static List<Long> getPropertyIdListByGoodsId(Long goodsId){
        try {
            return (List<Long>) RemoteUtil.exec(GET_PROPERTY_ID_LIST_BY_GOODS_ID,  goodsId);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 通过商品id获取商品属性
     */
    public static List<GoodsDetailRE> getPropertyListByGoodsId(Long goodsId){
        try {
            return (List<GoodsDetailRE>) RemoteUtil.exec(GET_PROPERTY_LIST_BY_GOODS_ID,  goodsId);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 通过主键id删除属性和图片
     */
    public static Integer deletePropertyAndGoodsPicById(Long id){
        try {
            return (Integer) RemoteUtil.exec(DELETE_PROPERTY_AND_GOODS_PIC_BY_ID,  id);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 通过商品属性id获取商品的商品属性数量
     */
    public static Integer getGoodsIdCountById(Long id){
        try {
            return (Integer) RemoteUtil.exec(GET_GOODS_ID_COUNT_BY_ID,  id);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 获取商品该名称规格的数量
     */
    public static Integer getCountByGoodsIdAndName(PropertyDTO propertyDTO){
        try {
            return (Integer) RemoteUtil.exec(GET_COUNT_BY_GOODS_ID_AND_NAME,  propertyDTO);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 根据id获取分类
     */
    public static CategoryRE queryCategory(Long id){
        try {
            return (CategoryRE) RemoteUtil.exec(QUERY_CATEGORY,  id);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 通过商品属性id查找商品图片的数量
     */
    public static Integer getCountByGoodsPic(GoodsPicDTO goodsPic){
        try {
            return (Integer) RemoteUtil.exec(GET_COUNT_BY_GOODS_PIC,  goodsPic);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 根据id获取商品信息
     */
    public static GoodsRE getGoodsById(Long id){
        try {
            return (GoodsRE) RemoteUtil.exec(GET_GOODS_BY_ID,  id);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 通过id获取属性
     */
    public static GoodsDetailRE getPropertyById(Long id){
        try {
            return (GoodsDetailRE) RemoteUtil.exec(GET_PROPERTY_BY_ID,  id);
        } catch (ClassCastException e) {
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return null;
    }

}