package com.sms.international.admin.mapper;

import com.sms.international.admin.model.SmsBlackMobileType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author guojiaju
 * Date 2017/12/12
 * Description 黑名单类型sql接口映射
 */
@Repository
public interface SmsBlackMobileTypeMapper extends BaseMapper<Integer,SmsBlackMobileType>{
    /**
     * 查询所有黑名单类型
     * @return
     */
    List<SmsBlackMobileType> getAll();
}
