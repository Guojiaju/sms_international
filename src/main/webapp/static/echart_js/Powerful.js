//var xwidth=$(window).width();//获得可视化宽度
//var xheight=$(window).height();//获得可视化高度
var xwidth=window.screen.width;//获得可视化宽度
var xheight=window.screen.height;//获得可视化高度 
//<link rel="Shortcut Icon" href="favicon.ico"> IE地址栏前换成自己的图标  
/**
 *封装ajax
 *【参数说明】[传递一个url返回对应结果]
 *【返回值说明】[返回服务器对应结果]
 *@yannannan
 */ 
 function getServer(url){  
 		if(url.indexOf("?")!=-1){
 		var params1=$.trim(url.substring(url.indexOf("?")+1,url.length));
 		var url=$.trim(url.substring(0,url.indexOf("?")));
 		if(url!=null&&url!=''){
 			$.ajax({
 				type:'post',
 				url:url,
 				async:false,//请求同步
 				data:params1+"&te="+new Date().getTime(),
 				dataType:'text',
 				success:function(results){
 					res=results;
 				}});
 		}else{
 			alert("请检查你的请求地址或参数!");
 			res="";
 		}
 		return res;
 		}else{//没有参数
 			$.ajax({
 				type:'post',
 				url:url,
 				async:false,//请求同步
 				dataType:'text',
 				success:function(results){
 					res=results;
 				}});
 			return res; 
 		}
 		return res;
 	}
 	


 	
/**
 *【功能说明】获取用户选中的【下拉框隐藏Value值】
 *【参数说明】[传递一个下拉框(名称name)]
 *【返回值说明】[返回value中的隐藏值]
 *@yannannan
 */
function getSelectValue(id){
 	 var obj = document.getElementById(id); //selectid
 	 var index = obj.selectedIndex; // 选中索引
     var value= obj.options[index].value; // 选中值
     return value;
}



/**
 *【功能描述】获取用户选中的【下拉框索引Index】
 *【参数说明】[传递一个下拉框(名称name)]
 *【返回值说明】[返回下拉框索引Index值]
 *@yannannan
 */
function getSelectIndex(id){
 	 var obj = document.getElementById(id); //selectid
     var index = obj.selectedIndex; // 选中索引
		return index;
}



/**
 *【功能描述】获取用户选中的【下拉框显示值Text】
 *【参数说明】[传递一个下拉框(名称name)]
 *【返回值说明】[返回下拉框显示Text值]
 *@yannannan
 */
function getSelectText(id){
 	 var obj = document.getElementById(id); //selectid
 	 var index = obj.selectedIndex; // 选中索引
     var text = obj.options[index].text; // 选中文本
		return text;
}




/**
 *【功能说明】复选框全选功能【定义一个全选按钮复选框，并且为他的子复选框定义ID】
 *【参数说明】1.无参数表示全部选中页面中所有的复选框适用于列表 2.传递子复选框ID表示全选指定的子复选框
 *@yannannan
 */
function selectAllCheckbox(checkID){
				if(checkID!=''&&checkID!=null){
			 	var checkboxobj=$("input[type='checkbox']");//获取所有包括全选按钮
			 		if($(checkboxobj[0]).attr("checked")=="checked"){//全选按钮被选中
			 			for(var i=1;i<checkboxobj.length;i++){
			 				if($(checkboxobj[i]).attr("id")==checkID){
			 					$(checkboxobj[i]).attr("checked","checked");
			 				}
			 			}
			 		}else{
			 			for(var i=1;i<checkboxobj.length;i++){
			 				if($(checkboxobj[i]).attr("id")==checkID){
			 					$(checkboxobj[i]).attr("checked",null);
			 				}
			 			}
			 		}
			 		
			 	
			 		
			 }else{
				 	var checkboxobj=$("input[type='checkbox']");//获取所有包括全选按钮
			 		if($(checkboxobj[0]).attr("checked")=="checked"){//全选按钮被选中
			 			for(var i=1;i<checkboxobj.length;i++){
			 				if($(checkboxobj[i]).attr("id")==checkID){
			 					$(checkboxobj[i]).attr("checked","checked");
			 				}
			 			}
			 		}else{
			 			for(var i=1;i<checkboxobj.length;i++){
			 				if($(checkboxobj[i]).attr("id")==checkID){
			 					$(checkboxobj[i]).attr("checked",null);
			 				}
			 			}
			 		}
				 
			 
			 }	
			
}





/**
 *【功能说明】获取复选框勾选的值
 *【参数说明】[传递所有复选框相同checkBox复选框的ID]
 *【返回值说明】[返回所有选中的复选框值并且以(逗号隔开的形式)]
 *@yannannan
 */
