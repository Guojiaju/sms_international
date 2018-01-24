package com.sms.international.admin.service.impl;

import com.sms.international.admin.mapper.SmsBlackMobileTypeMapper;
import com.sms.international.admin.model.SmsBlackMobileType;
import com.sms.international.admin.service.BaseService;
import com.sms.international.admin.service.SmsBlackMobileTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author guojiaju
 * Date 2017/12/12
 * Description
 */
@Service
@Transactional
public class SmsBlackMobileTypeServiceImpl extends BaseService<SmsBlackMobileType,SmsBlackMobileTypeMapper> implements SmsBlackMobileTypeService {
    /**
     * 查询所有的黑名单类型
     *
     * @return
     */
    @Override
    public List<SmsBlackMobileType> getAll() throws Exception{
        return mapper.getAll();
    }
}
