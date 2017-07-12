<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<%@ include file="/commons/basejs.jsp" %>

<script type="text/javascript">
    var dataGrid;
    var modalWidth = 500;
    var modalHeight = 350;

    $(function () {
        dataGrid = $('#dataGrid').datagrid({
            url: '${path}/book/list',
            method: 'get',
            fit: true,
            striped: true,
            rownumbers: true,
            pagination: true,
            singleSelect: true,
            idField: 'bookId',
            sortName: 'book_id',
            sortOrder: 'desc',
            pageSize: 10,
            pageList: [10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
            columns: [[
                {
                    width: '150',
                    title: 'book_id',
                    field: 'bookId',
                    hidden: true
                }, {
                    width: '500',
                    title: '书名',
                    field: 'name',
                    sortable: true
                }, {
                    width: '80',
                    title: '数量/册',
                    field: 'number',
                    sortable: true
                }, {
                    field: 'action',
                    title: '管理',
                    width: 150,
                    formatter: function (value, row, index) {
                        var str = '';

                        str += $.formatString('<a href="javascript:void(0)" class="user-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="edit(\'{0}\');" >编辑</a>', row.bookId);
                        str += '&nbsp;|&nbsp;';
                        str += $.formatString('<a href="javascript:void(0)" class="user-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="deleteFun(\'{0}\');" >删除</a>', row.bookId);
                        return str;
                    }
                }]],
            onLoadSuccess: function (data) {

                $('.user-easyui-linkbutton-edit').linkbutton({
                    text: '编辑'
                });

                $('.user-easyui-linkbutton-del').linkbutton({
                    text: '删除'
                });
            },
            toolbar: '#actionToolbar'
        });
    });


    function edit(id) {
        if (id == undefined) {
            var rows = dataGrid.datagrid('getSelections');
            id = rows[0].bookId;
        } else {
            dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
        }

        $.modalDialog({
            title: '图书信息编辑',
            width: modalWidth,
            height: modalHeight,
            modal: true,
            resizable: true,
            href: '${path}/book/editPage?id=' + id,
            buttons: [{
                text: '提交更改',
                handler: function () {
                    $.modalDialog.openner_dataGrid = dataGrid;
                    var f = $.modalDialog.handler.find('#form');
                    f.submit();
                }
            }]
        });
    }

    function addFun() {
        $.modalDialog({
            title: '图书信息录入',
            width: modalWidth,
            height: modalHeight,
            href: '${path}/book/addPage',
            method: 'GET',
            buttons: [{
                text: '确认添加',
                handler: function () {
                    $.modalDialog.openner_dataGrid = dataGrid;
                    var f = $.modalDialog.handler.find('#form');
                    f.submit();
                    dataGrid.datagrid('load', {});
                }
            }]
        });

    }

    function deleteFun(id) {
        if (id == undefined) { //点击右键菜单才会触发这个
            var rows = dataGrid.datagrid('getSelections');
            id = rows[0].id;
        } else { //点击操作里面的删除图标会触发这个
            dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
        }
        $.messager.confirm('询问', '确实要删除此书吗？', function (b) {
            if (b) {
                progressLoad();
                $.post('${path }/book/deleteBook', {
                    id: id
                }, function (result) {
                    if (result.success) {
                        $.messager.alert('提示', result.msg, 'info');
                        dataGrid.datagrid('reload');
                    }
                    progressClose();
                }, 'JSON');
            }
        });
    }

    function searchFun() {
        dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
    }
    function cleanFun() {
        $('#searchForm input').val('');
        dataGrid.datagrid('load', {});
    }
</script>


<link rel="stylesheet" type="text/css" href="../images/img/base.css"/>
<style type="text/css">
    body {
        background: #FFF;
        padding: 0px;
    }
</style>


<table width="100%" border="0" align="center" cellpadding="0"
       cellspacing="0">
    <tr>
        <td>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
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
                                                                    <th>书名:</th>
                                                                    <td><input name="name" placeholder="请输入书名"/></td>
                                                                    <!-- <th>添加时间:</th> -->
                                                                    <td>
                                                                        <!-- <input name="createTime" placeholder="点击选择时间"
																		onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
																		readonly="readonly" /> 至 <input name="createdateEnd"
																		placeholder="点击选择时间"
																		onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
																		readonly="readonly" /> -->
                                                                        <a href="javascript:void(0);"
                                                                           class="easyui-linkbutton"
                                                                           data-options="iconCls:'fi-magnifying-glass',plain:true"
                                                                           onclick="searchFun();">按此条件查询</a> <a
                                                                            href="javascript:void(0);"
                                                                            class="easyui-linkbutton"
                                                                            data-options="iconCls:'fi-x-circle',plain:true"
                                                                            onclick="cleanFun();">清空查询条件</a>
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </form>
                                                    </div>
                                                    <div
                                                            data-options="region:'center',border:true,title:'图书列表'">
                                                        <table id="dataGrid"
                                                               data-options="fit:true,border:false"></table>
                                                    </div>

                                                </div>

                                                <div id="actionToolbar" style="display: none;">
                                                    <a onclick="addFun();" href="javascript:void(0);"
                                                       class="easyui-linkbutton"
                                                       data-options="plain:true,iconCls:'fi-plus icon-green'">图书信息录入</a>

                                                </div>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>