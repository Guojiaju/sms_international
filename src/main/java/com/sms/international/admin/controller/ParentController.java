package com.sms.international.admin.controller;

import com.sms.international.admin.model.Admin;
import com.sms.international.admin.model.SmsAdminLogs;
import com.sms.international.admin.service.SmsAdminLogsService;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 共用返回类型
 * 
 * @author CQL  331737188@qq.com
 * @date : 2015年10月12日 上午10:02:36
 *
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class  ParentController {
	
	private static Logger logger = Logger.getLogger(ParentController.class);
	
	@Autowired
    private SmsAdminLogsService smsAdminLogsService;

	protected Map<String, Object> resultMap = new HashMap();

    public Map<String, Object> getResultMap() {
	    return this.resultMap;
	  }

	public void setResultMap(Map<String, Object> resultMap) {
	    this.resultMap = resultMap;
	  }


	/**
	 * 查询当前登陆人信息
	 * @return
	 */
	public Admin getAdmin(){
		Subject subject=SecurityUtils.getSubject();
		return (Admin)subject.getPrincipal();
	}


	protected void adminLogs(String log, String ip) {
	    Admin admin= getAdmin();
		Integer uid = admin.getId();
		String user = admin.getUsername();
		SmsAdminLogs smsAdminLogs = new SmsAdminLogs();
		smsAdminLogs.setIp(ip);
		smsAdminLogs.setLog(user + log);
		smsAdminLogs.setTime(new Date());
		smsAdminLogs.setUid(uid);
		smsAdminLogs.setUser(user);
		try {
			smsAdminLogsService.add(smsAdminLogs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
