package com.sms.international.admin.service.impl;

import com.sms.international.admin.mapper.SmsUserConsumeMapper;
import com.sms.international.admin.model.SmsUserConsume;
import com.sms.international.admin.service.BaseService;
import com.sms.international.admin.service.SmsUserConsumeService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author guojiaju
 * Date 2017/12/25
 * Description 消费记录实现类
 */
@Service
public class SmsUserConsumeServiceImpl extends BaseService<SmsUserConsume,SmsUserConsumeMapper> implements SmsUserConsumeService{

    @Override
    public JSONObject findByPage(SmsUserConsume smsUserConsume) {
        int count = mapper.countTotal(smsUserConsume);
        List<SmsUserConsume> list = mapper.findByT(smsUserConsume);
        JSONObject obj = new JSONObject();
        obj.put("code", 0);
        obj.put("msg", 0);
        obj.put("count", count);
        obj.put("data", JSONArray.fromObject(list));
        return obj;
    }
}
