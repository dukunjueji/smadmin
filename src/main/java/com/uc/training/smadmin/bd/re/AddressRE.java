package com.uc.training.smadmin.bd.re;
import java.io.Serializable;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 * @author qi.he@ucarinc.com
 * @date 2018-10-17 03:40
 * @description:
 */
public class AddressRE implements Serializable {

	private static final long serialVersionUID =  9197347895992467587L;

	/**自增主键ID**/
	private Long id;

	/**收货人**/
	private String receiver;

	/**手机号**/
	private String telephone;

	/**省**/
	private String province;

	/**市**/
	private String city;

	/**区**/
	private String district;

	/**区域编号**/
	private String areaCode;

	/**详细地址**/
	private String addrDetail;

	/**是否默认地址**/
	private Integer isDefault;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
