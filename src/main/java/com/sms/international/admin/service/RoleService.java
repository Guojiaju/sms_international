package com.sms.international.admin.service;

import com.sms.international.admin.model.Role;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @author HQ 94554798@qq.com
* @version 创建时间：2015年9月24日 下午1:47:43 
* 类说明角色
 */
public interface RoleService {
	
	/**
	 * 添加角色
	 * @param role
	 * @return 0为添加成功;-1为添加失败
	 */
	int addRole(Role role, String ip);
	
	/**
	 * 添加角色与管理人员关联
	 * @param adminId	后台人员ID
	 * @param roleId	角色ID
	 * @return 0为添加成功;-1为添加失败
	 */
	int addRoleToUser(int adminId, int roleId);
	
	/**
	 * Description: 修改角色的描述
	 * @param role
	 * @return 0为添加成功;-1为添加失败
	 */
	int updateRole(Role role, String ip);
	
	/**
	 * 查询角色
	 * @param role
	 * @return
	 */
	List<Role> findRoles(Role role);
	
	public JSONObject findByList(Role role);
	
	/**
	 * 禁用角色
	 * @param roleId
	 * @return
	 */
	int disableRole(int roleId, String ip);
	
	/**
	 * 查询后台用户绑定的角色
	 * @param adminId
	 * @return
	 */
	List<Integer> findAdminForRole(int adminId);
	
	/**
	 * 查询角色
	 * @param role
	 * @return
	 */
	Role findRole(Role role);
	
	/**
	 * 编辑角色
	 * @param role
	 * @return
	 */
	int editOrAdd(Role role, String ip);
	
	/**
	 * 查询已勾选的菜单项
	 * @param map
	 * @return
	 */
	List<Role> findAllAndChecked(Map<String, Integer> map);
	
	/***
	 * 获取用户角色列表
	 * @param id
	 * @return
	 */
	List<Role> selectRolesByAdminId(Integer id);
	
	public List<String> findRoleForAdmin(List<Integer> list);
	
	public List<Integer> findRoleForAdminId(List<Integer> list);
}
