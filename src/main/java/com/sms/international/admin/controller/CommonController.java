package com.sms.international.admin.controller;

import com.sms.international.admin.model.Admin;
import com.sms.international.admin.model.Menu;
import com.sms.international.admin.service.AdminService;
import com.sms.international.admin.service.MenuService;
import com.sms.international.admin.service.RoleService;
import com.sms.international.admin.utils.Captcha;
import com.sms.international.admin.utils.DateUtil;
import com.sms.international.admin.utils.Md5Util;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 公用
 * 
 * @author CQL 331737188@qq.com
 * @date : 2015年10月9日 下午3:19:54
 *
 */
@Scope("prototype")
@Controller("commonController")
public class CommonController {

	private static Log log = LogFactory.getLog(CommonController.class);

	@Autowired
	@Qualifier("menuService")
	private MenuService menuService;

	@Autowired
	@Qualifier("roleService")
	private RoleService roleService;
	
	@Autowired
	private AdminService adminService;

	@RequestMapping("/home")
	public String list(HttpServletRequest request, ModelMap model) {
		Subject subject = SecurityUtils.getSubject();
		Admin u = (Admin) subject.getPrincipal();
		List<Integer> roles = roleService.findAdminForRole(u.getId());
		Map<String, Object> map = new HashMap<String, Object>();

		if(roles.size() == 0)
			roles.add(0);
		map.put("departid", roles); // 这里的角色ID是一个集合. 需要转成String类型的.
		map.put("grade", 1);
		List<Menu> menulist0 = menuService.findAllByGroupid(map);
		map.put("grade", 2);
		List<Menu> menulist1 = menuService.findAllByGroupid(map);

		Map<Menu, List<Menu>> menuListMap = new LinkedHashMap<Menu, List<Menu>>();
		List<Menu> menu1List = null;
		for (Menu menu0 : menulist0) {
			menu1List = new ArrayList<Menu>();
			for (Menu menu1 : menulist1) {
				if (menu0.getId().intValue() == menu1.getPid().intValue()) {
					menu1List.add(menu1);
				}
			}
			menuListMap.put(menu0, menu1List);
		}
		
		
		
		model.put("admin", u);
		model.put("menuListMap", menuListMap);
		request.getSession().setAttribute("admin_id", u.getId());
		request.getSession().setAttribute("admin_user", u.getUsername());
		request.getSession().setAttribute("admin_name", u.getRoleName());
		request.getSession().setAttribute("auth", 1);
		return "common/index";
	}
	
	@RequestMapping("common/showNotice")
	public String showNotice(HttpServletRequest request, ModelMap model){
		Subject subject = SecurityUtils.getSubject();
		Admin u = (Admin) subject.getPrincipal();
		
		Admin adminUser = adminService.findByUser(u.getId());
		if(StringUtils.isBlank(adminUser.getLogtime())){
			String logTime = DateUtil.getCurrentDateTime();
			Admin updateUser = new Admin();
			updateUser.setId(u.getId());
			updateUser.setLogtime(logTime);
			adminService.updateUser(updateUser, request.getRemoteAddr());
		}
		model.put("logtime", adminUser.getLogtime());
		return "common/index";
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request, ModelMap model) {
		return "common/login";
	}
	
	@RequestMapping("/testFrom")
	public String testFrom(HttpServletRequest request, ModelMap model) {
		return "common/testFrom";
	}
	
	@RequestMapping("/testTable")
	public String testTable(HttpServletRequest request, ModelMap model) {
		return "common/testTable";
	}

	@RequestMapping("/main/top")
	public String top(HttpServletRequest request, ModelMap model) {
		return "common/top";
	}

	@RequestMapping("/main/left")
	public String left(HttpServletRequest request, ModelMap model) {
		try {

			Subject subject = SecurityUtils.getSubject();
			Admin u = (Admin) subject.getPrincipal();
			List<Integer> roles = roleService.findAdminForRole(u.getId());
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("departid", roles); // 这里的角色ID是一个集合. 需要转成String类型的.
			map.put("grade", 1);
			List<Menu> menulist0 = menuService.findAllByGroupid(map);
			map.put("grade", 2);
			List<Menu> menulist1 = menuService.findAllByGroupid(map);

			Map<Menu, List<Menu>> menuListMap = new LinkedHashMap<Menu, List<Menu>>();
			List<Menu> menu1List = null;
			for (Menu menu0 : menulist0) {
				menu1List = new ArrayList<Menu>();
				for (Menu menu1 : menulist1) {
					if (menu0.getId().intValue() == menu1.getPid().intValue()) {
						menu1List.add(menu1);
					}
				}
				menuListMap.put(menu0, menu1List);
			}

			model.put("menuListMap", menuListMap);

		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
		return "common/left";
	}

	@RequestMapping("/captcha")
	@ResponseBody
	public Map<String, Object> captcha(HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) throws IOException {
		response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
		response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);
		Captcha captcha = new Captcha();
		try {
			captcha.buildRandcode(request, response);// 输出图片方法
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/testSend")
	public String testSend(){
		return "common/testSend";
	}
	
	@RequestMapping("/doTestSend")
	@ResponseBody
	public Integer doTestSend(@RequestBody JSONObject obj){
		String sms = "";
		int statusCode;
		try {
			HttpClient httpClient = new HttpClient();
			String timestamp = getDayBefore();
			String url = "http://210.5.158.31:3801/sendsms";
			String from = "18";
			String username = "sioo";
			String pass = "yyzi91";
			String to = obj.getString("mobile");
			String text=java.net.URLEncoder.encode(obj.getString("content"), "utf-8");
			PostMethod postMethod = new PostMethod(url);
			String expend = "18";
			NameValuePair[] data = {
						new NameValuePair("from", from),//用户编号
						new NameValuePair("username", username),//用户账号
						new NameValuePair("timestamp", timestamp),//时间戳(yyyyMMddHHmmss)
						new NameValuePair("auth", Md5Util.getMD5(pass+timestamp)),//签权码(密码+时间戳组成新的字符串然后使用MD5加密32位小写字符串)
						new NameValuePair("to", to),//接收人号码
						new NameValuePair("text",text),//发送内容
						new NameValuePair("expend", expend)//拓展码
						 };
			postMethod.setRequestBody(data);
			statusCode = httpClient.executeMethod(postMethod);
				
			if (statusCode == HttpStatus.SC_OK) {
				sms = postMethod.getResponseBodyAsString();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sms.startsWith("0,")?1:0;
	}
	private String getDayBefore(){ 
		Calendar c = Calendar.getInstance(); 
		String l = c.get(Calendar.YEAR)+"" + ((c.get(Calendar.MONTH)+1)<10?("0"+(c.get(Calendar.MONTH)+1)):(c.get(Calendar.MONTH)+1)) +"" +  (c.get(Calendar.DAY_OF_MONTH)<10?("0"+c.get(Calendar.DAY_OF_MONTH)):c.get(Calendar.DAY_OF_MONTH)) + "" + (c.get(Calendar.HOUR_OF_DAY)<10?("0"+c.get(Calendar.HOUR_OF_DAY)):c.get(Calendar.HOUR_OF_DAY)) +"" +  (c.get(Calendar.MINUTE)<10?("0"+c.get(Calendar.MINUTE)):c.get(Calendar.MINUTE)) +"" + ( c.get(Calendar.SECOND)<10?("0"+ c.get(Calendar.SECOND)): c.get(Calendar.SECOND));
		return l; 
	}
}
