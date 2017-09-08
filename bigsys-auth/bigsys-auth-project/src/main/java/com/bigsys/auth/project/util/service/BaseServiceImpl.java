package com.bigsys.auth.project.util.service;

import com.bigsys.auth.project.util.CreateExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseServiceImpl<key, model, example, criteria> implements BaseService<key, model, example, criteria> {

    private Map<String, Method> methodMap = new HashMap<>();
    private final String MYBATIS_COUNTBYEXAMPLE = "countByExample";
    private final String MYBATIS_DELETEBYEXAMPLE = "deleteByExample";
    private final String MYBATIS_DELETEBYPRIMARYKEY = "deleteByPrimaryKey";
    private final String MYBATIS_INSERT = "insert";
    private final String MYBATIS_INSERTSELECTIVE = "insertSelective";
    private final String MYBATIS_SELECTBYEXAMPLE = "selectByExample";
    private final String MYBATIS_SELECTBYPRIMARYKEY = "selectByPrimaryKey";
    private final String MYBATIS_UPDATEBYEXAMPLESELECTIVE = "updateByExampleSelective";
    private final String MYBATIS_UPDATEBYEXAMPLE = "updateByExample";
    private final String MYBATIS_UPDATEBYPRIMARYKEYSELECTIVE = "updateByPrimaryKeySelective";
    private final String MYBATIS_UPDATEBYPRIMARYKEY = "updateByPrimaryKey";
    private final String MYBATIS_CREATECRITERIA = "createCriteria";

    public abstract Object getDao();

    public abstract Class getExample();

    @Override
    public long countByExample(CreateExample<example, criteria> createExample) {
        return (long) invokeMethod(getDao(), MYBATIS_COUNTBYEXAMPLE, produceExample(createExample));
    }

    @Override
    public int deleteByExample(CreateExample<example, criteria> createExample) {
        return (int) invokeMethod(getDao(), MYBATIS_DELETEBYEXAMPLE, produceExample(createExample));
    }

    @Override
    public int deleteByPrimaryKey(key id) {
        return (int) invokeMethod(getDao(), MYBATIS_DELETEBYPRIMARYKEY, id);
    }

    @Override
    public int insert(model record) {
        return (int) invokeMethod(getDao(), MYBATIS_INSERT, record);
    }

    @Override
    public int insertSelective(model record) {
        return (int) invokeMethod(getDao(), MYBATIS_INSERTSELECTIVE, record);
    }

    @Override
    public List<model> selectByExample(CreateExample<example, criteria> createExample) {
        return (List<model>) invokeMethod(getDao(), MYBATIS_SELECTBYEXAMPLE, produceExample(createExample));
    }

    @Override
    public model selectByPrimaryKey(key id) {
        return (model) invokeMethod(getDao(), MYBATIS_SELECTBYPRIMARYKEY, id);
    }

    @Override
    public int updateByExampleSelective(model record, CreateExample<example, criteria> createExample) {
        return (int) invokeMethod(getDao(), MYBATIS_UPDATEBYEXAMPLESELECTIVE, record, produceExample(createExample));
    }

    @Override
    public int updateByExample(model record, CreateExample<example, criteria> createExample) {
        return (int) invokeMethod(getDao(), MYBATIS_UPDATEBYEXAMPLE, record, produceExample(createExample));
    }

    @Override
    public int updateByPrimaryKeySelective(model record) {
        return (int) invokeMethod(getDao(), MYBATIS_UPDATEBYPRIMARYKEYSELECTIVE, record);
    }

    @Override
    public int updateByPrimaryKey(model record) {
        return (int) invokeMethod(getDao(), MYBATIS_UPDATEBYPRIMARYKEY, record);
    }

    @Override
    public PageInfo<model> selectByExampleAndPage(PageInfo<model> page, CreateExample<example, criteria> createExample) {
        page = PageHelper.startPage(page).doSelectPageInfo(() -> {
            invokeMethod(getDao(), MYBATIS_SELECTBYEXAMPLE, produceExample(createExample));
        });
        return page;
    }

    private void init() {
        Object dao = getDao();
        Class clazz = dao.getClass();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            methodMap.put(method.getName(), method);
        }
    }

    private example produceExample(CreateExample<example, criteria> createExample) {
        example example = null;
        try {
            example = (example) getExample().newInstance();
            Class clazz = example.getClass();
            criteria criteria = (criteria) clazz.getMethod(MYBATIS_CREATECRITERIA).invoke(example);
            createExample.createExample(example, criteria);
            return example;
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

    private Object invokeMethod(Object obj, String method, Object... args) {
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
