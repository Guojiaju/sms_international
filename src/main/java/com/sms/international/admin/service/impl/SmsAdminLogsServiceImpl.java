package com.sms.international.admin.service.impl;

import com.sms.international.admin.mapper.SmsAdminLogsMapper;
import com.sms.international.admin.mapper.SmsUserLogsMapper;
import com.sms.international.admin.model.SmsAdminLogs;
import com.sms.international.admin.service.BaseService;
import com.sms.international.admin.service.SmsAdminLogsService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/***
 * 管理员日志service实现类
 * @author OYJM 
 * @date 2016年7月25日
 *
 */
@Service("smsAdminLogsService")
@Transactional
public class SmsAdminLogsServiceImpl extends BaseService<SmsAdminLogs,SmsAdminLogsMapper> implements SmsAdminLogsService {

	@Autowired
	private SmsUserLogsMapper userLogsMapper;
	/***
	 * 添加管理员日志
	 */
	@Override
	public int add(SmsAdminLogs smsAdminLogs) throws Exception{
		int result = 0;
		try {
			result = super.saveOrUpdate(smsAdminLogs);
		} catch (Exception e) {
			result = -1;
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 分页查询操作日志
	 *
	 * @param logs
	 * @return
	 */
	@Override
	public JSONObject findAll(SmsAdminLogs logs) {
		JSONObject obj = new JSONObject();
		obj.put("code", 0);
		obj.put("msg", 0);
		int num ;
		List<SmsAdminLogs> logsList;
		if(logs.getType() ==1){
			num = mapper.countTotal(logs);
			logsList = mapper.findByT(logs);
		}else{
			num = userLogsMapper.countTotal(logs);
			logsList = userLogsMapper.findByT(logs);
		}
		obj.put("count", num);
		obj.put("data",JSONArray.fromObject(logsList));
		return obj;
	}
}
