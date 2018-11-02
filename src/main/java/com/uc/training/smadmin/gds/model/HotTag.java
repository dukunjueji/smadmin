package com.uc.training.smadmin.gds.model;

import com.uc.training.common.base.model.BaseModel;

/**
 * 热门标签
 *
 * @author zhongling(ling.zhong @ ucarinc.com)
 * @since 2018年10月15日 15:04
 */
public class HotTag extends BaseModel {

  private long id;
  /**
   * 热搜标签
   */
  private String tag;
  /**
   * 排序字段
   */
  private long sortNum;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }


  public long getSortNum() {
    return sortNum;
  }

  public void setSortNum(long sortNum) {
    this.sortNum = sortNum;
  }

}
