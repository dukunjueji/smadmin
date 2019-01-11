package com.ucar.smadmin.base.bd.vo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author qi.he@ucarinc.com
 * @date 2018-10-17 03:40
 * @description:
 */
public class AddressVO implements Serializable {

    private static final long serialVersionUID = -8075078973485898339L;

    private Long id;

    private Long memberId;

    /**
     * 收货人
     **/
    @NotBlank(message = "收货人不能为空")
    private String receiver;

    /**
     * 手机号
     **/
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3456789]\\d{9}$", message = "手机号格式不正确")
    private String telephone;

    /**
     * 省
     **/
    @NotBlank(message = "请选择区域")
    private String province;

    /**
     * 市
     **/
    @NotBlank(message = "请选择区域")
    private String city;

    /**
     * 区
     **/
    @NotBlank(message = "请选择区域")
    private String district;

    /**
     * 区域编号
     **/
    @NotBlank(message = "请选择区域")
    private String areaCode;

    /**
     * 详细地址
     **/
    @NotBlank(message = "详细地址不能为空")
    private String addrDetail;

    /**
     * 是否默认地址
     **/
    @NotNull(message = "默认地址不能为空")
    private Integer isDefault;

    /**
     * 创建人
     **/
    private Long createEmp;

    /**
     * 修改人
     **/
    private Long modifyEmp;

    @Override
    public String toString() {
        return "AddressVO{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", receiver='" + receiver + '\'' +
                ", telephone='" + telephone + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", addrDetail='" + addrDetail + '\'' +
                ", isDefault=" + isDefault +
                '}';
    }

    public Long getModifyEmp() {
        return modifyEmp;
    }

    public void setModifyEmp(Long modifyEmp) {
        this.modifyEmp = modifyEmp;
    }

    public Long getCreateEmp() {
        return createEmp;
    }

    public void setCreateEmp(Long createEmp) {
        this.createEmp = createEmp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAddrDetail() {
        return addrDetail;
    }

    public void setAddrDetail(String addrDetail) {
        this.addrDetail = addrDetail;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

}
