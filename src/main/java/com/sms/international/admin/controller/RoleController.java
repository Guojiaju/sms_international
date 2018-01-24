package com.sms.international.admin.controller;

import com.sms.international.admin.model.Admin;
import com.sms.international.admin.model.Menu;
import com.sms.international.admin.model.Role;
import com.sms.international.admin.service.MenuService;
import com.sms.international.admin.service.RoleService;
import com.sms.international.admin.utils.GetRemoteIp;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色管理
 * 
 * @author CQL 331737188@qq.com
 * @date : 2015年10月14日 下午3:16:28
 *
 */
@Scope("prototype")
@Controller("roleController")
@RequestMapping(value = "/role")
public class RoleController extends ParentController{

	@Autowired
	@Qualifier("roleService")
	private RoleService roleService;

	@Autowired
	@Qualifier("menuService")
	private MenuService menuService;

	@RequestMapping("/findAll")
	public String findAll() {
		return "/role/list";
	}
	
	/**
	 * 查询菜单列表
	 */
	@RequestMapping("/findData")
	@ResponseBody
	public String findData(@RequestBody Role role) {
			return roleService.findByList(role).toString();
	}

	@RequestMapping("/toEditOrAdd/{id}")
	public String toEditOrAdd(HttpServletRequest request, @PathVariable Integer id, ModelMap model) {
		try {
			Subject subject = SecurityUtils.getSubject();
			Admin u = (Admin) subject.getPrincipal();
			List<Integer> roles = roleService.findAdminForRole(u.getId());
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("pdepartid", roles); // 这里的角色ID是一个集合. 需要转成String类型的.
			map.put("departid", id);
			map.put("grade", 1);
			List<Menu> menulist1 = menuService.findAllAndChecked(map);
			model.put("menulist1", menulist1);

			map.put("grade", 2);
			List<Menu> menulist2 = menuService.findAllAndChecked(map);
			model.put("menulist2", menulist2);
			map.put("grade", 3);
			List<Menu> menulist3 = menuService.findAllAndChecked(map);
			model.put("menulist3", menulist3);
			if (id != 0) {
				model.put("role", roleService.findRole(new Role(id)));
			}
			return "/role/edit";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping("/doEditOrAdd")
	public String doEditOrAdd(HttpServletRequest request, @ModelAttribute Role role, ModelMap model) {
		try {
			boolean flag = (null == role.getId() || "".equals(role.getId()));

			int result = roleService.editOrAdd(role, request.getRemoteAddr());

			if (flag) {
				super.adminLogs("新增角色"+(result > 0 ? "成功":"失败"), GetRemoteIp.getIp(request));
			} else {
				super.adminLogs("修改角色id:" + role.getId() +(result > 0 ? "成功":"失败"), GetRemoteIp.getIp(request));
			}
			return "redirect:/role/findAll";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
