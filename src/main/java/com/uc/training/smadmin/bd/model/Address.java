package com.uc.training.smadmin.bd.model;
import com.uc.training.common.base.model.BaseModel;

import java.io.Serializable;

public class Address extends BaseModel implements Serializable {

	private static final long serialVersionUID =  9197347895992467587L;

	/**自增主键ID**/
	private Long id;

	/**会员ID**/
	private Long memberId;

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

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public void setReceiver(String receiver){
		this.receiver = receiver;
	}

	public String getReceiver(){
		return this.receiver;
	}

	public void setTelephone(String telephone){
		this.telephone = telephone;
	}

	public String getTelephone(){
		return this.telephone;
	}

	public void setProvince(String province){
		this.province = province;
	}

	public String getProvince(){
		return this.province;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return this.city;
	}

	public void setDistrict(String district){
		this.district = district;
	}

	public String getDistrict(){
		return this.district;
	}

	public void setAddrDetail(String addrDetail){
		this.addrDetail = addrDetail;
	}

	public String getAddrDetail(){
		return this.addrDetail;
	}

	public void setIsDefault(Integer isDefault){
		this.isDefault = isDefault;
	}

	public Integer getIsDefault(){
		return this.isDefault;
	}

}
