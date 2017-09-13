package com.bigsys.auth.project.service.impl;

import com.bigsys.auth.project.db.dao.UserMapper;
import com.bigsys.auth.project.db.model.Role;
import com.bigsys.auth.project.db.model.User;
import com.bigsys.auth.project.db.model.UserExample;
import com.bigsys.auth.project.db.model.UserRole;
import com.bigsys.auth.project.service.RoleService;
import com.bigsys.auth.project.service.UserRoleService;
import com.bigsys.auth.project.service.UserService;
import com.bigsys.auth.project.util.UUIDGenarator;
import com.bigsys.auth.project.util.service.BaseServiceImpl;
import com.github.pagehelper.PageInfo;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<String, User, UserExample, UserExample.Criteria> implements UserService {

    private final String INIT_PWD = "123456";
    @Resource
    private UserMapper userMapper;

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private RoleService roleService;

    @Override
    public Object getDao() {
        return userMapper;
    }

    @Override
    public Class getExample() {
        return UserExample.class;
    }


    @Override
    public void addOrUpdate(User model) {
        if (StringUtils.isEmpty(model.getId())) {
            model.setId(UUIDGenarator.getUUID());
            model.setPassward(INIT_PWD);
            model.setCreateTime(new Date());
            model.setUpdateTime(new Date());
            userMapper.insert(model);
        } else {
            userMapper.updateByPrimaryKeySelective(model);
        }
        userRoleService.deleteByExample((a, b) -> {
            b.andUsernameEqualTo(model.getUsername());
        });
        for (String roleId : model.getRoleIds()) {
            UserRole userRole = new UserRole();
            userRole.setUsername(model.getUsername());
            userRole.setRole(roleId);
            userRoleService.insert(userRole);
        }
    }

    @Override
    public PageInfo<User> page(User condition, PageInfo<User> pager) {
        User curUser = (User) SecurityUtils.getSubject().getPrincipal();
        List<UserRole> userRoles = userRoleService.getUserRoles(curUser.getUsername());
        List<Role> subRoles = new ArrayList<>();
        for (UserRole userRole : userRoles) {
            subRoles.addAll(roleService.getSubRoles(userRole.getRole()));
        }
        List<UserRole> subUserRoles = userRoleService.getUserRoles(subRoles.stream().map(subRole -> subRole.getId()).collect(Collectors.toList()));
        pager = selectByExampleAndPage(pager, (example, criteria) -> {
            example.setOrderByClause("updateTime desc");
            criteria.andUsernameIn(subUserRoles.stream().map(subUserRole -> subUserRole.getUsername()).collect(Collectors.toList()));
        });
        userRoleService.wrapperRoles(pager.getList());
        return pager;
    }

    @Override
    public User getUser(String username) {
        List<User> users = selectByExample((a, b) -> {
            b.andUsernameEqualTo(username);
        });
        return users.get(0);
    }


}
