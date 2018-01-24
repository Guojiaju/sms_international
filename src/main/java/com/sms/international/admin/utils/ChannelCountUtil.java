package com.sms.international.admin.utils;
/**
 * channel_day_count mongo 表
 * @author zhangjian
 *
 */
public final class ChannelCountUtil {
 //channel_day_count
//where2.put("a", channelid);//uid
//where2.put("b", Long.valueOf(20 + "" + respjson.get("days")));//time
//     set1.put("c", 1);//submit_count
//	set2.put("e", 1);//report_count
//	mongo.update3("channel_day_count", set2, where2);
//	set4.put("h", 1);//submit_fail
//	  set2.put("d", 1);//submit_succ
	
//	  set3.put("f", 1);//report_succ
//		set3.put("g", 1);//report_fail
	public static String getCid() {
		return "a";
	}
	public static String getTime() {
		return "b";
	}
	public static String getSubmit_count(){
		return "c";
	}
	public static String getSubmit_succ(){
		return "d";
	}
	public static String getReport_count(){
		return "e";
	}
	public static String getReport_succ(){
		return "f";
	}
	public static String getReport_fail(){
		return "g";
	}
	public static String getSubmit_fail(){
		return "h";
	}
	public static String getCountryId(){
		return "l";//国际编号
	}
}
