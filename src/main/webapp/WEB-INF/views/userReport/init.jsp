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
<div class="layui-tab layui-tab-brief" lay-filter="strategyTab">
    <ul class="layui-tab-title">
        <li data-group="1"class="layui-this">用户日报表</li>
        <li data-group="2">用户月报表</li>
        <li data-group="3">用户账单</li>
    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show" data-url="/userReport/initUserDay"><%@include file="user_day_report.jsp"%></div>
        <div class="layui-tab-item" data-url="/userReport/initUserMonth"></div>
        <div class="layui-tab-item" data-url="/userReport/initUserBill"></div>
    </div>
</div>
<script>
    //注意：选项卡 依赖 element 模块，否则无法进行功能性操作
    layui.use(['element','jquery'], function(){
        var element = layui.element,
                $ = layui.jquery;

        //监听tab的点击事件
        element.on('tab(strategyTab)',function(data){
            //获取当前选项卡下的内容并清空
            var contentArea = $(".layui-show");
            contentArea.html("");
            $.post('${ctx}'+ contentArea.attr('data-url'),{},function (res) {
                contentArea.html(res);
            });
            return false;
        });
    });
</script>
</body>
</html>