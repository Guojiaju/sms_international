package com.sms.international.admin.service;

import com.sms.international.admin.model.SmsCustomer;
import net.sf.json.JSONObject;

/**
 * Created by guojiaju on 2017/11/22.
 */
public interface SmsCustomerService {

	/**
	 * 分页查询客户信息
	 * @param customer
	 * @return
	 */
	JSONObject findByList(SmsCustomer customer);

	/**
	 * 添加/修改用户
	 * @param customer
	 * @return
	 */
	int addOrEdit(SmsCustomer customer);
	
	/**
	 * find sms_customer by id
	 * @param id
	 * @return
	 */
	SmsCustomer findById(int id);


	/**
	 * 删除客户
	 * @param id
	 * @return
	 */
	boolean deleteById(Integer id);

}
