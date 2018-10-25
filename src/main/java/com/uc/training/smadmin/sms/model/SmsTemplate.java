package com.uc.training.smadmin.sms.model;

import com.uc.training.common.base.model.BaseModel;
import com.uc.training.common.enums.SmsTemplateTypeEnum;

/**
 * @author 余旭东
 * @date Tue Oct 16 14:29:57 CST 2018
 * @description:
 */
public class SmsTemplate extends BaseModel {


	private static final long serialVersionUID =  6439980034838734393L;


	/**
	 * 自增主键ID
	 */
	private Long id;

	/**
	 * 模板类型：1注册，2，登录，3充值，4订单信息，5会员等级
	 */
	private Integer type;

	/**
	 * 模板编号
	 */
	private String code;

	/**
	 * 模板标题
	 */
	private String title;

	/**
	 * 模板内容
	 */
	private String content;

	/**
	 * 类型描述
	 */
	private String typeDes;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTypeDes() {
		return SmsTemplateTypeEnum.getMap().get(type);
	}

}
