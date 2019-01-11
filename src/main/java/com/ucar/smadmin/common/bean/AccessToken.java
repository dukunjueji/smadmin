package com.ucar.smadmin.common.bean;

import java.io.Serializable;
import java.util.Date;
/**
 * token封装类
 *
 * @author 吴佰川（baichuan.wu@ucarinc.com）创建
 * @version 1.0
 * @date 2018/10/26 8:36
 */
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
		return signDate == null ? null : (Date) signDate.clone();
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate == null ? null : (Date) signDate.clone();
	}

	public Date getExpireDate() {
		return expireDate == null ? null : (Date) expireDate.clone();
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate == null ? null : (Date) expireDate.clone();
	}
}
