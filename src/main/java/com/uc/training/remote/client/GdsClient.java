package com.uc.training.remote.client;

import com.uc.training.common.vo.RemoteResult;
import com.uc.training.gds.dto.CommentDTO;
import com.uc.training.gds.dto.CommentListDTO;
import com.uc.training.gds.dto.CommentReplyDTO;
import com.uc.training.gds.dto.CommentReplyModelDTO;
import com.uc.training.gds.dto.GoodsDTO;
import com.uc.training.gds.re.CommentAvgRE;
import com.uc.training.gds.re.CommentCountRE;
import com.uc.training.gds.re.CommentDetailRE;
import com.uc.training.gds.re.CommentPicRE;
import com.uc.training.gds.re.CommentRE;
import com.uc.training.smadmin.gds.model.CommentReply;
import com.uc.training.smadmin.gds.re.GoodsRE;
import com.uc.training.smadmin.gds.vo.CommentListVO;
import com.uc.training.smadmin.gds.vo.CommentReplyVO;
import com.uc.training.smadmin.gds.vo.CommentVO;
import com.uc.training.utils.RemoteUtil;
import com.ycc.base.framework.remote.client.annotations.RemoteMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/22
 */
@Service
public class GdsClient {

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
    public GoodsRE getCarGoodsById(GoodsDTO goodsDTO) {
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
    public Integer addComment(CommentVO commentVO) {
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
    public List<CommentCountRE> getCommentCountByOrderGoodsId(Long orderGoodsId){
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
    public List<CommentRE> getCommentList(CommentListVO commentListVO){
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
    public CommentAvgRE getCountBySroce(CommentListVO commentListVO){
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
    public CommentAvgRE getCommentCountAndAvg(CommentListVO commentListVO){
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
    public Integer editCommentById(CommentVO commentVO){
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
    public List<CommentPicRE> getCommentPicByCommentId(Long commentId){
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
    public String getAdminReplyContent(Long commentId){
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
    public CommentDetailRE getCommentDetailByCommentId(CommentReplyVO commentReplyVO){
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
    public Long insertCommentReply(CommentReply commentReply){
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
    public Integer editCommentReply(CommentReply commentReply){
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
    public Integer deleteCommentReplyById(CommentReplyVO commentReplyVO){
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
}