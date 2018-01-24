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
            <label class="layui-form-label">客户名称</label>
            <div class="layui-input-inline">
                <input type="text" id="cname" name="cname" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">联系电话</label>
            <div class="layui-input-inline">
                <input type="tel" id="phone" name="phone" lay-verify="phone" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">账号状态</label>
            <div class="layui-input-inline">
                <select id="stat" name="stat" class="layui-select">
                    <option value="">全部</option>
                    <option value="0">有效</option>
                    <option value="1">无效</option>
                </select>
            </div>
        </div>
    </div>
</form>
<div class="layui-input-block layui-btn-group">
    <button class="layui-btn" data-type="serchTable">查询</button>
    <button class="layui-btn" data-type="addTable">新增</button>
</div>
<table id="customerTable" lay-filter="customerTable"></table>
<script type="text/html" id="barDemo">
<%--
    <a class="layui-btn layui-btn-primary layui-btn-sm" lay-event="detail">查看</a>
--%>
    <a class="layui-btn layui-btn-sm" lay-event="edit">编辑</a>
    <%--<a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="offOrOn">
        {{# if(d.stat ==0){ }}
            禁用
        {{# } else{ }}
            启用
        {{# } }}
    </a>--%>
    <a class="layui-btn layui-btn-sm" lay-event="openAccount">开户</a>
</script>
<script type="text/html" id="statTemp">
    {{# if(d.stat ==0){ }}
        有效
    {{# } else{ }}
        无效
    {{# } }}
</script>
<script>
    layui.use('table', function () {
        var table = layui.table;
        //方法级渲染
        var initUserTable = table.render({
            elem: '#customerTable',
            method: 'post',
            url: '${ctx }/smsCustomer/findAll',
            cellMinWidth: 80,
            cols: [[ //标题栏
                {checkbox: true}
                , {field: 'id', title: 'ID', width:100,align: 'center'}
                , {field: 'cname', title: '客户名称', align: 'center'}
                , {field: 'phone', title: '联系电话', align: 'center'}
                , {field: 'link_man', title: '联系人', align: 'center'}
                , {field: 'stat', title: '状态', align: 'center',templet:'#statTemp'}
                , {field: 'create_date', title: '创建时间', align: 'center'}
                , {fixed: 'right', title: '操作', align: 'center', toolbar: '#barDemo'}
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

        //监听表格复选框选择
        table.on('checkbox(customerTable)', function (obj) {
            console.log(obj)
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
                layer.msg('ID：' + data.id + ' 的查看操作');
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
                                initUserTable.reload({
                                    where: {
                                        company: $('#company').val(),
                                        phone: $('#phone').val(),
                                        stat: $('#stat').val()
                                    }
                                });
                            });
                        },
                        error: function (result) {
                            layer.alert("系统错误");
                        }

                    });
                    layer.close(index);
                });
            } else if (obj.event === 'edit') {
                layer.open({
                    type: 2,
                    title: '编辑客户信息',
                    skin: 'layui-layer-rim', //加上边框
                    area: ['600px', '500px'], //宽高
                    //content: $('#addOrUpdate')
                    content: '${ctx}/smsCustomer/toAddOrEdit/' + data.id
                });
            } else if (obj.event === 'openAccount'){
                layer.open({
                    type: 2,
                    title: '添加账号',
                    skin: 'layui-layer-rim', //加上边框
                    area: ['750px', '500px'], //宽高
                    content: '${ctx}/smsUser/toAddOrEdit/0/'+ data.id
                });
            }
        });
        var $ = layui.jquery, active = {
            getCheckData: function () {
                var checkStatus = table.checkStatus('customerTable')
                        , data = checkStatus.data;
                var arr = [];
                for (var i in data) {
                    arr.push(data[i]["id"]);
                }
            },
            serchTable: function () {
                initUserTable.reload({
                    where: {
                        cname: $('#cname').val(),
                        phone: $('#phone').val(),
                        stat : $('#stat').val()
                    }
                });
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
        $('.layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
</script>
</body>
</html>