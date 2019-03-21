package com.laidw.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * SpringSecurity的配置类
 */

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 注入UserDetailsService
     */
    @Autowired private UserDetailsService service;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //设置访问权限
        http.authorizeRequests()
                //普通页面任何人都可以访问
                .antMatchers("/", "/index", "/login", "/registry", "/verify", "/check/**", "/logout", "/forget", "/modify").permitAll()

                //有user角色的用户（包括更高权限的用户）可以查看各种宝可梦信息
                .antMatchers("/pokemons/**", "/skills/**", "/abilities/**", "/categories/**", "/types/**").hasAnyRole("user", "admin", "super_admin")

                //有admin角色的用户（包括更高权限的用户）可以编辑各种宝可梦信息
                .antMatchers("/pokemon/**", "/skill/**", "/ability/**", "/category/**", "/type/**").hasAnyRole("admin", "super_admin")

                //超级管理员还可以对所有用户进行管理和宝可梦数据初始化
                .antMatchers("/account*/**", "/role*/**", "/data-init/**").hasRole("super_admin");

        //开启登录功能；自定义登录页面，用户用POST方式发送/login请求时SpringSecurity自动验证用户是否存在
        http.formLogin().loginPage("/login");

        //开启记住我功能，用户勾选记住我后SpringSecurity自动向浏览器写入一个Cookie
        http.rememberMe();

        //开启登出功能，用户发送/logout[POST]请求时将会退出登录，回到首页
        http.logout().logoutSuccessUrl("/");

        //关闭CSRF功能，不关闭的话删除操作无法完成；能不能换种方式让程序支持删除操作？
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //使用自定义的UserDetailsService，也就是从数据库中获取账户信息
        auth.userDetailsService(service);
    }
}
