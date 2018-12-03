package com.uc.training.gds.vo;

import java.io.Serializable;

/**
 * description: TODO
 *
 * @author 黄宏俊
 * @version 1.0
 * @date 2018/11/17  10:00
 */
public class CommentPicVO implements Serializable {

  private static final long serialVersionUID =  4289686880752388752L;
  /**
   * 评论表id
   */
  private Long commentId;

  /**
   * 评论图片地址
   */
private String[] picUrl;

  public Long getCommentId() {
    return commentId;
  }

  public void setCommentId(Long commentId) {
    this.commentId = commentId;
  }

  public String[] getPicUrl() {
    return picUrl;
  }

  public void setPicUrl(String[] picUrl) {
    this.picUrl = picUrl;
  }
}
