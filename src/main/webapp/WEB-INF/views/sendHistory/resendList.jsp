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
                        <label class="layui-form-label">提交状态</label>
                        <div class="layui-input-inline">
                            <select id="stat" name="stat">
                                <option value="-1">失败</option>
                                <option value="0">未提交</option>
                                <option value="1">成功</option>
                            </select>
                        </div>
                        <label class="layui-form-label">回执状态</label>
                        <div class="layui-input-inline">
                            <select name="succ" id="succ">
                                <option value="">不分</option>
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
                        <label class="layui-form-label">开始时间</label>
                        <div class="layui-input-inline">
                            <input type="text" id="startTime" name="startTime"class="layui-input" value="${obj.startTime}" />
                        </div>
                        <label class="layui-form-label">结束时间</label>
                        <div class="layui-input-inline">
                            <input type="text" id="endTime" name="endTime"class="layui-input" value="${obj.endTime}" />
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">手机号码</label>
                        <div class="layui-input-inline">
                            <input type="text" id="mobile" name="mobile" autocomplete="off" class="layui-input" value="${obj.mobile}" />
                        </div>
                        <label class="layui-form-label">内容</label>
                        <div class="layui-input-inline">
                            <input type="text" id="content" name="content" autocomplete="off" class="layui-input" value="${obj.content}" />
                        </div>
                        <label class="layui-form-label">非法词</label>
                        <div class="layui-input-inline">
                            <input type="text" id="mtstat" name="mtstat" autocomplete="off" class="layui-input" value="${obj.mtstat}" />
                        </div>
                        <div class="layui-input-inline">
                            <button type="button" class="layui-btn" data-type="searchTable">查询</button>
                        </div>
                    </div>
                </div>
            </form>
			<div style="margin-top:20px;">
                    <table id="dataTable" class="layui-hide" lay-filter="dataTable"></table>
                    <button type="button" id="btn_allSelect" class="layui-btn layui-btn-normal" data-type="selectCheckBox">全选 </button>
                    <button type="button" id="btn_reSelect"  class="layui-btn" data-type="selectCheckBox">反选</button>
                    <button type="button" id="btnSumbit" class="layui-btn" data-type="submitResend">再次提交</button>
                    <select name="changeChannel" id="changeChannel" style="height:30px;">
                        <option value="">全部</option>
                        <c:forEach items="${channel}" var="channel" >
                            <option value="${channel.id}"><c:if test="${channel.status != 0}"><font color="red">(停止)</font></c:if>${channel.channelName}</option>
                        </c:forEach>
                    </select>
                    <button type="button" id="btnSearchResend"  class="layui-btn layui-btn-normal"  data-type="btnSearchResend">搜索补发</button>
			</div>
    	</div>
	</body>

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
    <script type="text/html" id="statTemp">
        {{ ss() }}
        {{# function ss() {
                var temp;
                if(d.stat == 1){
                    temp = '成功';
                }else if(d.stat == 0){
                    temp = '<font color="green">待发</font>';
                }else{
                    temp = '<font color="red">失败</font>';
                }
                return temp + '[' +  d.stat + ']' + d.mtstat;
            }
        }}
    </script>
	<script type="text/javascript">
        layui.use('laydate', function(){
            var laydate = layui.laydate;
            laydate.render({
                elem: '#startTime',
                type: 'datetime',
                format:'yyyyMMddHHmmss',
                trigger: 'click'
            });
            laydate.render({
                elem: '#endTime',
                type: 'datetime',
                format:'yyyyMMddHHmmss',
                trigger: 'click'
            });
        });
        var $,active,table,form,layer;
        layui.use(["laytpl", "laypage", "layer", "table", "form"], function () {
            $ = layui.jquery, table = layui.table, form = layui.form, layer = layui.layer;
            //方法级渲染
            var dataTable = table.render({
                elem: '#dataTable',
                method: 'post',
                url: '${ctx }/smsSendHistory/findResend',
                cellMinWidth: 80,
                cols: [[ //标题栏
                     {field: 'id', checkbox:true,filter:"aa"}
                    ,{field: 'viewTime', title: '时间', width: 180,align:'center'}
                    ,{field: 'uid', title: '用户',  width: 80,align:'center'}
                    ,{field: 'mobile', title: '号码',  width: 150,align:'center'}
                    ,{field: 'content', title: '内容',minWidth:250,align:'center'}
                    ,{field: 'channel', title: '通道', width: 100, templet: '#channelName',align:'center'}
                    ,{field: 'stat', title: '提交状态', width: 150, templet: '#statTemp',align:'center'}
                    ,{field: 'contentNum', title: '数量', width: 60,align:'center'}
                    ,{field: 'contentCount', title: '字', width: 60,templet: '#contentCount',align:'center'}
                    ,{field: 'pid', title: '批次',  width: 100,align:'center'}
                    ,{field: 'location', title: '归属地', width: 120,align:'center'}
                ]]
                , id: 'dataTable'
                , skin: 'row' //表格风格
                , even: true
                , size: 'lg' //尺寸
                , page: true //是否显示分页
                , limits: [15, 30, 60]
                , limit: 15 //每页默认显示的数量
                , loading: true
            });
            active = {
                searchTable: function () {
                    dataTable.reload({
                        where: {
                            uid: $('#uid').val(),
                            stat:$("#stat").val(),
                            succ:$("#succ").val(),
                            channel: $('#channel').val(),
                            startTime:$("#startTime").val(),
                            endTime:$("#endTime").val(),
                            mobile: $('#mobile').val(),
                            content: $('#content').val(),
                            mtstat:$("#mtstat").val()
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
                },submitResend: function(){
                    var checkStatus = table.checkStatus("dataTable")
                         , data = checkStatus.data;
                    var arr = new Array();
                    for (var i in data) {
                         arr.push(data[i]["id"]);
                    }
                    if(arr.length == 0){
                         layer.alert("请选择补发的信息！\r\n",{title: '提示框',icon:0});
                         return;
                    }
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
                        content: "确定补发所选短信？",
                        success: function(layero){
                            var btn = layero.find('.layui-layer-btn');
                            btn.find('.layui-layer-btn0').on("click",function(){
                                btn.find('.layui-layer-btn0').attr("disabled",true);
                                $.ajax({
                                    type: 'POST',
                                    url: ctx+"/smsSendHistory/resend",
                                    contentType: "application/json",
                                    dataType: "json",
                                    data:JSON.stringify({ 'ids': arr,'channel':$("#changeChannel").val()}),
                                    success: function (result) {
                                        layer.alert(result.result + "\r\n",{title: '提示框',icon:0});
                                        if(result.stat == 1){
                                            active.searchTable().call();
                                        };
                                    },
                                    error: function (result) {
                                        layer.alert(result.result + "\r\n",{title: '系统错误',icon:0});
                                    }
                                });
                            });
                        }
                    });
                },btnSearchResend: function(){
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
                        content: "确定按搜索条件补发短信？",
                        success: function(layero){
                            var btn = layero.find('.layui-layer-btn');
                            btn.find('.layui-layer-btn0').on("click",function(){
                                btn.find('.layui-layer-btn0').attr("disabled",true);
                                $.ajax({
                                    type: 'POST',
                                    url: ctx+"/smsSendHistory/resendByQuery",
                                    contentType: "application/json",
                                    dataType: "json",
                                    data:JSON.stringify({ 'uid': $("#uid").val(),'stat':$("#stat").val(),"succ":$("#succ").val(),
                                        "channel":$("#channel").val(),"startTime":$("#startTime").val(),"endTime":$("#endTime").val(),
                                        "mobile":$("#mobile").val(),"content":$("#content").val(),"mtstat":$("#mtstat").val(),
                                        "changeChannel":$("#changeChannel").val()}),
                                    success: function (result) {
                                        layer.alert(result.result + "\r\n",{title: '提示框',icon:0});
                                        if(result.stat == 1){
                                            active.searchTable().call();
                                        };
                                    },
                                    error: function (result) {
                                        layer.alert(result.result + "\r\n",{title: '系统错误',icon:0});
                                    }
                                });
                            });
                        }
                    });
                }
            }
            $('.layui-btn').on('click', function () {
                var type = $(this).data('type');
                active[type] ? active[type].call(this) : '';
            });
        });
	</script>
</html>