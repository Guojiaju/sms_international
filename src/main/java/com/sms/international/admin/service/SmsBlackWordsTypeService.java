package com.sms.international.admin.service;

import com.sms.international.admin.model.SmsBlackWordsType;

import java.util.List;

/**
 * Author guojiaju
 * Date 2017/12/21
 * Description 屏蔽词类型接口
 */
public interface SmsBlackWordsTypeService {

    /**
     * 查询所有的屏蔽词类型
     * @return
     */
    List<SmsBlackWordsType> getAll();

    /**
     * 查询屏蔽词类型
     * @param smsBlackWordsType
     * @return
     */
    SmsBlackWordsType findByWhere(SmsBlackWordsType smsBlackWordsType);
}