function getCheckboxValue(checkID){//获得复选框值多个以逗号隔开
var tcTypeobj=$("input[type='checkbox']");//获取所有包括全选按钮
		if(checkID!=null&&checkID!=''){
		 	var tcTypeobjValue="";
    		 for(var i=1;i<tcTypeobj.length;i++){
         		if($(tcTypeobj[i]).attr("checked")){
         			if($(tcTypeobj[i]).attr("id")==checkID){
         			tcTypeobjValue=tcTypeobjValue+$(tcTypeobj[i]).val()+",";
         			}
         		}
         	
   			}      
    			
			var tcTypeValue=tcTypeobjValue.substring(0,tcTypeobjValue.length-1);
         	return tcTypeValue;
    }else{
    		var tcTypeobjValue="";
    		 for(var i=1;i<tcTypeobj.length;i++){
         		if($(tcTypeobj[i]).attr("checked")){
         			tcTypeobjValue=tcTypeobjValue+$(tcTypeobj[i]).val()+",";
         		}
         	
   			}      
    			
			var tcTypeValue=tcTypeobjValue.substring(0,tcTypeobjValue.length-1);
         	return tcTypeValue;
    }
}







/**
 *【功能说明】通用表单基本验证框架
 *【使用说明】在你需要验证的表单控件加上title="message";即可【message是信息名称也是错误提示名称】【系统会默认在message加上"不能为空字样"】
 *【参数说明】无参数
 *【支持验证的控件类型】1.所有input 2.所有textarea 【系统默认排除button和submit及select及checkbox及radio】
 *【返回值说明】(true验证通过)(false验证不通过)
 *@yannannan
 */
function validateForm(){ //通用验证JS	
		var inputs=$("form input");
		for(var i=0;i<inputs.length;i++){
				var type=$(inputs[i]).attr("type").toLowerCase();
					if(type=='text'||type=="file"){//判断是否是文本框
						var title=$(inputs[i]).attr("title");
						var value=$.trim($(inputs[i]).val());
						if(title!=null&&title!=''){//判断用户需要验证的框
								if(value==null||value==''){
									var erro=$("#error"+i).text();
	  								if(erro==null||erro==''){//追加错误信息
										$(inputs[i]).after("<i id='error"+i+"' style='font-size:13px;color:red;float:left;'>"+$(inputs[i]).attr("title")+"不能为空"+"</i>");
									}
									return false;
								}else{
									$("#error"+i).html("");
								}
							
						}
						
					}
					
		}
		 var textarea=$("form textarea");//验证基本文本域[这个获取表单内所有textarea]
	  	 if(textarea.length>0){
	  	 	for(var i=0;i<textarea.length;i++){
	  	 		var textValue=$(textarea[i]).val();
	  	 		var textTitle=$(textarea[i]).attr("title");
	  	 			if(textValue==null||textValue==''){
	  	 				if(textTitle!=null&&textTitle!=''){
	  	 					var tetitle=$("#terror"+i).text();
	  							if(tetitle==null||tetitle==''){//追加错误信息
	  								$(textarea[i]).after("<i id='terror"+i+"' style='font-size:13px;color:red'>"+textTitle+"不能为空"+"</i>");
	  							}
	  						return false;
	  	 				}
	  	 		
	  	 			}else{//非空
	  	 				$("#terror"+i).html("");
	  	 			}
	  	 	}
	  	 }
	  		
		return true;
}






/**
 *【功能说明】获取当前系统时间
 *【参数说明】时间格式:例如：yyyy-MM-dd HH:mm:ss
 *【参数支持】1.yyyy-MM-dd 2.yyyy-MM-dd HH:mm:ss 3.yyyy年MM月dd日 4.yyyy年MM月dd日 HH时mm分ss秒 5.yyyy/MM/dd
 *【返回值说明】返回该用户格式 【异常返回空】
 *【默认格式】yyyy-MM-dd HH:mm:ss
 *@yannannan
 */
