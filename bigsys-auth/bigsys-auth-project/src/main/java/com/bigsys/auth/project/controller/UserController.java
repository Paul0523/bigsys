package com.bigsys.auth.project.controller;

import com.bigsys.auth.project.db.model.User;
import com.bigsys.auth.project.service.UserService;
import com.bigsys.auth.project.util.BSResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

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

}
