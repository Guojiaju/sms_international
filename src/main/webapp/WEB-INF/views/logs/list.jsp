<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1">
    <%@include file="../common/inc/jscss.jsp" %>
    <style>
        body {
            padding: 10px;
        }
    </style>
</head>
<body>
<form class="layui-form">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">日志类型</label>
            <div class="layui-input-inline">
                <select name="type" id="type">
                    <option value="1">管理员日志</option>
                    <option value="2">用户日志</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">内容</label>
            <div class="layui-input-inline">
                <input type="tel" id="log" name="log" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-input-block layui-btn-group">
                <button class="layui-btn" type="button" data-type="serchTable">查询</button>
            </div>
        </div>
    </div>
</form>
<table id="logTable" lay-filter="logTable"></table>
<script type="text/html" id="timeTemp">
    {{ covertDate(d.time)}}
    {{# function covertDate (time){
            var date = new Date();
            date.setTime(time['time']);
            return date.getFullYear() + "-" + (date.getMonth()+1) + "-" + (date.getDate() <10 ? "0"+date.getDate() : date.getDate()) + " "
                   + date.getHours() +":"+ date.getMinutes() + ":" + date.getSeconds();
        }
    }}
</script>
<script>
    layui.use(['table','layer','jquery'], function () {
        var table = layui.table,
                layer = layui.layer,
         $ = layui.jquery;
        //方法级渲染
        var initUserTable = table.render({
            elem: '#logTable',
            method: 'post',
            url: '${ctx }/adminLogs/findAll',
            cellMinWidth: 80,
            cols: [[ //标题栏
                {field: 'time', title: '操作时间', width:'25%',align: 'center',templet:'#timeTemp'}
                , {field: 'ip', title: 'IP', width:'20%',  align: 'center'}
                , {field: 'user', title: '操作者', width:'10%' ,align: 'center'}
                , {field: 'log', title: '日志内容',width:'45%' ,align: 'center'}
            ]]

            , id: 'logTable'
            , skin: 'row' //表格风格
            , even: true
            , size: 'lg' //尺寸
            , page: true //是否显示分页
            , limits: [10, 15, 20]
            , limit: 15 //每页默认显示的数量
            , loading: true //请求数据时，是否显示loading
        });

        //监听单元格编辑
        table.on('edit(logTable)', function (obj) {
            var value = obj.value //得到修改后的值
                    , data = obj.data //得到所在行所有键值
                    , field = obj.field; //得到字段

        });

        var active = {
            serchTable: function () {
                initUserTable.reload({
                    where: {
                        type: $('#type').val(),
                        log: $('#log').val(),
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
</body>
</html>