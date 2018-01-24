package com.sms.international.admin.service.impl;

import com.sms.international.admin.mapper.SmsSignStoreMapper;
import com.sms.international.admin.model.SmsSignStore;
import com.sms.international.admin.service.BaseService;
import com.sms.international.admin.service.SmsSignStoreService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author guojiaju
 * Date 2017/12/13
 * Description
 */
@Service
@Transactional
public class SmsSignStoreServiceImpl extends BaseService<SmsSignStore,SmsSignStoreMapper> implements SmsSignStoreService {
    /**
     * 查询用户签名库
     *
     * @param smsSignStore
     * @return
     */
    @Override
    public JSONObject findByPage(SmsSignStore smsSignStore) {
        int count = mapper.countTotal(smsSignStore);
        List<SmsSignStore> list = mapper.findByT(smsSignStore);
        JSONObject obj = new JSONObject();
        obj.put("code", 0);
        obj.put("msg", 0);
        obj.put("count", count);
        obj.put("data", JSONArray.fromObject(list));
        return obj;
    }

    /**
     * 批量删除签名
     *
     * @param ids
     * @return
     */
    @Override
    public int batchRemove(String[] ids) throws Exception{
        return mapper.batchRemove(ids);
    }

    /**
     * 搜索删除
     *
     * @param smsSignStore
     * @return
     */
    @Override
    public int removeByT(SmsSignStore smsSignStore) throws Exception{
        return mapper.removeByT(smsSignStore);
    }

    /**
     * 新增签名
     *
     * @param smsSignStore
     * @return
     */
    @Override
    public int add(SmsSignStore smsSignStore) throws Exception{
        return mapper.save(smsSignStore);
    }

    /**
     * 更新签名
     *
     * @param smsSignStore
     * @return
     */
    @Override
    public int update(SmsSignStore smsSignStore) throws Exception{
        return mapper.update(smsSignStore);
    }

    /**
     * 查询签名
     *
     * @param smsSignStore
     * @return
     */
    @Override
    public SmsSignStore findOneByT(SmsSignStore smsSignStore) {
        return mapper.findOneByT(smsSignStore);
    }

    /**
     * 搜索报备
     *
     * @param smsSignStore
     * @return
     * @throws Exception
     */
    @Override
    public int registerBySearch(SmsSignStore smsSignStore) throws Exception {
        return mapper.registerBySearch(smsSignStore);
    }

    /**
     * 查找签名
     *
     * @param uid
     * @return
     */
    @Override
    public SmsSignStore findByUidAndExpend(Integer uid) {
        return mapper.findByUidAndExpend(uid);
    }
}
