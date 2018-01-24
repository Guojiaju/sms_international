package com.sms.international.admin.mapper;

import com.sms.international.admin.model.SmsUserSendTimeing;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmsUserSendTimeingMapper extends BaseMapper<Integer, SmsUserSendTimeing> {

    int clearByPid(int pid);

	/**
	 * 分组查询
	 *
	 * @param timeing
	 * @return
	 */
	List<SmsUserSendTimeing> findToBatch(SmsUserSendTimeing timeing);

	/**
	 * 分组查询统计
	 *
	 * @param timeing
	 * @return
	 */
	int countTotalToBatch(SmsUserSendTimeing timeing);

	/**
	 * 查询记录(包含字段:提交时间,发送时间,号码,内容')
	 *
	 * @param timeing
	 * @return
	 */
	List<SmsUserSendTimeing> findByList(SmsUserSendTimeing timeing);

	/***
	 * 批量插入
	 *
	 * @param list
	 * @return
	 */
	int batchInsert(List<SmsUserSendTimeing> list);
}