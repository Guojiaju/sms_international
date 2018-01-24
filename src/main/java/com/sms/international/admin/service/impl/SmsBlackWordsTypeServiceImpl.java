package com.sms.international.admin.service.impl;

import com.sms.international.admin.mapper.SmsBlackWordsTypeMapper;
import com.sms.international.admin.model.SmsBlackWordsType;
import com.sms.international.admin.service.BaseService;
import com.sms.international.admin.service.SmsBlackWordsTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author guojiaju
 * Date 2017/12/21
 * Description
 */
@Service
@Transactional
public class SmsBlackWordsTypeServiceImpl extends BaseService<SmsBlackWordsType,SmsBlackWordsTypeMapper> implements SmsBlackWordsTypeService{


    /**
     * 查询所有的屏蔽词类型
     *
     * @return
     */
    @Override
    public List<SmsBlackWordsType> getAll() {
        return mapper.getAll();
    }

    /**
     * 查询屏蔽词类型
     *
     * @param smsBlackWordsType
     * @return
     */
    @Override
    public SmsBlackWordsType findByWhere(SmsBlackWordsType smsBlackWordsType) {
        return mapper.findOneByWhere(smsBlackWordsType);
    }
}
