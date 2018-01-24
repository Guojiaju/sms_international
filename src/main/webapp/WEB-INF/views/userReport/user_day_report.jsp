<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%--<input hidden id="ctx" value="${ctx}">--%>
<form class="layui-form">
    <div class="layui-form-item">
         <div class="layui-inline">
            <label class="layui-form-label">企业编号</label>
            <div class="layui-input-inline">
                <input type="text" id="uidDayText" name="uid" autocomplete="off" class="layui-input">
            </div>
        </div>
         <div class="layui-inline">
            <label class="layui-form-label">开始时间</label>
            <div class="layui-input-inline">
                <input type="text" name="startTime" value="" id="startDayTime" lay-verify="date" placeholder="yyyyMMdd" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">结束时间</label>
            <div class="layui-input-inline">
                <input type="text" name="endTime" id="endDayTime" lay-verify="date" placeholder="yyyyMMdd" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">公司名称</label>
            <div class="layui-input-inline">
                <input type="text" id="companyText" name="company" autocomplete="off" class="layui-input">
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
      		<li style="float:left;margin-right:10px;"><label>提交总数：</label><span style="color:red;" id="submitCountSpan">0</span></li>
<!--       		<li style="float:left;margin-right:10px;"><label>成功提交网关：</label><span style="color:red;" id="submitSuccessSpan">0</span></li> -->
<!--       		<li style="float:left;margin-right:10px;"><label>平台失败：</label><span style="color:red;" id="submitFailSpan">0</span></li> -->
      		<li style="float:left;margin-right:10px;"><label>回执成功：</label><span style="color:red;" id="reportSuccessSpan">0</span></li>
      		<li style="float:left;margin-right:10px;"><label>回执失败：</label><span style="color:red;" id="reportFailSpan">0</span></li>
      		<li style="float:left;margin-right:10px;"><label>未知：</label><span style="color:red;" id="unknowSpan">0</span></li>
      	</ul>
      </div>
    </div>
</div>
<table id="user_day_table" lay-filter="user_day_table"></table>
<script type="text/html" id="userDayDetailScript">
	<a class="layui-btn layui-btn-sm" lay-event="userDayDetail">详情</a>
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

	initUserDaySum();//初始化汇总通道日统计
    layui.use(['table','layer','laydate','jquery'], function () {
        var table = layui.table,
                layer = layui.layer,
                 laydate = layui.laydate,
                $ = layui.jquery;
                
           //日期
        laydate.render({
            elem: '#startDayTime',
            format : 'yyyyMMdd',
//             value : new Date().getFullYear()+ "" + (new Date().getMonth()+1) +(new Date().getDate() < 10 ? "0" + new Date().getDate() :new Date().getDate() ) + "000000"
			value : new Date().getFullYear()+ "" + (new Date().getMonth()+1) +(new Date().getDate() < 10 ? "0" + new Date().getDate() :new Date().getDate() )
        });
        laydate.render({
            elem: '#endDayTime',
            format : 'yyyyMMdd'
        });      
                
                
                
        //方法级渲染
        var initUserTable = table.render({
            elem: '#user_day_table',
            method: 'post',
            url: '${ctx}/userReport/userDayReport',
            cellMinWidth: 80,
            cols: [[ //标题栏
                 {field: 'time', title: '日期', width:100,  align: 'center'}
                , {field: 'uid', title: '企业编号', width:90,  align: 'center'}
                , {field: 'company', title: '公司名称', width:100, align: 'center'}
                , {field: 'sms', title: '余额', width:90, align: 'center'}
                , {field: 'total', title: '提交总数', width:90, align: 'center'}
                , {field: 'user_price', title: '计费金额', width:90, align: 'center'}
//                 , {field: 'unsend', title: 'XA:2006', width:90, align: 'center'}
//                 , {field: 'submit_success', title: '提交成功', width:90, align: 'center'}
//                 , {field: 'fail', title: '平台失败', width:90, align: 'center'}
                , {field: 'arrive_succ', title: '成功回执', width:90, align: 'center'}
                , {field: 'success_rate', title: '成功率', width:90, align: 'center'}
                , {field: 'arrive_fail', title: '失败回执', width:90, align: 'center'}
                , {field: 'fail_rate', title: '失败率', width:80, align: 'center'}
                , {field: 'norpt_count', title: '无结果', width:80, align: 'center'}
                , {field: 'unknow_rate', title: '未知率', width:80, align: 'center'}
                , {fixed: 'right', title: '操作', align: 'center', toolbar: '#userDayDetailScript'}
            ]]
            , id: 'user_day_table'
            , skin: 'row' //表格风格
            , even: true
            , size: 'lg' //尺寸
            , page: true //是否显示分页
            , limits: [10, 15, 20]
            , limit: 15 //每页默认显示的数量
            , loading: true //请求数据时，是否显示loading
        });

       
       //监听事件
        table.on('tool(user_day_table)', function (obj) {
            var data = obj.data;
            if (obj.event === 'userDayDetail') {
                layer.open({
                    type: 2,
                    title: '用户报表详情',
                    skin: 'layui-layer-rim', //加上边框
                    area: ['70%', '600px'], //宽高
                    content: '${ctx}/userReport/initUserDayDetail?startTime='+data.time+"&uid="+data.uid
                });
            }
       })
       
       
        var active = {
            serchTable: function () {
            	initUserDaySum();//初始化汇总信息
                initUserTable.reload({
                    where: {
                        uid:$('#uidDayText').val(),
						startTime:$('#startDayTime').val(),
						endTime:$('#endDayTime').val(),
						company:$('#companyText').val()
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
	 function initUserDaySum(){
	 	$.ajax({
			url:'${ctx}/userReport/userDaySum',
			data:{
				 'uid':$('#uidDayText').val(),
				 'startTime':$('#startDayTime').val(),
				 'endTime':$('#endDayTime').val(),
				 'company':$('#companyText').val()
			},
			type:'post',
			async:false,
			success:function(data){
				if(data!=null&&data!=''&&data!="null"){
				 var sumDayJson=eval("("+data+")");
				 $("#submitCountSpan").text(sumDayJson.total);
// 				 $("#submitSuccessSpan").text(sumDayJson.setSubmit_success);
// 				 $("#submitFailSpan").text(sumDayJson.fail);
				 $("#reportSuccessSpan").text(sumDayJson.arrive_succ);
				 $("#reportFailSpan").text(sumDayJson.arrive_fail);
				 $("#unknowSpan").text(sumDayJson.norpt_count);
				}
			}
			
		});
	 }


</script>

