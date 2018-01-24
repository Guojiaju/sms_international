package com.sms.international.admin.model;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author goliath 后台用户类
 */
public class Admin extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    private String username;// 账号
    private String realname;// 用户真实姓名
    private String password;// 用户密码
    private String phone;// 用户电话
    private Integer status;// 用户状态,:0为启用;-1为禁用
    private String createDate;// 用户创建时间
    private String roleName;//后台用户所属部门
    private String logtime;    //上次登录时间
    private String rolesId;    //角色集
    private Integer rid;
    private int[] rids;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUsername() {
        return username;
    }

    public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	/**
     * @param username 用户名称
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    /**
     * @param password 用户密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    /**
     * @param phone 用户电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    /**
     * @param status 0为启用;-1为禁用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateDate() {
        if (createDate != null && createDate.length() > 19) {
            return createDate.substring(0, 19);
        }
        return createDate;
    }

    /**
     * @param createDate 创建日期
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getRolesId() {
        return rolesId;
    }

    /**
     * @param rolesId 该用户所属角色集
     */
    public void setRolesId(String rolesId) {
        this.rolesId = rolesId;
    }

    public String getLogtime() {
        if (logtime != null && logtime.length() > 19) {
            return logtime.substring(0, 19);
        }
        return logtime;
    }

    /**
     * @param logtime 上次登录时间
     */
    public void setLogtime(String logtime) {
        this.logtime = logtime;
    }

    public boolean checkRole(Integer roleId){
    	if(rolesId == null || rolesId.equals("") || roleId == null){
    		return false;
    	}
    	String[] rolesIdArray = rolesId.split(",");
    	for(String id:rolesIdArray){
    		if(id.equals(roleId.toString())){
    			return true;
    		}
    	}
    	return false;
    }

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public int[] getRids() {
		return rids;
	}

	public void setRids(int[] rids) {
		this.rids = rids;
	}

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"username\":\"")
                .append(username).append('\"');
        sb.append(",\"realname\":\"")
                .append(realname).append('\"');
        sb.append(",\"password\":\"")
                .append(password).append('\"');
        sb.append(",\"phone\":\"")
                .append(phone).append('\"');
        sb.append(",\"status\":")
                .append(status);
        sb.append(",\"createDate\":\"")
                .append(createDate).append('\"');
        sb.append(",\"roleName\":\"")
                .append(roleName).append('\"');
        sb.append(",\"logtime\":\"")
                .append(logtime).append('\"');
        sb.append(",\"rolesId\":\"")
                .append(rolesId).append('\"');
        sb.append(",\"rid\":")
                .append(rid);
        sb.append(",\"rids\":")
                .append(Arrays.toString(rids));
        sb.append('}');
        return sb.toString();
    }
}
