package com.sms.international.admin.mapper;

import com.sms.international.admin.model.SmsChargeRecords;
import org.springframework.stereotype.Repository;

/**
 * Author guojiaju
 * Date 2017/12/5
 * Description
 */
@Repository
public interface SmsChargeRecordsMapper extends BaseMapper<Integer,SmsChargeRecords>{
    /**
     * 修改到账状态
     * @param smsChargeRecords
     * @return
     */
    int updatePayStat(SmsChargeRecords smsChargeRecords);
}
