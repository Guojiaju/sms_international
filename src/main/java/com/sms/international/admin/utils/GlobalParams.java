package com.sms.international.admin.utils;

import com.sms.international.admin.model.SmsBlackWordsType;
import com.sms.international.admin.model.SmsChannel;
import com.sms.international.admin.model.SmsCountryInfos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author guojiaju
 * Date 2017/11/28
 * Description 全局参数
 */
public class GlobalParams {

    public static final String REQ_CTX = PropertiesUtils.get("reqCtx");

    /**
     * 国家map key
     */
    public static final String COUNTRY_KEY = "COUNTRY";

    /**
     * 通道map key
     */
    public static final String CHANNEL_KEY = "CHANNEL";

    /**
     * 所有国家信息
     */
    public static Map<String,List<SmsCountryInfos>> COUNTRY_MAP = new HashMap<String,List<SmsCountryInfos>>();
    
    /**
     * 【所有国家信息key=countryID，value=countryEntity】
     */
    public static Map<Integer,SmsCountryInfos> COUNTRY_ID_MAP=new HashMap<Integer,SmsCountryInfos>();
    

    /**
     * 所有通道信息
     */
    public static Map<String,List<SmsChannel>> CHANNEL_MAP = new HashMap<String,List<SmsChannel>>();

    /**
     * 队列管理
     */
    public static Map<String, HashMap> dictionaryInfoMap = new HashMap<String, HashMap>();

    /**
	 * 移动通道
	 */
	public static List<SmsChannel> CHANNEL_MOBILE = new ArrayList<SmsChannel>();
	/**
	 * 联通通道
	 */
	public static List<SmsChannel> CHANNEL_UNICOM = new ArrayList<SmsChannel>();
	/**
	 * 电信通道
	 */
	public static List<SmsChannel> CHANNEL_TELECOM = new ArrayList<SmsChannel>();

    /**
     * 屏蔽词缓存
     */
    public static Map<Integer,SmsBlackWordsType> BLACK_WORDS = new HashMap<Integer,SmsBlackWordsType>();
}
