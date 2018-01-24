package com.sms.international.admin.service.impl;

import com.sms.international.admin.mapper.MenuMapper;
import com.sms.international.admin.mapper.RoleMapper;
import com.sms.international.admin.model.Menu;
import com.sms.international.admin.service.MenuService;
import com.sms.international.admin.utils.DateUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private RoleMapper roleMapper;

	public List<Menu> findAllByGroupid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return menuMapper.findAllByGroupid(map);
	}

	public List<Menu> findAll(Menu menu) {
		// TODO Auto-generated method stub
		return menuMapper.findAll(menu);
	}

	public List<String> findUrlByLogname(String logname) {
		// TODO Auto-generated method stub
		return menuMapper.findUrlByLogname(logname);
	}

	public List<Menu> findAllAndChecked(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return menuMapper.findAllAndChecked(map);
	}

	public Menu findMenu(Menu menu) {
		// TODO Auto-generated method stub
		List<Menu> list = menuMapper.findAll(menu);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public int editOrAdd(Menu menu) {
		if (menu != null && menu.getId() != null) {
			return menuMapper.update(menu);
		} else {
			menu.setCreateDate(DateUtil.getCurrentDateTime());
			if(menu.getPid() != null && menu.getPid()>0){
				menu.setGrade(2);
				if(menu.getPid()>10){
					menu.setGrade(3);
				}
			}else{
				menu.setGrade(1);
			}
			int result = menuMapper.insert(menu);
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("departid", 1);
			map.put("menuid", menu.getId());
			roleMapper.insGroupMenu(map);
			return result;
		}
	}

	/***
	 * 根据ID删除菜单及子菜单
	 * 
	 * @param id
	 * @return
	 */
	public int deleteById(Integer id) {
		return menuMapper.deleteById(id);
	}

	@Override
	public JSONObject findByList(Menu menu) {
		int num = menuMapper.queryAllTotal(menu);
		JSONObject obj = new JSONObject();
		obj.put("code", 0);
		obj.put("msg", 0);
		obj.put("count", num);
		List<Menu> menus = menuMapper.findAll(menu);
		obj.put("data", JSONArray.fromObject(menus));
		return obj;
	}

}
