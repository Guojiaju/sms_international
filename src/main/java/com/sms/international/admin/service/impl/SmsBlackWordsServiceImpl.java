package com.sms.international.admin.service.impl;

import com.sms.international.admin.mapper.SmsBlackWordsMapper;
import com.sms.international.admin.model.SmsBlackWords;
import com.sms.international.admin.service.BaseService;
import com.sms.international.admin.service.SmsBlackWordsService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author guojiaju
 * Date 2017/12/19
 * Description 系统屏蔽词接口实现类
 */
@Service
@Transactional
public class SmsBlackWordsServiceImpl extends BaseService<SmsBlackWords,SmsBlackWordsMapper> implements SmsBlackWordsService {
    /**
     * 分页查询
     *
     * @param smsBlackWords
     * @return
     */
    @Override
    public JSONObject findByPage(SmsBlackWords smsBlackWords) {
        int num = mapper.countTotal(smsBlackWords);
        JSONObject obj = new JSONObject();
        obj.put("code", 0);
        obj.put("msg", 0);
        obj.put("count", num);
        List<SmsBlackWords> words = mapper.findByT(smsBlackWords);
        obj.put("data", JSONArray.fromObject(words));
        return obj;
    }

    /**
     * 修改屏蔽词
     *
     * @param smsBlackWords
     * @return
     */
    @Override
    public int update(SmsBlackWords smsBlackWords) throws Exception {
        return mapper.update(smsBlackWords);
    }

    /**
     * 根据id删除屏蔽词
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public int removeById(Integer id) throws Exception {
        return mapper.deleteById(id);
    }

    /**
     * 批量保存屏蔽词
     *
     * @param words
     * @return
     * @throws Exception
     */
    @Override
    public int batchSave(List<SmsBlackWords> words) throws Exception {
        return mapper.batchSave(words);
    }

    /**
     * 查询已存在的屏蔽词
     *
     * @param group_id
     * @param list
     * @return
     */
    @Override
    public List<String> getExistWords(Integer group_id, List<String> list) throws Exception {
        return mapper.getExistWords(group_id,list);
    }
}
