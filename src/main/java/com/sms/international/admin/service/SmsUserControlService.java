package com.sms.international.admin.service;

import com.sms.international.admin.model.SmsUserControl;

/**
 * Author guojiaju
 * Date 2017/11/28
 * Description 用户控制信息接口
 */
public interface SmsUserControlService {

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
    int save(SmsUserControl smsUserControl) throws Exception;

    /**
     * 修改用户控制信息
     * @param smsUserControl
     * @return
     */
    int update(SmsUserControl smsUserControl) throws Exception;
}
