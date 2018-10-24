package com.uc.training.common.bean;

import java.io.Serializable;
import java.util.Date;

public class AccessToken implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	private Long id;
	/**
	 * 是否验证通过
	 */
	private boolean verify;
	/**
	 * 是否过期
	 */
	private boolean expire;
	/**
	 * 签名时间
	 */
	private Date signDate;
	/**
	 * 过期时间
	 */
	private Date expireDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isVerify() {
		return verify;
	}

	public void setVerify(boolean verify) {
		this.verify = verify;
	}

	public boolean isExpire() {
		return expire;
	}

	public void setExpire(boolean expire) {
		this.expire = expire;
	}

	public Date getSignDate() {
		return signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
}
