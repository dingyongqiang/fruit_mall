<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<title>layui</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="res/css/layui.css">
<script type="text/javascript" src="res/js/jquery.1.12.4.min.js"></script>
<script type="text/javascript" src="res/js/layui.js"></script>

</head>
<body>

<div class="layui-form-item" style="margin:15px;15px;height:30px;">
    <div class="layui-input-inline">
        <input type="text" id="filter" name="filter" value="" lay-verify="" placeholder="请输入搜索内容" autocomplete="off" class="layui-input">
    </div>
    <div class="layui-input-inline">
        <button class="layui-btn" id="search" data-type="reload" name="search">
            <i class="layui-icon"></i>搜索
        </button>
    </div>
    <div style="float:right;width:110px;height:35px;">
	    <div class="layui-input-inline" style="width:100px;">
	     	<button class="layui-btn layui-btn-normal" id="delete" name="delete">
	            <i class="layui-icon">&#x1006;</i>批量删除
	        </button>
	    </div>
    </div>
</div>
<div>
    <table id="goods" lay-filter="goods"></table>
</div>
<div id="editForm" style="display:none;width:800px;padding-top:10px;">
    <form id="formData" class="layui-form">
    	<input type="hidden" name="goodsId" id="goodsId" >
    	<div class="layui-form-item">
			    <label class="layui-form-label">商品名称</label>
			    <div class="layui-input-block">
			      <input type="text" name="goodsName" id="goodsName" required lay-verify="required" placeholder="请输入商品名称" value="" class="layui-input" />
			    </div>			    
		</div>
		<div class="layui-form-item">
			    <label class="layui-form-label">商品价格</label>
			    <div class="layui-input-block">
			      <input type="text" name="goodsPrice" id="goodsPrice" required lay-verify="required" placeholder="请输入商品价格" value="" class="layui-input" />
			    </div>			    
		</div>
		<div class="layui-form-item">
			    <label class="layui-form-label">商品单位</label>
			    <div class="layui-input-block">
			      <input type="text" name=goodsUnit id="goodsUnit" required lay-verify="required" placeholder="请输入商品单位" value="" class="layui-input" />
			    </div>			    
		</div>
		<div class="layui-form-item">
			    <label class="layui-form-label">商品库存</label>
			    <div class="layui-input-block">
			      <input type="text" name=goodsVolume id="goodsVolume" required lay-verify="required" placeholder="请输入商品库存" value="" class="layui-input" />
			    </div>			    
		</div>
		
		<div class="layui-form-item">
			    <label class="layui-form-label">商品图片</label>
			    <img style="width:260px;height:180px;" src="" id="goodsImg">
			    <button type="button" class="layui-btn layui-btn-warm" id="uploadGoodsImg">
				  <i class="layui-icon">&#xe67c;</i>上传图片
				</button>
				<div style="display:inline-block;" id="newImg"></div>
		</div>
		
		<div class="layui-form-item">
			    <label class="layui-form-label">商品描述</label>
			    <div class="layui-input-block">
			      <textarea name="goodsDesc" id="goodsDesc"  required lay-verify="required" class="layui-textarea"></textarea>
			    </div>			    
		</div>
    </form>
</div>
<script type="text/html" id="bar">
    <a class="layui-btn layui-btn-xs layui-btn-normal" title="编辑" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-xs" title="刪除" lay-event="delete">刪除</a>
</script>
<script type="text/html" id="dateTpl">
    {{ layui.laytpl.fn(d.editdate) }}
