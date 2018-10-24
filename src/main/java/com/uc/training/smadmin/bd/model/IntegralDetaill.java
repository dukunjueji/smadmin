package com.uc.training.smadmin.bd.model;
import com.uc.training.common.base.model.BaseModel;

import java.io.Serializable;
import java.util.Date;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/15
 * 说明：积分值明细类
 */
public class IntegralDetaill extends BaseModel implements Serializable {
  private static final long serialVersionUID = -7892302270617195032L;
  /**
   * 主键id
   */
  private Long id;

  /**
   *会员id
   */
  private Long memberId;

  /**
   *类型  1:签到、2:评价、3:购买商品、4:使用积分消费类型  1:签到、2:评价、3:购买商品、4:使用积分消费
   */
  private Long type;

  /**
   *积分值
   */
  private Long integral;

  /**
   * 日期
   */
  private Date date;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getMemberId() {
    return memberId;
  }

  public void setMemberId(Long memberId) {
    this.memberId = memberId;
  }

  public Long getType() {
    return type;
  }

  public void setType(Long type) {
    this.type = type;
  }

  public Long getIntegral() {
    return integral;
  }

  public void setIntegral(Long integral) {
    this.integral = integral;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }


}
