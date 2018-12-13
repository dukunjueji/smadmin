package com.uc.training.gds.service.impl;

import com.uc.training.gds.re.CommentRE;
import com.uc.training.gds.service.CommentReplyService;
import com.uc.training.gds.service.CommentService;
import com.uc.training.gds.vo.CommentListVO;
import com.uc.training.gds.vo.CommentReplyVO;
import com.uc.training.remote.client.BaseClient;
import com.uc.training.remote.client.GdsClient;
import com.uc.training.gds.re.CommentDetailRE;
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
    private CommentService commentService;

    /**
     * 获取评论的后台回复
     *
     * @param commentId
     * @return
     */
    @Override
    public String getAdminReplyContent(Long commentId) {
        return GdsClient.getAdminReplyContent(commentId);
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
        CommentListVO commentListVO = new CommentListVO();
        commentListVO.setId(commentReplyVO.getCommentId());
        //获取商品评论
        List<CommentRE> commentRE = commentService.getCommentList(commentListVO);
        if (CollectionUtils.isEmpty(commentRE)) {
            return commentDetailRE;
        }
        commentDetailRE = GdsClient.getCommentDetailByCommentId(commentReplyVO);
        commentDetailRE.setCommentRE(commentRE.get(0));
        return commentDetailRE;
    }

    /**
     * 新增商品详情回复
     *
     * @param commentReplyVO
     * @return
     */
    @Override
    public Long insertCommentReply(CommentReplyVO commentReplyVO) {
        return GdsClient.insertCommentReply(commentReplyVO);
    }

    /**
     * 删除商品详情回复
     *
     * @param commentReplyVO
     * @return
     */
    @Override
    public Integer deleteCommentReplyById(CommentReplyVO commentReplyVO) {
        return GdsClient.deleteCommentReplyById(commentReplyVO);
    }

    @Override
    public Integer editCommentReply(CommentReplyVO commentReplyVO) {
        return GdsClient.editCommentReply(commentReplyVO);
    }
}
