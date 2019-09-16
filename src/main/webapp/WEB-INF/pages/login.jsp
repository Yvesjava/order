<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/24 0024
  Time: 下午 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/style1.css" media="screen, projection">
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/script.js"></script>


	<%--<script type="text/javascript" src="static/js/jquery.js"></script>--%>

	<%--<link href="static/js/bootstrap/css/bootstrap.css" rel="stylesheet">--%>
	<%--<script src="static/js/bootstrap/js/bootstrap.js"></script>--%>

	<!-- Custom Theme files -->
	<%--<link href="static/css/style.css" rel="stylesheet" type="text/css" media="all"/>--%>
	<!-- Custom Theme files -->
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="keywords" content="Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
	<!--Google Fonts-->
	<!-- <link href='http://fonts.useso.com/css?family=Roboto:500,900italic,900,400italic,100,700italic,300,700,500italic,100italic,300italic,400' rel='stylesheet' type='text/css'> -->
	<!-- <link href='http://fonts.useso.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'> -->
	<!--Google Fonts-->

	<title>登录</title>
</head>
<body>



<header>
	<h1>点餐了</h1>
</header>
<!-- BEGIN WRAPPER DIV -->
<div id="wrapper">
	<h3 id="sign-in-tab" class="active">登录</h3>
	<h3 id="register-tab">注册</h3>
	<!-- BEGIN FORM SECTION -->
	<ul id="form-section">
		<!-- BEGIN SIGN IN FORM -->
		<form class="sign-in-form" action="userLogin" method="post">
			<li>
				<label>
					<span>用户名</span>
					<input placeholder="请输入您的用户名" name="username" pattern="[a-zA-Z0-9]{6,}" type="text" tabindex="1" title="It must contain the username that you have chosen at registration" required autofocus>
				</label>
			</li>
			<li>
				<label>
					<span>密码</span>
					<input placeholder="请输入您的密码" name="password" pattern=".{6,}" type="password" tabindex="2" title="It must contain the password that you have chosen at registration" required>
				</label>
			</li>
			<div id="checkbox">
				<li>
					<input name="remember-me" type="checkbox" id="remember-me"/>
					<span class="unchecked-state"></span>
					<span class="checked-state"></span>
				</li>
				<label for="remember-me">Remember me next time</label>
			</div>
			<li>
				<button name="sign-in-submit" type="submit" id="sign-in-submit">Sign In</button>
			</li>
			<li>
				<label class="sign-in-submit">
					${msg}
				</label>
			</li>
			<div style="clear: both;"></div>
		</form><!-- END OF SIGN IN FORM -->

		<!-- BEGIN REGISTER FORM -->
		<form class="register-form" action="userRegister" method="post">
			<p><span class="register-numbering">1</span><span class="register-numbering-text">基础信息</span></p>
			<li>
				<label class="left-column">
					<span>昵称</span>
					<input type="text" name="nickname" tabindex="1" placeholder="请输入您的昵称" required autofocus title="最好是大家都知道的昵称" required>
				</label>
				<%--<label class="right-column">--%>
					<%--<span>Last Name*</span>--%>
					<%--<input type="text" name="lname" tabindex="2" pattern="[a-zA-Z ]{2,}" placeholder="Please enter your last name" title="It must contain only letters and a length of minimum 2 characters!" required>--%>
				<%--</label>--%>
			</li>
			<div style="clear: both;"></div>
			<li>
				<label>
					<span>Email*</span>
					<input type="email" name="email" tabindex="3" placeholder="Please enter a valid email address" title="It must contain a valid email address e.g. 'someone@provider.com' !" required>
				</label>
			</li>
			<li>
				<label>
					<span>Telephone*</span>
					<input type="tel" name="phoneNum" pattern="(\+?\d[- .]*){7,13}" tabindex="4" placeholder="Please enter your phone number" title="It must contain a valid phone number formed only by numerical values and a length between 7 and 13 characters !">
				</label>
			</li>
			<div style="clear: both;"></div>
			<li>
				<label>
					<span>微信号*</span>
					<input type="tel" name="wechat" tabindex="4" placeholder="请输入你的微信号" title="微信号" required>
				</label>
			</li>
			<div style="clear: both;"></div>
			<li>
				<label>
					<span>是否每日10:15分邮件提醒点餐*</span>
					<input type="radio" name="enableEmailRemind" value="1"> 提醒
					<input type="radio" name="enableEmailRemind" value="0"> 不提醒
				</label>
			</li>
			<%--<p><span class="register-numbering">2</span><span class="register-numbering-text">Location details</span></p>--%>
			<%--<li>--%>
				<%--<label>--%>
					<%--<span>Address*</span>--%>
					<%--<input type="text" name="address" tabindex="5" pattern="[a-zA-Z0-9. - , ]{10,}"  placeholder="Please enter your street address" title="It must contain letters and/or separators and a length of minimum 10 characters !" required>--%>
				<%--</label>--%>
			<%--</li>--%>
			<%--<li>--%>
				<%--<label class="left-column">--%>
					<%--<span>Country*</span>--%>
					<%--<input type="text" name="country" tabindex="6" pattern="[a-zA-Z- ]{2,}"  placeholder="Please enter your country" title="It must contain only letters and a length of minimum 2 characters !" required>--%>
				<%--</label>--%>
			<%--</li>--%>
			<%--<li>--%>
				<%--<label class="right-column">--%>
					<%--<span>ZIP Code*</span>--%>
					<%--<input type="text" name="zipcode" tabindex="7" pattern="[0-9 ]{3,}" placeholder="Please enter your zip code" title="It must contain only numbers and a length of minimum 3 characters !" required>--%>
				<%--</label>--%>
			<%--</li>--%>
			<div style="clear: both;"></div>
			<p><span class="register-numbering">2</span><span class="register-numbering-text">用户登录信息</span></p>
			<li>
				<label>
					<span>用户名*</span>
					<input type="text" name="name" tabindex="8" pattern="[a-zA-Z0-9]{6,}"  placeholder="请输入用户名" title="It must contain alphanumeric characters and a length of minimum 6 characters !" required>
				</label>
			</li>
			<li>
				<label>
					<span>密码*</span>
					<input type="password" name="password" tabindex="9" pattern=".{6,}"  placeholder="请输入密码" title="It can contain all types of characters and a length of minimum 6 characters!" required>
				</label>
			</li>
			<div style="clear: both;"></div>
			<li>
				<button name="submit" tabindex="11" type="submit" id="create-account-submit">注册账户</button>
			</li>
		</form><!-- END OF REGISTER FORM -->
	</ul><!-- END OF FORM SECTION -->
