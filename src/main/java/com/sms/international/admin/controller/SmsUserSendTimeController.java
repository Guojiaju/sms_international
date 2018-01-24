package com.sms.international.admin.controller;


import com.sms.international.admin.model.SmsUserSendTimeing;
import com.sms.international.admin.service.SmsUserSendTimeingService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Scope("prototype")
@Controller("smsUserSendTimeController")
@RequestMapping(value = "/timeing")
public class SmsUserSendTimeController extends ParentController{

	private static Log log = LogFactory.getLog(SmsUserSendTimeController.class);

	@Autowired
	private SmsUserSendTimeingService smsUserSendTimeingService;

	@RequestMapping("/initFind")
	public String initFind(HttpServletRequest request, ModelMap model) {
		return "timeing/list";
	}

	@RequestMapping("/findAll")
    @ResponseBody
	public String findAll(HttpServletRequest request, @RequestBody SmsUserSendTimeing t) {
		return smsUserSendTimeingService.findPageList(t).toString();
	}

	@RequestMapping("/initviewMobile")
	public String initviewMobile(HttpServletRequest request, @ModelAttribute SmsUserSendTimeing t, ModelMap model) {
	    model.put("obj", t);
		return "timeing/viewMobile";
	}


	@RequestMapping("/viewMobile")
    @ResponseBody
	public String viewMobile(@RequestBody SmsUserSendTimeing t, HttpServletRequest request){
		return smsUserSendTimeingService.viewMobile(t).toString();
	}

	@RequestMapping("/clearTimeing")
	@ResponseBody
	public String clearTimeing(@RequestParam Integer pid, HttpServletRequest request){
		int result = smsUserSendTimeingService.clearByPid(pid);
		if(result > 0){
			return "取消成功";
		}
		return "操作失败";
	}
}
