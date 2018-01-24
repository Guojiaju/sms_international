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
    <input hidden id="monthValueText" name="month" value="${obj.month}">
    <input hidden id="monthValueUidText" name="uid" value="${obj.uid}">
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
            url: '${ctx }/userReport/findUserMonthDetail',
            where : {month:$('#monthValueText').val(),uid: $('#monthValueUidText').val()},
            cellMinWidth: 80,
             cols: [[ //标题栏
                 {field: 'month', title: '日期', align: 'center'}
                , {field: 'country', title: '国家', align: 'center'}
                , {field: 'uid', title: '企业编号', align: 'center'}
                , {field: 'company', title: '公司名称', width:100, align: 'center'}
                , {field: 'total', title: '提交总数', width:90, align: 'center'}
//                 , {field: 'unsend', title: 'XA:2006', width:90, align: 'center'}
                , {field: 'user_price', title: '计费金额', width:90, align: 'center'}
//                 , {field: 'fail', title: '平台失败', width:90, align: 'center'}
//                 , {field: 'fail_rate', title: '平台失败率', width:90, align: 'center'}
                , {field: 'arrive_succ', title: '成功状态', width:90, align: 'center'}
                , {field: 'success_rate', title: '成功率', width:90, align: 'center'}
                , {field: 'arrive_fail', title: '失败状态', width:90, align: 'center'}
                , {field: 'arrive_rate', title: '失败率', width:90, align: 'center'}
                , {field: 'norpt_count', title: '未知', width:90, align: 'center'}
                , {field: 'unknow_rate', title: '未知率', width:90, align: 'center'}
                
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