</div><!-- END OF WRAPPER DIV -->
<div style="text-align:center;clear:both"><br>
	<p>本页面由qjy去网上找的</p><br>
</div>







<%--<div class="login">--%>
	<%--<h2>点餐了</h2>--%>
	<%--<div class="login-top">--%>
		<%--<h1>LOGIN FORM</h1>--%>
		<%--<form action="userLogin" method="post">--%>
			<%--<input type="text" name="username" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '';}">--%>
			<%--<input type="password" name="password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '';}">--%>
			<%--<div class="forgot">--%>
				<%--<a href="#">forgot Password</a>--%>
				<%--<input type="submit" value="Login" >--%>
			<%--</div>--%>
		<%--</form>--%>
	<%--</div>--%>
	<%--<div class="login-bottom">--%>
		<%--<h3>New User &nbsp;<a href="#">Register</a>&nbsp Here</h3>--%>
	<%--</div>--%>
<%--</div>--%>

<%--<c:if test="${msg!=null}">--%>
	<%--<div class="copyright">--%>
		<%--<p style="color: red;font-size: x-large;">${msg}</p>--%>
	<%--</div>--%>
<%--</c:if>--%>



<%--<div class="copyright">--%>
	<%--<p>Copyright &copy; 2015.Company diancanle All rights reserved.点餐了</p>--%>
<%--</div>--%>


</body>
</html>