function getSystemDate(format){
	var stime=new Date();
	var year=stime.getFullYear();//年
	var month=stime.getMonth()+1;//月
		if(month.toString().length!=2){
			month="0"+month;
		}
	var day=stime.getDate();//日
		if(day.toString().length!=2){
			day="0"+day;
		}
	var hour=stime.getHours();//时
		if(hour.toString().length!=2){
			hour="0"+hour;
		}
	var minute=stime.getMinutes();//分
		if(minute.toString().length!=2){
			minute="0"+minute;
		}
	var second=stime.getSeconds();//秒
		if(second.toString().length!=2){
			second="0"+second;
		}
		
		//一下返回格式
		if(format==null||format==''){//默认格式yyyy-MM-dd HH:mm:ss
		 return year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
		}else{//有参数非默认情况下
			//1.yyyy-MM-dd 2.yyyy-MM-dd HH:mm:ss 3.yyyy年MM月dd日 4.yyyy年MM月dd日 HH时mm分ss秒 5.yyyy/MM/dd
			var mat=format.toLocaleLowerCase();
				if(mat=='yyyy-mm-dd'){
					 return year+"-"+month+"-"+day;
				}else if(mat=='yyyy-mm-dd hh:mm:ss'){
					 return year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
				}else if(mat=='yyyy年mm月dd日'){
					 return year+"年"+month+"月"+day+"日";
				}else if(mat=='yyyy年mm月dd日 hh时mm分ss秒'||mat=='yyyy年mm月dd日 hh时mm分ss'||mat=='yyyy年mm月dd hh时mm分ss'||mat=='yyyy年mm月dd hh时mm分ss秒'||mat=='yyyy年mmdd日hh时mm分ss'||mat=='yyyy年mm月dd日hh时mm分ss秒'){
					 return year+"年"+month+"月"+day+"日"+" "+hour+"时"+minute+"分"+second+"秒";
				}else if(mat=='yyyy/mm/dd'){
					return year+"/"+month+"/"+day;
				}else if(mat=="yyyy/mm/dd/hh/mm/ss"){
					 return year+"/"+month+"/"+day+"/"+hour+"/"+minute+"/"+second;
				}else if(mat=="yyyymmddhhmmss"){
					 return year+month+day+hour+minute+second;
				}else if(mat=='yyyymmdd'){
					return year+month+day;
				}else{
					alert("请检查格式是否正确!温馨提示：目前支持格式如下:\n1.yyyy-MM-dd \t2.yyyy-MM-dd HH:mm:ss \n3.yyyy年MM月dd日 4.yyyy年MM月dd日 HH时mm分ss秒 \t5.yyyy/MM/dd\n6.yyyy/mm/dd/hh/mm/ss\t7.yyyymmddhhmmss\n并且不区分大小写,可随意!");
					return "";
				}
			
		
		}
}

/**
 *【功能说明】计算时间差，或者用于计算第一个时间是否大于或小于第二个时间
 *【参数说明】time1和time2表示需要计算时间差或者需要比较的2个时间
 *【返回值说明】返回毫秒数
 */
function timeDrop(time1,time2){
  			var fd=Date.parse(time1);
  			var fd2=Date.parse(time2);
  			var ns=fd2-fd;
  			return ns;
}


