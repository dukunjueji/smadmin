package com.uc.training.smadmin.gds.vo;

import java.io.Serializable;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/19
 */
public class CommentReplyVO implements Serializable {

    private static final long serialVersionUID = -617066452812184837L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 评论id
     */
    private Long commentId;
    /**
     * 会员id
     */
    private Long memberId;

    @Override
    public String toString() {
        return "CommentReplyVO{" +
                "id=" + id +
                ", commentId=" + commentId +
                ", memberId=" + memberId +
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

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
