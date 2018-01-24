<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%--<input hidden id="ctx" value="${ctx}">--%>
<form class="layui-form">
    <div class="layui-form-item">
         <div class="layui-inline">
            <label class="layui-form-label">企业编号</label>
            <div class="layui-input-inline">
                <input type="text" id="uidMonthText" name="uid" autocomplete="off" class="layui-input">
            </div>
        </div>
         <div class="layui-inline">
            <label class="layui-form-label">开始时间</label>
            <div class="layui-input-inline">
                <input type="text" name="month" value="" id="startMonthTime" lay-verify="date" placeholder="yyyyMMdd" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">公司名称</label>
            <div class="layui-input-inline">
                <input type="text" id="companyMonthText" name="company" autocomplete="off" class="layui-input">
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
      		<li style="float:left;margin-right:10px;"><label>提交总数：</label><span style="color:red;" id="totalCountSpan">0</span></li>
<!--       		<li style="float:left;margin-right:10px;"><label>计费总数：</label><span style="color:red;" id="totalPayCountSpan">0</span></li> -->
<!--       		<li style="float:left;margin-right:10px;"><label>XA:2006总数：</label><span style="color:red;" id="unsendCountSpan">0</span></li> -->
<!--       		<li style="float:left;margin-right:10px;"><label>平台失败：</label><span style="color:red;" id="failCountSpan">0</span></li> -->
      		<li style="float:left;margin-right:10px;"><label>回执成功：</label><span style="color:red;" id="arriveSuccSpan">0</span></li>
      		<li style="float:left;margin-right:10px;"><label>回执失败：</label><span style="color:red;" id="arriveFailSpan">0</span></li>
      		<li style="float:left;margin-right:10px;"><label>未知：</label><span style="color:red;" id="unknowSpan">0</span></li>
      	</ul>
      </div>
    </div>
</div>
<table id="user_month_table" lay-filter="user_month_table"></table>
<script type="text/html" id="userMonthDetailScript">
	<a class="layui-btn layui-btn-sm" lay-event="userMonthDetail">详情</a>
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

	initUserMonthSum();//初始化汇总通道日统计
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
            elem: '#user_month_table',
            method: 'post',
            url: '${ctx}/userReport/userMonthReport',
            cellMinWidth: 80,
            cols: [[ //标题栏
                 {field: 'month', title: '日期', width:100, align: 'center'}
                , {field: 'uid', title: '企业编号', width:90, align: 'center'}
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
                , {fixed: 'right', title: '操作', align: 'center', toolbar: '#userMonthDetailScript'}
                
            ]]
            , id: 'user_month_table'
            , skin: 'row' //表格风格
            , even: true
            , size: 'lg' //尺寸
            , page: true //是否显示分页
            , limits: [10, 15, 20]
            , limit: 15 //每页默认显示的数量
            , loading: true //请求数据时，是否显示loading
        });


		//监听事件
        table.on('tool(user_month_table)', function (obj) {
            var data = obj.data;
            if (obj.event === 'userMonthDetail') {
                layer.open({
                    type: 2,
                    title: '用户报表详情',
                    skin: 'layui-layer-rim', //加上边框
                    area: ['70%', '600px'], //宽高
                    content: '${ctx}/userReport/initUserMonthDetail?month='+data.month+"&uid="+data.uid
                });
            }
       })

       
        var active = {
            serchTable: function () {
            	initUserMonthSum();//初始化汇总信息
                initUserTable.reload({
                    where: {
                        uid:$('#uidMonthText').val(),
						startTime:$('#startMonthTime').val(),
						company:$('#companyMonthText').val()
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
	 function initUserMonthSum(){
	 	$.ajax({
			url:'${ctx}/userReport/userMonthSum',
			data:{
				 'uid':$('#uidDayText').val(),
				 'month':$('#startMonthTime').val(),
				 'company':$('#companyMonthText').val()
			},
			type:'post',
			async:false,
			success:function(data){
				if(data!=null&&data!=''&&data!="null"){
				 var sumMonthJson=eval("("+data+")");
				 $("#totalCountSpan").text(sumMonthJson.total);
// 				 $("#totalPayCountSpan").text(sumMonthJson.total);
// 				 $("#unsendCountSpan").text(sumMonthJson.unsend);
// 				 $("#failCountSpan").text(sumMonthJson.fail);
				 $("#arriveSuccSpan").text(sumMonthJson.arrive_succ);
				 $("#arriveFailSpan").text(sumMonthJson.arrive_fail);
				 $("#unknowSpan").text(sumMonthJson.norpt_count);
				}
			}
			
		});
	 }


</script>

