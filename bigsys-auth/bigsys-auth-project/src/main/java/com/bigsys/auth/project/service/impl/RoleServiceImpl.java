package com.bigsys.auth.project.service.impl;

import com.bigsys.auth.project.db.dao.RoleMapper;
import com.bigsys.auth.project.db.model.Role;
import com.bigsys.auth.project.db.model.RoleExample;
import com.bigsys.auth.project.service.RoleService;
import com.bigsys.auth.project.util.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<String, Role, RoleExample, RoleExample.Criteria> implements RoleService{

    @Resource
    private RoleMapper roleMapper;

    @Override
    public Object getDao() {
        return roleMapper;
    }

    @Override
    public Class getExample() {
        return RoleExample.class;
    }

    @Override
    public List<Role> getRoles() {
        List<Role> roles = selectByExample((a, b) -> {});
        Map<String, List<Role>> roleMap = new HashMap<>();
        for (Role role : roles) {
            if (roleMap.get(role.getParentId()) == null) {
                roleMap.put(role.getParentId(), new ArrayList<>());
            }
            roleMap.get(role.getParentId()).add(role);
        }
        Role role = roleMap.get(null).get(0);
        fillRole(role, roleMap);
        List<Role> roleWrapper = new ArrayList<>();
        roleWrapper.add(role);
        return roleWrapper;
    }

    @Override
    public void deleteRole(String id) {
        doDeleteRole(id);
    }

    @Override
    public List<Role> getSubRoles(String roleId) {
        List<Role> roles = new ArrayList<>();
        roles = selectByExample((a, b) -> {
            b.andParentIdEqualTo(roleId);
        });
        List<Role> subSubRoles = new ArrayList<>();
        for (Role role : roles) {
            subSubRoles.addAll(getSubRoles(role.getId()));
        }
        roles.addAll(subSubRoles);
        return roles;
    }

    private void doDeleteRole(String id) {
        List<Role> subRoles = selectByExample((a, b) -> {
            b.andParentIdEqualTo(id);
        });
        deleteByPrimaryKey(id);
        if (subRoles.size() > 0) {
            for (Role subRole : subRoles) {
                doDeleteRole(subRole.getId());
            }
        }
    }

    private void fillRole(Role role, Map<String, List<Role>> roleMap) {
        if (roleMap.get(role.getId()) == null) { //叶子节点
            return;
        }
        role.setRoles(roleMap.get(role.getId()));
        for (Role subRole : roleMap.get(role.getId())) {
            fillRole(subRole, roleMap);
        }
    }
}
