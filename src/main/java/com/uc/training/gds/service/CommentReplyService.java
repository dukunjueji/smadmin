package com.uc.training.gds.service;

import com.uc.training.smadmin.gds.model.CommentReply;
import com.uc.training.smadmin.gds.re.CommentDetailRE;
import com.uc.training.smadmin.gds.vo.CommentReplyVO;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/17
 */
public interface CommentReplyService {
    /**
     * 获取评论的后台回复
     * @param commentId
     * @return
     */
    String getAdminReplyContent(Long commentId);

    /**
     * 根据评论id获取评论详情
     * @param commentReplyVO
     * @return
     */
    CommentDetailRE getCommentDetailByCommentId(CommentReplyVO commentReplyVO);

    /**
     * 新增商品详情回复
     * @param commentReply
     * @return
     */
    Long insertCommentReply(CommentReply commentReply);

    /**
     * 根据评论ID修改商家评论信息
     * @param commentReply
     * @return
     */
    Integer editCommentReply(CommentReply commentReply);

    /**
     * 删除商品详情回复
     * @param commentReplyVO
     * @return
     */
    Integer deleteCommentReplyById(CommentReplyVO commentReplyVO);
}
