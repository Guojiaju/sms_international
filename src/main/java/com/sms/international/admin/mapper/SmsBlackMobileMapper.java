package com.sms.international.admin.mapper;

import com.sms.international.admin.model.SmsBlackMobile;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author guojiaju
 * Date 2017/12/11
 * Description 系统黑名单sql映射
 */
@Repository
public interface SmsBlackMobileMapper extends BaseMapper<Integer,SmsBlackMobile>{

    /**
     * 批量保存黑名单信息
     * @param list
     * @return
     */
    int batchSave(@Param("list") List<SmsBlackMobile> list);

    /**
     * 查询已存在的黑名单
     * @param list
     * @param group_id
     * @return
     */
    List<Long> getExistMobiles(@Param("list") List<Long> list, @Param("group_id") Integer group_id);
}
