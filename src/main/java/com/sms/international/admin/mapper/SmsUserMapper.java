package com.sms.international.admin.mapper;

import com.sms.international.admin.model.SmsUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsUserMapper extends BaseMapper<Integer, SmsUser> {

	
	int updateUserCharge(@Param("id") Integer id, @Param("sms") double sms);
	
	public int updatePassword(SmsUser user);

	/**
	 * 根据父账号id批量修改账号状态
	 * @param parentId
	 * @return
	 */
	int updateStatByParentId(@Param("parentId") Integer parentId, @Param("stat") Integer stat);

}