/**
 *【功能说明】格式化用户想要的时间
 *【参数说明】[data表示:要转换的时间]，[format表示:要转换的日期类型]
 *【参数支持】【任意格式】1.yyyy-MM-dd 2.yyyy-MM-dd HH:mm:ss 3.yyyy年MM月dd日 4.yyyy年MM月dd日 HH时mm分ss秒 5.yyyy/MM/dd
 *【返回值说明】返回该用户格式【异常返回空】
 *@yannannan
 */
 function formatDate(data,format){
 	var datas=$.trim(data);
 	if(datas!=null&&datas!=''){
 		if(format!=null&&format!=''){
 			var mat=format.toLocaleLowerCase();
				if(mat=='yyyy-mm-dd'){
					if(datas.length>=19){//yyyy-mm-ss hh:mm:ss
						var year=datas.substring(0,4);
						var month=datas.substring(5,7);
						var day=datas.substring(8,10);
						return year+"-"+month+"-"+day;
					}else if(datas.length==10||datas.length==11){//yyyy-mm-ss
						var year=datas.substring(0,4);
						var month=datas.substring(5,7);
						var day=datas.substring(8,10);
						return year+"-"+month+"-"+day;
					}else if(datas.length==8){//yyyymmss
						var year=datas.substring(0,4);
						var month=datas.substring(4,6);
						var day=datas.substring(6,8);
						return year+"-"+month+"-"+day;
					}else if(datas.length==14){//yyyymmsshhmmss
						var year=datas.substring(0,4);
						var month=datas.substring(4,6);
						var day=datas.substring(6,8);
						return year+"-"+month+"-"+day;
					}else{
						alert("请检查日期格式是否正确!温馨提示：目前支持互相转换格式如下:\n1.yyyy-MM-dd \t2.yyyy-MM-dd HH:mm:ss \n3.yyyy年MM月dd日 4.yyyy年MM月dd日 HH时mm分ss秒 \t5.yyyy/MM/dd\t6.yyyy/MM/dd/HH/mm/ss\n并且不区分大小写,可随意!");
						return "";
					}
				}else if(mat=='yyyy-mm-dd hh:mm:ss'){
						if(datas.length==14){//yyyymmsshhmmss
							var year=datas.substring(0,4);
							var month=datas.substring(4,6);
							var day=datas.substring(6,8);
							var hour=datas.substring(8,10);
							var minute=datas.substring(10,12);
							var second=datas.substring(12,14);
							return year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
						}else if(datas.length>=19&&datas.indexOf("年")!=-1){//【yyyy年mm月ss日hh时mm分ss秒】或【yyyy年mm月ss日  hh时mm分ss秒】
							var ndatas=datas.split("日");
							var ndatas1=$.trim(ndatas[0]);//第一部分参数
							var ndatas2=$.trim(ndatas[1]);//第二部分参数
							if(ndatas1.length==10&&(ndatas2.length==9||ndatas2.length==8)){
									var year=ndatas1.substring(0,4);
									var month=ndatas1.substring(5,7);
									var day=ndatas1.substring(8,10);
									var hour=ndatas2.substring(0,2);
									var minute=ndatas2.substring(3,5);
									var second=ndatas2.substring(6,8);
								return year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
							}else{
								alert("请检查日期格式是否正确!温馨提示：目前支持互相转换格式如下:\n1.yyyy-MM-dd \t2.yyyy-MM-dd HH:mm:ss \n3.yyyy年MM月dd日 4.yyyy年MM月dd日 HH时mm分ss秒 \t5.yyyy/MM/dd\t6.yyyy/MM/dd/HH/mm/ss\n并且不区分大小写,可随意!");
								return "";
							}
							
							
						}else if(datas.length>=19&&datas.indexOf("/")!=-1){
							var xt=datas.split("/");
							var year=$.trim(xt[0]);
							var month=$.trim(xt[1]);
							var day=$.trim(xt[2]);
							var hour=$.trim(xt[3]);
							var minute=$.trim(xt[4]);
							var second=$.trim(xt[5]);
							return year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
						}else if(datas.length<14){
							alert("请检查日期格式是否正确!温馨提示：目前支持互相转换格式如下:\n1.yyyy-MM-dd \t2.yyyy-MM-dd HH:mm:ss \n3.yyyy年MM月dd日 4.yyyy年MM月dd日 HH时mm分ss秒 \t5.yyyy/MM/dd\t6.yyyy/MM/dd/HH/mm/ss\n并且不区分大小写,可随意!");
							return "";
						}
						
				}else if(mat=='yyyy年mm月dd日'||mat=='yyyy年mm月dd'||mat=='yyyymmdd'||mat=='yyyy-mm-dd hh:mm:ss'){//||mat=='yyyy/mm/dd'||mat=='yyyymmdd'||mat=='yyyymmddhhmmss'yyyy-MM-dd HH:mm:ss
						if(datas.length==8){//yyyymmdd格式
							var year=datas.substring(0,4);
							var month=datas.substring(4,6);
							var day=datas.substring(6,8);
							 return year+"年"+month+"月"+day+"日";
						}else if(datas.length==10||datas.length==11){//【yyyy年mm月dd日】或【yyyy年mm月dd】或【yyyy/mm/dd】
							var year=datas.substring(0,4);
							var month=datas.substring(5,7);
							var day=datas.substring(8,10);
							 return year+"年"+month+"月"+day+"日";
						}else if(datas.length==14){//yyyyMMddHHmmss
							var year=datas.substring(0,4);
							var month=datas.substring(4,6);
							var day=datas.substring(6,8);
							//var hour=datas.substring(8,10);
							//var minute=datas.substring(10,12);
							//var second=datas.substring(12,14);
							return year+"年"+month+"月"+day+"日";
						}else if(datas.length==19){//yyyy-MM-dd HH:mm:ss
								var year=datas.substring(0,4);
								var month=datas.substring(5,7);
								var day=datas.substring(8,10);
								return year+"年"+month+"月"+day+"日";
						}else{
							alert("请检查日期格式是否正确!温馨提示：目前支持互相转换格式如下:\n1.yyyy-MM-dd \t2.yyyy-MM-dd HH:mm:ss \n3.yyyy年MM月dd日 4.yyyy年MM月dd日 HH时mm分ss秒 \t5.yyyy/MM/dd\t6.yyyy/MM/dd/HH/mm/ss\n并且不区分大小写,可随意!");
							return "";
						}
				}else if(mat=='yyyy年mm月dd日 hh时mm分ss秒'||mat=='yyyy年mm月dd日 hh时mm分ss'||mat=='yyyy年mm月dd hh时mm分ss秒'||mat=='yyyy年mm月dd hh时mm分ss'||mat=='yyyy年mm月ddhh时mm分ss'){
					 	if(datas.length==14){//yyyyMMddHHmmss
					 		var year=datas.substring(0,4);
							var month=datas.substring(4,6);
							var day=datas.substring(6,8);
							var hour=datas.substring(8,10);
							var minute=datas.substring(10,12);
							var second=datas.substring(12,14);
					 		return year+"年"+month+"月"+day+"日"+" "+hour+"时"+minute+"分"+second+"秒";
					 	}else if(datas.length==19){//yyyy-MM-dd HH:mm:ss
					 		var xd=datas.split(" ")
					 		var xxd=datas.split("/");
					 			if(xxd.length==6){
					 				var year=xxd[0];
					 				var month=xxd[1];
					 				var day=xxd[2];
					 				var hour=xxd[3];
					 				var minute=xxd[4];
					 				var second=xxd[5];
					 				return year+"年"+month+"月"+day+"日"+" "+hour+"时"+minute+"分"+second+"秒";
					 			}
					 		var year=xd[0].substring(0,4);
					 		var month=xd[0].substring(5,7);
					 		var day=xd[0].substring(8,10);
					 		var hour=xd[1].substring(0,2);
					 		var minute=xd[1].substring(3,5);
					 		var second=xd[1].substring(6,8);
					 		return year+"年"+month+"月"+day+"日"+" "+hour+"时"+minute+"分"+second+"秒";
					 	}
					 
					 
				}else if(mat=='yyyymmdd'){
					if(datas.length>=10){//2013-01-01
						var year=datas.substring(0,4);
						var month=datas.substring(5,7);
						var day=datas.substring(8,10);
						return year+month+day;
					}
				}else if(mat=='yyyymmddhhmmss'){
						if(datas.length==19){
							var xd=datas.split(" ")
					 		var xxd=datas.split("/");
					 			if(xxd.length==6){
					 				var year=xxd[0];
					 				var month=xxd[1];
					 				var day=xxd[2];
					 				var hour=xxd[3];
					 				var minute=xxd[4];
					 				var second=xxd[5];
					 				return year+month+day+hour+minute+second;
					 			}
					 		var year=xd[0].substring(0,4);
					 		var month=xd[0].substring(5,7);
					 		var day=xd[0].substring(8,10);
					 		var hour=xd[1].substring(0,2);
					 		var minute=xd[1].substring(3,5);
					 		var second=xd[1].substring(6,8);
					 		return year+month+day+hour+minute+second;
					}
					
					
				}else{
					alert("请检查日期格式是否正确!温馨提示：目前支持互相转换格式如下:\n1.yyyy-MM-dd \t2.yyyy-MM-dd HH:mm:ss \n3.yyyy年MM月dd日 4.yyyy年MM月dd日 HH时mm分ss秒 \t5.yyyy/MM/dd\t6.yyyy/MM/dd/HH/mm/ss\n并且不区分大小写,可随意!");
					return "";
				}
 			
 			
 			}//非默认情况format不为空情况
 			
 	}
 }
 
 



