package com.bigsys.auth.project.config;

import com.bigsys.auth.project.db.model.User;
import com.bigsys.auth.project.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("myRealm")
public class MyRealm extends AuthorizingRealm{

    @Resource
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        User user = userService.getUser((String) authenticationToken.getPrincipal());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, authenticationToken.getCredentials(), this.getClass().getName());
        return info;
    }
}
