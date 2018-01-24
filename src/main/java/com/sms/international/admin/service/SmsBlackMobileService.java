package com.sms.international.admin.service;

import com.sms.international.admin.model.SmsBlackMobile;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * Author guojiaju
 * Date 2017/12/11
 * Description 系统黑名单
 */
public interface SmsBlackMobileService {
    /**
     * 批量保存
     * @return
     * @throws Exception
     */
    int batchSave(List<SmsBlackMobile> list) throws Exception;

    /**
     * 分页查询
     * @param mobile
     * @return
     * @throws Exception
     */
    JSONObject findByPage(SmsBlackMobile mobile) throws Exception;

    /**
     * 查询单个黑名单信息
     * @param id
     * @return
     * @throws Exception
     */
    SmsBlackMobile findOne(Integer id);

    /**
     * 修改黑名单
     * @param mobile
     * @return
     * @throws Exception
     */
    int update(SmsBlackMobile mobile) throws Exception;

    /**
     * 删除单个黑名单信息
     * @param id
     * @return
     * @throws Exception
     */
    int deleteOne(Integer id) throws Exception;

    /**
     * 查询已存在的系统黑名单
     * @param group_id
     * @param list
     * @return
     */
    List<Long> getExistMobiles(Integer group_id, List<Long> list) throws Exception;
}
