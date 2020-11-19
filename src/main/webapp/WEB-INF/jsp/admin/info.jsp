<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="res/css/layui.css">
<script src="res/js/jquery.1.12.4.min.js"></script>
<script type="text/javascript" src="res/js/layui.js"></script>
</head>
<body>
	<div class="layui-fluid">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md12">
				<div class="layui-card">
					<div class="layui-card-header">查看我的资料</div>
					<div class="layui-card-body" pad15="">

						<form id="formData" class="layui-form">
							<div class="layui-form-item">
								<div class="layui-input-inline">
									<input type="hidden" name="adminId"
										value="${sessionScope.admin.adminId }" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">用户名</label>
								<div class="layui-input-inline">
									<input type="text" name="adminName"
										value="${sessionScope.admin.adminName }" readonly=""
										class="layui-input">
								</div>
								<div class="layui-form-mid layui-word-aux">不可修改。一般用于后台登入名</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">手机</label>
								<div class="layui-input-inline">
									<input type="text" name="adminPhone"
										value="${sessionScope.admin.adminPhone }" lay-verify="phone" readonly=""
										autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">日期</label>
								<div class="layui-input-inline">
									<input type="text" class="layui-input" id="test1"   readonly=""
										name="adminDate" value="${sessionScope.admin.adminDate}" />

								</div>
							</div>



							
						</form>

					</div>
				</div>
			</div>
		</div>
	</div>
</body>


</html>
