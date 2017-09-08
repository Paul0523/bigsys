package com.bigsys.auth.project.service;

import com.bigsys.auth.project.db.model.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

public interface UserService {

    void addOrUpdate(User model);

    PageInfo<User> page(User condition, PageInfo<User> pager);

}
