package com.bigsys.auth.project.config;

import com.bigsys.auth.project.db.model.User;
import com.bigsys.auth.project.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.SimpleByteSource;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.Charset;

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
        if (!user.getPassward().equals((new String((char[]) authenticationToken.getCredentials())))) {
            throw new IncorrectCredentialsException("密码错误！！");
        }
        AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassward(), this.getClass().getName());
        return info;
    }
}
