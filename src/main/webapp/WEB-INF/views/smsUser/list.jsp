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
            <label class="layui-form-label">UID</label>
            <div class="layui-input-inline">
                <input type="text" id="id" name="id" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">企业名称</label>
            <div class="layui-input-inline">
                <input type="text" id="company" name="company" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">联系电话</label>
            <div class="layui-input-inline">
                <input type="number" id="phone" name="phone" class="layui-input">
            </div>
        </div>

    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">账号状态</label>
            <div class="layui-input-inline">
                <select id="stat" name="stat" class="layui-select">
                    <option value="">全部</option>
                    <option value="1">有效</option>
                    <option value="2">无效</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">父账号ID:</label>
            <div class="layui-input-inline">
                <input type="number" id="parentId" name="parentId" class="layui-input">
            </div>
        </div>
        <div class="layui-input-block layui-btn-group">
            <button class="layui-btn" type="button" data-type="serchTable">查询</button>
            <%--<button class="layui-btn" type="button" data-type="add">新增</button>--%>
        </div>
    </div>
</form>
<table id="userTable" lay-filter="userTable"></table>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-sm" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-sm" lay-event="edit">修改</a>
    <a class="layui-btn layui-btn-sm" lay-event="control">控制</a>
    <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="offOrOn">
        {{# if(d.stat ==1){ }}
            禁用
        {{# } else{ }}
            启用
        {{# } }}
    </a>
    <shiro:hasRole name="finance">
        <a class="layui-btn layui-btn-sm" lay-event="charge">充值</a>
    </shiro:hasRole>
    <a class="layui-btn layui-btn-sm layui-btn-sm" lay-event="resourcesConfig">资源配置</a>
</script>
<script type="text/html" id="view_stat">
    {{# if(d.stat == 1){ }}
        <font color="green">有效</font>
    {{# } else { }}
        <font color="red">无效</font>
    {{# } }}
</script>
<script>
    layui.use('table', function () {
        var table = layui.table,
                layer = layui.layer;
        //方法级渲染
        var initUserTable = table.render({
            elem: '#userTable',
            method: 'post',
            url: '${ctx }/smsUser/findData',
            cellMinWidth: 80,
            cols: [[ //标题栏
                {field: 'id', title: 'UID',width:60, align: 'center',fixed: 'left'}
                , {field: 'company', title: '企业名称',align: 'center'}
                , {field: 'sms', title: '余额', align: 'center'}
                , {field: 'phone', title: '联系电话', align: 'center'}
                , {field: 'linkman', title: '联系人',align: 'center'}
                , {field: 'stat', title: '状态', align: 'center',templet:'#view_stat'}
                , {field: 'time', title: '创建时间', align: 'center'}
                , {fixed: 'right', title: '操作', width: 380, align: 'center', toolbar: '#barDemo'}
            ]]

            , id: 'userTable'
            , skin: 'row' //表格风格
            , even: true
            , size: 'lg' //尺寸
            , page: true //是否显示分页
            , limits: [10, 15, 20]
            , limit: 15 //每页默认显示的数量
            , loading: true //请求数据时，是否显示loading
        });

        //监听表格复选框选择
        table.on('checkbox(userTable)', function (obj) {
            console.log(obj)
        });

        //监听单元格编辑
        table.on('edit(userTable)', function (obj) {
            var value = obj.value //得到修改后的值
                    , data = obj.data //得到所在行所有键值
                    , field = obj.field; //得到字段

        });

        //监听工具条
        table.on('tool(userTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                layer.open({
                    type: 2,
                    title: '账号详情',
                    skin: 'layui-layer-rim', //加上边框
                    area: ['900px', '500px'], //宽高
                    content: '${ctx}/smsUser/initDetail/' + data.id
                })
            } else if (obj.event === 'offOrOn') {
                var stat = data.stat;
                layer.confirm(stat == 1 ? "确定禁用该账号吗？" : "确定启用该账号吗？", function (index) {
                    $.ajax({
                        url: '${ctx}/smsUser/enableOrDisable',
                        type: 'post',
                        contentType: 'application/json',
                        data: JSON.stringify({'id': data.id, 'stat': stat == 1 ? 2 : 1}),
                        dataType: 'json',
                        success: function (result) {
                            layer.msg(result >= 0 ? "操作成功" : "操作失败", {time: 2000}, function () {
                                initUserTable.reload({
                                    where: {
                                        id : $('#id').val(),
                                        company: $('#company').val(),
                                        phone: $('#phone').val(),
                                        stat: $('#stat').val(),
                                        parentId : $("#parentId").val()
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
                    title: '编辑用户信息',
                    skin: 'layui-layer-rim', //加上边框
                    area: ['750px', '500px'], //宽高
                    content: '${ctx}/smsUser/toAddOrEdit/' + data.id + "/0"
                })
            } else if (obj.event === 'resourcesConfig') {
                layer.open({
                    type: 2,
                    title: '资源配置',
                    skin: 'layui-layer-rim', //加上边框
                    area: ['780px', '500px'], //宽高
                    content: '${ctx}/smsUser/initResources/' + data.id
                })
            } else if (obj.event === 'control') {
                layer.open({
                    type: 2,
                    title: '修改控制信息',
                    skin: 'layui-layer-rim', //加上边框
                    area: ['900px', '500px'], //宽高
                    content: '${ctx}/smsUser/initControl/' + data.id
                })
            } else if (obj.event === 'charge'){
                layer.open({
                    type: 2,
                    title: '充值',
                    skin: 'layui-layer-rim', //加上边框
                    area: ['700px', '400px'], //宽高
                    content: '${ctx}/smsUser/initCharge/' + data.id
                })
            }
        });
        var $ = layui.jquery, active = {
            getCheckData: function () {
                var checkStatus = table.checkStatus('userTable')
                        , data = checkStatus.data;
                var arr = [];
                for (var i in data) {
                    arr.push(data[i]["id"]);
                }
            },
            serchTable: function () {
                initUserTable.reload({
                    where: {
                        id : $('#id').val(),
                        company: $('#company').val(),
                        phone: $('#phone').val(),
                        stat: $('#stat').val(),
                        parentId : $("#parentId").val()
                    }
                });
            },
            add: function () {
                layer.open({
                    type: 2,
                    title: '编辑用户信息',
                    skin: 'layui-layer-rim', //加上边框
                    area: ['750px', '500px'], //宽高
                    content: '${ctx}/smsUser/toAddOrEdit/0'
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