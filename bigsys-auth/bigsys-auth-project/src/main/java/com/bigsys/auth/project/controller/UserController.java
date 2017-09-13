package com.bigsys.auth.project.controller;

import com.bigsys.auth.project.db.model.User;
import com.bigsys.auth.project.service.UserService;
import com.bigsys.auth.project.util.response.BSResponse;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.naming.Name;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/delete")
    public BSResponse delete(String id) {
        userService.deleteByPrimaryKey(id);
        return BSResponse.ok();
    }

    @RequestMapping(value = "/addOrUpdate")
    public BSResponse addOrUpdateUser(@RequestBody User model) {
        userService.addOrUpdate(model);
        return BSResponse.ok();
    }

    @RequestMapping(value = "/getById")
    public BSResponse getById (String id) {
        User user = userService.selectByPrimaryKey(id);
        return BSResponse.ok(user);
    }

    @RequestMapping(value = "/page")
    public PageInfo<User> page (User model, PageInfo<User> page) {
        return userService.page(model, page);
    }

}
