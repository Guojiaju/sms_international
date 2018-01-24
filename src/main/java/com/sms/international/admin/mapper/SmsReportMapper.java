package com.sms.international.admin.mapper;

import com.sms.international.admin.model.SmsReport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author guojiaju
 * Date 2017/12/7
 * Description 回执sql映射接口
 */
@Repository
public interface SmsReportMapper{

    /**
     * 分页查询回执状态
     * @param report
     * @return
     */
    List<SmsReport> findRptList(SmsReport report);

    /**
     * 统计回执状态数量
     * @param report
     * @return
     */
    int countTotal(SmsReport report);
}
