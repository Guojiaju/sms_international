package com.sms.international.admin.service;

import com.sms.international.admin.model.SmsBlackMobileType;

import java.util.List;

/**
 * Author guojiaju
 * Date 2017/12/12
 * Description 黑名单类型接口
 */
public interface SmsBlackMobileTypeService {

    /**
     * 查询所有的黑名单类型
     * @return
     */
    List<SmsBlackMobileType> getAll() throws Exception;
}
