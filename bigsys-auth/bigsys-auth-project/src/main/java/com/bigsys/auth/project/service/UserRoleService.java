package com.bigsys.auth.project.service;

import com.bigsys.auth.project.db.model.UserRole;
import com.bigsys.auth.project.db.model.UserRoleExample;
import com.bigsys.auth.project.util.service.BaseService;

import java.util.List;

public interface UserRoleService extends BaseService<Integer, UserRole, UserRoleExample, UserRoleExample.Criteria> {

    /**
     * 为用户包裹上用户对应的角色信息
     * @param list
     */
    void wrapperRoles(List<? extends WrapperRole> list);

    /**
     * 通过用户名称获取用户对应的角色
     * @param username
     * @return
     */
    List<UserRole> getUserRoles(String username);

    /**
     * 根据角色id查询有该角色id的用户
     * @param roleIds
     * @return
     */
    List<UserRole> getUserRoles(List<String> roleIds);
}
