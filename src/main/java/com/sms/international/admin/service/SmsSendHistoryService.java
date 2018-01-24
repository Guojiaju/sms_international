package com.sms.international.admin.service;

import com.sms.international.admin.model.SmsChannel;
import com.sms.international.admin.model.SmsSendHistory;
import net.sf.json.JSONObject;

import java.util.Map;

/**
 * Author guojiaju
 * Date 2017/12/6
 * Description 发送记录接口
 */
public interface SmsSendHistoryService {


    /**
     * 查询3天内的发送记录
     * @param smsSendHistory
     * @return
     */
    JSONObject findByPageIn3(SmsSendHistory smsSendHistory);

    /**
     * 查询发送记录
     * @param smsSendHistory
     * @return
     */
    JSONObject findByPage(SmsSendHistory smsSendHistory);

    /**
     * 查询失败补发
     * @param smsSendHistory
     * @return
     */
    JSONObject findResendByPage(SmsSendHistory smsSendHistory);

    /**
     * 补发
     * @param smsSendHistory
     * @param channel
     * @return
     */
    int resend(SmsSendHistory smsSendHistory, SmsChannel channel);

    /***
	 * 按搜索补发
	 * @param jo
	 * @return
	 */
	Map<String,Integer> resendByQuery(JSONObject jo);

    /**
     * 查询3天内的详情
     * @param smsSendHistory
     * @return
     */
    JSONObject findDetailIn3(SmsSendHistory smsSendHistory);
}
