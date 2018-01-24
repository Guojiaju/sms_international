package com.sms.international.admin.model;

import java.util.Date;

/***
 * 管理员日志model类
 * @author OYJM 
 * @date 2016年7月25日
 *
 */
public class SmsAdminLogs extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private Integer uid;

    private String user;

    private Date time;

    private String ip;

    private String log;
    
    private String module;

    private Integer type;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user == null ? null : user.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log == null ? null : log.trim();
    }

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}