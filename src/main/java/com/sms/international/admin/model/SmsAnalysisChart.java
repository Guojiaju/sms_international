package com.sms.international.admin.model;

import java.io.Serializable;

/**
 * 【Analysis 用户月报表分析【基本信息实体类】】
 * @author yannannan
 *
 */
public class SmsAnalysisChart implements Serializable{
    private Integer item_id;//数据标识
    private Integer company_uid;//企业编号用户uid
    private String company_name;//企业名称
    private Integer bill_time;//账单周期
    private long send_count;//发送总数量
    private long XA2006;//xa2006数据
    private long receipt_count;//回执数量
    private String receipt_rate;//回执率
    private long success_count;//成功数量
    private String success_rate;//成功率
    private long fail_count;//失败数量
    private String fail_rate;//失败率
    private long unknown_count;//未知数量
    private String unknown_rate;//未知率

    private Integer startTime;//查询月初起时间范围
    private Integer endTime;//查询月终结束时间范围
    //平台失败数量数量 饼图数据item
    private String smscmpp_rptcode;//平台返回吗
    private Integer smscmpp_fail_count;//平台失败数量
    private Integer smscmpp_all_count;//平台失败数量
    private Integer smscmpp_rptcode_rate;//[前5条失败类型]/占[用户失败总量]百分比

    //速度分析数据
    private int send_mean;//整体发送平均耗时(秒)
    private int validate_mean;//验证码平均耗时(秒)
    private long submit_max_count;//单日最大提交量
    private long submit_min_count;//单日最小提交量
    private long upriver_count;//总上行量
    private long reply_tcount;//回复T量
    private long replay_max_count;//单日最大回复量
    private long replay_min_count;//单日最小回复量

    //国家分析
    private Integer countryId;//国家编号
    private String country;//国家名称
    private String user_price;//计费金额

