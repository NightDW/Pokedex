package com.laidw.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.laidw.entity.Account;
import com.laidw.entity.PageBean;
import com.laidw.mapper.AccountMapper;
import com.laidw.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * AccountService接口的实现类，负责账户的增删改查操作
 */

@Service
public class AccountServiceImpl implements AccountService {
    /**
     * 注入相关的Mapper
     */
    @Autowired private AccountMapper mapper;

    @Override
    public void saveAccount(Account account) throws Exception{
        checkNameAndEmail(account);
        mapper.saveAccount(account);
    }

    @Override
    public void deleteAccountById(Integer id) {
        mapper.deleteAccountById(id);
    }

    @Override
    public void deleteAccountByName(String name) {
        mapper.deleteAccountByName(name);
    }

    @Override
    public void deleteAllAccounts() {
        mapper.deleteAllAccounts();
    }

    @Override
    public void deleteAccountsByRoleId(Integer roleId) {
        mapper.deleteAccountsByRoleId(roleId);
    }

    /**
     * 执行更新操作时，要验证数据是否合法（即判断用户名和邮箱是否重复了）比较麻烦
     * 所以干脆不判断，直接存到数据库，由数据库自己判断数据是否合法
     * 如果数据是不合法的，那么此时会抛出RuntimeException
     * 所以方法上需要声明抛出异常，强制让调用者自己处理异常
     * @param account 存到数据库中的数据
     * @throws Exception 数据不合法时抛出的异常
     */
    public void updateAccountById(Account account) throws Exception{
        mapper.updateAccountById(account);
    }

    /**
     * 执行更新操作时，要验证数据是否合法（即判断用户名和邮箱是否重复了）比较麻烦
     * 所以干脆不判断，直接存到数据库，由数据库自己判断数据是否合法
     * 如果数据是不合法的，那么此时会抛出RuntimeException
     * 所以方法上需要声明抛出异常，强制让调用者自己处理异常
     * @param account 存到数据库中的数据
     * @throws Exception 数据不合法时抛出的异常
     */
    public void updateAccountByName(Account account) throws Exception {
        mapper.updateAccountByName(account);
    }

    @Override
    public void updateAccountVerifyCodeById(Integer id, String verifyCode) {
        mapper.updateAccountVerifyCodeById(id, verifyCode);
    }

    @Override
    public void activateAccountById(Integer id) {
        mapper.activateAccountById(id);
    }

    @Override
    public Boolean checkIfNameHasExisted(String name) {
        return mapper.selectAccountByName(name) != null;
    }

    @Override
    public Boolean checkIfEmailHasExisted(String email) {
        return mapper.selectAccountByEmail(email) != null;
    }

    @Override
    public Account selectAccountById(Integer id) {
        return mapper.selectAccountById(id);
    }

    @Override
    public Account selectAccountByName(String name) {
        return mapper.selectAccountByName(name);
    }

    @Override
    public Account selectAccountByEmail(String email) {
        return mapper.selectAccountByEmail(email);
    }

    @Override
    public Account selectAccountByNameAndNotId(String name, Integer id) {
        return mapper.selectAccountByNameAndNotId(name, id);
    }

    @Override
    public Account selectAccountByEmailAndNotId(String email, Integer id) {
        return mapper.selectAccountByEmailAndNotId(email, id);
    }

    @Override
    public List<Account> selectAllAccounts() {
        return mapper.selectAllAccounts();
    }

    @Override
    public PageBean<Account> selectAllAccountsLimits(Integer pageNum, Integer pageSize, Integer navigatePages) {
        Page<Account> page = PageHelper.startPage(pageNum, pageSize);
        List<Account> list = mapper.selectAllAccounts();
        PageInfo<Account> pageInfo = new PageInfo<>(list, navigatePages);

        PageBean<Account> pageBean = new PageBean<>();
        pageBean.setPage(page);
        pageBean.setList(list);
        pageBean.setPageInfo(pageInfo);
        return pageBean;
    }

    @Override
    public String selectAccountVerifyCodeById(Integer id) {
        return mapper.selectAccountVerifyCodeById(id);
    }

    /**
     * 一个私有的方法，用于检测数据是否合法
     * @param account 被检测的数据
     * @throws Exception 数据不合法时抛出的异常
     */
    private void checkNameAndEmail(Account account) throws Exception{
        if(checkIfNameHasExisted(account.getName()))
            throw new Exception("用户名已存在！");
        if(checkIfEmailHasExisted(account.getEmail()))
            throw new Exception("邮箱已被注册！");
    }
}
