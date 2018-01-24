package com.sms.international.admin.controller;

import com.sms.international.admin.model.SmsUserReport;
import com.sms.international.admin.service.SmsUserReportService;
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
import java.util.Calendar;
import java.util.Date;

/**
 * 【用户报表控制类】
 * @author yannannan
 * @date 2017-12-13
 * @desc
 */
@RequestMapping("/userReport")
@Controller
@Scope("prototype")
public class SmsUserReportStatisticsController extends ParentController{    

	@Autowired
	private SmsUserReportService smsUserReportService;

	 /***************【用户报表开始】******************/
	 @RequestMapping("/init")
	 public String initUser(ModelMap modelMap){
		 return "/userReport/init";
	 }
	 
	 
	 @RequestMapping("/initUserDay")
	 public String initUserDay(ModelMap modelMap){
		 return "/userReport/user_day_report";
	 }
	 
	 @RequestMapping("/initUserMonth")
	 public String initUserMonth(ModelMap modelMap){
		 return "/userReport/user_month_report";
	 }
	 
	 @RequestMapping("/initUserBill")
	 public String initUserBill(ModelMap modelMap){
		 return "/userReport/user_bill";
	 }
	 
	 
	 
	 /**
	  * *******【查询用户日报表】
	  */
	 @RequestMapping("/userDayReport") 
	 @ResponseBody
	 public String findUserDayReport(@RequestBody SmsUserReport smsUserReport){  
		 try{ 
			   if(smsUserReport.getStartTime()==null){
				 smsUserReport.setStartTime(Long.valueOf(MyUtils_Time.getTimeFormat(new Date(), "yyyyMMdd")));
				}
			 String jsonStr=smsUserReportService.findSmsUserDayReportList(smsUserReport).toString();
			 System.out.println("用户日报表："+jsonStr);
			 return jsonStr;
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return null;
	 }
	 
	 @RequestMapping("/userDaySum")
	 @ResponseBody
	 public String findUserReportSumDay(HttpServletRequest request, @ModelAttribute SmsUserReport smsUserReport){
		 try{
			  if(smsUserReport.getStartTime()==null){
				 smsUserReport.setStartTime(Long.valueOf(MyUtils_Time.getTimeFormat(new Date(), "yyyyMMdd")));
				}
			  SmsUserReport sumObj=smsUserReportService.findSmsUserDayReportSum(smsUserReport);
			  if(sumObj!=null){
				  sumObj.setSubmit_success(sumObj.getTotal()-sumObj.getFail());
				  sumObj.setNorpt_count(sumObj.getTotal()-(sumObj.getArrive_succ()+sumObj.getArrive_fail()+sumObj.getFail()));
				  String userDaySumStr=JSONObject.fromObject(sumObj).toString();
				  System.out.println("用户日报表汇总统计："+userDaySumStr);
				  return userDaySumStr;
			  }
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return null;
	 }
	 
	 /***************【用户日报表结束】******************/
	
	 /***********【用户月报表开始】****************/
	 @RequestMapping("/userMonthReport") 
	 @ResponseBody
	 public String findUserMonthReport(@RequestBody SmsUserReport smsUserReport){  
		 try{ 
			 SimpleDateFormat sdf=new SimpleDateFormat("yyyyMM");
				if(smsUserReport.getMonth()==null){
					smsUserReport.setMonth(sdf.format(new Date()));
				}
				JSONObject jsonObjStr=smsUserReportService.findSmsUserMonthReportList(smsUserReport);
				if(jsonObjStr!=null){
					System.out.println("用户月报表"+jsonObjStr.toString());
					return jsonObjStr.toString();
				}
				return null;
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return null;
	 }
	 
	 @RequestMapping("/userMonthSum")   
	 @ResponseBody
	 public String findUserReportSumMonth(HttpServletRequest request, @ModelAttribute SmsUserReport smsUserReport){
		 try{
			  if(smsUserReport.getMonth()==null){
				 smsUserReport.setMonth(MyUtils_Time.getTimeFormat(new Date(), "yyyyMMdd"));
				}
			  SmsUserReport sumObj=smsUserReportService.findSmsUserDayReportSum(smsUserReport);
			  SmsUserReport unknowSum=smsUserReportService.findSmsUserDayReportSumSend(smsUserReport);
			  if(sumObj!=null&&unknowSum!=null){
				  sumObj.setNorpt_count(sumObj.getTotal()-sumObj.getArrive_succ()-sumObj.getArrive_fail()-sumObj.getFail());
				  sumObj.setUnsend(unknowSum.getUnsend());
				  String monthSumStr=JSONObject.fromObject(sumObj).toString();
				  System.out.println("用户报表月汇总统计："+monthSumStr);
				  return monthSumStr;
			  }
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return null;
	 }
	 
	 /***********【用户月报表结束】****************/

	 
	 /**
	  * *******【用户日报表详情】*******
	  */
	 /**
	  * 【打开初始化用户日报表详情】
	  */
	 @RequestMapping("/initUserDayDetail") 
	 public String initChannelDayDetail(ModelMap modelMap,@ModelAttribute SmsUserReport smsUserReport){
		 try{
			 modelMap.put("obj", smsUserReport);
			 return "userReport/userDayDetail";
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return null;
	 }
	 /**
	  * 【查询用户日报表详情列表】
	  */
	 @RequestMapping("/findUserDayDetail")
	 @ResponseBody
	 public String findUserDayDetail(@RequestBody SmsUserReport smsUserReport){
		 try{ 
			   if(smsUserReport.getStartTime()==null){
				 smsUserReport.setStartTime(Long.valueOf(MyUtils_Time.getTimeFormat(new Date(), "yyyyMMdd")));
				}
			   JSONObject jsonStr=smsUserReportService.findUserDayDetail(smsUserReport);
			   if(jsonStr!=null){
				   System.out.println("用户日报表："+jsonStr);
					return jsonStr.toString();
			   }
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return null;
	 }
	 
	 
	 
	 
/***************【用户账单】开始******************/
	 @RequestMapping("/userBillReport") 
	 @ResponseBody
	 public String userBillReport(@RequestBody SmsUserReport smsUserReport){  
		 try{ 
			 if(smsUserReport.getStartTime()==null){
				 smsUserReport.setStartTime(Long.valueOf(getYMD(0)));
				}
				if(smsUserReport.getEndTime()==null){
					smsUserReport.setEndTime(Long.valueOf(getYMD(0)));
				}
			JSONObject jsonObjStr=smsUserReportService.findUserBillList(smsUserReport);
			if(jsonObjStr!=null){
				System.out.println("用户账单："+jsonObjStr);
				return jsonObjStr.toString();
			}
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return null;
	 }
	 @RequestMapping("/userBillSum")
	 @ResponseBody
	 public String userBillSum(HttpServletRequest request, @ModelAttribute SmsUserReport smsUserReport){
		 try{
			 if(smsUserReport.getStartTime()==null){
				 smsUserReport.setStartTime(Long.valueOf(getYMD(0)));
				}
				if(smsUserReport.getEndTime()==null){
					smsUserReport.setEndTime(Long.valueOf(getYMD(0)));
				}
				SmsUserReport userBillSum=smsUserReportService.findSmsUserDayReportSum(smsUserReport);
				if(userBillSum!=null){
					return JSONObject.fromObject(userBillSum).toString();
				}
		 }catch(Exception e){
			e.printStackTrace();
		 }
		 return null;
	 }
	 
	 
	 private String getYMD(int day){
			Calendar c = Calendar.getInstance();
			int day_ = c.get(Calendar.DATE);
			c.set(Calendar.DATE, day_ - day);
			return c.get(Calendar.YEAR)+"" + ((c.get(Calendar.MONTH)+1)<10?("0"+(c.get(Calendar.MONTH)+1)):(c.get(Calendar.MONTH)+1)) +"" +  (c.get(Calendar.DAY_OF_MONTH)<10?("0"+c.get(Calendar.DAY_OF_MONTH)):c.get(Calendar.DAY_OF_MONTH)); 
		}
	 
/***************【用户账单】结束******************/
	 
	 
	 /**
	  * *******【用户月报表详情】*******
	  */
	 /**
	  * 【打开初始化用户月报表详情】
	  */
	 @RequestMapping("/initUserMonthDetail") 
	 public String initChannelMonthDetail(ModelMap modelMap,@ModelAttribute SmsUserReport smsUserReport){
		 try{
			 modelMap.put("obj", smsUserReport);
			 return "userReport/userMonthDetail";
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return null;
	 }
	 /**
	  * 【查询用户月报表详情列表】
	  */
	 @RequestMapping("/findUserMonthDetail")
	 @ResponseBody
	 public String findUserMonthDetail(@RequestBody SmsUserReport smsUserReport){
		 try{ 
			 SimpleDateFormat sdf=new SimpleDateFormat("yyyyMM");
				if(smsUserReport.getMonth()==null){
					smsUserReport.setMonth(sdf.format(new Date()));
				}
			   JSONObject jsonStr=smsUserReportService.findUserMonthDetail(smsUserReport);
			   if(jsonStr!=null){
				   System.out.println("用户月报表："+jsonStr);
					return jsonStr.toString();
			   }
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return null;
	 }
	 
	 
}
