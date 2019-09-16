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

	<title>点餐了</title>
	<link href="${pageContext.request.contextPath}/static/image/favicon.ico" rel="icon" type="image/x-ico">
</head>
<body>

<style>
	.imgbox {
		position: relative;
		width: 550px;
	}

	.imgbox img {
		position: absolute;
		top: 0;
		bottom: 0;
		left: 0;
		right: 0;
		margin: auto;
		max-width: 100%;
		outline: 1px solid #000;
	}
</style>

<div class="container">

	<div class="row" style="margin-top: 10px;">
		<c:if test="${user!=null && user.admin}">
			<div class="pull-left">
				<a class="btn btn-primary btn-sm" href="set">设置</a>
			</div>
		</c:if>

		<div class="pull-right">
			<c:choose>
				<c:when test="${user==null}">
					<%--<button class="btn btn-primary btn-sm" data-toggle="modal"--%>
					        <%--data-target="#myModal">--%>
						<%--登陆--%>
					<%--</button>--%>
					<a class="btn btn-primary btn-sm" href="userLogin">
						登陆 | 注册
					</a>
					<%--<button class="btn btn-primary btn-sm" href="">--%>
						<%--注册--%>
					<%--</button>--%>
				</c:when>
				<c:when test="${user!=null}">
					<div>您好,
						<span class="text-info">${user.nickname}</span>
						<a href="logout"><span class="text-warning"> | 退出</span></a>
					</div>
				</c:when>
			</c:choose>
			<%--<div class="pull-right" style="display: inline-block">--%>
				<a class="btn btn-default btn-sm" data-toggle="modal"
				   data-target="#moneyCode" role="button">查看转账二维码</a>
				<%--<a href="testEmail" class="btn btn-default btn-sm"  role="button">sendMail</a>--%>
			<%--</div>--%>
		</div>
	</div>

	<div class="row text-center">
		<h1>菜单</h1>
	</div>

	<div class="row" style="margin-top: 15px;">
		<div class="container">
			<div class="col-md-6"><img class="imgbox"
									   src="${pageContext.request.contextPath}/static/image/dishA_img.jpg?rand=${stamp}"></div>
			<div class="col-md-6"><img class="imgbox" src="${pageContext.request.contextPath}/static/image/dishB_img.jpg?rand=${stamp}"></div>
		</div>
	</div>
	<form id="addOrder" action="addOrder" method="post">
		<div class="row" style="margin-top: 15px;">
			<div class="container">
				<table class="table table-hover">

					<%-- 是否是点餐时间,如果是就显示,不是隐藏 --%>
					<thead>
					<tr>
						<%--<c:if test="${user.admin}">--%>
						<td>用户</td>
						<%--</c:if>--%>
						<td>菜名<c:if test="${user!=null}">-共:${orderCount}份</c:if></td>
						<td>金额<c:if test="${user!=null}">-共:${totalMoney}元</c:if></td>
						<td>操作</td>
					</tr>
					</thead>

					<tbody>
					<c:forEach items="${orders}" var="order">
						<c:if test="${order.deleted==0}">
							<tr id="tr${order.id}">
								<%--<c:if test="${user.admin}">--%>
								<td>${order.user.nickname}</td>
								<%--</c:if>--%>
								<td class="dish">${order.dish}</td>
								<td class="money">${order.money}</td>
								<td>
									<c:if test="${orderTime}">
									<c:if test="${order.user.id==user.id}">
										<a href="softDelete?id=${order.id}&uId=${user.id}">删除</a>
									</c:if>
									<a onclick="copyDish(${order.id})">我也要</a>
									</c:if>
									<c:if test="${!orderTime}">
										--
									</c:if>
								</td>
							</tr>
						</c:if>
						<c:if test="${order.deleted==1}">
							<tr>
								<%--<c:if test="${user.admin}">--%>
									<td><del>${order.user.nickname}</del></td>
								<%--</c:if>--%>
								<td><del>${order.dish}</del></td>
								<td><del>${order.money}</del></td>
								<td>
									<%--<a href="softDelete?id=${order.id}&uId=${user.id}">恢复</a>--%>
									--
								</td>
							</tr>
						</c:if>
					</c:forEach>
					<c:if test="${orderTime}">
					<tr class="addTr">
						<%--<c:if test="${user.admin}">--%>
							<td><span class="form-control">${user.nickname}</span></td>
						<%--</c:if>--%>
						<td><input class="col-sm-10 inputTxt form-control" type="text" name="dish" placeholder="填写快餐名字"
						           required="required"></td>
						<td><input class="col-sm-10 inputTxt form-control" type="number" name="money" placeholder="填写快餐金额"
						           required="required"></td>
						<%--<td><input class="deleteBtn" type="button" value="删除"></td>--%>
						<%--<td>删除</td>--%>
						<td><input type="submit" value="提交"/></td>
					</tr>
					</c:if>
					<c:if test="${!orderTime}">
						<tr align="center">
							<td colspan="<c:if test="${!user.admin}">4</c:if><c:if test="${user.admin}">4</c:if>">
								当前时间不能点餐
							</td>
						</tr>
					</c:if>

					</tbody>

					<%-- 是否是点餐时间 --%>
					<tfoot>
						<c:if test="${ordersMonth.size()==0}">
							<div class='alert alert-primary' style="background-color: #337ab7">
								<p style="font-size: large;color: black;display: none;">倡议:每个月的第一笔订单,建议给酒仙多转1-5元(土豪随意),
									因为每次都是酒仙打电话,非强制的.本提示在每月的第一笔订单时出现</p>
							</div>
						</c:if>
					</tfoot>

				</table>

			</div>
			<div class="row text-center">
				<!-- <input id="addMenu" type="button" value="添加一个"> -->
				<%--<button name="submitForm" onclick="formSubmit()" >提交</button>--%>

			</div>

		</div>
	</form>
	<div style="height: 30px;"></div>

	<div class='alert alert-primary' style="background-color: #54f16c">
		<p style="font-size: large;color: black;">
			1.每次点餐时间是[AM 9:00-11:00]&[PM 17:00-18:00]截止
			<c:if test="${!orderTime}">,当前不是点餐时间</c:if>
			<c:if test="${orderTime}">,当前可以点餐</c:if> <br/>
			2.超过上述时间,本页面将不再接受订单
		</p>
	</div>

	<div style="height: 30px;"></div>
