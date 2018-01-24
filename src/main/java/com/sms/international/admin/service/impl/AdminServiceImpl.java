package com.sms.international.admin.service.impl;

import com.sms.international.admin.mapper.AdminMapper;
import com.sms.international.admin.model.Admin;
import com.sms.international.admin.service.AdminService;
import com.sms.international.admin.service.BaseService;
import com.sms.international.admin.utils.Md5Util;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台用户管理接口实现类
 * 
 * @author CQL 331737188@qq.com
 * @date : 2015年9月25日 上午11:48:20
 *
 */
@Transactional
@Service("adminService")
public class AdminServiceImpl extends BaseService<Admin,AdminMapper> implements AdminService {

//    @Autowired
//    private AdminMapper userMapper;

    /**
     * Description:用户登录
     * 
     * @param user
     * @return
     */
    public int addUser(Admin user, String ip) {
        int result = 1;
        try {
            if (user.getPassword() != null) {
                user.setPassword(Md5Util.getMD5(user.getPassword()));
            }
            mapper.addUser(user);
            if (StringUtils.isNotEmpty(user.getRolesId())) {
                Map<String, Integer> map = new HashMap<String, Integer>();
                map.put("uid", user.getId());
                String[] roleIds = user.getRolesId().split(",");
                for (String id : roleIds) {
                    map.put("rid", NumberUtils.toInt(id));
                    mapper.insUserRole(map);
                }
            }
        } catch (Exception e) {
            result = 0;
            e.printStackTrace();
        }
        return result;
    }

    public boolean login(Admin user, String ip) {
        boolean flag = false;
        try {
			Subject subject = SecurityUtils.getSubject();
			AuthenticationToken token = new UsernamePasswordToken(user.getUsername(), DigestUtils.md5Hex(user.getPassword()));
			subject.login(token);
			flag = true;
		} catch (AuthenticationException e) {
			flag = false;
			e.printStackTrace();
		}
        return flag;
    }

    
    public int updateUser(Admin user, String ip) {
        int result = 1;
        try {
            mapper.updateUser(user);
            mapper.delRoles(user.getId());
            if (StringUtils.isNotBlank(user.getRolesId())) {
            	
                Map<String, Integer> map = new HashMap<String, Integer>();
                map.put("uid", user.getId());
                String[] roleIds = user.getRolesId().split(",");
                for (String id : roleIds) {
                	if(StringUtils.isNotBlank(id)){
	                    map.put("rid", Integer.parseInt(id));
	                    mapper.insUserRole(map);
                	}
                }
            }
        } catch (Exception e) {
            result = 0;
            e.printStackTrace();
        }
        return result;
    }

    
    public int deleteUser(Admin user, int uid, String ip) {
        // TODO Auto-generated method stub
        int result = 0;
        if (user == null) {
            return -1;
        }
        try {
            mapper.deleteUser(uid);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            result = -1;
            e.printStackTrace();
        }
        return result;
    }

    
    public int disableAccount(int uid, String ip) {
        // TODO Auto-generated method stub
        int result = 0;
        try {
            Admin user = new Admin();
            user.setId(uid);
            user.setStatus(-1);
            mapper.updateUser(user);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            result = -1;
            e.printStackTrace();
        }
        return result;
    }

    
    public Admin findUser(Admin user) {
        // TODO Auto-generated method stub
            List<Admin> users = mapper.findByT(user);
            if (users != null && users.size() > 0) {
                return users.get(0);
            }
        return null;
    }


    @Override
	public JSONObject findByList(Admin admin) {
    	int num = mapper.countTotal(admin);
		JSONObject obj = new JSONObject();
		obj.put("code", 0);
		obj.put("msg", 0);
		obj.put("count", num);
		List<Admin> admins = mapper.findUserList(admin);
		obj.put("data", JSONArray.fromObject(admins));
		return obj;
	}
    
    public boolean lognameIsExist(String username) {
        int num = mapper.lognameIsExist(username);
        return num > 0;
    }

    
    public int updatePass(String newPass, int uid) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("password", Md5Util.getMD5(newPass));
        map.put("uid", uid);
        int result = mapper.updatePass(map);
        return result;
    }

    
    public boolean phoneIsExist(String phone) {
        int num = mapper.phoneIsExist(phone);
        return num > 0;
    }

    
    public int updatePhone(String phone, int uid, String ip) {
        // TODO Auto-generated method stub
        try {
            Admin u = new Admin();
            u.setId(uid);
            u.setPhone(phone);
            mapper.updateUser(u);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            return -1;
        }
        return 0;
    }


    public Admin findByUser(int uid) {
        // TODO Auto-generated method stub
        Admin user = new Admin();
        user.setId(uid);
        List<Admin> users = mapper.findByT(user);
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

//    
//    public int resetPwd(int uid, String ip) {
//        // TODO Auto-generated method stub
//        int result = 0;
//        try {
//            Admin user = new Admin();
//            user.setId(uid);
//            user.setPassword(new MD5().getMD5(ConfigHelper.defaultpwd));
//            userMapper.updateUser(user);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            result = -1;
//            e.printStackTrace();
//        }
//        return result;
//    }

    
    public int enableAccount(int uid, String ip) {
        // TODO Auto-generated method stub
        int result = 0;
        try {
            Admin user = new Admin();
            user.setId(uid);
            user.setStatus(0);
            mapper.updateUser(user);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            result = -1;
            e.printStackTrace();
        }
        return result;
    }

    
    public List<Admin> findByUsername(String username) {
         return mapper.findByUsername(username);
    }


    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        
    }

    
//    public Page<Admin> findByUser(Admin user) {
//        Page<Admin> pageList = new Page<Admin>();
//
//        // 统计总数
//        int num = mapper.countTotal(user);
//        if (num > 0) {
//            pageList.setRows(num);
//            pageList.setCurrentPage(user.getCurrentPage() == null ? 1 : user.getCurrentPage());
//            pageList.setPageSize(user.getPageSize());
//            user.setCurrentPageIndex(pageList.getCurrentPageIndex());
//            List<Admin> users = mapper.findByT(user);
//            pageList.setResult(users);
//        }
//
//        return pageList;
//    }

	public int resetPwd(int uid, String ip) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Admin> findUsers(Admin user) {
		// TODO 自动生成的方法存根
		return mapper.findUserList(user);
	}

	public List<Admin> findByRoleId(Integer roleId) {
		if(roleId == null) return null;
		return mapper.findByRoleId(roleId);
	}

}
