package com.sms.international.admin.controller;

import com.sms.international.admin.service.SmsBlackMobileTypeService;
import com.sms.international.admin.utils.GlobalParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author guojiaju
 * Date 2017/12/8
 * Description 系统策略控制类
 */
@Controller
@RequestMapping("sysStrategy")
@Scope("prototype")
public class SmsSysStrategyController extends ParentController{

    private static final Logger log = LoggerFactory.getLogger(SmsSysStrategyController.class);


    @Autowired
    private SmsBlackMobileTypeService smsBlackMobileTypeService;

    /**
     * 初始化
     * @return
     */
    @RequestMapping("/init")
    public String init(ModelMap modelMap){
        try {
            modelMap.put("country",GlobalParams.COUNTRY_MAP.get(GlobalParams.COUNTRY_KEY));
            modelMap.put("mobileType",smsBlackMobileTypeService.getAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "sysStrategy/init";
    }
}
