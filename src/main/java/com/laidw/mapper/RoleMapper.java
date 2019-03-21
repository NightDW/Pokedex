package com.laidw.mapper;

import com.laidw.entity.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 用于操作Role的Mapper接口；由于比较简单，所以使用注解方式开发
 */

@Mapper
public interface RoleMapper {
    @Insert("INSERT INTO role(name) VALUES(#{name})")
    void saveRole(Role role);

    @Delete("DELETE FROM role WHERE id=#{id}")
    void deleteRoleById(Integer id);

    @Delete("DELETE FROM role WHERE name=#{name}")
    void deleteRoleByName(String name);

    @Delete("DELETE FROM role")
    void deleteAllRoles();

    @Update("UPDATE role SET name=#{name} WHERE id=#{id}")
    void updateRoleById(Role role);

    @Select("SELECT * FROM role WHERE id=#{id}")
    Role selectRoleById(Integer id);

    @Select("SELECT * FROM role WHERE name=#{name}")
    Role selectRoleByName(String name);

    @Select("SELECT * FROM role")
    List<Role> selectAllRoles();

    @Select("SELECT * FROM role ORDER BY name")
    List<Role> selectAllRolesOrderByName();
}
