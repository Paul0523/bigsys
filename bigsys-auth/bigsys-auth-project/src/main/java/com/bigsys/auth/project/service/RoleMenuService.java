package com.bigsys.auth.project.service;

import com.bigsys.auth.project.db.model.RoleMenu;
import com.bigsys.auth.project.db.model.RoleMenuExample;
import com.bigsys.auth.project.model.RoleMenus;
import com.bigsys.auth.project.util.service.BaseService;

import java.util.List;

public interface RoleMenuService extends BaseService<Integer, RoleMenu, RoleMenuExample, RoleMenuExample.Criteria> {
    void addRoleMenus(RoleMenus roleMenus);

    List<String> getRoleMenus(String role);

    List<String> getRoleMenus(List<String> roleIds);
}
