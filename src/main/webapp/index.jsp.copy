<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<%@ include file="/commons/basejs.jsp" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>图书管理系统</title>
    <link href="images/style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript"
            src="Static/bootstrap/js/jquery-1.10.1.min.js"></script>
    <%-- [EasyUI] --%>
    <link id="easyuiTheme" rel="stylesheet" type="text/css"
          href="${staticPath }/Static/easyui/themes/default/easyui.css"/>
    <link id="easyuiTheme" rel="stylesheet" type="text/css"
          href="${staticPath }/Static/easyui/themes/icon.css"/>
    <script type="text/javascript"
            src="${staticPath }/Static/easyui/jquery.easyui.min.js" charset="utf-8"></script>
    <script type="text/javascript"
            src="${staticPath }/Static/easyui/locale/easyui-lang-zh_CN.js"
            charset="utf-8"></script>
    <%-- [扩展JS] --%>
    <script type="text/javascript" src="${staticPath }/Static/extJs.js"
            charset="utf-8"></script>
    <script type="text/javascript">
        $(function () {
            //setMenuHeight
            $('.leftmenu').height($(window).height() - 51 - 27 - 26);
            $('.sidebar').height($(window).height() - 51 - 27 - 26);
            $('.page').height($(window).height() - 51 - 27 - 26);
            $('.page iframe').width($(window).width() - 15 - 190 - 10);

            //menu on and off
            $('.btn').click(function () {
                $('.leftmenu').toggle();

                if ($(".leftmenu").is(":hidden")) {
                    $('.page iframe').width($(window).width() - 15 + 5);
                } else {
                    $('.page iframe').width($(window).width() - 15 - 190);
                }
            });

            //
            $('.subMenu a[href="#"]').click(function () {
                $(this).next('ul').toggle();
                return false;
            });
        })

        function logout() {
            $.messager.confirm('提示', '确定要退出?', function (r) {
                if (r) {
                    progressLoad();
                    $.post('${path }/logout', function (result) {
                        if (result.success) {
                            progressClose();
                            window.location.href = '${path }';
                        }
                    }, 'json');
                }
            });
        }

        function editUserPwd() {
            $.modalDialog({
                title: '修改密码',
                width: 300,
                height: 250,
                href: '${path }/user/editPwdPage',
                buttons: [{
                    text: '确定',
                    handler: function () {
                        var f = $.modalDialog.handler.find('#editUserPwdForm');
                        f.submit();
                    }
                }]
            });
        }
        window.onresize = function () {
            $('.leftmenu').height($(window).height() - 51 - 27 - 26);
            $('.sidebar').height($(window).height() - 51 - 27 - 26);
            $('.page').height($(window).height() - 51 - 27 - 26);
            $('.page iframe').width($(window).width() - 15 - 190 - 10);
        }
    </script>

    <style type="text/css">
        body {
            background: #FFF;
            padding: 0px;
        }
    </style>
</head>

<body>
<div id="wrap">
    <div id="header">
        <div class="logo fleft"></div>

        <span style="float: right; padding-right: 20px; color: #333">
					</b><a href="javascript:void(0)" onclick="editUserPwd()"
                           class="easyui-linkbutton"
                           data-options="plain:true">修改密码</a>
						<a href="javascript:void(0)" onclick="logout()"
                           class="easyui-linkbutton"
                           data-options="plain:true">安全退出</a> </span>

        <div class="clear"></div>
        <div class="subnav">
            <div class="subnavLeft fleft"></div>
            <div class="fleft"></div>
            <div class="subnavRight fright"></div>
        </div>
    </div>
    <!--#header -->
    <div id="content">
        <div class="space"></div>
        <div class="leftmenu fleft">
            <ul>
                <li class="subMenuTitle">Menu</li>
                <li><a href="./welcome.html" target="right">首页</a></li>
                <li class="subMenu"><a href="#">图书管理</a>
                    <ul style="display: none">
                        <li><a href="book/page" target="right">图书管理</a></li>

                    </ul>
                </li>
                <li class="subMenu"><a href="#">图书借阅</a>
                    <ul style="display: none">
                        <li><a href="#" target="right">开发中:)</a></li>
                        <li><a href="#" target="right">开发中:)</a></li>
                    </ul>
                </li>
                <li class="subMenu"><a href="#">图书购买</a>
                    <ul style="display: none">
                        <li><a href="#" target="right">开发中:)</a></li>

                        <li><a href="#" target="right">开发中:)</a></li>

                    </ul>
                </li>

                <li class="subMenu"><a href="#">图书出租 </a>
                    <ul style="display: none">
                        <li><a href="#" target="right">开发中:)</a></li>
                        <li><a href="#" target="right">开发中:)</a></li>
                    </ul>
                </li>

                <li class="subMenu"><a href="#">用户管理</a>
                    <ul style="display: none">
                        <li><a href="#" target="right">开发中:)</a></li>
                        <li><a href="#" target="right">开发中:)</a></li>
                    </ul>
                </li>
                <li class="subMenu"><a href="#">学生管理</a>
                    <ul style="display: none">
                        <li><a href="#" target="right">开发中:)</a></li>
                        <li><a href="#" target="right">开发中:)</a></li>
                    </ul>
                </li>
                <li class="subMenu">
                    <a href="#">系统设置</a>
                    <ul style="display: none">
                        <li>
                            <a href="${pageContext.request.contextPath}/user/manager"
                               target="right">用户管理</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/role/manager"
                               target="right">角色管理</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/resource/manager"
                               target="right">资源管理</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
        <div class="sidebar fleft">
            <div class="btn"></div>
        </div>
        <div class="page">
            <iframe width="100%" scrolling="auto" height="100%"
                    frameborder="false" allowtransparency="true"
                    style="border: medium none;" src="welcome.html" id="rightMain"
                    name="right"></iframe>
        </div>
    </div>
    <!--#content -->
    <div id="footer" style="height: 21px;">
        <div style="text-align: center;">
            <p style="color: white;">图书管理系统</p>
        </div>
    </div>
    <!--#footer -->
</div>

</body>
</html>
