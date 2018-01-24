package com.sms.international.admin.controller;

import com.sms.international.admin.model.Admin;
import com.sms.international.admin.model.Role;
import com.sms.international.admin.service.AdminService;
import com.sms.international.admin.service.RoleService;
import com.sms.international.admin.utils.Captcha;
import com.sms.international.admin.utils.DateUtil;
import com.sms.international.admin.utils.GetRemoteIp;
import com.sms.international.admin.utils.Md5Util;
import net.sf.json.JSONObject;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台用户
 *
 * @author CQL 331737188@qq.com
 * @date : 2015年10月12日 上午9:58:52
 *
 */
@Scope("prototype")
@Controller("adminController")
@RequestMapping(value = "/admin")
public class AdminController extends ParentController{

    private static Log log = LogFactory.getLog(Admin.class);

    public static final String PHONERANDOMVALIDATECODEKEY = "PHONERANDOMVALIDATECODEKEY";// 放到session中的key

    @Autowired 
    @Qualifier("roleService")
    private RoleService roleService;

    @Autowired
    private AdminService service;
    @RequestMapping("/loginVerify")
    @ResponseBody
    public Map<String, Object> loginVerify(HttpServletRequest request, @ModelAttribute Admin user, ModelMap model) {
        try {
            if (!StringUtils.isNotEmpty(user.getUsername()) || !StringUtils.isNotEmpty(user.getPassword())) {
                resultMap.put("info", "用户名或密码为空！");
                resultMap.put("status", "n");
                return resultMap;
            }
            String captcha = request.getParameter("captcha");
            if (!StringUtils.isNotEmpty(captcha)) {
                resultMap.put("info", "验证码为空！");
                resultMap.put("status", "n");
                return resultMap;
            }
            String sessionCaptcha = (String) request.getSession().getAttribute(Captcha.RANDOMCODEKEY);

            if (null != sessionCaptcha && sessionCaptcha.equals(captcha.toUpperCase())) {

                user.setPassword(Md5Util.getMD5(user.getPassword()));
                Admin u = service.findUser(user);
                if (u != null) {
                    resultMap.put("info", "验证成功！");
                    resultMap.put("status", "y");
                } else {
                    resultMap.put("info", "用户名或密码错误！");
                    resultMap.put("status", "n");
                }
            } else {
                resultMap.put("info", "验证码错误！");
                resultMap.put("status", "n");
            }

            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(this, e);
            return null;
        }
    }

    /**
     * @Description: 用户登录
     * @param request
     * @param user
     * @param model
     * @return
     */
    @RequestMapping("/doLogin")
    @ResponseBody
    public Map<String, Object> doLogin(HttpServletRequest request, HttpServletResponse response, @ModelAttribute Admin user, ModelMap model) {
        try {
        	log.info("管理员登陆:"+user.getUsername()+",IP:"+request.getRemoteAddr());
            if (!StringUtils.isNotEmpty(user.getUsername()) || !StringUtils.isNotEmpty(user.getPassword())) {
                resultMap.put("info", "用户名或密码为空！");
                resultMap.put("status", "n");
                return resultMap;
            }
            String captcha = request.getParameter("captcha");
            if (!StringUtils.isNotEmpty(captcha)) {
                resultMap.put("info", "验证码为空！");
                resultMap.put("status", "n");
                return resultMap;
            }
            String sessionCaptcha = (String) request.getSession().getAttribute(Captcha.RANDOMCODEKEY);

            if (null != sessionCaptcha && sessionCaptcha.equals(captcha.toUpperCase())) {
                boolean boo = service.login(user, request.getRemoteAddr());

                if(boo){
                    resultMap.put("status", "y");
                }else{
                    resultMap.put("info", "用户名或密码错误！");
                    resultMap.put("status", "n");
                }
            } else {
                resultMap.put("info", "验证码错误！");
                resultMap.put("status", "n");
            }
            return resultMap;
        } catch (Exception e) {
            log.error(this, e);
            resultMap.put("info", "登录失败，系统错误！");
            resultMap.put("status", "n");
            return resultMap;
        }
    }

    @RequestMapping("/home")
    public String main(HttpServletRequest request, @ModelAttribute Admin member, ModelMap model) {
        return "/common/home";
    }

