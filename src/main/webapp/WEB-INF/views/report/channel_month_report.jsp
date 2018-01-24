<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%--<input hidden id="ctx" value="${ctx}">--%>
<form class="layui-form">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">通道</label>
            <div class="layui-input-inline">
                <select name="channelid" id="channelMonthCombox">
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
                <input type="text" name="month" value="" id="startMonthTime" lay-verify="date" placeholder="yyyyMMdd" autocomplete="off" class="layui-input">
            </div>
        </div>
        
        
    </div>
</form>
<div class="layui-input-block layui-btn-group">
        <button class="layui-btn" data-type="serchTable">查询</button>
</div>

<div class="layui-row" style="margin-top:20px;">
    <div class="layui-col-xs6 layui-col-md12">
      <div class="grid-demo grid-demo-bg2">
      	<ul style="list-style:none;width:100%;">
      		<li style="float:left;margin-right:10px;"><label>发送总数：</label><span style="color:red;" id="submitMonthCountSpan">0</span></li>
      		<li style="float:left;margin-right:10px;"><label>提交成功：</label><span style="color:red;" id="submitMonthSuccessSpan">0</span></li>
      		<li style="float:left;margin-right:10px;"><label>提交失败：</label><span style="color:red;" id="submitMonthFailSpan">0</span></li>
      		<li style="float:left;margin-right:10px;"><label>回执成功：</label><span style="color:red;" id="reportMonthSuccessSpan">0</span></li>
      		<li style="float:left;margin-right:10px;"><label>回执失败：</label><span style="color:red;" id="reportMonthFailSpan">0</span></li>
      		<li style="float:left;margin-right:10px;"><label>未知：</label><span style="color:red;" id="unknowMonthSpan">0</span></li>
      	</ul>
      </div>
    </div>
</div>

<table id="channel_month_table" lay-filter="channel_month_table"></table>
<script type="text/html" id="channelMonthDetailScript">
<a class="layui-btn layui-btn-sm" lay-event="monthDetail">详情</a>
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

	initChannelMonthSum();//汇总月报表统计
    layui.use(['table','layer','laydate','jquery'], function () {
        var table = layui.table,
                layer = layui.layer,
                 laydate = layui.laydate,
                $ = layui.jquery;
                
           //日期
        laydate.render({
            elem: '#startMonthTime',
            format : 'yyyyMM',
//             value : new Date().getFullYear()+ "" + (new Date().getMonth()+1) +(new Date().getDate() < 10 ? "0" + new Date().getDate() :new Date().getDate() ) + "000000"
			value : new Date().getFullYear()+ "" + (new Date().getMonth()+1)
        });
                
                
                
        //方法级渲染
        var initUserTable = table.render({
            elem: '#channel_month_table',
            method: 'post',
            url: '${ctx}/reportStatis/channelMonth',
            cellMinWidth: 80,
            cols: [[ //标题栏
                 {field: 'channelid', title: '编号', align: 'center'}
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
                , {field: 'month', title: '日期', width:100, align: 'center'}
                , {fixed: 'right', title: '操作', align: 'center', toolbar: '#channelMonthDetailScript'}
            ]]
            , id: 'channel_month_table'
            , skin: 'row' //表格风格
            , even: true
            , size: 'lg' //尺寸
            , page: true //是否显示分页
            , limits: [10, 15, 20]
            , limit: 15 //每页默认显示的数量
            , loading: true //请求数据时，是否显示loading
        });

        //监听事件
        table.on('tool(channel_month_table)', function (obj) {
            var data = obj.data;
            if (obj.event === 'monthDetail') {
                layer.open({
                    type: 2,
                    title: '通道报表详情',
                    skin: 'layui-layer-rim', //加上边框
                    area: ['70%', '600px'], //宽高
                    content: '${ctx}/reportStatis/initChMonthDetail?month='+data.month+"&channelid="+data.channelid
                });
            }
       })
       
       
        var active = {
            serchTable: function () {
            	initChannelMonthSum();//汇总月报表统计
                initUserTable.reload({
                    where: {
                        channelid : $('#channelMonthCombox').val(),
                        month : $('#startMonthTime').val(),
                    }
                });
            }
           
        };
        $('.layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });


	/**
	 *【通道日报表汇总统计单独ajax】
	 */
	 function initChannelMonthSum(){
	 	$.ajax({
			url:'${ctx}/reportStatis/channelSumMonth',
			data:{
				'channelid':$('#channelMonthCombox').val(),
				'month':$('#startMonthTime').val()
			},
			type:'post',
			async:false,
			success:function(data){
				if(data!=null&&data!=''&&data!="null"){
					 var sumMonthJson=eval("("+data+")");
					 $("#submitMonthCountSpan").text(sumMonthJson.submit_count);
					 $("#submitMonthSuccessSpan").text(sumMonthJson.submit_count-sumMonthJson.submit_fail);
					 $("#submitMonthFailSpan").text(sumMonthJson.submit_fail);
					 $("#reportMonthSuccessSpan").text(sumMonthJson.report_succ);
					 $("#reportMonthFailSpan").text(sumMonthJson.report_fail);
					 $("#unknowMonthSpan").text(sumMonthJson.submit_count - sumMonthJson.submit_fail - sumMonthJson.report_count);
				}
			}
			
		});
	 }

</script>

