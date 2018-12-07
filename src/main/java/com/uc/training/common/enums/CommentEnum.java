package com.uc.training.common.enums;

/**
 * 评论枚举
 * @author DK
 */
public enum CommentEnum {
  // 商家未回复
  COMMENT_NO(0,"商家未回复"),

  // 商家已回复
  COMMENT_RE(1,"商家已回复");

  /**
   * 数据库订单状态码
   */
  private final Integer key;

  /**
   * 状态描述
   */
  private final String value;

  CommentEnum(Integer key, String value) {
    this.key = key;
    this.value = value;
  }

  public Integer getKey() {
    return key;
  }

  public String getValue() {
    return value;
  }
}
