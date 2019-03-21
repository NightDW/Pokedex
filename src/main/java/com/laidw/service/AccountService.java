package com.laidw.service;

import com.laidw.entity.Account;
import com.laidw.entity.PageBean;

import java.util.List;

/**
 * 该Service负责处理账户相关的业务（增删改查）
 */

public interface AccountService {
    void saveAccount(Account account) throws Exception;

    void deleteAccountById(Integer id);
    void deleteAccountByName(String name);
    void deleteAllAccounts();
    void deleteAccountsByRoleId(Integer roleId);

    void updateAccountById(Account account) throws Exception;
    void updateAccountByName(Account account) throws Exception;
    void updateAccountVerifyCodeById(Integer id, String verifyCode);
    void activateAccountById(Integer id);

    Boolean checkIfNameHasExisted(String name);
    Boolean checkIfEmailHasExisted(String email);
    Account selectAccountById(Integer id);
    Account selectAccountByName(String name);
    Account selectAccountByEmail(String email);
    Account selectAccountByNameAndNotId(String name, Integer id);
    Account selectAccountByEmailAndNotId(String email, Integer id);
    List<Account> selectAllAccounts();
    PageBean<Account> selectAllAccountsLimits(Integer pageNum, Integer pageSize, Integer navigatePages);
    String selectAccountVerifyCodeById(Integer id);
}
