package com.sms.international.admin.controller;

import com.sms.international.admin.model.*;
import com.sms.international.admin.service.*;
import com.sms.international.admin.servlet.ServiceClient;
import com.sms.international.admin.utils.*;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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
import java.util.Date;
import java.util.List;

/**
 * Created by 37985 on 2016/7/1.
 * 账号管理控制层
 */
@Scope("prototype")
@Controller
@RequestMapping(value = "/smsUser")
public class SmsUserController extends ParentController {

	public static final Logger logger = LoggerFactory.getLogger(SmsUserController.class);

	@Autowired
	private SmsUserService smsUserService;

	@Autowired
	private SmsUserPriceMenuService smsUserPriceMenuService;

	@Autowired
	private SmsUserControlService smsUserControlService;

	@Autowired
	private SmsChargeRecordsService smsChargeRecordsService;

	@Autowired
	private ServiceClient serviceClient;

	@Autowired
	private CaiwuAccPassService caiwuAccPassService;

	@Autowired
	private SmsCustomerService smsCustomerService;

	@Autowired
	private SmsSignStoreService smsSignStoreService;

	@Autowired
	private SmsPriceTemplateService smsPriceTemplateService;
	/**
	 * 初始化账号管理页面
	 * @return
	 */
	@RequestMapping(value = "/findAll")
	public String list(ModelMap modelMap) {
        modelMap.put("admin",super.getAdmin());
		return "/smsUser/list";
	}

	/**
	 * 分页查询所有账号信息
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/findData")
	@ResponseBody
	public String findData(@RequestBody SmsUser user) {
        JSONObject obj = smsUserService.findByList(user);
		return obj.toString();
	}

	/**
	 * 跳转到编辑页面，初始化页面信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/toAddOrEdit/{id}/{parentId}")
	public String toAddOrEdit(@PathVariable Integer id , @PathVariable Integer parentId, ModelMap model) {
		SmsUser user = new SmsUser();
		if (id > 0) {
			user = smsUserService.findById(id);
			SmsSignStore signStore = smsSignStoreService.findByUidAndExpend(id);
			if(signStore != null){
				user.setSign(signStore.getStore());
			}
		}else{
			model.put("parent",smsCustomerService.findById(parentId));
		}
		model.put("user", user);
		return "/smsUser/edit";
	}

	/**
	 * 初始化详情tab页
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/initDetail/{id}")
	public String initDetail(@PathVariable Integer id, ModelMap model){
		model.put("id",id);
		return "/smsUser/initDetail";
	}

	/**
	 * 查看账号详情
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Integer id, ModelMap model) {
		SmsUser user = new SmsUser();
		if (id > 0) {
			user = smsUserService.findById(id);
			SmsSignStore signStore = smsSignStoreService.findByUidAndExpend(id);
			if(signStore != null){
				user.setSign(signStore.getStore());
			}
		}
		model.put("user", user);
		return "/smsUser/detail/detail";
	}

	/**
	 * 禁用/开启账号
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping("/enableOrDisable")
	@ResponseBody
	public Integer enableOrDisable(HttpServletRequest request,@RequestBody SmsUser user){
		logger.info(super.getAdmin().getUsername()+"修改账号状态");
		int result;
		try{
			result = smsUserService.addOrEdit(user);
			serviceClient.excuteClient(PubType.USER,PubMethod.UPDATE,"{\"uid\":" + user.getId() +"}");
		}catch (Exception e){
			e.printStackTrace();
			result =0;
		}
		super.adminLogs("修改账号状态"+(result >0 ? "成功":"失败")+"uid:"+user.getId(), GetRemoteIp.getIp(request));
		return result;
	}

	/**
	 * 新增/修改账号信息
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping("/doAddOrEdit")
	@ResponseBody
	public Integer doAddOrEdit(HttpServletRequest request,@RequestBody SmsUser user) {
		logger.info(super.getAdmin().getUsername() + "编辑账号信息");
		Subject subject = SecurityUtils.getSubject();
		Admin admin = (Admin) subject.getPrincipal();
		if(user.getId() == null){
			user.setAddUid(admin.getUsername());
			user.setTime(DateUtil.getFormatTime(new Date()));
		}
		//密码加密
		if(user.getPwd() !=null && user.getPwd().length() >= 6){
			user.setDpwd(user.getPwd());
			user.setPwd(Md5Util.getMD5(user.getPwd()));
		}
		int result = smsUserService.addOrEdit(user);

		try {
			SmsSignStore signStore = smsSignStoreService.findByUidAndExpend(user.getId());
			//如果存在则更新，不存在则新增
			if(signStore != null) {
				signStore.setStore(user.getSign());
				smsSignStoreService.update(signStore);
			}else{
				signStore = new SmsSignStore();
				signStore.setStore(user.getSign());
				signStore.setAddtime(DateUtil.getCurrentTime());
				signStore.setUid(user.getId());
				signStore.setExpend(user.getId()+"");
				signStore.setType(2);
				signStore.setUserstat((byte)1);
				signStore.setStatus(0);
				signStore.setUserexpend(user.getId()+"");
				signStore.setChannel(0);
				smsSignStoreService.add(signStore);
			}
			serviceClient.excuteClient(PubType.USER_SIGN,PubMethod.UPDATE,"{\"uid\":" + user.getId() +"}");
		} catch (Exception e) {
			e.printStackTrace();
		}
		serviceClient.excuteClient(PubType.USER,PubMethod.UPDATE,"{\"uid\":" + user.getId() +"}");

		super.adminLogs("编辑账号"+(result >0 ? "成功,":"失败,")+ user.toString(), GetRemoteIp.getIp(request));
		return result;
	}


	/**
	 * 初始化用户控制页面
	 * @return
	 */
	@RequestMapping("/initControl/{uid}")
	public String initControl(@PathVariable Integer uid,ModelMap modelMap){
		modelMap.put("control",smsUserControlService.findByUid(uid));
		modelMap.put("channel",GlobalParams.CHANNEL_MAP.get(GlobalParams.CHANNEL_KEY));
		modelMap.put("uid",uid);
		return "/smsUser/control";
	}

