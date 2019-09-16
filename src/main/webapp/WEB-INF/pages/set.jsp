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

	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.js"></script>

	<link href="${pageContext.request.contextPath}/static/js/bootstrap/css/bootstrap.css" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/static/js/bootstrap/js/bootstrap.js"></script>

	<script src="${pageContext.request.contextPath}/static/js/jquery.validate.js"></script>

    <title>后台设置</title>
</head>
<body>

<div class="container">

	<div class="row text-center">
		<h1>设置</h1>
	</div>

	<ul class="nav nav-tabs">
		<li class="active">
			<a href="#home" data-toggle="tab">管理员设置</a>
		</li>
		<li><a href="#order" data-toggle="tab">个人设置</a></li>
		<%--<li>--%>
			<%--<a href="#knowledge" data-toggle="tab">知识库</a>--%>
		<%--</li>--%>
	</ul>
	<div class="tab-content">
		<%-- 管理员设置,菜单图片支付宝图片 --%>
		<div class="tab-pane fade in active" id="home">
			<div class="col-md-12">
				<form action="fileReceive" method="post" enctype="multipart/form-data">

					<div class="form-group">
						<label for="exampleInputFile">菜单A面</label>
						<input type="file" name="dishA_img" id="exampleInputFile">
						<p class="help-block">上传菜单A面</p>
					</div>

					<div class="form-group">
						<label for="exampleInputFile1">菜单B面</label>
						<input type="file" name="dishB_img" id="exampleInputFile1">
						<p class="help-block">上传菜单B面</p>
					</div>

					<div class="form-group">
						<label for="exampleInputFile2">支付宝收款码</label>
						<input type="file" name="alipay" id="exampleInputFile2">
						<p class="help-block">支付宝收款码</p>
					</div>

					<div class="form-group">
						<label for="exampleInputFile3">微信收款码</label>
						<input type="file" name="wechat_pay" id="exampleInputFile3">
						<p class="help-block">微信收款码</p>
					</div>

					<div class="checkbox">
						<label>
							<input type="checkbox"> Check me out
						</label>
					</div>

					<button type="submit" class="btn btn-default">提交</button>

				</form>
			</div>
		</div>
		<%-- 管理员设置,菜单图片支付宝图片END --%>

		<%-- 管理员的个人信息设置 --%>
		<div class="tab-pane fade" id="order">
			<div class="col-md-12">
				<form action="" method="post">

					<div class="form-group">
						<label for="email">Email address</label>
						<input type="email" id="email" class="form-control" placeholder="Email">
					</div>

					<div class="form-group">
						<label for="password">Password</label>
						<input type="password" id="password" class="form-control" placeholder="Password">
					</div>

					<div class="form-group">
						<label for="exampleInputFile">File input</label>
						<input type="file">
						<p class="help-block">Example block-level help text here.</p>
					</div>

					<div class="checkbox">
						<label>
							<input type="checkbox"> Check me out
						</label>
					</div>

					<button type="submit" class="btn btn-default">Submit</button>

				</form>
			</div>
		</div>
		<%-- 管理员的个人信息设置END --%>

		<%--<div class="tab-pane fade" id="knowledge">--%>
			<%--<div style="padding:10px;">--%>
				<%--<div class="list-group" id="div_listKL">--%>
					<%--<div class="alert alert-danger" role="alert">--%>
						<%--<a href="javascript:void(0);" class="alert-link">没有消息了！</a>--%>
					<%--</div>--%>
				<%--</div>--%>
			<%--</div>--%>
		<%--</div>--%>
	</div>




	<div style="height: 30px;"></div>



	<div style="height: 30px;"></div>
</div>


</body>
</html>
