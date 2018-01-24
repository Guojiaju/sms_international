<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<title>单日国家分析图</title>
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
	min-width:400px;
	height:auto;
	margin-top:30px;
	border:1px solid #EEEEEE;
}
.base_chart_div{
	margin:0px;
	padding:0px;
	width:90%;
	margin-left:5%;
	float:left;
	min-width:400px;
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
#base_cookies_div{
	width:90%;
	float:left;
	height:400px;
	margin-bottom:20px;
}
#country_send_cookies{
	float:left;
	width:45%;
	height:390px;
	min-width:200px;
}
#country_price_cookies{
	min-width:200px;
	float:left;
	width:45%;
	height:390px;
	margin-left:1%;
}

</style>
</head>
<body>
<form class="layui-form">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">开始时间</label>
            <div class="layui-input-inline">
                <input type="text" name="bill_time" value="${smsUserAnalysis.startTime}" id="startAnalysisDayTime" lay-verify="date" placeholder="yyyyMMdd" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
</form>
<div class="layui-input-block layui-btn-group">
    <button class="layui-btn" data-type="serchTable" onclick="searchChart()">查询</button>
</div>
<!-- 图表区域 开始-->
<div class="base_div">
	<div id="base_cookies_div" class="base_chart_div">
		<div id="country_send_cookies">
			
		</div>
		<div id="country_price_cookies">
		
		</div>
	</div>
	<div id="base_chart_div" class="base_chart_div"></div>
	<div id="base_count_div" class="base_count_div">
		<ul>
			<li><label>单日发送总量：</label><span>${sumCountryAnalysis.send_count}</span></li>
			<li><label>单日总金额：</label><span>${sumCountryAnalysis.user_price}</span></li>
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
            format : 'yyyyMMdd',
// 			value : new Date().getFullYear()+ "" + (new Date().getMonth()+1)
        });
 });
 

function searchChart(){
	var searchTime=$("#startAnalysisDayTime").val();
	window.location.href='${ctx}/analysis/initCountryChart?bill_time='+searchTime;
} 

 

var colorArrayList=['#5e7e54','#e44f2f','#81b6b2','#eba422','#5e7e54','#e44f2f'
,'#FFDEAD','#FFC1C1','#FFB90F','#FFA54F','#FF8C00','#FF7F50','#FF6EB4','#FF4500','#FF3030'
,'#EE7621','#EE6363','#EE3A8C','#EE00EE','#EAEAEA','#E5E5E5','#E0EEE0','#DEB887','#DBDBDB','#D9D9D9'
,'#D3D3D3','#D1D1D1','#CDCDC1','#CDC9A5','#CDC1C5','#CDB7B5','#CDAF95','#CD9B1D','#CD8C95','#CD7054'
,'#CD661D','#CD5B45','#CD3333','#CD1076','#CAFF70','#C71585','#C4C4C4','#C1CDC1','#BFBFBF','#BDB76B','#BBFFFF'
,'#B8B8B8','#B4EEB4','#B3B3B3','#B0E2FF','#B03060','#ADADAD','#A9A9A9','#A4D3EE','#A1A1A1','#9F79EE','#9B30FF'
,'#9A32CD','#98F5FF','#949494','#912CEE','#8EE5EE','#8DEEEE','#8B8B7A','#8B8878','#8B8378','#8B7D6B'
,'#8B7500','#8B668B','#8B5A2B','#8B4789','#8B4500','#8B3626','#8B1C62','#8B0000','#87CEFF','#858585'
,'#838B83','#7FFF00','#7D7D7D','#7B68EE','#7A67EE','#778899','#737373','#707070','#6CA6CD','#6A5ACD'
,'#6959CD','#66CD00','#63B8FF','#5F9EA0','#5C5C5C','#556B2F','#548B54','#525252','#4EEE94','#4A4A4A'
,'#474747','#458B00','#424242','#3D3D3D','#388E8E','#333333','#2E8B57','#282828','#228B22','#1F1F1F'
,'#1C1C1C','#171717','#0F0F0F','#050505','#00FF00','#00EE76','#00CDCD','#00BFFF','#008B45','#006400'
,'#0000AA','#FFDAB9','#FFC125','#FFB6C1','#FFA500','#FF83FA','#FF7F24','#FF6A6A','#FF4040'
,'#FF1493','#EE7600','#EE5C42','#EE30A7','#EE0000','#E9967A','#E3E3E3','#E0E0E0','#DDA0DD','#DB7093'
,'#D8BFD8','#D2B48C','#D15FEE','#CDCDB4','#CDC8B1','#CDC0B0','#CDB79E','#CDAD00','#CD96CD','#CD853F'
,'#CD69C9','#CD6600','#CD5555','#CD3278','#CD00CD','#CAE1FF','#C6E2FF','#C2C2C2','#C1C1C1','#BF3EFF'
,'#BCEE68','#BABABA','#B8860B','#B4CDCD','#B2DFEE','#B0E0E6','#AEEEEE','#ABABAB','#A8A8A8','#A3A3A3','#A0522D'
,'#9E9E9E','#9AFF9A','#999999','#97FFFF','#9400D3','#90EE90','#8E8E8E','#8DB6CD','#8B8B00','#8B8682','#8B814C','#8B7B8B'
,'#8B7355','#8B6508','#8B5A00','#8B475D','#8B3E2F','#8B2500','#8B1A1A','#8A8A8A','#87CEFA','#848484'
,'#836FFF','#7F7F7F','#7D26CD','#7AC5CD','#7A378B','#76EEC6','#71C671','#6E8B3D','#6C7B8B','#698B69'
,'#68838B','#668B8B','#636363','#5E5E5E','#5B5B5B','#555555','#545454','#515151','#4D4D4D','#48D1CC'
,'#473C8B','#454545','#4169E1','#3CB371','#383838','#32CD32','#2E2E2E','#27408B','#218868','#1E90FF'
,'#1A1A1A','#141414','#0D0D0D','#030303','#00FA9A','#00EE00','#00CD66','#00B2EE','#008B00','#0000FF','#00008B'
];




