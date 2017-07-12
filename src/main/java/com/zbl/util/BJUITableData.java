/**
 * @since 
 */
package com.zbl.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

/**
 * B-Jui页面数据模型
 * 
 * @author 
 * @date 
 */
public class BJUITableData {
	
	private String identifier = "id";

	private String label = "id";

	/** 当前页码 */
	private int pageIndex;

	/** 总页数 */
	private int pageCount;

	/** 总记录数 */
	private int recordCount;
	
	/** 当前页码 */
	private int pageSize;


	private List<Object> items = new ArrayList<Object>();
	
	public BJUITableData(){
		super();
	}
	public BJUITableData(String identifier,String label){
		this.identifier=identifier;
		this.label=label;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * @return the pageIndex
	 */
	public int getPageIndex() {
		return pageIndex;
	}

	/**
	 * @param pageIndex
	 *            the pageIndex to set
	 */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	/**
	 * @return the pageCount
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * @param pageCount
	 *            the pageCount to set
	 */
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * @return the recordCount
	 */
	public int getRecordCount() {
		return recordCount;
	}

	/**
	 * @param recordCount
	 *            the recordCount to set
	 */
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	/**
	 * @return the items
	 */
	public List<Object> getItems() {
		return items;
	}

	/**
	 * 添加数据
	 * 
	 * @param obj
	 */
	public void addItem(Object obj) {
		this.items.add(obj);
	}
	/**
	 * 添加数据
	 * 
	 * @param obj
	 */
	public void addItem(int index,Object obj) {
		this.items.add(index, obj);
	}

	/**
	 * 移除数据
	 * 
	 * @param obj
	 */
	public void removeItem(Object obj) {
		this.items.remove(obj);
	}

	/**
	 * 添加数据集合
	 * 
	 * @param items
	 */
	public void addAll(Collection<?> items) {

		this.items.addAll(items);
	}

	/**
	 * 转换为json对象
	 * 
	 * @return
	 */
	public JSON asJson() {
		return JSONObject.fromObject(this);
	}

	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @return 总记录数
	 */
	public int getNumRows() {
		return recordCount;
	}

}