	/**
	 * 新增或者保存用户控制信息
	 * @param control
	 * @return
	 */
	@RequestMapping("/saveOrUpdateControl")
	@ResponseBody
	public Integer saveOrUpdateControl(HttpServletRequest request,@RequestBody SmsUserControl control){
		logger.info(super.getAdmin().getUsername()+"新增或者保存用户控制信息");
		int result;
		try{
			SmsUserControl temp = smsUserControlService.findByUid(control.getUid());
			if(temp !=null){
				result = smsUserControlService.update(control);
			}else{
				result = smsUserControlService.save(control);
			}
			serviceClient.excuteClient(PubType.USER,PubMethod.UPDATE,"{\"uid\":" + control.getUid() +"}");
		}catch (Exception e){
			e.printStackTrace();
			result = 0;
		}
		super.adminLogs("新增／保存用户控制信息"+(result > 0 ? "成功,":"失败,") + control.toString(),GetRemoteIp.getIp(request));
		return result;
	}

	/**
	 * 初始化资源配置页面
	 * @return
	 */
	@RequestMapping("/initResources/{uid}")
	public String initResources(@PathVariable Integer uid,ModelMap modelMap){
		modelMap.put("userPriceMenu",smsUserPriceMenuService.findByUid(uid));
		modelMap.put("country", GlobalParams.COUNTRY_MAP.get(GlobalParams.COUNTRY_KEY));
		modelMap.put("uid",uid);
		modelMap.put("templates",smsPriceTemplateService.findAll());
		return "/smsUser/resources";
	}

