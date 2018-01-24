package com.sms.international.admin.controller;

import com.sms.international.admin.model.SmsAdminLogs;
import com.sms.international.admin.service.SmsAdminLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author guojiaju
 * Date 2017/12/7
 * Description 操作日志控制层
 */
@RequestMapping("/adminLogs")
@Controller
@Scope("prototype")
public class SmsAdminLogsController extends ParentController{

    @Autowired
    private SmsAdminLogsService smsAdminLogsService;

    /**
     * 初始化管理员日志页面
     * @return
     */
    @RequestMapping("/init")
    public String init(){
        return "logs/list";
    }

    /**
     * 分页查询管理员日志
     * @param logs
     * @return
     */
    @RequestMapping("/findAll")
    @ResponseBody
    public String findAll(@RequestBody SmsAdminLogs logs){
        if(logs.getType() == null){
            logs.setType(1);
        }
        return smsAdminLogsService.findAll(logs).toString();
    }
}
