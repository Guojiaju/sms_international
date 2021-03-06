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
            <label class="layui-form-label">通道名称</label>
            <div class="layui-input-inline">
                <input type="text" id="channelName" name="channelName" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline-block layui-btn-group">
            <button class="layui-btn" type="button" data-type="serchTable">查询</button>
            <button class="layui-btn" type="button" data-type="addTable">新增</button>
        </div>
        </div>
    </div>
</form>
<table id="channelTable" class="layui-hide" lay-filter="channelTable"></table>
<script type="text/html" id="statusTemp">
  <!-- 这里的 checked 的状态只是演示 -->
  <input type="checkbox" name="status" value="{{d.id}}" lay-skin="switch" lay-text="开|关" lay-filter="offOrOn" {{ d.status == 0 ? 'checked' : '' }}>
</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-sm" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-sm" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-sm layui-btn-sm" lay-event="resourcesConfig">资源配置</a>
</script>
<script>
    layui.use(['table','layer'], function () {
        var table = layui.table,
                layer = layui.layer,form = layui.form;;
        //方法级渲染
        var initUserTable = table.render({
            elem: '#channelTable',
            method: 'post',
            url: '${ctx }/channel/findAll',
            cellMinWidth: 80,
            cols: [[ //标题栏
                 {field: 'id', title: 'ID',align:'center', width:60}
                , {field: 'channelName', title: '通道名称', align:'center'}
                , {field: 'channelProvider', title: '提供商', align:'center'}
                , {field: 'status', title: '运行状态',align:'center',templet:'#statusTemp'}
                , {field: 'remark', title: '备注', align:'center'}
                , {fixed: 'right', title: '操作',minWidth:260, toolbar: '#barDemo',align:'center'}
            ]]

            , id: 'channelTable'
            , skin: 'row' //表格风格
            , even: true
            , size: 'lg' //尺寸
            , page: true //是否显示分页
            , limits: [10, 15, 20]
            , limit: 15 //每页默认显示的数量
            , loading: true //请求数据时，是否显示loading
        });

        //监听单元格编辑
        table.on('edit(channelTable)', function (obj) {
            var value = obj.value //得到修改后的值
                    , data = obj.data //得到所在行所有键值
                    , field = obj.field; //得到字段

        });

        //监听工具条
        table.on('tool(channelTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                layer.open({
                    type: 2,
                    title: '通道信息',
                    skin: 'layui-layer-rim', //加上边框
                    area: ['700px', '570px'], //宽高
                    content: '${ctx}/channel/detail/'+data.id
                });
            } else if (obj.event === 'resourcesConfig') {
                layer.open({
                    type: 2,
                    title: '资源配置',
                    skin: 'layui-layer-rim', //加上边框
                    area: ['780px', '500px'], //宽高
                    content: '${ctx}/channel/initResources/' + data.id
                })
            }  else if (obj.event === 'edit') {
                layer.open({
                    type: 2,
                    title: '编辑通道信息',
                    skin: 'layui-layer-rim', //加上边框
                    area: ['700px', '570px'], //宽高
                    content: '${ctx}/channel/toAddOrEdit/' + data.id
                });
            }
        });
        var $ = layui.jquery, active = {
            getCheckData: function () {
                var checkStatus = table.checkStatus('channelTable')
                        , data = checkStatus.data;
                var arr = [];
                for (var i in data) {
                    arr.push(data[i]["id"]);
                }
            },
            serchTable: function () {
                initUserTable.reload({
                    where: {
                        channelName: $('#channelName').val(),
                    }
                });
            },
            addTable: function () {
                layer.open({
                    type: 2,
                    title: '编辑通道信息',
                    skin: 'layui-layer-rim', //加上边框
                    area: ['700px', '570px'], //宽高
                    content: '${ctx}/channel/toAddOrEdit/0'
                });
            }
        };
        $('.layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
      //监听性别操作
        form.on('switch(offOrOn)', function(obj){
          var status = this.value;
          $.ajax({
              url: '${ctx}/channel/enableOrDisable',
              type: 'post',
              contentType: 'application/json',
              data: JSON.stringify({'id': this.value, 'status': obj.elem.checked == false ? 1 : 0}),
              dataType: 'json',
              success: function (result) {
                  layer.msg(result > 0 ? "操作成功" : "操作失败", {time: 2000}, function () {
                      initUserTable.reload({
                          where: {
                              channelName: $('#channelName').val()
                          }
                      });
                  });
              },
              error: function (result) {
                  layer.alert("系统错误");
              }
          });
        });
    });
</script>
</body>
</html>