</div>


<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel"
     aria-hidden="true" data-backdrop="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<!--登陆框头部-->
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
				        aria-hidden="true">
					×
				</button>
				<h4 class="modal-title" id="myModalLabel">
					欢迎登陆！
				</h4>
			</div>
			<!--登陆框中间部分(from表单)-->
			<div class="modal-body">
				<form action="userLogin" class="form-horizontal" role="form" method="post">
					<!--用户框-->
					<div class="form-group">
						<label for="username"
						       class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-10">
							<input type="text" class="form-control"
							       id="username"
							       name="username"
							       placeholder="username"
							       required="required">
						</div>
					</div>
					<!--密码框-->
					<div class="form-group">
						<label for="password"
						       class="col-sm-2 control-label">密码</label>
						<div class="col-sm-10">
							<input type="password" class="form-control"
							       id="password"
							       name="password"
							       placeholder="password"
							       required="required">
						</div>
					</div>
					<!--记住密码-->
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<div class="checkbox">
								<label>
									<input type="checkbox"> 记住密码
								</label>
							</div>
						</div>
					</div>
					<!--登陆按钮-->
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit"
							        class="btn btn-default">登录
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="moneyCode" tabindex="-1" role="dialog"
     aria-labelledby="moneyCodeLabel"
     aria-hidden="true" data-backdrop="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<!--登陆框头部-->
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
				        aria-hidden="true">
					×
				</button>
				<h4 class="modal-title" id="moneyCodeLabel">
					酒仙道友！
				</h4>
			</div>
			<!-- 内容区域,平放两张收款码 -->
			<div class="modal-body">
				<img style="width: 400px;margin-right: 60px;margin-left: 2px"
					 src="${pageContext.request.contextPath}/static/image/wechat_pay.jpg?rand=${stamp}">
				<img style="width: 400px;" src="${pageContext.request.contextPath}/static/image/alipay.jpg?rand=${stamp}">
			</div>
		</div>
	</div>
</div>



<script>
    // var $addMenu = $("#addMenu");
    // var $tbody = $("div.container table tbody");
    // var $deleteBtn = $("div.container table tbody input.deleteBtn");
    // var $submit = $("#submit");


    // var html = "<tr class='addTr'>\n" +
    //     "\t\t\t\t\t\t<td><input class=\"col-sm-10 inputTxt\" type=\"text\" value=\"\" name=\"dish\" placeholder=\"填写快餐名字\"\n" +
    //     "\t\t\t\t\t\t           required=\"required\"></td>\n" +
    //     "\t\t\t\t\t\t<td><input class=\"col-sm-10 inputTxt\" type=\"text\" value=\"\" name=\"money\" placeholder=\"填写快餐金额\"\n" +
    //     "\t\t\t\t\t\t           required=\"required\"></td>\n" +
    //     "\t\t\t\t\t\t<td><input class=\"deleteBtn\" type=\"button\" value=\"删除\"></td>\n" +
    //     "\t\t\t\t\t</tr>";


    // $addMenu.click(function (e) {
    //     $tbody.append(html);
    //     $deleteBtn = $("div.container table tbody input.deleteBtn");
    //     console.log($deleteBtn);
    // });

    // $tbody.on('click', '.deleteBtn', function (e) {
    //     if ($deleteBtn.length > 1) {
    //         var that = $(this);
    //         var tr = that.parents("tr");
    //         tr.remove();
    //     }
    // });


    function formSubmit(){
        var flag = true;
        $("#addOrder > .inputTxt").each(function (evt) {
            if (!($(this).val())){
                flag = false;
                alert("请填写完整!再提交");
                return false;
            }
        });

        // 都有值
        if (flag){
            $("#addOrder").submit(function (e) {
                e.preventDefault();
                var form = $(this);
                $("#addOrder tr.addTr").each(function (evt) {
                    var tr = $(this);
                    $.ajax({
                        url: form.attr("action"),
                        type: form.attr("method"),
                        data: {
                            'dish' : tr.children('td').children('input[name="dish"]').val(),
                            'money' : tr.children('td').children('input[name="money"]').val()
                        }
                    });
                });
            });
        }
    }


</script>


<script>

	function copyDish(e){
	    str = "#tr"+e;
	    var originalDish = $(str).children('.dish').html();
	    var originalMoney = $(str).children('.money').html();
        $($('#addOrder')[0][0]).val('').val(originalDish);
        $($('#addOrder')[0][1]).val('').val(parseFloat(originalMoney));
	}
</script>



</body>
</html>
