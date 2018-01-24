package com.sms.international.admin.service;

import com.sms.international.admin.model.SmsChannelPriceMenu;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * Author guojiaju
 * Date 2017/11/28
 * Description 通道价目表接口
 */
public interface SmsChannelPriceMenuService {

    /**
     * 通道价目分页信息
     * @param smsChannelPriceMenu
     * @return
     */
    JSONObject findByPage(SmsChannelPriceMenu smsChannelPriceMenu) throws Exception;

    /**
     * 根据通道id查询该通道价目信息
     * @param channelId
     * @return
     * @throws Exception
     */
    List<SmsChannelPriceMenu> findByChannelId(Integer channelId) throws Exception;

    /**
     * 新增通道价目信息
     * @param smsChannelPriceMenu
     * @return
     */
    int save(SmsChannelPriceMenu smsChannelPriceMenu) throws Exception;

    /**
     * 修改通道价目信息
     * @param smsChannelPriceMenu
     * @return
     */
    int update(SmsChannelPriceMenu smsChannelPriceMenu) throws Exception;

    /**
     * 删除通道价目
     * @param id
     * @return
     */
    int deleteById(Integer id) throws Exception;

    /**
     * 根据通道id删除对应的价目信息
     * @param id
     * @return
     * @throws Exception
     */
    int deleteByChannelId(Integer id) throws Exception;

    /**
     * 批量保存通道价目信息
     * @param list
     * @return
     * @throws Exception
     */
    int batchSave(List<SmsChannelPriceMenu> list) throws Exception;
}
