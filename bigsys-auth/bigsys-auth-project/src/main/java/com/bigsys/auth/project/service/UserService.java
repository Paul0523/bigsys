package com.bigsys.auth.project.service;

import com.bigsys.auth.project.db.model.User;
import com.bigsys.auth.project.db.model.UserExample;
import com.bigsys.auth.project.util.service.BaseService;
import com.github.pagehelper.PageInfo;

public interface UserService extends BaseService<String, User, UserExample, UserExample.Criteria> {

    void addOrUpdate(User model);

    /**
     * 根据当前登录人角色返回对应的角色下的用户
     * @param condition
     * @param pager
     * @return
     */
    PageInfo<User> page(User condition, PageInfo<User> pager);

    User getUser(String username);
}