    /**
     * 初始化页面
     * @param model
     * @return
     */
    @RequestMapping("/findAll")
    public String findAll( ModelMap model) {
        return "/admin/list";
    }

    /**
     * 获取数据
     * @param admin
     * @return
     */
    @RequestMapping("/findData")
    @ResponseBody
    public String findData(@RequestBody Admin admin) {
		return service.findByList(admin).toString();
    }

    /**
     * 忘记密码
     */
    @RequestMapping("/forgetPass")
    public String forgetPass(HttpServletRequest request, @ModelAttribute Admin member, ModelMap model) {
        return "/admin/forgetPass";
    }

    /**
     * 找回密码
     */
    @RequestMapping("/getPassword")
    public String getPassword(HttpServletRequest request, String phone, String code, ModelMap model) {
        Integer sessionPhoneCode = (Integer) request.getSession().getAttribute(PHONERANDOMVALIDATECODEKEY);
        if (sessionPhoneCode.toString().equals(code)) {
            // 短信验证成功
            Admin user = new Admin();
            user.setPhone(phone);
            Admin u = service.findUser(user);
            model.put("user", u);
        } else {
            return null;
        }
        return "/admin/setPass";
    }

    @RequestMapping("/toEditOrAdd/{id}")
    public String toEditOrAdd(HttpServletRequest request, @PathVariable Integer id, ModelMap model) {
        try {
            Admin user = service.findByUser(id);
            model.put("user", user);
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("uid", id);
            List<Role> roles = roleService.findAllAndChecked(map);
            model.put("roles", roles);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/admin/edit2";
    }

    @RequestMapping("/doEditOrAdd")
    @ResponseBody
    public Integer doEditOrAdd(HttpServletRequest request, @RequestBody Admin user) {
        int result;
        try {
            boolean flag = (null == user.getId() || "".equals(user.getId()));
            if (flag) {
                user.setCreateDate(DateUtil.getCurrentDateTime());
                result = service.addUser(user, request.getRemoteAddr());
                super.adminLogs("新增用户信息"+(result > 0 ? "成功,":"失败,") + user.toString(), GetRemoteIp.getIp(request));
            } else {
                if (StringUtils.isNotBlank(user.getPassword())) {
                    user.setPassword(Md5Util.getMD5(user.getPassword()));
                }
                result = service.updateUser(user, request.getRemoteAddr());
                super.adminLogs("修改用户信息"+(result > 0 ? "成功,":"失败,") + user.toString(), GetRemoteIp.getIp(request));
            }
            if (StringUtils.isNotBlank(user.getRolesId())) {
                String[] roleIds = user.getRolesId().split(",");
                for (String idstr : roleIds) {
                    if (StringUtils.isBlank(idstr)) {
                        continue;
                    }
                }
            }
        } catch (Exception e) {
            log.error(this, e);
            result =0;
        }
        return result;
    }

    /**
     * 禁用管理员账号
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/doDisable/{id}")
    @ResponseBody
    public Integer doDisable(HttpServletRequest request, @PathVariable Integer id) {
        int result ;
        try {
            result = service.disableAccount(id, request.getRemoteAddr());
        }catch (Exception e){
            e.printStackTrace();
            result = 0;
        }
        super.adminLogs("禁用管理员账号Id:"+ id + (result >0 ? "成功" : "失败"),GetRemoteIp.getIp(request));
        return result ;
    }

    /**
     * 启用管理员账号
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/doEnable/{id}")
    @ResponseBody
    public Integer doEnable(HttpServletRequest request, @PathVariable Integer id) {
        int result ;
        try {
            result = service.enableAccount(id, request.getRemoteAddr());
        }catch (Exception e){
            e.printStackTrace();
            result = 0;
        }
        super.adminLogs("启用管理员账号Id:"+ id + (result >0 ? "成功" : "失败"),GetRemoteIp.getIp(request));
        return result ;
    }

    @RequestMapping("/toChangePwd")
    public String toChangePwd(HttpServletRequest request, ModelMap modelMap) {
        try {
            return "/admin/changePwd";
        } catch (Exception e) {
            log.error(this, e);
            return null;
        }
    }

    @RequestMapping("/homepage")
    public String homepage(HttpServletRequest request, ModelMap modelMap) {
        try {
            Subject subject = SecurityUtils.getSubject();
            Admin admin = (Admin) subject.getPrincipal();
            Admin u = service.findByUser(admin.getId());
            if(u.getLogtime()==null){
                return "redirect:/admin/toChangePwd";
            }
            modelMap.put("user", u);
            Admin user=new Admin();
            user.setLogtime(DateUtil.getCurrentDateTime());
            user.setUsername(u.getUsername());
            this.service.updateUser(user, request.getRemoteAddr());

            return "common/homepage";
        } catch (Exception e) {
            log.error(this, e);
            return null;
        }
    }

    @RequestMapping("/toUpdatePhone")
    public String toUpdatePhone(HttpServletRequest request, ModelMap modelMap) {
        try {
            return "user/updatePhone";
        } catch (Exception e) {
            log.error(this, e);
            return null;
        }
    }

    /**
     * 修改手机号码
     */
    @RequestMapping("/doUpdatePhone")
    public String doUpdatePhone(HttpServletRequest request, String phone, String code, String password, ModelMap model) {
        Integer sessionPhoneCode = (Integer) request.getSession().getAttribute(PHONERANDOMVALIDATECODEKEY);
        if (sessionPhoneCode.toString().equals(code)) {
            // 短信验证成功
            Subject subject = SecurityUtils.getSubject();
            Admin admin = (Admin) subject.getPrincipal();
            if (Md5Util.getMD5(password).equals(admin.getPassword())) {
//                int stat = service.updatePhone(phone, admin.getId(), request.getRemoteAddr());
            }
        } else {
            return null;
        }
        return "common/homepage";
    }

    @RequestMapping("/doChangePwd")
    @ResponseBody
    public Map<String, Object> doChangePwd(HttpServletRequest request, @RequestBody JSONObject obj) {
        try {
            Subject subject = SecurityUtils.getSubject();
            Admin admin = (Admin) subject.getPrincipal();
            log.info("管理修改密码,原密码:"+obj.getString("password")+",新密码:"+obj.getString("newPassword")+",操作IP:"+GetRemoteIp.getIp(request));
            resultMap.put("info", "修改密码失败！");
            resultMap.put("status", "n");
            String oldPassword = Md5Util.getMD5(obj.getString("password"));
            if (!admin.getPassword().equalsIgnoreCase(oldPassword)) {
                resultMap.put("info", "修改密码失败,原密码输入错误！");
                resultMap.put("status", "n");
                super.adminLogs("ID:"+admin.getId()+"修改密码失败，原密码输入错误",GetRemoteIp.getIp(request));
            } else {
                int res = service.updatePass(obj.getString("newPassword"), admin.getId());
                if (res > 0) {
                    resultMap.put("info", "修改密码成功！");
                    resultMap.put("status", "y");
                }
                super.adminLogs("ID:"+admin.getId()+"修改密码"+(res > 0 ? "成功":"失败"),GetRemoteIp.getIp(request));
            }
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(this, e);
            super.adminLogs("系统异常,修改密码失败",GetRemoteIp.getIp(request));
            return null;
        }
    }


    /**
     * @Description: 用户登出
     * @return
     */
    @RequestMapping("/logOut")
    public String logOut() {

        service.logout();

        return "redirect:common/login";
    }

    @RequestMapping("toAddAdmin")
    public String toAddMembers(ModelMap modelMap) {
        List<Role> roles = this.roleService.findRoles(null);
        modelMap.put("roles", roles);
        return "user/addAdmin";
    }

    /***
     * validform校验用户是否存在
     * @param request
     * @param param
     * @param model
     * @return
     */
    @RequestMapping("/checkUsername")
    @ResponseBody
    public Object checkUsername(HttpServletRequest request, String param,ModelMap model){
        List<Admin> list = service.findByUsername(param);
        if(list == null || list.isEmpty()){
            return "{\"info\":\"验证通过！\",\"status\":\"y\"}";
        }
        return "{\"info\":\"用户名已存在！\",\"status\":\"n\"}";
    }
}
