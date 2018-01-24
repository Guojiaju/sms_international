package com.sms.international.admin.service.impl;

import com.sms.international.admin.mapper.SmsChannelMapper;
import com.sms.international.admin.model.SmsChannel;
import com.sms.international.admin.service.SmsChannelService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author guojiaju
 * Date 2017/11/29
 * Description 通道接口实现类
 */
@Service
@Transactional
public class SmsChannelServiceImpl implements SmsChannelService{

    @Autowired
    private SmsChannelMapper smsChannelMapper;
    /**
     * 查询所有的通道信息
     *
     * @return
     */
    @Override
    public List<SmsChannel> getAll() throws Exception {
        return smsChannelMapper.getAll();
    }

    /**
     * 查询所有通道信息
     *
     * @param smsChannel
     * @return
     */
    @Override
    public JSONObject findAll(SmsChannel smsChannel) throws Exception{
        int num = smsChannelMapper.countTotal(smsChannel);
        JSONObject obj = new JSONObject();
        obj.put("code", 0);
        obj.put("msg", 0);
        obj.put("count", num);
        List<SmsChannel> channels = smsChannelMapper.findAll(smsChannel);
        obj.put("data", JSONArray.fromObject(channels));
        return obj;
    }

    /**
     * 新增通道
     *
     * @param smsChannel
     * @return
     */
    @Override
    public int save(SmsChannel smsChannel) throws Exception {
        return smsChannelMapper.save(smsChannel);
    }

    /**
     * 修改通道信息
     *
     * @param smsChannel
     * @return
     */
    @Override
    public int update(SmsChannel smsChannel) throws Exception{
        return smsChannelMapper.update(smsChannel);
    }

    /**
     * 删除通道
     *
     * @param id
     * @return
     */
    @Override
    public int deleteById(Integer id) throws Exception{
        return smsChannelMapper.deleteById(id);
    }

    /**
     * 查询该id的通道
     *
     * @param id
     * @return
     */
    @Override
    public SmsChannel findOne(Integer id) throws Exception{
        return smsChannelMapper.findOne(id);
    }
}
