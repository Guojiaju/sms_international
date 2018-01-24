package com.sms.international.admin.service;

import com.sms.international.admin.model.SmsUserPriceMenu;

import java.util.List;

/**
 * Author guojiaju
 * Date 2017/11/28
 * Description 用户价目表接口
 */
public interface SmsUserPriceMenuService {

    /**
     * 查询用户各国家价目信息
     * @param uid
     * @return
     */
    List<SmsUserPriceMenu> findByUid(Integer uid);

    /**
     * 批量新增价目信息
     * @param list
     * @return
     */
    int batchSave(List<SmsUserPriceMenu> list);

    /**
     * 修改用户价目信息
     * @param smsUserPriceMenu
     * @return
     */
    int update(SmsUserPriceMenu smsUserPriceMenu);

    /**
     * 删除用户价目信息
     * @param uid
     * @return
     */
    int deleteByUid(Integer uid);

}
