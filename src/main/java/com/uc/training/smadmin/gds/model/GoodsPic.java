package com.uc.training.smadmin.gds.model;

import com.uc.training.common.base.model.BaseModel;

/**
 *
 * 功能描述: 
 *
 * @param: 
 * @return: 
 * @auther: ling
 * @date: 2018/10/30 10:04
 */
public class GoodsPic extends BaseModel {

  private long id;
  /**
   * 商品属性表的id
   */
  private long propertyId;
  /**
   * 图片名称
   */
  private String picName;
  /**
   * 图片url
   */
  private String picUrl;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getPropertyId() {
    return propertyId;
  }

  public void setPropertyId(long propertyId) {
    this.propertyId = propertyId;
  }


  public String getPicName() {
    return picName;
  }

  public void setPicName(String picName) {
    this.picName = picName;
  }


  public String getPicUrl() {
    return picUrl;
  }

  public void setPicUrl(String picUrl) {
    this.picUrl = picUrl;
  }


}
