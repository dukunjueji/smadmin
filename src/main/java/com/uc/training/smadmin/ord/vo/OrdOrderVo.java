package com.uc.training.smadmin.ord.vo;

import com.ycc.base.common.BaseDomain;

import java.io.Serializable;

/**
 *用户订单信息VO
 * @author DK
 * @date 2018/10/15
 */

public class OrdOrderVo extends BaseDomain implements Serializable{
    public String getReceiptName() {
        return receiptName;
    }

    public void setReceiptName(String receiptName) {
        this.receiptName = receiptName;
    }

    public String getReceiptTel() {
        return receiptTel;
    }

    public void setReceiptTel(String receiptTel) {
        this.receiptTel = receiptTel;
    }

    public String getReceiptAddress() {
        return receiptAddress;
    }

    public void setReceiptAddress(String receiptAddress) {
        this.receiptAddress = receiptAddress;
    }

    /**
     * 收货人姓名
     */
    private String receiptName;

    /**
     * 收货人电话
     */
    private String receiptTel;

    /**
     * 收货人地址
     */
    private String receiptAddress;

}
