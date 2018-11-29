package com.uc.training.smadmin.gds.model;

import com.uc.training.common.base.model.BaseModel;

import java.io.Serializable;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/17
 */
public class CommentPic extends BaseModel implements Serializable {

    private static final long serialVersionUID = -1824545116574689760L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 评论id
     */
    private Long commentId;
    /**
     * 图片地址
     */
    private String imageUrl;

    @Override
    public String toString() {
        return "CommentPic{" +
                "id=" + id +
                ", commentId=" + commentId +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
