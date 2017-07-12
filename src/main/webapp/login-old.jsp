<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

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
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>图书管理系统</title>

<link href="images/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="Static/bootstrap/js/jquery-1.10.1.min.js"></script>
<script type="text/javascript">
 if (top.location != self.location){       
            top.location=self.location;       
        }  
	$(function() {
		//setMenuHeight
		$('.menu').height($(window).height() - 51 - 27 - 26);
		$('.sidebar').height($(window).height() - 51 - 27 - 26);
		$('.page').height($(window).height() - 51 - 27 - 26);
		$('.page iframe').width($(window).width() - 15 - 168);
		//menu on and off
		$('.btn').click(function() {
			$('.menu').toggle();
			if ($(".menu").is(":hidden")) {
				$('.page iframe').width($(window).width() - 15 + 5);
			} else {
				$('.page iframe').width($(window).width() - 15 - 168);
			}
		});

		//
		$('.subMenu a[href="#"]').click(function() {
			$(this).next('ul').toggle();
			return false;
		});
		
		//start
		 var browser = {
		        versions: function() {
		            var u = navigator.userAgent,
		            app = navigator.appVersion;
		            return {
		                mobile: !!u.match(/AppleWebKit.*Mobile.*/),
		                ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/),
		                android: u.indexOf("Android") > -1 || u.indexOf("Linux") > -1,
		                iPhone: u.indexOf("iPhone") > -1,
		                iPad: u.indexOf("iPad") > -1
		            };
		        } (),
		        language: (navigator.browserLanguage || navigator.language).toLowerCase()
		    };		    
		    if(browser.versions.mobile)
		    {
		        window.location.href="phone/login"; 
		    }
		//end
	})
</script>
<script type="text/javascript">
	var COOKIE_NAME = 'sys__username';
	var errmsg="${error}";
	if(errmsg!="")
	{
	   alert(errmsg);
	}
	$(function() {
		//choose_bg();
		//changeCode();
		if ($.cookie(COOKIE_NAME)) {
			$("#username").val($.cookie(COOKIE_NAME));
			$("#password").focus();
			$("#remember").attr('checked', true);
		} else {
			$("#username").focus();
		}
		/*$("#captcha_img").click(function(){
			changeCode();
		});*/
		$("#login_form").submit(function() {
			var issubmit = true;
			var i_index = 0;
			$(this).find('.in').each(function(i) {
				if ($.trim($(this).val()).length == 0) {
					$(this).css('border', '1px #ff0000 solid');
					issubmit = false;
					if (i_index == 0)
						i_index = i;
				}
			});
			if (!issubmit) {
				$(this).find('.in').eq(i_index).focus();
				return false;
			}
			var $remember = $("#remember");
			if ($remember.attr('checked')) {
				$.cookie(COOKIE_NAME, $("#username").val(), {
					path : '/',
					expires : 15
				});
			} else {
				$.cookie(COOKIE_NAME, null, {
					path : '/'
				}); //删除cookie
			}
			$("#login_ok").attr("disabled", true).val('登陆中..');
			/* 		var password = HMAC_SHA256_MAC($("#username").val(), $("#password").val());
					$("#password").val(HMAC_SHA256_MAC($("#j_randomKey").val(), password)); */
			//window.location.href = 'index.html'; /*注意：生产环境时请删除此行*/
			return true;
		});
	});
</script>
</head>

<body>
	<div id="wrap">
		<div id="header"></div>
		<div id="content-wrap">
			<div class="space"></div>
			<form action="${pageContext.servletContext.contextPath }/login"
				id="login_form" method="post" theme="simple">
				<div class="content">
					<div class="field">
						<label>账 户：</label><input id="username" name="username"
							type="text" />
					</div>
					<div class="field">
						<label>密 码：</label><input id="password" name="password"
							type="password" /> <br />
					</div>
					<div class="btn">
						<input name="" type="submit" class="login-btn" value="" />
					</div>
				</div>
			</form>
		</div>
		<div id="footer"></div>
	</div>
</body>
</html>
