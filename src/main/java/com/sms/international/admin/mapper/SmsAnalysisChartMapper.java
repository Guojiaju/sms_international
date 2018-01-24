package com.sms.international.admin.mapper;

import com.sms.international.admin.model.SmsAnalysisChart;

import java.util.List;

public interface SmsAnalysisChartMapper {
	
/***********【单日发送量趋势图】开始************/
	public List<SmsAnalysisChart> findAllUserSendDayCount(SmsAnalysisChart smsAnalysisChart);
	public List<SmsAnalysisChart> findAllUserSuccessSendDayCount(SmsAnalysisChart smsAnalysisChart);
	public List<SmsAnalysisChart> findAllUserFailSendDayCount(SmsAnalysisChart smsAnalysisChart);
	
	public Integer findAllUserMonthAllSendCount(SmsAnalysisChart smsAnalysisChart);
	public Integer findAllUserMonthAllSuccessCount(SmsAnalysisChart smsAnalysisChart);
	public Integer findAllUserMonthAllFailCount(SmsAnalysisChart smsAnalysisChart);
/***********【单日发送量趋势图】结束************/
	
	
	
/***********【月送量趋势图】开始************/	
	public List<SmsAnalysisChart> findAllUserSingMonthSendCount(SmsAnalysisChart smsAnalysisChart);
	public List<SmsAnalysisChart> findAllUserSingMongthSuccessCount(SmsAnalysisChart smsAnalysisChart);
	public List<SmsAnalysisChart> findAllUserSingMongthFailCount(SmsAnalysisChart smsAnalysisChart);
/***********【月送量趋势图】结束************/	
	
/*************【单日国家统计趋势图】开始**************/	
	public List<SmsAnalysisChart> findCountryDayChart(SmsAnalysisChart smsAnalysisChart);
	public SmsAnalysisChart findCountryDaySumCount(SmsAnalysisChart smsAnalysisChart);
/*************【单日国家统计趋势图】结束**************/	
}
