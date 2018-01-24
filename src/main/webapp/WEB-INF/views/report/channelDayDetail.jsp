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
    <input hidden id="startDetailTime" name="startTime" value="${obj.startTime}">
    <input hidden id="channelDetailId" name="channelid" value="${obj.channelid}">
    <table id="detailTable" lay-filter="detailTable"></table>
</div>
<script type="text/html" id="channelTemp">
    <c:forEach items="${minuteChannel}" var="channel" >
        {{# if(d.channel == ${channel.id}){ }}
            ${channel.channelName}
        {{# } }}
    </c:forEach>
</script>
<script>
    layui.use(['table','jquery'], function() {
            table = layui.table,
                $ = layui.jquery;
        //方法级渲染
        table.render({
            elem: '#detailTable',
            method: 'post',
            url: '${ctx }/reportStatis/findChannelDayDetail',
            where : {startTime:$('#startDetailTime').val(),channelid:$('#channelDetailId').val()},
            cellMinWidth: 80,
            cols: [[ //标题栏
                 {field: 'channelid', title: '编号', align: 'center'}
                ,{field: 'country', title: '国家', align: 'center'}
                , {field: 'channelName', title: '通道名称',width:100, align: 'center'}
                , {field: 'submit_count', title: '提交量', width:100, align: 'center'}
                , {field: 'submit_succ', title: '提交成功量', width:100, align: 'center'}
                , {field: 'submit_fail', title: '提交失败', width:100, align: 'center'}
                , {field: 'report_count', title: '回执总量', width:100, align: 'center'}
                , {field: 'report_succ', title: '回执成功总量', width:100, align: 'center'}
                , {field: 'success_rate', title: '成功率', width:100, align: 'center'}
                , {field: 'report_fail', title: '回执失败量', width:100, align: 'center'}
                , {field: 'fail_rate', title: '失败率', width:100, align: 'center'}
                , {field: 'unknow_count', title: '未知量', width:100, align: 'center'}
                , {field: 'unknow_rate', title: '未知率', width: 100, align: 'center'}
                , {field: 'temp_time', title: '日期', width:100, align: 'center'}
            ]]
            , id: 'detailTable'
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

