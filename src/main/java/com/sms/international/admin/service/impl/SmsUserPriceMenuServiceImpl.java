package com.sms.international.admin.service.impl;

import com.sms.international.admin.mapper.SmsUserPriceMenuMapper;
import com.sms.international.admin.model.SmsUserPriceMenu;
import com.sms.international.admin.service.SmsUserPriceMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author guojiaju
 * Date 2017/11/28
 * Description 用户价目信息接口实现类
 */
@Service
@Transactional
public class SmsUserPriceMenuServiceImpl implements SmsUserPriceMenuService {

    @Autowired
    private SmsUserPriceMenuMapper smsUserPriceMenuMapper;

    /**
     * 查询用户各国家价目信息
     *
     * @param uid
     * @return
     */
    @Override
    public List<SmsUserPriceMenu> findByUid(Integer uid) {
        return smsUserPriceMenuMapper.findByUid(uid);
    }

    /**
     * 批量新增价目信息
     *
     * @param list
     * @return
     */
    @Override
    public int batchSave(List<SmsUserPriceMenu> list) {
        return smsUserPriceMenuMapper.batchSave(list);
    }

    /**
     * 修改用户价目信息
     *
     * @param smsUserPriceMenu
     * @return
     */
    @Override
    public int update(SmsUserPriceMenu smsUserPriceMenu) {
        return smsUserPriceMenuMapper.update(smsUserPriceMenu);
    }

    /**
     * 删除用户价目信息
     *
     * @param uid
     * @return
     */
    @Override
    public int deleteByUid(Integer uid) {
        return smsUserPriceMenuMapper.deleteByUid(uid);
    }
}
