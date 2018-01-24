package com.sms.international.admin.mapper;

import com.sms.international.admin.model.Role;

import java.util.List;
import java.util.Map;

public interface RoleMapper extends BaseMapper<Integer,Role>{
	
	/**
	 * 添加角色
	 * @param role
	 */
	void saveRole(Role role);
	
//	/**
//	 * 修改角色
//	 * @param role
//	 */
//	void update(Role role);
	
	/**
	 * 禁用角色
	 * @param roleId
	 */
	void disableRole(int roleId);

	
	/**
	 * 查询角色
	 * @param role
	 * @return
	 */
	List<Role> findRole(Role role);
	
	/**
	 * 查询后台人员所有角色
	 * @param adminId	后台人员编号
	 * @return
	 */
	List<Role> findAdminOrRole(int adminId);
	
	/**
	 * 查询用户角色编号
	 * @param adminId
	 * @return
	 */
	List<Role> findAdminForRole(int adminId);
	
	/**
	 * 查询条数
	 * @param role
	 * @return
	 */
	int queryAllTotal(Role role);
	
	/**
	 * 删除角色拥有菜单权限集
	 * @param id
	 * @return
	 */
	int delGroupMenu(int id);

	/**
	 * 添加角色菜单权限
	 * @param map
	 * @return
	 */
	int insGroupMenu(Map<String, Integer> map);
	
	/**
	 * 查询勾选的角色
	 * @param map
	 * @return
	 */
	List<Role> findAllAndChecked(Map<String, Integer> map);
	
	List<Role> selectRolesByAdminId(Integer id);
	
	public List<String> findRoleForAdmin(List<Integer> list);
	
	public List<Integer> findRoleForAdminId(List<Integer> list);
}
