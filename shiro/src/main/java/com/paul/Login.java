package com.paul;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by fangzhipeng on 2017/8/15.
 */
@RequestMapping(value = "/login")
public class Login {

    @RequestMapping(value = "/login")
    public void login() {
//        Subject subject = SecurityUtils.getSubject();
//        subject.login(new AuthenticationToken() {
//            public Object getPrincipal() {
//                return null;
//            }
//
//            public Object getCredentials() {
//                return null;
//            }
//        });
        System.out.println("登录成功！");
    }

}
