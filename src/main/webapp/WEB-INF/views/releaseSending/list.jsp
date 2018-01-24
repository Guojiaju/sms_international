<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
<table id="releaseTable" class="table" lay-filter="releaseTable" style="table-layout: fixed"></table>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-sm" lay-event="pass">通过</a>
    <a class="layui-btn layui-btn-sm" lay-event="reject">驳回</a>
    <a class="layui-btn layui-btn-sm" lay-event="edit">修改</a>
</script>
<form class="layui-form">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">UID</label>
            <div class="layui-input-inline">
                <input type="text" id="uid" name="uid" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">内容</label>
            <div class="layui-input-inline">
                <input type="text" id="content" name="content" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">通道</label>
            <div class="layui-input-inline">
                <select id="channel" name="channel" class="layui-select">
                    <option value="">全部</option>
                    <c:forEach items="${channels}" var="channel">
                        <option value="${channel.id}">${channel.channelName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <div class="layui-input-block layui-btn-group">
                <button class="layui-btn" type="button" data-type="serchTable">查询</button>
                <button class="layui-btn" type="button" data-type="passChecked">勾选通过</button>
                <button class="layui-btn layui-btn-danger" type="button" data-type="rejectChecked">勾选驳回</button>
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-input-inline" style="width: 100px;">
                <input type="checkbox" id="channelIsChanged" name="channelIsChanged" title="切通道">
            </div>
            <div class="layui-input-inline">
                <select id="changeChannel" name="channel" class="layui-select">
                    <c:forEach items="${channels}" var="channel">
                        <option value="${channel.id}">${channel.channelName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-input-block layui-btn-group">
                <button class="layui-btn" type="button" data-type="passSearch">搜索通过</button>
                <button class="layui-btn layui-btn-danger" type="button" data-type="rejectSearch">搜索驳回</button>
            </div>
        </div>
    </div>
</form>
<div id="change_Pass" style="display:none">
    <div style="margin-top: 10px;">
        <form class="layui-form">
            <div class="layui-form-item">
                <label class="layui-form-label">通道</label>
                <div class="layui-input-inline">
                    <select class="layui-select" name="channel_to" id="channel_to">
                        <option value="0" >全部</option>
                        <c:forEach items="${channels}" var="channel">
                            <option value="${channel.id}">${channel.channelName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">审核数量</label>
                <div class="layui-input-inline">
                    <input name="count_to" type="text" class="layui-input" id="count_to" value="1000">
                </div>
            </div>
        </form>
    </div>
</div>

<div id="updateContent" style="display:none">
  <div style="margin-top: 10px;">
      <form class="layui-form">
          <div class="layui-form-item">
              <div class="layui-inline">
                  <label class="layui-form-label">内容</label>
                  <div class="layui-input-block">
                      <textarea id="newcontent" name="newcontent" onkeyup="countLen()" class="layui-textarea" style="width: 350px;"></textarea>
                  </div>
                  <div id="wordcount" style="margin-left: 108px;">当前计费字数<font color="red" style="font-weight: bold;">0</font>个字</div>
              </div>
          </div>
      </form>
    </div>
</div>
<script type="text/html" id="channel_temp">
    <c:forEach items="${channels}" var="channel">
        {{# if(d.channel == ${channel.id}){ }}
            ${channel.channelName}
        {{# } }}
    </c:forEach>
</script>
<script>
    /**
     * 计算当前短信内容字数
     */
    function countLen(){
        var value = $('#newcontent').val().length;
        $('#wordcount').html("当前字数<font color=\"red\" style=\"font-weight: bold;\">"+value+"</font>个字.");
    }

    layui.use(['table','form','layer','laydate','jquery'], function () {
        var table = layui.table,
                layer = layui.layer,
                $ = layui.jquery,
                form = layui.form;

        //方法级渲染
        var initUserTable = table.render({
            elem: '#releaseTable',
            method: 'post',
            url: '${ctx }/releaseSending/findAll',
            cellMinWidth: 80,
            cols: [[ //标题栏
                {checkbox: true}
                , {field: 'viewTime', title: '时间', width: 180,align : 'center'}
                , {field: 'uid', title: 'UID',align : 'center'}
                , {field: 'content', title: '内容', width: '35%' ,align : 'center'}
                , {field: 'count', title: '号码数',align : 'center'}
                , {field: 'num', title: '总数',align : 'center'}
                , {field: 'channel', title: '通道', width: 120,align : 'center', templet: '#channel_temp'}
                , {field: '', title: '操作', width: 180,align : 'center',toolbar: '#barDemo'}
            ]]
            , id: 'releaseTable'
            , skin: 'row' //表格风格
            , even: true
            , size: 'lg' //尺寸
            , page: true //是否显示分页
            , limits: [10, 15, 20]
            , limit: 15 //每页默认显示的数量
            , loading: true //请求数据时，是否显示loading
            , done : function(){
                $("td[data-field='content']").each(function(){
                   $(this).find(".layui-table-cell").removeClass('layui-table-cell');
                });
            }
        });

        //监听表格复选框选择
        table.on('checkbox(releaseTable)', function (obj) {
            console.log(obj.data);
        });

        //监听单元格编辑
        table.on('edit(releaseTable)', function (obj) {
            var value = obj.value //得到修改后的值
                    , data = obj.data //得到所在行所有键值
                    , field = obj.field; //得到字段
        });

        /**
         * 重新加载数据
         */
        function reloadTable(){
            initUserTable.reload({
                where: {
                    uid: $('#uid').val(),
                    content: $('#content').val(),
                    channel : $('#channel').val()
                }
            });
        }

        //监听工具条
        table.on('tool(releaseTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
                var mdstr = data.mdstr;
                var content = data.content;
                document.getElementById("newcontent").value=content;
                countLen();
                layer.open({
                    type: 1,
                    title: '审核队列内容修改',
                    maxmin: true,
                    shadeClose: true, //点击遮罩关闭层
                    area : ['500px' , '250px'],
                    content:$('#updateContent'),
                    btn:['确认修改','取消'],
                    yes:function(index,layero){
                        if($("#newcontent").val()==null || $("#newcontent").val()==''){
                            layer.msg("内容不能为空",{time:2000});
                            return;
                        }
                        layer.close(index);
                        $.ajax({
                            type:"POST",
                            url: '${ctx }/releaseSending/update',
                            contentType: "application/json",
                            dataType: "json",
                            data:JSON.stringify({ 'content': $("#newcontent").val(), 'mdstr': mdstr}),
                            success: function (result) {
                                layer.msg(result.result,{time:2000},function(){
                                    reloadTable();
                                });
                            },
                            error: function (result) {
                                layer.alert("系统错误\r\n",{
                                    title: '提示框',
                                    icon:0,
                                });
                            }
                        });
                    }
                });
            } else if (obj.event === 'pass') {
                var mdstr= data.mdstr;
                var count = data.count;
                var channel = data.channel;
                //初始化下拉框选中项
                $("#channel_to").find("option[value="+ channel +"]").attr("selected",true);
                form.render('select');

                if(count > 10000){
                    document.all.channel_to.value = channel;
                    layer.open({
                        type: 1,
                        title: '通过部分审核',
                        shadeClose: true, //点击遮罩关闭层
                        area : ['380px' , ''],
                        content:$('#change_Pass'),
                        btn: ['确定','取消'],
                        yes: function(index, layero){
                            if($('#channel_to').val() <= 0){
                                layer.alert("请选择切换后的通道\r\n",{
                                    title: '提示框',
                                    icon:0,
                                });
                                return false;
                            }
                            if($('#count_to').val() <= 0){
                                layer.alert("请输入切换的数量\r\n",{
                                    title: '提示框',
                                    icon:0,
                                });
                                return false;
                            }
                            layer.close(index);
                            $.ajax({
                                type:"POST",
                                url: '${ctx }/releaseSending/releaseMessage_to',
                                contentType: "application/json",
                                dataType: "json",
                                data:JSON.stringify({ 'mdstr': mdstr, 'stat':1, 'count':$('#count_to').val(),'channel_to':$('#channel_to').val()}),
                                beforeSend: function(XHR){
                                    layer.load();
                                },
                                success: function (result) {
                                    layer.closeAll('loading');
                                    layer.msg(result.result,{time:2000},function(){
                                        reloadTable()
                                    });
                                },
                                error: function (result) {
                                    layer.closeAll('loading');
                                    layer.alert("系统错误\r\n",{
                                        title: '提示框',
                                        icon:0,
                                    },function(){
                                        reloadTable();
                                    });
                                }
                            });
                        }
                    });
                }else{
                    $.ajax({
                        type:"POST",
                        url: '${ctx }/releaseSending/releaseMessage',
                        contentType: "application/json",
                        dataType: "json",
                        data:JSON.stringify({ 'mdstr': mdstr, 'stat':1, 'count':0}),
                        success: function (result) {
                            layer.msg(result.result,{time:2000},function(){
                                reloadTable();
                            });
                        },
                        error: function (result) {
                            layer.alert("系统错误\r\n",{
                                title: '提示框',
                                icon:0,
                            },function(){
                                reloadTable();
                            });
                        }
                    });
                }
            } else if (obj.event === 'reject') {
                var mdstr= data.mdstr;
                $.ajax({
                    type:"POST",
                    url: '${ctx }/releaseSending/releaseMessage',
                    contentType: "application/json",
                    dataType: "json",
                    data:JSON.stringify({ 'mdstr': mdstr, 'stat':0 ,'count':0}),
                    success: function (result) {
                        layer.msg(result.result,{time:2000},function(){
                            reloadTable();
                        });
                    },
                    error: function (result) {
                        layer.alert("系统错误\r\n",{
                            title: '提示框',
                            icon:0,
                        },function () {
                            reloadTable();
                        });
                    }
                });
            }
        });

        var active = {
            serchTable: function () {
                //查询
                reloadTable();
            },
            passChecked : function () {
                //勾选通过
                var checkStatus = table.checkStatus('releaseTable'),
                        data = checkStatus.data;
                var ids = "";
                for (var i in data) {
                    ids += data[i]["mdstr"] + ",";
                }
                if(ids.length > 1){
                    ids = ids.substring(0,ids.length-1);
                }
                if(ids.length == 0){
                    layer.msg("请先勾选记录",{time:2000});
                    return false;
                }
                layer.confirm("是否通过所选的短信?",function(){
                    //验证通过后拼装id字符串
                    var channel = 0;
                    if($('#channelIsChanged').is(':checked')){
                        channel = $('#changeChannel').val();
                    }
                    $.ajax({
                        type:"POST",
                        url: '${ctx }/releaseSending/BatchReleaseMessage',
                        contentType: "application/json",
                        dataType: "json",
                        data:JSON.stringify({ 'mdstr': ids, 'stat':1, 'channel':channel}),
                        beforeSend: function(XHR){
                            layer.load();
                        },
                        success: function (result) {
                            layer.closeAll('loading');
                            layer.msg(result.result,{time:2000},function(){
                                reloadTable()
                            });
                        },
                        error: function (result) {
                            layer.closeAll('loading');
                            layer.alert("系统错误\r\n",{
                                title: '提示框',
                                icon:0,
                            });
                        }
                    });
                });
            },
            rejectChecked : function () {
                //勾选驳回
                var checkStatus = table.checkStatus('releaseTable'),
                        data = checkStatus.data;
                var ids = "";
                for (var i in data) {
                    ids += data[i]["mdstr"] + ",";
                }
                if(ids.length > 1){
                    ids = ids.substring(0,ids.length-1);
                }
                if(ids.length == 0){
                    layer.msg("请先勾选记录",{time:2000});
                    return false;
                }
                layer.confirm("是否驳回所选的短信?",function(){
                    $.ajax({
                        type:"POST",
                        url: '${ctx }/releaseSending/BatchReleaseMessage',
                        contentType: "application/json",
                        dataType: "json",
                        data:JSON.stringify({ 'mdstr': ids, 'stat':0}),
                        beforeSend: function(XHR){
                            layer.load();
                        },
                        success: function (result) {
                            layer.closeAll('loading');
                            layer.msg(result.result,{time:2000},function(){
                                reloadTable()
                            });
                        },
                        error: function (result) {
                            layer.closeAll('loading');
                            layer.alert("系统错误\r\n",{
                                title: '提示框',
                                icon:0,
                            });
                        }
                    });
                });
            },
            passSearch : function () {
                //搜索通过
                layer.confirm("按<搜索结果通过>所选的短信?",function(){
                    var content=$('#content').val();
                    if(content==null){
                        content='';
                    }
                    var uid=$('#uid').val();
                    if(uid==null||uid==''||uid=='null'){
                        uid=0;
                    }
                    var toChannel = 0;
                    if($('#channelIsChanged').is(':checked')){
                        toChannel = $('#changeChannel').val();
                    }
                    $.ajax({
                        type:"POST",
                        url: '${ctx }/releaseSending/searchMessage',
                        contentType: "application/json",
                        dataType: "json",
                        data:JSON.stringify({'uid': uid, 'stat':1,'content':content,'channel':$('#channel').val(),'tochannel':toChannel,'mtype':0}),
                        beforeSend: function(XHR){
                            layer.load();
                        },
                        success: function (result) {
                            layer.closeAll('loading');
                            layer.msg(result.result,{time:2000},function(){
                                reloadTable()
                            });
                        },
                        error: function (result) {
                            layer.closeAll('loading');
                            layer.alert("系统错误\r\n",{
                                title: '提示框',
                                icon:0,
                            });
                        }
                    });
                });
            },
            rejectSearch : function () {
                //搜索驳回
                layer.confirm("按<搜索结果驳回>所选的短信?",function(){
                    var content=$('#content').val();
                    if(content==null){
                        content='';
                    }
                    var uid=$('#uid').val();
                    if(uid==null||uid==''||uid=='null'){
                        uid=0;
                    }
                    var channel = 0;
                    $.ajax({
                        type:"POST",
                        url: '${ctx }/releaseSending/searchMessage',
                        contentType: "application/json",
                        dataType: "json",
                        data:JSON.stringify({'uid': uid, 'stat':0,'content':content,'channel':$('#channel').val(),'tochannel':channel,'mtype':0}),
                        beforeSend: function(XHR){
                            layer.load();
                        },
                        success: function (result) {
                            layer.closeAll('loading');
                            layer.msg(result.result,{time:2000},function(){
                                reloadTable();
                            });
                        },
                        error: function (result) {
                            ayer.closeAll('loading');
                            layer.alert("系统错误\r\n",{
                                title: '提示框',
                                icon:0,
                            });
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