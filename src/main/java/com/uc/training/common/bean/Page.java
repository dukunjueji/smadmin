package com.uc.training.common.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
/**
 * @Description:分页工具类
 * @Package:org.jeese.model
 * @author:wubc
 * @version:1.0
 * @date:2017年10月30日-下午1:51:12
 * @Copyright:2017 Inc. All rights reserved.
 */
public class Page<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 默认当前页码
	 */
	private final static int DEFAULT_PAGE_NO = 1;

	/**
	 * 默认查询记录数
	 */
	private final static int DEFAULT_PAGE_SIZE = 15;

	/**
	 * 默认排序方式
	 */
	private final static String ORDER_DIRECTION = "desc";

	/**
	 * 默认跳转类型(navTab 或 dialog)
	 */
	private final static String TARGET_TYPE = "navTab";

	/**
	 * 查询对象
	 * 参数类型:输入
	 */
	private T object;

	/**
	 * 页码
	 * 参数类型:输入
	 */
	private int pageNum = DEFAULT_PAGE_NO;

	/**
	 * 每页记录数
	 * 参数类型:输入
	 */
	private int pageSize = DEFAULT_PAGE_SIZE;

	/**
	 * 记录总数
	 * 参数类型:输出
	 */
	private long totalCount;

	/**
	 * 排序字段
	 */
	private String orderField;

	/**
	 * 排序方式：asc/desc升序降序
	 */
	private String orderDirection = ORDER_DIRECTION;

	/**
	 * 跳转目标类型
	 */
	private String targetType = TARGET_TYPE;

	/**
	 * 记录结果集
	 */
	private List<T> list;

	/**
	 * 记录结果集
	 */
	private List<Map<String,Object>> mapList;

	/**
	 * 参数列表
	 */
	private Map<String,Object> params;

	public Page() {
	}

	public Page(int pageNum, int pageSize) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public String getOrderDirection() {
		return orderDirection;
	}

	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public List<Map<String, Object>> getMapList() {
		return mapList;
	}

	public void setMapList(List<Map<String, Object>> mapList) {
		this.mapList = mapList;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	/**
	 * 下一页
	 * @return
	 */
	public int nextPageNo(){
		return hasNextPage() ? (pageNum + 1 ): pageNum;
	}
	/**
	 * 是否有下一页
	 * @return
	 */
	public boolean hasNextPage(){
		return (lastPageNo() == pageNum) ? false : true;
	}

	/**
	 * 最后一页
	 * @return
	 */
	public int lastPageNo(){
		return (int)totalCount/pageSize + ((totalCount%pageSize != 0) ? 1 : 0);
	}
	public int getTotalPage(){
		return (int)totalCount/pageSize + ((totalCount%pageSize != 0) ? 1 : 0);
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
}
