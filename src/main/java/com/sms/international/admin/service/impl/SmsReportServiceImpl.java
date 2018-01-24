package com.sms.international.admin.service.impl;

import com.sms.international.admin.mapper.SmsReportMapper;
import com.sms.international.admin.model.SmsReport;
import com.sms.international.admin.service.SmsReportService;
import com.sms.international.admin.utils.DateUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author guojiaju
 * Date 2017/12/7
 * Description 回执状态接口实现类
 */
@Service
public class SmsReportServiceImpl implements SmsReportService {

    @Autowired
    private SmsReportMapper smsReportMapper;

    /**
     * 分页查询回执
     *
     * @param report
     * @return
     */
    @Override
    public JSONObject findByPage(SmsReport report) {
        int num = smsReportMapper.countTotal(report);
        JSONObject obj = new JSONObject();
        obj.put("code", 0);
        obj.put("msg", 0);
        obj.put("count", num);
        List<SmsReport> reports = smsReportMapper.findRptList(report);
        for(SmsReport temp : reports){
            temp.setRptstat(temp.getRptcode());
            temp.setChannel(temp.getChannelId());
            temp.setExpid(report.getExpid());
            temp.setSenddate((DateUtil.getTime(Long.parseLong(temp.getRpttime().replace("-","").replace(":","").replace(" ","")))-DateUtil.getTime(report.getSenddate()))/1000);
        }
        obj.put("data", JSONArray.fromObject(reports));
        return obj;
    }
}
