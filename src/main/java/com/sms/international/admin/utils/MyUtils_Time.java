package com.sms.international.admin.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyUtils_Time {
	/**
	 * @author luo 2015-05-08
	 */

	/**
	 * @param timeForm
	 *            时间格式,12小时制hh,24小时制:HH
	 * @return 返回现在格式化时间
	 * */
	public static String getTimeFormat(String timeForm) {
		SimpleDateFormat fmt = new SimpleDateFormat(timeForm);
		return fmt.format(new Date());
	}
	
	/**
	 * @param timeForm
	 *            时间格式,12小时制hh,24小时制:HH
	 * @return 返回现在格式化时间
	 * */
	public static String getTimeFormat(Date date,String timeForm) {
		SimpleDateFormat fmt = new SimpleDateFormat(timeForm);
		return fmt.format(date);
	}

	/**
	 * @param time
	 *            需要格式化的时间
	 * @param parsePattern
	 *            对应解析time的时间格式
	 * @param fmtPattern
	 *            输出时间字符串的格式
	 * @return 返回指定fmtPattern格式的时间字符串
	 * */
	public static String getTimeByParse(String time, String parsePattern, String outPattern) {
		SimpleDateFormat format = new SimpleDateFormat(parsePattern);
		Date date = null;
		try {
			date = format.parse(time);
			format = new SimpleDateFormat(outPattern);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return format.format(date);
	}

	/**
	 * @param day
	 *            在今天的时间上增加或减去的日期
	 * @return
	 * */
	public static String addDate(int day) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
		Date beginDate = new Date();
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) + day);
		return dft.format(date.getTime());
	}
	
	/**
	 * @param day
	 *            在今天的时间上增加或减去的日期
	 * @return
	 * */
	public static String addDate(int day,String pattern) {
		SimpleDateFormat dft = new SimpleDateFormat(pattern);
		Date beginDate = new Date();
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) - day);
		return dft.format(date.getTime());
	}


	// 增加或减少天数
	public static Date addDay(Date date, int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		return startDT.getTime();
	}
	
   /**
    * 获取yyyyMMddHHmmss long型时间
    * @return
    */
	public static Long getLongTimeDay() {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		return Long.valueOf(df.format(new Date()));
	}
	
	public static Long getTime(long time) {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar startDT = Calendar.getInstance();
		try {
			startDT.setTime(df.parse(String.valueOf(time)));
			Date d=startDT.getTime();
			return d.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return 0l;
		}
	}
  
	public static String parseLongDate(long date){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date newDate = new Date(date*1000);
		return df.format(newDate);
	}

	public static long getDayBefore(int min){
		Calendar c = Calendar.getInstance();
		int min_ = c.get(Calendar.MINUTE);
		c.set(Calendar.MINUTE, min_ - min);
		String l = c.get(Calendar.YEAR)+"" + ((c.get(Calendar.MONTH)+1)<10?("0"+(c.get(Calendar.MONTH)+1)):(c.get(Calendar.MONTH)+1)) +"" +  (c.get(Calendar.DAY_OF_MONTH)<10?("0"+c.get(Calendar.DAY_OF_MONTH)):c.get(Calendar.DAY_OF_MONTH)) + "" + (c.get(Calendar.HOUR_OF_DAY)<10?("0"+c.get(Calendar.HOUR_OF_DAY)):c.get(Calendar.HOUR_OF_DAY)) +"" +  (c.get(Calendar.MINUTE)<10?("0"+c.get(Calendar.MINUTE)):c.get(Calendar.MINUTE)) +"" + ( c.get(Calendar.SECOND)<10?("0"+ c.get(Calendar.SECOND)): c.get(Calendar.SECOND));
		return Long.parseLong(l);
	}
	
	 public static void main(String[] args) {
		 System.out.println(addDate(30)+"000000");
	 }
}
