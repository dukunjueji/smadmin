package com.uc.training.smadmin.ord.vo;

import java.io.Serializable;
import java.util.List;

/**
 * description: TODO
 *
 * @author hhj
 * @version 1.0
 * @date 2018/11/2  12:28
 */
public class OrdGoodsVO implements Serializable {

  /**
   * 商品id
   */
  private Long memberId;

  /**
   * 购物车商品表ID list
   */
  private List<Long> list;

  public Long getMemberId() {
    return memberId;
  }

  public void setMemberId(Long memberId) {
    this.memberId = memberId;
  }

  public List<Long> getList() {
    return list;
  }

  public void setList(List<Long> list) {
    this.list = list;
  }

  @Override
  public String toString() {
    return "OrdGoodsVO{" +
            "memberId=" + memberId +
            ", list=" + list +
            '}';
  }
}
