package com.sms.international.admin.utils;

import com.sms.international.admin.model.Admin;
import com.sms.international.admin.model.SmsAdminLogs;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.util.Date;


public class ConstantSys {
	private static Logger logger = Logger.getLogger(ConstantSys.class);
	private static String key2 = null;
	private static String key_value = null;

	public static String ROLE_SALES = "sale";
	public static String ROLE_ADMIN = "admin";
	public static String ROLE_FINANCE = "finance";
	public static String ROLE_CUST = "customerservice";
	public static Integer ROLE_ZUZHANG = 18;
	public static int[] ROLE_ADMIN_ARRAY = new int[]{1,14,16,18};
	public static int[] ROLE_VIEW_ADMIN_ARRAY = new int[]{1,7,8,14,16,18};
	public static Integer BASE_ADMIN_ID = 14;

	/** 1管理员 8客服 9业务 **/
	public static final Integer ROLE_ADMIN_ID = 1;
	public static final Integer ROLE_CUST_ID = 8;
	public static final Integer ROLE_SALES_ID = 9;

	/**
	 * groupType
	 */
	public static final Integer AUTO_BLACK_WORDS = 1;
	public static final Integer RELEASE_BLACK_WORDS = 2;
	public static final Integer BLACK_MOBILE = 3;
	public static final Integer WHITE_MOBILE = 5;
	public static final Integer WHITE_SIGN = 6;
	public static final Integer BLACK_SIGN = 7;


	public static String MONGODB_NAME = null;
	public static String MONGODB_HOST = null;
	public static int MONGODB_PORT = 0;

	public static String RABBIT_HOST = null;
	public static Integer RABBIT_PORT = null;
	public static String RABBIT_USERNAME = null;
	public static String RABBIT_PASSWORD = null;

	static {
		MONGODB_NAME = PropertiesUtils.getRabbit("MONGODB_NAME");
		MONGODB_HOST = PropertiesUtils.getRabbit("MONGODB_HOST");
		MONGODB_PORT = Integer.parseInt(PropertiesUtils.getRabbit("MONGODB_PORT"));

		RABBIT_HOST = PropertiesUtils.getRabbit("RABBIT_HOST");
		RABBIT_PORT = Integer.parseInt(PropertiesUtils.getRabbit("RABBIT_PORT"));
		RABBIT_USERNAME = PropertiesUtils.getRabbit("RABBIT_USERNAME");
		RABBIT_PASSWORD = PropertiesUtils.getRabbit("RABBIT_PASSWORD");
	}

	public static SmsAdminLogs adminLogs(String log, String ip, String module) {
    	Subject subject=SecurityUtils.getSubject();
	    Admin admin=(Admin)subject.getPrincipal();
		Integer uid = admin.getId();
		String user = admin.getUsername();
		SmsAdminLogs smsAdminLogs = new SmsAdminLogs();
		smsAdminLogs.setIp(ip);
		smsAdminLogs.setLog(user + log);
		smsAdminLogs.setTime(new Date());
		smsAdminLogs.setUid(uid);
		smsAdminLogs.setUser(user);
		smsAdminLogs.setModule(module);
		return smsAdminLogs;
	}
}