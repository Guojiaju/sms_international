package com.sms.international.admin.service.impl;

import com.sms.international.admin.mapper.SmsUserControlMapper;
import com.sms.international.admin.model.SmsUserControl;
import com.sms.international.admin.service.SmsUserControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author guojiaju
 * Date 2017/11/28
 * Description 用户控制信息实现类
 */
@Service
@Transactional
public class SmsUserControlServiceImpl implements SmsUserControlService{

    @Autowired
    private SmsUserControlMapper smsUserControlMapper;

    /**
     * 根据用户id查询该用户的控制信息
     *
     * @param uid
     * @return
     */
    @Override
    public SmsUserControl findByUid(Integer uid) {
        return smsUserControlMapper.findByUid(uid);
    }

    /**
     * 新增用户控制信息
     *
     * @param smsUserControl
     * @return
     */
    @Override
    public int save(SmsUserControl smsUserControl) throws Exception{
        return smsUserControlMapper.save(smsUserControl);
    }

    /**
     * 修改用户控制信息
     *
     * @param smsUserControl
     * @return
     */
    @Override
    public int update(SmsUserControl smsUserControl) throws Exception{
        return smsUserControlMapper.update(smsUserControl);
    }
}
