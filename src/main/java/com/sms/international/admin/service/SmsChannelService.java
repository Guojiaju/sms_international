package com.sms.international.admin.service;

import com.sms.international.admin.model.SmsChannel;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * Author guojiaju
 * Date 2017/11/29
 * Description 通道接口
 */
public interface SmsChannelService {

    /**
     * 查询所有的通道信息
     * @return
     */
    List<SmsChannel> getAll() throws Exception;

    /**
     * 查询所有通道信息
     * @param smsChannel
     * @return
     */
    JSONObject findAll(SmsChannel smsChannel) throws Exception;

    /**
     * 新增通道
     * @param smsChannel
     * @return
     */
    int save(SmsChannel smsChannel) throws Exception;

    /**
     * 修改通道信息
     * @param smsChannel
     * @return
     */
    int update(SmsChannel smsChannel) throws Exception;

    /**
     * 删除通道
     * @param id
     * @return
     */
    int deleteById(Integer id) throws Exception;

    /**
     * 查询该id的通道
     * @param id
     * @return
     */
    SmsChannel findOne(Integer id) throws Exception;

}
