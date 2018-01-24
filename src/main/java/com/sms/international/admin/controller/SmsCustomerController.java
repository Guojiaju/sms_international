package com.sms.international.admin.controller;

import com.sms.international.admin.model.SmsCustomer;
import com.sms.international.admin.service.SmsCustomerService;
import com.sms.international.admin.service.SmsUserService;
import com.sms.international.admin.utils.GetRemoteIp;
import net.sf.json.JSONObject;
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
 * Created by 37985 on 2016/7/1.
 * 客户管理控制层
 */
@Scope("prototype")
@Controller
@RequestMapping(value = "/smsCustomer")
public class SmsCustomerController extends ParentController{

	public static final Logger logger = LoggerFactory.getLogger(SmsCustomerController.class);
	@Autowired
	private SmsCustomerService smsCustomerService;

	@Autowired
	private SmsUserService smsUserService;

	/**
	 * 初始化客户列表页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/init")
	public String initCustomer(HttpServletRequest request, ModelMap model) {
		return "/smsCustomer/list";
	}

	/**
	 * 分页查询所有的客户信息
	 * @param smsCustomer
	 * @return
	 */
	@RequestMapping(value = "/findAll")
	@ResponseBody
	public String findAll(@RequestBody SmsCustomer smsCustomer) {
		logger.info("查询客户信息");
		return smsCustomerService.findByList(smsCustomer).toString();
	}

	/**
	 * 跳转到编辑页面，初始化页面数据
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/toAddOrEdit/{id}")
	public String toAddOrEdit(@PathVariable Integer id,ModelMap model) {
		SmsCustomer customer = new SmsCustomer();
		if (id > 0) {
			customer = smsCustomerService.findById(id);
		}
		model.put("customer", customer);
		return "/smsCustomer/edit";
	}

	/**
	 * 新增/修改客户信息
	 * @param request
	 * @param smsCustomer
	 * @return
	 */
	@RequestMapping("/addOrEdit")
	@ResponseBody
	public Integer addOrEdit(HttpServletRequest request ,@RequestBody SmsCustomer smsCustomer) {
		logger.info(super.getAdmin().getUsername()+"编辑客户信息");
		int result = smsCustomerService.addOrEdit(smsCustomer);
		super.adminLogs("编辑客户信息"+(result > 0 ? "成功,":"失败,") + JSONObject.fromObject(smsCustomer), GetRemoteIp.getIp(request));
		return result;
	}

	/**
	 * 禁用/开启客户账号，并禁用/开启该客户下所有的账号
	 * @param request
	 * @param customer
	 * @return
	 */
	@RequestMapping("/enableOrDisable")
	@ResponseBody
	public Integer enableOrDisable(HttpServletRequest request,@RequestBody SmsCustomer customer){
		logger.info(super.getAdmin().getUsername()+"修改客户账号状态");
		int result ;
		try{
			//先禁用改客户下的所有账号
			smsUserService.disableOrEndableByParentId(customer.getId(),customer.getStat());
			//然后在禁用该客户
			result = smsCustomerService.addOrEdit(customer);
		}catch (Exception e){
			e.printStackTrace();
			result = 0;
		}
		super.adminLogs("修改客户账号id："+customer.getId()+"状态"+(result >0 ? "成功":"失败"),GetRemoteIp.getIp(request));
		return result;
	}
}
