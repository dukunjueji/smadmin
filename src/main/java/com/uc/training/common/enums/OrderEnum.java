package com.uc.training.common.enums;
/**
 * @Author: hhj
 * @Date: 2018/10/25 16:52
 * @Description: 订单状态枚举类：1-待付款 2-取消订单 3-待发货 4-待收货 5-已完成
 */
public enum OrderEnum {
  WAITPAY(1, "待付款"),
  CANCEL(2,"取消订单"),
  WAITSHIP(3,"待发货"),
  WAIRECEPIT(4,"待收货"),
  COMPLETED(5,"已完成");
  private final Integer key;
  private final String value;

  OrderEnum(Integer key, String value) {
    this.key = key;
    this.value = value;
  }

  public Integer getKey() {
    return key;
  }

  public String getValue() {
    return value;
  }

  public static OrderEnum getEnumByKey(Integer key){
    if(null == key){
      return null;
    }
    for(OrderEnum temp:OrderEnum.values()){
      if(temp.getKey().equals(key)){
        return temp;
      }
    }
    return null;
  }
  public static int getMaxKey(){
    int max = 0;
    for(OrderEnum temp:OrderEnum.values()){
      if(temp.getKey() > max ){
      max = temp.getKey();
      }
    }
    return max;
  }
}