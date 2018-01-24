<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="inc/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>国际短信平台管理端</title>
<%@include file="inc/jscss.jsp"%>
</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">国际短信平台管理端</div>
			<!-- 头部区域（可配合layui已有的水平导航） -->
		</div>
			<!-- 内容主体区域 -->
			<div style="padding: 15px;"></div>
			<form id="submitFrom" class="layui-form layui-form-pane1" style="width: 500px;margin: auto;height: 100%;" action="${ctx}/home" method="post">
				<div class="layui-form-item">
					<label class="layui-form-label">账&nbsp;&nbsp;&nbsp;号</label>
					<div class="layui-input-inline" style="width: 300px;">
						<input type="text" id="username" name="username" lay-verify="required" autocomplete="off"
							class="layui-input">
					</div>
				</div>
				<div class="layui-form-item" >
					<label class="layui-form-label">密&nbsp;&nbsp;&nbsp;码</label>
					<div class="layui-input-inline" style="width: 300px;">
						<input type="password" id="password" name="password"
							autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item" >
					<label class="layui-form-label">验证码</label>
					<div class="layui-input-inline" style="width: 200px;">
						<input type="number" id="captcha" name="captcha"  lay-verify="number"
							autocomplete="off" class="layui-input">
					</div><img src="${ctx }/captcha?tamp=<%=Math.random()%>" style="width: 90px;height: 35px;" onclick="this.src=tampUrl('${ctx }/captcha');"/>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit lay-filter="*" onclick="duSubmit()">登录</button>
					</div>
				</div>
			</form>

		<div class="layui-footer" style="left: 0;text-align: center;">
			<!-- 底部固定区域 -->
		</div>
	</div>
	<script>
		layui.use('element', function() {
			var element = layui.element;
		});
		
		function duSubmit(){
			$.ajax({
				url:"${ctx}/admin/doLogin",
				data:{
					'username':$("#username").val(),
					'password':$("#password").val(),
					'captcha':$("#captcha").val(),
				},
				type:'POST',
				async:false,
				success:function(data){
					 if(data.status == "y"){
				        $("submitFrom").submit()
				     }else{
				    	 layer.alert(data.info,{title: '提示框', icon:3}); 
				     }
				}
				
			});
		}
	</script>
</body>
</html>