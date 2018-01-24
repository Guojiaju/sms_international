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
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-inline">
                <input type="text" id="username" name="username" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-input-block layui-btn-group">
                <button class="layui-btn" type="button" data-type="serchTable">查询</button>
                <button type="button" class="layui-btn" data-type="add">添加管理员</button>
            </div>
        </div>
    </div>
</form>
<table id="adminTable" lay-filter="adminTable"></table>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-sm" lay-event="edit">修改</a>
    <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="offOrOn">
        {{# if(d.status == 0 ){ }}
            禁用
        {{#  } else{ }}
            启用
        {{# } }}
    </a>
</script>
<script type="text/html" id="statusTemp">
    {{# if(d.status == 0){ }}
        启用
    {{# } else { }}
        禁用
    {{# } }}
</script>
<script>
    layui.use('table', function () {
        var table = layui.table,
                layer = layui.layer;
        //方法级渲染
        var initUserTable = table.render({
            elem: '#adminTable',
            method: 'post',
            url: '${ctx }/admin/findData',
            cellMinWidth: 80,
            cols: [[ //标题栏
               {field: 'id', title: '编号', width:80, align: 'center'}
                , {field: 'username', title: '用户名',align: 'center'}
                , {field: 'realname', title: '姓名', align: 'center'}
                , {field: 'phone', title: '手机号码',align: 'center'}
                , {field: 'roleName', title: '所属部门', align: 'center'}
                , {field: 'createDate', title: '创建日期', align: 'center'}
                , {field: 'status', title: '是否有效',align: 'center',templet:'#statusTemp'}
                , {fixed: 'right', title: '操作', width: 200, align: 'center', toolbar: '#barDemo'}
            ]]

            , id: 'adminTable'
            , skin: 'row' //表格风格
            , even: true
            , size: 'lg' //尺寸
            , page: true //是否显示分页
            , limits: [10, 15, 20]
            , limit: 15 //每页默认显示的数量
            , loading: true //请求数据时，是否显示loading
        });

        //监听表格复选框选择
        table.on('checkbox(adminTable)', function (obj) {
            console.log(obj)
        });

        //监听单元格编辑
        table.on('edit(adminTable)', function (obj) {
            var value = obj.value //得到修改后的值
                    , data = obj.data //得到所在行所有键值
                    , field = obj.field; //得到字段

        });

        //监听工具条
        table.on('tool(adminTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'offOrOn') {
                var status = data.status;
                layer.confirm(status == 0 ? "确定禁用该账号吗？" : "确定启用该账号吗？", function (index) {
                    var url = status == 0 ? "${ctx}/admin/doDisable/" + data.id  : "${ctx}/admin/doEnable/" + data.id ;
                    $.ajax({
                        url: url,
                        type: 'post',
                        contentType: 'application/json',
                        dataType: 'json',
                        success: function (result) {
                            layer.msg(result >= 0 ? "操作成功" : "操作失败", {time: 2000}, function () {
                                initUserTable.reload({
                                    where: {
                                        username: $('#username').val()
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
                    title: '编辑管理员',
                    skin: 'layui-layer-rim', //加上边框
                    area: ['550px', '400px'], //宽高
                    content: '${ctx}/admin/toEditOrAdd/'+data.id
                })
            }
        });
        var $ = layui.jquery, active = {
            serchTable: function () {
                initUserTable.reload({
                    where: {
                        username: $('#username').val()
                    }
                });
            },
            add: function () {
                layer.open({
                    type: 2,
                    title: '编辑管理员',
                    skin: 'layui-layer-rim', //加上边框
                    area: ['550px', '400px'], //宽高
                    content: '${ctx}/admin/toEditOrAdd/0'
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