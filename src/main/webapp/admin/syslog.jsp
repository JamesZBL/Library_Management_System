<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<%@ include file="/commons/basejs.jsp"%>
<script type="text/javascript">
	var sysLogDataGrid;

	$(function() {
		sysLogDataGrid = $('#sysLogDataGrid').datagrid({
			url : '${path }/log/log_search',
			method : "GET",
			striped : true,
			pagination : true,
			singleSelect : true,
			idField : 'id',
			pageSize : 20,
			sortName : 'create_time',
			sortOrder : 'desc',
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			columns : [ [ {
				width : '120',
				title : '登录名',
				field : 'loginName',
				sortable : true
			}, {
				width : '120',
				title : '用户名',
				field : 'roleName'
			}, {
				width : '100',
				title : 'IP地址',
				field : 'clientIp',
				sortable : true
			}, {
				width : '500',
				title : '内容',
				field : 'optContent'
			}, {
				width : '130',
				title : '创建时间',
				field : 'createTime',
				formatter : function(value) {
					var date = new Date(value);
					var y = date.getFullYear();
					var m = date.getMonth() + 1;
					var d = date.getDate();
					var h = date.getHours();
					var mm = date.getMinutes();
					var ss = date.getSeconds();
					return y + '-' + m + '-' + d + ' ' + h + ':' + mm + ':' + ss;

				}
			} ] ]
		});
	});


	function searchLogFun() {
		sysLogDataGrid.datagrid('load', $.serializeObject($('#searchForm')));
	}

	function cleanLogFun() {
		sysLogDataGrid.datagrid('load', {});
	}
</script>
<!-- <div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false">
		<table id="sysLogDataGrid" data-options="fit:true,border:false"></table>
	</div>
</div> -->

<link rel="stylesheet" type="text/css" href="../images/img/base.css" />
<style type="text/css">
body {
	background: #FFF;
	padding: 0px;
}
</style>

<table width="100%" border="0" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<td height="30">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="15" height="30"><img src="../images/img/tab_03.gif"
						width="15" height="30" /></td>
					<td background="../images/img/tab_05.gif"><strong><img
							src="../images/img/311.gif" width="16" height="16" /> <span
							class="STYLE1">操作日志
						</span> </strong></td>
					<td width="14"><img src="../images/img/tab_07.gif" width="14"
						height="30" /></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="9" background="../images/img/tab_12.gif"></td>
					<td valign="top" bgcolor="#d3e7fc">
						<table width="100%">
							<tr>
								<td>
									<table class="listTb" cellpadding="0" cellspacing="1">
										<tr style="padding-left: 30px;" height="40">
											<td style="height: 500px;">
												<div class="easyui-layout"
													data-options="fit:true,border:false">
													<div data-options="region:'north',border:false"
														style="height: 30px; overflow: hidden; background-color: #fff">
														<form id="searchForm">
															<table>
																<tr>
																	<th>时间:</th>
																	<td><input name="createTime" placeholder="点击选择时间"
																		onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
																		readonly="readonly" /> 至 <input name="createdateEnd"
																		placeholder="点击选择时间"
																		onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
																		readonly="readonly" /> <a href="javascript:void(0);"
																		class="easyui-linkbutton"
																		data-options="iconCls:'fi-magnifying-glass',plain:true"
																		onclick="searchLogFun();">查询</a> <a
																		href="javascript:void(0);" class="easyui-linkbutton"
																		data-options="iconCls:'fi-x-circle',plain:true"
																		onclick="cleanLogFun();">清空</a></td>
																</tr>
															</table>
														</form>
													</div>
													<div
														data-options="region:'center',border:true,title:'操作日志列表'">
														<table id="sysLogDataGrid"
															data-options="fit:true,border:false"></table>
													</div>

												</div>

											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
					<td width="14" rowspan="3" background="../images/img/tab_16.gif">
						&nbsp;</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td height="29">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="15" height="29"><img src="../images/img/tab_20.gif"
						width="15" height="29" /></td>
					<td align="center" background="../images/img/tab_21.gif"
						class="STYLE1"></td>
					<td width="14"><img src="../images/img/tab_22.gif" width="14"
						height="29" /></td>
				</tr>
			</table>

		</td>
	</tr>
</table>