package com.laidw.mapper;

import com.laidw.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用于操作Account的Mapper接口
 */

@Mapper
public interface AccountMapper {
    void saveAccount(Account account);

    void deleteAccountById(Integer id);
    void deleteAccountByName(String name);
    void deleteAllAccounts();
    void deleteAccountsByRoleId(Integer roleId);

    void updateAccountById(Account account);
    void updateAccountByName(Account account);
    void updateAccountVerifyCodeById(@Param("id") Integer id, @Param("verifyCode") String verifyCode);
    void activateAccountById(Integer id);

    Account selectAccountById(Integer id);
    Account selectAccountByName(String name);
    Account selectAccountByEmail(String email);

    //这个方法的意思是查看除了某个id的账户数据，是否还有其他账户数据的名称为name
    Account selectAccountByNameAndNotId(@Param("name") String name, @Param("id") Integer id);
    //这个方法的意思是查看除了某个id的账户数据，是否还有其他账户数据的邮箱为email
    Account selectAccountByEmailAndNotId(@Param("email") String email, @Param("id") Integer id);

    List<Account> selectAllAccounts();
    String selectAccountVerifyCodeById(Integer id);
}
