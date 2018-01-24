package com.sms.international.admin.service.impl;

import com.sms.international.admin.mapper.SmsBlackMobileMapper;
import com.sms.international.admin.model.SmsBlackMobile;
import com.sms.international.admin.service.BaseService;
import com.sms.international.admin.service.SmsBlackMobileService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author guojiaju
 * Date 2017/12/11
 * Description
 */
@Service
@Transactional
public class SmsBlackMobileServiceImpl extends BaseService<SmsBlackMobile,SmsBlackMobileMapper> implements SmsBlackMobileService {
    /**
     * 批量保存
     *
     * @return
     * @throws Exception
     */
    @Override
    public int batchSave(List<SmsBlackMobile> list) throws Exception {
        return mapper.batchSave(list);
    }

    /**
     * 分页查询
     *
     * @param mobile
     * @return
     * @throws Exception
     */
    @Override
    public JSONObject findByPage(SmsBlackMobile mobile) throws Exception {
        int num = mapper.countTotal(mobile);
        JSONObject obj = new JSONObject();
        obj.put("code", 0);
        obj.put("msg", 0);
        obj.put("count", num);
        List<SmsBlackMobile> mobiles = mapper.findByT(mobile);
        obj.put("data", JSONArray.fromObject(mobiles));
        return obj;
    }

    /**
     * 查询单个黑名单信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public SmsBlackMobile findOne(Integer id) {
        return mapper.findOne(id);
    }

    /**
     * 修改黑名单
     *
     * @param mobile
     * @return
     * @throws Exception
     */
    @Override
    public int update(SmsBlackMobile mobile) throws Exception {
        return mapper.update(mobile);
    }

    /**
     * 删除单个黑名单信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public int deleteOne(Integer id) throws Exception {
        return mapper.deleteById(id);
    }

    /**
     * 查询已存在的系统黑名单
     *
     * @param group_id
     * @param list
     * @return
     */
    @Override
    public List<Long> getExistMobiles(Integer group_id, List<Long> list) throws Exception{
        return mapper.getExistMobiles(list,group_id);
    }

}
