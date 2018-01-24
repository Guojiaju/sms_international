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
            <label class="layui-form-label">企业名称</label>
            <div class="layui-input-inline">
                <input type="text" id="company" name="company" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">Uid</label>
            <div class="layui-input-inline">
                <input type="tel" id="uid" name="uid" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-inline">
                <select id="stat" name="stat" class="layui-select">
                    <option value="">全部</option>
                    <option value="0">已到账</option>
                    <option value="1">未到账</option>
                </select>
            </div>
        </div>
    </div>
</form>
<div class="layui-input-block">
    <button class="layui-btn" data-type="serchTable">查询</button>
</div>
<table id="recordsTable" lay-filter="recordsTable"></table>
<div id="payStatDiv" style="margin-top: 5px;display: none;">
    <form class="layui-form">
        <input hidden id ="id">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">到账说明</label>
                <div class="layui-input-block">
                    <textarea id="info" lay-verify="required" name="info" class="layui-textarea"></textarea>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" type="button" data-type="updatePayStat">立即提交</button>
            </div>
        </div>
    </form>
</div>
<script type="text/html" id="barDemo">
    {{#  if(d.stat == 1){ }}
        <shiro:hasRole name="finance">
            <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="pay">到账</a>
        </shiro:hasRole>
    {{#  } }}
</script>
<script type="text/html" id="chargeTypeTemp">
    {{# if(d.chargeType == 0){ }}
        人工充值
    {{# } else if(d.chargeType){ }}
        系统返还
    {{# } else{ }}
        其他
    {{# } }}
</script>
<script type="text/html" id="statusTemp">
    {{# if(d.stat == 0){ }}
        已到账
    {{# } else { }}
        未到账
    {{# } }}
</script>
<script>
    layui.use(['table','layer','jquery'], function () {
        var table = layui.table,
                layer = layui.layer
                $ = layui.jquery;
        //方法级渲染
        var initUserTable = table.render({
            elem: '#recordsTable',
            method: 'post',
            url: '${ctx }/chargeRecords/findAll',
            cellMinWidth: 80,
            cols: [[ //标题栏
                {field: 'uid', title: '用户', width : 60, align: 'center'},
                {field: 'company', title: '企业名称', align: 'center'}
                , {field: 'view_amount', title: '充值金额', align: 'center'}
                , {field: 'create_time', title: '充值日期', align: 'center'}
                , {field: 'chargeType', title: '充值方式', align: 'center' ,templet:'#chargeTypeTemp'}
                , {field: 'status', title: '是否到账',  align: 'center',templet:'#statusTemp'}
                , {field: 'info', title: '到账说明',  align: 'center'}
                , {field: 'add_uid', title: '操作人',  align: 'center'}
                , {fixed: 'right', title: '操作', align: 'center', toolbar: '#barDemo'}
            ]]

            , id: 'recordsTable'
            , skin: 'row' //表格风格
            , even: true
            , size: 'lg' //尺寸
            , page: true //是否显示分页
            , limits: [10, 15, 20]
            , limit: 15 //每页默认显示的数量
            , loading: true //请求数据时，是否显示loading
        });

        //监听单元格编辑
        table.on('edit(recordsTable)', function (obj) {
            var value = obj.value //得到修改后的值
                    , data = obj.data //得到所在行所有键值
                    , field = obj.field; //得到字段
        });

        //监听工具条
        table.on('tool(recordsTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'pay') {
                $("#id").val(data.id);
                $("#info").val(data.info);
                layer.open({
                    type: 1,
                    title: '到账',
                    skin: 'layui-layer-rim', //加上边框
                    area: ['350px', '250px'], //宽高
                    content: $('#payStatDiv')
                })
            }
        });

        var active = {
            serchTable: function () {
                initUserTable.reload({
                    where: {
                        company: $('#company').val(),
                        uid: $('#uid').val(),
                        stat: $('#stat').val()
                    }
                });
            },
            updatePayStat: function () {
                if(!$("#info").val()){
                    layer.alert("请输入到账说明！");
                    return false;
                }
                layer.confirm("确定到账?", function (index) {
                    $.ajax({
                        type: 'post',
                        datatype: 'json',
                        contentType: 'application/json',
                        url: '${ctx}/chargeRecords/updatePayStat',
                        data : JSON.stringify({'id':$("#id").val(),'stat':0,'info':$("#info").val()}),
                        success: function (result) {
                            layer.msg(result > 0 ? "操作成功" : "操作失败", {time: 2000}, function () {
                                layer.closeAll();
                                initUserTable.reload({
                                    where: {
                                        company: $('#company').val(),
                                        stat: $('#stat').val(),
                                        uid: $('#uid').val()
                                    }
                                });
                            });
                        },
                        error: function (result) {

                        }
                    });
                })
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