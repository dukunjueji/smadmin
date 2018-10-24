package com.uc.training.smadmin.gds.vo;

/**
 * 商品库存
 *
 * @author zhongling(ling.zhong @ ucarinc.com)
 * @since 2018年10月19日 14:21
 */
public class GoodsStokeVO {
    private Long propertyId;
    /**
     * 需要扣除的库存
     */
    private Long stoke;

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public Long getStoke() {
        return stoke;
    }

    public void setStoke(Long stoke) {
        this.stoke = stoke;
    }
}
