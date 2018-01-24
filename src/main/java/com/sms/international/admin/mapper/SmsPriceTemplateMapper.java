package com.sms.international.admin.mapper;

import com.sms.international.admin.model.SmsPriceTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author guojiaju
 * Date 2018/1/17
 * Description 价目模板
 */
@Repository
public interface SmsPriceTemplateMapper extends BaseMapper<Integer,SmsPriceTemplate> {
    /**
     * 查找所有有效的模板
     * @return
     */
    List<SmsPriceTemplate> findAll();
}
