package com.sms.international.admin.service;

import com.sms.international.admin.model.SmsPriceTemplate;
import com.sms.international.admin.model.SmsPriceTemplateDetail;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * Author guojiaju
 * Date 2018/1/18
 * Description 价格模板接口
 */
public interface SmsPriceTemplateService {

    /**
     * 分页查询价目模板
     * @param template
     * @return
     */
    JSONObject findByPage(SmsPriceTemplate template);

    /**
     * 删除模版
     * @param id
     * @return
     */
    int deleteByTempId(Integer id) throws Exception;

    /**
     * 新增模板
     * @param template
     * @return
     */
    int addTemplate(SmsPriceTemplate template) throws Exception;

    /**
     * 修改模板信息
     * @param template
     * @return
     */
    int updateTemplate(SmsPriceTemplate template) throws Exception;

    /**
     * 批量添加价格信息
     * @param list
     * @return
     */
    int addPriceInfos(Integer temp_id, List<SmsPriceTemplateDetail> list) throws Exception;

    /**
     * 查询单个模板
     * @param id
     * @return
     */
    SmsPriceTemplate getOne(Integer id);

    /**
     * 根据模板id查询价格信息
     * @param temp_id
     * @return
     */
    List<SmsPriceTemplateDetail> findDetailByTempId(Integer temp_id);

    /**
     * 查找所有有效的模板
     * @return
     */
    List<SmsPriceTemplate> findAll();
}
