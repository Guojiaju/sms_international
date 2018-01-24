package com.sms.international.admin.model;

/**
 * Author guojiaju
 * Date 2017/12/14
 * Description 审核队列
 */
public class SmsUserSendingRelease extends BaseEntity{
    /**
     * 号码类型
     */
    private Integer mtype;
    /**
     * 提交时间
     */
    private Long senddate;
    /**
     * 用户编号
     */
    private Integer uid;
    /**
     * 发送号码
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
     * 内容条数
     */
    private Integer contentNum;
    /**
     * 计费总数
     */
    private Integer num;
    /**
     * 条数
     */
    private Integer count;
    /**
     * 批次号
     */
    private Integer pid;

    /**
     * 优先级
     */
    private Integer grade;

    /**
     * 拓展号
     */
    private String expid;

    /**
     * 描述
     */
    private String remark;

    /**
     * 历史记录ID
     */
    private Integer hisids;
    /**
     * 内容md5值
     */
    private String mdstr;

    /**
     * md5值集合(主要用于批量操作时)
     */
    private String[] mdStrs;

    /**
     * 状态 0待审核，1审核通过，2审核驳回
     */
    private Integer handstat;

    /**
     * 号码归属地
     */
    private String location;

    /**
     * 将Long时间转成日期类型显示在页面
     */
    private String viewTime;

    private Integer screenType;

    private String screenType_display;

    public Integer getMtype() {
        return mtype;
    }

    public void setMtype(Integer mtype) {
        this.mtype = mtype;
    }

    public Long getSenddate() {
        return senddate;
    }

    public void setSenddate(Long senddate) {
        this.senddate = senddate;
    }

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

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getContentNum() {
        return contentNum;
    }

    public void setContentNum(Integer contentNum) {
        this.contentNum = contentNum;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getExpid() {
        return expid;
    }

    public void setExpid(String expid) {
        this.expid = expid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getHisids() {
        return hisids;
    }

    public void setHisids(Integer hisids) {
        this.hisids = hisids;
    }

    public String getMdstr() {
        return mdstr;
    }

    public void setMdstr(String mdstr) {
        this.mdstr = mdstr;
    }

    public String[] getMdStrs() {
        return mdStrs;
    }

    public void setMdStrs(String[] mdStrs) {
        this.mdStrs = mdStrs;
    }

    public Integer getHandstat() {
        return handstat;
    }

    public void setHandstat(Integer handstat) {
        this.handstat = handstat;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    /**
     * 将Long时间转成日期类型显示在页面
     * @return viewTime 将Long时间转成日期类型显示在页面
     */
    public String getViewTime() {
        if(senddate != null && senddate > 0 && (senddate+"").length() >= 14){
            String temp = senddate+"";
            viewTime = temp.substring(0,4)+"-"+temp.substring(4, 6)+"-"+temp.substring(6, 8)+" "+temp.substring(8, 10)+":"+temp.substring(10, 12)+":"+temp.substring(12, 14);
        }
        return viewTime;
    }

    public Integer getScreenType() {
        return screenType;
    }

    public void setScreenType(Integer screenType) {
        this.screenType = screenType;
    }

    public String getScreenType_display() {
        return screenType_display;
    }

    public void setScreenType_display(String screenType_display) {
        this.screenType_display = screenType_display;
    }
}
