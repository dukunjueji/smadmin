package com.uc.training.smadmin.sms.model;

import com.uc.training.common.base.model.BaseModel;

import java.io.Serializable;
/**
 * @author 余旭东
 * @date Tue Oct 16 14:07:25 CST 2018
 * @description: 
 */
public class Sms extends BaseModel implements Serializable {


	private static final long serialVersionUID =  8007084508146514316L;


	/**
	 * 自增主键ID
	 */
	private Long id;

	/**
	 * 目标用户手机号
	 */
	private String telephone;

	/**
	 * 短信内容
	 */
	private String content;

	/**
	 * 短信类型：1注册，2，登录，3充值，4订单信息，5会员等级
	 */
	private Integer type;

	/**
	 * 发送状态：0失败，1成功
	 */
	private Integer status;


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
