package com.sms.international.admin.mapper;

import com.sms.international.admin.model.Admin;

import java.util.List;
import java.util.Map;

/**
 * 后台人员
 * 
 * @author CQL  331737188@qq.com
 * @date : 2015年9月23日 下午1:37:09
 *
 */
public interface AdminMapper extends BaseMapper<Integer,Admin>{
	
	/**
	 * 添加后台用户
	 * @param user 用户类
	 * @return
	 */
	void addUser(Admin user);
	
	/**
	 * 修改后台用户
	 * @param user
	 * @return
	 */
	void updateUser(Admin user);
	
	/**
	 * 删除后台用户
	 * @param uid 用户ID
	 * @return
	 */
	void deleteUser(int uid);
	
	/**
	 * 按条件查询后台用户
	 * @param user AdminUser 类
	 * @return List集合
	 */
	List<Admin> findByT(Admin user);
	
	/**
	 * 根据条件统计记录总数
	 * @param user
	 * @return
	 */
	int countTotal(Admin user);

	

	/**
	 * 根据名称查询用户
	 * @param username	用户名
	 * @return
	 */
	List<Admin> findByUsername(String username);
	
	/**
	 * 查询用户列表
	 * @return
	 */
	List<Admin> findUserList(Admin user);
	
	/**
	 * 删除用户角色集
	 * @param id
	 * @return
	 */
	int delRoles(int id);
	
	/**
	 * 添加用户角色
	 * @param map
	 * @return
	 */
	int insUserRole(Map<String, Integer> map);
	
	/**
	 * 查询用户名是否存在
	 * @param username
	 * @return
	 */
	int lognameIsExist(String username);
	
	/**
	 * 修改密码
	 * @param newPass	新密码
	 * @param uid	用户编号
	 * @return
	 */
	int updatePass(Map<String, Object> map);
	
	/**
	 * 查询号码是否已使用
	 * @param phone
	 * @return
	 */
	int phoneIsExist(String phone);
	
	List<Admin> findByRoleId(Integer roleId);
}
