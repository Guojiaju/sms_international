<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">角色名称</label>
				<div class="layui-input-inline">
					<input type="text" id="roleName" name="roleName" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-inline layui-btn-group">
				<button class="layui-btn" data-type="serchTable" type="button">查询</button>
				<button class="layui-btn"  type="button" onclick="javascript:window.location.href='${ctx }/role/toEditOrAdd/0';">新增</button>
			</div>
		</div>
		<table id="roleTable" lay-filter="roleTable"></table>
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
							var initroleTable = table.render({
								elem : '#roleTable',
								method:'post',
								url : '${ctx }/role/findData',
								cellMinWidth: 80,
								cols : [ [ //标题栏
								{field : 'id',title : 'ID',width:80,align : 'center'},
								{field : 'roleName',title : '角色名称',align : 'center'},
								{field : 'description',title : '角色英文',align : 'center'},
								{field : 'createDate',title : '创建时间',align : 'center'},
								{fixed : 'right',title : '操作',align : 'center',toolbar : '#barDemo'} ] ],
								id : 'roleTable',
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
							table.on('edit(roleTable)', function(obj) {
								var value = obj.value //得到修改后的值
								, data = obj.data //得到所在行所有键值
								, field = obj.field; //得到字段

							});

							//监听工具条
							table.on('tool(roleTable)', function(obj) {
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
									window.location.href = "${ctx }/role/toEditOrAdd/"+data.id;
								}
							});
							var $ = layui.jquery, active = {
								serchTable : function() {
									initroleTable.reload({
										where : {
											roleName : $('#roleName').val()
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
</body>
</html>