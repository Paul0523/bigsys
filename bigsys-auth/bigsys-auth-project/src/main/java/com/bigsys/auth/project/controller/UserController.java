package com.bigsys.auth.project.controller;

import com.bigsys.auth.project.db.model.User;
import com.bigsys.auth.project.service.UserService;
import com.bigsys.auth.project.util.response.BSResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/addOrUpdateUser")
    public BSResponse addOrUpdateUser(User model) {
        userService.addOrUpdate(model);
        return BSResponse.ok();
    }

    @RequestMapping(value = "/getById")
    public BSResponse getById (String id) {
        User user = userService.selectByPrimaryKey(id);
        return BSResponse.ok(user);
    }

}
