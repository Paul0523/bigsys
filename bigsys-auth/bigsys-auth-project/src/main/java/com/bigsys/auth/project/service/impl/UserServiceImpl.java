package com.bigsys.auth.project.service.impl;

import com.bigsys.auth.project.db.dao.UserMapper;
import com.bigsys.auth.project.db.model.User;
import com.bigsys.auth.project.db.model.UserExample;
import com.bigsys.auth.project.service.UserService;
import com.bigsys.auth.project.util.CreateExample;
import com.bigsys.auth.project.util.UUIDGenarator;
import com.bigsys.auth.project.util.dao.BaseServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<String, User, UserExample, UserExample.Criteria> implements UserService {

    private final String INIT_PWD = "123456";
    @Resource
    private UserMapper userMapper;

    @Override
    public Object getDao() {
        return userMapper;
    }

    @Override
    public Class getExample() {
        return UserExample.class;
    }


    @Override
    public void addOrUpdate(User model) {
        if (StringUtils.isEmpty(model.getId())) {
            model.setId(UUIDGenarator.getUUID());
            model.setPassward(INIT_PWD);
            model.setCreateTime(new Date());
            model.setUpdateTime(new Date());
            userMapper.insert(model);
        } else {
            userMapper.updateByPrimaryKey(model);
        }
    }

    @Override
    public PageInfo<User> page(User condition, PageInfo<User> pager) {
        pager = selectByExampleAndPage(pager, (example, criteria) -> {
            example.setOrderByClause("name asc");
        });
        return pager;
    }



}
