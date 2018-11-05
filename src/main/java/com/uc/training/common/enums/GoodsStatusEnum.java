package com.uc.training.common.enums;

/**
 * 商品状态枚举类
 * @author DK
 */
public enum GoodsStatusEnum {
    //未删除
    GOODSISDELETE("未删除", 1),
    //删除
    GOODSDELETE("删除", 0),
    //上架
    GOODSSHELVES ("上架", 1),
    //下架
    GOODSISSHELVES ("下架", 0);
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
