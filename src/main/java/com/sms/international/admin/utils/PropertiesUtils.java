package com.sms.international.admin.utils;

import java.io.InputStream;
import java.util.Properties;

/**
 * 对属性文件操作的工具
 */
public class PropertiesUtils {

	public static Properties prop = null;

	public static Properties rabbit = null;

	static {
		loadProp();
		getRabbitProperties();
	}

	/**
	 * 获取属文件的数据 根据key获取值?
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		return prop.getProperty(key);
	}

	/**
	 * 获取Rabbit属文件的数据 根据key获取值?
	 * @param key
	 * @return
	 */
	public static String getRabbit(String key) {
		return rabbit.getProperty(key);
	}
	
	/**
	 * 返回 Properties
	 * @param fileName 文件
	 * @param
	 * @return
	 */
	private static void loadProp() {
		try {
			if(prop == null){
				InputStream in = PropertiesUtils.class.getResourceAsStream("/config.properties");
				prop = new Properties();
				prop.load(in);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加载rabbit属性文件
	 * @return
	 */
	public static void getRabbitProperties(){
		try {
			if(rabbit == null) {
				InputStream in = PropertiesUtils.class.getResourceAsStream("/rabbitConfigs.properties");
				rabbit = new Properties();
				rabbit.load(in);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