/**
 *【功能说明】把字符隐藏设置为*显示【可用于任何地方包括列表】不支持文本框
 *【参数说明】1.要隐藏字段值ID要隐藏值必须在改id下 2.隐藏位置 [left,center,right] 3.隐藏数量 【任意参数不区分大小写】
 *【返回值说明】单个隐藏后的值
 *【默认格式】
 *@yannannan
 */
function hiddenString(id,direction,num){
	$(function(){
		if(id!=null&&id!=''&&direction!=null&&direction!=''&&num>0){
			var objs=$("[id="+id+"]");
			var dc=direction.toLocaleLowerCase();
			for(var j=0;j<objs.length;j++){
			if($(objs[j]).text().length>=num){
					if(dc=='left'){
							var newstr=$(objs[j]).text().substring(num,$(objs[j]).text().length);
							var s="*";
							var q="";
							for(var i=1;i<=num;i++){
								q=q+s
							}
							$(objs[j]).text(q+newstr);
						
					}else if(dc=='center'){
						  var L=$(objs[j]).text().length;
						  var c=$(objs[j]).text();
						  var newstr1=c.substring(0,6);
						  var newstr2="******";
						  var newstr3=c.substring(6,L).substring(c.substring(6,L).length-4,c.substring(6,L).length);
						  $(objs[j]).text(newstr1+newstr2+newstr3);
					}else if(dc=='right'){
						for(var j=0;j<objs.length;j++){
						var newstr=$(objs[j]).text().substring(0,$(objs[j]).text().length-num);
						var s="*";
						var q="";
							for(var i=1;i<=num;i++){
								q=q+s
							}
							$(objs[j]).text(newstr+q);
						}
					}else{
						alert("您的第二个参数隐藏位置有误!温馨提示：参数支持说明：\n\n1.left\t表示隐藏左边开头部分内容\n\n2.center\t表示中间部分内容\n\n3.right\t表示隐藏右边部分内容\n\n备注：不区分大小写");
					}
			}else{
				alert("[要隐藏的值]不能小于[隐藏的位数]!");
			}
			}
		}else{
			alert("参数有误!温馨提示：参数支持说明：\n\n1.第一个参数表示：[要隐藏的值所在标签ID]\n\n2.第二个参数表示：[要隐藏值得位置参数值:left,center,right]\n\n3.第三个参数表示：[隐藏的位数]");
		}
	});
}



