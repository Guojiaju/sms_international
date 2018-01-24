package com.sms.international.admin.service;

import com.sms.international.admin.model.SmsUserSendTimeing;
import net.sf.json.JSONObject;

import java.util.List;

public interface SmsUserSendTimeingService {


    /**
     * 分页查询
     * @param timeing
     * @return
     */
	JSONObject findPageList(SmsUserSendTimeing timeing);

	/**
	 * 根据条件查询记录(单条显示)
	 *
	 * @param timeing
	 * @return
	 */
	List<SmsUserSendTimeing> findByList(SmsUserSendTimeing timeing);

	/**
	 * 显示号码
	 *
	 * @param timeing
	 * @return
	 */
	JSONObject viewMobile(SmsUserSendTimeing timeing);

    /**
	 * 按批次号取消定时
	 *
	 * @param pid
	 * @return
	 */
	int clearByPid(int pid);
}
