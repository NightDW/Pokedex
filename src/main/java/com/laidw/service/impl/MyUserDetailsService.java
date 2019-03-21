package com.laidw.service.impl;

import com.laidw.entity.Account;
import com.laidw.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

/**
 * 这个Service实现类是和验证用户相关的
 * SpringSecurity将会调用该类中的相关方法来获取数据库中的账户信息
 * 然后通过对比数据库中的账户信息与用户提交的账户信息来确定是否登录成功
 */

@Service
public class MyUserDetailsService implements UserDetailsService {
    /**
     * 注入相关的Mapper
     */
    @Autowired private AccountMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = mapper.selectAccountByName(username);
        if(account == null)
            throw new UsernameNotFoundException("用户名不存在！");

        Collection<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + account.getRole().getName()));
        return new User(account.getName(), account.getPassword(), account.getIsActive(), true, true, true, authorities);
    }
}
