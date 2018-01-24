<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="inc/header.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="inc/jscss.jsp"%>
</head>
<body>
<form class="layui-form layui-form-pane1" name="roleForm" id="roleForm" action="${ctx }/role/doEditOrAdd">
<input type="hidden" name="id" id="id" value="${role.id }"/>
<input type="hidden" name="menuids" id="menuids"/>
  <div class="layui-form-item">
    <label class="layui-form-label">发送号码</label>
    <div class="layui-input-inline">
      <input type="text" name="mobile" id="mobile"  value="8615000184642" autocomplete="off" class="layui-input">
    </div>
    <div class="layui-form-mid layui-word-aux">号码前必须加国家代码,中国是86开头,测试时使用的是18账号,可以调整通道后测试,红树国际通道不支持中国号码</div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">发送内容</label>
    <div class="layui-input-inline">
      <input type="tel" name="content" id="content"  value="【希奥信息】你的验证码是:975657" autocomplete="off" class="layui-input">
    </div>
    <div class="layui-form-mid layui-word-aux">内容需要带签名</div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label"></label>
    <div class="layui-input-block">
    </div>
  </div>
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit lay-filter="save">立即发送</button>
    </div>
  </div>
</form>
</body>
<script>

layui.use('form', function(){
  var form = layui.form;
  form.on('submit(save)',function (data) {
	  alert($('#mobile').val());
      $.ajax({
          type : 'post',
          url : '${ctx}/doTestSend',
          contentType : 'application/json',
          dataType : 'json',
          data : JSON.stringify({ 'mobile': $('#mobile').val(), 'content': $('#content').val()}),
          success : function(result){
              layer.msg(result > 0 ? "发送成功":"发送失败",{time:2000},function(){
                  parent.layer.closeAll(); //执行关闭
                  parent.location.reload();
              });
          },
          error : function(result){
              layer.alert("系统错误");
          }
      });
      return false;
  });
});

</script>
</html>