package com.laidw.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 该类用于封装宝可梦技能的相关信息
 */

@Getter@Setter@ToString
public class Skill {
    /**
     * 技能的id，唯一标识
     */
    private Integer id;

    /**
     * 技能的名称
     */
    private String name;

    /**
     * 技能的属性
     */
    private Type type;

    /**
     * 技能的类型，分为物理，特殊，变化
     */
    private Category category;

    /**
     * 技能的威力
     */
    private Integer power;

    /**
     * 技能的命中率
     */
    private Integer accuracy;

    /**
     * 技能的PP数
     */
    private Integer pp;

    /**
     * 技能的效果
     */
    private String effect;
}