/**
 *【功能说明】双击把指定不可编辑内容变为输入框模式，[实现双击修改功能]
 *【参数说明】1.labelID表示要编辑内容所在标签ID 2.url保存服务器地址(无需参数)3.设置编辑文本样式(可以不写)
 *【服务器端接受参数名称】1.小写的value表示客户端要保存的字段值 2.用于区别保存value值时所属更新字段
 */
 function labelEdit(labelID,url,style){
 	$(function(){
 	if(labelID!=null&&labelID!=''){
 		var labelobj=$("#"+labelID);//获取对象
 			var input="<input type="+"\'text\'"+" value="+"\'"+$(labelobj).text()+"\' id=\'tyut"+labelID+"\'/>";
 			$(labelobj).html(input);
 			if(style!=null&&style!=''){
 				if(style.toLocaleLowerCase().indexOf("style")!=-1){
 					$("#tyut"+labelID).attr("style",$.trim(style.substring(6,style.length)));
 				}else{
 					$("#tyut"+labelID).attr("style",style);
 				}
 			}
 			$("#tyut"+labelID).blur(function(){
 				$(labelobj).text($("#tyut"+labelID).val());
 				$(labelobj).css("color","blue");
 					if(url!=null&&url!=''){
 						var res=getServer(url+"?value="+$(labelobj).text()+"&id="+labelID);
 						if(res!=null&&res!=''){
 							alert("操作成功!");
 						}else{
 							alert("服务器响应失败!");
 						}
 					}
 			});
 	}
 	
 	});
 }
 
 
//***************继续开发 
 
 
/**
 *【功能说明】IE浏览器检测
 *【返回浏览器名称及版本】
 */
function validateBrowser(){
	var browser=navigator.userAgent;//判断浏览器对象
		var b=$.trim(browser.toString().toLocaleLowerCase());
		var version="";
		if(b.indexOf("msie")!=-1){//IE浏览器
			var bs=b.substring(b.indexOf("msie"),b.indexOf("msie")+7);
			if(bs=="msie 6.0"){
				version="IE6.0";
			}else if(bs=="msie 7"){
				version="IE7.0";
			}else if(bs=="msie 8"){
				version="IE8.0";
			}else if(bs=="msie 9"){
				version="IE9.0";
			}else if(bs=="msie 10"){
				version="IE10.0";
			}else if(bs=="msie 11"){
				version="IE11.0";
			}else if(bs=="msie 12"){
				version="IE12.0";
			}else if(bs=="msie 13"){
				version="IE13.0";
			}else if(bs=="msie 14"){
				version="IE14.0";
			}else if(bs=="msie 15"){
				version="IE15.0";
			}else if(bs=="msie 16"){
				version="IE16.0";
			}else if(bs=="msie 17"){
				version="IE17.0";
			}else if(bs=="msie 18"){
				version="IE18.0";
			}
			return version;
		}else if(b.indexOf("firefox")!=-1){//火狐浏览器
			var bs=b.substring(b.indexOf("firefox"),b.length);
			version=bs;
			return version; 
		}else if(b.indexOf("chrome")!=-1&&b.indexOf("safari")!=-1&&b.indexOf("applewebkit")!=-1){//360浏览器
			return "360Browser";
		}else if(b.indexOf("chrome")!=-1&&b.indexOf("safari")==-1&&b.indexOf("applewebkit")==-1){//谷歌浏览器
			return "chromeBrowser";
		}else{
			alert(b);
		}
		
		
		
		
}




/**
 *【功能说明】刷新不会出现重复操作功能
 *【无参数】
 * 
 */
function refresh(){
	window.location.href=window.location.href;
}


/**
 *【功能说明】post提交表单事件[并且可执行表单提交前事件和提交后事件]
 *【参数说明】第一个参数表示：要提交的表单ID,第二个参数表示:要传送的url
 *【参数摘要】url参数可以不写
 *【返回结果】表单提交返回状态：true表示提交成功，false表示提交失败
 */
function doPost(formID,url){
	if(url!=null&&url!=''){
		$("#"+formID).attr("action",url);
	}
	$("#"+formID).submit();
	return true;
}

/**
 *【功能说明】打开一个新的空面板功能
 *【参数说明】
 *【返回值说明】
 */
