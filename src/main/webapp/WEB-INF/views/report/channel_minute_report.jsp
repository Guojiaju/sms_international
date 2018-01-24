<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%--<input hidden id="ctx" value="${ctx}">--%>
<form class="layui-form">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">通道</label>
            <div class="layui-input-inline">
                <select name="channelid" id="channelCombox">
                    <option value="">全部</option>
                    <c:forEach items="${minuteChannel}" var="channel" >
                        <option value="${channel.id}"><c:if test="${channel.status != 0}"><font color="red">(停止)</font></c:if>${channel.channelName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
         <div class="layui-inline">
            <label class="layui-form-label">开始时间</label>
            <div class="layui-input-inline">
                <input type="text" name="startTime" value="" id="startTime" lay-verify="date" placeholder="yyyyMMdd" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">结束时间</label>
            <div class="layui-input-inline">
                <input type="text" name="endTime" id="endTime" lay-verify="date" placeholder="yyyyMMdd" autocomplete="off" class="layui-input">
            </div>
        </div>
        
        
    </div>
</form>
<div class="layui-input-block layui-btn-group">
        <button class="layui-btn" data-type="serchTable">查询</button>
</div>
<table id="channel_real_table" lay-filter="channel_real_table"></table>
<script type="text/html" id="real_create_time">
</script>
<script>
	 /**
     * 页面加载完成执行form渲染
     */
    $(function(){
        renderForm();
    });

    /**
     * 重新渲染form
     */
    function renderForm(){
        layui.use('form', function(){
            var form = layui.form;
            form.render();
        });
    }


    layui.use(['table','layer','laydate','jquery'], function () {
        var table = layui.table,
                layer = layui.layer,
                 laydate = layui.laydate,
                $ = layui.jquery;
                
           //日期
        laydate.render({
            elem: '#startTime',
            format : 'yyyyMMdd',
//             value : new Date().getFullYear()+ "" + (new Date().getMonth()+1) +(new Date().getDate() < 10 ? "0" + new Date().getDate() :new Date().getDate() ) + "000000"
			value : new Date().getFullYear()+ "" + (new Date().getMonth()+1) +(new Date().getDate() < 10 ? "0" + new Date().getDate() :new Date().getDate() )
        });
        laydate.render({
            elem: '#endTime',
            format : 'yyyyMMdd'
        });      
                
                
                
        //方法级渲染
        var initUserTable = table.render({
            elem: '#channel_real_table',
            method: 'post',
            url: '${ctx}/reportStatis/channelMinute',
            cellMinWidth: 80,
            cols: [[ //标题栏
                 {field: 'channelid', title: '编号', width:60,align: 'center'}
                , {field: 'channelName', title: '通道名称',align: 'center'}
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
//                 , {field: 'country', title: '地区',align: 'center'}
                , {field: 'temp_time', title: '日期', width:100, align: 'center'}
            ]]
            , id: 'channel_real_table'
            , skin: 'row' //表格风格
            , even: true
            , size: 'lg' //尺寸
            , page: true //是否显示分页
            , limits: [10, 15, 20]
            , limit: 15 //每页默认显示的数量
            , loading: true //请求数据时，是否显示loading
        });

       
        var active = {
            serchTable: function () {
                initUserTable.reload({
                    where: {
                        channelid : $('#channelCombox').val(),
                        startTime : $('#startTime').val(),
                    	endTime : $('#endTime').val()
                    }
                });
            }
           
        };
        $('.layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });

</script>

