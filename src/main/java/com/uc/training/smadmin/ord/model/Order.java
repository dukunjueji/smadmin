package com.uc.training.smadmin.ord.model;
import java.io.Serializable;

/**
 * @author kun.du01@ucarinc.com
 * @date 2018-10-17 星期三 16:28
 * @description:用户订单表
 */
public class Order implements Serializable {

	private static final long serialVersionUID =  4490456302239574233L;

	/**主键id**/
	private Long id;

	/**会员id**/
	private Long memberId;

	/**订单号**/
	private String orderNum;

	/**订单总额**/
	private double orderPrice;

	/**整个订单的实际支付金额**/
	private double payPrice;

	/**是否删除:1表示删除，0表示未删除**/
	private Integer isDelete;

	/**状态:1待付款2取消订单3待发货4待收货5已完成**/
	private Integer status;

	/**收货人姓名**/
	private String receiptName;

	/**收货人电话**/
	private String receiptTel;

	/**收货人地址**/
	private String receiptAddress;

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

	public void setMemberId(Long memberId){
		this.memberId = memberId;
	}

	public Long getMemberId(){
		return this.memberId;
	}

	public void setOrderNum(String orderNum){
		this.orderNum = orderNum;
	}

	public String getOrderNum(){
		return this.orderNum;
	}

	public void setOrderPrice(double orderPrice){
		this.orderPrice = orderPrice;
	}

	public double getOrderPrice(){
		return this.orderPrice;
	}

	public void setPayPrice(double payPrice){
		this.payPrice = payPrice;
	}

	public double getPayPrice(){
		return this.payPrice;
	}

	public void setIsDelete(Integer isDelete){
		this.isDelete = isDelete;
	}

	public Integer getIsDelete(){
		return this.isDelete;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus(){
		return this.status;
	}

	public void setReceiptName(String receiptName){
		this.receiptName = receiptName;
	}

	public String getReceiptName(){
		return this.receiptName;
	}

	public void setReceiptTel(String receiptTel){
		this.receiptTel = receiptTel;
	}

	public String getReceiptTel(){
		return this.receiptTel;
	}

	public void setReceiptAddress(String receiptAddress){
		this.receiptAddress = receiptAddress;
	}

	public String getReceiptAddress(){
		return this.receiptAddress;
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
