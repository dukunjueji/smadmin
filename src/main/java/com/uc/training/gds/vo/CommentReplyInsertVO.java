package com.uc.training.gds.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/19
 */
public class CommentReplyInsertVO implements Serializable {

    private static final long serialVersionUID = -4520148653431841238L;
    /**
     * 评论id
     */
    @NotNull(message = "请重新选择商品评论！")
    private Long commentId;

    private Long parentId;

    @NotBlank(message = "评论内容不能为空！")
    @Length(max = 255,min = 2,message = "内容必须在2-255个字符之间")
    private String content;

    @Override
    public String toString() {
        return "CommentReplyInsertVO{" +
                "commentId=" + commentId +
                ", parentId=" + parentId +
                ", content='" + content + '\'' +
                '}';
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
