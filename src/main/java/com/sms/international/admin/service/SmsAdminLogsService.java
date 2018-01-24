package com.sms.international.admin.service;

import com.sms.international.admin.model.SmsAdminLogs;
import net.sf.json.JSONObject;

/***
 * 管理员日志service接口
 * @author OYJM 
 * @date 2016年7月25日
 *
 */
public interface SmsAdminLogsService {
	/***
	 * 添加管理员日志
	 * @param smsAdminLogs
	 * @return
	 */
	int add(SmsAdminLogs smsAdminLogs) throws Exception;

	/**
	 * 分页查询操作日志
	 * @param logs
	 * @return
	 */
	JSONObject findAll(SmsAdminLogs logs);
	
}
