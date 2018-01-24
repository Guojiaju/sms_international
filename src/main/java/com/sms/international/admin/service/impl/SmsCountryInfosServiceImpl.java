package com.sms.international.admin.service.impl;

import com.sms.international.admin.mapper.SmsCountryInfosMapper;
import com.sms.international.admin.model.SmsCountryInfos;
import com.sms.international.admin.service.SmsCountryInfosService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author guojiaju
 * Date 2017/11/28
 * Description 国家信息接口实现类
 */
@Service
public class SmsCountryInfosServiceImpl implements SmsCountryInfosService {

    @Autowired
    private SmsCountryInfosMapper smsCountryInfosMapper;

    /**
     * 查询所有国家信息
     *
     * @return
     */
    @Override
    public List<SmsCountryInfos> findAll() {
        return smsCountryInfosMapper.findAll();
    }

    /**
     * 查询国家信息的分页数据
     *
     * @param smsCountryInfos
     * @return
     */
    @Override
    public JSONObject findByPage(SmsCountryInfos smsCountryInfos) {
        int num = smsCountryInfosMapper.countTotal(smsCountryInfos);
        JSONObject obj = new JSONObject();
        obj.put("code", 0);
        obj.put("msg", 0);
        obj.put("count", num);
        List<SmsCountryInfos> countryInfos = smsCountryInfosMapper.findByT(smsCountryInfos);
        obj.put("data", JSONArray.fromObject(countryInfos));
        return obj;
    }

    /**
     * 查询单个国家信息
     *
     * @param smsCountryInfos
     * @return
     */
    @Override
    public SmsCountryInfos findOne(SmsCountryInfos smsCountryInfos) {
        return smsCountryInfosMapper.findOne(smsCountryInfos);
    }
}
