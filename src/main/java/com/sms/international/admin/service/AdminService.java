package com.sms.international.admin.service;

import com.sms.international.admin.model.Admin;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * 后台管理员接口
 * 
 * @author CQL  331737188@qq.com
 * @date : 2015年9月25日 上午11:47:48
 *
 */
public interface AdminService {
	 
	/**
	 * Description:后台用户登录 
	* @param user
	* @return
	 */
	/**
	 * 添加后台用户
	 * @param user 用户类
	 * @return	0为成功;-1为失败
	 */
	int addUser(Admin user, String ip);
	 
	
	/**
	 * 修改后台用户
	 * @param user
	 * @param ip	用户登录IP
	 * @return	0为成功;-1为失败
	 */
	int updateUser(Admin user, String ip);
	
	/**
	 * 删除后台用户
	 * @param user	操作人
	 * @param uid 删除的用户ID
	 * @param ip	用户登录IP
	 * @return	0为成功;-1为失败
	 */
	int deleteUser(Admin user, int uid, String ip);
	
	/**
	 * 按条件查询后台用户
	 * @param user Admin 类
	 * @return List集合
	 */
//	Page<Admin> findByUser(Admin user);
	
	/**
	 * 根据用户编号查询用户所有信息
	 * @param uid
	 * @return
	 */
	Admin findByUser(int uid);
	
	/**
	 * 重置后台账号密码
	 * @param uid
	 * @param ip	用户登录IP
	 * @return	0为成功;-1为失败
	 */
	int resetPwd(int uid, String ip);
	
	/**
	 * 禁用账号
	 * @param uid
	 * @param ip	用户登录IP
	 * @return	0为成功;-1为失败
	 */
	int disableAccount(int uid, String ip);
	
	/**
	 * 启用账号
	 * @param uid
	 * @param ip	用户登录IP
	 * @return	0为成功;-1为失败
	 */
	int enableAccount(int uid, String ip);
	
	/**
	 * 查询用户信息
	 * @param user
	 * @return
	 */
	Admin findUser(Admin user);
	
	/**
	 * Description: 用户登录
	* @param user
	* @return
	 */
	boolean login(Admin user, String ip);
	
	/**
	 * Description: 用户登出
	*
	 */
	void logout();
	
	
	/**
	 * Description:根据后台用户名查询用户 
	* @param usrename
	* @return
	 */
	List<Admin> findByUsername(String usrename);
	

	public JSONObject findByList(Admin role);
	
	
	/**
	 * 查询用户列表 
	 * @param user
	 * @return
	 */
	List<Admin> findUsers(Admin user);
	
	/**
	 * 查询用户名是否存在
	 * @param username
	 * @return
	 */
	boolean lognameIsExist(String username);
	
	/**
	 * 修改密码
	 * @param newPass	新密码
	 * @param uid	用户ID
	 * @return
	 */
	int updatePass(String newPass, int uid);
	
	/**
	 * 查询号码是否已使用
	 * @return
	 */
	boolean phoneIsExist(String phone);
	
	/**
	 * 修改用户手机
	 * @param phone 	新手机号码
	 * @param uid	用户编号
	 * @return
	 */
	int updatePhone(String phone, int uid, String ip);


	List<Admin> findByRoleId(Integer roleId);
}
