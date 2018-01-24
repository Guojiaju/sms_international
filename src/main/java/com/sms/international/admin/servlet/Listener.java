package com.sms.international.admin.servlet;

import com.sms.international.admin.model.SmsBlackWordsType;
import com.sms.international.admin.model.SmsChannel;
import com.sms.international.admin.model.SmsCountryInfos;
import com.sms.international.admin.service.SmsBlackWordsTypeService;
import com.sms.international.admin.service.SmsChannelService;
import com.sms.international.admin.service.SmsCountryInfosService;
import com.sms.international.admin.utils.GlobalParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletConfigAware;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.List;

/**
 * Author guojiaju
 * Date 2017/11/28
 * Description 监听器，初始化数据
 */
@Component
public class Listener implements ServletConfigAware {

    private static final Logger logger = LoggerFactory.getLogger(Listener.class);

    @Resource
    private SmsCountryInfosService smsCountryInfosService;

    @Autowired
    private SmsChannelService smsChannelService;

    @Autowired
    private SmsBlackWordsTypeService smsBlackWordsTypeService;

    private static ServletContext sc;

    /**
     * Set the {@link ServletConfig} that this object runs in.
     * <p>Invoked after population of normal bean properties but before an init
     * callback like InitializingBean's {@code afterPropertiesSet} or a
     * custom init-method. Invoked after ApplicationContextAware's
     * {@code setApplicationContext}.
     *
     * @param servletConfig ServletConfig object to be used by this object
     * @see InitializingBean#afterPropertiesSet
     * @see ApplicationContextAware#setApplicationContext
     */
    @Override
    public void setServletConfig(ServletConfig servletConfig) {
        logger.info("加载初始化数据");
        sc = servletConfig.getServletContext();
        sc.setAttribute("ctx", GlobalParams.REQ_CTX);
        //加载初始化信息
        init();
    }

    /**
     * 初始化方法
     */
    public void init(){
        cacheCountryInfos();
        cacheChannels();
        reloadChannel();
        initBlackWordsType();
        logger.info("初始化数据完成");
    }

    /**
     * 初始化国家信息放到缓存中
     */
    public void cacheCountryInfos(){
        GlobalParams.COUNTRY_MAP.put(GlobalParams.COUNTRY_KEY,smsCountryInfosService.findAll());
        GlobalParams.COUNTRY_ID_MAP.clear();
        List<SmsCountryInfos> countryList=GlobalParams.COUNTRY_MAP.get(GlobalParams.COUNTRY_KEY);
        for(SmsCountryInfos countryTemp:countryList){
        	 GlobalParams.COUNTRY_ID_MAP.put(countryTemp.getId(), countryTemp);
        }
        logger.info("国家信息加载完成..");
    }

    /**
     * 初始化通道信息放到缓存中
     */
    public void cacheChannels(){
        try {
            GlobalParams.CHANNEL_MAP.put(GlobalParams.CHANNEL_KEY,smsChannelService.getAll());
        } catch (Exception e) {
            e.printStackTrace();
            GlobalParams.CHANNEL_MAP.put(GlobalParams.CHANNEL_KEY,null);
        }
    }

    //初始化短信屏蔽词类型
    public void initBlackWordsType(){
        List<SmsBlackWordsType> list = smsBlackWordsTypeService.getAll();
        for(SmsBlackWordsType smsBlackWordsType :list){
            GlobalParams.BLACK_WORDS.put(smsBlackWordsType.getId(),smsBlackWordsType);
        }
    }


    public void reloadChannel(){
        try{
            List<SmsChannel> smsChannelList = smsChannelService.getAll();
            HashMap<Integer, String> channelNameMap = new HashMap<Integer, String>();
            HashMap<Integer, Integer> channelSizeMap = new HashMap<Integer, Integer>();

            HashMap<Integer, String> channelYidongNameMap = new HashMap<Integer, String>();
            HashMap<Integer, String> channelLiantongNameMap = new HashMap<Integer, String>();
            HashMap<Integer, String> channelXiaolingtongNameMap = new HashMap<Integer, String>();
            HashMap<Integer, String> channelDianxinNameMap = new HashMap<Integer, String>();

            GlobalParams.CHANNEL_MOBILE.clear();
            GlobalParams.CHANNEL_UNICOM.clear();
            GlobalParams.CHANNEL_TELECOM.clear();
            for (SmsChannel sc : smsChannelList) {
                channelNameMap.put(sc.getId(), sc.getChannelName());
                channelSizeMap.put(sc.getId(), sc.getSendWordsMaxlen());

                GlobalParams.dictionaryInfoMap.put("smsChannelIdNameMap", channelNameMap);
                logger.info("smsChannelIdNameMap size:" + channelNameMap.size());
                GlobalParams.dictionaryInfoMap.put("smsChannelIdSizeMap", channelSizeMap);
                logger.info("channelSizeMap size:" + channelSizeMap.size());
                GlobalParams.dictionaryInfoMap.put("channelYidongNameMap", channelYidongNameMap);
                GlobalParams.dictionaryInfoMap.put("channelLiantongNameMap", channelLiantongNameMap);
                GlobalParams.dictionaryInfoMap.put("channelXiaolingtongNameMap", channelXiaolingtongNameMap);
                GlobalParams.dictionaryInfoMap.put("channelDianxinNameMap", channelDianxinNameMap);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
	}
}
