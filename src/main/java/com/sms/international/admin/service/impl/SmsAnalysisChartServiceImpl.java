package com.sms.international.admin.service.impl;

import com.sms.international.admin.mapper.SmsAnalysisChartMapper;
import com.sms.international.admin.model.SmsAnalysisChart;
import com.sms.international.admin.service.SmsAnalysisChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("smsAnalysisChartService")
public class SmsAnalysisChartServiceImpl implements SmsAnalysisChartService{

	@Autowired
	private SmsAnalysisChartMapper smsAnalysisChartMapper;
	
	
/**************【单日发送量趋势图】开始***************/
	public List<SmsAnalysisChart> findAllUserSendDayCount(SmsAnalysisChart smsAnalysisChart){
		return smsAnalysisChartMapper.findAllUserSendDayCount(smsAnalysisChart);
	}
	public List<SmsAnalysisChart> findAllUserSuccessSendDayCount(SmsAnalysisChart smsAnalysisChart){
		return smsAnalysisChartMapper.findAllUserSuccessSendDayCount(smsAnalysisChart);
	}
	
	public List<SmsAnalysisChart> findAllUserFailSendDayCount(SmsAnalysisChart smsAnalysisChart){
		return smsAnalysisChartMapper.findAllUserFailSendDayCount(smsAnalysisChart);
	}
	
	public Integer findAllUserMonthAllSendCount(SmsAnalysisChart smsAnalysisChart){
		return smsAnalysisChartMapper.findAllUserMonthAllSendCount(smsAnalysisChart);
	}
	
	public Integer findAllUserMonthAllSuccessCount(SmsAnalysisChart smsAnalysisChart){
		return smsAnalysisChartMapper.findAllUserMonthAllSuccessCount(smsAnalysisChart);
	}
	
	public Integer findAllUserMonthAllFailCount(SmsAnalysisChart smsAnalysisChart){
		return smsAnalysisChartMapper.findAllUserMonthAllFailCount(smsAnalysisChart);
	}
	
/**************【单日发送量趋势图】结束***************/
	
	
/**************【月送量趋势图】开始***************/	
	public List<SmsAnalysisChart> findAllUserSingMonthSendCount(SmsAnalysisChart smsAnalysisChart){
		return smsAnalysisChartMapper.findAllUserSingMonthSendCount(smsAnalysisChart);
	}
		
	public List<SmsAnalysisChart> findAllUserSingMongthSuccessCount(SmsAnalysisChart smsAnalysisChart){
		return smsAnalysisChartMapper.findAllUserSingMongthSuccessCount(smsAnalysisChart);
	}
		
	public List<SmsAnalysisChart> findAllUserSingMongthFailCount(SmsAnalysisChart smsAnalysisChart){
		return smsAnalysisChartMapper.findAllUserSingMongthFailCount(smsAnalysisChart);
	}
/**************【月送量趋势图】结束***************/	

	
	
/****************【单日国家趋势图】*****************************/	
	public List<SmsAnalysisChart> findCountryDayChart(SmsAnalysisChart smsAnalysisChart){
		return smsAnalysisChartMapper.findCountryDayChart(smsAnalysisChart);
	}
	public SmsAnalysisChart findCountryDaySumCount(SmsAnalysisChart smsAnalysisChart){
		return smsAnalysisChartMapper.findCountryDaySumCount(smsAnalysisChart);
	}
/****************【单日国家趋势图】*****************************/	
	
	
}
