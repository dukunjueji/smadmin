package com.uc.training.common.enums;

/**
 * 商品状态枚举类
 * @author DK
 */
public enum GoodsStatusEnum {
    //未删除
    GOODS_IS_DELETE("未删除", 0),
    //删除
    GOODS_DELETE("删除", 1),
    //上架
    GOODS_SHELVES("上架", 1),
    //下架
    GOODS_IS_SHELVES("下架", 0);
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

    public Integer getType() {
        return type;
    }

}
