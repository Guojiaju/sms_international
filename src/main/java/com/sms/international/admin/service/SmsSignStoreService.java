package com.sms.international.admin.service;

import com.sms.international.admin.model.SmsSignStore;
import net.sf.json.JSONObject;

/**
 * Author guojiaju
 * Date 2017/12/13
 * Description 签名接口
 */
public interface SmsSignStoreService {

    /**
     * 查询用户签名库
     * @param smsSignStore
     * @return
     */
    JSONObject findByPage(SmsSignStore smsSignStore);

    /**
     * 批量删除签名
     * @param ids
     * @return
     */
    int batchRemove(String[] ids) throws Exception;

    /**
     * 搜索删除
     * @param smsSignStore
     * @return
     */
    int removeByT(SmsSignStore smsSignStore) throws Exception;

    /**
     * 查询签名
     * @param id
     * @return
     */
    SmsSignStore findOne(Integer id);

    /**
     * 新增签名
     * @param smsSignStore
     * @return
     */
    int add(SmsSignStore smsSignStore) throws Exception;

    /**
     * 更新签名
     * @param smsSignStore
     * @return
     */
    int update(SmsSignStore smsSignStore) throws Exception;

    /**
     * 删除签名
     * @param id
     * @return
     */
    boolean deleteById(Integer id) throws Exception;

    /**
     * 查询签名
     * @param smsSignStore
     * @return
     */
    SmsSignStore findOneByT(SmsSignStore smsSignStore);

    /**
     * 搜索报备
     * @param smsSignStore
     * @return
     * @throws Exception
     */
    int registerBySearch(SmsSignStore smsSignStore)throws Exception;

    /**
     * 查找签名
     * @param uid
     * @return
     */
    SmsSignStore findByUidAndExpend(Integer uid);
}
