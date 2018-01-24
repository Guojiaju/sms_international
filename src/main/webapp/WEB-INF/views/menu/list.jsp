<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/inc/header.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="../common/inc/jscss.jsp"%>
</head>
<body>
	<form class="layui-form layui-form-pane1" action="">
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">菜单名称</label>
				<div class="layui-input-inline">
					<input type="text" id="mname" name="mname" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">上级菜单</label>
				<div class="layui-input-inline">
					<select name="mpid" id="mpid" >
						<option value="">----全部----</option>
						<option value="0" <c:if test="${0 == menu.pid }"> selected="selected"</c:if>>===顶级===</option>
						<c:forEach items="${parentMenu1 }" var="m1">
							<option value="${m1.id }"
								<c:if test="${m1.id == menu.pid }"> selected="selected"</c:if>>${m1.name }</option>
							<c:forEach items="${parentMenu2 }" var="m2">
								<c:if test="${m1.id == m2.pid }">
									<option value="${m2.id }"
										<c:if test="${m2.id == menu.pid }"> selected="selected"</c:if>>&nbsp;&nbsp;&nbsp;&nbsp;----${m2.name }</option>
								</c:if>
							</c:forEach>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-inline layui-btn-group">
				<button class="layui-btn" data-type="serchTable" type="button">查询</button>
				<button class="layui-btn" data-type="insertTable" type="button">新增</button>
			</div>
		</div>
	</form>
	<table id="menuTable" lay-filter="menuTable"></table>
	<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-sm" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="del">删除</a>
	</script>
	<script>
		layui.use(
						'table',
						function() {
							var table = layui.table;
							//方法级渲染
							var initMenuTable = table.render({
								elem : '#menuTable',
								method:'post',
								url : '${ctx }/menu/findData',
								cellMinWidth: 80,
								cols : [ [ //标题栏
								{field : 'id',title : 'ID',width:80, align : 'center'},
								{field : 'name',title : '菜单名称',align : 'center'},
								{field : 'url',title : 'URL',align : 'center'},
								{field : 'pname',title : '上级菜单',align : 'center'},
								{field : 'sort',title : '菜单排序',align : 'center'},
								{fixed : 'right',title : '操作',align : 'center',toolbar : '#barDemo'} ] ],
								id : 'menuTable',
								skin : 'row', //表格风格
								even : true,
								size : 'lg', //尺寸
								page : true, //是否显示分页
								limits : [ 15, 20, 30 ],
								limit : 15, //每页默认显示的数量
								loading : true
							//请求数据时，是否显示loading
							});

							//监听单元格编辑
							table.on('edit(menuTable)', function(obj) {
								var value = obj.value //得到修改后的值
								, data = obj.data //得到所在行所有键值
								, field = obj.field; //得到字段

							});

							//监听工具条
							table.on('tool(menuTable)', function(obj) {
								var data = obj.data;
								if (obj.event === 'detail') {
								} else if (obj.event === 'del') {
									layer.confirm('确定删除菜单[<font color="red">'+data.name+'</font>]吗?', function(index) {
										var indexload = layer.load();
										$.ajax({
											type : 'POST',
											url : "${ctx}/menu/doDelete",
											data : {
												id : data.id
											},
											success : function(info) {
												if (info == 'success') {
													layer.close(indexload);
													obj.del();
													layer.msg('删除成功！');
												} else {
													layer.msg('删除失败！');
												}
											}
										});
										layer.close(index);
									});
								} else if (obj.event === 'edit') {
									var data = obj.data;
									$('#id').val(data.id);
									$('#name').val(data.name);
									$('#url').val(data.url);
									$('#pid').val(data.pid);
									$('#sort').val(data.sort);
									layer.open({
									      type: 1,
									      title: '编辑菜单[<font color="red">'+data.name+'</font>]信息',
											maxmin: true, 
											shadeClose: true, //点击遮罩关闭层
									      area : ['400px' , '400px'],
									      content:$('#list_to_edit'),
									      btn: ['保存','关闭'],
									      yes: function(index, layero){
												$.ajax({
												     type: 'POST',
												     url: "${ctx}/menu/doEditOrAdd" ,
												     data: $('form[name="editMenuForm"]').serialize() ,
												     success: function(result){
												    	 if(result == 'success'){
												    		 layer.msg('操作成功',function(){
																	initMenuTable.reload({
																		where : {
																			mname : $('#mname').val(),
																			pid : $('#mpid').val()
																		}
																	});
												    		 });
												    	 }else{
												    		 layer.msg('操作失败');
												    	 }
												     },error:function(){
												    	 layer.msg('操作失败');
												     }
												});
												layer.close(index);
											}
									  });
								}
							});
							var $ = layui.jquery, active = {
								serchTable : function() {
									initMenuTable.reload({
										where : {
											mname : $('#mname').val(),
											pid : $('#mpid').val()
										}
									});
								},
								insertTable : function() {
									layer.open({
									      type: 1,
									      title: '添加菜单',
											maxmin: true, 
											shadeClose: true, //点击遮罩关闭层
									      area : ['400px' , '400px'],
									      content:$('#list_to_edit'),
									      btn: ['保存','关闭'],
									      yes: function(index, layero){
												$.ajax({
												     type: 'POST',
												     url: "${ctx}/menu/doEditOrAdd" ,
												     data: $('form[name="editMenuForm"]').serialize() ,
												     success: function(result){
												    	 if(result == 'success'){
												    		 layer.msg('操作成功',function(){
																	initMenuTable.reload({
																		where : {
																			mname : $('#mname').val(),
																			pid : $('#mpid').val()
																		}
																	});
												    		 });
												    	 }else{
												    		 layer.msg('操作失败');
												    	 }
												     },error:function(){
												    	 layer.msg('操作失败');
												     }
												});
												layer.close(index);
											}
									  });
								}
							};
							$('.layui-btn').on('click', function() {
								var type = $(this).data('type');
								active[type] ? active[type].call(this) : '';
							});
						});
	</script>
	<div id="list_to_edit" hidden="">
<form class="layui-form layui-form-pane1" name="editMenuForm">
<input type="hidden" id="id" name="id" class="layui-input">
  <div class="layui-form-item">
    <div class="layui-inline" style="margin-top: 10px;">
				<label class="layui-form-label">菜单名称</label>
				<div class="layui-input-inline">
					<input type="text" id="name" name="name" autocomplete="off"
						class="layui-input">
				</div>
			</div>
    <div class="layui-inline">
      <label class="layui-form-label">URL</label>
      <div class="layui-input-inline">
        <input type="text" name="url"  id="url" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">上级菜单</label>
      <div class="layui-input-inline">
      <select name="pid" id="pid" >
			<option value="">----全部----</option>
			<option value="0" <c:if test="${0 == menu.pid }"> selected="selected"</c:if>>===顶级===</option>
			<c:forEach items="${parentMenu1 }" var="m1">
				<option value="${m1.id }"
					<c:if test="${m1.id == menu.pid }"> selected="selected"</c:if>>${m1.name }</option>
				<c:forEach items="${parentMenu2 }" var="m2">
					<c:if test="${m1.id == m2.pid }">
						<option value="${m2.id }"
							<c:if test="${m2.id == menu.pid }"> selected="selected"</c:if>>&nbsp;&nbsp;&nbsp;&nbsp;----${m2.name }</option>
					</c:if>
				</c:forEach>
			</c:forEach>
		</select>
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">菜单排序</label>
      <div class="layui-input-inline">
        <input type="text" name="sort" id="sort"  autocomplete="off" class="layui-input">
      </div>
    </div>
  </div>
</form>
	</div>
</body>
</html>