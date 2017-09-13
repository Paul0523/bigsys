package com.bigsys.auth.project.config;

import com.bigsys.auth.project.db.model.User;
import com.bigsys.auth.project.util.response.BSResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by fangzhipeng on 2017/9/4.
 */
@Configuration
public class ShiroConfig {

    @Resource
    private Realm myRealm;

    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     *
     * Filter Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过
     * 3、部分过滤器可指定参数，如perms，roles
     *
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setFilters(getMyFilters());

        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/loginRes");
        // 未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        // 拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/hello", "anon");
        filterChainDefinitionMap.put("/**", "authc");
//        // 配置不会被拦截的链接 顺序判断
//        filterChainDefinitionMap.put("/static/**", "anon");
//        filterChainDefinitionMap.put("/ajaxLogin", "anon");
//
//        // 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
//        filterChainDefinitionMap.put("/logout", "logout");
//
//        filterChainDefinitionMap.put("/add", "perms[权限添加]");
//
//        // <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
//        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
//        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        System.out.println("Shiro拦截器工厂类注入成功");
        return shiroFilterFactoryBean;
    }

    private Map<String, Filter> getMyFilters() {
        Map<String, Filter> filters = new HashMap<>();
        filters.put("authc", new FormAuthenticationFilter() {

            @Override
            protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
                if (isLoginRequest(request, response)) {
                    if (isLoginSubmission(request, response)) {
                        return executeLogin(request, response);
                    } else {
                        //allow them to see the login page ;)
                        return true;
                    }
                } else {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("utf-8");
                    BSResponse bsResponse = BSResponse.needLogin("您已经退出，请重新登录。");
                    OutputStream outputStream = null;
                    outputStream = response.getOutputStream();
                    outputStream.write(new ObjectMapper().writeValueAsString(bsResponse).getBytes("utf-8"));
                    return false;
                }
            }

            @Override
            protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
                try {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("utf-8");
                    BSResponse bsResponse = BSResponse.error(e.getMessage());
                    OutputStream outputStream = null;
                    outputStream = response.getOutputStream();
                    outputStream.write(new ObjectMapper().writeValueAsString(bsResponse).getBytes("utf-8"));
                    return false;
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                return false;
            }

            @Override
            protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                User user = (User) SecurityUtils.getSubject().getPrincipal();
                BSResponse bsResponse = BSResponse.ok(user);
                response.setContentLength(new ObjectMapper().writeValueAsString(bsResponse).getBytes("utf-8").length);
                OutputStream outputStream = response.getOutputStream();
                outputStream.write(new ObjectMapper().writeValueAsString(bsResponse).getBytes("utf-8"));
                return false;
            }
        });

        return filters;
    }

    @Bean
    public SecurityManager securityManager(SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager);
        // 设置realm.
        securityManager.setRealm(myRealm);
        return securityManager;
    }

    @Bean
    public SessionManager sessionManager() {
        Cookie sessionIdCookie = new SimpleCookie();
        sessionIdCookie.setName("MySessionId");
        sessionIdCookie.setHttpOnly(false);
        sessionIdCookie.setPath("/");
        DefaultWebSessionManager manager = new DefaultWebSessionManager();
        manager.setSessionIdCookie(sessionIdCookie);
        return manager;
    }


//    /**
//     * 身份认证realm; (这个需要自己写，账号密码校验；权限等)
//     *
//     * @return
//     */
//    @Bean
//    public MyShiroRealm myShiroRealm() {
//        MyShiroRealm myShiroRealm = new MyShiroRealm();
//        return myShiroRealm;
//    }
}
