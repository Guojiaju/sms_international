package com.sms.international.admin.controller;

import com.sms.international.admin.model.SmsAnalysisChart;
import com.sms.international.admin.model.SmsCountryInfos;
import com.sms.international.admin.service.SmsAnalysisChartService;
import com.sms.international.admin.utils.GlobalParams;
import com.sms.international.admin.utils.MyUtils_Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("/analysis") 
@Controller
@Scope("prototype")
public class SmsAnalysisChartController extends ParentController{

	@Autowired
	private SmsAnalysisChartService smsAnalysisChartService;
	
	
	protected static String getTargetDateFirstDayAndLastDay(String yyyyMM){
		try{
			Calendar calendar=new GregorianCalendar();  
	        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");  
	        SimpleDateFormat mf=new SimpleDateFormat("yyyyMM");  
	        Date date=mf.parse(yyyyMM);
	        calendar.setTime(date);  
	        calendar.add(calendar.DATE, 0);//因为格式化时默认了DATE为本月第一天所以此处为0  
	        String startTime=sdf.format(calendar.getTime());  
	        calendar.roll(calendar.DATE, -1);//api解释roll()：向指定日历字段添加指定（有符号的）时间量，不更改更大的字段  
	        String endTime=sdf.format(calendar.getTime());
			return startTime+"#"+endTime;
			}catch(Exception e){
				e.printStackTrace();
			}
		return null;
	}
	
