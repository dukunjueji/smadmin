package com.uc.training.common.enums;

/**
 * 库存状态码
 *
 * @author zhongling(ling.zhong @ ucarinc.com)
 * @since 2018年11月02日 17:36
 */
public enum StokeStatusEnum {
    /**
     * 查询数据库存信息失败，商品属性id有问题
     */
    BLANK_STATUS(-1,"查询数据库存信息失败，商品属性id有问题"),
    /**
     * 商品已下架
     */
    SHELVED_STATUS(0,"商品已下架"),
    /**
     * 更新库存成功
     */
    SUCCESS_STATUS(1,"更新库存成功"),
    /**
     * 商品已删除
     */
    DELETE_STATUS(2,"商品已删除"),
    /**
     * 商品库存不足
     */
    NOT_ENOUGH_STATUS(3,"商品库存不足");

    StokeStatusEnum(Integer status, String describe) {
        this.status = status;
        this.describe = describe;
    }

    /**
     * 状态码
     */
    private Integer status;
    /**
     * 描述
     */
    private String describe;

    public Integer getStatus() {
        return status;
    }


    public String getDescribe() {
        return describe;
    }

}
