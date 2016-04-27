package com.lyp360.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lyp360.dao.SystemRoleMapper;
import com.lyp360.dao.SystemUserMapper;
import com.lyp360.entity.SystemRole;
import com.lyp360.entity.SystemUser;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class JdbcRealm extends AuthorizingRealm implements Realm, InitializingBean {

    @Autowired
    private SystemUserMapper userDao;
    @Autowired
    private SystemRoleMapper roleDao;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(userDao);
    }

    @Override
    public String getName() {
        return getClass().getName();
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        String username = (String) super.getAvailablePrincipal(principals);
        SystemUser user = userDao.findUserByLoginName(username);
        List<SystemRole> userRoles = roleDao.getRolesByUserId(user.getId());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<String>();
        Set<String> permissions = new HashSet<String>();
        for (SystemRole role : userRoles) {
            roles.add(role.getRoleCode());
//            for (Permission per : role.getPermissionSet()) {
//                permissions.add(per.getName());
//            }
        }
        info.addRoles(roles);
        info.addStringPermissions(permissions);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        SimpleAuthenticationInfo info = null;
        UsernamePasswordToken upt = (UsernamePasswordToken) token;
        String username = upt.getUsername();
        SystemUser user = userDao.findUserByLoginName(username);
        if (user == null) {
            throw new AuthenticationException();
        }
        info = new SimpleAuthenticationInfo(user.getLoginName(), user.getPassWord(), getName());
        return info;
    }
}
