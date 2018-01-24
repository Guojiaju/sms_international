package com.sms.international.admin.service;

import com.sms.international.admin.model.SmsGatewayChannel;
import com.sms.international.admin.model.SmsReportChannel;
import net.sf.json.JSONObject;

import java.util.Map;

public interface SmsReportStatisticsService {
	/**
	 * 【查询网管通道信息】
	 * @return
	 */
	public Map<Integer,String> findGatewayChannelList(SmsGatewayChannel smsGatewayChannel);
	
	
	/**
	 * 【通道实时报表】
	 */
	public JSONObject findRealTimeChannelReport(SmsReportChannel smsReportChannel);
	
	
	
	
	
	/*****************【通道日报表】开始****************/
		public JSONObject findChannelDayList(SmsReportChannel smsReportChannel);
		public SmsReportChannel findChannelDaySum(SmsReportChannel smsReportChannel);
	/*****************【通道日报表】结束****************/
	
		
		
	/*****************【通道月报表】开始**************/
		public JSONObject findChannelMonthList(SmsReportChannel smsReportChannel);
		public SmsReportChannel findChannelMonthSum(SmsReportChannel smsReportChannel);
	/*****************【通道月报表】结束**************/		
		
		
		
	/**
	 * 【查询通道单日详情信息国家】
	 */
	public JSONObject findChannelDayDetail(SmsReportChannel smsReportChannel);
	/**
	 * 【通道月详情信息国家】
	 */
	public JSONObject findChannelMonthDetail(SmsReportChannel smsReportChannel);
}