</script>
<script type="text/javascript">
var table;
var layer;
var form;
var upload;
        layui.use(['layer', 'table','form','upload'], function ()
        {
            table = layui.table;
            layer = layui.layer;
            form =layui.form;
            upload=layui.upload;
            layui.laytpl.fn = function (value)
            {
                //json日期格式转换为正常格式
                var date = new Date(parseInt(value.replace("/Date(", "").replace(")/", ""), 10));
                var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
                var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
                return date.getFullYear() + "-" + month + "-" + day;
            }
            var uploadInst = upload.render({
                elem: '#uploadGoodsImg' //绑定元素
                ,url: 'goods/uploadImg' //上传接口
                ,data:'json'
                ,done: function(res){
                  layer.msg("上传图片成功！",{icon:1,time:2000});
                  var str="<img style='width:280px;height:160px;' src='upload/"+res.src+"' /><input type='hidden' name='goodsImg' value='"+res.src+"' />";
                  $("#newImg").html(str);
                }
                ,error: function(){
                  layer.msg("图片上传失败！请重试！",{icon:5,time:2000});
                }
              });
            
            
            //--------------方法渲染TABLE----------------
            var tableIns = table.render({
                elem: '#goods'
                , id: 'goods'
                , url: 'goods/findBySplitPage'
                , cols: [[
                     { checkbox: true, LAY_CHECKED: false } //其它参数在此省略
                      , { field: 'goodsId', title: '编号', width: 80, align: 'center' }
                     , { field: 'goodsName', title: '商品名称', width: 140, align: 'center' }
                    , { field: 'goodsPrice', title: '价格', width: 90,sort:true, align: 'center' }
                    , { field: 'goodsUnit', title: '单位', width: 90,sort:true, align: 'center' }
                    , { field: 'goodsVolume', title: '库存', width: 90,sort:true, align: 'center' }
                    ,{field:'goodsImg', title: '商品图片', width: 160 ,align: 'center',templet: function(d){
                          return '<img src="upload/'+d.goodsImg+'" />'
                     	}
                    }
                    , { field: 'goodsDesc', title: '商品描述', width: 160, align: 'center' }
                    ,{ field:'goodsState', title: '用户状态', width: 112, align: 'center',templet: function(d){
                    	 if(d.goodsState==1){
                       	  return "<input type='checkbox' lay-filter='switchTest' name='"+d.userId+"' lay-skin='switch' lay-text='禁用|正常'><input type='hidden' id='kaigID' value='"+d.goodsId+"' />"
                    	 }else{                    	 
                      	  return "<input type='checkbox' lay-filter='switchTest' name='"+d.goodsState+"' lay-skin='switch' lay-text='禁用|正常' checked><input type='hidden' id='kaigID' value='"+d.goodsId+"' />"
                    	 }		  
                      	  
              	 }
                }
                    , {title: '操作', fixed: 'right', width: 160, align: 'center', toolbar: '#bar' }
                ]]
                , page: true
                , limits: [5, 10, 15]
                , limit: 10 //默认采用10
                , done: function (res, curr, count)
                {
                    //如果是异步请求数据方式，res即为你接口返回的信息。
                    //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                    //console.log(res);
                    //得到当前页码
                    //console.log(curr);
                    $("#curPageIndex").val(curr);
                    //得到数据总量
                    //console.log(count);
                }
            });
            //#endregion
			form.on('switch(switchTest)',function(data){
				console.log(data.elem.checked); //开关是否开启，true或者false
				console.log(data.value); //开关value值，也可以通过data.elem.value得到
				var id = $(data.othis).next("input").val();
				if(data.elem.checked ==true){
					$.ajax({
						type:"post",
						url:"goods/changeGoodsState",
						data:"goodsId="+id+"&goodsState="+0,
						success:function(data){
							if(data=="success"){
								layer.msg('禁用分类成功！',{icon:1,time:2000});
							}else{
								layer.msg('禁用分类失败！请重试！',{icon:5,time:2000});
							}
						}
					});
				}else{
					$.ajax({
						type:"post",
						url:"goods/changeGoodsState",
						data:"goodsId="+id+"&goodsState="+1,
						success:function(data){
							if(data=="success"){
								layer.msg("启用分类成功！",{icon:1,time:2000});
							}else{
								layer.msg("启用分类失败！请重试！",{icon:5,time:2000});
							}
						}
					});
				}
			});
            //#region --------------搜索----------------
            $("#search").click(function ()
            {
                var strFilter = $("#filter").val();
                tableIns.reload({
                    where: {
                        keyword: strFilter
                    },page: {
                    curr: 1 //重新从第 1 页开始
                  }
                });
            });
            //#endregion

            //#region --------------批量删除----------------
            $("#delete").click(function ()
            {
                var checkStatus = table.checkStatus('goods');
                var count = checkStatus.data.length;//选中的行数
                if (count > 0)
                {
                    parent.layer.confirm('确定要删除所选商品？', { icon: 3 }, function (index)
                    {
                        var data = checkStatus.data; //获取选中行的数据
                        var batchId = '';
                        for (var i = 0; i < data.length; i++)
                        {
                        	batchId += data[i].goodsId + ",";
                        }
                        $.ajax({
                            url: 'goods/batchDelete',
                            type: "post",
                            data: { 'batchId': batchId },
                            success: function (result){
                                if (result=="success"){
                                     parent.layer.msg('删除成功', { icon: 1, shade: 0.4, time: 1000 })
                                     $("#search").click();//重载TABLE
                                }else{
                                     parent.layer.msg("删除失败", { icon: 5, shade: [0.4], time: 1000 });
                                }
                                parent.layer.close(index);
                            }
                        })
                    });
                }
                else
                    parent.layer.msg("请至少选择一条数据", { icon: 5, shade: 0.4, time: 1000 });
            });
            //#endregion

            //#endregion
            //工具条事件监听
            table.on('tool(goods)', function (obj)
            { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                var layEvent = obj.event; //获得 lay-event 对应的值
                var tr = obj.tr; //获得当前行 tr 的DOM对象
                var goodsId = data.goodsId;
                if (layEvent === 'edit')
                { //编辑
                    layer.open({
                        type: 1,
                        title: '编辑商品信息',
                        shade: 0.4,  //阴影度
                        fix: false,
                        shadeClose: true,
                        maxmin: false,
                        area: ['900px;', '540px;'],    //窗体大小（宽,高）
                        content: $('#editForm'),
                        success: function (layero, index)
                        {
                            var body = layer.getChildFrame('body', index); //得到子页面层的BODY
                            $("#goodsId").val(data.goodsId);
                            $("#goodsName").val(data.goodsName);
                           	$("#goodsPrice").val(data.goodsPrice);
                        	$("#goodsUnit").val(data.goodsUnit);
                            $("#goodsVolume").val(data.goodsVolume);                          
                            $("#goodsImg").attr("src","upload/"+data.goodsImg);
                            $("#goodsDesc").val(data.goodsDesc);
                            form.render();
                            body.find('#hidValue').val(index); //将本层的窗口索引传给子页面层的hidValue中
                        },
                        btn:['修改','取消'],
                        yes: function(index, layero){
                        	$.post('goods/updateGoods',$('#formData').serialize(),function(data){
                                if (data == 'success')
                                {
                                    parent.layer.msg('修改成功', { icon: 1, shade: 0.4, time: 1000 });
                                    $("#search").click();
                                    $("#handle_status").val('');
                                }
                                else
                                {
                                    parent.layer.msg('修改失败', { icon: 5, shade: 0.4, time: 1000 });
                                }
                                $("#newImg").html("");
                                layer.close(index);
                        	}); 
                        }
                    });
                }else if(layEvent === 'delete'){
                	layer.confirm('是否删除该商品？', {
                		  btn: ['确认', '取消'] //可以无限个按钮
                		  ,btn1: function(index, layero){
                			  $.ajax({
                				   type: "POST",
                				   url: "goods/delete",
                				   data: "goodsId="+data.goodsId,
                				   success: function(msg){
                				     if(msg=='success'){
                				    	 parent.layer.msg('删除成功', { icon: 1, shade: 0.4, time: 1000 });
                				     }else{
                				    	 parent.layer.msg('删除失败', { icon: 5, shade: 0.4, time: 1000 });
                				     }
                				   }
                				});
                			  $(tr).remove();
                			  layer.close(index);
                		  }
                		});
                }
            });
        });
    </script>
</body>
</html>