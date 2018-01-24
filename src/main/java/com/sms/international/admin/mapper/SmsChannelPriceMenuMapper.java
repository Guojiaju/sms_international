package com.sms.international.admin.mapper;

import com.sms.international.admin.model.SmsChannelPriceMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author guojiaju
 * Date 2017/11/27
 * Description 通道价目表映射接口
 */
@Repository
public interface SmsChannelPriceMenuMapper{

    /**
     * 查询分页数据
     * @param smsChannelPriceMenus
     * @return
     */
    List<SmsChannelPriceMenu> findByT(SmsChannelPriceMenu smsChannelPriceMenus);

    /**
     * 查询id通道的价目信息
     * @param id
     * @return
     */
    List<SmsChannelPriceMenu> findByChannelId(Integer id);

    /**
     * 根据条件统计通道价目数量
     * @param smsChannelPriceMenu
     * @return
     */
    int countTotal(SmsChannelPriceMenu smsChannelPriceMenu);

    /**
     * 新增通道价目信息
     * @param smsChannelPriceMenu
     * @return
     */
    int save(SmsChannelPriceMenu smsChannelPriceMenu);

    /**
     * 修改通道价目信息
     * @param smsChannelPriceMenu
     * @return
     */
    int update(SmsChannelPriceMenu smsChannelPriceMenu);

    /**
     * 删除单个通道价目信息
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 根据通道删除对应的价目信息
     * @param id
     * @return
     */
    int deleteByChannelId(Integer id);

    /**
     * 批量保存通道价目
     * @param list
     * @return
     */
    int batchSave(@Param("list") List<SmsChannelPriceMenu> list);

    /**
     * 清空临时价目表
     */
    void emptyTempPrice();

    /**
     * 批量保存临时价目信息
     * @return
     */
    int batchSaveTemp();
}
