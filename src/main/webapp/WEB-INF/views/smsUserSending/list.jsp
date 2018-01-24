<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../common/inc/header.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@include file="../common/inc/jscss.jsp"%>
</head>
<body>
    <div class="page-content">
        <form id="searchForm" method="post" class="layui-form">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">用户ID</label>
                    <div class="layui-input-inline">
                        <input type="text" id="uid" name="uid" autocomplete="off" class="layui-input" value="${obj.uid}" />
                    </div>
                    <label class="layui-form-label">手机号码</label>
                    <div class="layui-input-inline">
                        <input type="text" id="mobile" name="mobile" autocomplete="off" class="layui-input" value="${obj.mobile}" />
                    </div>
                    <label class="layui-form-label">内容</label>
                    <div class="layui-input-inline">
                        <input type="text" id="content" name="content" autocomplete="off" class="layui-input" value="${obj.content}" />
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">通道</label>
                <div class="layui-input-inline">
                    <select name="channel" style="width: 156px; margin-left: 10px;" id="channel">
                        <option value="" >全部</option>
                        <c:forEach items="${channel}" var="item">
                            <option value="${item.id}" >${item.channelName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">国家</label>
                    <div class="layui-input-inline">
                        <select id ="location" name ="location" style="width: 156px;">
                            <option value="">请选择</option>
                            <c:forEach items="${country}" var="country">
                                <option value="${country.id}">${country.country}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="layui-inline-block layui-btn-group">
                    <button class="layui-btn" type="button" data-type="searchTable">查询</button>
                </div>
            </div>
        </form>
        <div style="margin-top:20px;">
            <table id="dataTable" class="layui-hide" lay-filter="dataTable"></table>
            <button id="btn_allSelect" class="layui-btn layui-btn-normal" type="button" data-type="selectCheckBox">全选</button>
            <button id="btn_reSelect" class="layui-btn" type="button" data-type="selectCheckBox">反选</button>
            <select id="changeChannel" name="changeChannel" class="select" style="height: 30px;">
                <option value="">选择切换通道</option>
                <c:forEach items="${channel}" var="data">
                    <option value="${data.id}" >${data.channelName}</option>
                </c:forEach>
            </select>
            <button id="btn_changeChannel" class="layui-btn" type="button" data-type="changeChannel">切通道</button>
            <button id="btn_Reject" class="layui-btn layui-btn-danger" data-type="reject" type="button">驳回短信</button>
            <button id="btn_searchReject" class="layui-btn layui-btn-danger" data-type="searchReject" type="button">搜索驳回</button>
            <button id="btn_batchChangeChannel" class="layui-btn layui-btn-normal" data-type="batchChangeChannel" type="button">搜索切换通道</button>
            <button id="btn_batchChangeChannel_to2" class="layui-btn layui-btn-normal" data-type="batchChangeChannel" type="button">搜索切2万</button>
            <button id="btn_batchChangeChannel_to5" class="layui-btn layui-btn-normal" data-type="batchChangeChannel" type="button">搜索切5万</button>
            <button id="btn_batchChangeChannel_to10" class="layui-btn layui-btn-normal" data-type="batchChangeChannel" type="button">搜索切10万</button>
	    </div>
    </div>

<script type="text/html" id="barDemo">
    <button type="button" class="layui-btn layui-btn-sm" lay-event="updateContent">修改</button>
</script>
<script type="text/html" id="contentCount">
    {{# return d.content.length }}
</script>
<script type="text/html" id="channelName">
    <c:forEach items="${channel}" var="channel" >
        {{# if(d.channel == ${channel.id}){ }}
            ${channel.channelName}
        {{# } }}
    </c:forEach>
</script>
<script type="text/javascript">
    var $,active,table,form,layer;
    layui.use(["laytpl", "laypage", "layer", "table", "form"], function () {
        $ = layui.jquery, table = layui.table, form = layui.form, layer = layui.layer;
        //方法级渲染
        var dataTable = table.render({
            elem: '#dataTable',
            method: 'post',
            url: '${ctx }/smsUserSending/findAll',
            cellMinWidth: 80,
            cols: [[ //标题栏
                 {field: 'id',title:' ',checkbox:true , filter:"aa" ,align : 'center'}
                ,{field: 'viewTime', title: '时间', width:'14%', align : 'center'}
                ,{field: 'uid', title: '用户',align : 'center'}
                ,{field: 'mobile', title: '号码', width:'12%',align : 'center'}
                ,{field: 'content', title: '内容', width:'38%',align : 'center'}
                ,{field: 'contentNum', title: '条',align : 'center'}
                ,{field: 'contentCount', title: '字', templet: '#contentCount',align : 'center'}
                ,{field: 'channel', title: '通道',templet: '#channelName',align : 'center'}
                ,{field: 'location', title: '地区',align : 'center'}
                ,{field: '', title: '操作', toolbar: '#barDemo',align : 'center'}
            ]]
            , id: 'dataTable'
            , skin: 'row' //表格风格
            , even: true
            , size: 'lg' //尺寸
            , page: true //是否显示分页
            , limits: [15, 30, 60]
            , limit: 15 //每页默认显示的数量
            , loading: true
            , done : function(){
                $("td[data-field='content']").each(function(){
                    $(this).find(".layui-table-cell").removeClass('layui-table-cell');
                });
            }
        });
        table.on('tool(dataTable)', function(obj){
            var data = obj.data
            ,layEvent = obj.event;
            var contentHtml = "<textarea id='changeContent' style='resize: none;' name='changeContent' rows='6' cols='40' onkeyup='countLen()'>"+ data.content +"</textarea>"
                + "</br>当前计费字数<span id='countSpan' color='red' style='font-weight: bold;'>" + data.content.length + "</span>个字";
            if(layEvent === 'updateContent'){
                layer.open({
                    type: 1,
                    title: false, //不显示标题栏
                    closeBtn: false,
                    area:'300px',
                    shade:0.8,
                    id: 'lay_changeChannel', //设定一个id，防止重复弹出
                    btn: ['确定', '取消'],
                    btnAlign: 'c',
                    moveType: 1, //拖拽模式，0或者1
                    content: contentHtml,
                    success: function(layero){
                        var btn = layero.find('.layui-layer-btn');
                        btn.find('.layui-layer-btn0').on("click",function(){
                            btn.find('.layui-layer-btn0').attr("disabled",true);
                            $.ajax({
                                type:"POST",
                                url: '${ctx }/smsUserSending/updateContent',
                                contentType: 'application/json',
                                dataType: 'json',
                                data: JSON.stringify({ 'oldContent': data.content, 'uid': data.uid, 'changeContent':$("#changeContent").val()}),
                                success: function (result) {
                                    layer.alert(result.result + "\r\n",{title: '提示框',icon:0});
                                    if(result.stat  == 1){
                                        active.searchTable().call();
                                    }
                                },error: function (result) {
                                    layer.alert("系统错误\r\n",{title: '提示框',icon:0});
                                }
                            });
                        });
                    }
                });
            }
        });
        active = {
            searchTable: function () {
                dataTable.reload({
                    where: {
                        uid: $('#uid').val(),
                        mobile: $('#mobile').val(),
                        content: $('#content').val(),
                        channel: $('#channel').val(),
                        location: $('#location').val()
                    }
                });
            },changeChannel: function(){
                var checkStatus = table.checkStatus("dataTable")
                , data = checkStatus.data;
                var arr = new Array();
                for (var i in data) {
                    arr.push(data[i]["id"]);
                }
                var channel=$('#changeChannel').val();

                if(channel == ''){
                    layer.alert("请选择切换的通道！\r\n",{title: '提示框',icon:0});
                    return;
                }
                if(arr.length == 0){
                    layer.alert("请选择切换的信息！\r\n",{title: '提示框',icon:0});
                    return;
                }
                layer.open({
                    type: 1,
                    title: false, //不显示标题栏
                    closeBtn: false,
                    area: '300px;',
                    shade: 0.8,
                    id: 'lay_changeChannel', //设定一个id，防止重复弹出
                    btn: ['确定', '取消'],
                    btnAlign: 'c',
                    moveType: 1, //拖拽模式，0或者1
                    content: "确定将所选信息切换到" + channel,
                    success: function(layero){
                        var btn = layero.find('.layui-layer-btn');
                        btn.find('.layui-layer-btn0').on("click",function(){
                            btn.find('.layui-layer-btn0').attr("disabled",true);
                            $.ajax({
                                type:"POST",
                                url: '${ctx }/smsUserSending/switchChannel',
                                contentType: 'application/json',
                                dataType: 'json',
                                data: JSON.stringify({ 'ids': arr, 'channel':channel}),
                                success: function (result) {
                                    layer.alert(result.result + "\r\n",{title: '提示框',icon:0});
                                    if(result.stat  == 1){
                                        active.searchTable().call();
                                    }
                                },error: function (result) {
                                    layer.alert("系统错误\r\n",{title: '提示框',icon:0});
                                }
                            });
                        });
                    }
                });
            },reject: function(){
                var checkStatus = table.checkStatus("dataTable")
                , data = checkStatus.data;
                var arr = new Array();
                for (var i in data) {
                    arr.push(data[i]["id"]);
                }
                if(arr.length == 0){
                    layer.alert("请选择驳回的信息！\r\n",{title: '提示框',icon:0});
                    return;
                }
                layer.open({
                    type: 1,
                    title: false, //不显示标题栏
                    closeBtn: false,
                    area: '300px;',
                    shade: 0.8,
                    id: 'lay_reject', //设定一个id，防止重复弹出
                    btn: ['确定', '取消'],
                    btnAlign: 'c',
                    moveType: 1, //拖拽模式，0或者1
                    content: "确定将所选信息驳回?",
                    success: function(layero){
                        var btn = layero.find('.layui-layer-btn');
                        btn.find('.layui-layer-btn0').on("click",function(){
                            btn.find('.layui-layer-btn0').attr("disabled",true);
                            $.ajax({
                                type:"POST",
                                url: '${ctx }/smsUserSending/rejectQueueSms',
                                contentType: 'application/json',
                                dataType: 'json',
                                data: JSON.stringify({ 'ids': arr}),
                                success: function (result) {
                                    layer.alert(result.result + "\r\n",{title: '提示框',icon:0});
                                    if(result.stat  == 1){
                                        active.searchTable().call();
                                    }
                                },error: function (result) {
                                    layer.alert("系统错误\r\n",{title: '提示框',icon:0});
                                }
                            });
                        });
                    }
                });
            },searchReject: function(){
                var content = $('#content').val();
                var uid=$('#uid').val();
                var mtype=$('#mtype').val();
                var channel=$('#channel').val();
                var mobile=$('#mobile').val();
                var location=$('#location').val();
                layer.open({
                    type: 1,
                    title: false, //不显示标题栏
                    closeBtn: false,
                    area: '300px;',
                    shade: 0.8,
                    id: 'lay_reject', //设定一个id，防止重复弹出
                    btn: ['确定', '取消'],
                    btnAlign: 'c',
                    moveType: 1, //拖拽模式，0或者1
                    content: "确定将搜索信息驳回",
                    success: function(layero){
                        var btn = layero.find('.layui-layer-btn');
                        btn.find('.layui-layer-btn0').on("click",function(){
                            btn.find('.layui-layer-btn0').attr("disabled",true);
                            $.ajax({
                                type:"POST",
                                url: '${ctx }/smsUserSending/searchReject',
                                contentType: 'application/json',
                                dataType: 'json',
                                data: JSON.stringify({'uid': uid,'content':content,'channel':channel,'mobile':mobile,'location':location}),
                                success: function (result) {
                                    layer.alert(result.result + "\r\n",{title: '提示框',icon:0});
                                    if(result.stat  == 1){
                                        active.searchTable().call();
                                    }
                                },error: function (result) {
                                    layer.alert("系统错误\r\n",{title: '提示框',icon:0});
                                }
                            });
                        });
                    }
                });
            },batchChangeChannel: function(){
                var content = $('#content').val();
                var uid = $('#uid').val();
                var channel = $('#channel').val();
                var changeChannel = $("#changeChannel").val();
                var mobile = $('#mobile').val();
                var location = $('#location').val();

                if(changeChannel == ""){
                    layer.alert("请选择切换的通道！\r\n",{title: '提示框',icon:0});
                    return;
                }
                var id = this.id;
                var num;
                if(id == "batchChangeChannel"){
                    num = 1;
                }else if(id == "batchChangeChannel_to2" ||
                            id == "batchChangeChannel_to5" ||
                            id == "batchChangeChannel_to10"){
                    if(id == "batchChangeChannel_to2")
                        num = 2
                    if(id == "batchChangeChannel_to5")
                        num = 5
                    if(id == "batchChangeChannel_to10")
                        num = 10
                }else{
                    num = -1;
                }
                layer.open({
                    type: 1,
                    title: false, //不显示标题栏
                    closeBtn: false,
                    area: '300px;',
                    shade: 0.8,
                    id: 'lay_reject', //设定一个id，防止重复弹出
                    btn: ['确定', '取消'],
                    btnAlign: 'c',
                    moveType: 1, //拖拽模式，0或者1
                    content: "确定将搜索的信息切换到" + changeChannel,
                    success: function(layero){
                        var btn = layero.find('.layui-layer-btn');
                        btn.find('.layui-layer-btn0').on("click",function(){
                            btn.find('.layui-layer-btn0').attr("disabled",true);
                            $.ajax({
                                type:"POST",
                                url: '${ctx }/smsUserSending/searchChangeChannel',
                                contentType: 'application/json',
                                dataType: 'json',
                                data: JSON.stringify({'num':num, 'uid': uid, 'content':content, 'channel':channel, 'mobile':mobile, 'location':location, 'changeChannel':changeChannel}),
                                success: function (result) {
                                    layer.alert(result.result + "\r\n",{title: '提示框',icon:0});
                                    if(result.stat  == 1){
                                        active.searchTable().call();
                                    }
                                },error: function (result) {
                                    layer.alert("系统错误\r\n",{title: '提示框',icon:0});
                                }
                            });
                        });
                    }
                });
            },selectCheckBox: function(){
                var id = this.id;
                var child = $(this).parent().find("tbody input[type='checkbox']");
                child.each(function(index, item){
                    var divObj = $(item).parent().find("div");
                    var e = $(divObj).prev(),
                    l = e.parents("tr").eq(0).data("index"),
                    n = e[0].checked;
                    if(id == "btn_allSelect")
                        n = true;
                    else if(id == "btn_reSelect")
                        n = n ? false : true;
                    $(table.cache['dataTable'][l]).attr("LAY_CHECKED",n);

                    $(divObj).prev()[0].checked = n;
                    var addClass = n ? "layui-unselect layui-form-checkbox layui-form-checked" : "layui-unselect layui-form-checkbox";
                    $(divObj).removeClass();
                    $(divObj).addClass(addClass);
                });
            }
        };
        $('.layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });

    function countLen(){
    	$('#countSpan').text($('#changeContent').val().length);
    }
</script>
</body>
</html>