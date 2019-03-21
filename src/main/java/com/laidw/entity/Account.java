package com.laidw.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 该类用于封装和用户相关的信息
 */

@Getter@Setter@ToString
public class Account {
    /**
     * 账户的id，唯一标识
     */
    private Integer id;

    /**
     * 账户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 绑定邮箱
     */
    private String email;

    /**
     * 该账户是否已经激活；在确认了邮箱后才能激活；账户必须激活后才能使用
     */
    private Boolean isActive;

    /**
     * 该账户拥有的角色；为了方便，只允许一个账户有一个角色
     */
    private Role role;

    /**
     * 验证码，在激活账户时会用到
     */
    private String verifyCode;
}
