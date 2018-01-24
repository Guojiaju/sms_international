<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<title>发送量单日趋势图</title>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1">
    <%@include file="../common/inc/jscss.jsp" %>
<style type="text/css">
body {
   padding: 10px;
}

ul,li,span,label{
	padding:0px;
	margin:0px;
}
.base_shadow{
	box-shadow:4px 4px 10px 4px #CCCCCC;
	-webkit-box-shadow:4px 4px 10px 4px #CCCCCC;
	-moz-box-shadow:4px 4px 10px 4px #CCCCCC;
	-o-box-shadow:4px 4px 10px 4px #CCCCCC;
	-ms-box-shadow:4px 4px 10px 4px #CCCCCC;
}
.base_div{
	margin:0px;
	padding:0px;
	width:100%;
	min-width:300px;
	height:430px;
	margin-top:30px;
	border:1px solid #EEEEEE;
}
.base_chart_div{
	margin:0px;
	padding:0px;
	width:90%;
	height:400px;
	margin-left:5%;
	float:left;
}
.base_count_div{
	float:left;
	margin:0px;
	padding:0px;
	width:100%;
	height:35px;
	background-color:#EEEEEE;	
	bottom:0px;
}
.base_count_div ul{
	width:80%;
	margin:0 auto;
	list-style:none;
}
.base_count_div ul li{
	float:left;
	line-height:35px;
	margin-right:5%;
}
.base_count_div ul li label{
	float:left;
	font-weight:bold;
}
.base_count_div ul li span{
	float:left;
	color:red;
}
</style>
</head>
<body>
<form class="layui-form">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">开始时间</label>
            <div class="layui-input-inline">
                <input type="text" name="month" value="${smsUserAnalysis.startTime}" id="startAnalysisDayTime" lay-verify="date" placeholder="yyyyMMdd" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
</form>
<div class="layui-input-block layui-btn-group">
    <button class="layui-btn" data-type="serchTable" onclick="searchChart()">查询</button>
</div>
<!-- 图表区域 开始-->
<div class="base_div">
	<div id="base_chart_div" class="base_chart_div"></div>
	<div id="base_count_div" class="base_count_div">
		<ul>
			<li><label>日均发送总量：</label><span></span></li>
			<li><label>月发送总量：</label><span>${monthSendCount}</span></li>
			<li><label>月发送成功总量：</label><span>${monthSuccessCount}</span></li>
			<li><label>月发送失败总量：</label><span>${monthFailCount}</span></li>
		</ul>
	</div>
</div>
<!-- 图表区域结束 -->
<script type="text/javascript" src="${ctx }/static/echart_js/yan_echarts.js"></script>
<script type="text/javascript">
layui.use(['layer','laydate','jquery'], function () {
                layer = layui.layer,
                 laydate = layui.laydate,
                $ = layui.jquery;
                
           //日期
        laydate.render({
            elem: '#startAnalysisDayTime',
            format : 'yyyyMM',
// 			value : new Date().getFullYear()+ "" + (new Date().getMonth()+1)
        });
 });
 

function searchChart(){
	var searchTime=$("#startAnalysisDayTime").val();
	window.location.href='${ctx}/analysis/initDayChart?startTime='+searchTime;
} 

 
/**
 *【折线图制作】
 */
var myChart3=null;
 var option3=null;
 function initSmsDayChartEvent(){
 	$("#base_chart_div").html("");
 	var dayAndSendStr='${allUserLineChart}';
 	var allUserDayAndSendSuccessStr='${allUserSuccessLineChart}';
 	var allUserDayAndSendFailStr='${allUserFailLineChart}';
 	
 	if(dayAndSendStr!=null&&dayAndSendStr!=''&&allUserDayAndSendSuccessStr!=null&&allUserDayAndSendSuccessStr!=''){
 		var dayArray=eval("["+dayAndSendStr.split("#")[0]+"]");
 		var sendCountArray=eval("["+dayAndSendStr.split("#")[1]+"]");
 		var sendSuccessArray=eval("["+allUserDayAndSendSuccessStr.split("#")[1]+"]");
 		var sendFailArray=eval("["+allUserDayAndSendFailStr.split("#")[1]+"]");
 			//得到发送成功数组
			myChart3=echarts.init(document.getElementById("base_chart_div"));
			var monthTitleStr=('${smsUserAnalysis.startTime}').substring(0,4)+"年"+('${smsUserAnalysis.startTime}').substring(4,6)+"日发送趋势图";
			 	option3 = {
			    title: {
			        text:monthTitleStr,
			        left:'2%'
			    },
			    tooltip: {
			        trigger: 'axis'
			    },
			    legend: {
			        data:['发送总数','成功总数','失败总数']
			    },
			    grid: {
			    	width:'96%',
			        left: '1%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
			    toolbox: {
			        feature: {
			            saveAsImage: {}
			        }
			    },
			    xAxis: {
			        type: 'category',
			        boundaryGap:true,
			        data:dayArray
			    },
			    yAxis: {
			        type: 'value'
			    },
			    series: [
			        {
			            name:'发送总数',
			            type:'line',
			            stack: '总数',
			            
			            data:sendCountArray
			        },
			        {
			            name:'成功总数',
			            type:'line',
			            stack: '成功总数',
			           
			            data:sendSuccessArray
			        },
			        {
			            name:'失败总数',
			            type:'line',
			            stack: '失败总数',
			             
			            data:sendFailArray
			        }
			    ]
			};
			 myChart3.setOption(option3);
// 			 window.onresize =myChart3.resize;
 	}else{
 		$("#base_chart_div").html("<div style='color:red;margin:0 auto;width:60%;height:auto;text-align:center;margin-top:100px;'>暂未查询到数据</div>");
 	}
 	
 }
 
 /**
  *【计算日均发送量】
  */
  function avgDayCount(){
  	var monthCount='${monthSendCount}';
  	var dayAndSendStr='${allUserLineChart}';
  	var dayArray=eval("["+dayAndSendStr.split("#")[0]+"]");
  	var dayNum=dayArray.length;
  	var avg=parseInt(monthCount/dayNum);
  		$("#base_count_div ul li").eq(0).find("span").text(avg);
  }
 
function loadParam(){
	avgDayCount();//日均发送
	initSmsDayChartEvent();
}
window.onload=loadParam;
</script>
</body>
</html>