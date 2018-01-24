package com.sms.international.admin.service;

import com.sms.international.admin.model.SmsUserReport;
import net.sf.json.JSONObject;

/**
 * 【用户报表】
 * @author yannannan
 * @date 2017-12-13
 * @desc
 */
public interface SmsUserReportService {
	
	 /**
	  * 【查询用户日报表信息】
	  */
	public JSONObject findSmsUserDayReportList(SmsUserReport smsUserReport);
	public SmsUserReport findSmsUserDayReportSum(SmsUserReport smsUserReport);
	public SmsUserReport findSmsUserDayReportSumSend(SmsUserReport smsUserReport);
	public JSONObject findUserDayDetail(SmsUserReport smsUserReport);
	
	
	/********【用户月报表】************/
	public JSONObject findSmsUserMonthReportList(SmsUserReport smsUserReport);
	public JSONObject findUserMonthDetail(SmsUserReport smsUserReport);
	
	
	
	/*************【用户账单】***************/
	public JSONObject findUserBillList(SmsUserReport smsUserReport);
}
