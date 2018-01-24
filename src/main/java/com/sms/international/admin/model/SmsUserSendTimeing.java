package com.sms.international.admin.model;

public class SmsUserSendTimeing extends BaseEntity {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Integer mtype;

	private Long sendtime;

	private Long startTime;

	private Long endTime;

	private Long submitTime;

	private Integer uid;

	private Integer channel;

	private Integer contentNum;

	private Integer mobileNum;

	private Short stat;

	private Short isrelease;

	private Integer pid;

	private Long mobile;

	private String content;

	private Short period;

	private Integer periodNum;

	private String viewSendTime;

	private String viewSubmitTime;

	public Integer getMtype() {
		return mtype;
	}

	public void setMtype(Integer mtype) {
		this.mtype = mtype;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public Integer getContentNum() {
		return contentNum;
	}

	public void setContentNum(Integer contentNum) {
		this.contentNum = contentNum;
	}

	public Integer getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(Integer mobileNum) {
		this.mobileNum = mobileNum;
	}

	public Short getStat() {
		return stat;
	}

	public void setStat(Short stat) {
		this.stat = stat;
	}

	public Short getIsrelease() {
		return isrelease;
	}

	public void setIsrelease(Short isrelease) {
		this.isrelease = isrelease;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public Short getPeriod() {
		return period;
	}

	public void setPeriod(Short period) {
		this.period = period;
	}

	public Integer getPeriodNum() {
		return periodNum;
	}

	public void setPeriodNum(Integer periodNum) {
		this.periodNum = periodNum;
	}

	public Long getSendtime() {
		return sendtime;
	}

	public Long getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Long submitTime) {
		this.submitTime = submitTime;
	}

	public String getViewSendTime() {
		if (sendtime != null && sendtime > 0 && (sendtime + "").length() >= 14) {
			String temp = sendtime + "";
			viewSendTime = temp.substring(0, 4) + "-" + temp.substring(4, 6) + "-" + temp.substring(6, 8) + " " + temp.substring(8, 10) + ":" + temp.substring(10, 12) + ":"
					+ temp.substring(12, 14);
		}
		return viewSendTime;
	}

	public String getViewSubmitTime() {
		if (submitTime != null && submitTime > 0 && (submitTime + "").length() >= 14) {
			String temp = submitTime + "";
			viewSubmitTime = temp.substring(0, 4) + "-" + temp.substring(4, 6) + "-" + temp.substring(6, 8) + " " + temp.substring(8, 10) + ":" + temp.substring(10, 12) + ":"
					+ temp.substring(12, 14);
		}
		return viewSubmitTime;
	}

	public void setSendtime(Long sendtime) {
		this.sendtime = sendtime;
	}

	public Long getStartTime() {
		return startTime;
	}


	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
}