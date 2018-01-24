package com.sms.international.admin.service;

import com.sms.international.admin.model.SmsChargeRecords;
import net.sf.json.JSONObject;

/**
 * Author guojiaju
 * Date 2017/12/5
 * Description 充值记录接口
 */
public interface SmsChargeRecordsService {

    /**
     * 查询充值记录信息
     * @param smsChargeRecords
     * @return
     */
    JSONObject findByPage(SmsChargeRecords smsChargeRecords);

    /**
     * 修改到账状态
     * @param smsChargeRecords
     * @return
     * @throws Exception
     */
    int updatePayStat(SmsChargeRecords smsChargeRecords) throws Exception;

    /**
     * 保存充值记录
     * @param smsChargeRecords
     * @return
     * @throws Exception
     */
    int save(SmsChargeRecords smsChargeRecords) throws Exception;

}
