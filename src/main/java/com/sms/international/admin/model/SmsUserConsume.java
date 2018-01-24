package com.sms.international.admin.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 用户消费表
 */
public class SmsUserConsume extends BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer uid;

	/**
	 * 已发费用统计
	 */
    private double send;

	/**
	 * 未发统计
	 */
	private double unsend;
    
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String date;

    private Short utype;

    @JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
    private Date snum;
    
    private String startTime;
    
    private String endTime;


    public String getSnum() {
        return date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public double getSend() {
        return send;
    }

    public void setSend(double send) {
        this.send = send;
    }

    public double getUnsend() {
		return unsend;
	}

	public void setUnsend(double unsend) {
		this.unsend = unsend;
	}

	public String getDate() {
    	if(date != null && date.length() > 10){
    		date = date.substring(0, 10);
    	}
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Short getUtype() {
        return utype;
    }

    public void setUtype(Short utype) {
        this.utype = utype;
    }

	/**  
	 * startTime  
	 * @return startTime startTime  
	 */
	public String getStartTime() {
		return startTime;
	}

	/**  
	 * startTime  
	 * @return startTime startTime  
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**  
	 * endTime  
	 * @return endTime endTime  
	 */
	public String getEndTime() {
		return endTime;
	}

	/**  
	 * endTime  
	 * @return endTime endTime  
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**  
	 * snum  
	 * @return snum snum  
	 */
	public void setSnum(Date snum) {
		this.snum = snum;
	}
}