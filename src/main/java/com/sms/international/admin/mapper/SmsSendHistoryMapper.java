package com.sms.international.admin.mapper;

import com.sms.international.admin.model.SmsSendHistory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author guojiaju
 * Date 2017/11/27
 * Description 历史记录sql映射接口
 */
@Repository
public interface SmsSendHistoryMapper extends BaseMapper<Integer,SmsSendHistory> {

    void insertToSendingBatch(List<SmsSendHistory> list);
}
