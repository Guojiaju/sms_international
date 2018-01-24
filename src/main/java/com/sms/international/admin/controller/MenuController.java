package com.sms.international.admin.controller;

import com.sms.international.admin.model.Menu;
import com.sms.international.admin.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 菜单管理
 * 
 * @author CQL 331737188@qq.com
 * @date : 2015年10月14日 下午1:14:33
 *
 */
@Scope("prototype")
@Controller("menuController")
@RequestMapping(value = "/menu")
public class MenuController {

	@Autowired
	@Qualifier("menuService")
	private MenuService menuService;

	/**
	 * 查询菜单列表
	 */
	@RequestMapping("/findAll")
	public String findAll(HttpServletRequest request, @ModelAttribute Menu menu, ModelMap model) {
		try {
			Menu querymenu = new Menu();
			querymenu.setGrade(1);
			List<Menu> parentMenu1 = menuService.findAll(querymenu);
			querymenu.setGrade(2);
			List<Menu> parentMenu2 = menuService.findAll(querymenu);

			model.put("parentMenu1", parentMenu1);
			model.put("parentMenu2", parentMenu2);

			return "/menu/list";
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 查询菜单列表
	 */
	@RequestMapping("/findData")
	@ResponseBody
	public String findData(@RequestBody Menu menu) {
		return menuService.findByList(menu).toString();
	}

	/**
	 * 查询添加或修改菜单
	 */
	@RequestMapping("/toEditOrAdd/{id}")
	public String toEditOrAdd(HttpServletRequest request, @PathVariable Integer id, ModelMap model) {
		try {
			Menu menu = new Menu();
			menu.setGrade(1);
			List<Menu> parentMenu1 = menuService.findAll(menu);
			menu.setGrade(2);
			List<Menu> parentMenu2 = menuService.findAll(menu);

			model.put("parentMenu1", parentMenu1);
			model.put("parentMenu2", parentMenu2);

			if (id != 0) {
				menu = new Menu(id);
				model.put("menu", menuService.findMenu(menu));
			}
			return "/menu/edit";
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 执行添加或修改菜单
	 */
	@RequestMapping("/doEditOrAdd")
	@ResponseBody
	public String doEditOrAdd(HttpServletRequest request, @ModelAttribute Menu menu, ModelMap model) {
		try {
			int result = menuService.editOrAdd(menu);
			if (result >= 0) {
				return "success";
			} else {
				return "fail";
			}
		} catch (Exception e) {
			return "fail";
		}
	}

	/***
	 * 删除菜单
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doDelete")
	public Object doDelete(HttpServletRequest request, Integer id) {
		Integer result = menuService.deleteById(id);
		if (result >= 0) {
			return "success";
		} else {
			return "fail";
		}
	}
}
