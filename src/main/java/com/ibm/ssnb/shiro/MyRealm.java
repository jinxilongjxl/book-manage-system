package com.ibm.ssnb.shiro;

import com.ibm.ssnb.dao.UserDao;
import com.ibm.ssnb.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {

    @Resource
    private UserDao userDao;

    /**
     * 授权--验证url
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String name = (String)SecurityUtils.getSubject().getPrincipal();
        User user = userDao.findByName(name);

        // 有了用户就可以拿到角色，有了角色，就可以得到对应的菜单list集合 -permissions
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Set<String> roles = new HashSet<String>();
        roles.add("管理员");
        info.addStringPermission("添加用户权限"); // 添加权限
        info.setRoles(roles);
        return info;
    }


    /**
     * 权限验证--登陆
     * @param authenticationToken
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String name = (String)authenticationToken.getPrincipal();
        User user = userDao.findByName(name);
        if (user!= null) {

            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getName(), user.getPwd(), "xxx");

                    return authcInfo;
        } else {
            return null;
        }

    }
}
