<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<%@ include file="/commons/basejs.jsp" %>
<script type="text/javascript">
	var resourceTreeGrid;
	$(function() {
		resourceTreeGrid = $('#resourceTreeGrid')
				.treegrid(
						{
							url : '${path }/resource/treeGrid',
							idField : 'id',
							treeField : 'name',
							parentField : 'pid',
							fit : true,
							fitColumns : false,
							border : false,
							frozenColumns : [ [ {
								title : '编号',
								field : 'id',
								width : 40
							} ] ],
							columns : [ [
									{
										field : 'name',
										title : '资源名称',
										width : 150
									},
									{
										field : 'url',
										title : '资源路径',
										width : 200
									},
									{
										field : 'openMode',
										title : '打开方式',
										width : 60
									},
									{
										field : 'seq',
										title : '排序',
										width : 40
									},
									{
										field : 'iconCls',
										title : '图标',
										width : 120
									},
									{
										field : 'resourceType',
										title : '资源类型',
										width : 80,
										formatter : function(value, row, index) {
											switch (value) {
											case 0:
												return '菜单';
											case 1:
												return '按钮';
											}
										}
									},
									{
										field : 'pid',
										title : '上级资源ID',
										width : 150,
										hidden : true
									},
									{
										field : 'status',
										title : '状态',
										width : 40,
										formatter : function(value, row, index) {
											switch (value) {
											case 0:
												return '正常';
											case 1:
												return '停用';
											}
										}
									},
									{
										field : 'action',
										title : '操作',
										width : 130,
										formatter : function(value, row, index) {
											var str = '';											
											str += $
													.formatString(
															'<a href="javascript:void(0)" class="resource-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="editResourceFun(\'{0}\');" >编辑</a>',
															row.id);											
											str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
											str += $
													.formatString(
															'<a href="javascript:void(0)" class="resource-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="deleteResourceFun(\'{0}\');" >删除</a>',
															row.id);											
											return str;
										}
									} ] ],
							onLoadSuccess : function(data) {
								$('.resource-easyui-linkbutton-edit')
										.linkbutton({
											text : '编辑'
										});
								$('.resource-easyui-linkbutton-del')
										.linkbutton({
											text : '删除'
										});
							},
							toolbar : '#resourceToolbar'
						});
	});

	function editResourceFun(id) {
		if (id != undefined) {
			resourceTreeGrid.treegrid('select', id);
		}
		var node = resourceTreeGrid.treegrid('getSelected');
		if (node) {
			parent.$.modalDialog({
						title : '编辑',
						width : 500,
						height : 350,
						href : '${path }/resource/editPage?id=' + node.id,
						buttons : [ {
							text : '确定',
							handler : function() {
								parent.$.modalDialog.openner_treeGrid = resourceTreeGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
								var f = parent.$.modalDialog.handler
										.find('#resourceEditForm');
								f.submit();
							}
						} ]
					});
		}
	}

	function deleteResourceFun(id) {
		if (id != undefined) {
			resourceTreeGrid.treegrid('select', id);
		}
		var node = resourceTreeGrid.treegrid('getSelected');
		if (node) {
			$.messager.confirm('询问', '您是否要删除当前资源？删除当前资源会连同子资源一起删除!',
					function(b) {
						if (b) {
							progressLoad();
							$.post('${path }/resource/delete', {
								id : node.id
							}, function(result) {
								if (result.success) {
									$.messager.alert('提示', result.msg,
											'info');
									resourceTreeGrid.treegrid('reload');
									layout_west_tree.tree('reload');
								}
								progressClose();
							}, 'JSON');
						}
					});
		}
	}

	function addResourceFun() {
		$.modalDialog({
			title : '添加',
			width : 500,
			height : 350,
			href : '${path }/resource/addPage',
			buttons : [ {
				text : '添加',
				handler : function() {
					$.modalDialog.openner_treeGrid = resourceTreeGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
					var f = $.modalDialog.handler
							.find('#resourceAddForm');
					f.submit();
				}
			} ]
		});
	}
</script>
<link rel="stylesheet" type="text/css"
			href="../images/img/base.css" />
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
							<td width="15" height="30">
								<img src="../images/img/tab_03.gif" width="15" height="30" />
							</td>
							<td background="../images/img/tab_05.gif">
								<strong><img src="../images/img/311.gif"
										width="16" height="16" /> <span class="STYLE1"> <a
										href="examList.action" title="资源管理">资源管理</a> </span> </strong>
							</td>
							<td width="14">
								<img src="../images/img/tab_07.gif" width="14" height="30" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="9" background="../images/img/tab_12.gif">
							</td>
							<td valign="top" bgcolor="#d3e7fc">
								<table width="100%">
									<tr>
										<td>
											<table class="listTb" cellpadding="0" cellspacing="1">												
												<tr style="padding-left: 30px;" height="40">
													<td style="height: 500px;">
															<div class="easyui-layout" data-options="fit:true,border:false">
													    <div data-options="region:'center',border:false"  style="overflow: hidden;">
													        <table id="resourceTreeGrid"></table>
													    </div>
													</div>
													<div id="resourceToolbar" style="display: block;">
													    <%-- <shiro:hasPermission name="/resource/add"> --%>
													        <a onclick="addResourceFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-plus icon-green'">添加</a>
													    <%-- </shiro:hasPermission> --%>
													</div>
													</td>
												</tr>
											</table>
										</td>
									</tr>									
								</table>
							</td>
							<td width="14" rowspan="3"
								background="../images/img/tab_16.gif">
								&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="29">					    
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="15" height="29"><img		src="../images/img/tab_20.gif" width="15" height="29" />
						</td>
						<td align="center" background="../images/img/tab_21.gif"	class="STYLE1">
							 
						</td>
						<td width="14"><img src="../images/img/tab_22.gif"	width="14" height="29" />
						</td>
					</tr>
				</table>    
					    
				</td>
			</tr>
		</table>
	

