<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">

    $(function () {
        $('#form').form({
            url: '${path}/book/saveBook',
            onSubmit: function () {
                progressLoad();
                var isValid = $(this).form('validate');
                if (!isValid) {
                    progressClose();
                }
                return isValid;
                progressClose();
            },
            success: function (result) {
                progressClose();
                result = $.parseJSON(result);

                if (result.success) {
                    $.modalDialog.openner_dataGrid.datagrid('reload'); //之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为shop.jsp页面预定义好了
                    $.modalDialog.handler.dialog('close');
                    parent.$.messager.alert('提示', result.msg, 'info');
                } else {
                    parent.$.messager.alert('提示', result.msg, 'warning');
                }
            }
        });
    });
</script>

<script type="text/JavaScript">
    $.extend($.fn.validatebox.defaults.rules, {
        telNum: { //既验证手机号，又验证座机号
            validator: function (value) {
                return /(^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$)|(^((\d{3})|(\d{3}\-))?(1[358]\d{9})$)/.test(value);
            },
            message: '请输入正确的电话号码。'
        }
    });
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title=""
         style="overflow: hidden;padding: 3px;">
        <form id="form" method="post">
            <table class="grid">
                <tr>
                    <td>书名</td>
                    <td colspan="3"><input name="name" type="text" size="35"
                                           placeholder="请输入图书名称" class="easyui-validatebox"
                                           data-options="required:true"/></td>
                    <td>数量/册</td>
                    <td colspan="3"><input name="number" type="text" size="10"
                                           placeholder="请输入册数" class="easyui-validatebox"
                                           data-options="required:true"/></td>
                </tr>
            </table>
        </form>
    </div>
</div>