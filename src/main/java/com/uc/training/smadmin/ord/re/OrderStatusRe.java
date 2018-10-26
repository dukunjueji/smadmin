package com.uc.training.smadmin.ord.re;

import java.io.Serializable;

/**
 * description: TODO
 * @author 黄宏俊
 * @version 1.0
 * @date 2018/10/25  17:58
 */
public class OrderStatusRe implements Serializable {
  private static final long serialVersionUID = -6912839796213052826L;
  /**order枚举key**/
  private Integer value;
  /**order枚举value**/
  private String label;

  public Integer getValue() {
    return value;
  }

  public void setValue(Integer value) {
    this.value = value;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }
}
