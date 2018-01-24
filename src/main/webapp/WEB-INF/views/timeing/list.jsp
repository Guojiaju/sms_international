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
		<div class="page-content">
			<form id="searchForm" method="post" class="layui-form">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">用户ID</label>
                        <div class="layui-input-inline">
                            <input type="text" id="uid" name="uid" autocomplete="off" class="layui-input" value="${obj.uid}" />
                        </div>
                        <label class="layui-form-label">手机号码</label>
                        <div class="layui-input-inline">
                            <input type="text" id="mobile" name="mobile" autocomplete="off" class="layui-input" value="${obj.mobile}" />
                        </div>
                        <label class="layui-form-label">内容</label>
                        <div class="layui-input-inline">
                            <input type="text" id="content" name="content" autocomplete="off" class="layui-input" value="${obj.content}" />
                        </div>
                        <div class="layui-input-inline">
                            <button class="layui-btn" type="button" data-type="searchTable">查询</button>
                        </div>
                    </div>
                </div>
                <table id="dataTable" class="layui-hide" lay-filter="dataTable"></table>
			</form>
    	</div>
    </body>
    <script type="text/html" id="barDemo">
        <button type="button" class="layui-btn" lay-event="viewMobile">查看号码</button>
		<button type="button" class="layui-btn" lay-event="clearTimeing">取消定时</button>
    </script>
<script type="text/javascript">
    var $,active,table,form,layer;
    layui.use(["laytpl", "laypage", "layer", "table", "form"], function () {
        $ = layui.jquery, table = layui.table, form = layui.form, layer = layui.layer;
        //方法级渲染
        var dataTable = table.render({
            elem: '#dataTable',
            method: 'post',
            url: '${ctx }/timeing/findAll',
            cellMinWidth: 80,
            cols: [[
                {field: 'viewSendTime', title: '发送时间',align:'center'}
                ,{field: 'mobileNum', title: '号码数量',align:'center'}
                ,{field: 'content', title: '发送内容',minWidth:250,align:'center'}
                ,{field: 'viewSubmitTime', title: '提交时间',align:'center'}
                ,{fixed: 'right', title: '操作', toolbar: '#barDemo',align:'center'}
            ]]
            , id: 'dataTable'
            , skin: 'row' //表格风格
            , even: true
            , size: 'lg' //尺寸
            , page: true //是否显示分页
            , limits: [15, 30, 60]
            , limit: 15 //每页默认显示的数量
            , loading: true
        });
        table.on('tool(dataTable)', function(obj){
            var data = obj.data
            ,layEvent = obj.event;
            if(layEvent === 'viewMobile'){
                layer.open({
                    type: 2,
                    title: '号码详情',
                    maxmin: true,
                    shadeClose: true,
                    area : ['880px' , '600px'],
                    content:'${ctx }/timeing/initviewMobile?pid = ' + data.pid,
                });
            }else if(layEvent === 'clearTimeing'){
                layer.confirm('您确定取消该批次定时短信吗？', {
                    btn: ['是', '否']
                }, function () {
                    $.ajax({
                        type:"POST",
                        url: '${ctx }/timeing/clearTimeing?pid=' + data.pid,
                        success: function (result) {
                            layer.alert(result+"\r\n",{title: '提示框',icon:0});
                            active.searchTable().call();
                        },
                        error: function (result) {
                            layer.alert("系统错误\r\n",{title: '提示框',icon:0});
                        }
                    });
                });
            }
        });
        active = {
            searchTable: function () {
                dataTable.reload({
                    where: {
                        uid: $('#uid').val(),
                        mobile: $('#mobile').val(),
                        content: $('#content').val()
                    }
                });
            }
        };
        $('.layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
</script>
</html>