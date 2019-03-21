package com.laidw.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 该类用于封装和角色相关的信息
 */

@Getter@Setter@ToString
public class Role {
    /**
     * 角色的id，唯一标识
     */
    private Integer id;

    /**
     * 角色的名称
     */
    private String name;
}
