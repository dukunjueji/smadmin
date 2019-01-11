package com.ucar.smadmin.gds.service.impl;

import com.ucar.smapi.gds.re.CommentRE;
import com.ucar.smadmin.gds.service.CommentReplyService;
import com.ucar.smadmin.gds.service.CommentService;
import com.ucar.smadmin.gds.vo.CommentListVO;
import com.ucar.smadmin.gds.vo.CommentReplyVO;
import com.ucar.smadmin.remote.client.GdsClient;
import com.ucar.smapi.gds.re.CommentDetailRE;
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
    GdsClient gdsClient;

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
        return gdsClient.getAdminReplyContent(commentId);
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
        commentDetailRE = gdsClient.getCommentDetailByCommentId(commentReplyVO);
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
        return gdsClient.insertCommentReply(commentReplyVO);
    }

    /**
     * 删除商品详情回复
     *
     * @param commentReplyVO
     * @return
     */
    @Override
    public Integer deleteCommentReplyById(CommentReplyVO commentReplyVO) {
        return gdsClient.deleteCommentReplyById(commentReplyVO);
    }

    @Override
    public Integer editCommentReply(CommentReplyVO commentReplyVO) {
        return gdsClient.editCommentReply(commentReplyVO);
    }
}
