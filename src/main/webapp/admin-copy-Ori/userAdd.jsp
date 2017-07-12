<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#userAddOrganizationId').combotree({
            url : '${path }/organization/tree',
            parentField : 'pid',
            lines : true,
            panelHeight : 'auto'
        });

        $('#userAddRoleIds').combotree({
            url: '${path }/role/tree',
            multiple: true,
            required: true,
            panelHeight : '120'
        });

        $('#userAddForm').form({
            url : '${path }/user/add',
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
                    $.messager.alert('提示', result.msg, 'warning');
                }
            }
        });
        
    });
    
    $.extend($.fn.validatebox.defaults.rules, {    
   
    
    telNum:{ //既验证手机号，又验证座机号
      validator: function(value){ 
          return /(^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$)|(^((\d{3})|(\d{3}\-))?(1[358]\d{9})$)/.test(value);
         },    
         message: '请输入正确的电话号码!' 
    }  
});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="userAddForm" method="post">
            <table class="grid">
                <tr>
                    <td>登录名</td>
                    <td><input name="accountname" type="text" placeholder="请输入登录名称" class="easyui-validatebox" data-options="required:true" value=""></td>
                    <td>姓名</td>
                    <td><input name="username" type="text" placeholder="请输入姓名" class="easyui-validatebox" data-options="required:true" value=""></td>
                </tr>
                <tr>
                    <td>密码</td>
                    <td><input name="password" type="password" placeholder="请输入密码" class="easyui-validatebox" data-options="required:true"></td>
                    <td>性别</td>
                    <td>
                        <select name="sex" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="0" selected="selected">男</option>
                            <option value="1" >女</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>年龄</td>
                    <td><input type="text" name="age" class="easyui-numberbox"/></td>
                       <td>电话</td>
                     <td>
                        <input type="text" name="phone" class="easyui-textbox" "  data-options="prompt:'请输入正确的电话号码。',required:true" />
							
                    </td>
                    <!-- 
                    <td>用户类型</td>
                    <td>
                        <select name="userType" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="0">管理员</option>
                            <option value="1" selected="selected">用户</option>
                        </select>
                    </td>
                     -->
                </tr>
                <tr>
                       <td>角色</td>
                    <td><select id="userAddRoleIds" name="roleIds" style="width: 140px; height: 29px;"></select></td>
                </tr>
                <tr>
                 
                   
                    <!-- 
                    <td>用户状态</td>
                    <td>
                        <select name="locked" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                                <option value="0">正常</option>
                                <option value="1">停用</option>
                        </select>
                    </td>
                     -->
                </tr>
            </table>
        </form>
    </div>
</div>