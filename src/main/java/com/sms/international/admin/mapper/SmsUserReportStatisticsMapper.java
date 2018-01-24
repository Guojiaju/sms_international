package com.sms.international.admin.mapper;

import com.sms.international.admin.model.SmsUserReport;

import java.util.List;

/**
 * 【用户报表分析及统计】
 * @author yannannan
 * @date 2017-12-13
 * @desc
 */
public interface SmsUserReportStatisticsMapper {
	
	 /**
	  * *******【查询用户日报表信息】**********
	  */
	public List<SmsUserReport> findSmsUserDayReportList(SmsUserReport smsUserReport);
	public Integer findSmsUserDayReportCount(SmsUserReport smsUserReport);
	public SmsUserReport findSmsUserDayReportSum(SmsUserReport smsUserReport);
	public SmsUserReport findSmsUserDayReportSumSend(SmsUserReport smsUserReport);
	public Integer findUserDayDetailCount(SmsUserReport smsUserReport);
	public List<SmsUserReport> findUserDayDetailList(SmsUserReport smsUserReport);
	
	/***********【用户月报表】***********/
	public List<SmsUserReport> findSmsUserMonthReportList(SmsUserReport smsUserReport);
	public Integer findSmsUserMonthReportCount(SmsUserReport smsUserReport);
	public Integer findUserMonthDetailCount(SmsUserReport smsUserReport);
	public List<SmsUserReport> findUserMonthDetailList(SmsUserReport smsUserReport);
	
	
	/*************【用户账单】***************/
	public List<SmsUserReport> findUserBillList(SmsUserReport smsUserReport);
	public Integer findUserBillCount(SmsUserReport smsUserReport);
	public SmsUserReport findUserBillSum(SmsUserReport smsUserReport);
}
