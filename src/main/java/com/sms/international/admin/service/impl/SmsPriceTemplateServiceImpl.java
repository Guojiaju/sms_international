package com.sms.international.admin.service.impl;

import com.sms.international.admin.mapper.SmsPriceTemplateDetailsMapper;
import com.sms.international.admin.mapper.SmsPriceTemplateMapper;
import com.sms.international.admin.model.SmsPriceTemplate;
import com.sms.international.admin.model.SmsPriceTemplateDetail;
import com.sms.international.admin.service.BaseService;
import com.sms.international.admin.service.SmsPriceTemplateService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author guojiaju
 * Date 2018/1/18
 * Description 价格模板接口实现类
 */
@Service
@Transactional
public class SmsPriceTemplateServiceImpl extends BaseService<SmsPriceTemplate,SmsPriceTemplateMapper> implements SmsPriceTemplateService {

    @Autowired
    private SmsPriceTemplateDetailsMapper detailsMapper;


    /**
     * 分页查询价目模板
     *
     * @param template
     * @return
     */
    @Override
    public JSONObject findByPage(SmsPriceTemplate template) {
        int num = mapper.countTotal(template);
        JSONObject obj = new JSONObject();
        obj.put("code", 0);
        obj.put("msg", 0);
        obj.put("count", num);
        List<SmsPriceTemplate> channels = mapper.findByT(template);
        obj.put("data", JSONArray.fromObject(channels));
        return obj;
    }

    /**
     * 删除模版
     *
     * @param id
     * @return
     */
    @Override
    public int deleteByTempId(Integer id) throws Exception {
        //先删除模板对应的价格信息
        detailsMapper.deleteByTempId(id);
        return mapper.deleteById(id);
    }

    /**
     * 新增模板
     *
     * @param template
     * @return
     */
    @Override
    public int addTemplate(SmsPriceTemplate template) throws Exception {
        return mapper.save(template);
    }

    /**
     * 修改模板信息
     *
     * @param template
     * @return
     */
    @Override
    public int updateTemplate(SmsPriceTemplate template) throws Exception {
        return mapper.update(template);
    }

    /**
     * 批量添加价格信息
     *
     * @param list
     * @return
     */
    @Override
    public int addPriceInfos(Integer temp_id,List<SmsPriceTemplateDetail> list) throws Exception {
        //先删除再保存
        detailsMapper.deleteByTempId(temp_id);
        return detailsMapper.batchSave(list);
    }

    /**
     * 查询单个模板
     *
     * @param id
     * @return
     */
    @Override
    public SmsPriceTemplate getOne(Integer id) {
        return mapper.findOne(id);
    }

    /**
     * 根据模板id查询价格信息
     *
     * @param temp_id
     * @return
     */
    @Override
    public List<SmsPriceTemplateDetail> findDetailByTempId(Integer temp_id) {
        return detailsMapper.findByTempId(temp_id);
    }

    /**
     * 查找所有有效的模板
     *
     * @return
     */
    @Override
    public List<SmsPriceTemplate> findAll() {
        return mapper.findAll();
    }
}