/**
 *【发送量饼图】
 */ 
var myChart1=null;
var option1=null;
function initCountrySendCookies(){
		var countryJsonStr='${countryNameDayChart}';
 		var countrySendJsonStr='${countrySendDayChart}';
 		if(countryJsonStr!=null&&countryJsonStr!=""){
 			var countryArray=eval("["+countryJsonStr+"]");
 			var countrySendArray=eval("["+countrySendJsonStr+"]");
 			var cookieDate=new Array();
 			for(var i=0;i<countryArray.length;i++){
 				var itemObj=new Object();
 					itemObj.name=countryArray[i];
 					itemObj.value=countrySendArray[i];
 				cookieDate[i]=itemObj;
 			}
 			 myChart1=echarts.init(document.getElementById("country_send_cookies"));
					option1 = {
						    title : {
						        text: '单日国家发送量对比',
						        subtext: '仅供参考',
						        x:'center'
						    },
						    tooltip : {
						        trigger: 'item',
						        formatter: "{a} <br/>{b} : {c} ({d}%)"
						    },
						    legend: {
						        orient: 'vertical',
						        left: 'left',
						       
						    },
						    series : [
						        {
						            name: '发送量',
						            type: 'pie',
						            radius : '55%',
						            center: ['50%', '60%'],
						            data:cookieDate,
						            itemStyle: {
						                emphasis: {
						                    shadowBlur: 10,
						                    shadowOffsetX: 0,
						                    shadowColor: 'rgba(0, 0, 0, 0.5)'
						                }
						            }
						        }
						    ]
						};

			myChart1.setOption(option1);
 		}else{
 			$("#country_send_cookies").html("<div style='color:red;margin:0 auto;width:60%;height:auto;text-align:center;margin-top:100px;'>暂未查询到数据</div>");
 		}
}



/**
 *【发送价格量饼图】
 */ 
