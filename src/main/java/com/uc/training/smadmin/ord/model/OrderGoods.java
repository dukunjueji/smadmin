package com.uc.training.smadmin.ord.model;
import java.io.Serializable;

/**
 * @author kun.du01@ucarinc.com
 * @date 2018-10-17 星期三 16:28
 * @description:订单商品信息表
 */
public class OrderGoods implements Serializable {

	private static final long serialVersionUID =  2616817542365114797L;

	/**自增主键**/
	private Long id;

	/**订单id**/
	private Long orderId;

	/**商品id**/
	private Long goodsId;

	/**商品属性id**/
	private Long goodsPropertyId;

	/**商品数量**/
	private Integer goodsNum;

	/**商品的原价**/
	private Double salePrice;

	/**商品的优惠单价**/
	private Double discountPrice;

	/**商品的实际支付价格**/
	private Double payPrice;

	/**创建人**/
	private Long createEmp;

	/**创建时间**/
	private java.util.Date createTime;

	/**修改人**/
	private Long modifyEmp;

	/**修改时间**/
	private java.util.Date modifyTime;



	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setOrderId(Long orderId){
		this.orderId = orderId;
	}

	public Long getOrderId(){
		return this.orderId;
	}

	public void setGoodsId(Long goodsId){
		this.goodsId = goodsId;
	}

	public Long getGoodsId(){
		return this.goodsId;
	}

	public void setGoodsPropertyId(Long goodsPropertyId){
		this.goodsPropertyId = goodsPropertyId;
	}

	public Long getGoodsPropertyId(){
		return this.goodsPropertyId;
	}

	public void setGoodsNum(Integer goodsNum){
		this.goodsNum = goodsNum;
	}

	public Integer getGoodsNum(){
		return this.goodsNum;
	}

	public void setSalePrice(Double salePrice){
		this.salePrice = salePrice;
	}

	public Double getSalePrice(){
		return this.salePrice;
	}

	public void setDiscountPrice(Double discountPrice){
		this.discountPrice = discountPrice;
	}

	public Double getDiscountPrice(){
		return this.discountPrice;
	}

	public void setPayPrice(Double payPrice){
		this.payPrice = payPrice;
	}

	public Double getPayPrice(){
		return this.payPrice;
	}

	public void setCreateEmp(Long createEmp){
		this.createEmp = createEmp;
	}

	public Long getCreateEmp(){
		return this.createEmp;
	}

	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}

	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	public void setModifyEmp(Long modifyEmp){
		this.modifyEmp = modifyEmp;
	}

	public Long getModifyEmp(){
		return this.modifyEmp;
	}

	public void setModifyTime(java.util.Date modifyTime){
		this.modifyTime = modifyTime;
	}

	public java.util.Date getModifyTime(){
		return this.modifyTime;
	}

}
