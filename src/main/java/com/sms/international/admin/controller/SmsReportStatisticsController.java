package com.sms.international.admin.controller;

import com.sms.international.admin.model.SmsReportChannel;
import com.sms.international.admin.service.SmsReportStatisticsService;
import com.sms.international.admin.utils.GlobalParams;
import com.sms.international.admin.utils.MyUtils_Time;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 【业务报表控制层】
 * @author yannannan
 * @date 2017-12-12
 * @desc
 */
@RequestMapping("/reportStatis")  
@Controller
@Scope("prototype")
public class SmsReportStatisticsController extends ParentController{      
 
	@Autowired
	private SmsReportStatisticsService smsReportStatisticsService;
	
	 //菜单事件tab初始化到通道实时报表
	 @RequestMapping("/init")
	 public String init(ModelMap modelMap){
		 modelMap.put("minuteChannel", GlobalParams.CHANNEL_MAP.get(GlobalParams.CHANNEL_KEY));
		 return "/report/init";
	 }
	
	 
	 
	 /**
	  * ********【通道实时报表】统计信息开始
	  */
	 //点击实时报表按钮初始化
	 @RequestMapping("/initMinute")
	 public String initChannelMinute(ModelMap modelMap){
		 modelMap.put("minuteChannel", GlobalParams.CHANNEL_MAP.get(GlobalParams.CHANNEL_KEY));
		 return "/report/channel_minute_report";
	 }
	 
	 @RequestMapping("/channelMinute")
	 @ResponseBody
	 public String findChannelMinute(@RequestBody SmsReportChannel smsReportChannel,ModelMap modelMap){
		 try{
			 JSONObject jsonStr=smsReportStatisticsService.findRealTimeChannelReport(smsReportChannel);
			 if(jsonStr!=null){
				 return jsonStr.toString();
			 }
		 }catch(Exception e){
			 return null;
		 }
		 return null;
	 }
	 /**
	  * ***********【通道实时报表】结束
	  */
	 
	 
	 
	 
	 /**
	  * *********【通道日报表】统计信息**********
	  */
	 @RequestMapping("/initDay")
	 public String initChannelDay(ModelMap modelMap){
		 modelMap.put("minuteChannel", GlobalParams.CHANNEL_MAP.get(GlobalParams.CHANNEL_KEY));
		 return "/report/channel_day_report";
	 }
	 
	 @RequestMapping("/channelDay") 
	 @ResponseBody
	 public String findChannelDay(@RequestBody SmsReportChannel smsReportChannel,ModelMap modelMap){ 
		 try{
			 if(smsReportChannel.getStartTime()==null){
				 smsReportChannel.setStartTime(Long.valueOf(MyUtils_Time.getTimeFormat(new Date(), "yyyyMMdd")));
			 }
			 JSONObject jsonChannelDayStr=smsReportStatisticsService.findChannelDayList(smsReportChannel);
			 if(jsonChannelDayStr!=null){
				 System.out.println("通道日报表："+jsonChannelDayStr);
				 return jsonChannelDayStr.toString();
			 }
		 }catch(Exception e){
			 return null;
		 }
		 return null;
	 }
	 
	 @RequestMapping("/channelSumDay")
	 @ResponseBody
	 public String findChannelSumDay(HttpServletRequest request, @ModelAttribute SmsReportChannel smsReportChannel){
		 try{
			 if(smsReportChannel.getStartTime()==null){
				 smsReportChannel.setStartTime(Long.valueOf(MyUtils_Time.getTimeFormat(new Date(), "yyyyMMdd")));
			 }
			 SmsReportChannel sumObj= smsReportStatisticsService.findChannelDaySum(smsReportChannel);
			 if(sumObj!=null){
				 String sumJsonStr=JSONObject.fromObject(sumObj).toString();
				 System.out.println("通道日报表汇总统计："+sumJsonStr);
				 return sumJsonStr;
			 }
		 }catch(Exception e){
			return null;
		 }
		 return null;
	 }
	 /**
	  * *********【通道日报表】统计信息结束********
	  */
	 
	 
	 
