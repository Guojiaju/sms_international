<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../common/inc/header.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="../common/inc/jscss.jsp"%>
</head>
<body>
<form class="layui-form layui-form-pane1" name="roleForm" id="roleForm" action="${ctx }/role/doEditOrAdd">
<input type="hidden" name="id" id="id" value="${role.id }"/>
<input type="hidden" name="menuids" id="menuids"/>
  <div class="layui-form-item">
    <label class="layui-form-label">角色名称</label>
    <div class="layui-input-block">
      <input type="text" name="roleName" id="roleName" value="${role.roleName }" lay-verify="required|title" required placeholder="请输入角色名称" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">角色代码</label>
    <div class="layui-input-block">
      <input type="tel" name="description" id="description" value="${role.description }" lay-verify="required|title" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label"></label>
    <div class="layui-input-block">
      <c:forEach items="${menulist1 }" var="m1">
			<ul >
				<li>
					<input type="checkbox" name="form-field-checkbox" id="0_${m1.id }" value="${m1.id }" ${m1.checked } /> ${m1.name }
				</li>
				<c:forEach items="${menulist2 }" var="m2">
					<c:if test="${m2.pid == m1.id }">
						<li style="margin-left: 40px;">
								<input name="form-field-checkbox" type="checkbox" class="ace" id="1_${m1.id }_${m2.id }" value="${m2.id }" ${m2.checked } />
								${m2.name }
						</li>
						<c:forEach items="${menulist3 }" var="m3">
							<c:if test="${m3.pid == m2.id }">
								<li style="margin-left: 70px;"><label class="checkbox-inline">
										<input name="form-field-checkbox" type="checkbox"  id="1_${m2.id }_${m3.id }" value="${m3.id }" ${m3.checked } >
										${m3.name }</label>
								</li>
							</c:if>
						</c:forEach>
					</c:if>
				</c:forEach>
			</ul>
			<hr>
		</c:forEach>
    </div>
  </div>
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit lay-filter="*">立即提交</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </div>
</form>
</body>
<script>

layui.use('form', function(){
  var form = layui.form;
  //事件监听
  form.on('select', function(data){
    console.log(this);
  })
  
  form.on('select(aihao)', function(data){
    console.log(data);
  });
  
  //监听提交
  form.on('submit(*)', function(data){
	  var ids = [];
		$('#roleForm input[type="checkbox"][id^="0_"]:checked,#roleForm input[type="checkbox"][id^="1_"]:checked,#roleForm input[type="checkbox"][id^="2_"]:checked').each(function(){
			ids.push($(this).val());
		});
		$('#menuids').val(ids.join("-"));
    return true;
  });
  
});

</script>
</html>