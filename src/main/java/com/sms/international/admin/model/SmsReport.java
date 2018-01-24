package com.sms.international.admin.model;

/**
 * Author guojiaju
 * Date 2017/12/7
 * Description 状态查询
 */
public class SmsReport extends BaseEntity{

    /**
     * uid
     */
    private Integer uid;
    /**
     * 手机号码
     */
    private Long mobile;
    /**
     * 状态
     */
    private String stat;
    /**
     * 历史记录id
     */
    private Long hisId;
    /**
     * 批次id
     */
    private Long pid;
    /**
     * 通道
     */
    private Integer channelId;
    /**
     * 页面显示通道
     */
    private Integer channel;
    /**
     * 页面显示耗时
     */
    private Long senddate;//仅用于显示
    /**
     * 回执状态
     */
    private String rptcode;
    /**
     * 回执时间
     */
    private String rpttime;
    /**
     * 页面显示回执状态
     */
    private String rptstat;
    /**
     * 用户发送时间
     */
    private Integer userday;
    private String extend1;
    private Integer extend2;

    /**
     * 扩展码
     */
    private String expid;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public Long getHisId() {
        return hisId;
    }

    public void setHisId(Long hisId) {
        this.hisId = hisId;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Long getSenddate() {
        return senddate;
    }

    public void setSenddate(Long senddate) {
        this.senddate = senddate;
    }

    public String getRptcode() {
        return rptcode;
    }

    public void setRptcode(String rptcode) {
        this.rptcode = rptcode;
    }

    public String getRpttime() {
        return rpttime;
    }

    public void setRpttime(String rpttime) {
        this.rpttime = rpttime;
    }

    public String getRptstat() {
        return rptstat;
    }

    public void setRptstat(String rptstat) {
        this.rptstat = rptstat;
    }

    public Integer getUserday() {
        return userday;
    }

    public void setUserday(Integer userday) {
        this.userday = userday;
    }

    public String getExtend1() {
        return extend1;
    }

    public void setExtend1(String extend1) {
        this.extend1 = extend1;
    }

    public Integer getExtend2() {
        return extend2;
    }

    public void setExtend2(Integer extend2) {
        this.extend2 = extend2;
    }

    public String getExpid() {
        return expid;
    }

    public void setExpid(String expid) {
        this.expid = expid;
    }
}
