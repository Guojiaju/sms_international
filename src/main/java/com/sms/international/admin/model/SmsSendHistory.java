package com.sms.international.admin.model;

/**
 * Author guojiaju
 * Date 2017/11/27
 * Description 发送记录
 */
public class SmsSendHistory extends BaseEntity{

    /**
     * 提交时间
     */
    private Long senddate;

    /**
     * 用户编号
     */
    private Integer uid;

    /**
     * 号码
     */
    private Long mobile;

    /**
     * 通道
     */
    private Integer channel;

    /**
     * 内容
     */
    private String content;

    /**
     * 计费条数
     */
    private Integer contentNum;

    /**
     * 状态 1成功 0待发 -1失败
     */
    private Integer stat;

    /**
     * 非法词
     */
    private String mtstat;

    /**
     * PID
     */
    private Integer pid;

    /**
     * 拓展码
     */
    private String expid;

    /**
     * 查询子账号历史记录时使用
     */
    private Object[] uids;

    /**
     * 开始时间(该字段不存在数据库,仅用做时间范围查询)
     */
    private Long startTime;

    /**
     * 结束时间(该字段不存在数据库,仅用做时间范围查询)
     */
    private Long endTime;

    /**
     * 将Long时间转成日期类型显示在页面
     */
    private String viewTime;

    /**
     * 提交成功
     */
    private Integer succ;

    /**
     * 提交失败
     */
    private Integer fail;

    /**
     * 国字编码
     */
    private String countryCode;

    /**
     * 国家
     */
    private String country;

    /**
     * 地区
     */
    private String location;

    /**
     * 回执成功
     */
    private Integer arrive_succ;

    /**
     * 回执失败
     */
    private Integer arrive_fail;
    /**
     * 历史记录id
     */
    private Long hisId;
    /**
     * 优先级
     */
    private Integer grade;

    /**
     * 1. 3日未知记录
     * 2. 3日前
     */
    private Integer records;

    private String[] ids;

    /**
     * 状态回执
     */
    private String rptstat;

    /**
     * 回执时间
     */
    private String rpttime;

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Long getHisId() {
        return hisId;
    }

    public void setHisId(Long hisId) {
        this.hisId = hisId;
    }

    /**
     * 开始时间(该字段不存在数据库仅用做时间范围查询)
     *
     * @return startTime 开始时间(该字段不存在数据库仅用做时间范围查询)
     */
    public Long getStartTime() {
        return startTime;
    }

    /**
     * 开始时间(该字段不存在数据库仅用做时间范围查询)
     *
     * @return startTime 开始时间(该字段不存在数据库仅用做时间范围查询)
     */
    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    /**
     * 结束时间(该字段不存在数据库仅用做时间范围查询)
     *
     * @return endTime 结束时间(该字段不存在数据库仅用做时间范围查询)
     */
    public Long getEndTime() {
        return endTime;
    }

    /**
     * 结束时间(该字段不存在数据库仅用做时间范围查询)
     *
     * @return endTime 结束时间(该字段不存在数据库仅用做时间范围查询)
     */
    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    /**
     * 将Long时间转成日期类型显示在页面
     *
     * @return viewTime 将Long时间转成日期类型显示在页面
     */
    public String getViewTime() {
        if (senddate != null && senddate > 0 && (senddate + "").length() >= 14) {
            String temp = senddate + "";
            viewTime = temp.substring(0, 4) + "-" + temp.substring(4, 6) + "-" + temp.substring(6, 8) + " " + temp.substring(8, 10) + ":" + temp.substring(10, 12) + ":"
                    + temp.substring(12, 14);
        }
        return viewTime;
    }

    /**
     * 提交时间
     *
     * @return senddate 提交时间
     */
    public Long getSenddate() {
        return senddate;
    }

    /**
     * 提交时间
     *
     * @return senddate 提交时间
     */
    public void setSenddate(Long senddate) {
        this.senddate = senddate;
    }

    /**
     * 用户编号
     *
     * @return uid 用户编号
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 用户编号
     *
     * @return uid 用户编号
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 号码
     *
     * @return mobile 号码
     */
    public Long getMobile() {
        return mobile;
    }

    /**
     * 号码
     *
     * @return mobile 号码
     */
    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    /**
     * 通道
     *
     * @return channel 通道
     */
    public Integer getChannel() {
        return channel;
    }

    /**
     * 通道
     *
     * @return channel 通道
     */
    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    /**
     * 内容
     *
     * @return content 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 内容
     *
     * @return content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 计费条数
     *
     * @return contentNum 计费条数
     */
    public Integer getContentNum() {
        return contentNum;
    }

    /**
     * 计费条数
     *
     * @return contentNum 计费条数
     */
    public void setContentNum(Integer contentNum) {
        this.contentNum = contentNum;
    }

    /**
     * 状态
     *
     * @return stat 状态
     */
    public Integer getStat() {
        return stat;
    }

    /**
     * 状态
     *
     * @return stat 状态
     */
    public void setStat(Integer stat) {
        this.stat = stat;
    }

    /**
     * 非法词
     *
     * @return mtstat 非法词
     */
    public String getMtstat() {
        return mtstat;
    }

    /**
     * 非法词
     *
     * @return mtstat 非法词
     */
    public void setMtstat(String mtstat) {
        this.mtstat = mtstat;
    }

    /**
     * PID
     *
     * @return pid PID
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * PID
     *
     * @return pid PID
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 拓展码
     *
     * @return expid 拓展码
     */
    public String getExpid() {
        return expid;
    }

    /**
     * 拓展码
     *
     * @return expid 拓展码
     */
    public void setExpid(String expid) {
        this.expid = expid;
    }

    /**
     * 查询子账号历史记录时使用
     *
     * @return uids 查询子账号历史记录时使用
     */
    public Object[] getUids() {
        return uids;
    }

    /**
     * 查询子账号历史记录时使用
     *
     * @return uids 查询子账号历史记录时使用
     */
    public void setUids(Object[] uids) {
        this.uids = uids;
    }

    public Integer getSucc() {
        return succ;
    }

    public void setSucc(Integer succ) {
        this.succ = succ;
    }

    public Integer getFail() {
        return fail;
    }

    public void setFail(Integer fail) {
        this.fail = fail;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public Integer getRecords() {
        return records;
    }

    public void setRecords(Integer records) {
        this.records = records;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }

    public String getRptstat() {
        return rptstat;
    }

    public void setRptstat(String rptstat) {
        this.rptstat = rptstat;
    }

    public String getRpttime() {
        return rpttime;
    }

    public void setRpttime(String rpttime) {
        this.rpttime = rpttime;
    }
}
