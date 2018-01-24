package com.sms.international.admin.mapper;

import com.sms.international.admin.model.SmsBlackWordsType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author guojiaju
 * Date 2017/12/20
 * Description 屏蔽词类型sql映射
 */
@Repository
public interface SmsBlackWordsTypeMapper extends BaseMapper<Integer,SmsBlackWordsType> {

    /**
     * 查询所有
     * @return
     */
    List<SmsBlackWordsType> getAll();

    /**
     * 查询屏蔽词类型
     * @param smsBlackWordsType
     * @return
     */
    SmsBlackWordsType findOneByWhere(SmsBlackWordsType smsBlackWordsType);
}
