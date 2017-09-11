package com.bigsys.auth.project.service;

import com.bigsys.auth.project.db.model.Role;
import com.bigsys.auth.project.db.model.RoleExample;
import com.bigsys.auth.project.util.service.BaseService;

import java.util.List;

public interface RoleService extends BaseService<String, Role, RoleExample, RoleExample.Criteria>{

    /**
     * 获取嵌套结构的role列表
     */
    List<Role> getRoles();

    /**
     * 有子角色的话一同删除
     * @param id
     */
    void deleteRole(String id);
}
