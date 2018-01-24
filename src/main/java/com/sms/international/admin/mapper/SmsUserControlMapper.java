package com.sms.international.admin.mapper;

import com.sms.international.admin.model.SmsUserControl;
import org.springframework.stereotype.Repository;

/**
 * Author guojiaju
 * Date 2017/11/27
 * Description 用户控制表映射接口
 */
@Repository
public interface SmsUserControlMapper {

    /**
     * 根据用户id查询该用户的控制信息
     * @param uid
     * @return
     */
    SmsUserControl findByUid(Integer uid);

    /**
     * 新增用户控制信息
     * @param smsUserControl
     * @return
     */
    int save(SmsUserControl smsUserControl);

    /**
     * 修改用户控制信息
     * @param smsUserControl
     * @return
     */
    int update(SmsUserControl smsUserControl);
}