	/**
	 * 【单日国际短信发送趋势图】【单日所有用户汇总趋势图】
	 */
	@RequestMapping("/initDayChart")
	public String findDayChart(HttpServletRequest request, @ModelAttribute SmsAnalysisChart smsAnalysisChart, ModelMap model){
		try{
			if(smsAnalysisChart.getStartTime()==null||smsAnalysisChart.getStartTime().equals("")){
				SimpleDateFormat sdf=new SimpleDateFormat("yyyyMM");
				String endTime=sdf.format(new Date(System.currentTimeMillis()));//今天
				String[] startTimeAndEndTime=getTargetDateFirstDayAndLastDay(endTime).split("#");//初始化获取当月第一天和最后一天
				smsAnalysisChart.setStartTime(Integer.parseInt(startTimeAndEndTime[0]));//设置查询当月开始时间
				smsAnalysisChart.setEndTime(Integer.parseInt(startTimeAndEndTime[1]));//设置查询当月结束时间
			}else{
				String[] startTimeAndEndTime=getTargetDateFirstDayAndLastDay(smsAnalysisChart.getStartTime()+"").split("#");//初始化获取当月第一天和最后一天
				smsAnalysisChart.setStartTime(Integer.parseInt(startTimeAndEndTime[0]));//设置查询当月开始时间
				smsAnalysisChart.setEndTime(Integer.parseInt(startTimeAndEndTime[1]));//设置查询当月结束时间
			}
				
			List<SmsAnalysisChart> lineAllUserSendDayChartList=smsAnalysisChartService.findAllUserSendDayCount(smsAnalysisChart);
			StringBuilder dayBuilder=new StringBuilder();
			StringBuilder sendBuilder=new StringBuilder();
			for(SmsAnalysisChart smsuserSendCountObj:lineAllUserSendDayChartList){
				String dayStr=(smsuserSendCountObj.getBill_time()+"").substring(6,8);
				dayBuilder.append(",\""+dayStr+"日\"");
				sendBuilder.append(","+smsuserSendCountObj.getSend_count());//获取所有用户单日发送总量
			}
			String allUserSuccessLineChart=initAllUserSuccessLineChart(smsAnalysisChart);
			String allUserFailLineChart=initAllUserFailLineChart(smsAnalysisChart);
			initSmsAllUserMonthCountToLineChart(smsAnalysisChart,model);//继续初始化月发送汇总总量//已设置model中
			String startTime=smsAnalysisChart.getStartTime()+"";
			smsAnalysisChart.setStartTime(Integer.parseInt(startTime.substring(0,6)));
			if(dayBuilder.toString()!=null&&!dayBuilder.toString().equals("")&&sendBuilder.toString()!=null&&!sendBuilder.toString().equals("")){
				String allUserLineChart=dayBuilder.toString().substring(1,dayBuilder.toString().length())+"#"+sendBuilder.toString().substring(1,sendBuilder.toString().length());
				model.put("allUserLineChart",allUserLineChart);
				model.put("allUserSuccessLineChart",allUserSuccessLineChart);
				model.put("allUserFailLineChart",allUserFailLineChart);
			}
			model.put("smsUserAnalysis", smsAnalysisChart);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/smsAnalysisChart/smsAnalysisDayChart";
	}
	
	/**
	 * 【查询所有用户发送成功总量折线图】
	 * @param yyyyMM
	 * @return
	 */
	public String initAllUserSuccessLineChart(SmsAnalysisChart smsAnalysisChart){
		try{
			List<SmsAnalysisChart> lineAllUserSuccessSendDayChartList=smsAnalysisChartService.findAllUserSuccessSendDayCount(smsAnalysisChart);
			StringBuilder dayBuilder=new StringBuilder();
			StringBuilder sendBuilder=new StringBuilder();
			for(SmsAnalysisChart smsuserSendCountObj:lineAllUserSuccessSendDayChartList){
				String dayStr=(smsuserSendCountObj.getBill_time()+"").substring(6,8);
				dayBuilder.append(",\""+dayStr+"日\"");
				sendBuilder.append(","+smsuserSendCountObj.getSend_count());//获取所有用户单日发送总量
			}
			if(dayBuilder.toString()!=null&&!dayBuilder.toString().equals("")&&sendBuilder.toString()!=null&&!sendBuilder.toString().equals("")){
				String allUserLineSuccessChart=dayBuilder.toString().substring(1,dayBuilder.toString().length())+"#"+sendBuilder.toString().substring(1,sendBuilder.toString().length());
				return allUserLineSuccessChart;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 【查询所有用户发送失败总量折线图】
	 * @param yyyyMM
	 * @return
	 */
	public String initAllUserFailLineChart(SmsAnalysisChart smsAnalysisChart){
		try{
			List<SmsAnalysisChart> lineAllUserFailSendDayChartList=smsAnalysisChartService.findAllUserFailSendDayCount(smsAnalysisChart);
			StringBuilder dayBuilder=new StringBuilder();
			StringBuilder sendBuilder=new StringBuilder();
			for(SmsAnalysisChart smsuserSendCountObj:lineAllUserFailSendDayChartList){
				String dayStr=(smsuserSendCountObj.getBill_time()+"").substring(6,8);
				dayBuilder.append(",\""+dayStr+"日\"");
				sendBuilder.append(","+smsuserSendCountObj.getSend_count());//获取所有用户单日发送总量
			}
			if(dayBuilder.toString()!=null&&!dayBuilder.toString().equals("")&&sendBuilder.toString()!=null&&!sendBuilder.toString().equals("")){
				String allUserLineFailChart=dayBuilder.toString().substring(1,dayBuilder.toString().length())+"#"+sendBuilder.toString().substring(1,sendBuilder.toString().length());
				return allUserLineFailChart;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 【初始化】【日线统计月汇总数量】
	 * @param yyyyMM
	 * @return
	 */
	public void initSmsAllUserMonthCountToLineChart(SmsAnalysisChart smsAnalysisChart, ModelMap model){
		try{
			Integer monthSendCount=smsAnalysisChartService.findAllUserMonthAllSendCount(smsAnalysisChart);
			Integer monthSuccessCount=smsAnalysisChartService.findAllUserMonthAllSuccessCount(smsAnalysisChart);
			Integer monthFailCount=smsAnalysisChartService.findAllUserMonthAllFailCount(smsAnalysisChart);
			if(monthSendCount!=null&&monthSendCount>0){
				model.put("monthSendCount",monthSendCount);
			}else{
				model.put("monthSendCount",0);
			}
			if(monthSuccessCount!=null&&monthSuccessCount>0){
				model.put("monthSuccessCount",monthSuccessCount);
			}else{
				model.put("monthSuccessCount",0);
			}
			if(monthFailCount!=null&&monthFailCount>0){
				model.put("monthFailCount",monthFailCount);
			}else{
				model.put("monthFailCount",0);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	
/******************【月线统计】开始************************/
		/**
		 * 【月线统计入口】
		 */
	@RequestMapping("/initMonthChart")
	 public String initMonthChart(HttpServletRequest request, @ModelAttribute SmsAnalysisChart smsAnalysisChart, ModelMap model){
		 try{
				if(smsAnalysisChart.getStartTime()==null&&smsAnalysisChart.getEndTime()==null){
					SimpleDateFormat sdfYear=new SimpleDateFormat("yyyy");
					String todayYearTime=sdfYear.format(new Date(System.currentTimeMillis()))+"0101";//今天
					smsAnalysisChart.setStartTime(Integer.parseInt(todayYearTime));
					SimpleDateFormat sdf=new SimpleDateFormat("yyyyMM");
					String todayEndtime=sdf.format(new Date(System.currentTimeMillis()));//今天
					String[] startTimeAndEndTime=getTargetDateFirstDayAndLastDay(todayEndtime).split("#");//初始化获取当月第一天和最后一天
					smsAnalysisChart.setEndTime(Integer.parseInt(startTimeAndEndTime[1]));//设置查询当月结束时间
				}else if(smsAnalysisChart.getStartTime()!=null&&smsAnalysisChart.getEndTime()==null){//开始至今
					String[] startTimeAndEndTime=getTargetDateFirstDayAndLastDay(smsAnalysisChart.getStartTime()+"").split("#");//初始化获取当月第一天和最后一天
					smsAnalysisChart.setStartTime(Integer.parseInt(startTimeAndEndTime[0]));//设置用户选择查询当月开始时间
					SimpleDateFormat sdf=new SimpleDateFormat("yyyyMM");
					//***********开始至今【结束日期没有选择】
					String endTime=sdf.format(new Date(System.currentTimeMillis()));//今天
					String[] todayStartTimeAndEndTime=getTargetDateFirstDayAndLastDay(endTime).split("#");//初始化获取当月第一天和最后一天
					smsAnalysisChart.setEndTime(Integer.parseInt(todayStartTimeAndEndTime[1]));//设置查询结束日期为今天的最后一天
				}else if(smsAnalysisChart.getStartTime()!=null&&smsAnalysisChart.getEndTime()!=null){
					String[] startTimeAndEndTime=getTargetDateFirstDayAndLastDay(smsAnalysisChart.getStartTime()+"").split("#");//初始化获取当月第一天和最后一天
					smsAnalysisChart.setStartTime(Integer.parseInt(startTimeAndEndTime[0]));//设置用户选择开始时间的第一天
					String[] endTimeAndEndTime=getTargetDateFirstDayAndLastDay(smsAnalysisChart.getEndTime()+"").split("#");//初始化获取当月第一天和最后一天
					smsAnalysisChart.setEndTime(Integer.parseInt(endTimeAndEndTime[1]));//设置用户选择结束日期的最后一天
				}
					
				List<SmsAnalysisChart> lineAllSingUserSendMonthChartList=smsAnalysisChartService.findAllUserSingMonthSendCount(smsAnalysisChart);
				StringBuilder dayBuilder=new StringBuilder();
				StringBuilder sendBuilder=new StringBuilder();
				for(SmsAnalysisChart smsuserSendCountObj:lineAllSingUserSendMonthChartList){
					String monthStr=(smsuserSendCountObj.getBill_time()+"").substring(2,6);
					dayBuilder.append(",\""+monthStr+"月\"");
					sendBuilder.append(","+smsuserSendCountObj.getSend_count());//获取所有用户单日发送总量
				}
				String allUserSuccessLineChart=initAllUserSingMonthSuccessLineChart(smsAnalysisChart);
				String allUserFailLineChart=initAllUserSingMonthFailLineChart(smsAnalysisChart);
				initSmsAllUserMonthCountToLineChart(smsAnalysisChart,model);//继续初始化月发送汇总总量//已设置model中
				if(dayBuilder.toString()!=null&&!dayBuilder.toString().equals("")&&sendBuilder.toString()!=null&&!sendBuilder.toString().equals("")){
					String allUserLineChart=dayBuilder.toString().substring(1,dayBuilder.toString().length())+"#"+sendBuilder.toString().substring(1,sendBuilder.toString().length());
					String startTime=smsAnalysisChart.getStartTime()+"";
					String endTime=smsAnalysisChart.getEndTime()+"";
					smsAnalysisChart.setStartTime(Integer.parseInt(startTime.substring(0,6)));
					smsAnalysisChart.setEndTime(Integer.parseInt(endTime.substring(0,6)));
					model.put("allUserMonthLineChart",allUserLineChart);
					model.put("allUserMonthSuccessLineChart",allUserSuccessLineChart);
					model.put("allUserMonthFailLineChart",allUserFailLineChart);
				}
				model.put("smsUserAnalysis", smsAnalysisChart);
			}catch(Exception e){
				e.printStackTrace();
			}
			return "/smsAnalysisChart/smsAnalysisMonthChart"; 
	 }
	 
	 /**
		 * 【初始化】【月线统计发送成功趋势图】
		 */
		public String initAllUserSingMonthSuccessLineChart(SmsAnalysisChart smsAnalysisChart){
			try{
				List<SmsAnalysisChart> lineAllUserSuccessSendMonthChartList=smsAnalysisChartService.findAllUserSingMongthSuccessCount(smsAnalysisChart);
				StringBuilder dayBuilder=new StringBuilder();
				StringBuilder sendBuilder=new StringBuilder();
				for(SmsAnalysisChart smsuserSendCountObj:lineAllUserSuccessSendMonthChartList){
					String monthStr=(smsuserSendCountObj.getBill_time()+"").substring(2,6);
					dayBuilder.append(",\""+monthStr+"月\"");
					sendBuilder.append(","+smsuserSendCountObj.getSend_count());//获取所有用户单日发送总量
				}
				if(dayBuilder.toString()!=null&&!dayBuilder.toString().equals("")&&sendBuilder.toString()!=null&&!sendBuilder.toString().equals("")){
					String allUserLineSuccessChart=dayBuilder.toString().substring(1,dayBuilder.toString().length())+"#"+sendBuilder.toString().substring(1,sendBuilder.toString().length());
					return allUserLineSuccessChart;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;
		}
		
		/**
		 * 【初始化】【月线统计发送失败趋势图】
		 */
		public String initAllUserSingMonthFailLineChart(SmsAnalysisChart smsAnalysisChart){
			try{
				List<SmsAnalysisChart> lineAllUserSingMonthFailChartList=smsAnalysisChartService.findAllUserSingMongthFailCount(smsAnalysisChart);
				StringBuilder dayBuilder=new StringBuilder();
				StringBuilder sendBuilder=new StringBuilder();
				for(SmsAnalysisChart smsuserSendCountObj:lineAllUserSingMonthFailChartList){
					String dayStr=(smsuserSendCountObj.getBill_time()+"").substring(2,6);
					dayBuilder.append(",\""+dayStr+"月\"");
					sendBuilder.append(","+smsuserSendCountObj.getSend_count());//获取所有用户单日发送总量
				}
				if(dayBuilder.toString()!=null&&!dayBuilder.toString().equals("")&&sendBuilder.toString()!=null&&!sendBuilder.toString().equals("")){
					String allUserLineFailChart=dayBuilder.toString().substring(1,dayBuilder.toString().length())+"#"+sendBuilder.toString().substring(1,sendBuilder.toString().length());
					return allUserLineFailChart;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;
			
		}
	 
/******************【月线统计】结束************************/
		
		/**
		 * 【国家统计】【当天统计】
		 */
		@RequestMapping("/initCountryChart")
		public String initCountryDayChart(HttpServletRequest request, @ModelAttribute SmsAnalysisChart smsAnalysisChart, ModelMap model){
			try{
				Map<Integer,SmsCountryInfos> countryMap=GlobalParams.COUNTRY_ID_MAP;
				if(smsAnalysisChart.getBill_time()==null||smsAnalysisChart.getBill_time().equals("")){
					smsAnalysisChart.setBill_time(Integer.parseInt(MyUtils_Time.addDate(0)));
				}
				List<SmsAnalysisChart> lineCountryList=smsAnalysisChartService.findCountryDayChart(smsAnalysisChart);
				SmsAnalysisChart sumCountryAnalysis=smsAnalysisChartService.findCountryDaySumCount(smsAnalysisChart);
				StringBuilder countryBuilder=new StringBuilder();
				StringBuilder sendBuilder=new StringBuilder();
				StringBuilder priceBuilder=new StringBuilder();
				for(int i=0;i<lineCountryList.size();i++){
					if(i>0){
						countryBuilder.append(",");
						sendBuilder.append(",");
						priceBuilder.append(",");
					}
					 if(countryMap.containsKey(lineCountryList.get(i).getCountryId())){
						 lineCountryList.get(i).setCountry(countryMap.get(lineCountryList.get(i).getCountryId()).getCountry());
					 }
					countryBuilder.append("\""+lineCountryList.get(i).getCountry()+"\"");
					sendBuilder.append(lineCountryList.get(i).getSend_count());
					priceBuilder.append(lineCountryList.get(i).getUser_price());
				}
				if(countryBuilder!=null&&!countryBuilder.equals("")){
					model.put("countryNameDayChart",countryBuilder.toString());
					model.put("countrySendDayChart",sendBuilder.toString());
					model.put("countryPriceDayChart",priceBuilder.toString());
					model.put("sumCountryAnalysis",sumCountryAnalysis);
				}
				model.put("smsUserAnalysis", smsAnalysisChart);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return "/smsAnalysisChart/countryAnalysisDayChart";
		}
		
	
}
