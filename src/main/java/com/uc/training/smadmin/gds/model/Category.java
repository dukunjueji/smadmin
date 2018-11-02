package com.uc.training.smadmin.gds.model;

import com.uc.training.common.base.model.BaseModel;

/**
 * 商品类别
 *
 * @author zhongling(ling.zhong @ ucarinc.com)
 * @since 2018年10月15日 15:04
 */
public class Category extends BaseModel {

  private Long id;
  /**
   * 类别名称
   */
  private String name;
  /**
   * 排序号
   */
  private Integer sortNum;
  /**
   * 分类图标
   */
  private String imageUrl;
  /**
   * 父级id
   */
  private Long parentId;
  /**
   * 逻辑删除
   */
  private Long isDelete;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getSortNum() {
    return sortNum;
  }

  public void setSortNum(Integer sortNum) {
    this.sortNum = sortNum;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  public Long getIsDelete() {
    return isDelete;
  }

  public void setIsDelete(Long isDelete) {
    this.isDelete = isDelete;
  }
}
