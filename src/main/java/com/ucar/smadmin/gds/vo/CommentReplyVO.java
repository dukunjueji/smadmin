package com.ucar.smadmin.gds.vo;

import com.ucar.smapi.common.base.model.BaseModel;

import java.io.Serializable;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/17
 */
public class CommentReplyVO extends BaseModel implements Serializable {

    private static final long serialVersionUID = -3550695496886085851L;
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
    /**
     * 会员昵称
     */
    private String memberNickname;
    /**
     * 会员头像
     */
    private String memberImageUrl;
    /**
     * 父级id
     */
    private Long parentId;
    /**
     * 类型1.商家回复，2他人回复
     */
    private Integer type;
    /**
     * 评论内容
     */
    private String content;

    @Override
    public String toString() {
        return "CommentReplyDao{" +
                "id=" + id +
                ", commentId=" + commentId +
                ", memberId=" + memberId +
                ", memberNickname='" + memberNickname + '\'' +
                ", memberImageUrl='" + memberImageUrl + '\'' +
                ", parentId=" + parentId +
                ", type=" + type +
                ", content='" + content + '\'' +
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

    public String getMemberNickname() {
        return memberNickname;
    }

    public void setMemberNickname(String memberNickname) {
        this.memberNickname = memberNickname;
    }

    public String getMemberImageUrl() {
        return memberImageUrl;
    }

    public void setMemberImageUrl(String memberImageUrl) {
        this.memberImageUrl = memberImageUrl;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
