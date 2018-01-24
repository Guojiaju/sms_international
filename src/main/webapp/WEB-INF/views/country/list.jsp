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
            <label class="layui-form-label">国家名称</label>
            <div class="layui-input-inline">
                <input type="text" id="country" name="country" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">国家代码</label>
            <div class="layui-input-inline">
                <input type="number" id="countryCode" name="countryCode" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-input-block layui-btn-group">
                <button class="layui-btn" type="button" data-type="serchTable">查询</button>
                <button class="layui-btn" type="button" data-type="add">新增</button>
            </div>
        </div>
    </div>
</form>
<table id="countryTable" lay-filter="countryTable"></table>
<%--<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-sm" lay-event="edit">编辑</a>
</script>--%>
<script>
    layui.use('table', function () {
        var table = layui.table;
        //方法级渲染
        var initUserTable = table.render({
            elem: '#countryTable',
            method: 'post',
            url: '${ctx }/country/findAll',
            cellMinWidth: 80,
            cols: [[ //标题栏
                 {field: 'id', title: 'ID',align: 'center'}
                , {field: 'country', title: '国家或地区', align: 'center'}
                , {field: 'country_en', title: 'Countries and Regions', align: 'center'}
                , {field: 'country_ab', title: '国际域名缩写', align: 'center'}
                , {field: 'countryCode', title: '电话代码', align: 'center'}
/*
                , {fixed: 'right', title: '操作', width: 100, align: 'center', toolbar: '#barDemo'}
*/
            ]]

            , id: 'countryTable'
            , skin: 'row' //表格风格
            , even: true
            /*, size: 'lg' //尺寸*/
            , page: true //是否显示分页
            , limits: [10, 15, 20]
            , limit: 15 //每页默认显示的数量
            , loading: true //请求数据时，是否显示loading
        });

        var $ = layui.jquery, active = {
            serchTable: function () {
                initUserTable.reload({
                    where: {
                        country: $('#country').val(),
                        countryCode: $('#countryCode').val(),
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