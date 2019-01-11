package com.ucar.smadmin.gds.service;

import com.ucar.smapi.gds.re.CommentPicRE;

import java.util.List;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/17
 */
public interface CommentPicService {
    /**
     * 根据评论id获取评论图片
     * @param commentId
     * @return
     */
    List<CommentPicRE> getCommentPicByCommentId(Long commentId);
}
