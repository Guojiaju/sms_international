package com.sms.international.admin.model;

import java.io.Serializable;

/**
 * 短信队列
 */
public class SmsUserSending extends BaseEntity implements Serializable{
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

    private Integer mtype;

    private Long senddate;

    private Integer uid;

    private Long mobile;

    private Integer channel;

    private Integer contentNum;

    private Integer stat;

    private Integer release;

    private Integer pid;

    private Integer grade;

    private String expid;

    private String remark;

    private Long hisids;

    private String content;

    private String location;
    private String[] ids;

	/**
	 * 0 默认，1切通道，2驳回
	 */
	private Integer hand_stat;

    public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	/**
	 * 将Long时间转成日期类型显示在页面
	 */
	private String viewTime;

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

	/**
	 * mtype
	 * @return mtype mtype
	 */
	public Integer getMtype() {
		return mtype;
	}

	/**
	 * mtype
	 * @return mtype mtype
	 */
	public void setMtype(Integer mtype) {
		this.mtype = mtype;
	}

	/**
	 * senddate
	 * @return senddate senddate
	 */
	public Long getSenddate() {
		return senddate;
	}

	/**
	 * senddate
	 * @return senddate senddate
	 */
	public void setSenddate(Long senddate) {
		this.senddate = senddate;
	}

	/**
	 * uid
	 * @return uid uid
	 */
	public Integer getUid() {
		return uid;
	}

	/**
	 * uid
	 * @return uid uid
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	/**
	 * mobile
	 * @return mobile mobile
	 */
	public Long getMobile() {
		return mobile;
	}

	/**
	 * mobile
	 * @return mobile mobile
	 */
	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	/**
	 * channel
	 * @return channel channel
	 */
	public Integer getChannel() {
		return channel;
	}

	/**
	 * channel
	 * @return channel channel
	 */
	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	/**
	 * contentNum
	 * @return contentNum contentNum
	 */
	public Integer getContentNum() {
		return contentNum;
	}

	/**
	 * contentNum
	 * @return contentNum contentNum
	 */
	public void setContentNum(Integer contentNum) {
		this.contentNum = contentNum;
	}

	/**
	 * stat
	 * @return stat stat
	 */
	public Integer getStat() {
		return stat;
	}

	/**
	 * stat
	 * @return stat stat
	 */
	public void setStat(Integer stat) {
		this.stat = stat;
	}

	/**
	 * release
	 * @return release release
	 */
	public Integer getRelease() {
		return release;
	}

	/**
	 * release
	 * @return release release
	 */
	public void setRelease(Integer release) {
		this.release = release;
	}

	/**
	 * pid
	 * @return pid pid
	 */
	public Integer getPid() {
		return pid;
	}

	/**
	 * pid
	 * @return pid pid
	 */
	public void setPid(Integer pid) {
		this.pid = pid;
	}

	/**
	 * grade
	 * @return grade grade
	 */
	public Integer getGrade() {
		return grade;
	}

	/**
	 * grade
	 * @return grade grade
	 */
	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	/**
	 * expid
	 * @return expid expid
	 */
	public String getExpid() {
		return expid;
	}

	/**
	 * expid
	 * @return expid expid
	 */
	public void setExpid(String expid) {
		this.expid = expid;
	}

	/**
	 * remark
	 * @return remark remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * remark
	 * @return remark remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * hisids
	 * @return hisids hisids
	 */
	public Long getHisids() {
		return hisids;
	}

	/**
	 * hisids
	 * @return hisids hisids
	 */
	public void setHisids(Long hisids) {
		this.hisids = hisids;
	}

	/**
	 * content
	 * @return content content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * content
	 * @return content content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * location
	 * @return location location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * location
	 * @return location location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getHand_stat() {
		return hand_stat;
	}

	public void setHand_stat(Integer hand_stat) {
		this.hand_stat = hand_stat;
	}
}