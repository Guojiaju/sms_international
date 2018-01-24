package com.sms.international.admin.model;

/**
 * 平台签名库
 */
public class SmsSignStore extends BaseEntity{

	/**
	 * uid
	 */
	private Integer uid;
	
	/**
	 * 签名
	 */
	private String store;
	
	/**
	 * 拓展
	 */
	private String expend;
	
	/**
	 * 状态:1为已报备;0为未报备;默认为0
	 */
	private Integer status;
	
	/**
	 * 报备时间
	 */
	private String signtime;
	
	/**
	 * 添加时间
	 */
	private String addtime;

	/**
	 * 1通道签名，2平台签名
	 */
	private Integer type ;

	/**
	 * 通道
	 */
	private Integer channel;

	/**
	 * 渠道自增长字段
	 */
	private String expendqd;

	/**
	 * 普通用户系统生成拓展，用于确定自增长最大值
	 */
	private String expend2;

	/**
	 * 绑定上行推送
	 */
	private String userexpend;

	/**
	 *
	 */
	private byte userstat;

	/**
	 * 批量操作
	 */
	private String [] ids;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public String getExpend() {
		return expend;
	}

	public void setExpend(String expend) {
		this.expend = expend;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSigntime() {
		return signtime;
	}

	public void setSigntime(String signtime) {
		this.signtime = signtime;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public String getExpendqd() {
		return expendqd;
	}

	public void setExpendqd(String expendqd) {
		this.expendqd = expendqd;
	}

	public String getExpend2() {
		return expend2;
	}

	public void setExpend2(String expend2) {
		this.expend2 = expend2;
	}

	public String getUserexpend() {
		return userexpend;
	}

	public void setUserexpend(String userexpend) {
		this.userexpend = userexpend;
	}

	public byte getUserstat() {
		return userstat;
	}

	public void setUserstat(byte userstat) {
		this.userstat = userstat;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("{");
		sb.append("\"uid\":")
				.append(uid);
		sb.append(",\"store\":\"")
				.append(store).append('\"');
		sb.append(",\"expend\":\"")
				.append(expend).append('\"');
		sb.append(",\"status\":")
				.append(status);
		sb.append(",\"signtime\":\"")
				.append(signtime).append('\"');
		sb.append(",\"addtime\":\"")
				.append(addtime).append('\"');
		sb.append(",\"type\":")
				.append(type);
		sb.append(",\"channel\":")
				.append(channel);
		sb.append(",\"expendqd\":\"")
				.append(expendqd).append('\"');
		sb.append(",\"expend2\":\"")
				.append(expend2).append('\"');
		sb.append(",\"userexpend\":\"")
				.append(userexpend).append('\"');
		sb.append(",\"userstat\":")
				.append(userstat);
		sb.append('}');
		return sb.toString();
	}
}
