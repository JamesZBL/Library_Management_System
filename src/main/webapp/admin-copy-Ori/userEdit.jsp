<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#userEditorganizationId').combotree({
            url : '${path }/organization/tree',
            parentField : 'pid',
            lines : true,
            panelHeight : 'auto',
            value : '${user.organizationId}'
        });

        $('#userEditRoleIds').combotree({
            url : '${path }/role/tree',
            parentField : 'pid',
            lines : true,
            panelHeight : '120',
            multiple : true,
            required : true,
            cascadeCheck : false,
            value : ${roleIds }
        });

        $('#userEditForm').form({
            url : '${path }/user/edit',
            onSubmit : function() {
                progressLoad();
                var isValid = $(this).form('validate');
                if (!isValid) {
                    progressClose();
                }
                return isValid;
            },
            success : function(result) {
                progressClose();
                result = $.parseJSON(result);
                if (result.success) {
                    parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
                    parent.$.modalDialog.handler.dialog('close');
                } else {
                    parent.$.messager.alert('错误', result.msg, 'error');
                }
            }
        });
        $("#userEditSex").val('${user.sex}');
        $("#userEditUserType").val('${user.userType}');
        $("#userEditStatus").val('${user.locked}');
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="userEditForm" method="post">
            <div class="light-info" style="overflow: hidden;padding: 3px;">
                <div>密码不修改请留空。</div>
            </div>
            <table class="grid">
                <tr>
                    <td>登录名</td>
                    <td><input name="id" type="hidden"  value="${user.id}">
                    <input name="accountname" type="text" placeholder="请输入登录名称" class="easyui-validatebox" data-options="required:true" value="${user.accountname}"></td>
                    <td>姓名</td>
                    <td><input name="username" type="text" placeholder="请输入姓名" class="easyui-validatebox" data-options="required:true" value="${user.username}"></td>
                </tr>
                <tr>
                    <td>密码</td>
                    <td><input type="text" name="password"/></td>
                    <td>性别</td>
                    <td><select id="userEditSex" name="sex" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="0">男</option>
                            <option value="1">女</option>
                    </select></td>
                </tr>
                <tr>
                    <td>年龄</td>
                    <td><input type="text" name="age" value="${user.age}" class="easyui-numberbox"/></td>
                    <td>电话</td>
                    <td>
                        <input type="text" name="phone"  class="easyui-textbox"  value="${user.phone}"   data-options="prompt:'请输入正确的电话号码。',required:true" />
							
					</td>
                    
                  <!-- 
                    <td>用户类型</td>
                    <td><select id="userEditUserType" name="userType" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="0">管理员</option>
                            <option value="1">用户</option>
                    </select></td>
                     -->
                </tr>
                <tr>
                    <td>部门</td>
                    <td><select id="userEditorganizationId" name="organizationId" style="width: 140px; height: 29px;" class="easyui-validatebox" data-options="required:true"></select></td>
                    <td>角色</td>
                    <td><input  id="userEditRoleIds" name="roleIds" style="width: 140px; height: 29px;"/></td>
                </tr>
                <tr>
                    
                    <!--
                    <td>用户状态</td>
                    <td><select id="userEditStatus" name="locked" value="${user.locked}" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="0">正常</option>
                            <option value="1">停用</option>
                    </select></td>
                    -->
                </tr>
            </table>
        </form>
    </div>
</div>