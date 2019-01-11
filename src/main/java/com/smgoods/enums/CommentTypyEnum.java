package com.smgoods.enums;

/**
 * 评论状态枚举
 * @author DK
 */
public enum CommentTypyEnum {
    ALL_COMMENT(1,5,"全部"),
    GOOD_COMMENT(4,5,"好评"),
    MID_COMMENT(3,3,"中评"),
    BAD_COMMENT(1,2,"差评");
    /**
     * 区间下限
     */
    private Integer low;
    /**
     * 区间下限
     */
    private Integer high;

    /**
     * 描述
     */
    private  String desc;

    CommentTypyEnum(Integer low, Integer high, String desc) {
        this.low = low;
        this.high = high;
        this.desc = desc;
    }

    public Integer getLow() {
        return low;
    }

    public Integer getHigh() {
        return high;
    }

    public String getDesc() {
        return desc;
    }
}
