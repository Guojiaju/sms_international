package com.sms.international.admin.service;

import com.sms.international.admin.model.SmsUser;
import net.sf.json.JSONObject;

/**
 * Created by 37985 on 2016/7/1.
 */
public interface SmsUserService {


	/**
	 * 分页查询用户信息
	 * @param user
	 * @return
	 */
	JSONObject findByList(SmsUser user);

	/**
	 * 添加/修改用户
	 * 
	 * @param user
	 * @return
	 */
	int addOrEdit(SmsUser user);
	
	/**
	 * find sms_user by id
	 *
	 * @param id
	 * @return
	 */
	SmsUser findById(int id);

	/**
	 * 删除用户
	 * @param userId
	 * @return
	 */
	 boolean deleteById(Integer userId);

	/**
	 * @MethodName: disableOrEndableByParentId
	 * @Description: 根据父账号id禁用/启用账号状态
	 * @Param: [parentId, stat]
	 * @Return: int
	 * @Author: guojiaju
	 * @Date: 2017/11/24
	 */
	int disableOrEndableByParentId(Integer parentId, Integer stat);

	/**
	 * 更新用户余额
	 * @param id
	 * @param charge
	 * @return
	 */
	int updateUserCharge(Integer id, double charge) throws Exception;

}
