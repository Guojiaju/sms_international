<%--
  Created by IntelliJ IDEA.
  User: guojiaju
  Date: 2017/11/22
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
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
<%--<%@ include file="../common/inc/header.jsp"%>--%>
<div class="layui-tab layui-tab-brief" lay-filter="detailTab">
    <ul class="layui-tab-title">
        <li class="layui-this">账号信息</li>
        <li>回访记录</li>
        <li>充值记录</li>
        <li>消费记录</li>
    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show" data-type="text" data-url="/smsUser/detail/${id}"></div>
        <div class="layui-tab-item" data-url="#"></div>
        <div class="layui-tab-item" data-url="/chargeRecords/initRecords/${id}"></div>
        <div class="layui-tab-item" data-url="/consume/initConsume/${id}"></div>
    </div>
</div>
<script>
    window.onload = function () {
        $('.layui-this').click();
    }
    //注意：选项卡 依赖 element 模块，否则无法进行功能性操作
    layui.use(['element','jquery'], function(){
        var element = layui.element,
                $ = layui.jquery;

        //监听tab的点击事件
        element.on('tab(detailTab)',function(data){
            //获取当前选项卡下的内容并清空
            var contentArea = $(".layui-show");
            contentArea.html("");
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

