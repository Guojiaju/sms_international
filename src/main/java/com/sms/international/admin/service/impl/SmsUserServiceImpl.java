package com.sms.international.admin.service.impl;


import com.sms.international.admin.mapper.SmsUserMapper;
import com.sms.international.admin.model.SmsUser;
import com.sms.international.admin.service.BaseService;
import com.sms.international.admin.service.SmsUserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("smsUserService")
@Transactional
public class SmsUserServiceImpl  extends BaseService<SmsUser, SmsUserMapper> implements SmsUserService{
	
	@Autowired
	private SmsUserMapper smsUserMapper;

	@Override
	public JSONObject findByList(SmsUser user){
		int num = mapper.countTotal(user);
		JSONObject obj = new JSONObject();
		obj.put("code", 0);
		obj.put("msg", 0);
		obj.put("count", num);
		List<SmsUser> users = mapper.findByT(user);
		obj.put("data", JSONArray.fromObject(users));
		return obj;
	}

	@Override
	public int addOrEdit(SmsUser user) {
		int result = 0;
		if (user.getId() != null && user.getId() > 0) {
			result = smsUserMapper.update(user);
		} else {
			result = smsUserMapper.save(user);
		}
		return result;
	}

	@Override
	public SmsUser findById(int id) {
		return mapper.findOne(id);
	}

	/**
	 * @param parentId
	 * @param stat
	 * @MethodName: disableOrEndableByParentId
	 * @Description: 根据父账号id禁用/启用账号状态
	 * @Param: [parentId, stat]
	 * @Return: int
	 * @Author: guojiaju
	 * @Date: 2017/11/24
	 */
	@Override
	public int disableOrEndableByParentId(Integer parentId, Integer stat) {
		return smsUserMapper.updateStatByParentId(parentId,stat);
	}

	/**
	 * 更新用户余额
	 *
	 * @param id
	 * @param charge
	 * @return
	 */
	@Override
	public int updateUserCharge(Integer id, double charge) throws Exception{
		return smsUserMapper.updateUserCharge(id,charge);
	}
}
