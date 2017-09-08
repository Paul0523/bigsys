package com.bigsys.auth.project.util.dao;

import com.bigsys.auth.project.util.CreateExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xml.internal.security.Init;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseServiceImpl<key, model, example, criteria> {

    private Map<String, Method> methodMap = new HashMap<>();

    private final String MYBATIS_SELECTBYEXAMPLE = "selectByExample";
    private final String MYBATIS_CREATECRITERIA = "createCriteria";

    public abstract Object getDao();

    public abstract Class getExample();

    public void init() {
        Object dao = getDao();
        Class clazz = dao.getClass();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            methodMap.put(method.getName(), method);
        }
    }

    public PageInfo<model> selectByExampleAndPage(PageInfo<model> page, CreateExample<example, criteria> createExample) {
        try {
            example example = (example) getExample().newInstance();
            Class clazz = example.getClass();
            criteria criteria = (criteria) clazz.getMethod(MYBATIS_CREATECRITERIA).invoke(example);
            createExample.createExample(example, criteria);
            page = PageHelper.startPage(page).doSelectPageInfo(() -> {
                invokeMethod(getDao(), MYBATIS_SELECTBYEXAMPLE, example);
            });
            return page;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    Object invokeMethod(Object obj, String method, Object... args) {
        if (methodMap.size() == 0) {
            init();
        }
        try {
            Object value = methodMap.get(method).invoke(obj, args);
            return value;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

}
