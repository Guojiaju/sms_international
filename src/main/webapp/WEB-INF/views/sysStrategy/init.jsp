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
        <li data-group="1"class="layui-this">系统黑名单</li>
        <li data-group="2">系统黑签名</li>
        <li data-group="3">审核屏蔽词</li>
        <li data-group="4">自动屏蔽词</li>
        <li data-group="5">屏蔽词类型</li>
        <li data-group="6">黑名单类型</li>
        <li data-group="7">策略组设置</li>
    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show" data-url="/smsBlackMobile/init"><%@include file="blackMobiles/list.jsp"%></div>
        <div class="layui-tab-item" data-url="#"></div>
        <div class="layui-tab-item" data-url="/blackWords/initReleaseWords"></div>
        <div class="layui-tab-item" data-url="#"></div>
        <div class="layui-tab-item" data-url="#"></div>
        <div class="layui-tab-item" data-url="#"></div>
        <div class="layui-tab-item" data-url="#"></div>
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
            $.post('${ctx}'+ contentArea.attr('data-url'),{},function (res) {
                //清空其他tab页面内容
                $(".layui-tab-item").each(function(){
                    $(this).html("");
                });
                contentArea.html(res);
            });
            return false;
        });
    });
</script>
</body>
</html>