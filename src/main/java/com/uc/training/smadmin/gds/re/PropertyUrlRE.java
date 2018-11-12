package com.uc.training.smadmin.gds.re;

/**
 * 请填写类注释
 *
 * @author zhongling(ling.zhong @ ucarinc.com)
 * @since 2018年10月26日 15:11
 */
public class PropertyUrlRE {
    /**
     * 商品属性id
     */
    private long propertyId;
    /**
     * 商品图片
     */
    private String picUrl;

    public long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(long propertyId) {
        this.propertyId = propertyId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Override
    public String toString() {
        return "PropertyUrlRE{" +
                "propertyId=" + propertyId +
                ", picUrl='" + picUrl + '\'' +
                '}';
    }
}
