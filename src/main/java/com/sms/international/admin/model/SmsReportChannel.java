package com.sms.international.admin.model;

import java.io.Serializable;


/****
 * 通道报表查询返回实体类
 * @author yannannan
 * @date 2016年7月26日
 *
 */
public class SmsReportChannel  extends BaseEntity implements Serializable {
	
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
    private Integer channelid;
	private String channelName;   //通道名称
	private String month;         //月份（按月查询时）
	private int submit_count;
	private int submit_succ;
	private int submit_fail;
	private int report_count;
	private int report_succ;
	private int report_fail;
	private long create_time;
	private String temp_time;
	private String success_rate;//成功率
	private String fail_rate;//失败率
	private int unknow_count;//未知量
	private String unknow_rate;//未知率
	private Long startTime;
	private Long endTime;
	private String country;//国际名称
	private Integer countryId;//国际编号
	
	public SmsReportChannel() {
		super();
	}
	public SmsReportChannel(Integer channelid, String channelName,
			String month, int submit_count, int submit_succ, int submit_fail,
			int report_count, int report_succ, int report_fail,
			long create_time, String temp_time, String success_rate,
			String fail_rate, int unknow_count, String unknow_rate,
			Long startTime, Long endTime, String country, Integer countryId) {
		super();
		this.channelid = channelid;
		this.channelName = channelName;
		this.month = month;
		this.submit_count = submit_count;
		this.submit_succ = submit_succ;
		this.submit_fail = submit_fail;
		this.report_count = report_count;
		this.report_succ = report_succ;
		this.report_fail = report_fail;
		this.create_time = create_time;
		this.temp_time = temp_time;
		this.success_rate = success_rate;
		this.fail_rate = fail_rate;
		this.unknow_count = unknow_count;
		this.unknow_rate = unknow_rate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.country = country;
		this.countryId = countryId;
	}
	public Integer getChannelid() {
		return channelid;
	}
	public void setChannelid(Integer channelid) {
		this.channelid = channelid;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public int getSubmit_count() {
		return submit_count;
	}
	public void setSubmit_count(int submit_count) {
		this.submit_count = submit_count;
	}
	public int getSubmit_succ() {
		return submit_succ;
	}
	public void setSubmit_succ(int submit_succ) {
		this.submit_succ = submit_succ;
	}
	public int getSubmit_fail() {
		return submit_fail;
	}
	public void setSubmit_fail(int submit_fail) {
		this.submit_fail = submit_fail;
	}
	public int getReport_count() {
		return report_count;
	}
	public void setReport_count(int report_count) {
		this.report_count = report_count;
	}
	public int getReport_succ() {
		return report_succ;
	}
	public void setReport_succ(int report_succ) {
		this.report_succ = report_succ;
	}
	public int getReport_fail() {
		return report_fail;
	}
	public void setReport_fail(int report_fail) {
		this.report_fail = report_fail;
	}
	public long getCreate_time() {
		return create_time;
	}
	public void setCreate_time(long create_time) {
		this.create_time = create_time;
	}
	public String getTemp_time() {
		return temp_time;
	}
	public void setTemp_time(String temp_time) {
		this.temp_time = temp_time;
	}
	public String getSuccess_rate() {
		return success_rate;
	}
	public void setSuccess_rate(String success_rate) {
		this.success_rate = success_rate;
	}
	public String getFail_rate() {
		return fail_rate;
	}
	public void setFail_rate(String fail_rate) {
		this.fail_rate = fail_rate;
	}
	public int getUnknow_count() {
		return unknow_count;
	}
	public void setUnknow_count(int unknow_count) {
		this.unknow_count = unknow_count;
	}
	public String getUnknow_rate() {
		return unknow_rate;
	}
	public void setUnknow_rate(String unknow_rate) {
		this.unknow_rate = unknow_rate;
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Integer getCountryId() {
		return countryId;
	}
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
