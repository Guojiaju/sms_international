package com.sms.international.admin.realm;

import com.sms.international.admin.model.Admin;
import com.sms.international.admin.model.Role;
import com.sms.international.admin.service.AdminService;
import com.sms.international.admin.service.MenuService;
import com.sms.international.admin.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author HQ 94554798@qq.com
 * @version 创建时间：2015年9月24日 下午3:24:43 类说明
 */
public class AdminRealm extends AuthorizingRealm {

    @Autowired
    private MenuService menuService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;
    /**
     * 授权信息
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Admin user = (Admin) principals.fromRealm(getName()).iterator().next();
         if (user != null && StringUtils.isNotEmpty(user.getUsername())) {
            List<String> urllist = menuService.findUrlByLogname(user.getUsername());
            List<Role> roleList = roleService.selectRolesByAdminId(user.getId());
            Set<String> roles = new HashSet<String>();
            for(Role role : roleList){
            	roles.add(role.getDescription());
            }
            Set<String> permissions = new HashSet<String>();
            findPermissions(permissions, urllist);
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            info.setStringPermissions(permissions);
            info.setRoles(roles);
            return info;
        }
        return null;
    }

    public void findPermissions(Set<String> permissions, List<String> urllist) {
        if (urllist != null && urllist.size() > 0) {
            for (String url : urllist) {
                permissions.add(ChainDefinitionSectionMetaSource.findPermission(url));
            }
        }
    }

    @Override
    // 认证方法， 获取用户密码信息
    // 返回 null 代表用户名 不存在
    // 返回 密码信息
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        Admin user = new Admin();
        user.setUsername(token.getUsername());
        StringBuilder sb=new StringBuilder();
        char[] pw=token.getPassword();
        for (char c : pw) {
        	sb.append(c);
		}
        user.setPassword(sb.toString());
        user = adminService.findUser(user);
         if (user != null) {
            return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        } else {
            return null;
        }
     }
}
