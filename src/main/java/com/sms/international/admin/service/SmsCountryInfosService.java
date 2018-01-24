package com.sms.international.admin.service;

import com.sms.international.admin.model.SmsCountryInfos;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * Author guojiaju
 * Date 2017/11/28
 * Description 国家信息接口
 */
public interface SmsCountryInfosService {

    /**
     * 查询所有国家信息
     * @return
     */
    List<SmsCountryInfos> findAll();

    /**
     * 查询国家信息的分页数据
     * @param smsCountryInfos
     * @return
     */
    JSONObject findByPage(SmsCountryInfos smsCountryInfos);

    /**
     * 查询单个国家信息
     * @param smsCountryInfos
     * @return
     */
    SmsCountryInfos findOne(SmsCountryInfos smsCountryInfos);
}
