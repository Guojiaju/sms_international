package com.sms.international.admin.service;

import com.sms.international.admin.model.SmsUserConsume;
import net.sf.json.JSONObject;

/**
 * Author guojiaju
 * Date 2017/12/25
 * Description 用户消费记录
 */
public interface SmsUserConsumeService {

    JSONObject findByPage(SmsUserConsume smsUserConsume);
}
