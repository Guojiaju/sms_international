package com.sms.international.admin.service;

import com.sms.international.admin.model.SmsUserSending;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;

public interface SmsUserSendingService {
    JSONObject findByPage(SmsUserSending smsUserSending);
    int batchUpdate(Map map);
    List<SmsUserSending> findByIdsToPart(SmsUserSending smsUserSending);
    int update(Map<String, Object> map);
    void updateMongoSendingHistory(List<SmsUserSending> list, Map<String, Object> map);
}
