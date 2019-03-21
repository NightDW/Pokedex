package com.laidw.service;

import com.laidw.entity.PageBean;
import com.laidw.entity.Role;

import java.util.List;

/**
 * 该Service负责处理角色相关的业务（增删改查）
 */

public interface RoleService {
    void saveRole(Role role);

    void deleteRoleById(Integer id);
    void deleteRoleByName(String name);
    void deleteAllRoles();

    void updateRoleById(Role role);

    Role selectRoleById(Integer id);
    List<Role> selectAllRoles();
    List<Role> selectAllRolesOrderByName();
    PageBean<Role> selectAllRolesLimits(Integer pageNum, Integer pageSize, Integer navigatePages);
}
