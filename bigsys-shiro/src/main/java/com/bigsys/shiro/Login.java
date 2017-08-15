package com.bigsys.shiro;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fangzhipeng on 2017/8/15.
 */
@Controller
public class Login {

    @RequestMapping(value = "/test")
    @ResponseBody
    public String login() {
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
        return "hello shiro";
    }

}