	 /**
	  * *********【通道月报表】统计信息开始
	  */
	 @RequestMapping("/initMonth")
	 public String initChannelMonth(ModelMap modelMap){
		 modelMap.put("minuteChannel", GlobalParams.CHANNEL_MAP.get(GlobalParams.CHANNEL_KEY));
		 return "/report/channel_month_report";
	 }
	 /**
	  * *********【通道月报表】统计信息开始
	  */
	 @RequestMapping("/channelMonth")
	 @ResponseBody
	 public String findChannelMonth(@RequestBody SmsReportChannel smsReportChannel,ModelMap modelMap){ 
		 try{
			 SimpleDateFormat sdf=new SimpleDateFormat("yyyyMM");
				if(smsReportChannel.getMonth()==null){
					smsReportChannel.setMonth(sdf.format(new Date()));
				}
				JSONObject jsonChannelMonthStr=smsReportStatisticsService.findChannelMonthList(smsReportChannel);
				if(jsonChannelMonthStr!=null){
					return jsonChannelMonthStr.toString();
			    }
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return null;
	 }
	 
	 @RequestMapping("/channelSumMonth")
	 @ResponseBody
	 public String findChannelSumMonth(HttpServletRequest request, @ModelAttribute SmsReportChannel smsReportChannel){
		 try{
			 if(smsReportChannel.getMonth() ==null||smsReportChannel.getMonth().equals("")){
				 smsReportChannel.setMonth(MyUtils_Time.getTimeFormat(new Date(), "yyyyMM"));
			 }
			 SmsReportChannel sumObj= smsReportStatisticsService.findChannelMonthSum(smsReportChannel);
			 	if(sumObj!=null){
			 		 String sumJsonStr=JSONObject.fromObject(sumObj).toString();
					 System.out.println("通道月报表汇总统计："+sumJsonStr);
					 return sumJsonStr;
			 	}
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return null;
	 }
	 
	 
	 
	 /**
	  * 【打开初始化通道报表详情】
	  */
	 @RequestMapping("/initChDetail")
	 public String initChannelDayDetail(ModelMap modelMap,@ModelAttribute SmsReportChannel smsReportChannel){
		 modelMap.put("minuteChannel", GlobalParams.CHANNEL_MAP.get(GlobalParams.CHANNEL_KEY));
		 modelMap.put("obj",smsReportChannel);
		 return "/report/channelDayDetail";
	 }
	 
	 /**
	  * 【查询通道单日详情信息】
	  */
	 @RequestMapping("/findChannelDayDetail")
	 @ResponseBody
	 public String findChannelDayDetail(@RequestBody SmsReportChannel smsReportChannel){
		try{
			 if(smsReportChannel.getStartTime()==null){
				 smsReportChannel.setStartTime(Long.valueOf(MyUtils_Time.getTimeFormat(new Date(), "yyyyMMdd")));
			 }
			 JSONObject channelDayDetailJson=smsReportStatisticsService.findChannelDayDetail(smsReportChannel);
				if(channelDayDetailJson!=null){
					return channelDayDetailJson.toString();
			    }
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	 }
	 
	 
	 
	 
	 /**
	  * 【通道月详情信息】
	  */
	 @RequestMapping("/initChMonthDetail")
	 public String initChannelMonthDetail(ModelMap modelMap,@ModelAttribute SmsReportChannel smsReportChannel){
		 modelMap.put("minuteChannel", GlobalParams.CHANNEL_MAP.get(GlobalParams.CHANNEL_KEY));
		 modelMap.put("obj",smsReportChannel);
		 return "/report/channelMonthDetail";
	 }
	 /**
	  * 【查询通道月详情信息】
	  */
	 @RequestMapping("/findChannelMonthDetail")
	 @ResponseBody
	 public String findChannelMonthDetail(@RequestBody SmsReportChannel smsReportChannel){
		 try{
			 SimpleDateFormat sdf=new SimpleDateFormat("yyyyMM");
				if(smsReportChannel.getMonth()==null){
					smsReportChannel.setMonth(sdf.format(new Date()));
				}
				JSONObject jsonChannelMonthStr=smsReportStatisticsService.findChannelMonthDetail(smsReportChannel);
				if(jsonChannelMonthStr!=null){
					return jsonChannelMonthStr.toString();
			    }
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		return null;
	 }
	 
}
