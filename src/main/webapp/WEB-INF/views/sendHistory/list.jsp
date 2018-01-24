<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <label class="layui-form-label">uid</label>
            <div class="layui-input-inline">
                <input type="text" id="uid" name="uid" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">手机号码</label>
            <div class="layui-input-inline">
                <input type="tel" id="mobile" name="mobile" lay-verify="phone" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">内容</label>
            <div class="layui-input-inline">
                <input type="text" id="content" name="content" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">提交状态</label>
            <div class="layui-input-inline">
                <select name="stat" id="stat">
                    <option value="">::不分::</option>
                    <option value="1">成功</option>
                    <option value="-1">失败</option>
                    <option value="0">待发</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">回执状态</label>
            <div class="layui-input-inline">
                <select name="succ" id="succ">
                    <option value="">::不分::</option>
                    <option value="1">成功</option>
                    <option value="-1">失败</option>
                    <option value="0">未知</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">通道</label>
            <div class="layui-input-inline">
                <select name="channel" id="channel">
                    <option value="">全部</option>
                    <c:forEach items="${channel}" var="channel" >
                        <option value="${channel.id}"><c:if test="${channel.status != 0}"><font color="red">(停止)</font></c:if>${channel.channelName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">归属地</label>
            <div class="layui-input-inline">
                <select name="location" id="location" lay-search>
                    <option value="">全球</option>
                    <c:forEach items="${country}" var="country" >
                        <option value="${country.id}">${country.country}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">开始时间</label>
            <div class="layui-input-inline">
                <input type="text" name="startTime" value="" id="startTime" lay-verify="date" placeholder="yyyyMMdd000000" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">结束时间</label>
            <div class="layui-input-inline">
                <input type="text" name="endTime" id="endTime" lay-verify="date" placeholder="yyyyMMdd000000" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">数据源</label>
            <div class="layui-input-inline">
                <select name="records" id="records" lay-search>
                    <option value="1" selected>3日内</option>
                    <option value="2">3日前</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-input-block">
                <button class="layui-btn" type="button" data-type="serchTable">查询</button>
            </div>
        </div>
    </div>
</form>
<table id="customerTable" lay-filter="customerTable" class="layui-hide"></table>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-sm" lay-event="detail">详情</a>
</script>
<script type="text/html" id="statTemp">
    {{# if(d.stat == 1){ }}
        成功
    {{# } else if(d.stat == 0){ }}
        <font color="green">待发</font>
    {{# } else{ }}
        <font color="red">失败</font>
    {{# } }}
</script>
<script type="text/html" id="channelTemp">
    <c:forEach items="${channel}" var="channel" >
        {{# if(d.channel == ${channel.id}){ }}
            ${channel.channelName}
        {{# } }}
    </c:forEach>
</script>
<script type="text/html" id="contentLength">
    {{# return d.content.length }}
</script>
<script>
    layui.use(['table','layer','laydate','jquery'], function () {
        var table = layui.table,
            layer = layui.layer,
            laydate = layui.laydate,
                $ = layui.jquery;

        //日期
        laydate.render({
            elem: '#startTime',
            type : 'datetime',
            format : 'yyyyMMddHHmmss',
            value : new Date().getFullYear()+ "" + (new Date().getMonth()+1 < 10 ? "0" + (new Date().getMonth()+1) : new Date().getMonth()+1) +(new Date().getDate() < 10 ? "0" + new Date().getDate() :new Date().getDate() ) + "000000"
        });
        laydate.render({
            elem: '#endTime',
            type : 'datetime',
            format : 'yyyyMMddHHmmss'
        });

        //方法级渲染
        var initUserTable = table.render({
            elem: '#customerTable',
            method: 'post',
            url: '${ctx }/smsSendHistory/findData',
            where : {records:1},
            cellMinWidth: 80,
            cols: [[ //标题栏
              {field: 'viewTime', title: '时间', width:180, align: 'center',fixed: 'left'}
                , {field: 'uid', title: '用户', width:80, align: 'center'}
                , {field: 'mobile', title: '手机号码',width:150,align: 'center'}
                , {field: 'content', title: '内容', minWidth: 250, align: 'center'}
                , {field: 'channel', title: '通道', width: 100, align: 'center' , templet:'#channelTemp'}
                , {field: 'stat', title: '提交状态', width: 100, align: 'center',templet:'#statTemp'}
                , {field: 'contentNum', title:'条', width: 40, align: 'center'}
                , {field: 'contentLen', title: '字', width: 40, align: 'center',templet:'#contentLength'}
                , {field: 'location', title: '归属地', width: 100, align: 'center'}
                , {fixed: 'right', title: '操作',width: 100, align: 'center', toolbar: '#barDemo'}
            ]]
            , id: 'customerTable'
            , skin: 'row' //表格风格
            , even: true
            , size: 'lg' //尺寸
            , page: true //是否显示分页
            , limits: [10, 15, 20]
            , limit: 15 //每页默认显示的数量
            , loading: true //请求数据时，是否显示loading
        });

        //监听单元格编辑
        table.on('edit(customerTable)', function (obj) {
            var value = obj.value //得到修改后的值
                    , data = obj.data //得到所在行所有键值
                    , field = obj.field; //得到字段

        });

        //监听工具条
        table.on('tool(customerTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                layer.open({
                    type: 2,
                    title: '查看详情',
                    skin: 'layui-layer-rim', //加上边框
                    area: ['900px', '500px'], //宽高
                    content: '${ctx}/smsSendHistory/initDetail?uid='+data.uid+"&hisId="+data.id+"&expid="+data.expid+"&senddate="+data.senddate+"&records="+$("#records").val()
                });
            } else if (obj.event === 'offOrOn') {
                var stat = data.stat;
                layer.confirm(stat == 0 ? "确定禁用该客户账号吗？" : "确定启用该客户账号吗？", function (index) {
                    $.ajax({
                        url: '${ctx}/smsCustomer/enableOrDisable',
                        type: 'post',
                        contentType: 'application/json',
                        data: JSON.stringify({'id': data.id, 'stat': stat == 0 ? 1 : 0}),
                        dataType: 'json',
                        success: function (result) {
                            layer.msg(result > 0 ? "操作成功" : "操作失败", {time: 2000}, function () {
                                reRenderTable();
                            });
                        },
                        error: function (result) {
                            layer.alert("系统错误");
                        }
                    });
                    layer.close(index);
                });
            }
        });
        var active = {
            getCheckData: function () {
                var checkStatus = table.checkStatus('customerTable')
                        , data = checkStatus.data;
                var arr = [];
                for (var i in data) {
                    arr.push(data[i]["id"]);
                }
            },
            serchTable: function () {
                reRenderTable();
            },
            addTable: function () {
                layer.open({
                    type: 2,
                    title: '编辑客户信息',
                    skin: 'layui-layer-rim', //加上边框
                    area: ['600px', '500px'], //宽高
                    content: '${ctx}/smsCustomer/toAddOrEdit/0'
                });
            }
        };

        /**
         * 重新加载table
         */
        function reRenderTable(){
            initUserTable.reload({
                where: {
                    uid: $('#uid').val(),
                    mobile: $('#mobile').val(),
                    content : $('#content').val(),
                    stat : $('#stat').val(),
                    succ : $('#succ').val(),
                    channel : $('#channel').val(),
                    location : $('#location').val(),
                    startTime : $('#startTime').val(),
                    endTime : $('#endTime').val(),
                    records : $('#records').val()
                }
            });
        }
        $('.layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
</script>
</body>
</html>