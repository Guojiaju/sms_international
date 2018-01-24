<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="../common/inc/header.jsp"%>
<%@include file="../common/inc/jscss.jsp"%>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>短信回复</title>
</head>

<body>
<div class="page-content">
    <form id="SmallForm" method="post" class="layui-form">
        <input id="pid" name="pid" type="hidden" value="${obj.pid }"/>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">号码</label>
                <div class="layui-input-inline">
                    <input type="text" id="mobile" name="mobile" autocomplete="off" class="layui-input" value="${obj.uid}" />
                </div>
                <div class="layui-input-inline">
                    <button class="layui-btn" type="button" data-type="searchTable">查询</button>
                </div>
            </div>
        </div>
        <table id="detailDataTable" class="layui-hide" lay-filter="detailDataTable"></table>
    </form>
</div>
<script type="text/html" id="status">
    {{# return '待发送' }}
</script>
<script type="text/javascript">
    var $,active,table,form,layer;
    layui.use(["laytpl", "laypage", "layer", "table", "form"], function () {
        $ = layui.jquery, table = layui.table, form = layui.form, layer = layui.layer;
        //方法级渲染
        var detailDataTable = table.render({
            elem: '#detailDataTable',
            method: 'post',
            url: '${ctx }/timeing/viewMobile',
            cellMinWidth: 80,
            cols: [[
                {field: 'viewSendTime', title: '号码',align:'center'}
                ,{field: 'status', title: '状态', templet: '#status',align:'center'}
            ]]
            , id: 'detailDataTable'
            , skin: 'row' //表格风格
            , even: true
            , size: 'sm' //尺寸
            , page: true //是否显示分页
            , limits: [15, 30, 60]
            , limit: 15 //每页默认显示的数量
            , loading: true
        });
        active = {
            searchTable: function () {
                detailDataTable.reload({
                    where: {
                        mobile: $('#mobile').val(),
                        pid:$("#pid").val()
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
