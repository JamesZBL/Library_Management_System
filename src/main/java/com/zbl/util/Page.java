package com.zbl.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Administrator
 * @todo 对页面进行分页控制
 * 
 */
public class Page {
	public static final int DEFAULT_PAGE_SIZE = 20;

	public static final int DEFAULT_PAGE_NUMBER = 1;

	// 基本页面属性

	private int totalObjects = 0; // 总记录数

	private int pageNumber = 1; // 当前页数

	private int pageSize = DEFAULT_PAGE_SIZE; // 每页记录数

	private int start = 0; // 查询记录起始位置，·如果为空则按pogeNumber来查询。

	private String[][] sortProperty; // 按哪个字段排序 new String[]{"ID","DESC"}

	private boolean pageAble = true;
	
	private boolean pageIndexAble = true;//

	private List checkedItem = new ArrayList(); // 保存用户所选择的信息

	// 页面扩展属性

	// 查询条件
	public String search = "";

	// Task
	public String task = "";

	// 选择的项目

	public String boxChecked = "";

	// 排序字段
	public String filterOrder = "";

	// 派序条件
	public String filterOrderDir = "";

	// select filter
	public HashMap filterMap = new HashMap();

	private String hql;

	private int arrayCount;

	private Object[] values;

	public String getHql() {
		return hql;
	}

	public void setHql(String hql) {
		this.hql = hql;
	}

	public int getArrayCount() {
		return arrayCount;
	}

	public void setArrayCount(int arrayCount) {
		this.arrayCount = arrayCount;
	}

	public Object[] getValues() {
		return values;
	}

	public void setValues(Object[] values) {
		this.values = values;
	}

	/**
	 * 通过计算返回查询记录的起始位置。
	 * @return
	 */
	public int getFirstResult() {
		if(this.start>=0){
			if(this.start < this.totalObjects){
				return this.start;
			}else{
				return 0;
			}
		}
		// 计算第一条记录
		int totalPages2 = this.getTotalPages();
		while (totalPages2 < pageNumber && pageNumber != 1)
			pageNumber--;
		return (pageNumber - 1) * pageSize;
	}

	public int getTotalPages() {
		if (totalObjects % pageSize == 0)
			return totalObjects / pageSize;
		else
			return (totalObjects / pageSize) + 1;
	}

	/**
	 * 
	 * @return 总记录数
	 */
	public int getTotalObjects() {
		return totalObjects;
	}

	/**
	 * 设置总记录数
	 * 
	 * @param totalObjects
	 */
	public void setTotalObjects(int totalObjects) {
		this.totalObjects = totalObjects;
	}

	/**
	 * 获取当前页码，页码以1开头
	 * 
	 * @return
	 */
	public int getPageNumber() {
//		if (getTotalObjects() == 0) {
//			return 1;
//		}
		return pageNumber;
	}

	/**
	 * 设置当前页码，页码以1开头
	 * 
	 * @param pageNumber，
	 *            if pageNumber < 1 , pageNumber will be set to 1
	 */
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber < 1 ? 1 : pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String[][] getSortProperty() {
		return sortProperty;
	}

	public void setSortProperty(String[][] sortProperty) {
		this.sortProperty = sortProperty;
	}

	public boolean isPageAble() {
		return pageAble;
	}

	public void setPageAble(boolean pageAble) {
		this.pageAble = pageAble;
	}

	/**
	 * @return the search
	 */
	public String getSearch() {
		return search;
	}

	/**
	 * @param search
	 *            the search to set
	 */
	public void setSearch(String search) {
		this.search = search;
	}

	/**
	 * @return the task
	 */
	public String getTask() {
		return task;
	}

	/**
	 * @param task
	 *            the task to set
	 */
	public void setTask(String task) {
		this.task = task;
	}

	/**
	 * @return the boxChecked
	 */
	public String getBoxChecked() {
		return boxChecked;
	}

	/**
	 * @param boxChecked
	 *            the boxChecked to set
	 */
	public void setBoxChecked(String boxChecked) {
		this.boxChecked = boxChecked;
	}

	/**
	 * @return the filterOrder
	 */
	public String getFilterOrder() {
		return filterOrder;
	}

	/**
	 * @param filterOrder
	 *            the filterOrder to set
	 */
	public void setFilterOrder(String filterOrder) {
		this.filterOrder = filterOrder;
	}

	/**
	 * @return the filterOrderDir
	 */
	public String getFilterOrderDir() {
		return filterOrderDir;
	}

	/**
	 * @param filterOrderDir
	 *            the filterOrderDir to set
	 */
	public void setFilterOrderDir(String filterOrderDir) {
		this.filterOrderDir = filterOrderDir;
	}

	/**
	 * @return the filterMap
	 */
	public HashMap getFilterMap() {
		return filterMap;
	}

	/**
	 * @param filterMap
	 *            the filterMap to set
	 */
	public void setFilterMap(HashMap filterMap) {
		this.filterMap = filterMap;
	}

	public List getCheckedItem() {
		return checkedItem;
	}

	public void setCheckedItem(List checkedItem) {
		this.checkedItem = checkedItem;
	}

	/**
	 * 查询记录起始位置，·如果为空则按pogeNumber来查询。
	 * @param start 要设置的 start
	 */
	public void setStart(int start) {
		this.start = start;
	}

	public boolean isPageIndexAble() {
		return pageIndexAble;
	}

	public void setPageIndexAble(boolean pageIndexAble) {
		this.pageIndexAble = pageIndexAble;
	}

}
