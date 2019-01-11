package com.ucar.smadmin.enums;

public enum OrderGoodsCommentEnum {
//1：未评论。2,：已评论。3,：已追加

  // 未评论
  NO_COMMENT(1,"未评论"),
  // 已评论
  HAVE_COMMENT(2,"已评论"),
  //已追加
  AFTER_HAVE_COMMENT(3,"已追加");

  /**
   * 状态码
   */
  private final Integer key;

  /**
   * 状态描述
   */
  private final String value;

  OrderGoodsCommentEnum(Integer key, String value) {
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
