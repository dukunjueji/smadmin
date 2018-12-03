package com.uc.training.gds.service;

import com.uc.training.gds.re.CommentAvgRE;
import com.uc.training.gds.re.CommentCountRE;
import com.uc.training.gds.vo.CommentListVO;
import com.uc.training.gds.vo.CommentVO;
import com.uc.training.gds.re.CommentRE;


import java.util.List;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/17
 */
public interface CommentService {
    /**
     * 插入评论
     * hhj
     *
     * @param commentVO
     * @return
     */
    Integer addComment(CommentVO commentVO);

    /**
     * hhj
     * 根据订单商品表id获取评论List
     *
     * @param orderGoodsId
     * @return
     */
    List<CommentCountRE> getCommentCountByOrderGoodsId(Long orderGoodsId);

    /**
     * 获取评论列表
     * @param commentListVO
     * @return
     */
    List<CommentRE> getCommentList(CommentListVO commentListVO);

    /**
     * 根据评分上下限获取计数
     * @param commentListVO
     * @return
     */
    CommentAvgRE getCountBySroce(CommentListVO commentListVO);

    /**
     * 获取总评价数
     * @param commentListVO
     * @return
     */
    CommentAvgRE getCommentCountAndAvg(CommentListVO commentListVO);

    /**
     * 根据ID修改评论
     * @param commentVO
     * @return
     */
    Integer editCommentById(CommentVO commentVO);
}
