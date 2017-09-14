package com.bigsys.auth.project.service.impl;

import com.bigsys.auth.project.db.dao.RoleMenuMapper;
import com.bigsys.auth.project.db.model.RoleMenu;
import com.bigsys.auth.project.db.model.RoleMenuExample;
import com.bigsys.auth.project.model.RoleMenus;
import com.bigsys.auth.project.service.RoleMenuService;
import com.bigsys.auth.project.util.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service("roleMenuService")
public class RoleMenuServiceImpl extends BaseServiceImpl<Integer, RoleMenu, RoleMenuExample, RoleMenuExample.Criteria> implements RoleMenuService {

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Override
    public Object getDao() {
        return roleMenuMapper;
    }

    @Override
    public Class getExample() {
        return RoleMenuExample.class;
    }

    @Override
    public void addRoleMenus(RoleMenus roleMenus) {
        deleteByExample((a, b) -> {
           b.andRoleEqualTo(roleMenus.getId());
        });
        for (String link : roleMenus.getLinks()) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setMenu(link);
            roleMenu.setRole(roleMenus.getId());
            insert(roleMenu);
        }
    }

    @Override
    public List<String> getRoleMenus(String role) {
        List<RoleMenu> roleMenus = selectByExample((a, b) -> {
            b.andRoleEqualTo(role);
        });
        List<String> menus = roleMenus.stream()
                .map(roleMenu -> roleMenu.getMenu())
                .collect(Collectors.toList());
        return menus;
    }

    @Override
    public List<String> getRoleMenus(List<String> roleIds) {
        List<RoleMenu> roleMenus = selectByExample((a, b) -> {
            b.andRoleIn(roleIds);
        });
        return roleMenus.stream()
                .map(roleMenu -> roleMenu.getMenu())
                .distinct().collect(Collectors.toList());
    }
}
