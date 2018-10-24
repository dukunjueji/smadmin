package com.uc.training.smadmin.ord.model;
import java.io.Serializable;

/**
 * @author kun.du01@ucarinc.com
 * @date 2018-10-17 星期三 16:28
 * @description:
 */
public class OrderHistory implements Serializable {

	private static final long serialVersionUID =  2554853947932732216L;

	/**主键id**/
	private Long id;

	/**订单id**/
	private Long orderId;

	/**状态:1待付款2取消订单3待发货4待收货5已完成**/
	private Integer status;

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

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus(){
		return this.status;
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
