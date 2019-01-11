package com.ucar.smadmin.gds.service.impl;

import com.ucar.smadmin.gds.service.CommentPicService;
import com.ucar.smadmin.remote.client.GdsClient;
import com.ucar.smapi.gds.re.CommentPicRE;
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
public class CommentPicServiceImpl implements CommentPicService {
    @Autowired
    GdsClient gdsClient;

    /**
     * 根据评论id获取评论图片
     *
     * @param commentId
     * @return
     */
    @Override
    public List<CommentPicRE> getCommentPicByCommentId(Long commentId) {
        return gdsClient.getCommentPicByCommentId(commentId);
    }
}
