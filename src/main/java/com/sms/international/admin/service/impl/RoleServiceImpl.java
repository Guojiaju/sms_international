package com.sms.international.admin.service.impl;

import com.sms.international.admin.mapper.RoleMapper;
import com.sms.international.admin.model.Admin;
import com.sms.international.admin.model.Role;
import com.sms.international.admin.service.RoleService;
import com.sms.international.admin.utils.DateUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author 何琦 94554798@qq.com
 * @version 创建时间：2015年9月24日 下午1:30:20 类说明
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    
    
    public int addRole(Role role,String ip) {
        int result = 0;
        Subject subject = SecurityUtils.getSubject();
        Admin user = (Admin)subject.getPrincipal();
        
        if (user == null || role == null || role.getId() == null) {
			return -1;
		}
        
        try {
            roleMapper.saveRole(role);
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            result = -1;
            e.printStackTrace();
        }
        return result;
    }

    
    public int updateRole(Role role,String ip) {
        int result = 0;
        Subject subject = SecurityUtils.getSubject();
        Admin user = (Admin)subject.getPrincipal();
        
        if (user == null || role == null || role.getId() == null) {
			return -1;
		}
        
        try {
            roleMapper.update(role);
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            result = -1;
            e.printStackTrace();
        }
        return result;
    }

    
    public List<Role> findRoles(Role role) {
        try {
            return roleMapper.findRole(role);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    
    public Role findRole(Role role) {
        try {
            List<Role> roles = roleMapper.findRole(role);
            if (roles != null && roles.size() > 0) {
                return roles.get(0);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    
    public int disableRole(int roleId,String ip) {
        int result = 0;
        
        Subject subject = SecurityUtils.getSubject();
        Admin user = (Admin)subject.getPrincipal();
        
        if (user == null || roleId == 0) {
			return -1;
		}
        try {
            roleMapper.disableRole(roleId);
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            result = -1;
            e.printStackTrace();
        }
        return result;
    }

    
    public int addRoleToUser(int adminId, int roleId) {
        // TODO Auto-generated method stub
        return 0;
    }

    
    public List<Integer> findAdminForRole(int adminId) {
        // TODO Auto-generated method stub
        List<Role> roles = roleMapper.findAdminForRole(adminId);
        List<Integer> ids = new ArrayList<Integer>();
        if (roles != null && roles.size() > 0) {
            for (Role r : roles) {
                ids.add(r.getId());
            }
        }

        return ids;
    }

 /*
    public void queryRoles(Page<Role> pageList, Role role) {
        // TODO Auto-generated method stub
        int num = roleMapper.queryAllTotal(role);
        if (num > 0) {
            pageList.setRows(num);
            pageList.setCurrentPage(role.getCurrentPage() == null ? 1 : role.getCurrentPage());
            pageList.setPageSize(role.getPageSize());

            role.setCurrentPageIndex(pageList.getCurrentPageIndex());
            List<Role> list = findRoles(role);
            pageList.setResult(list);
        }
    }
    */
    @Override
	public JSONObject findByList(Role role) {
		// TODO Auto-generated method stub
    	int num = roleMapper.queryAllTotal(role);
		JSONObject obj = new JSONObject();
		obj.put("code", 0);
		obj.put("msg", 0);
		obj.put("count", num);
		JSONArray arr = new JSONArray();
		List<Role> roles = roleMapper.findRole(role);
		if(roles != null &&roles.size() > 0){
			for (Role u : roles) {
				JSONObject o = new JSONObject();
				o.put("id", u.getId().toString());
				o.put("roleName", u.getRoleName());
				o.put("description", u.getDescription());
				o.put("createDate", u.getCreateDate());
				o.put("status", u.getStatus());
				arr.add(o);
			}
		}
		obj.put("data", arr);
		return obj;
	}

    
    public int editOrAdd(Role role, String ip) {
        // TODO Auto-generated method stub
        int result = 0;
        if (role != null && role.getId() != null) {
            roleMapper.update(role);
            roleMapper.delGroupMenu(role.getId());
        } else {
        	role.setCreateDate(DateUtil.getCurrentDateTime());
            roleMapper.saveRole(role);
        }
        if (StringUtils.isNotEmpty(role.getMenuids())) {
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("departid", role.getId());
            String[] menuid = role.getMenuids().split("-");
            for (String id : menuid) {
                map.put("menuid", NumberUtils.toInt(id));
                roleMapper.insGroupMenu(map);
            }
        }

        return result;
    }

    
    public List<Role> findAllAndChecked(Map<String, Integer> map) {
        // TODO Auto-generated method stub

        return roleMapper.findAllAndChecked(map);
    }

	@Override
	public List<Role> selectRolesByAdminId(Integer id) {
		return roleMapper.selectRolesByAdminId(id);
	}


	@Override
	public List<String> findRoleForAdmin(List<Integer> list) {
		// TODO Auto-generated method stub
		return roleMapper.findRoleForAdmin(list);
	}

	@Override
	public List<Integer> findRoleForAdminId(List<Integer> list){
		// TODO Auto-generated method stub
		return roleMapper.findRoleForAdminId(list);
	}

}
