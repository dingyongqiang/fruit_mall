<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>水果商城</title>
<link href="res/css/bootstrap.min.css" rel="stylesheet">
<link href="res/css/style.css" rel="stylesheet">
<link href="res/css/layui.css" rel="stylesheet">
<script src="res/js/jquery.min.js" type="text/javascript"></script>
<script src="res/js/bootstrap.min.js" type="text/javascript"></script>
<script src="res/js/layui.js" type="text/javascript"></script>
</head>
<body>
	<!--导航栏部分-->
	<jsp:include page="userview/include/header.jsp" />
	<!-- 中间内容 -->
	<div class="container-fluid">
		<div class="row">
			<!-- 控制栏 -->
			<div class="col-sm-2 col-md-1 sidebar sidebar-1">
				<ul class="nav nav-sidebar">
					
				</ul>
			</div>
			<!-- 控制内容 -->
			<div class="col-sm-9 col-sm-offset-3 col-md-9 col-md-offset-2 main">
				<!-- 轮播图 -->
				<div id="myCarousel" class="layui-carousel"   style="height: 330px;">
					 <div carousel-item   style="height: 300px;">
					 	<c:forEach items="${applicationScope.bannerList }" var="b">
					 		 <div><a href="${b.bannerUrl }"><img style="width:1144px;height:400px;" src="upload/${b.bannerImg }"></a></div>
					 	</c:forEach>
					  </div>
				</div>

			
				<!-- 商品展示 -->
				<div class="row">
				
				<div class="span16" style="width:1120px; " >
					<ul>
				<c:forEach items="${applicationScope.goodsList }" var="g"  varStatus="t">
					
					     <c:if test="${t.count%4!=0 }">
								<a href="goods/detail?goodsId=${g.goodsId }">
									<li ><img src="upload/${g.goodsImg }" />
										<p class="goods-title">${g.goodsName }</p>
										<p class="goods-desc">${g.goodsDesc }</p>
										<p>
											<span class="newprice">${g.goodsPrice }</span>&nbsp;
										</p>
									</li>
								</a>
							</c:if>
							<c:if test="${t.count%4==0 }">
								<a href="goods/detail?goodsId=${g.goodsId }">
									<li class='brick4' ><img src="upload/${g.goodsImg }" />
										<p class="goods-title">${g.goodsName }</p>
										<p class="goods-desc">${g.goodsDesc }</p>
										<p>
											<span class="newprice">${g.goodsPrice }元</span>&nbsp;
										</p>
									</li>
								</a>
							</c:if>
				</c:forEach>
				</ul>
				</div>
				</div>
				
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-9 col-md-offset-2">
				<jsp:include page="userview/include/foot.jsp" />
			</div>
		</div>
	</div>

	<script type="text/javascript">
	var layer;
		layui.use(['carousel','layer'], function(){
			  var carousel = layui.carousel;
			  layer=layui.layer;
			  //建造实例
			  carousel.render({
			    elem: '#myCarousel'
			    ,width: '100%' //设置容器宽度
			    ,arrow: 'hover' //始终显示箭头
			    ,anim: 'fade' //切换动画方式
			  });
		});
		$(function(){
			//alert("55");
			getAllType();
		});
		function getAllType(){
			$.ajax({
				   type: "POST",
				   url: "goods/findAll",
				   success: function(arr){
				     for(var i=0;i<arr.length;i++){
				    	   var str="<div class='span16'><ul>";
						     for(var i=0;i<arr.length;i++){					    	
						    	 if((i+1)%4!=0){
						    		 str=str+"<a href='goods/detail?goodsId="+arr[i].goodsId+"'><li>"+
									    "<img src='upload/"+arr[i].goodsImg+"' /><p class='goods-title'>"+arr[i].goodsName+"</p>"+
									    "<p class='goods-desc'>"+arr[i].goodsDesc+"</p><p><span class='newprice'>"+arr[i].goodsPrice+"元</span>&nbsp;"+
									    "</p></li></a>";
						    	 }else{
						    		 str=str+"<a href='goods/detail?goodsId="+arr[i].goodsId+"'><li class='brick4'>"+
									    "<img src='upload/"+arr[i].goodsImg+"' /><p class='goods-title'>"+arr[i].goodsName+"</p>"+
									    "<p class='goods-desc'>"+arr[i].goodsDesc+"</p><p><span class='newprice'>"+arr[i].goodsPrice+"元</span>&nbsp;"+
									    "</p></li></a>";
						    	 }
						     }
						     var str=str+"</ul></div>";
						     $("#productArea"+id).html(str);
				     }
				   }
				});
		}
		
		
	</script>


</body>
</html>