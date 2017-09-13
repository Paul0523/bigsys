package com.bigsys.auth.project.controller;

import com.bigsys.auth.project.db.model.Role;
import com.bigsys.auth.project.model.RoleMenus;
import com.bigsys.auth.project.service.RoleMenuService;
import com.bigsys.auth.project.service.RoleService;
import com.bigsys.auth.project.util.response.BSResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/role")
public class RoleController {

    @Resource
    private RoleService roleService;
    @Resource
    private RoleMenuService roleMenusService;

    @RequestMapping(value = "/addRole")
    public BSResponse addRole(@RequestBody Role role) {
        roleService.insert(role);
        return BSResponse.ok();
    }

    @RequestMapping(value = "/deleteRole")
    public BSResponse deleteRole(String id) {
        roleService.deleteRole(id);
        return BSResponse.ok();
    }

    @RequestMapping(value = "/getRole")
    public BSResponse getRole() {
        List<Role> role = roleService.getRoles();
        return BSResponse.ok(role);
    }

    @RequestMapping(value = "/addRoleMenus")
    public BSResponse addRoleMenu(@RequestBody RoleMenus roleMenus) {
        roleMenusService.addRoleMenus(roleMenus);
        return BSResponse.ok();
    }

    @RequestMapping(value = "/getRoleMenus")
    public BSResponse getRoleMenus(String role) {
        List<String> links = roleMenusService.getRoleMenus(role);
        return BSResponse.ok(links);
    }

    @RequestMapping(value = "/getSubRoles")
    public BSResponse getSubRoles(String roleId) {
        List<Role> roles = roleService.getSubRoles(roleId);
        return BSResponse.ok(roles);
    }
}
