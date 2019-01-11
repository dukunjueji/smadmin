package com.smgoods.enums;

/**
 * 编号枚举类型
 *
 * @author zhongling(ling.zhong @ ucarinc.com)
 * @since 2018年10月15日 15:09
 */
public enum  UUIDTypeEnum {
    //商品编号信息
    GOODSID("goodsid","GDS","商品编号"),
    //订单编号信息
    ORDERID("orderid","ORD","订单编号");
    /**
     * 编号类型
     */
    private String type;
    /**
     *编号类型值
     */
    private String value;
    /**
     *描述
     */
    private String desc;
    /**
     *流水线的队列长度
     */
    public static final int QUEUESIZE=10000;
    /**
     *当到达流水线的队列长度为100时，开始生产一个ONEPRODUCTQUEUESIZE个数据
     */
    public static final int MINQUEUESIZE=100;
    /**
     *当到达流水线的队列长度为100时，开始生产de数据量
     */
    public static final int ONEPRODUCTQUEUESIZE=10;

    UUIDTypeEnum(String type, String value, String desc) {
        this.type = type;
        this.value = value;
        this.desc = desc;
    }
    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
