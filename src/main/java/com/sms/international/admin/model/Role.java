package com.sms.international.admin.model;

import java.io.Serializable;

/**
 * 
 * @author goliath
 *角色类
 */
public class Role  extends BaseEntity implements Serializable{
	
	/**
	 * 
	 */
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String roleName;//角色名称,用中文
	
	private String description;	//角色描述
	
	private int status;	//角色状态
	
	private String createDate;	//创建时间
	
	private String checked;	//是否被选中,该字段不存在数据库中
	
	private String menuids;	//菜单权限集
	
	public Role(){
		
	}
	public Role(Integer id){
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	/**
	 * 角色名称
	 * @param roleName
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getStatus() {
		return status;
	}

	/**
	 * 角色状态
	 * @param status
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	public String getCreateDate() {
		if(createDate != null && createDate.length() > 19){
			return createDate.substring(0, 19);
		}
		return createDate;
	}

	public String getDescription() {
		return description;
	}

	/**
	 * 角色描述
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 创建时间
	 * @param createDate
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}
	public String getMenuids() {
		return menuids;
	}
	
	/**
	 * 角色拥有权限集(该字段不存在数据库)
	 * @param menuids
	 */
	public void setMenuids(String menuids) {
		this.menuids = menuids;
	}
	
}
