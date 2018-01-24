package com.sms.international.admin.service;

import com.sms.international.admin.model.SmsBlackWords;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * Author guojiaju
 * Date 2017/12/19
 * Description 系统屏蔽词接口
 */
public interface SmsBlackWordsService {

    /**
     * 分页查询
     * @param smsBlackWords
     * @return
     */
    JSONObject findByPage(SmsBlackWords smsBlackWords);

    /**
     * 修改屏蔽词
     * @param smsBlackWords
     * @return
     */
    int update(SmsBlackWords smsBlackWords) throws Exception;

    /**
     * 根据id删除屏蔽词
     * @param id
     * @return
     * @throws Exception
     */
    int removeById(Integer id) throws Exception;

    /**
     * 批量保存屏蔽词
     * @param words
     * @return
     * @throws Exception
     */
    int batchSave(List<SmsBlackWords> words) throws Exception;

    /**
     * 查询已存在的屏蔽词
     * @param group_id
     * @param list
     * @return
     */
    List<String> getExistWords(Integer group_id, List<String> list) throws Exception;

    /**
     * 查询单个
     * @return
     */
    SmsBlackWords findOne(Integer id);
}
