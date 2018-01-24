package com.sms.international.admin.mapper;


import com.sms.international.admin.model.SmsUserSending;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SmsUserSendingMapper extends BaseMapper<Integer,SmsUserSending>{

    List<SmsUserSending> findByIdsToPart(SmsUserSending smsUserSending);

    int batchUpdate(Map map);

    int update(Map<String, Object> map);
}