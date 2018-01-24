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
            <label class="layui-form-label">模板名称</label>
            <div class="layui-input-inline">
                <input type="text" id="name" name="name" class="layui-input">
            </div>
        </div>
       <!--  <div class="layui-inline">
            <label class="layui-form-label">模板描述</label>
            <div class="layui-input-inline">
                <input type="tel" id="description" name="description" class="layui-input">
            </div>
        </div> -->
        <div class="layui-inline">
            <label class="layui-form-label">模板状态</label>
            <div class="layui-input-inline">
                <select id="status" name="status" class="layui-select">
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
<table id="templateTable" lay-filter="templateTable"></table>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-sm" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="remove">删除</a>
    <a class="layui-btn layui-btn-sm layui-btn-sm" lay-event="resourcesConfig">资源配置</a>
</script>
<script type="text/html" id="statTemp">
    {{# if(d.status ==0){ }}
        <font color="green">有效</font>
    {{# } else{ }}
        <font color="red">无效</font>
    {{# } }}
</script>
<script>
    layui.use('table', function () {
        var table = layui.table;
        //方法级渲染
        var initUserTable = table.render({
            elem: '#templateTable',
            method: 'post',
            url: '${ctx }/priceTemplate/findAll',
            cellMinWidth: 80,
            cols: [[ //标题栏
                {field: 'id', title: 'ID', width:100,align: 'center'}
                , {field: 'name', title: '模板名称', align: 'center'}
                , {field: 'description', title: '模板描述', align: 'center'}
                , {field: 'status', title: '状态', align: 'center',templet:'#statTemp'}
                , {field: 'addTime', title: '创建时间', align: 'center'}
                , {fixed: 'right', title: '操作', width:220, align: 'center', toolbar: '#barDemo'}
            ]]

            , id: 'templateTable'
            , skin: 'row' //表格风格
            , even: true
            , size: 'lg' //尺寸
            , page: true //是否显示分页
            , limits: [10, 15, 20]
            , limit: 15 //每页默认显示的数量
            , loading: true //请求数据时，是否显示loading
        });

        //监听表格复选框选择
        table.on('checkbox(templateTable)', function (obj) {
            console.log(obj)
        });

        //监听单元格编辑
        table.on('edit(templateTable)', function (obj) {
            var value = obj.value //得到修改后的值
                    , data = obj.data //得到所在行所有键值
                    , field = obj.field; //得到字段

        });

        //监听工具条
        table.on('tool(templateTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'resourcesConfig') {
                layer.open({
                    type: 2,
                    title: '模板配置',
                    skin: 'layui-layer-rim', //加上边框
                    area: ['780px', '500px'], //宽高
                    //content: $('#addOrUpdate')
                    content: '${ctx}/priceTemplate/initResources/' + data.id
                });
            }  else if (obj.event === 'edit') {
                layer.open({
                    type: 2,
                    title: '编辑模板信息',
                    skin: 'layui-layer-rim', //加上边框
                    area: ['400px', '300px'], //宽高
                    //content: $('#addOrUpdate')
                    content: '${ctx}/priceTemplate/initEdit/' + data.id
                });
            } else if(obj.event === 'remove'){
                layer.confirm("确定删除该模板吗？",function () {
                    $.ajax({
                        type:'post',
                        dataType :'json',
                        contentType : 'application/json',
                        url:'${ctx}/priceTemplate/remove/'+ data.id,
                        data : JSON.stringify(data),
                        success : function(result){
                            layer.msg(result > 0 ? "删除成功" : "删除失败", {time: 2000}, function () {
                                reloadTable();
                            });
                        },
                        error:function(){
                            layer.msg("系统错误", {time: 2000});
                        }
                    });
                })
            }
        });
        /**
         * 重新家在表格数据
         */
        function reloadTable(){
            initUserTable.reload({
                where: {
                    name: $('#name').val(),
                    description: $('#description').val(),
                    status : $('#status').val()
                }
            });
        }
        var $ = layui.jquery, active = {
            getCheckData: function () {
                var checkStatus = table.checkStatus('templateTable')
                        , data = checkStatus.data;
                var arr = [];
                for (var i in data) {
                    arr.push(data[i]["id"]);
                }
            },
            serchTable: function () {
                initUserTable.reload({
                    where: {
                        name: $('#name').val(),
                        description: $('#description').val(),
                        status : $('#status').val()
                    }
                });
            },
            addTable: function () {
                layer.open({
                    type: 2,
                    title: '编辑模板信息',
                    skin: 'layui-layer-rim', //加上边框
                    area: ['400px', '300px'], //宽高
                    content: '${ctx}/priceTemplate/initEdit/0'
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