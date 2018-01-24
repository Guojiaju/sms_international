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
                <input type="text" id="uid" name="uid" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">签名</label>
            <div class="layui-input-inline">
                <input type="tel" id="store" name="store" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">拓展号</label>
            <div class="layui-input-inline">
                <input type="tel" id="expend" name="expend" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">时间</label>
            <div class="layui-input-inline">
                <input type="text" id="addtime" name="addtime" lay-verify="date" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">报备状态</label>
            <div class="layui-input-inline">
                <select id="status" name="status" class="layui-select">
                    <option value="">全部</option>
                    <option value="0">未报</option>
                    <option value="1">已报</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-input-block layui-btn-group">
                <button class="layui-btn" type="button" data-type="serchTable">查询</button>
                <button class="layui-btn" type="button" data-type="add">新增</button>
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-input-block layui-btn-group">
                <button class="layui-btn" type="button" data-type="registerBySearch">按搜索条件报备</button>
                <button class="layui-btn" type="button" data-type="exportBySearch">按搜索条件导出</button>
                <button class="layui-btn layui-btn-danger" type="button" data-type="removeChecked">删除选中</button>
                <button class="layui-btn layui-btn-danger" type="button" data-type="removeBySearch">按搜索条件删除</button>
            </div>
        </div>
    </div>
</form>

<table id="signTable" lay-filter="signTable"></table>
<div id="addDiv" style="display: none;">
    <div style="margin-top: 10px;">
        <form id="batchAddForm" class="layui-form">
            <input hidden name="group_id" value="2">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">签名</label>
                    <div class="layui-input-inline">
                        <textarea id="signStores" name="signStores" placeholder="每行一个，格式：uid,拓展码,签名" class="layui-textarea"></textarea>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="addSign">立即提交</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-sm" lay-event="edit">修改</a>
    <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="remove">删除</a>
    <a class="layui-btn layui-btn-sm" lay-event="register">报备</a>
    <a class="layui-btn layui-btn-sm layui-btn-danger" lay-event="doBlack">拉黑</a>
