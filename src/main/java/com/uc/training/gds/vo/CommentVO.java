package com.uc.training.gds.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hhj
 * @date Fri Nov 16 15:49:33 CST 2018
 * @description: 评论model
 */
public class CommentVO implements Serializable {


	private static final long serialVersionUID =  4289686880752788753L;


	/**
	 *自增主键id
	 */
	private Long id;

	/**
	 * 商品id
	 */
	private Long goodsId;

	/**
	 * 用户id
	 */
	private Long memberId;

	/**
	 * 用户昵称
	 */
	private String memberNickname;

	/**
	 * 用户头像地址N
	 */
	private String memberImageurl;

	/**
	 *商品属性ID
	 */
	private Long goodsPropertyId;

	/**
	 *商品属性名称
	 */
	private String goodsPropertyName;

	/**
	 *商品评分
	 */
	private Double goodsSroce;

	/**
	 *回复状态（商家回复），0.未回复，1.已回复
	 */
	private Integer status;

	/**
	 *父级Id
	 */
	private Long parentId;

	/**
	 * 订单详情表id
	 */
	private Long orderGoodsId;

	/**
	 *评论内容
	 */
	private String content;

	/**
	 * 评论图片
	 */
	private String[] url;

	/**
	 *创建人
	 */
	private Long createEmp;

	/**
	 *创建时间
	 */
	private Date createTime;

	/**
	 *修改人
	 */
	private Long modifyEmp;

	/**
	 *修改时间
	 */
	private Date modifyTime;

	public String[] getUrl() {
		return url;
	}

	public void setUrl(String[] url) {
		this.url = url;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public String getMemberImageurl() {
		return memberImageurl;
	}

	public void setMemberImageurl(String memberImageurl) {
		this.memberImageurl = memberImageurl;
	}

	public Long getGoodsPropertyId() {
		return goodsPropertyId;
	}

	public void setGoodsPropertyId(Long goodsPropertyId) {
		this.goodsPropertyId = goodsPropertyId;
	}

	public String getGoodsPropertyName() {
		return goodsPropertyName;
	}

	public void setGoodsPropertyName(String goodsPropertyName) {
		this.goodsPropertyName = goodsPropertyName;
	}

	public Double getGoodsSroce() {
		return goodsSroce;
	}

	public void setGoodsSroce(Double goodsSroce) {
		this.goodsSroce = goodsSroce;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getCreateEmp() {
		return createEmp;
	}

	public void setCreateEmp(Long createEmp) {
		this.createEmp = createEmp;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getModifyEmp() {
		return modifyEmp;
	}

	public void setModifyEmp(Long modifyEmp) {
		this.modifyEmp = modifyEmp;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Long getOrderGoodsId() {
		return orderGoodsId;
	}

	public void setOrderGoodsId(Long orderGoodsId) {
		this.orderGoodsId = orderGoodsId;
	}
}
