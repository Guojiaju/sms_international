package com.sms.international.admin.service;

import com.sms.international.admin.model.SmsReport;
import net.sf.json.JSONObject;

/**
 * Author guojiaju
 * Date 2017/12/7
 * Description 回执接口
 */
public interface SmsReportService {
    /**
     * 分页查询回执
     * @param report
     * @return
     */
    JSONObject findByPage(SmsReport report);
}