</script>
<script type="text/html" id="view_status">
    {{# if(d.status == 0){ }}
        <font color="red">未报</font>
    {{# } else { }}
        <font color="green">已报</font>
    {{# } }}
</script>
<script>
    layui.use(['table','form','layer','laydate','jquery'], function () {
        var table = layui.table,
                layer = layui.layer,
            laydate = layui.laydate,
                $ = layui.jquery,
                form = layui.form;

        laydate.render({
            elem: '#addtime',
            format : 'yyyy-MM-dd'
        });

        //方法级渲染
        var initUserTable = table.render({
            elem: '#signTable',
            method: 'post',
            url: '${ctx }/sign/findAll',
            where : {'type' : 2},
            cellMinWidth: 80,
            cols: [[ //标题栏
                {checkbox: true ,fixed : 'left'}
                , {field: 'uid', title: 'UID',width:60,align : 'center' }
                , {field: 'store', title: '签名',align : 'center' }
                , {field: 'expend', title: '拓展',align : 'center'}
                , {field: 'userexpend', title: '用户推送拓展',align : 'center' }
                , {field: 'addtime', title: '创建时间',align : 'center'}
                , {field: 'status', title: '报备状态', templet: '#view_status',align : 'center'}
                , {fixed: 'right', title: '操作', width: 250,toolbar: '#barDemo',align : 'center'}
            ]]
            , id: 'signTable'
            , skin: 'row' //表格风格
            , even: true
            , size: 'lg' //尺寸
            , page: true //是否显示分页
            , limits: [10, 15, 20]
            , limit: 15 //每页默认显示的数量
            , loading: true //请求数据时，是否显示loading
        });

        //监听单元格编辑
        table.on('edit(signTable)', function (obj) {
            var value = obj.value //得到修改后的值
                    , data = obj.data //得到所在行所有键值
                    , field = obj.field; //得到字段
        });

        /**
         * 添加平台签名
         */
        form.on('submit(addSign)',function () {
            $.ajax({
                type : 'post',
                url : '${ctx}/sign/doAdd',
                dataType : 'json',
                data : {'signStores':$("#signStores").val()},
                success : function(result){
                    layer.msg(result.msg,{time:2000},function(){
                        layer.closeAll(); //执行关闭
                        $("#signStores").val("");
                        reloadTable();
                    });
                },
                error : function(result){
                    layer.alert("系统错误");
                }
            });
            return false;
        });

        /**
         * 重新加载数据
         */
        function reloadTable(){
            initUserTable.reload({
                where: {
                    uid: $('#uid').val(),
                    store: $('#store').val(),
                    expend: $('#expend').val(),
                    addtime : $('#addtime').val(),
                    status : $('#status').val()
                }
            });
        }

        //监听工具条
        table.on('tool(signTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
                layer.open({
                    type: 2,
                    title: '修改签名',
                    skin: 'layui-layer-rim', //加上边框
                    area: ['400px', '350px'], //宽高
                    content: '${ctx}/sign/initUpdate/' + data.id
                })
            } else if (obj.event === 'remove') {
                layer.confirm("确定删除该签名吗？", function (index) {
                    $.ajax({
                        url: '${ctx}/sign/deleteById/' + data.id,
                        type: 'post',
                        contentType: 'application/json',
                        dataType: 'json',
                        success: function (result) {
                            layer.msg(result > 0 ? "删除成功" : "删除失败", {time: 2000}, function () {
                                reloadTable();
                            });
                        },
                        error: function (result) {
                            layer.alert("系统错误");
                        }
                    });
                    layer.close(index);
                });
            } else if (obj.event === 'register') {
                $.ajax({
                    type : 'post',
                    url : '${ctx}/sign/doUpdate',
                    contentType : 'application/json',
                    dataType : 'json',
                    data : JSON.stringify({'id':data.id,'status':1}),
                    success : function(result){
                        layer.msg(result > 0 ? "报备成功":"报备失败",{time:2000},function(){
                            reloadTable();
                        });
                    },
                    error : function(result){
                        layer.alert("系统错误");
                    }
                });
            } else if (obj.event === 'doBlack') {
                layer.confirm("确定拉黑该签名吗？", function (index) {

                });
            }
        });

        var active = {
            getCheckData: function () {
                /*var checkStatus = table.checkStatus('signTable')
                        , data = checkStatus.data;
                for (var i in data) {
                    arr.push(data[i]["id"]);
                }*/
            },
            serchTable: function () {
                reloadTable();
            },
            add: function () {
                layer.open({
                    type: 1,
                    title: '新增平台签名',
                    skin: 'layui-layer-rim', //加上边框
                    area: ['400px', '300px'], //宽高
                    content: $('#addDiv')
                })
            },
            registerBySearch : function () {
                var uid = $('#uid').val();
                var store = $('#store').val();
                var expend = $('#expend').val();
                var addtime = $('#addtime').val();
                var status = $('#status').val();
                if((uid == null || uid == '') && (store == null || store == '' )
                        && (expend == null || expend == '' )&& (addtime == null || addtime == '')
                        && (status == null || status == '')){
                    layer.alert("请输入搜索条件");
                    return false;
                }
                var params = {
                    'uid' : uid,
                    'store' : store ,
                    'expend' : expend,
                    'addtime' : addtime,
                    'status' : status
                }
                layer.confirm("确定执行该操作？",function (index) {
                    $.ajax({
                        type : 'post',
                        url: '${ctx}/sign/registerBySearch',
                        contentType : 'application/json',
                        dataType : 'json',
                        data : JSON.stringify(params),
                        success : function(result){
                            layer.msg(result > 0 ? "操作成功":"操作失败",{time:2000},function(){
                                layer.close(index); //执行关闭
                                reloadTable();
                            });
                        },
                        error : function(result){
                            layer.alert("系统错误");
                        }
                    });
                });
            },
            exportBySearch : function () {

            },
            removeChecked :function () {
                var checkStatus = table.checkStatus('signTable'), data = checkStatus.data;
                var arr = [];
                for (var i in data) {
                     arr.push(data[i]["id"]);
                }
                if(arr.length == 0){
                    layer.msg("请先勾选记录",{time:2000});
                    return false;
                }
                if(confirm("确定删除所选的签名吗？")){
                    $.ajax({
                        type:"POST",
                        url: '${ctx }/sign/batchRemove',
                        dataType: "json",
                        data:{'ids': arr.toString()},
                        success: function (result) {
                            if(result > 0){
                                layer.msg("删除成功",{time:2000},function(){
                                    reloadTable();
                                });
                            }else{
                                layer.msg("删除失败",{time:2000});
                            }
                        },
                        error: function (result) {
                            layer.alert("系统错误\r\n",{
                                title: '提示框',
                                icon:0,
                            });
                        }
                    });
                }
            },
            removeBySearch :function () {
                var uid = $('#uid').val();
                var store = $('#store').val();
                var expend = $('#expend').val();
                var addtime = $('#addtime').val();
                var status = $('#status').val();
                if((uid == null || uid == '') && (store == null || store == '' )
                        && (expend == null || expend == '' )&& (addtime == null || addtime == '')
                        && (status == null || status == '')){
                    layer.alert("请输入搜索条件");
                    return false;
                }
                var params = {
                    'uid' : uid,
                    'store' : store ,
                    'expend' : expend,
                    'addtime' : addtime,
                    'status' : status
                }
                layer.confirm("确定执行该操作？",function (index) {
                    $.ajax({
                        type : 'post',
                        url: '${ctx}/sign/removeBySearch',
                        contentType : 'application/json',
                        dataType : 'json',
                        data : JSON.stringify(params),
                        success : function(result){
                            layer.msg(result > 0 ? "操作成功":"操作失败",{time:2000},function(){
                                layer.close(index); //执行关闭
                                reloadTable();
                            });
                        },
                        error : function(result){
                            layer.alert("系统错误");
                        }
                    });
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