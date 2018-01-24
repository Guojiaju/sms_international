package com.sms.hy.cmpp.vo;

import java.io.Serializable;

public class SendingVo implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	private int mtype;       // 运营商
	private long senddate;   // 提交时间
	private int uid;         // 用户id
	private long mobile;   // 接收号码
	private int channel;     // 发送通道
	private String content;  // 发送内容
	private int contentNum;  // 内条数
	private int stat;        // 状态
	private String mtStat;   // 屏蔽词
	private int pid;         // 发送包ID
	private int grade;
	private String expid;    // 发送扩展号
	private String rptStat;  // 发送状态
	private int release;     // 是否通过审核
	private String msgid;    // 网关返回消息ID
	private String location; // 号码归属地
	private int autoFlag;    // 处理标记,-1自动处理
	private int handStat;    // 审核表使用字段，默认值-1，审核通过1，审核驳回0，审核清除2
	private int hisids;
	private String source;	//消息来源,比如cmpp1,http1的名称
	private Integer screenType;

	public Integer getScreenType() {
		return screenType;
	}

	public void setScreenType(Integer screenType) {
		this.screenType = screenType;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getMtype() {
		return mtype;
	}
	public void setMtype(int mtype) {
		this.mtype = mtype;
	}
	public long getSenddate() {
		return senddate;
	}
	public void setSenddate(long senddate) {
		this.senddate = senddate;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	/**
	 * mobile
	 * @return mobile mobile
	 */
	public long getMobile() {
		return mobile;
	}
	/**
	 * mobile
	 * @return mobile mobile
	 */
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public int getChannel() {
		return channel;
	}
	public void setChannel(int channel) {
		this.channel = channel;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getContentNum() {
		return contentNum;
	}
	public void setContentNum(int contentNum) {
		this.contentNum = contentNum;
	}
	public int getStat() {
		return stat;
	}
	public void setStat(int stat) {
		this.stat = stat;
	}
	public String getMtStat() {
		return mtStat;
	}
	public void setMtStat(String mtStat) {
		this.mtStat = mtStat;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getExpid() {
		return expid;
	}
	public void setExpid(String expid) {
		this.expid = expid;
	}
	public String getRptStat() {
		return rptStat;
	}
	public void setRptStat(String rptStat) {
		this.rptStat = rptStat;
	}
	public int getRelease() {
		return release;
	}
	public void setRelease(int release) {
		this.release = release;
	}
	public String getMsgid() {
		return msgid;
	}
	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getAutoFlag() {
		return autoFlag;
	}
	public void setAutoFlag(int autoFlag) {
		this.autoFlag = autoFlag;
	}
	public int getHandStat() {
		return handStat;
	}
	public void setHandStat(int handStat) {
		this.handStat = handStat;
	}
	public int getHisids() {
		return hisids;
	}
	public void setHisids(int hisids) {
		this.hisids = hisids;
	}
	/**
	 * source
	 * @return source source
	 */
	public String getSource() {
		return source;
	}
	/**
	 * source
	 * @return source source
	 */
	public void setSource(String source) {
		this.source = source;
	}

}
