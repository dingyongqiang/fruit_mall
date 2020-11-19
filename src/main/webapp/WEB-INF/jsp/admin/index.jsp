<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>水果商城后台管理</title>
<link rel="stylesheet" href="res/css/layui.css">
<script src="res/js/jquery.1.12.4.min.js"></script>
<script src="res/js/layui.js"></script>
</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">水果商城后台管理</div>
			<!-- 头部区域（可配合layui已有的水平导航） -->
			<ul class="layui-nav layui-layout-left">
				<li class="layui-nav-item"><a href="view/welcome" target="myframe">主页</a></li>
			</ul>
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item"><a href="javascript:;">${sessionScope.admin.adminName }</a>
				</li>
				<li class="layui-nav-item"><a href="admin/logout">退出</a></li>
			</ul>
		</div>

		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree" lay-filter="test">
					<li class="layui-nav-item">
							<a class=""	href="javascript:;">商品管理</a>
								<dl class="layui-nav-child">
										<dd>
											<a href="${pageContext.request.contextPath }/view/goodsList" target="myframe">查看商品</a>
										</dd>	
										<dd>
											<a href="${pageContext.request.contextPath }/view/addGoods" target="myframe">添加商品</a>
										</dd>						
								</dl>
			               </li>
							<li class="layui-nav-item">
							<a class=""	href="javascript:;">订单管理</a>
								<dl class="layui-nav-child">
										<dd>
											<a href="${pageContext.request.contextPath }/view/orderlist" target="myframe">查看订单</a>
										</dd>	
															
								</dl>
			               </li>
			               <li class="layui-nav-item">
							<a class=""	href="javascript:;">用户管理</a>
								<dl class="layui-nav-child">
										<dd>
											<a href="${pageContext.request.contextPath }/view/userlist" target="myframe">查看用户</a>
										</dd>	
															
								</dl>
			               </li>
			               <li class="layui-nav-item">
							<a class=""	href="javascript:;">轮播图管理</a>
								<dl class="layui-nav-child">
										<dd>
											<a href="${pageContext.request.contextPath }/view/bannerlist" target="myframe">查看轮播图</a>
										</dd>	
										<dd>
											<a href="${pageContext.request.contextPath }/view/addbanner" target="myframe">添加轮播图</a>
										</dd>						
								</dl>
			               </li>
			               <li class="layui-nav-item">
							<a class=""	href="javascript:;">评论管理</a>
								<dl class="layui-nav-child">
										<dd>
											<a href="${pageContext.request.contextPath }/view/evalist" target="myframe">查看评论</a>
										</dd>	
													
								</dl>
			               </li>
						<li class="layui-nav-item">
							<a class=""	href="javascript:;">账户管理</a>
								<dl class="layui-nav-child">
										<dd>
											<a href="${pageContext.request.contextPath }/view/admininfo" target="myframe">基本资料</a>
										</dd>	
										<dd>
											<a href="${pageContext.request.contextPath }/view/updatepass" target="myframe">修改密码</a>
										</dd>						
								</dl>
			               </li>
				</ul>
			</div>
		</div>

		<div class="layui-body">
			<!-- 内容主体区域 -->
			<iframe src="view/welcome" name="myframe" style="width:1140px;height:550px;border: 0;" ></iframe>
		</div>

		<div class="layui-footer">
			<!-- 底部固定区域 -->
			© www.ymkz - 源码客栈制作
		</div>
	</div>
	
	<script>
		//JavaScript代码区域
		layui.use(['element','layer', 'table'], function() {
			var element = layui.element;
			var table = layui.table;
			var layer = layui.layer;
		});
	</script>
</body>
</html>