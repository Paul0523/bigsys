package com.bigsys.auth.project.service.impl;

import com.bigsys.auth.project.db.dao.UserRoleMapper;
import com.bigsys.auth.project.db.model.UserRole;
import com.bigsys.auth.project.db.model.UserRoleExample;
import com.bigsys.auth.project.service.WrapperRole;
import com.bigsys.auth.project.service.UserRoleService;
import com.bigsys.auth.project.util.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service("userRoleService")
public class UserRoleServiceImpl extends BaseServiceImpl<Integer, UserRole, UserRoleExample, UserRoleExample.Criteria> implements UserRoleService{

    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public Object getDao() {
        return userRoleMapper;
    }

    @Override
    public Class getExample() {
        return UserRoleExample.class;
    }

    @Override
    public void wrapperRoles(List<? extends WrapperRole> list) {
        for (WrapperRole wrapperRole : list) {
            List<UserRole> userRoles = selectByExample((a, b) -> {
                b.andUsernameEqualTo(wrapperRole.getUsername());
            });
            wrapperRole.setRoleIds(userRoles.stream().map(userRole -> userRole.getRole()).collect(Collectors.toList()));
        }
    }

    @Override
    public List<UserRole> getUserRoles(String username) {
        List<UserRole> userRoles = selectByExample((a, b) -> {
            b.andUsernameEqualTo(username);
        });
        return userRoles;
    }

    @Override
    public List<UserRole> getUserRoles(List<String> roleIds) {
        List<UserRole> userRoles = selectByExample((a, b) -> {
            b.andRoleIn(roleIds);
        });
        return userRoles;
    }
}
