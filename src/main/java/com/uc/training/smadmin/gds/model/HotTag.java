package com.uc.training.smadmin.gds.model;

import com.uc.training.common.base.model.BaseModel;

/**
 *
 * 功能描述: 热门标签
 *
 * @param:
 * @return: 
 * @auther: ling
 * @date: 2018/10/30 10:05
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
