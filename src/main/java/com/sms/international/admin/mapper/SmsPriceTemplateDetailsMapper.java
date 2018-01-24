package com.sms.international.admin.mapper;

import com.sms.international.admin.model.SmsPriceTemplateDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author guojiaju
 * Date 2018/1/18
 * Description 价目模板对应的配置
 */
@Repository
public interface SmsPriceTemplateDetailsMapper {

    /**
     * 查询用户下的所有价目信息
     * @param temp_id
     * @return
     */
    List<SmsPriceTemplateDetail> findByTempId(Integer temp_id);

    /**
     * 批量新增用户价目信息
     * @param list
     * @return
     */
    int batchSave(@Param("list") List<SmsPriceTemplateDetail> list);

    /**
     * 删除用户价目信息
     * @param temp_id
     * @return
     */
    int deleteByTempId(Integer temp_id);
}
