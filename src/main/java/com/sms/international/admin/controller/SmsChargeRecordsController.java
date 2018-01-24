package com.sms.international.admin.controller;

import com.sms.international.admin.model.SmsChargeRecords;
import com.sms.international.admin.service.SmsChargeRecordsService;
import com.sms.international.admin.utils.GetRemoteIp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Author guojiaju
 * Date 2017/12/5
 * Description
 */
@RequestMapping("/chargeRecords")
@Controller
@Scope("prototype")
public class SmsChargeRecordsController extends ParentController{

    public static final Logger logger = LoggerFactory.getLogger(SmsChargeRecordsController.class);

    @Autowired
    private SmsChargeRecordsService smsChargeRecordsService;

    /**
     * 初始化充值记录查询页面
     * @return
     */
    @RequestMapping("/init")
    public String initRecords(){
        return "/chargeRecords/list";
    }

    /**
     * 分页查询充值记录
     * @param records
     * @return
     */
    @RequestMapping("/findAll")
    @ResponseBody
    public String findAll(@RequestBody SmsChargeRecords records){
        return smsChargeRecordsService.findByPage(records).toString();
    }

    /**
     * 初始化充值记录查询页面
     * @return
     */
    @RequestMapping("/initRecords/{uid}")
    public String initRecordsByUid(@PathVariable Integer uid , ModelMap modelMap){
        modelMap.put("uid",uid);
        return "/smsUser/detail/chargeRecords";
    }

    /**
     * 分页查询充值记录
     * @param uid
     * @return
     */
    @RequestMapping("/findAllByUid/{uid}")
    @ResponseBody
    public String findAllByUid(@PathVariable  Integer uid){
        SmsChargeRecords records = new SmsChargeRecords();
        records.setUid(uid);
        return smsChargeRecordsService.findByPage(records).toString();
    }

    /**
     * 修改到账状态
     * @param records
     * @return
     */
    @RequestMapping("/updatePayStat")
    @ResponseBody
    public Integer updatePayStat(HttpServletRequest request, @RequestBody SmsChargeRecords records){
        int result ;
        try {
           result = smsChargeRecordsService.updatePayStat(records);
        } catch (Exception e) {
            e.printStackTrace();
            result =0;
        }
        super.adminLogs("修改id:"+records.getId()+"的到账状态"+ (result > 0 ? "成功;":"失败;") + records.toString(),GetRemoteIp.getIp(request));
        return result;
    }
}
