package com.uc.training.smadmin.gds.vo;

import java.io.Serializable;

/**
 * description: TODO
 *
 * @author 黄宏俊
 * @version 1.0
 * @date 2018/11/15  10:25
 */
public class CommentVO implements Serializable {
	/**
	 * 订单详情表ID
	 */
	private Long id;

	/**
	 * 商品名称
	 */
	private String goodsName;

	/**
	 * 商品id
	 */
	private Long goodsId;

	/**
	 * 商品属性id
	 */
	private Long goodsPropertyId;

	/**
	 *商品属性
	 */
	private String GoodsProperty;

	/**
	 * 商品图片url
	 */
	private String imgUrl;

	/**
	 *上下架状态
	 */
	private Long status;

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
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

	public Long getGoodsPropertyId() {
		return goodsPropertyId;
	}

	public void setGoodsPropertyId(Long goodsPropertyId) {
		this.goodsPropertyId = goodsPropertyId;
	}

	public String getGoodsProperty() {
		return GoodsProperty;
	}

	public void setGoodsProperty(String goodsProperty) {
		GoodsProperty = goodsProperty;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
}
