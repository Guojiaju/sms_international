package com.sms.international.admin.mapper;

import com.sms.international.admin.model.SmsGatewayChannel;
import com.sms.international.admin.model.SmsReportChannel;

import java.util.List;

/**
 * 【所有业务报表统计】
 * @author yannannan
 * @date 2017-12-12
 * @desc
 */
public interface SmsReportStatisticsMapper {
	
	/*****************【通道实时】报表开始****************/
	//查询所有通道
	public List<SmsGatewayChannel> findGatewayChannelList(SmsGatewayChannel smsGatewayChannel);
	
	public Integer findGatewayChannelCount(SmsGatewayChannel smsGatewayChannel);
	/*****************【通道实时】报表结束****************/
	
	
	
	/*****************【通道日报表】开始****************/
	public Integer findChannelDayCount(SmsReportChannel smsReportChannel);
	
	public List<SmsReportChannel> findChannelDayList(SmsReportChannel smsReportChannel);
	
	public SmsReportChannel findChannelDaySum(SmsReportChannel smsReportChannel);
	/*****************【通道日报表】结束****************/
	
	
	
	/*****************【通道月报表】开始**************/
	public Integer findChannelMonthCount(SmsReportChannel smsReportChannel);
	
	public List<SmsReportChannel> findChannelMonthList(SmsReportChannel smsReportChannel);
	
	public SmsReportChannel findChannelMonthSum(SmsReportChannel smsReportChannel);
	/*****************【通道月报表】结束**************/
	
	
	/******************【通道日统计详情国际总量统计】开始*********************/
	public Integer findChannelDayDetailCount(SmsReportChannel smsReportChannel);
	public List<SmsReportChannel> findChannelDayDetailList(SmsReportChannel smsReportChannel);
	/******************【通道日统计详情国际总量统计】结束*********************/
	
	
	/******************【通道月统计详情国际总量统计】开始*********************/
	public Integer findChannelMonthDetailCount(SmsReportChannel smsReportChannel);
	public List<SmsReportChannel> findChannelMonthDetailList(SmsReportChannel smsReportChannel);
	/******************【通道月统计详情国际总量统计】结束*********************/
	
}
