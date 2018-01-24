package com.sms.international.admin.model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by 37985 on 2016/7/1.
 */
public class SmsUser extends BaseEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 加密后密码
	 */
	private String pwd;
	/**
	 * 原密码
	 */
	private String dpwd;
	/**
	 * 企业名称
	 */
	private String company;
	/**
	 * 手机号码
	 */
	private String phone;
	/**
	 * 联系电话
	 */
	private String tel;
	/**
	 * 邮箱
	 */
	private String mail;
	/**
	 * 联系人
	 */
	private String linkman;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 业务员
	 */
	private String sales;
	/**
	 * 创建日期
	 */
	private String time;
	/**
	 * 添加人
	 */
	private String addUid;
	/**
	 * 状态 1 有效，2无效
	 */
	private Integer stat;
	/**
	 * 余额
	 */
	private Double sms;
	/**
	 * 优先级
	 */
	private Integer priority;
	/**
	 * QQ
	 */
	private String qq;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 用户组
	 */
	private Integer userkind;
	/**
	 * 客服
	 */
	private String kefu;
	/**
	 * 企业代码
	 */
	private String username;
	/**
	 * 用户接入类型
	 */
	private Integer submittype;
	/**
	 * 父账号
	 */
	private Integer parentId;
	/***
	 * 客户ID
	 */
	private Integer customerId;

	/**
	 * 1,代理；2,终端用户
	 */
	private Integer usertype;
	/**
	 * 开户人姓名
	 */
	private String realname;

	/***
	 * 付费方式：0预付费 1后付费
	 */
	private Integer paytype;

	private int[] ids;

	private String loginTime;

	private List<String> departmentSales;
	
	private Integer searchOnly;
	
	private Integer childCount;

	private String sign;

	public Integer getPaytype() {
		return paytype;
	}

	public void setPaytype(Integer paytype) {
		this.paytype = paytype;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	/**
	 * 客户ID
	 * 
	 * @return 客户ID
	 */
	public Integer getCustomerId() {
		return customerId;
	}

	/***
	 * 客户ID
	 * 
	 * @param customerId
	 *            客户ID
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	/**
	 * 是否是子账号;1为子账号,2为主账号
	 */
	private Integer isChild;

	/**
	 * 加密后密码
	 * 
	 * @return pwd 加密后密码
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * 加密后密码
	 * 
	 * @return pwd 加密后密码
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * 原密码
	 * 
	 * @return dpwd 原密码
	 */
	public String getDpwd() {
		return dpwd;
	}

	/**
	 * 原密码
	 * 
	 * @return dpwd 原密码
	 */
	public void setDpwd(String dpwd) {
		this.dpwd = dpwd;
	}

	/**
	 * 企业名称
	 * 
	 * @return company 企业名称
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * 企业名称
	 * 
	 * @return company 企业名称
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * 手机号码
	 * 
	 * @return phone 手机号码
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 手机号码
	 * 
	 * @return phone 手机号码
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 联系电话
	 * 
	 * @return tel 联系电话
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * 联系电话
	 * 
	 * @return tel 联系电话
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * 邮箱
	 * 
	 * @return mail 邮箱
	 */
	public String getMail() {
		return mail;
	}

	/**  
	 * 余额  
	 * @return sms 余额  
	 */
	public Double getSms() {
		return sms;
	}

	/**  
	 * 余额  
	 * @return sms 余额  
	 */
	public void setSms(Double sms) {
		this.sms = sms;
	}

	/**
	 * 邮箱
	 * 
	 * @return mail 邮箱
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * 联系人
	 * 
	 * @return linkman 联系人
	 */
	public String getLinkman() {
		return linkman;
	}

	/**
	 * 联系人
	 * 
	 * @return linkman 联系人
	 */
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	/**
	 * 地址
	 * 
	 * @return address 地址
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 地址
	 * 
	 * @return address 地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 业务员
	 * 
	 * @return sales 业务员
	 */
	public String getSales() {
		return sales;
	}

	/**
	 * 业务员
	 * 
	 * @return sales 业务员
	 */
	public void setSales(String sales) {
		this.sales = sales;
	}


	/**
	 * 创建日期
	 * 
	 * @return time 创建日期
	 */
	public String getTime() {
		return time;
	}

	/**
	 * 创建日期
	 * 
	 * @return time 创建日期
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * 添加人
	 * 
	 * @return addUid 添加人
	 */
	public String getAddUid() {
		return addUid;
	}

	/**
	 * 是否是子账号;1为子账号2为主账号
	 * 
	 * @return isChild 是否是子账号;1为子账号2为主账号
	 */
	public Integer getIsChild() {
		return isChild;
	}

	/**
	 * 是否是子账号;1为子账号2为主账号
	 * 
	 * @return isChild 是否是子账号;1为子账号2为主账号
	 */
	public void setIsChild(Integer isChild) {
		this.isChild = isChild;
	}

	/**
	 * 添加人
	 * 
	 * @return addUid 添加人
	 */
	public void setAddUid(String addUid) {
		this.addUid = addUid;
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
	 * 状态 0有效，1无效
	 * 
	 * @return stat 状态
	 */
	public void setStat(Integer stat) {
		this.stat = stat;
	}


	/**
	 * 优先级
	 * 
	 * @return priority 优先级
	 */
	public Integer getPriority() {
		return priority;
	}

	/**
	 * 优先级
	 * 
	 * @return priority 优先级
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}


	/**
	 * QQ
	 * 
	 * @return qq QQ
	 */
	public String getQq() {
		return qq;
	}

	/**
	 * QQ
	 * 
	 * @return qq QQ
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}

	/**
	 * 备注
	 * 
	 * @return remark 备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 备注
	 * 
	 * @return remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 用户组
	 * 
	 * @return userkind 用户组
	 */
	public Integer getUserkind() {
		return userkind;
	}

	/**
	 * 用户组
	 * 
	 * @return userkind 用户组
	 */
	public void setUserkind(Integer userkind) {
		this.userkind = userkind;
	}

	/**
	 * 客服
	 * 
	 * @return kefu 客服
	 */
	public String getKefu() {
		return kefu;
	}

	/**
	 * 客服
	 * 
	 * @return kefu 客服
	 */
	public void setKefu(String kefu) {
		this.kefu = kefu;
	}

	/**
	 * 企业代码
	 * 
	 * @return username 企业代码
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 企业代码
	 * 
	 * @return username 企业代码
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 用户接入类型
	 * 
	 * @return submittype 用户接入类型
	 */
	public Integer getSubmittype() {
		return submittype;
	}

	/**
	 * 用户接入类型
	 * 
	 * @return submittype 用户接入类型
	 */
	public void setSubmittype(Integer submittype) {
		this.submittype = submittype;
	}

	/**
	 * 父账号
	 * 
	 * @return parentId 父账号
	 */
	public Integer getParentId() {
		return parentId;
	}

	/**
	 * 父账号
	 * 
	 * @return parentId 父账号
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}


	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}


	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	public int[] getIds() {
		return ids;
	}

	public void setIds(int[] ids) {
		this.ids = ids;
	}

	public List<String> getDepartmentSales() {
		return departmentSales;
	}

	public void setDepartmentSales(List<String> departmentSales) {
		this.departmentSales = departmentSales;
	}

	public Integer getSearchOnly() {
		return searchOnly;
	}

	public void setSearchOnly(Integer searchOnly) {
		this.searchOnly = searchOnly;
	}

	public Integer getChildCount() {
		return childCount;
	}

	public void setChildCount(Integer childCount) {
		this.childCount = childCount;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("{");
		sb.append("\"pwd\":\"")
				.append(pwd).append('\"');
		sb.append(",\"dpwd\":\"")
				.append(dpwd).append('\"');
		sb.append(",\"company\":\"")
				.append(company).append('\"');
		sb.append(",\"phone\":\"")
				.append(phone).append('\"');
		sb.append(",\"tel\":\"")
				.append(tel).append('\"');
		sb.append(",\"mail\":\"")
				.append(mail).append('\"');
		sb.append(",\"linkman\":\"")
				.append(linkman).append('\"');
		sb.append(",\"address\":\"")
				.append(address).append('\"');
		sb.append(",\"sales\":\"")
				.append(sales).append('\"');
		sb.append(",\"time\":\"")
				.append(time).append('\"');
		sb.append(",\"addUid\":\"")
				.append(addUid).append('\"');
		sb.append(",\"stat\":")
				.append(stat);
		sb.append(",\"sms\":")
				.append(sms);
		sb.append(",\"priority\":")
				.append(priority);
		sb.append(",\"qq\":\"")
				.append(qq).append('\"');
		sb.append(",\"remark\":\"")
				.append(remark).append('\"');
		sb.append(",\"userkind\":")
				.append(userkind);
		sb.append(",\"kefu\":\"")
				.append(kefu).append('\"');
		sb.append(",\"username\":\"")
				.append(username).append('\"');
		sb.append(",\"submittype\":")
				.append(submittype);
		sb.append(",\"parentId\":")
				.append(parentId);
		sb.append(",\"customerId\":")
				.append(customerId);
		sb.append(",\"usertype\":")
				.append(usertype);
		sb.append(",\"realname\":\"")
				.append(realname).append('\"');
		sb.append(",\"paytype\":")
				.append(paytype);
		sb.append(",\"ids\":")
				.append(Arrays.toString(ids));
		sb.append(",\"loginTime\":\"")
				.append(loginTime).append('\"');
		sb.append(",\"departmentSales\":")
				.append(departmentSales);
		sb.append(",\"searchOnly\":")
				.append(searchOnly);
		sb.append(",\"childCount\":")
				.append(childCount);
		sb.append(",\"isChild\":")
				.append(isChild);
		sb.append('}');
		return sb.toString();
	}
}
