package com.sms.international.admin.service.impl;


import com.sms.international.admin.mapper.SmsCustomerMapper;
import com.sms.international.admin.model.SmsCustomer;
import com.sms.international.admin.service.BaseService;
import com.sms.international.admin.service.SmsCustomerService;
import com.sms.international.admin.utils.DateUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SmsCustomerServiceImpl extends BaseService<SmsCustomer, SmsCustomerMapper> implements SmsCustomerService{

	/**
	 * 分页查询客户信息
	 *
	 * @param customer
	 * @return
	 */
	@Override
	public JSONObject findByList(SmsCustomer customer) {
		int num = mapper.countTotal(customer);
		JSONObject obj = new JSONObject();
		obj.put("code", 0);
		obj.put("msg", 0);
		obj.put("count", num);
		List<SmsCustomer> customers = mapper.findByT(customer);
		obj.put("data", JSONArray.fromObject(customers));
		return obj;
	}

	/**
	 * 添加/修改用户
	 *
	 * @param customer
	 * @return
	 */
	@Override
	public int addOrEdit(SmsCustomer customer) {
		int result = 0;
		if (customer.getId() != null && customer.getId() > 0) {
			result = mapper.update(customer);
		} else {
			customer.setCreate_date(DateUtil.getFormatTime(new Date()));
			result = mapper.save(customer);
		}
		return result;
	}

	/**
	 * find sms_customer by id
	 *
	 * @param id
	 * @return
	 */
	@Override
	public SmsCustomer findById(int id) {
		return mapper.findOne(id);
	}
}