var myChart2=null;
var option2=null;
function initCountryPriceCookies(){
		var countryJsonStr='${countryNameDayChart}';
 		var countryPriceJsonStr='${countryPriceDayChart}';
 		if(countryPriceJsonStr!=null&&countryPriceJsonStr!=""){
 			var countryArray=eval("["+countryJsonStr+"]");
 			var countryPriceArray=eval("["+countryPriceJsonStr+"]");
 			var cookieDate=new Array();
 			for(var i=0;i<countryArray.length;i++){
 				var itemObj=new Object();
 					itemObj.name=countryArray[i];
 					itemObj.value=countryPriceArray[i];
 				cookieDate[i]=itemObj;
 			}
 			 myChart2=echarts.init(document.getElementById("country_price_cookies"));
					option2= {
						    title : {
						        text: '单日国家消费对比',
						        subtext: '仅供参考',
						        x:'center'
						    },
						    tooltip : {
						        trigger: 'item',
						        formatter: "{a} <br/>{b} : {c} ({d}%)"
						    },
						    legend: {
						        orient: 'vertical',
						        left: 'left',
						       
						    },
						    series : [
						        {
						            name: '消费金额',
						            type: 'pie',
						            radius : '55%',
						            center: ['50%', '60%'],
						            data:cookieDate,
						            itemStyle: {
						                emphasis: {
						                    shadowBlur: 10,
						                    shadowOffsetX: 0,
						                    shadowColor: 'rgba(0, 0, 0, 0.5)'
						                }
						            }
						        }
						    ]
						};

			myChart2.setOption(option2);
 		}else{
 			$("#country_price_cookies").html("<div style='color:red;margin:0 auto;width:60%;height:auto;text-align:center;margin-top:100px;'>暂未查询到数据</div>");
 		}
}






/**
 *【折线图制作】
 */
var myChart3=null;
 var option3=null;
 function initSmsDayChartEvent(){
 	$("#base_chart_div").html("");
 		var countryJsonStr='${countryNameDayChart}';
 		var countrySendJsonStr='${countrySendDayChart}';
 		var countryPriceJsonStr='${countryPriceDayChart}';
 		if(countryJsonStr!=null&&countryJsonStr!=""){
 			var countryArray=eval("["+countryJsonStr+"]");
 				$(".base_div").css("height",(countryArray.length*120+420)+"px");
 			  $("#base_chart_div").css("height",(countryArray.length*120)+"px");//重置Echarts高度
 			var countrySendArray=eval("["+countrySendJsonStr+"]");
 			var countryPriceArray=eval("["+countryPriceJsonStr+"]");
			myChart3=echarts.init(document.getElementById("base_chart_div"));
			 	option3 = {
					    title: {
					        text: '单日国家发送量对比图',
					        subtext: '发送量高排前'
					    },
					    tooltip: {
					        trigger: 'axis',
					        axisPointer: {
					            type: 'shadow'
					        }
					    },
					    legend: {
					        data: ['2017年']
					    },
					    grid: {
					        left: '3%',
					        right: '4%',
					        bottom: '3%',
					        containLabel: true
					    },
					    xAxis: {
					        type: 'value',
					        boundaryGap: [0, 0.01]
					    },
					    yAxis: {
					        type: 'category',
					        data:countryArray
					    },
					    series: [
					        {
					        	barMinWidth:30,//最小宽度
					        	barMaxWidth:30,//最大宽度
					            name: '发送量',
					            type: 'bar',
					            itemStyle:{
					            	 normal: {
						                        color: function(params) {
						                            var colorList = [
						                              '#5e7e54','#e44f2f','#81b6b2','#eba422','#5e7e54',
						                               '#e44f2f'
						                            ];
						                            return colorList[params.dataIndex]
						                        },
						                        label:{
						                         show:true,
						                         formatter: '发送 ( {c} ) 条短信'
						                        }
						                    }
						              
					            },
					             data:countrySendArray
					        },{
					        	barMinWidth:30,//最小宽度
					        	barMaxWidth:30,//最大宽度
					            name: '消费金额',
					            type: 'bar',
					            itemStyle:{
					            	 normal: {
						                        color: function(params) {
						                            var colorList = ['#2F4F4F'];
						                            return colorList[params.dataIndex]
						                        },
						                        label:{
						                         show:true,
						                         formatter: '消费 ( {c} ) 元'
						                        }
						                    }
						              
					            },
					             data:countryPriceArray
					        }
					    ]
					};
			 myChart3.setOption(option3);
 	}else{
 		$("#base_chart_div").html("<div style='color:red;margin:0 auto;width:60%;height:auto;text-align:center;margin-top:100px;'>暂未查询到数据</div>");
 	}
 }
 

 
function loadParam(){
	initCountrySendCookies();//发送量cookies
	initSmsDayChartEvent();
	initCountryPriceCookies();//国家发送饼图
}
window.onload=loadParam;
</script>
</body>
</html>