package com.bigsys.auth.project.controller;

import com.bigsys.auth.project.db.model.User;
import com.bigsys.auth.project.service.RoleMenuService;
import com.bigsys.auth.project.service.RoleService;
import com.bigsys.auth.project.service.UserRoleService;
import com.bigsys.auth.project.service.WrapperRole;
import com.bigsys.auth.project.util.response.BSResponse;
import com.google.common.collect.Lists;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/menu")
public class MenuController {

    @Resource
    private RoleMenuService roleMenuService;
    @Resource
    private UserRoleService userRoleService;

    @RequestMapping(value = "/getMenus")
    public BSResponse getMenus() {
        User curUser = (User) SecurityUtils.getSubject().getPrincipal();
        userRoleService.wrapperRoles(Lists.newArrayList(curUser));
        if (curUser.getRoleIds().contains("admin")) {
            return BSResponse.ok("all");
        }
        List<String> menus = roleMenuService.getRoleMenus(curUser.getRoleIds());
        return BSResponse.ok(menus);
    }

}
