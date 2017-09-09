package com.bigsys.auth.project.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;

@Component
public class SpringInit implements ApplicationContextAware{

    @Resource
    private ApplicationContext applicationContext;

    @PostConstruct
    public void addSupportType() {
        SimpleUrlHandlerMapping handlerMapping = applicationContext.getBean("resourceHandlerMapping", SimpleUrlHandlerMapping.class);
        Map<String, ResourceHttpRequestHandler> urlMap = (Map<String, ResourceHttpRequestHandler>) handlerMapping.getUrlMap();
        for (Map.Entry<String, ResourceHttpRequestHandler> entry : urlMap.entrySet()) {
            entry.getValue().setSupportedMethods("GET", "POST");
        }

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
