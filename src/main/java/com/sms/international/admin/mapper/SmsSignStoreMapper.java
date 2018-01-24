package com.sms.international.admin.mapper;

import com.sms.international.admin.model.SmsSignStore;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Author guojiaju
 * Date 2017/12/13
 * Description 签名sql映射
 */
@Repository
public interface SmsSignStoreMapper extends BaseMapper<Integer,SmsSignStore> {

    /**
     * 批量删除签名
     * @param ids
     * @return
     */
    int batchRemove(@Param("ids") String[] ids);

    /**
     * 查询签名
     * @param signStore
     * @return
     */
    SmsSignStore findOneByT(SmsSignStore signStore);

    /**
     * 搜索删除
     * @param smsSignStore
     * @return
     */
    int removeByT(SmsSignStore smsSignStore);

    /**
     * 搜索报备
     * @param smsSignStore
     * @return
     */
    int registerBySearch(SmsSignStore smsSignStore);

    /**
     * 查询用户的签名
     * @param uid
     * @return
     */
    SmsSignStore findByUidAndExpend(@Param("uid") Integer uid);

}
