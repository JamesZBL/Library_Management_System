/**
 * @since 
 */
package com.zbl.util;

import javax.servlet.http.HttpServletRequest;



/**
 * 分页工具
 * 
 * @author 
 * @date 
 */
public class PageUtil {

	private static final String GRID_PARAM_START = "pageCurrent";

	private static final String GRID_PAGE_SIZE = "pageSize";

	private static final String GRID_ORDER_PROPERTY = "orderProp";

	private static final String GRID_ORDER = "order";
	
	private static final String GRID_SORT = "sort";
	/**
	 * 从请求参数中获取分页对象，页码参数名为”start“
	 * 
	 * @param request
	 * @return
	 */
	public static Page getPage(HttpServletRequest request) {
		Page page = new Page();
		try {
			String pageNum = request.getParameter(GRID_PARAM_START);
			
			int iPageNum = Page.DEFAULT_PAGE_NUMBER;
			if (!Empty.isEmpty(pageNum, true)) {
				iPageNum = Integer.parseInt(pageNum);
			}
			String countS = request.getParameter(GRID_PAGE_SIZE);
			if (!Empty.isEmpty(countS, true)) {
				page.setPageSize(Integer.parseInt(countS));
				page.setStart(iPageNum);
				page.setPageNumber(iPageNum);
			}else{
				page.setPageNumber(iPageNum);
			}
		} catch (Exception e) {
		}

		// 排序信息
		String orderProp = request.getParameter(GRID_ORDER_PROPERTY);
		String order = request.getParameter(GRID_ORDER);
		String sort = request.getParameter(GRID_SORT);
		if (!Empty.isEmpty(order) && !Empty.isEmpty(orderProp)) {
			page.setSortProperty(new String[][] {{orderProp,order}});
		}else if(!Empty.isEmpty(sort)){
			if(sort.startsWith("-")){
				page.setSortProperty(new String[][] { {sort.substring(1),"desc"}});
			}else{
				page.setSortProperty(new String[][] { {sort,"asc"}});
			}
		}
		return page;
	}

	/**
	 * 从请求参数中获取分页对象，页码参数名为”start“
	 * 
	 * @param request
	 * @return
	 */
	public static Page getPage(HttpServletRequest request, String[][] defaultOrder) {
		Page page = new Page();

		try {
			String pageNum = request.getParameter(GRID_PARAM_START);
			int iPageNum = Page.DEFAULT_PAGE_NUMBER;
			if (!Empty.isEmpty(pageNum, true)) {
				iPageNum = Integer.parseInt(pageNum);
			}
			String countS = request.getParameter(GRID_PAGE_SIZE);
			if (!Empty.isEmpty(countS, true)) {
				page.setPageSize(Integer.parseInt(countS));
				page.setStart(iPageNum);
			}else{
				page.setPageNumber(iPageNum);
			}
		} catch (Exception e) {
		}

		// 排序信息
		String orderProp = request.getParameter(GRID_ORDER_PROPERTY);
		String order = request.getParameter(GRID_ORDER);
		String sort = request.getParameter(GRID_SORT);
		if (!Empty.isEmpty(order) && !Empty.isEmpty(orderProp)) {
			page.setSortProperty(new String[][] {{orderProp,order}});
		}else if(!Empty.isEmpty(sort)){
			if(sort.startsWith("-")){
				page.setSortProperty(new String[][] { {sort.substring(1),"desc"}});
			}else{
				page.setSortProperty(new String[][] { {sort,"asc"}});
			}
		}else{
			page.setSortProperty(defaultOrder);
		}

		return page;
	}
	/**
	 * 从请求参数中获取JqueryDatatables分页对象，页码参数名为”start“
	 * 
	 * @param request
	 * @return
	 */
	public static Page getJqueryDataTablesPage(HttpServletRequest request, String[][] defaultOrder) {
		Page page = new Page();
		String orderColumn=""; 
		 String orderDir ="";
		try {
			
			String startIndex = request.getParameter("startIndex");
		    //数据长度
		    String pageSize = request.getParameter("pageSize");


		   //获取客户端需要那一列排序
		    orderColumn = request.getParameter("orderColumn");
		    if(orderColumn == null){
		    	orderColumn = "publishTime";
		    }
		   
		    //获取排序方式 默认为asc
		    orderDir = request.getParameter("orderDir");
		    if(orderDir == null){
		    	orderDir = "desc";
		    }
			
			
			String pageNum = startIndex;
			int iPageNum = Page.DEFAULT_PAGE_NUMBER;
			if (!Empty.isEmpty(pageNum, true)) {
				iPageNum = Integer.parseInt(pageNum);
			}
			String countS = pageSize;
			if (!Empty.isEmpty(countS, true)) {
				page.setPageSize(Integer.parseInt(countS));
				page.setStart(iPageNum);
			}else{
				page.setPageNumber(iPageNum);
			}
		} catch (Exception e) {
		}

		// 排序信息
		String orderProp = orderColumn;
		String order = orderDir;
		String sort = "";
		if (!Empty.isEmpty(order) && !Empty.isEmpty(orderProp)) {
			page.setSortProperty(new String[][] {{orderProp,order}});
		}else if(!Empty.isEmpty(sort)){
			if(sort.startsWith("-")){
				page.setSortProperty(new String[][] { {sort.substring(1),"desc"}});
			}else{
				page.setSortProperty(new String[][] { {sort,"asc"}});
			}
		}else{
			page.setSortProperty(defaultOrder);
		}

		return page;
	}
	
	/**
	 * 将page中的分页信息设置到json数据封装对象中（包括总记录类、总页数、当前页码），<br>
	 * 以便一起返回到页面。
	 * 
	 * @param data
	 * @param page
	 */
	public static void setPageInfo(BJUITableData data, Page page) {
		data.setPageCount(page.getTotalPages());
		data.setPageIndex(page.getPageNumber());
		data.setRecordCount(page.getTotalObjects());
		data.setPageSize(page.getPageSize());
	}

}
