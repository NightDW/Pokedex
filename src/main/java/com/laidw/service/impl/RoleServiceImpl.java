package com.laidw.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.laidw.entity.PageBean;
import com.laidw.entity.Role;
import com.laidw.mapper.AccountMapper;
import com.laidw.mapper.RoleMapper;
import com.laidw.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * RoleService接口的实现类，负责角色的增删改查操作
 */

@Service
public class RoleServiceImpl implements RoleService {
    /**
     * 注入相关的Mapper
     */
    @Autowired private RoleMapper mapper;
    @Autowired private AccountMapper accountMapper;

    @Override
    public void saveRole(Role role) {
        mapper.saveRole(role);
    }

    @Override
    public void deleteRoleById(Integer id) {
        accountMapper.deleteAccountsByRoleId(id);
        mapper.deleteRoleById(id);
    }

    @Override
    public void deleteRoleByName(String name) {
        Role role = mapper.selectRoleByName(name);
        accountMapper.deleteAccountsByRoleId(role.getId());
        mapper.deleteRoleByName(name);
    }

    @Override
    public void deleteAllRoles() {
        accountMapper.deleteAllAccounts();
        mapper.deleteAllRoles();
    }

    @Override
    public void updateRoleById(Role role) {
        mapper.updateRoleById(role);
    }

    @Override
    public Role selectRoleById(Integer id) {
        return mapper.selectRoleById(id);
    }

    @Override
    public List<Role> selectAllRoles() {
        return mapper.selectAllRoles();
    }

    @Override
    public List<Role> selectAllRolesOrderByName() {
        return mapper.selectAllRolesOrderByName();
    }

    @Override
    public PageBean<Role> selectAllRolesLimits(Integer pageNum, Integer pageSize, Integer navigatePages) {
        Page<Role> page = PageHelper.startPage(pageNum, pageSize);
        List<Role> list = mapper.selectAllRoles();
        PageInfo<Role> pageInfo = new PageInfo<>(list, navigatePages);

        PageBean<Role> pageBean = new PageBean<>();
        pageBean.setPage(page);
        pageBean.setList(list);
        pageBean.setPageInfo(pageInfo);
        return pageBean;
    }
}
