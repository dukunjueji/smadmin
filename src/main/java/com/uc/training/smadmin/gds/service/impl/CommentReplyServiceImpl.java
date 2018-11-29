package com.uc.training.smadmin.gds.service.impl;

import com.uc.training.remote.client.GdsClient;
import com.uc.training.smadmin.gds.model.CommentReply;
import com.uc.training.gds.re.CommentDetailRE;
import com.uc.training.smadmin.gds.service.CommentReplyService;
import com.uc.training.smadmin.gds.service.CommentService;
import com.uc.training.smadmin.gds.vo.CommentReplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return GdsClient.getCommentDetailByCommentId(commentReplyVO);
    }

    /**
     * 新增商品详情回复
     *
     * @param commentReply
     * @return
     */
    @Override
    public Long insertCommentReply(CommentReply commentReply) {
        return GdsClient.insertCommentReply(commentReply);
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
    public Integer editCommentReply(CommentReply commentReply) {
        return GdsClient.editCommentReply(commentReply);
    }
}