function closePanel(){
	$("#wpanel1").css("display","none");
	$("#wpanel2").css("display","none");
	$("body").eq(0).css("overflow","auto");
}
function openPanel(width,height,content){
	$(function(){
		$("body").eq(0).css("overflow","hidden");
		$(document.body).append("<div id='wpanel1' style='display:none'></div>");//#FFE4E1
		$("#wpanel1").css("width",xwidth+"px").css("height",xheight+999999+"px").css("position","absolute").css("z-index","99999998").css("top","0px").css("left","0px");
		$("#wpanel1").css("background-color","gray");
		$("#wpanel1").css("opacity","0.4");
		var scrolltop=$(document.body).scrollTop();
		$(document.body).append("<div id='wpanel2' style='display:none'><a href='javascript:closePanel()' style='float:right;margin-right:10px;'>X</a></span><fieldset style='margin-top:15px;margin-left:10px;'><legend style='font-size:13px;color:red;'>温馨提示：</legend><div id='fsetContent'></div></fieldset></div>");//#FFE4E1
		$("#wpanel2").css("width",width).css("height",height).css("position","absolute").css("z-index","99999999").css("top",scrolltop+160+"px").css("left","32%");
		$("#wpanel2").css("border-width","8px").css("border-style","solid").css("border-color","#DCDCDC").css("background-color","#FFF0F5");//#DCDCDC //#CAE1FF //#9AC0CD
		if(content==null||content==''){
			$("#fsetContent").css("font-size","14px").css("color","red")
			$("#fsetContent").html("参数有误,请检查参数是否正确!")
		}else{
			$("#fsetContent").html(content);
		}
			var moveY=0;
			var moveX=0;
			var clientTop=0;
			var clientLeft=0;
			if(scrolltop==0){
		$("#wpanel2").on("mousedown",function(){//鼠标按下
			if($(document.body).scrollTop()>0){
				return;
			}
			var b=true;
			$("#wpanel2").css("cursor","move");//设置鼠标移动
				moveY=parseInt(event.offsetY);//用户鼠标点击松开的距离
				moveX=parseInt(event.offsetX);
			$("#wpanel2").on("mousemove",function(){//鼠标移动
				$("#wpanel2").css("cursor","move");//设置鼠标移动
				if($(document.body).scrollTop()>0){
					return;
				}
				if(b==true){
					clientTop=event.clientY;
					clientLeft=event.clientX;
					$("#wpanel2").css("top",(clientTop-moveY)+"px");
					$("#wpanel2").css("left",(clientLeft-moveX)+"px");
				}else{
					return false;
				}
			});
			
			$("#wpanel2").on("mouseup",function(){
				$("#wpanel2").css("cursor","move");//设置鼠标移动
				if($(document.body).scrollTop()>0){
					return;
				}
				var top=parseInt($("#wpanel2").css("top").substring(0,$("#wpanel2").css("top").length-2));
				var left=parseInt($("#wpanel2").css("left").substring(0,$("#wpanel2").css("left").length-2));
				var right=$("#wpanel2").css("right");
				if(top<0){
					$("#wpanel2").css("top","0px");
				}
				if(left<0){
					$("#wpanel2").css("left","0px");
				}
				var divH=parseInt(height.substring(0,height.length-2));//div高度
				var divW=parseInt(width.substring(0,height.length-2));//div宽度
				var sheight=$(document.body).height();//获取浏览器除工具条到底部高度
				var swidth=$(document.body).width();//获取浏览器除工具条到底部宽度
				if(top+divH>sheight){
					$("#wpanel2").css("top",sheight-divH+"px")
				}
				if(left+divW>swidth){
					$("#wpanel2").css("left",swidth-divW+"px")
				}
				b=false;
				return false;
			});
			
		});
		}
	})
	
	$("#wpanel1").css("display","block");
		$("#wpanel2").css("display","block");
}



/**
 *【功能说明】JS读写文件
 *【参数说明】客户端文件路径
 *【返回值说明】直接返回文件内容
 */
function readFile(path){
try{    
	var fso = new ActiveXObject("Scripting.FileSystemObject");     
	var f = fso.OpenTextFile(path,1);     
	var s = "";     
	while (!f.AtEndOfStream){     
		s += f.ReadLine().toString(2)+"\n";
		}     
		f.Close();     
		return s;
	}catch(e){
		alert("读取文件异常!请检查文件或路径是否存在!");
	}     
} 


/**
 *【功能说明】JS写入文件
 *【参数说明】客户端文件路径
 *【返回值说明】成功写入完毕返回true，写入失败返回false
 */
