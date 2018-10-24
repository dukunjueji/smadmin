package com.uc.training.smadmin.gds.re;

/**
 * 商品库存
 *
 * @author zhongling(ling.zhong @ ucarinc.com)
 * @since 2018年10月19日 14:21
 */
public class GoodsStokeRE {
    private Long propertyId;
    /**
     * 需要扣除的库存
     */
    private Long stoke;
    /**
     * 是否删除
     */
    private int isDelete;
    /**
     * 是否下架，1表示下架，0表示未下架
     */
    private int status;

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

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
