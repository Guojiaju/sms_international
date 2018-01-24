package com.sms.international.admin.service.impl;

import com.sms.international.admin.mapper.SmsChannelPriceMenuMapper;
import com.sms.international.admin.model.SmsChannelPriceMenu;
import com.sms.international.admin.service.SmsChannelPriceMenuService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author guojiaju
 * Date 2017/11/28
 * Description 通道价目接口实现类
 */
@Service
@Transactional
public class SmsChannelPriceMenuServiceImpl implements SmsChannelPriceMenuService{

    @Autowired
    private SmsChannelPriceMenuMapper smsChannelPriceMenuMapper;

    /**
     * 通道价目分页信息
     *
     * @param smsChannelPriceMenu
     * @return
     */
    @Override
    public JSONObject findByPage(SmsChannelPriceMenu smsChannelPriceMenu) throws Exception  {
        int num = smsChannelPriceMenuMapper.countTotal(smsChannelPriceMenu);
        JSONObject obj = new JSONObject();
        obj.put("code", 0);
        obj.put("msg", 0);
        obj.put("count", num);
        List<SmsChannelPriceMenu> users = smsChannelPriceMenuMapper.findByT(smsChannelPriceMenu);
        obj.put("data", JSONArray.fromObject(users));
        return obj;
    }

    /**
     * 根据通道id查询该通道价目信息
     * @param channelId
     * @return
     * @throws Exception
     */
    @Override
    public List<SmsChannelPriceMenu> findByChannelId(Integer channelId) throws Exception {
        return smsChannelPriceMenuMapper.findByChannelId(channelId);
    }

    /**
     * 新增通道价目信息
     *
     * @param smsChannelPriceMenu
     * @return
     */
    @Override
    public int save(SmsChannelPriceMenu smsChannelPriceMenu) throws Exception  {
        return smsChannelPriceMenuMapper.save(smsChannelPriceMenu);
    }

    /**
     * 修改通道价目信息
     *
     * @param smsChannelPriceMenu
     * @return
     */
    @Override
    public int update(SmsChannelPriceMenu smsChannelPriceMenu) throws Exception {
        return smsChannelPriceMenuMapper.update(smsChannelPriceMenu);
    }

    /**
     * 删除通道价目
     *
     * @param id
     * @return
     */
    @Override
    public int deleteById(Integer id) throws Exception  {
        return smsChannelPriceMenuMapper.deleteById(id);
    }

    /**
     * 根据通道id删除对应的价目信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public int deleteByChannelId(Integer id) throws Exception {
        return smsChannelPriceMenuMapper.deleteByChannelId(id);
    }

    /**
     * 批量保存通道价目信息
     *
     * @param list
     * @return
     * @throws Exception
     */
    @Override
    public int batchSave(List<SmsChannelPriceMenu> list) throws Exception {
        return smsChannelPriceMenuMapper.batchSave(list);
    }
}
