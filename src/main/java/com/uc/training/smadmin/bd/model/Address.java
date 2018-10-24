package com.uc.training.smadmin.bd.model;
import java.io.Serializable;
import java.util.Date;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 * @author qi.he@ucarinc.com
 * @date 2018-10-17 03:40
 * @description:
 */
public class Address implements Serializable {

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

	/**创建时间**/
	private Date createTime;

	/**创建时间**/
	private Long createEmp;

	/**修改时间**/
	private Date modifyTime;

	/**修改人**/
	private Long modifyEmp;

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

	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}

	public Date getCreateTime(){
		return this.createTime;
	}

	public void setCreateEmp(Long createEmp){
		this.createEmp = createEmp;
	}

	public Long getCreateEmp(){
		return this.createEmp;
	}

	public void setModifyTime(Date modifyTime){
		this.modifyTime = modifyTime;
	}

	public Date getModifyTime(){
		return this.modifyTime;
	}

	public void setModifyEmp(Long modifyEmp){
		this.modifyEmp = modifyEmp;
	}

	public Long getModifyEmp(){
		return this.modifyEmp;
	}

}
