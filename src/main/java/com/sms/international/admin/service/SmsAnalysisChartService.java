package com.sms.international.admin.service;

import com.sms.international.admin.model.SmsAnalysisChart;

import java.util.List;

public interface SmsAnalysisChartService {
/*************【日线趋势图】开始****************/
	public List<SmsAnalysisChart> findAllUserSendDayCount(SmsAnalysisChart smsAnalysisChart);
	
	public List<SmsAnalysisChart> findAllUserSuccessSendDayCount(SmsAnalysisChart smsAnalysisChart);
	
	public List<SmsAnalysisChart> findAllUserFailSendDayCount(SmsAnalysisChart smsAnalysisChart);
	
	public Integer findAllUserMonthAllSendCount(SmsAnalysisChart smsAnalysisChart);
	
	public Integer findAllUserMonthAllSuccessCount(SmsAnalysisChart smsAnalysisChart);
	
	public Integer findAllUserMonthAllFailCount(SmsAnalysisChart smsAnalysisChart);
	
/*************【日线趋势图】结束****************/
	
	
/*************【月线趋势图】开始*****************/	
	public List<SmsAnalysisChart> findAllUserSingMonthSendCount(SmsAnalysisChart smsAnalysisChart);
	
	public List<SmsAnalysisChart> findAllUserSingMongthSuccessCount(SmsAnalysisChart smsAnalysisChart);
	
	public List<SmsAnalysisChart> findAllUserSingMongthFailCount(SmsAnalysisChart smsAnalysisChart);
	
/*************【月线趋势图】结束***********************/	
	
	
/****************【单日国家趋势图】*****************************/	
	public List<SmsAnalysisChart> findCountryDayChart(SmsAnalysisChart smsAnalysisChart);
	public SmsAnalysisChart findCountryDaySumCount(SmsAnalysisChart smsAnalysisChart);
/****************【单日国家趋势图】*****************************/
	
}
