package com.uc.training.smadmin.gds.model;

import com.uc.training.common.base.model.BaseModel;

/**
 * 商品
 *
 * @author zhongling(ling.zhong @ ucarinc.com)
 * @since 2018年10月15日 15:04
 */
public class Goods extends BaseModel {

  private long id;
  /**
   * 商品编号
   */
  private String code;
  /**
   * 商品名称
   */
  private String name;
  /**
   * 类型id
   */
  private long categoryId;
  /**
   * 出售价格
   */
  private Integer sales;
  /**
   * 详情
   */
  private String detail;
  /**
   * 商品状态
   */
  private long status;
  private long isDelete;



  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(long categoryId) {
    this.categoryId = categoryId;
  }


  public Integer getSales() {
    return sales;
  }

  public void setSales(Integer sales) {
    this.sales = sales;
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }


  public long getIsDelete() {
    return isDelete;
  }

  public void setIsDelete(long isDelete) {
    this.isDelete = isDelete;
  }


}
