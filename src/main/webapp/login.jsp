<%--
  Created by IntelliJ IDEA.
  User: James
  Date: 2017/7/6
  Time: 12:57
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--ctxPath--%>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
<%--项目路径 --%>
<c:set var="path" value="${ctxPath}"/>
<%--静态文件目录 --%>
<c:set var="staticPath" value="${ctxPath}"/>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<%
    response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
    response.setHeader("Cache-Control", "no-store,no-cache,must-revalidate");
    response.addHeader("Cache-Control", "post-check=0, pre-check=0");
    response.setHeader("Pragma", "no-cache");
    session.setMaxInactiveInterval(-1);
%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登录</title>
    <link rel="shortcut icon" href="${staticPath}/Static/style/images/employee.png"/>

    <!-- Bootstrap core CSS -->
    <link href="images/login-boot/bootstrap.css" rel="stylesheet">

    <!--external css-->
    <link href="images/login-boot/font-awesome.css" rel="stylesheet"/>

    <!-- Custom styles for this template -->
    <link href="images/login-boot/style.css" rel="stylesheet">
    <link href="images/login-boot/style-responsive.css" rel="stylesheet">
</head>
<body>

<style>
    .login-wrap {
        padding: 20px;
        padding-bottom: 80px;
    }
</style>

<script>
    $("#login_form").submit(function () {
        var issubmit = true;
        var i_index = 0;
//        $(this).find('.in').each(function (i) {
//            if ($.trim($(this).val()).length == 0) {
//                $(this).css('border', '1px #ff0000 solid');
//                issubmit = false;
//                if (i_index == 0)
//                    i_index = i;
//            }
//        });
//        if (!issubmit) {
//            $(this).find('.in').eq(i_index).focus();
//            return false;
//        }
//        var $remember = $("#remember");
//        if ($remember.attr('checked')) {
//            $.cookie(COOKIE_NAME, $("#username").val(), {
//                path: '/',
//                expires: 15
//            });
//        } else {
//            $.cookie(COOKIE_NAME, null, {
//                path: '/'
//            }); //删除cookie
//        }
//        $("#login_ok").attr("disabled", true).val('登陆中..');
        /* 		var password = HMAC_SHA256_MAC($("#username").val(), $("#password").val());
         $("#password").val(HMAC_SHA256_MAC($("#j_randomKey").val(), password)); */
        //window.location.href = 'index.html'; /*注意：生产环境时请删除此行*/
        return true;
    });
</script>

<!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->

<div id="login-page">
    <div class="container">

        <form class="form-login" action="${pageContext.servletContext.contextPath }/login" id="login_form"
              method="post">
            <h2 class="form-login-heading">图书管理系统</h2>
            <div class="login-wrap">
                <input name="username" type="text" class="form-control" placeholder="账号" autofocus>
                <br>
                <input name="password" type="password" class="form-control" placeholder="密码">
                <label class="checkbox">
		                <span class="pull-right">
                            <%--<a data-toggle="modal" href="login.html#myModal"> Forgot Password?</a>--%>

                        </span>
                </label>
                <button class="btn btn-theme btn-block" type="submit">
                    登录
                </button>
                <%--<hr>--%>

                <%--<div class="login-social-link centered">--%>
                <%--<p>or you can sign in via your social network</p>--%>
                <%--<button class="btn btn-facebook" type="submit"><i class="fa fa-facebook"></i> Facebook</button>--%>
                <%--<button class="btn btn-twitter" type="submit"><i class="fa fa-twitter"></i> Twitter</button>--%>
                <%--</div>--%>
                <%--<div class="registration">--%>
                <%--Don't have an account yet?<br/>--%>
                <%--<a class="" href="#">--%>
                <%--Create an account--%>
                <%--</a>--%>
                <%--</div>--%>

            </div>

            <!-- Modal -->
            <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal"
                 class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title">Forgot Password ?</h4>
                        </div>
                        <div class="modal-body">
                            <p>Enter your e-mail address below to reset your password.</p>
                            <input type="text" name="email" placeholder="Email" autocomplete="off"
                                   class="form-control placeholder-no-fix">

                        </div>
                        <div class="modal-footer">
                            <button data-dismiss="modal" class="btn btn-default" type="button">Cancel</button>
                            <button class="btn btn-theme" type="button">Submit</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- modal -->

        </form>

    </div>
</div>

<!-- js placed at the end of the document so the pages load faster -->
<script src="images/login-boot/js/jquery.js"></script>
<script src="images/login-boot/js/bootstrap.min.js"></script>

<!--BACKSTRETCH-->
<!-- You can use an image of whatever size. This script will stretch to fit in any screen size.-->
<script type="text/javascript" src="images/login-boot/js/jquery.backstretch.min.js"></script>
<script>
    $.backstretch("images/img/login-bg.jpg", {speed: 500});
</script>


</body>
</html>
