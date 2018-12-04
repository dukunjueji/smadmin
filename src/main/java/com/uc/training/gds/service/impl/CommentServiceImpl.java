package com.uc.training.gds.service.impl;

import com.uc.training.common.enums.CommentEnum;
import com.uc.training.common.enums.OrderGoodsCommentEnum;
import com.uc.training.gds.re.CommentAvgRE;
import com.uc.training.gds.re.CommentCountRE;
import com.uc.training.gds.re.CommentRE;
import com.uc.training.gds.service.CommentPicService;
import com.uc.training.gds.service.CommentReplyService;
import com.uc.training.gds.service.CommentService;
import com.uc.training.gds.vo.CommentListVO;
import com.uc.training.gds.vo.CommentVO;
import com.uc.training.ord.re.OrderRE;
import com.uc.training.ord.service.OrderGoodsService;
import com.uc.training.ord.vo.OrdOrderGoodsVO;
import com.uc.training.remote.client.GdsClient;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/17
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentPicService commentPicService;

    @Autowired
    CommentReplyService commentReplyService;

    @Autowired
    OrderGoodsService orderGoodsService;
    /**
     * hhj
     * @param commentVO
     * @return
     */
    @Override
    public Integer addComment(CommentVO commentVO) {
        OrdOrderGoodsVO ordOrderGoodsVo = new OrdOrderGoodsVO();
        ordOrderGoodsVo.setId(commentVO.getOrderGoodsId());
        //插评论
        GdsClient.addComment(commentVO);
        // 修改订单商品表的状态
        if (commentVO.getParentId() == null) {
            ordOrderGoodsVo.setCommentStatus(OrderGoodsCommentEnum.HAVE_COMMENT.getKey());
            return orderGoodsService.upOrdGoodsCommentStatus(ordOrderGoodsVo);
        } else {
            ordOrderGoodsVo.setCommentStatus(OrderGoodsCommentEnum.AFTER_HAVE_COMMENT.getKey());
            return orderGoodsService.upOrdGoodsCommentStatus(ordOrderGoodsVo);
        }
    }

    /**
     * hhj
     * 根据订单商品表id获取评论List
     *
     * @param orderGoodsId
     * @return
     */
    @Override
    public List<CommentCountRE> getCommentCountByOrderGoodsId(Long orderGoodsId) {
        return GdsClient.getCommentCountByOrderGoodsId(orderGoodsId);
    }

    /**
     * 获取评论列表
     *
     * @param commentListVO
     * @returns
     */
    @Override
    public List<CommentRE> getCommentList(CommentListVO commentListVO) {

        List<CommentRE> commentList = GdsClient.getCommentList(commentListVO);
        //判空
        if (CollectionUtils.isEmpty(commentList)) {
            return null;
        }

        for (CommentRE commentRE : commentList) {
            // 获取评论的图片
            commentRE.setCommentPic(commentPicService.getCommentPicByCommentId(commentRE.getId()));
            //获取后台回复(后台请求不需要回复)
            if (commentRE.getStatus().equals(CommentEnum.COMMENT_RE.getKey())) {
                commentRE.setAdminContent(commentReplyService.getAdminReplyContent(commentRE.getId()));
            }
            //获取追加评论图片
            if (commentRE.getAppendId() != null) {
                //获取后台追加回复(后台请求不需要回复)
                if (commentRE.getAppendStatus().equals(CommentEnum.COMMENT_RE.getKey())) {
                    commentRE.setAppendAdminContent(commentReplyService.getAdminReplyContent(commentRE.getAppendId()));
                }
                commentRE.setAppendPic(commentPicService.getCommentPicByCommentId(commentRE.getAppendId()));
            }
        }
        if (commentListVO.getGoodsId() == null && commentListVO.getId() == null) {
            for (CommentRE commentRE : commentList) {
                OrderRE order = orderGoodsService.getOrderByOrdGoodsId(commentRE.getOrderGoodsId());
                if (order != null) {
                    commentRE.setOrderNum(order.getOrderNum());
                }
            }
        }
        return commentList;
    }

    @Override
    public CommentAvgRE getCountBySroce(CommentListVO commentListVO) {
       return GdsClient.getCountBySroce(commentListVO);
    }

    @Override
    public CommentAvgRE getCommentCountAndAvg(CommentListVO commentListVO) {

        return GdsClient.getCommentCountAndAvg(commentListVO);
    }

    @Override
    public Integer editCommentById(CommentVO commentVO) {
        return GdsClient.editCommentById(commentVO);
    }
}
