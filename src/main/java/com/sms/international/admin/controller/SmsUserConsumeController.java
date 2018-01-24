package com.sms.international.admin.controller;

import com.sms.international.admin.model.SmsUserConsume;
import com.sms.international.admin.service.SmsUserConsumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author guojiaju
 * Date 2017/12/25
 * Description 消费记录控制类
 */
@Controller
@Scope("prototype")
@RequestMapping("/consume")
public class SmsUserConsumeController extends ParentController {

    @Autowired
    private SmsUserConsumeService smsUserConsumeService;

    /**
     * 初始化查询记录页面
     * @param id
     * @param modelMap
     * @return
     */
    @RequestMapping("/initConsume/{id}")
    public String initConsume(@PathVariable Integer id , ModelMap modelMap){
        modelMap.put("uid",id);
        return "smsUser/detail/consumeRecords";
    }

    /**
     * 查询用户消费记录
     * @param smsUserConsume
     * @return
     */
    @RequestMapping("/findByPage")
    @ResponseBody
    public String findByPage(@RequestBody SmsUserConsume smsUserConsume){
        return smsUserConsumeService.findByPage(smsUserConsume).toString();
    }

}
