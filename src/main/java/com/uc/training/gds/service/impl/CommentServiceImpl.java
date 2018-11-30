package com.uc.training.gds.service.impl;

import com.uc.training.common.enums.CommentEnum;
import com.uc.training.common.enums.CommentTypyEnum;
import com.uc.training.common.enums.OrderGoodsCommentEnum;
import com.uc.training.smadmin.gds.dao.CommentDao;
import com.uc.training.smadmin.gds.model.Comment;
import com.uc.training.smadmin.gds.re.CommentRE;
import com.uc.training.smadmin.gds.service.CommentPicService;
import com.uc.training.smadmin.gds.service.CommentReplyService;
import com.uc.training.smadmin.gds.service.CommentService;
import com.uc.training.smadmin.gds.vo.CommentAvgVO;
import com.uc.training.smadmin.gds.vo.CommentListVO;
import com.uc.training.smadmin.gds.vo.CommentPicVO;
import com.uc.training.smadmin.gds.vo.CommentVO;
import com.uc.training.smadmin.ord.model.Order;
import com.uc.training.smadmin.ord.service.OrderGoodsService;
import com.uc.training.smadmin.ord.vo.OrdOrderGoodsVo;
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
    CommentDao commentDao;

    @Autowired
    OrderGoodsService orderGoodsService;

    @Autowired
    CommentPicService commentPicService;

    @Autowired
    CommentReplyService commentReplyService;

    /**
     * hhj
     * @param commentVO
     * @return
     */
    @Override
    public Integer addComment(CommentVO commentVO) {
        OrdOrderGoodsVo ordOrderGoodsVo = new OrdOrderGoodsVo();
        ordOrderGoodsVo.setId(commentVO.getOrderGoodsId());
        //插评论
        if (commentDao.addComment(commentVO) == null) {
            return 0;
        }
        //插入图片
        if (commentVO.getUrl().length >= 1) {
            CommentPicVO commentPicVO = new CommentPicVO();
            commentPicVO.setCommentId(commentVO.getId());
            commentPicVO.setPicUrl(commentVO.getUrl());
            commentDao.addCommentPic(commentPicVO);
        }
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
    public List<Comment> getCommentCountByOrderGoodsId(Long orderGoodsId) {
        return commentDao.getCommentCountByOrdergoodsId(orderGoodsId);
    }

    /**
     * 获取评论列表
     *
     * @param commentListVO
     * @returns
     */
    @Override
    public List<CommentRE> getCommentList(CommentListVO commentListVO) {

        List<CommentRE> commentList = commentDao.getCommentList(commentListVO);

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
            Order order = new Order();
            for (CommentRE commentRE : commentList) {
                order = orderGoodsService.getOrderByOrdGoodsId(commentRE.getOrderGoodsId());
                if (order != null) {
                    commentRE.setOrderNum(order.getOrderNum());
                }
            }
        }
        return commentList;
    }

    @Override
    public CommentAvgVO getCountBySroce(CommentListVO commentListVO) {
        CommentListVO commentListVO1 = new CommentListVO();
        commentListVO1.setGoodsId(commentListVO.getGoodsId());
        commentListVO1.setLowSroce(CommentTypyEnum.ALL_COMMENT.getLow());
        commentListVO1.setHighSroce(CommentTypyEnum.ALL_COMMENT.getHigh());
        CommentAvgVO commentAvgVO = this.getCommentCountAndAvg(commentListVO1);
        if (commentAvgVO == null) {
            return null;
        }
        CommentAvgVO commentAvgVO1 = new CommentAvgVO();
        //差评统计
        commentListVO.setLowSroce(CommentTypyEnum.BAD_COMMENT.getLow());
        commentListVO.setHighSroce(CommentTypyEnum.BAD_COMMENT.getHigh());
        commentAvgVO.setBadCount(commentDao.getCountBySroce(commentListVO));

        //中评统计
        commentListVO.setLowSroce(CommentTypyEnum.MID_COMMENT.getLow());
        commentListVO.setHighSroce(CommentTypyEnum.MID_COMMENT.getHigh());
        commentAvgVO.setMidCount(commentDao.getCountBySroce(commentListVO));

        //好评统计
        commentListVO.setLowSroce(CommentTypyEnum.GOOD_COMMENT.getLow());
        commentListVO.setHighSroce(CommentTypyEnum.GOOD_COMMENT.getHigh());
        commentAvgVO.setGoodCount(commentDao.getCountBySroce(commentListVO));
        return commentAvgVO;
    }

    @Override
    public CommentAvgVO getCommentCountAndAvg(CommentListVO commentListVO) {

        return commentDao.getCommentCountAndAvg(commentListVO);
    }

    @Override
    public Integer editCommentById(CommentVO commentVO) {
        return commentDao.editCommentById(commentVO);
    }
}
