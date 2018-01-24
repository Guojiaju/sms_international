package com.sms.international.admin.utils;

import com.sms.international.admin.mapper.SmsChannelPriceMenuMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author guojiaju
 * Date 2017/12/4
 * Description 定时任务类
 */
@Service("timedTask")
@Transactional
public class TimedTask {
    private Logger log = Logger.getLogger(TimedTask.class);

    @Autowired
    private SmsChannelPriceMenuMapper smsChannelPriceMenuMapper;

    /**
     * @MethodName: updateTempPrice
     * @Description: 更新临时价目
     * @Param: []
     * @Return: void
     * @Author: guojiaju
     * @Date: 2017/12/4
     */
    private void updateTempPrice(){
        log.info("更新临时价目表信息----" + DateUtil.getCureentTime());
        try{
            //先清空临时价目表
            smsChannelPriceMenuMapper.emptyTempPrice();
            //挑选价格最低的通道信息保存
            smsChannelPriceMenuMapper.batchSaveTemp();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
