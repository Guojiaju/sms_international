package com.sms.international.admin.mapper;

import com.sms.international.admin.model.SmsBlackWords;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author guojiaju
 * Date 2017/12/19
 * Description 系统屏蔽词sql映射
 */
@Repository
public interface SmsBlackWordsMapper extends BaseMapper<Integer,SmsBlackWords> {

    /**
     * 批量保存
     * @param list
     * @return
     */
    int batchSave(@Param("list") List<SmsBlackWords> list);

    /**
     * 查询已存在的屏蔽词
     * @param group_id
     * @param list
     * @return
     */
    List<String> getExistWords(@Param("group_id") Integer group_id, @Param("list") List<String> list);

}
