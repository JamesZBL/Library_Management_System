<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<%@ include file="/commons/basejs.jsp"%>

<script type="text/javascript">
	var userDataGrid;
	var organizationTree;

	$(function() {
		organizationTree = $('#organizationTree').tree({
			url : '${path }/organization/tree',
			parentField : 'pid',
			lines : true,
			onClick : function(node) {
				userDataGrid.datagrid('load', {
					organizationId : node.id
				});
			}
		});

		userDataGrid = $('#userDataGrid').datagrid({
			url : '${path }/user/dataGrid',
			fit : true,
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'id',
			sortName : 'createTime',
			sortOrder : 'asc',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			columns : [ [ {
				width : '80',
				title : '登录名',
				field : 'accountname',
				sortable : true
			}, {
				width : '80',
				title : '姓名',
				field : 'username',
				sortable : true
			}, {
				width : '80',
				title : '部门ID',
				field : 'organizationId',
				hidden : true
			}, {
				width : '80',
				title : '所属部门',
				field : 'organizationName'
			}, {
				width : '130',
				title : '创建时间',
				field : 'createtime',
				sortable : true,
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
			}, {
				width : '40',
				title : '性别',
				field : 'sex',
				sortable : true,
				formatter : function(value, row, index) {
					switch (value) {
					case 0:
						return '男';
					case 1:
						return '女';
					}
				}
			}, {
				width : '40',
				title : '年龄',
				field : 'age',
				sortable : true
			}, {
				width : '120',
				title : '电话',
				field : 'phone',
				sortable : true
			},
				{
					width : '200',
					title : '角色',
					field : 'roleNames',
					sortable : true
				}, {
					width : '60',
					title : '用户类型',
					field : 'userType',
					sortable : true,
					hidden : true,
					formatter : function(value, row, index) {
						if (value == 0) {
							return "管理员";
						} else if (value == 1) {
							return "用户";
						}
						return "未知类型";
					}
				}, {
					width : '60',
					title : '状态',
					field : 'locked',
					sortable : true,
					formatter : function(value, row, index) {
						switch (value) {
						case 0:
							return '正常';
						case 1:
							return '停用';
						}
					}
				}, {
					field : 'action',
					title : '操作',
					width : 130,
					formatter : function(value, row, index) {
					   if(row.id==3) return "";
						var str = '';

						str += $.formatString('<a href="javascript:void(0)" class="user-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="editUserFun(\'{0}\');" >编辑</a>', row.id);

						str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
						str += $.formatString('<a href="javascript:void(0)" class="user-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="deleteUserFun(\'{0}\');" >删除</a>', row.id);

						return str;
					}
				} ] ],
			onLoadSuccess : function(data) {
				$('.user-easyui-linkbutton-edit').linkbutton({
					text : '编辑'
				});
				$('.user-easyui-linkbutton-del').linkbutton({
					text : '删除'
				});
			},
			toolbar : '#userToolbar'
		});
	});

	function addUserFun() {
		parent.$.modalDialog({
			title : '添加',
			width : 500,
			height : 300,
			href : '${path }/user/addPage',
			buttons : [ {
				text : '添加',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = userDataGrid; //因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#userAddForm');
					f.submit();					
					/* setTimeout(function() {
									$.modalDialog.handler.dialog('close');
									userDataGrid.datagrid('reload');
								}, 500); */
				}
			} ]
		});
	}

	function deleteUserFun(id) {
		if (id == undefined) { //点击右键菜单才会触发这个
			var rows = userDataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else { //点击操作里面的删除图标会触发这个
			userDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.messager.confirm('询问', '您是否要删除当前用户？', function(b) {
			if (b) {
				var currentUserId = '${sessionInfo.id}'; /*当前登录用户的ID*/
				if (currentUserId != id) {
					progressLoad();
					$.post('${path }/user/delete', {
						id : id
					}, function(result) {
						if (result.success) {
							parent.$.messager.alert('提示', result.msg, 'info');
							userDataGrid.datagrid('reload');
						}
						progressClose();
					}, 'JSON');
				} else {
					parent.$.messager.show({
						title : '提示',
						msg : '不可以删除自己！'
					});
				}
			}
		});
	}

	function editUserFun(id) {
		if (id == undefined) {
			var rows = userDataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {
			userDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.modalDialog({
			title : '编辑',
			width : 500,
			height : 300,
			href : '${path }/user/editPage?id=' + id,
			buttons : [ {
				text : '确定',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = userDataGrid; //因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#userEditForm');
					f.submit();					
				}
			} ]
		});
	}

	function searchUserFun() {
		userDataGrid.datagrid('load', $.serializeObject($('#searchUserForm')));
	}
	function cleanUserFun() {
		$('#searchForm input').val('');
		userDataGrid.datagrid('load', {});
	}
</script>


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
							class="STYLE1"> <a href="#" title="用户管理">用户管理</a>
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
														style="height: 30px; overflow: hidden;background-color: #fff">
														<form id="searchUserForm">
															<table>
																<tr>
																	<th>姓名:</th>
																	<td><input name="name" placeholder="请输入用户姓名" /></td>
																	<th>创建时间:</th>
																	<td><input name="createdateStart"
																		placeholder="点击选择时间"
																		onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
																		readonly="readonly" />至 <input name="createdateEnd"
																		placeholder="点击选择时间"
																		onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
																		readonly="readonly" /> <a href="javascript:void(0);"
																		class="easyui-linkbutton"
																		data-options="iconCls:'fi-magnifying-glass',plain:true"
																		onclick="searchUserFun();">查询</a> <a
																		href="javascript:void(0);" class="easyui-linkbutton"
																		data-options="iconCls:'fi-x-circle',plain:true"
																		onclick="cleanUserFun();">清空</a></td>
																</tr>
															</table>
														</form>
													</div>
													<div
														data-options="region:'center',border:true,title:'用户列表'">
														<table id="userDataGrid"
															data-options="fit:true,border:false"></table>
													</div>
													<div
														data-options="region:'west',border:true,split:false,title:'组织机构'"
														style="width:150px;overflow: hidden; ">
														<ul id="organizationTree"
															style="width:160px;margin: 10px 10px 10px 10px"></ul>
													</div>
												</div>
												<div id="userToolbar" style="display: none;">
													<%--     <shiro:hasPermission name="/user/add"> --%>
													<a onclick="addUserFun();" href="javascript:void(0);"
														class="easyui-linkbutton"
														data-options="plain:true,iconCls:'fi-plus icon-green'">添加</a>
													<%--     </shiro:hasPermission> --%>
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


