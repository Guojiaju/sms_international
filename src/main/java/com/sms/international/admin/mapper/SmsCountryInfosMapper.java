package com.sms.international.admin.mapper;

import com.sms.international.admin.model.SmsCountryInfos;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author guojiaju
 * Date 2017/11/27
 * Description 国家映射接口
 */
@Repository
public interface SmsCountryInfosMapper{

    /**
     * 查询所有的国家信息
     * @return
     */
    List<SmsCountryInfos> findAll();

    /**
     * 分页查询国家信息
     * @param smsCountryInfos
     * @return
     */
    List<SmsCountryInfos> findByT(SmsCountryInfos smsCountryInfos);

    /**
     * 统计国家数量
     * @param smsCountryInfos
     * @return
     */
    int countTotal(SmsCountryInfos smsCountryInfos);

    /**
     * 查询单个国家信息
     * @param smsCountryInfos
     * @return
     */
    SmsCountryInfos findOne(SmsCountryInfos smsCountryInfos);

}
