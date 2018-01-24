<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%--<input hidden id="ctx" value="${ctx}">--%>
<form class="layui-form">
    <div class="layui-form-item">
         <div class="layui-inline">
            <label class="layui-form-label">企业编号</label>
            <div class="layui-input-inline">
                <input type="text" id="uidBillText" name="uid" autocomplete="off" class="layui-input">
            </div>
        </div>
         <div class="layui-inline">
            <label class="layui-form-label">开始时间</label>
            <div class="layui-input-inline">
                <input type="text" name="startTime" value="" id="startBillTime" lay-verify="date" placeholder="yyyyMMdd" autocomplete="off" class="layui-input">
            </div>
        </div>
         <div class="layui-inline">
            <label class="layui-form-label">结束时间</label>
            <div class="layui-input-inline">
                <input type="text" name="endTime" value="" id="endBillTime" lay-verify="date" placeholder="yyyyMMdd" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">公司名称</label>
            <div class="layui-input-inline">
                <input type="text" name="company" id="companyBillText" autocomplete="off" class="layui-input">
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
      		<li style="float:left;margin-right:10px;"><label>计费总数：</label><span style="color:red;" id="sendBillSpan">0</span></li>
      		<li style="float:left;margin-right:10px;"><label>回执成功：</label><span style="color:red;" id="arriveSuccBillSpan">0</span></li>
      		<li style="float:left;margin-right:10px;"><label>回执失败：</label><span style="color:red;" id="arriveFailBillSpan">0</span></li>
      		<li style="float:left;margin-right:10px;"><label>未知：</label><span style="color:red;" id="norptCountBillSpan">0</span></li>
      	</ul>
      </div>
    </div>
</div>
<table id="user_bill_table" lay-filter="user_bill_table"></table>
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

	initUserBillSum();
    layui.use(['table','layer','laydate','jquery'], function () {
        var table = layui.table,
                layer = layui.layer,
                 laydate = layui.laydate,
                $ = layui.jquery;
                
           //日期
        laydate.render({
            elem: '#startBillTime',
            format : 'yyyyMMdd',
//             value : new Date().getFullYear()+ "" + (new Date().getMonth()+1) +(new Date().getDate() < 10 ? "0" + new Date().getDate() :new Date().getDate() ) + "000000"
			value : new Date().getFullYear()+ "" + (new Date().getMonth()+1) +(new Date().getDate() < 10 ? "0" + new Date().getDate() :new Date().getDate() )
        });
        laydate.render({
            elem: '#endBillTime',
            format : 'yyyyMMdd'
        });            
                
                
        //方法级渲染
        var initUserTable = table.render({
            elem: '#user_bill_table',
            method: 'post',
            url: '${ctx}/userReport/userBillReport',
            cellMinWidth: 80,
            cols: [[ //标题栏
                 {field: 'time', title: '日期',align: 'center'}
                , {field: 'uid', title: '企业编号',align: 'center'}
                , {field: 'company', title: '公司名称', width:100, align: 'center'}
                , {field: 'send', title: '计费条数', width:90, align: 'center'}
                , {field: 'arrive_succ', title: '成功回执', width:90, align: 'center'}
                , {field: 'success_rate', title: '成功率', width:90, align: 'center'}
                , {field: 'failRptCount', title: '失败回执', width:90, align: 'center'}
                , {field: 'fail_rate', title: '失败率', width:90, align: 'center'}
                , {field: 'norpt_count', title: '未知', width:90, align: 'center'}
                , {field: 'unknow_rate', title: '未知率', width:90, align: 'center'}
                
            ]]
            , id: 'user_bill_table'
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
            	initUserBillSum();//初始化汇总信息
                initUserTable.reload({
                    where: {
                        uid:$('#uidBillText').val(),
						startTime:$('#startBillTime').val(),
						endTime:$('#endBillTime').val(),
						company:$('#companyBillText').val()
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
	 function initUserBillSum(){
	 	$.ajax({
			url:'${ctx}/userReport/userBillSum',
			data:{
				  'uid':$('#uidBillText').val(),
				  'startTime':$('#startBillTime').val(),
				  'endTime':$('#endBillTime').val(),
				  'company':$('#companyBillText').val()
			},
			type:'post',
			async:false,
			success:function(data){
				if(data!=null&&data!=''&&data!="null"){
				 var sumBillJson=eval("("+data+")");
				 $("#sendBillSpan").text(sumBillJson.send);
				 $("#arriveSuccBillSpan").text(sumBillJson.arrive_succ);
				 $("#arriveFailBillSpan").text(sumBillJson.arrive_fail+sumBillJson.fail);
				 $("#norptCountBillSpan").text(sumBillJson.send-sumBillJson.arrive_succ- sumBillJson.fail -sumBillJson.arrive_fail);
				}
			}
			
		});
	 }


</script>

