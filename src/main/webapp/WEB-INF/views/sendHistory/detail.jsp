<%--
  Created by IntelliJ IDEA.
  User: guojiaju
  Date: 2017/11/22
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ include file="../common/inc/header.jsp"%>
<%@include file="../common/inc/jscss.jsp"%>
<div style="margin: 5px;">
    <input hidden id="uid" name="uid" value="${obj.uid}">
    <input hidden id="expid" name="expid" value="${obj.expid}">
    <input hidden id="senddate" name="senddate" value="${obj.senddate}">
    <input hidden id="records" name="records" value="${obj.records}">
    <input hidden id="hisId" name="hisId" value="${obj.hisId}">
    <table id="detailTable" lay-filter="detailTable"></table>
</div>
<script type="text/html" id="channelTemp">
    <c:forEach items="${channel}" var="channel" >
        {{# if(d.channel == ${channel.id}){ }}
            ${channel.channelName}
        {{# } }}
    </c:forEach>
</script>
<script type="text/html" id="expidTemp">
    ${obj.expid}
</script>
<script>
    layui.use(['table','jquery'], function() {
            table = layui.table,
                $ = layui.jquery;
        //方法级渲染
        table.render({
            elem: '#detailTable',
            method: 'post',
            url: '${ctx }/smsSendHistory/findDetail',
            where : {hisId:$('#hisId').val(),uid: $('#uid').val(),expid:$('#expid').val(),senddate: $('#senddate').val(),records:$('#records').val()},
            cellMinWidth: 80,
            cols: [[ //标题栏
                {field: 'mobile', title: '发送号码', align: 'center'}
                , {field: 'expid', title: '扩展码',align: 'center',templet:'#expidTemp'}
                , {field: 'rptstat', title: '回执代码', align: 'center'}
                , {field: 'channel', title: '通道', align: 'center' , templet:'#channelTemp'}
                , {field: 'rpttime', title: '回执时间', minWidth: 185, align: 'center'}
                , {field: 'senddate', title:'耗时(秒)', align: 'center'}
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
    });
</script>