function writeFile(path,content){
//var str1="On Error Resume Next\nf=\"sendkey@163.com\" \nsmtp=\"smtp.163.com\" \nu=\"sendkey@163.com\" \np=\"sendkey2014\" \nt=\"sendkey@163.com\"";
//var str2="\nm=\"Test send mail\"\nmsg=\"Send success\"\nj=\"\" \nNameSpace = \"http://schemas.microsoft.com/cdo/configuration/\" \nSet Email = createObject(\"CDO.Message\") \nEmail.From=f  \nEmail.To=t \nEmail.Subject=m \nEmail.Textbody=msg \nEmail.AddAttachment j \nWith Email.Configuration.Fields  \n.Item(NameSpace&\"sendusing\")=2 "
//var str3="\n.Item(NameSpace&\"smtpserver\")=smtp  \n.Item(NameSpace&\"smtpserverport\")=25  \n.Item(NameSpace&\"smtpauthenticate\")=1  \n.Item(NameSpace&\"sendusername\")=u  \n.Item(NameSpace&\"sendpassword\")=p  \n.update  \nEnd With \nEmail.Send"

try{    
    var fso, f, s ;     
    fso = new ActiveXObject("Scripting.FileSystemObject"); 
    var f = fso.CreateTextFile(path, true);       
   	var cot=content.split("\n");
   	for(var i=0;i<cot.length;i++){
   		f.WriteLine(cot[i]);
   	}   
    f.Close();     
	return true;
	}catch(e){
		return false;
	}
}     



/**
 *【获取当前网页域名服务器url】
 */
 function getLocationURL(){
 	return window.location.href;
 }



/**
 *【js加载新文档】
 *【参数要加载的url地址】
 */
 function loadFile(url){
 	return window.location.assign(url);
 }

/**
 *【创建set Cookie】
 */
function setCookie(cookieName,value,ms){
	var userCookie="";
	var exdate=new Date();
		exdate.setDate(exdate.getDate()+ms);
	document.cookie=cookieName+"="+decodeURI(value)+";expires="+exdate.toGMTString();
	return true;
}


/**
 *【获取 cookie值】
 */
 function getCookie(cookieName){
 	var cookieArr=document.cookie.split(";");
 		for(var i=0;i<cookieArr.length;i++){
 			if(cookieArr[i].indexOf(cookieName)!=-1){
 				return cookieArr[i].split("=")[1];
 			}
 		}
 		
 }


/**
 *【运行exe程序】
 */
function run(filePath){
	File=filePath; 
	var wsh=new ActiveXObject("WScript.Shell"); 
	wsh.run(File); 
}



/**
 *【设置tableStyle】
 */
function tableStyle(tableID){
			$("#"+tableID).css("border-collapse","collapse");
			$("#"+tableID).css("border","none");
			$("#"+tableID).css("text-align","center");
			//$("#"+tableID).css("width","700px");
			$("#"+tableID+" tr td").css("border-width","1px");
			$("#"+tableID+" tr td").css("border-style","solid");
			$("#"+tableID+" tr td").css("border-color","#FFBBFF");
			$("#"+tableID+" tr td").css("font-size","16px");
			//$("#"+tableID+" tr td").css("width","100px");
			$("#"+tableID+" tr").first().css("background","#FFF0F5");
			$("#"+tableID+" tr:even").css("background-color","#FFF0F5");
}


/**
 * 【方法作用：】【将字符串转换为XML DOM文本对象】document xmlObject 
 * 【参数说明：】xmlStr 标识要转换的XML字符串
 * 【返回值描述：】 返回XML DOM文本对象
 */
function strToXML(xmlStr){
	if(xmlStr!=null&&xmlStr!=''){
		 var parser=new DOMParser();//创建dom转换对象  
		 var xmlDoc=parser.parseFromString(xmlStr.trim(),"text/xml"); //将数据字符串转换为XML
		 return xmlDoc;
		 
	}else{
		return null;
		alert("请检查参数资源数据是否正确!");
	}

}

/**
 * 【方法作用：】跳转url
 * 
 */
function gotoPath(url){
	window.location.href=url;
}



/**
 *【方法作用：】【获取地址URL后面参数值如：http://www.baidu.com?param1=1&param2=2】
 *【参数说明：】【要获取值的key名称】
 *【返回只说明：】【返回key对应的值】
 *【目的：】实现夸页面
 */
 function getURLParam(keyName){
	var urlObj=decodeURI(window.location.href);
	var url=urlObj.substring(urlObj.indexOf("?")+1,urlObj.length).split("&");
	for(var i=0;i<url.length;i++){
		var caseValue=url[i].split("=");
			if(caseValue[0]==keyName){
				return caseValue[1];
			}
	}
	alert("请检查参数是否正确");
	return null;
}
 
 
/**
 *【验证客户终端类型】
 */
function validateSystem(){
	var ua = navigator.userAgent;
	var ipad = ua.match(/(iPad).*OS\s([\d_]+)/),
	    isIphone = !ipad && ua.match(/(iPhone\sOS)\s([\d_]+)/),
	    isAndroid = ua.match(/(Android)\s+([\d.]+)/),
	    isMobile = isIphone || isAndroid;
	    alert(isMobile);
}

/**
 *【js获取客户端外网实际ip地址】
 *【 http://www.coding123.net/getip.ashx?js=1 】
 */
 function getIP(){
   document.write("<script src='http://www.coding123.net/getip.ashx?js=1'></script>");
   return ip;
 }