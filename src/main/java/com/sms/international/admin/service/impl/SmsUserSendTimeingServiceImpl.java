package com.sms.international.admin.service.impl;

import com.sms.international.admin.mapper.SmsUserSendTimeingMapper;
import com.sms.international.admin.model.SmsUserSendTimeing;
import com.sms.international.admin.service.BaseService;
import com.sms.international.admin.service.SmsUserSendTimeingService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmsUserSendTimeingServiceImpl extends BaseService<SmsUserSendTimeing, SmsUserSendTimeingMapper> implements SmsUserSendTimeingService {

	@Autowired
	private SmsUserSendTimeingMapper smsUserSendTimeingMapper;

	@Override
	public JSONObject findPageList(SmsUserSendTimeing timeing) {
	    int num = smsUserSendTimeingMapper.countTotalToBatch(timeing);
        List<SmsUserSendTimeing> list = mapper.findToBatch(timeing);
        JSONObject obj = new JSONObject();
        obj.put("code", 0);
        obj.put("msg", 0);
        obj.put("count", num);
        obj.put("data", JSONArray.fromObject(list));

        return obj;
	}

	@Override
	public int clearByPid(int pid) {
		// TODO Auto-generated method stub
		return smsUserSendTimeingMapper.clearByPid(pid);
	}

	@Override
	public JSONObject viewMobile(SmsUserSendTimeing timeing) {
		int count = smsUserSendTimeingMapper.countTotal(timeing);
		List<SmsUserSendTimeing> result = smsUserSendTimeingMapper.findByT(timeing);
		JSONObject obj = new JSONObject();
        obj.put("code", 0);
        obj.put("msg", 0);
        obj.put("count", count);
        obj.put("data", JSONArray.fromObject(result));

		return obj;
	}

	@Override
	public List<SmsUserSendTimeing> findByList(SmsUserSendTimeing timeing) {
		// TODO Auto-generated method stub
		return smsUserSendTimeingMapper.findByList(timeing);
	}
}
