package com.sms.international.admin.model;

import java.io.Serializable;

/**
 * 【实时报表通道刷新】
 * @author yannannan
 * @date 2017-12-12
 * @desc
 */
public class SmsGatewayChannel extends BaseEntity implements Serializable {
		private Integer id;
		private Integer gateway_template;//网关配置模板(0为任意,1为cmpp,2为sgip,3为smgp,4为http)
		private Integer gateway_port;//网关端口号
		private String gateway_ip;//网关IP地址
		private String gateway_account;//网管账号
		private String gateway_pass;//网关密码
		private String gateway_url;//网关url地址(主要用于http接口的网关)
		private String local_ip;//本地ip(主要用于sgip协议)
		private Integer local_delay;//每次发送间隔时间(单位毫秒)
		private Integer local_read_num;//网关提供的流速
		private Integer rabbit_port;//消息队列端口
		private String rabbit_ip;//消息队列IP
		private String rabbit_account;//消息队列账号
		private String channel_provider;//通道提供商
		private String rabbit_pass;//消息队列密码
		private String channel_name;//通道名称
		private Integer status;//通道状态0为正常,1为停止
		private Double price;//通道价格
		private String remark;//备注
		private Integer send_words_len;//计费字数
		private Integer send_words_maxlen;//最大字数
		private Integer start_time;//开始时间
		private Integer end_time;//结束时间
		private String extend_temp;//扩展字段
		
		public SmsGatewayChannel() {
			super();
		}
		public SmsGatewayChannel(Integer id, Integer gateway_template,
				Integer gateway_port, String gateway_ip,
				String gateway_account, String gateway_pass,
				String gateway_url, String local_ip, Integer local_delay,
				Integer local_read_num, Integer rabbit_port, String rabbit_ip,
				String rabbit_account, String channel_provider,
				String rabbit_pass, String channel_name, Integer status,
				Double price, String remark, Integer send_words_len,
				Integer send_words_maxlen, Integer start_time,
				Integer end_time, String extend_temp) {
			super();
			this.id = id;
			this.gateway_template = gateway_template;
			this.gateway_port = gateway_port;
			this.gateway_ip = gateway_ip;
			this.gateway_account = gateway_account;
			this.gateway_pass = gateway_pass;
			this.gateway_url = gateway_url;
			this.local_ip = local_ip;
			this.local_delay = local_delay;
			this.local_read_num = local_read_num;
			this.rabbit_port = rabbit_port;
			this.rabbit_ip = rabbit_ip;
			this.rabbit_account = rabbit_account;
			this.channel_provider = channel_provider;
			this.rabbit_pass = rabbit_pass;
			this.channel_name = channel_name;
			this.status = status;
			this.price = price;
			this.remark = remark;
			this.send_words_len = send_words_len;
			this.send_words_maxlen = send_words_maxlen;
			this.start_time = start_time;
			this.end_time = end_time;
			this.extend_temp = extend_temp;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Integer getGateway_template() {
			return gateway_template;
		}
		public void setGateway_template(Integer gateway_template) {
			this.gateway_template = gateway_template;
		}
		public Integer getGateway_port() {
			return gateway_port;
		}
		public void setGateway_port(Integer gateway_port) {
			this.gateway_port = gateway_port;
		}
		public String getGateway_ip() {
			return gateway_ip;
		}
		public void setGateway_ip(String gateway_ip) {
			this.gateway_ip = gateway_ip;
		}
		public String getGateway_account() {
			return gateway_account;
		}
		public void setGateway_account(String gateway_account) {
			this.gateway_account = gateway_account;
		}
		public String getGateway_pass() {
			return gateway_pass;
		}
		public void setGateway_pass(String gateway_pass) {
			this.gateway_pass = gateway_pass;
		}
		public String getGateway_url() {
			return gateway_url;
		}
		public void setGateway_url(String gateway_url) {
			this.gateway_url = gateway_url;
		}
		public String getLocal_ip() {
			return local_ip;
		}
		public void setLocal_ip(String local_ip) {
			this.local_ip = local_ip;
		}
		public Integer getLocal_delay() {
			return local_delay;
		}
		public void setLocal_delay(Integer local_delay) {
			this.local_delay = local_delay;
		}
		public Integer getLocal_read_num() {
			return local_read_num;
		}
		public void setLocal_read_num(Integer local_read_num) {
			this.local_read_num = local_read_num;
		}
		public Integer getRabbit_port() {
			return rabbit_port;
		}
		public void setRabbit_port(Integer rabbit_port) {
			this.rabbit_port = rabbit_port;
		}
		public String getRabbit_ip() {
			return rabbit_ip;
		}
		public void setRabbit_ip(String rabbit_ip) {
			this.rabbit_ip = rabbit_ip;
		}
		public String getRabbit_account() {
			return rabbit_account;
		}
		public void setRabbit_account(String rabbit_account) {
			this.rabbit_account = rabbit_account;
		}
		public String getChannel_provider() {
			return channel_provider;
		}
		public void setChannel_provider(String channel_provider) {
			this.channel_provider = channel_provider;
		}
		public String getRabbit_pass() {
			return rabbit_pass;
		}
		public void setRabbit_pass(String rabbit_pass) {
			this.rabbit_pass = rabbit_pass;
		}
		public String getChannel_name() {
			return channel_name;
		}
		public void setChannel_name(String channel_name) {
			this.channel_name = channel_name;
		}
		public Integer getStatus() {
			return status;
		}
		public void setStatus(Integer status) {
			this.status = status;
		}
		public Double getPrice() {
			return price;
		}
		public void setPrice(Double price) {
			this.price = price;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public Integer getSend_words_len() {
			return send_words_len;
		}
		public void setSend_words_len(Integer send_words_len) {
			this.send_words_len = send_words_len;
		}
		public Integer getSend_words_maxlen() {
			return send_words_maxlen;
		}
		public void setSend_words_maxlen(Integer send_words_maxlen) {
			this.send_words_maxlen = send_words_maxlen;
		}
		public Integer getStart_time() {
			return start_time;
		}
		public void setStart_time(Integer start_time) {
			this.start_time = start_time;
		}
		public Integer getEnd_time() {
			return end_time;
		}
		public void setEnd_time(Integer end_time) {
			this.end_time = end_time;
		}
		public String getExtend_temp() {
			return extend_temp;
		}
		public void setExtend_temp(String extend_temp) {
			this.extend_temp = extend_temp;
		}
		
		
		
}
