package com.uc.training.common.enums;

/**
 * 商品状态枚举类
 * @author DK
 */
public enum GoodsStatusEnum {
    //商品状态：未删除
    GoodsIsDelete("未删除", 1),
    //商品状态：删除
    GoodsDelete("删除", 0),
    //商品状态：上架
    GoodsShelves ("上架", 1),
    //商品状态：下架
    GoodsIsShelves ("下架", 0);

    /**
     * 描述
     */
    private String desc;

    /**
     * 商品状态类型
     */
    private Integer type;
    
    GoodsStatusEnum(String desc, Integer type) {
        this.desc = desc;
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
