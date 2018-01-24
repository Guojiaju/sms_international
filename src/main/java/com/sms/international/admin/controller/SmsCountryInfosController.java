package com.sms.international.admin.controller;

import com.sms.international.admin.model.SmsCountryInfos;
import com.sms.international.admin.service.SmsCountryInfosService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author guojiaju
 * Date 2017/11/28
 * Description 国家信息控制类
 */
@Controller
@RequestMapping("/country")
@Scope("prototype")
public class SmsCountryInfosController extends ParentController{

    @Autowired
    private SmsCountryInfosService smsCountryInfosService;

    /**
     * 初始化国家信息页面
     * @return
     */
    @RequestMapping("/init")
    public String init(){
        return "/country/list";
    }

    /**
     * 分页查询国家信息
     * @param smsCountryInfos
     * @return
     */
    @RequestMapping("/findAll")
    @ResponseBody
    public String findAll(@RequestBody SmsCountryInfos smsCountryInfos){
        return smsCountryInfosService.findByPage(smsCountryInfos).toString();
    }

    /**
     * 查询单个国家信息
     * @param smsCountryInfos
     * @return
     */
    @RequestMapping("/findOne")
    @ResponseBody
    public JSONObject findOne(@RequestBody SmsCountryInfos smsCountryInfos){
        return JSONObject.fromObject(smsCountryInfosService.findOne(smsCountryInfos));
    }
}
