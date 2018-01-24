package com.sms.international.admin.mapper;

import com.sms.international.admin.model.SmsChannel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author guojiaju
 * Date 2017/11/29
 * Description 通道映射接口
 */
@Repository
public interface SmsChannelMapper {

    /**
     * 获取所有通道信息
     * @return
     */
    List<SmsChannel> getAll();

    /**
     * 查询所有通道信息
     * @param smsChannel
     * @return
     */
    List<SmsChannel> findAll(SmsChannel smsChannel);

    /**
     * 查询单个通道
     * @param id
     * @return
     */
    SmsChannel findOne(Integer id);

    /**
     * 新增通道信息
     * @param smsChannel
     * @return
     */
    int save(SmsChannel smsChannel);

    /**
     * 修改通道信息
     * @param smsChannel
     * @return
     */
    int update(SmsChannel smsChannel);

    /**
     * 删除单个通道
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 统计通道数量
     * @param smsChannel
     * @return
     */
    int countTotal(SmsChannel smsChannel);
}
