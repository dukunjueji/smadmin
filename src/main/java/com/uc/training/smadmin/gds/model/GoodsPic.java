package com.uc.training.smadmin.gds.model;

import com.uc.training.common.base.model.BaseModel;

import java.io.Serializable;

/**
 * 商品图片
 *
 * @author zhongling(ling.zhong @ ucarinc.com)
 * @since 2018年10月15日 15:04
 */
public class GoodsPic extends BaseModel implements Serializable {

    private static final long serialVersionUID = 2817643930769922112L;
    private long id;
    /**
     * 商品属性表的id
     */
    private long propertyId;
    /**
     * 图片名称
     */
    private String picName;
    /**
     * 图片url
     */
    private String picUrl;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(long propertyId) {
        this.propertyId = propertyId;
    }


    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }


    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }


}
