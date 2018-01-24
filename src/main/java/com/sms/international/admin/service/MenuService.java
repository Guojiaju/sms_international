package com.sms.international.admin.service;

import com.sms.international.admin.model.Menu;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * 后台菜单
 * 
 * @author CQL 331737188@qq.com
 * @date : 2015年10月8日 上午11:28:39
 *
 */
public interface MenuService {

	/**
	 * 获取菜单列表
	 * 
	 * @param map
	 *            map包含
	 * @return
	 */
	List<Menu> findAllByGroupid(Map<String, Object> map);

	public JSONObject findByList(Menu menu);

	/**
	 * 查询菜单列表
	 * 
	 * @param menu
	 * @return
	 */
	List<Menu> findAll(Menu menu);

	/**
	 * 查询菜单访问路径
	 * 
	 * @param logname
	 *            后台人员登录名
	 * @return 菜单路径集
	 */
	List<String> findUrlByLogname(String logname);

	/**
	 * 查询已勾选的菜单项
	 * 
	 * @param map
	 * @return 菜单路径集
	 */
	List<Menu> findAllAndChecked(Map<String, Object> map);

	/**
	 * 查询菜单详细
	 * 
	 * @param menu
	 * @return 菜单
	 */
	Menu findMenu(Menu menu);

	/**
	 * 修改或添加菜单
	 * 
	 * @param menu
	 * @return 0为成功;-1为失败
	 */
	int editOrAdd(Menu menu);

	/**
	 * 删除菜单
	 * 
	 * @param menu
	 * @return 0为成功;-1为失败
	 */
	int deleteById(Integer id);
}