    public SmsAnalysisChart() {
        super();
    }
    public SmsAnalysisChart(Integer item_id, Integer company_uid,
                            String company_name, Integer bill_time, long send_count,
                            long xA2006, long receipt_count, String receipt_rate,
                            long success_count, String success_rate, long fail_count,
                            String fail_rate, long unknown_count, String unknown_rate,
                            Integer startTime, Integer endTime, String smscmpp_rptcode,
                            Integer smscmpp_fail_count, Integer smscmpp_all_count,
                            Integer smscmpp_rptcode_rate, int send_mean, int validate_mean,
                            long submit_max_count, long submit_min_count,
                            long upriver_count, long reply_tcount, long replay_max_count,
                            long replay_min_count, Integer countryId, String country,
                            String user_price) {
        super();
        this.item_id = item_id;
        this.company_uid = company_uid;
        this.company_name = company_name;
        this.bill_time = bill_time;
        this.send_count = send_count;
        XA2006 = xA2006;
        this.receipt_count = receipt_count;
        this.receipt_rate = receipt_rate;
        this.success_count = success_count;
        this.success_rate = success_rate;
        this.fail_count = fail_count;
        this.fail_rate = fail_rate;
        this.unknown_count = unknown_count;
        this.unknown_rate = unknown_rate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.smscmpp_rptcode = smscmpp_rptcode;
        this.smscmpp_fail_count = smscmpp_fail_count;
        this.smscmpp_all_count = smscmpp_all_count;
        this.smscmpp_rptcode_rate = smscmpp_rptcode_rate;
        this.send_mean = send_mean;
        this.validate_mean = validate_mean;
        this.submit_max_count = submit_max_count;
        this.submit_min_count = submit_min_count;
        this.upriver_count = upriver_count;
        this.reply_tcount = reply_tcount;
        this.replay_max_count = replay_max_count;
        this.replay_min_count = replay_min_count;
        this.countryId = countryId;
        this.country = country;
        this.user_price = user_price;
    }
    public Integer getItem_id() {
        return item_id;
    }
    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }
    public Integer getCompany_uid() {
        return company_uid;
    }
    public void setCompany_uid(Integer company_uid) {
        this.company_uid = company_uid;
    }
    public String getCompany_name() {
        return company_name;
    }
    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }
    public Integer getBill_time() {
        return bill_time;
    }
    public void setBill_time(Integer bill_time) {
        this.bill_time = bill_time;
    }
    public long getSend_count() {
        return send_count;
    }
    public void setSend_count(long send_count) {
        this.send_count = send_count;
    }
    public long getXA2006() {
        return XA2006;
    }
    public void setXA2006(long xA2006) {
        XA2006 = xA2006;
    }
    public long getReceipt_count() {
        return receipt_count;
    }
    public void setReceipt_count(long receipt_count) {
        this.receipt_count = receipt_count;
    }
    public String getReceipt_rate() {
        return receipt_rate;
    }
    public void setReceipt_rate(String receipt_rate) {
        this.receipt_rate = receipt_rate;
    }
    public long getSuccess_count() {
        return success_count;
    }
    public void setSuccess_count(long success_count) {
        this.success_count = success_count;
    }
    public String getSuccess_rate() {
        return success_rate;
    }
    public void setSuccess_rate(String success_rate) {
        this.success_rate = success_rate;
    }
    public long getFail_count() {
        return fail_count;
    }
    public void setFail_count(long fail_count) {
        this.fail_count = fail_count;
    }
    public String getFail_rate() {
        return fail_rate;
    }
    public void setFail_rate(String fail_rate) {
        this.fail_rate = fail_rate;
    }
    public long getUnknown_count() {
        return unknown_count;
    }
    public void setUnknown_count(long unknown_count) {
        this.unknown_count = unknown_count;
    }
    public String getUnknown_rate() {
        return unknown_rate;
    }
    public void setUnknown_rate(String unknown_rate) {
        this.unknown_rate = unknown_rate;
    }
    public Integer getStartTime() {
        return startTime;
    }
    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }
    public Integer getEndTime() {
        return endTime;
    }
    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }
    public String getSmscmpp_rptcode() {
        return smscmpp_rptcode;
    }
    public void setSmscmpp_rptcode(String smscmpp_rptcode) {
        this.smscmpp_rptcode = smscmpp_rptcode;
    }
    public Integer getSmscmpp_fail_count() {
        return smscmpp_fail_count;
    }
    public void setSmscmpp_fail_count(Integer smscmpp_fail_count) {
        this.smscmpp_fail_count = smscmpp_fail_count;
    }
    public Integer getSmscmpp_all_count() {
        return smscmpp_all_count;
    }
    public void setSmscmpp_all_count(Integer smscmpp_all_count) {
        this.smscmpp_all_count = smscmpp_all_count;
    }
    public Integer getSmscmpp_rptcode_rate() {
        return smscmpp_rptcode_rate;
    }
    public void setSmscmpp_rptcode_rate(Integer smscmpp_rptcode_rate) {
        this.smscmpp_rptcode_rate = smscmpp_rptcode_rate;
    }
    public int getSend_mean() {
        return send_mean;
    }
    public void setSend_mean(int send_mean) {
        this.send_mean = send_mean;
    }
    public int getValidate_mean() {
        return validate_mean;
    }
    public void setValidate_mean(int validate_mean) {
        this.validate_mean = validate_mean;
    }
    public long getSubmit_max_count() {
        return submit_max_count;
    }
    public void setSubmit_max_count(long submit_max_count) {
        this.submit_max_count = submit_max_count;
    }
    public long getSubmit_min_count() {
        return submit_min_count;
    }
    public void setSubmit_min_count(long submit_min_count) {
        this.submit_min_count = submit_min_count;
    }
    public long getUpriver_count() {
        return upriver_count;
    }
    public void setUpriver_count(long upriver_count) {
        this.upriver_count = upriver_count;
    }
    public long getReply_tcount() {
        return reply_tcount;
    }
    public void setReply_tcount(long reply_tcount) {
        this.reply_tcount = reply_tcount;
    }
    public long getReplay_max_count() {
        return replay_max_count;
    }
    public void setReplay_max_count(long replay_max_count) {
        this.replay_max_count = replay_max_count;
    }
    public long getReplay_min_count() {
        return replay_min_count;
    }
    public void setReplay_min_count(long replay_min_count) {
        this.replay_min_count = replay_min_count;
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
