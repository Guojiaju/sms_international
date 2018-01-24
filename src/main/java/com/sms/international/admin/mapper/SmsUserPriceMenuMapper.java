package com.sms.international.admin.mapper;

import com.sms.international.admin.model.SmsUserPriceMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author guojiaju
 * Date 2017/11/27
 * Description 用户价目表映射接口
 */
@Repository
public interface SmsUserPriceMenuMapper{

    /**
     * 查询用户下的所有价目信息
     * @param uid
     * @return
     */
    List<SmsUserPriceMenu> findByUid(Integer uid);

    /**
     * 批量新增用户价目信息
     * @param list
     * @return
     */
    int batchSave(@Param("list") List<SmsUserPriceMenu> list);

    /**
     * 修改用户价目信息
     * @param smsUserPriceMenu
     * @return
     */
    int update(SmsUserPriceMenu smsUserPriceMenu);

    /**
     * 删除用户价目信息
     * @param uid
     * @return
     */
    int deleteByUid(Integer uid);

}
