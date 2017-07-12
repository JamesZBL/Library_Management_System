<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<%@ include file="/commons/basejs.jsp" %>
<script type="text/javascript">
    var roleDataGrid;
    $(function() {
        roleDataGrid = $('#roleDataGrid').datagrid({
            url : '${path }/role/dataGrid',
            striped : true,
            rownumbers : true,
            pagination : true,
            singleSelect : true,
            idField : 'id',
            sortName : 'id',
            sortOrder : 'asc',
            pageSize : 20,
            pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
            frozenColumns : [ [ {
                width : '100',
                title : 'id',
                field : 'id',
                sortable : true
            }, {
                width : '80',
                title : '名称',
                field : 'name',
                sortable : true
            } , {
                width : '80',
                title : '排序号',
                field : 'seq',
                sortable : true
            }, {
                width : '200',
                title : '描述',
                field : 'description'
            } , {
                width : '60',
                title : '状态',
                field : 'status',
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
                width : 200,
                formatter : function(value, row, index) {
                        
                    var str = '';
                       
                            str += $.formatString('<a href="javascript:void(0)" class="role-easyui-linkbutton-ok" data-options="plain:true,iconCls:\'fi-check icon-green\'" onclick="grantRoleFun(\'{0}\');" >授权</a>', row.id);
                         str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                           str += $.formatString('<a href="javascript:void(0)" class="role-easyui-linkbutton-save" data-options="plain:true,iconCls:\'fi-check icon-gray\'" onclick="departRoleFun(\'{0}\');" >部门</a>', row.id);
                        if(row.id !=1) 
                        {
                            str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                            str += $.formatString('<a href="javascript:void(0)" class="role-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="editRoleFun(\'{0}\');" >编辑</a>', row.id);
                        
                        
                            str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                            str += $.formatString('<a href="javascript:void(0)" class="role-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="deleteRoleFun(\'{0}\');" >删除</a>', row.id);
                        }
                    return str;
                }
            } ] ],
            onLoadSuccess:function(data){
                $('.role-easyui-linkbutton-ok').linkbutton({text:'授权'});
                $('.role-easyui-linkbutton-save').linkbutton({text:'部门'});
                $('.role-easyui-linkbutton-edit').linkbutton({text:'编辑'});
                $('.role-easyui-linkbutton-del').linkbutton({text:'删除'});
            },
            toolbar : '#roleToolbar'
        });
    });

    function addRoleFun() {
        parent.$.modalDialog({
            title : '添加',
            width : 500,
            height : 300,
            href : '${path }/role/addPage',
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = roleDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#roleAddForm');
                    f.submit();
                   /*  setTimeout(function() {
									$.modalDialog.handler.dialog('close');
									roleDataGrid.datagrid('reload');
								}, 500); */
                }
            } ]
        });
    }

    function editRoleFun(id) {
        if (id == undefined) {
            var rows = roleDataGrid.datagrid('getSelections');
            id = rows[0].id;
        } else {
            roleDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
        }
       parent.$.modalDialog({
            title : '编辑',
            width : 500,
            height : 300,
            href : '${path }/role/editPage?id=' + id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = roleDataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#roleEditForm');
                    f.submit();
                    
                  /*    setTimeout(function() {
									$.modalDialog.handler.dialog('close');
									roleDataGrid.datagrid('reload');
								}, 500); */
                }
            } ]
        });
    }

    function deleteRoleFun(id) {
        if (id == undefined) {//点击右键菜单才会触发这个
            var rows = roleDataGrid.datagrid('getSelections');
            id = rows[0].id;
        } else {//点击操作里面的删除图标会触发这个
            roleDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
        }
       parent.$.messager.confirm('询问', '您是否要删除当前角色？', function(b) {
            if (b) {
                progressLoad();
                $.post('${path }/role/delete', {
                    id : id
                }, function(result) {
                    if (result.success) {
                        parent.$.messager.alert('提示', result.msg, 'info');
                        roleDataGrid.datagrid('reload');
                    }
                    progressClose();
                }, 'JSON');
            }
        });
    }

    function grantRoleFun(id) {
        if (id == undefined) {
            var rows = roleDataGrid.datagrid('getSelections');
            id = rows[0].id;
        } else {
            roleDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
        }
        
        parent.$.modalDialog({
            title : '授权',
            width : 500,
            height : 500,
            href : '${path }/role/grantPage?id=' + id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = roleDataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#roleGrantForm');
                    f.submit();
                }
            } ]
        });
    }
    
    
      function departRoleFun(id) {
        if (id == undefined) {
            var rows = roleDataGrid.datagrid('getSelections');
            id = rows[0].id;
        } else {
            roleDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
        }
        
       parent.$.modalDialog({
            title : '部门',
            width : 500,
            height : 500,
            href : '${path }/role/grantDepartPage?id=' + id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = roleDataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#roleDepartForm');
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
										href="examList.action" title="角色管理">角色管理</a> </span> </strong>
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
															    <div data-options="region:'center',fit:true,border:false">
															        <table id="roleDataGrid" data-options="fit:true,border:false"></table>
															    </div>
															</div>
															<div id="roleToolbar" style="display: none;">
															    <%-- <shiro:hasPermission name="/role/add"> --%>
															        <a onclick="addRoleFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-plus icon-green'">添加</a>
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

