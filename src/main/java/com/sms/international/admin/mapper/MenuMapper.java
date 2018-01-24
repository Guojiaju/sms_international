package com.sms.international.admin.mapper;

import com.sms.international.admin.model.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MenuMapper {

	/**
	 * 查询菜单数量
	 *
	 * @param menu
	 */
	int queryAllTotal(Menu menu);

	/**
	 * 查询菜单列表
	 *
	 * @param menu
	 * @return
	 */
	List<Menu> findAll(Menu menu);

	/**
	 * 获取菜单列表
	 *
	 * @param map
	 *            map包含
	 * @return
	 */
	List<Menu> findAllByGroupid(Map<String, Object> map);

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
	 * 添加菜单
	 *
	 * @param menu
	 * @return
	 */
	int insert(Menu menu);

	/**
	 * 修改菜单
	 *
	 * @param menu
	 * @return
	 */
	int update(Menu menu);

	/**
	 * 删除菜单
	 *
	 * @param menu
	 * @return
	 */
	int delete(Menu menu);

	/***
	 * 根据ID删除菜单及子菜单
	 * 
	 * @param id
	 * @return
	 */
	int deleteById(@Param("id") Integer id);

}
