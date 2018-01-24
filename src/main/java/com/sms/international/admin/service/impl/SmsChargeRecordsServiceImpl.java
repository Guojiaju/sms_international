package com.sms.international.admin.service.impl;

import com.sms.international.admin.mapper.SmsChargeRecordsMapper;
import com.sms.international.admin.model.SmsChargeRecords;
import com.sms.international.admin.service.BaseService;
import com.sms.international.admin.service.SmsChargeRecordsService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author guojiaju
 * Date 2017/12/5
 * Description
 */
@Service
@Transactional
public class SmsChargeRecordsServiceImpl extends BaseService<SmsChargeRecords,SmsChargeRecordsMapper> implements SmsChargeRecordsService {

    /**
     * 查询充值记录信息
     *
     * @param smsChargeRecords
     * @return
     */
    @Override
    public JSONObject findByPage(SmsChargeRecords smsChargeRecords) {
        int num = mapper.countTotal(smsChargeRecords);
        JSONObject obj = new JSONObject();
        obj.put("code", 0);
        obj.put("msg", 0);
        obj.put("count", num);
        List<SmsChargeRecords> records = mapper.findByT(smsChargeRecords);
        obj.put("data", JSONArray.fromObject(records));
        return obj;
    }

    /**
     * 修改到账状态
     *
     * @param smsChargeRecords
     * @return
     * @throws Exception
     */
    @Override
    public int updatePayStat(SmsChargeRecords smsChargeRecords) throws Exception {
        return mapper.updatePayStat(smsChargeRecords);
    }

    /**
     * 保存充值记录
     *
     * @param smsChargeRecords
     * @return
     * @throws Exception
     */
    @Override
    public int save(SmsChargeRecords smsChargeRecords) throws Exception {
        return mapper.save(smsChargeRecords);
    }

}
