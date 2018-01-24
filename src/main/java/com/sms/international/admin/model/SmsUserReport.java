package com.sms.international.admin.model;

import java.io.Serializable;

/**
 * 【用户报表】
 * @author yannannan
 * @date 2017-12-13
 * @desc
 */
public class SmsUserReport extends BaseEntity implements Serializable{
	private Integer uid;
	private String company;
	private Integer sms;
	private Integer total;
	private Integer fail;
	private Integer arrive_succ;
	private Integer arrive_fail;
	private long time;
	private String month;
	private Integer unsend;
	private Integer parentId;
	private String[] ids;
	private Integer isReturn;
	private Integer send;
	private Long startTime;
	private Long endTime;
	private Object[] uids;
	private Integer submit_success;//临时
	private String success_rate;//临时
	private String arrive_rate;//用户月报表失败率
	private String fail_rate;//临时
	private Integer norpt_count;//临时
	private String unknow_rate;//临时
	private Integer failRptCount;//临时失败回执用户账单
	private Integer countryId;//国家编号
	private String country;//国家名称
	private String user_price;//计费金额
	
	public SmsUserReport() {
		super();
	}
	public SmsUserReport(Integer uid, String company, Integer sms,
			Integer total, Integer fail, Integer arrive_succ,
			Integer arrive_fail, long time, String month, Integer unsend,
			Integer parentId, String[] ids, Integer isReturn, Integer send,
			Long startTime, Long endTime, Object[] uids,
			Integer submit_success, String success_rate, String arrive_rate,
			String fail_rate, Integer norpt_count, String unknow_rate,
			Integer failRptCount, Integer countryId, String country,
			String user_price) {
		super();
		this.uid = uid;
		this.company = company;
		this.sms = sms;
		this.total = total;
		this.fail = fail;
		this.arrive_succ = arrive_succ;
		this.arrive_fail = arrive_fail;
		this.time = time;
		this.month = month;
		this.unsend = unsend;
		this.parentId = parentId;
		this.ids = ids;
		this.isReturn = isReturn;
		this.send = send;
		this.startTime = startTime;
		this.endTime = endTime;
		this.uids = uids;
		this.submit_success = submit_success;
		this.success_rate = success_rate;
		this.arrive_rate = arrive_rate;
		this.fail_rate = fail_rate;
		this.norpt_count = norpt_count;
		this.unknow_rate = unknow_rate;
		this.failRptCount = failRptCount;
		this.countryId = countryId;
		this.country = country;
		this.user_price = user_price;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Integer getSms() {
		return sms;
	}
	public void setSms(Integer sms) {
		this.sms = sms;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getFail() {
		return fail;
	}
	public void setFail(Integer fail) {
		this.fail = fail;
	}
	public Integer getArrive_succ() {
		return arrive_succ;
	}
	public void setArrive_succ(Integer arrive_succ) {
		this.arrive_succ = arrive_succ;
	}
	public Integer getArrive_fail() {
		return arrive_fail;
	}
	public void setArrive_fail(Integer arrive_fail) {
		this.arrive_fail = arrive_fail;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Integer getUnsend() {
		return unsend;
	}
	public void setUnsend(Integer unsend) {
		this.unsend = unsend;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String[] getIds() {
		return ids;
	}
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	public Integer getIsReturn() {
		return isReturn;
	}
	public void setIsReturn(Integer isReturn) {
		this.isReturn = isReturn;
	}
	public Integer getSend() {
		return send;
	}
	public void setSend(Integer send) {
		this.send = send;
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
	public Object[] getUids() {
		return uids;
	}
	public void setUids(Object[] uids) {
		this.uids = uids;
	}
	public Integer getSubmit_success() {
		return submit_success;
	}
	public void setSubmit_success(Integer submit_success) {
		this.submit_success = submit_success;
	}
	public String getSuccess_rate() {
		return success_rate;
	}
	public void setSuccess_rate(String success_rate) {
		this.success_rate = success_rate;
	}
	public String getArrive_rate() {
		return arrive_rate;
	}
	public void setArrive_rate(String arrive_rate) {
		this.arrive_rate = arrive_rate;
	}
	public String getFail_rate() {
		return fail_rate;
	}
	public void setFail_rate(String fail_rate) {
		this.fail_rate = fail_rate;
	}
	public Integer getNorpt_count() {
		return norpt_count;
	}
	public void setNorpt_count(Integer norpt_count) {
		this.norpt_count = norpt_count;
	}
	public String getUnknow_rate() {
		return unknow_rate;
	}
	public void setUnknow_rate(String unknow_rate) {
		this.unknow_rate = unknow_rate;
	}
	public Integer getFailRptCount() {
		return failRptCount;
	}
	public void setFailRptCount(Integer failRptCount) {
		this.failRptCount = failRptCount;
	}
	public Integer getCountryId() {
		return countryId;
	}
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getUser_price() {
		return user_price;
	}
	public void setUser_price(String user_price) {
		this.user_price = user_price;
	}
	
	
}