	/**
	 * 重新加载资源配置页面
	 * @param uid
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/reloadResources/{uid}/{temp_id}")
	public String reloadResources(@PathVariable Integer uid,@PathVariable Integer temp_id, ModelMap modelMap){
		List<SmsUserPriceMenu> menus = smsUserPriceMenuService.findByUid(uid);
		List<SmsPriceTemplateDetail> details = smsPriceTemplateService.findDetailByTempId(temp_id);
		if(details != null && details.size()>0) {
			for (SmsPriceTemplateDetail detail : details) {
				SmsUserPriceMenu temp  = new SmsUserPriceMenu();
				temp.setCountry(detail.getCountry());
				temp.setCountryId(detail.getCountryId());
				temp.setPrice(detail.getPrice());
				menus.add(temp);
			}
		}
		modelMap.put("userPriceMenu",menus);
		modelMap.put("country", GlobalParams.COUNTRY_MAP.get(GlobalParams.COUNTRY_KEY));
		modelMap.put("uid",uid);
		modelMap.put("templates",smsPriceTemplateService.findAll());
		return "/smsUser/resources";
	}


    /**
     * 保存用户价目信息
     * @param request
     * @param uid
     * @param menus
     * @return
     */
	@RequestMapping("/saveResources/{uid}")
	@ResponseBody
	public Integer saveResources(HttpServletRequest request,@PathVariable Integer uid , @RequestBody List<SmsUserPriceMenu> menus){
		logger.info(super.getAdmin().getUsername()+"修改uid:"+uid+"的价目表");
		//先删除该uid下所有的价目信息，再保存一次
		int result ;
		result = smsUserPriceMenuService.deleteByUid(uid);
		if(menus == null || menus.size()<=0){
			return result;
		}else{
			result = smsUserPriceMenuService.batchSave(menus);
		}
		serviceClient.excuteClient(PubType.USER_PRICE,PubMethod.RELOAD,"{\"uid\":" + uid +"}");
		super.adminLogs("修改uid:"+uid+"的价目表。"+(result >0 ? "成功":"失败"), GetRemoteIp.getIp(request));
		return result;
	}


	/**
	 * 初始化充值页面
	 * @return
	 */
	@RequestMapping("/initCharge/{uid}")
	public String initCharge(@PathVariable Integer uid,ModelMap modelMap){
		SmsUser user = smsUserService.findById(uid);
		modelMap.put("user",user);
		return "/smsUser/charge";
	}

	/**
	 * 充值
	 * @param request
	 * @param records
	 * @return
	 */
	@RequestMapping("/doCharge")
	@ResponseBody
	public Integer doCharge(HttpServletRequest request , @RequestBody SmsChargeRecords records){
		logger.info(super.getAdmin().getUsername()+"给uid:"+records.getUid()+"充值");
		Admin admin = super.getAdmin();
		int result ;
		if(records.getPassword() != null && records.getPassword() != ""){
			if(!caiwuAccPassService.findPass().equalsIgnoreCase(Md5Util.getMD5(records.getPassword()))){
				super.adminLogs("充值失败，密码错误",GetRemoteIp.getIp(request));
				result = -1;
				return result;
			}
		}
        try {
            //修改用户余额
            result = smsUserService.updateUserCharge(records.getUid(),records.getAmount());
            //保存充值记录
            records.setAdd_uid(admin.getRealname());
            records.setCreate_time(DateUtil.getCurrentTime());
            smsChargeRecordsService.save(records);

            serviceClient.excuteClient(PubType.USER,PubMethod.UPDATE,"{\"uid\":" + records.getUid() +"}");
            serviceClient.excuteClient(PubType.USER_SMS, PubMethod.UPDATE, "{\"uid\":" + records.getUid() + ",\"sms\":" +records.getAmount()+"}");
            serviceClient.excuteClient(PubType.USER_ALERT, PubMethod.UPDATE, "{\"uid\":" + records.getUid() + "}");
        } catch (Exception e) {
            e.printStackTrace();
            result =0;
        }
        //先更新用户余额
		return result;
	}

}
