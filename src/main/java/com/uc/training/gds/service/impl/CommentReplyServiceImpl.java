package com.uc.training.gds.service.impl;

import com.uc.training.smadmin.gds.dao.CommentReplyDao;
import com.uc.training.smadmin.gds.model.CommentReply;
import com.uc.training.smadmin.gds.re.CommentDetailRE;
import com.uc.training.smadmin.gds.re.CommentRE;
import com.uc.training.smadmin.gds.re.CommentReplyRE;
import com.uc.training.smadmin.gds.service.CommentReplyService;
import com.uc.training.smadmin.gds.service.CommentService;
import com.uc.training.smadmin.gds.vo.CommentListVO;
import com.uc.training.smadmin.gds.vo.CommentReplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/17
 */
@Service
public class CommentReplyServiceImpl implements CommentReplyService {

    @Autowired
    private CommentReplyDao commentReplyDao;

    @Autowired
    private CommentService commentService;

    /**
     * 获取评论的后台回复
     *
     * @param commentId
     * @return
     */
    @Override
    public String getAdminReplyContent(Long commentId) {
        return commentReplyDao.getAdminReplyContent(commentId);
    }

    /**
     * 根据评论id获取评论详情
     *
     * @param commentReplyVO
     * @return
     */
    @Override
    public CommentDetailRE getCommentDetailByCommentId(CommentReplyVO commentReplyVO) {

        CommentDetailRE commentDetailRE = new CommentDetailRE();

        //商品评论信息
        CommentListVO commentListVO = new CommentListVO();
        commentListVO.setId(commentReplyVO.getCommentId());
        //获取商品评论
        List<CommentRE> commentRE = commentService.getCommentList(commentListVO);
        if (CollectionUtils.isEmpty(commentRE)) {
            return commentDetailRE;
        }
        //商品回复
        commentDetailRE.setCommentRE(commentRE.get(0));
        //商品回复信息
        List<CommentReplyRE> commentReplyREList = commentReplyDao.getCommentReplyList(commentReplyVO);
        //判空
        if (CollectionUtils.isEmpty(commentReplyREList)) {
            return commentDetailRE;
        }
        // 查找父级id对应的昵称
        for (CommentReplyRE commentReplyRE : commentReplyREList) {
           if (commentReplyRE.getParentId() != null) {
               for (CommentReplyRE replyRE : commentReplyREList) {
                   if (replyRE.getId().equals(commentReplyRE.getParentId())) {
                       commentReplyRE.setReplyMemberNickname(replyRE.getMemberNickname());
                   }
               }
           }
        }

        commentDetailRE.setCommentReplyList(commentReplyREList);

        return commentDetailRE;
    }

    /**
     * 新增商品详情回复
     *
     * @param commentReply
     * @return
     */
    @Override
    public Long insertCommentReply(CommentReply commentReply) {
        return commentReplyDao.insertCommentReply(commentReply);
    }

    /**
     * 删除商品详情回复
     *
     * @param commentReplyVO
     * @return
     */
    @Override
    public Integer deleteCommentReplyById(CommentReplyVO commentReplyVO) {
        return commentReplyDao.deleteCommentReplyById(commentReplyVO);
    }

    @Override
    public Integer editCommentReply(CommentReply commentReply) {
        return commentReplyDao.editAdminReplyContent(commentReply);
    }
}
