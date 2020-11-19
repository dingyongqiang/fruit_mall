<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>水果商城后台管理系统</title>
<link href="res/css/style.css" rel="stylesheet">
<link href="res/css/layui.css" rel="stylesheet">
<link href="res/css/admin.css" rel="stylesheet">
<link href="res/css/pro.css" rel="stylesheet">
<link href="res/css/login.css" rel="stylesheet">
<script src="res/js/jquery.min.js" type="text/javascript"></script>
<script src="res/js/layui.js" type="text/javascript"></script>
<style type="text/css">
</style>
</head>
<body>
	<div class="container-fluid">
		<form id="loginForm">
			<div class="layadmin-user-login layadmin-user-display-show"
				style="padding-bottom: 0px; padding-top: 50px; height: 250px;"
				id="LAY-user-login">
				<div class="layadmin-user-login-main" style="height: 200px;">
					<div class="layadmin-user-login-box layadmin-user-login-header">
						<h2>登陆</h2>
					</div>
					<div
						class="layadmin-user-login-box layadmin-user-login-body layui-form"
						style="height: 100px;">
						<div class="layui-form-item">
							<label
								class="layadmin-user-login-icon layui-icon layui-icon-username"
								for="LAY-user-login-username"></label> <input type="text"
								name="name" lay-verify="required" placeholder="用户名"
								class="layui-input layui-form-danger">
						</div>
						
						<div class="layui-form-item">
							<label
								class="layadmin-user-login-icon layui-icon layui-icon-password"
								for="LAY-user-login-password"></label> <input type="password"
								name="pass" id="userPass" lay-verify="required"
								placeholder="密码" class="layui-input">
						</div>
						<div class="layui-form-item">
							
								<select name="role" lay-verify="required">
									<option value="">请选择</option>
									<option value="1">用户</option>
									<option value="2">管理员</option>
								</select>
							</div>
							<div class="layui-form-item">
								<button class="layui-btn layui-btn-fluid" type="button"
									lay-submit="" lay-filter="loginSubmit">登 入</button>
							</div>
						</div>
					</div>
				</div>
		</form>
	</div>
	<!--尾部-->
	<script type="text/javascript">
		layui.use([ 'form', 'layer' ], function() {
			var form = layui.form;
			 form.render();
			var layer = layui.layer;	
			form.on('submit(loginSubmit)', function() {
				$.ajax({
					type : "post",
					url : "admin/login",
					data : $("#loginForm").serialize(),
					success : function(data) {
						if (data == "successUser") {
							layer.msg("登陆成功！", {
								icon : 1,
								anim : 2,
								time : 2000
							}, function() {
								window.location.href = "view/index";
							});
						}if (data == "successAdmin") {
							layer.msg("登陆成功！", {
								icon : 1,
								anim : 2,
								time : 2000
							}, function() {
								window.location.href = "view/toAdminIndex";
							});
						}if (data == "fail"){
							layer.msg("登陆失败！请检查用户名和密码后重试！", {
								icon : 5,
								anim : 6,
								time : 3000
							});
						}
					}
				});
			});
		});
	</script>
</body>
</html>