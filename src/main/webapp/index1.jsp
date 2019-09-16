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


	<script type="text/javascript" src="static/js/jquery.js"></script>

	<link href="static/js/bootstrap/css/bootstrap.css" rel="stylesheet">
	<script src="static/js/bootstrap/js/bootstrap.js"></script>

	<title>点餐了</title>
</head>
<body>

<style>
	.imgbox{
		position: relative;
		width: 550px;
		height: 750px;
	}
	.imgbox img{
		position: absolute;
		top: 0;
		bottom: 0;
		left: 0;
		right: 0;
		margin: auto;
		max-width: 100%;
		max-height: 100%;
		outline: 1px solid #000;
	}
</style>
<form action="addOrder" method="post">
<div class="container">

	<div class="row">
		<div class="pull-right">
			<c:choose>
				<c:when test="${pageRes.user}"><div>您好,${pageRes.user.name}</div></c:when>
				<c:when test="${!pageRes.user}"><div>您好,请<a href="/login">登录/注册</a></div></c:when>
			</c:choose>
		</div>
	</div>

	<div class="row text-center">
		<h1>菜单</h1>
	</div>

	<div class="row" style="margin-top: 15px;">
		<div class="container">
			<div class="col-md-6"><img class="imgbox" src="static/image/dishA_img.jpg"></div>
			<div class="col-md-6"><img class="imgbox" src="static/image/dishA_img.jpg"></div>
		</div>
	</div>

	<div class="row" style="margin-top: 15px;">
		<div class="container">
			<table class="table table-hover">
				<thead>
				<tr>
					<td>姓名</td>
					<td>菜名</td>
					<td>金额</td>
					<td>操作</td>
				</tr>
				</thead>
				<tbody>
				<tr>
					<td>隔壁老王(栗子)</td>
					<td>小炒牛肉</td>
					<td>15</td>
					<td>删除</td>
				</tr>
				<tr>
					<td><input class="col-sm-10" type="text" value="" name="name"></td>
					<td><input class="col-sm-10" type="text" value="" name="dish"></td>
					<td><input class="col-sm-10" type="text" value="" name="money"></td>
					<td><input class="deleteBtn" type="button" value="删除"></td>
				</tr>
				</tbody>

				<tfoot></tfoot>
			</table>

		</div>
		<div class="row text-center">
			<input id="addMenu" type="button" value="添加一个">
			<input type="button" value="提交"/>
		</div>

	</div>
</div>
</form>

<div style="height: 800px;"></div>



<script>
	var $addMenu = $("#addMenu");
	var $tbody = $("div.container table tbody");
    var $deleteBtn = $("div.container table tbody input.deleteBtn");
    var $submit = $("div.row text-center input[type='button']");

	var html = "<tr>\n" +
        "\t\t\t\t\t<td><input class=\"col-sm-10\" type=\"text\" value=\"\" name=\"name\"></td>\n" +
        "\t\t\t\t\t<td><input class=\"col-sm-10\" type=\"text\" value=\"\" name=\"dish\"></td>\n" +
        "\t\t\t\t\t<td><input class=\"col-sm-10\" type=\"text\" value=\"\" name=\"money\"></td>\n" +
        "\t\t\t\t\t<td><input class=\"deleteBtn\" type=\"button\" value=\"删除\"></td>\n" +
        "\t\t\t\t</tr>";


	$addMenu.click(function (e) {
		$tbody.append(html);
		$deleteBtn = $("div.container table tbody input.deleteBtn");
		console.log($deleteBtn);
    });

    $tbody.on('click','.deleteBtn',function (e) {
        if ($deleteBtn.length>1){
            var that = $(this);
            var tr = that.parents("tr");
            tr.remove();
        }
    });

    $submit.on("click",function (e) {
	    console.log(111);
	    return false;
    });





</script>


</body>
</html>
